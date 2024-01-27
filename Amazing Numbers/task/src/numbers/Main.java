package numbers;

import numbers.determiner.NumberDeterminerFactory;
import numbers.determiner.NumbersDeterminer;
import numbers.filter.FilterFactory;
import numbers.filter.FilterType;
import numbers.printer.PrinterFactory;

import java.util.Collection;
import java.util.Scanner;
import java.util.Set;

public class Main {
    Throwable d;
    private final static String INTRO = """
            Welcome to Amazing Numbers!
                        
            Supported requests:
            - enter a natural number to know its properties;
            - enter two natural numbers to obtain the properties of the list:
              * the first parameter represents a starting number;
              * the second parameter shows how many consecutive numbers are to be printed;
            - two natural numbers and a property to search for;
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
                NumberDeterminerFactory.createNumberDeterminer(NumberDeterminerFactory.DeterminerType.GAP),
                NumberDeterminerFactory.createNumberDeterminer(NumberDeterminerFactory.DeterminerType.SPY)
        );
        var game = new AmazingNumberGameImpl(determiners);

        System.out.println(INTRO);
        long number;
        int repeats;
        FilterType filter;

        while (true) {
            // initialization
            repeats = 1;
            game.setFilter(null);

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
            // set filter
            if (input.length == 3) {
                String filterString = input[2];
                try {
                    filter = FilterType.valueOf(filterString);
                    game.setFilter(FilterFactory.getFilter(filter));
                } catch (IllegalArgumentException e) {
                    System.out.printf("The property [%s] is wrong.\n" +
                                    "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY]%n",
                            filterString);
                    continue;
                }
            }
            // set printer
            if (input.length > 1) {
                game.setPrintStrategy(multiNumPrinter);
            } else {
                game.setPrintStrategy(singleNumPrinter);
            }
            game.playAmazingNumbers(number, repeats);
        }
        System.out.println("\nGoodbye!");
    }
}
