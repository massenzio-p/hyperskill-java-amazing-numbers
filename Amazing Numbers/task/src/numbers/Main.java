package numbers;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        NumbersGame buzzNumberGame = new BuzzNumberGame(scanner);
        buzzNumberGame.play();
        scanner.close();
    }
}
