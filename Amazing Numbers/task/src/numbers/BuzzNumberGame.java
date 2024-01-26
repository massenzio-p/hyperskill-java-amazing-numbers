package numbers;

import java.util.Scanner;

public class BuzzNumberGame implements NumbersGame {

    private static final int OK_CODE = 0;
    private static final int ERROR_CODE = 1;

    private final Scanner scanner;

    public BuzzNumberGame(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void play() {
        System.out.println("Enter a natural number");
        int number = scanner.nextInt();

        int code = validateNaturality(number);
        if (code != 0) return;

        determineEvenOrOdd(number);
        String explanation = determineWhetherTheNumberIsBuzz(number);
        System.out.printf("Explanation:%n%s%n", explanation);
    }
    int divisible = 1;
    int endsWith7 = 2;
    private String determineWhetherTheNumberIsBuzz(int number) {
        int resCode = 0;
        if (number % 7 == 0) {
            resCode += divisible;
        }
        if ((number - 7) % 10 == 0) {
            resCode += endsWith7;
        }
        String explanation = "%d is neither divisible by 7 nor does it end with 7.";
        if (resCode == divisible) {
            explanation = "%d is divisible by 7.";
        } else if (resCode == endsWith7) {
            explanation = "%d ends with 7.";
        } else if (resCode == divisible + endsWith7) {
            explanation = "%d is divisible by 7 and ends with 7.";
        }
        System.out.printf("It is %sa Buzz number.%n", resCode > 0  ? "" : "not ");
        return String.format(explanation, number);
    }

    private void determineEvenOrOdd(int number) {
        String evenOdd = "Odd";
        if (number % 2 == 0) {
            evenOdd = "Even";
        }
        System.out.printf("This number is %s.%n", evenOdd);
    }

    private int validateNaturality(int number) {
        if (number > 0) {
            return OK_CODE;
        }
        System.out.println("This number is not natural!");
        return ERROR_CODE;
    }
}
