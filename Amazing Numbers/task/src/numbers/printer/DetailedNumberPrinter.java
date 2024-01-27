package numbers.printer;

import numbers.NumberReport;

public class DetailedNumberPrinter implements NumbersPrinter {

    private final static String PATTERN = """
            %nProperties of %d
                          even: %b
                           odd: %b
                          buzz: %b
                          duck: %b
                   palindromic: %b
                        gapful: %b
                           spy: %b%n""";

    @Override
    public void print(NumberReport number) {
        System.out.printf(
                PATTERN,
                number.getNumber(),
                number.isEven(),
                !number.isEven(),
                number.isBuzz(),
                number.isDuck(),
                number.isPalindromic(),
                number.isGap(),
                number.isSpy()
        );
    }
}
