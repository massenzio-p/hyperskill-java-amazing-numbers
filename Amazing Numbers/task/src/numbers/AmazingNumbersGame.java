package numbers;

import numbers.filter.Filter;
import numbers.printer.NumbersPrinter;

public interface AmazingNumbersGame {

    void playAmazingNumbers(long start, int number);
    void setPrintStrategy(NumbersPrinter numbersPrinter);
    void setFilter(Filter filter);
}
