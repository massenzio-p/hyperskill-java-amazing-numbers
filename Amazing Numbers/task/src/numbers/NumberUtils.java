package numbers;

public class NumberUtils {

    static boolean isNatural(long number) {
        if (number > 0) {
            return true;
        }
        return false;
    }

    static boolean isEven(long number) {
        return number % 2 == 0;
    }
}
