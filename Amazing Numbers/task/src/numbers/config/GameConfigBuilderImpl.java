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

            validateSecondArg(repeats);
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

        filterTypes = derriveFilterTypes(strFilters);


        for (FilterType filterType : filterTypes) {
            if (filterTypes.contains(mutiallyExclusiveMap.get(filterType))) {
                String msg = String.format(
                        "The request contains mutually exclusive properties: [%s, %s]",
                        filterType, mutiallyExclusiveMap.get(filterType)
                );
                throw new InvalidInputException(msg);
            }
            filterTypes.add(filterType);
        }

        return filterTypes.stream()
                .map(FilterFactory::getFilter)
                .collect(Collectors.toSet());
    }

    private Collection<FilterType> derriveFilterTypes(String[] strFilters) throws InvalidInputException {
        Collection<String> wrongFilterNames = new HashSet<>();
        Collection<FilterType> filterTypes = new HashSet<>();
        FilterType type;
        // Collect filter types and wrong filter names
        for (String sFilter : strFilters) {
            try {
                filterTypes.add(FilterType.valueOf(sFilter.toUpperCase()));
            } catch (IllegalArgumentException e) {
                wrongFilterNames.add(sFilter.toUpperCase());
            }
        }
        // return if all of them are OK
        if (wrongFilterNames.isEmpty()) return filterTypes;
        // if not all of them are OK throw an exception
        String msg = String.format(
                "The propert%s [%s] %s wrong.\n" +
                        "Available properties: " +
                        "[EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING]%n",
                wrongFilterNames.size() > 1 ? "ies" : "y",
                String.join(", ", wrongFilterNames),
                wrongFilterNames.size() > 1 ? "are" : "is");
        throw new InvalidInputException(msg);
    }

    private void validateSecondArg(int repeats) throws InvalidInputException {
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
