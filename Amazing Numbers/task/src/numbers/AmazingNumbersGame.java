package numbers;

import numbers.printer.NumbersPrinter;

public interface AmazingNumbersGame {

    void playAmazingNumbers(long[] numbers);
    void setPrintStrategy(NumbersPrinter numbersPrinter);
}
