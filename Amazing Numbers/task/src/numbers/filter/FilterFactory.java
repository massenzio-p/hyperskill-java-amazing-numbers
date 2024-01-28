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
            case EVEN, NOT_ODD -> (NumberReport::isEven);
            case NOT_EVEN, ODD -> (report -> !report.isEven());
            case BUZZ -> (NumberReport::isBuzz);
            case NOT_BUZZ -> (report -> !report.isBuzz());
            case DUCK -> (NumberReport::isDuck);
            case NOT_DUCK -> (report -> !report.isDuck());
            case PALINDROMIC -> (NumberReport::isPalindromic);
            case NOT_PALINDROMIC -> (report -> !report.isPalindromic());
            case GAPFUL -> (NumberReport::isGap);
            case NOT_GAPFUL -> (report -> !report.isGap());
            case SPY -> (NumberReport::isSpy);
            case NOT_SPY -> (report -> !report.isSpy());
            case SUNNY -> (NumberReport::isSunny);
            case NOT_SUNNY -> (report -> !report.isSunny());
            case SQUARE -> (NumberReport::isSquare);
            case NOT_SQUARE -> (report -> !report.isSquare());
            case JUMPING -> (NumberReport::isJumping);
            case NOT_JUMPING -> (report -> !report.isJumping());
            case HAPPY, NOT_SAD -> (NumberReport::isHappy);
            case NOT_HAPPY, SAD -> (report -> !report.isHappy());
        };
    }
}
