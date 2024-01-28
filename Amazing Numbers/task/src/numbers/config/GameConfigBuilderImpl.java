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

import static numbers.filter.FilterType.*;


public class GameConfigBuilderImpl implements GameConfigBuilder {

    private final GameConfig config = new GameConfigModel();
    private final HashMap<FilterType, Set<FilterType>> mutuallyExclusiveMap = new HashMap<>() {{
        put(EVEN, Set.of(NOT_EVEN, ODD));
        put(ODD, Set.of(NOT_ODD, EVEN));
        put(BUZZ, Set.of(NOT_BUZZ));
        put(DUCK, Set.of(NOT_DUCK, SPY));
        put(PALINDROMIC, Set.of(NOT_PALINDROMIC));
        put(GAPFUL, Set.of(NOT_GAPFUL));
        put(SPY, Set.of(NOT_SPY, DUCK));
        put(SQUARE, Set.of(NOT_SQUARE, SUNNY));
        put(SUNNY, Set.of(NOT_SUNNY, SQUARE));
        put(JUMPING, Set.of(NOT_JUMPING));
        put(HAPPY, Set.of(NOT_HAPPY, SAD));
        put(SAD, Set.of(NOT_SAD, HAPPY));

        put(NOT_EVEN, Set.of(EVEN, NOT_ODD));
        put(NOT_ODD, Set.of(ODD, NOT_EVEN));
        put(NOT_BUZZ, Set.of(BUZZ));
        put(NOT_DUCK, Set.of(DUCK, NOT_SPY));
        put(NOT_PALINDROMIC, Set.of(PALINDROMIC));
        put(NOT_GAPFUL, Set.of(GAPFUL));
        put(NOT_SPY, Set.of(SPY, NOT_DUCK));
        put(NOT_SQUARE, Set.of(SQUARE, NOT_SUNNY));
        put(NOT_SUNNY, Set.of(SUNNY, NOT_SQUARE));
        put(NOT_JUMPING, Set.of(JUMPING));
        put(NOT_HAPPY, Set.of(HAPPY, NOT_SAD));
        put(NOT_SAD, Set.of(SAD, NOT_HAPPY));
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
        Collection<FilterType> filterTypes = deriveFilterTypes(strFilters);

        for (FilterType filterType : filterTypes) {
            FilterType exclusiveOne = findExclusive(filterTypes, filterType);
            if (exclusiveOne != null) {
                String msg = String.format(
                        "The request contains mutually exclusive properties: [%s, %s]",
                        filterType.toString().replaceFirst("NOT_", "-"),
                        exclusiveOne.toString().replaceFirst("NOT_", "-")
                );
                throw new InvalidInputException(msg);
            }
            filterTypes.add(filterType);
        }

        return filterTypes.stream()
                .map(FilterFactory::getFilter)
                .collect(Collectors.toSet());
    }

    private FilterType findExclusive(Collection<FilterType> filterTypes, FilterType filterType) {
        for (var exclusiveType : mutuallyExclusiveMap.get(filterType)) {
            if (filterTypes.contains(exclusiveType)) {
                return exclusiveType;
            }
        }
        return null;
    }

    private Collection<FilterType> deriveFilterTypes(String[] strFilters) throws InvalidInputException {
        Collection<String> wrongFilterNames = new HashSet<>();
        Collection<FilterType> filterTypes = new HashSet<>();
        FilterType type;
        // Collect filter types and wrong filter names
        for (String sFilter : strFilters) {
            try {
                sFilter = sFilter.replaceFirst("-", "NOT_").toUpperCase();
                filterTypes.add(FilterType.valueOf(sFilter.toUpperCase()));
            } catch (IllegalArgumentException e) {
                wrongFilterNames.add(sFilter.replaceFirst("NOT_", "-").toUpperCase());
            }
        }
        // return if all of them are OK
        if (wrongFilterNames.isEmpty()) return filterTypes;
        // if not all of them are OK throw an exception
        String msg = String.format(
                "The propert%s [%s] %s wrong.\n" +
                        "Available properties: " +
                        "[EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]%n",
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
