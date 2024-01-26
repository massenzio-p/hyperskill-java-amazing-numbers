package numbers;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        playGame(scanner);
        scanner.close();
    }

    private static void playGame(Scanner scanner) {
        System.out.println("Enter a natural number");
        int number = scanner.nextInt();

        if (!NumberUtils.isNatural(number)) {
            return;
        }

        NumbersDeterminer buzzNumberDeterminer = new BuzzNumberDeterminer();
        NumbersDeterminer duckNumberDeterminer = new DuckNumberDeterminer();
        boolean isEven = NumberUtils.isEven(number);

        System.out.printf("Properties of %d%n" +
                "        even: %b%n" +
                "         odd: %b%n" +
                "        buzz: %b%n" +
                "        duck: %b%n",
                number,
                isEven,
                !isEven,
                buzzNumberDeterminer.getProperty(number),
                duckNumberDeterminer.getProperty(number)
                );
    }
}
