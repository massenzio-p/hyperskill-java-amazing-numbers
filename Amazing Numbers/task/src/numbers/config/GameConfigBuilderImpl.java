package numbers.config;

import numbers.NumberUtils;
import numbers.exception.InvalidInputException;
import numbers.filter.Filter;
import numbers.filter.FilterFactory;
import numbers.filter.FilterType;
import numbers.printer.NumbersPrinter;
import numbers.printer.PrinterFactory;

import java.util.*;
import java.util.stream.Collectors;


public class GameConfigBuilderImpl implements GameConfigBuilder {

    private final GameConfig config = new GameConfigModel();
    private final HashMap<FilterType, FilterType> mutiallyExclusiveMap = new HashMap<>() {{
        put(FilterType.EVEN, FilterType.ODD);
        put(FilterType.ODD, FilterType.EVEN);
        put(FilterType.DUCK, FilterType.SPY);
        put(FilterType.SPY, FilterType.DUCK);
        put(FilterType.SUNNY, FilterType.SQUARE);
        put(FilterType.SQUARE, FilterType.SUNNY);
    }};

    @Override
    public GameConfig buildConfig(String[] input) throws InvalidInputException {
        if (input == null || input.length == 0) throw new InvalidInputException("Input must exist");

        // check command to exit
        if (input[0].equals("0")) return null;

        // check if first param is a natural number
        GameMode mode = determineGameMode(input);
        config.setGameMode(mode);

        long startNumber = Long.parseLong(input[0]);
        validateFirstArg(startNumber);

        NumbersPrinter printer;
        if (mode != GameMode.SINGLE_NUMBER) {
            printer = PrinterFactory.createPrintStrategy(PrinterFactory.PrinterMode.BRIEF);
            int repeats = Integer.parseInt(input[1]);

            validateSecondArt(repeats);
            config.setRepeats(repeats);

            if (mode == GameMode.FILTERED_SET_OF_NUMBERS) {
                String[] strFilters = Arrays.copyOfRange(input, 2, input.length);
                Collection<Filter> filters = mapFilters(strFilters);
                config.setFilters(filters);
            }
        } else {
            config.setRepeats(1);
            printer = PrinterFactory.createPrintStrategy(PrinterFactory.PrinterMode.DETAILED);
        }

        config.setPrinter(printer);
        config.setStartNumber(startNumber);

        return config;
    }

    private Collection<Filter> mapFilters(String[] strFilters) throws InvalidInputException {
        Collection<FilterType> filterTypes = new HashSet<>();

        FilterType type;
        for (String sFilter : strFilters) {
            type = (mapFilter(sFilter.toUpperCase()));
            if (filterTypes.contains(mutiallyExclusiveMap.get(type))) {
                String msg = String.format(
                        "The request contains mutually exclusive properties: [%s, %s]",
                        type, mutiallyExclusiveMap.get(type)
                );
                throw new InvalidInputException(msg);
            }
            filterTypes.add(type);
        }

        return filterTypes.stream()
                .map(FilterFactory::getFilter)
                .collect(Collectors.toSet());
    }

    private FilterType mapFilter(String stringFilter) throws InvalidInputException {
        try {
            return FilterType.valueOf(stringFilter);
        } catch (IllegalArgumentException e) {
            String msg = String.format(
                    "The property [%s] is wrong.\n" +
                            "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]%n",
                    stringFilter);
            throw new InvalidInputException(msg);
        }
    }

    private void validateSecondArt(int repeats) throws InvalidInputException {
        if (!NumberUtils.isNatural(repeats)) {
            throw new InvalidInputException("The second parameter should be a natural number.");
        }
    }

    private void validateFirstArg(long s) throws InvalidInputException {
        if (!NumberUtils.isNatural(s)) {
            throw new InvalidInputException("\nThe first parameter should be a natural number or zero.");
        }
    }

    private static GameMode determineGameMode(String[] input) {
        return switch (input.length) {
            case 1 -> GameMode.SINGLE_NUMBER;
            case 2 -> GameMode.UNFILTERED_SET_OF_NUMBERS;
            default -> GameMode.FILTERED_SET_OF_NUMBERS;
        };
    }
}
