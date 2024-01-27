package numbers.printer;

import numbers.NumberReport;
import numbers.printer.NumbersPrinter;

public class BriefNumberPrinter implements NumbersPrinter {
    public static final String PATTERN = "             %d is %s%s%s%s%s%s%n";

    @Override
    public void print(NumberReport number) {
        System.out.printf(
                PATTERN,
                number.getNumber(),
                number.isBuzz() ? "buzz, " : "",
                number.isDuck() ? "duck, " : "",
                number.isPalindromic() ? "palindromic, " : "",
                number.isGap() ? "gapful, " : "",
                number.isSpy() ? "spy, " : "",
                number.isEven() ? "even" : "odd"
        );
    }
}
