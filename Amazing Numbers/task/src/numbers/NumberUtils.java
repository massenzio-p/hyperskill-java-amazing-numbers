package numbers;

public class NumberUtils {

    static boolean isNatural(int number) {
        if (number > 0) {
            return true;
        }
        System.out.println("This number is not natural!");
        return false;
    }

    static boolean isEven(int number) {
        return number % 2 == 0;
    }
}
