package numbers.filter;

import numbers.NumberReport;

@FunctionalInterface
public interface Filter {
    boolean filter(NumberReport report);
}
