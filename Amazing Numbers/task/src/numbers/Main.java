package numbers;

import numbers.config.GameConfig;
import numbers.config.GameConfigBuilder;
import numbers.config.GameConfigBuilderImpl;
import numbers.determiner.NumberDeterminerFactory;
import numbers.determiner.NumbersDeterminer;
import numbers.exception.InvalidInputException;
import numbers.filter.FilterType;
import numbers.printer.PrinterFactory;

import java.util.Collection;
import java.util.Scanner;

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
            - two natural numbers and two properties to search for;
            - separate the parameters with one space;
            - enter 0 to exit.""";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        playGame(scanner);
        scanner.close();
    }


    private static void playGame(Scanner scanner) {
        var game = new AmazingNumberGameImpl(NumberDeterminerFactory.getAllTypes());

        System.out.println(INTRO);

        GameConfig config;
        GameConfigBuilder gameConfigBuilder = new GameConfigBuilderImpl();

        while (true) {

            System.out.print("\nEnter a request: ");
            String[] input = scanner.nextLine().split(" ");

            try {
                config = gameConfigBuilder.buildConfig(input);
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                System.out.println("There are no numbers with these properties.");
                continue;
            }
            if (config == null) break;

            game.playAmazingNumbers(config);
        }
        System.out.println("\nGoodbye!");
    }
}
