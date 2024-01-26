package numbers;

import java.util.Scanner;

public class Main {

    private final static String INTRO = """
            Welcome to Amazing Numbers!
                        
            Supported requests:
            - enter a natural number to know its properties;
            - enter 0 to exit.""";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        playGame(scanner);
        scanner.close();
    }

    private static void playGame(Scanner scanner) {
        System.out.println(INTRO);
        long number;
        while (true) {
            System.out.print("\nEnter a request: ");
            number = scanner.nextLong();

            if (number == 0) {
                break;
            }

            if (!NumberUtils.isNatural(number)) {
                System.out.println("\nThe first parameter should be a natural number or zero.");
                continue;
            }

            System.out.println();
            NumbersDeterminer buzzNumberDeterminer = new BuzzNumberDeterminer();
            NumbersDeterminer duckNumberDeterminer = new DuckNumberDeterminer();
            NumbersDeterminer palindromicNumberDeterminer = new PalindromicNumberDeterminer();
            boolean isEven = NumberUtils.isEven(number);

            System.out.printf("Properties of %d%n" +
                            "        even: %b%n" +
                            "         odd: %b%n" +
                            "        buzz: %b%n" +
                            "        duck: %b%n" +
                            " palindromic: %b%n",
                    number,
                    isEven,
                    !isEven,
                    buzzNumberDeterminer.getProperty(number),
                    duckNumberDeterminer.getProperty(number),
                    palindromicNumberDeterminer.getProperty(number)
            );
        }
        System.out.println("\nGoodbye!");
    }
}
