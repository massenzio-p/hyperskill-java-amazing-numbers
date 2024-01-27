package numbers.printer;

import numbers.NumberReport;

@FunctionalInterface
public interface NumbersPrinter {

    void print(NumberReport number);
}
