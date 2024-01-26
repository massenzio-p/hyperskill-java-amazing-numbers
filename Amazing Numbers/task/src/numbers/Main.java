package numbers;

import numbers.determiner.NumberDeterminerFactory;
import numbers.determiner.NumbersDeterminer;
import numbers.printer.PrinterFactory;

import java.util.Collection;
import java.util.Scanner;
import java.util.Set;

public class Main {

    private final static String INTRO = """
            Welcome to Amazing Numbers!
                        
            Supported requests:
            - enter a natural number to know its properties;
            - enter two natural numbers to obtain the properties of the list:
              * the first parameter represents a starting number;
              * the second parameter shows how many consecutive numbers are to be printed;
            - separate the parameters with one space;
            - enter 0 to exit.""";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        playGame(scanner);
        scanner.close();
    }

    private static void playGame(Scanner scanner) {
        var singleNumPrinter = PrinterFactory.createPrintStrategy(PrinterFactory.PrinterMode.DETAILED);
        var multiNumPrinter = PrinterFactory.createPrintStrategy(PrinterFactory.PrinterMode.BRIEF);

        Collection<NumbersDeterminer> determiners = Set.of(
                NumberDeterminerFactory.createNumberDeterminer(NumberDeterminerFactory.DeterminerType.BUZZ),
                NumberDeterminerFactory.createNumberDeterminer(NumberDeterminerFactory.DeterminerType.DUCK),
                NumberDeterminerFactory.createNumberDeterminer(NumberDeterminerFactory.DeterminerType.PALINDROMIC),
                NumberDeterminerFactory.createNumberDeterminer(NumberDeterminerFactory.DeterminerType.EVEN),
                NumberDeterminerFactory.createNumberDeterminer(NumberDeterminerFactory.DeterminerType.GAP)
        );
        var game = new AmazingNumberGameImpl(determiners, singleNumPrinter);

        System.out.println(INTRO);
        long number;
        int repeats;
        while (true) {
            repeats = 1;
            System.out.print("\nEnter a request: ");
            String[] input = scanner.nextLine().split(" ");
            number = Long.parseLong(input[0]);

            // check command to exit
            if (number == 0) {
                break;
            }
            // check if first param is a natural number
            if (!NumberUtils.isNatural(number)) {
                System.out.println("\nThe first parameter should be a natural number or zero.");
                continue;
            }
            // check if mode is single number or repeatable
            if (input.length > 1) {
                repeats = Integer.parseInt(input[1]);
                if (!NumberUtils.isNatural(repeats)) {
                    System.out.println("The second parameter should be a natural number.");
                    continue;
                }
            }

            if (input.length > 1) {
                game.setPrintStrategy(multiNumPrinter);
            } else {
                game.setPrintStrategy(singleNumPrinter);
            }
            long[] numbers = new long[repeats];
            for (int i = 0; i < repeats; i++) {
                numbers[i] = number++;
            }
            game.playAmazingNumbers(numbers);
        }
        System.out.println("\nGoodbye!");
    }
}
