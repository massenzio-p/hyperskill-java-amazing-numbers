package numbers.filter;

import numbers.NumberReport;

import java.util.HashMap;
import java.util.Map;

public class FilterFactory {
    private static final Map<FilterType, Filter> filterCache = new HashMap<>();

    public static Filter getFilter(FilterType type) {
        return filterCache.computeIfAbsent(type, FilterFactory::createFilter);
    }

    private static Filter createFilter(FilterType type) {
        return switch (type) {
            case EVEN -> (NumberReport::isEven);
            case ODD -> (report -> !report.isEven());
            case BUZZ -> (NumberReport::isBuzz);
            case DUCK -> (NumberReport::isDuck);
            case PALINDROMIC -> (NumberReport::isPalindromic);
            case GAPFUL -> (NumberReport::isGap);
            case SPY -> (NumberReport::isSpy);
            case SUNNY -> (NumberReport::isSunny);
            case SQUARE -> (NumberReport::isSquare);
        };
    }
}
