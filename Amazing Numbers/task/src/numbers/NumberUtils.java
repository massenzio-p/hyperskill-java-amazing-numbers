package numbers;

public class NumberUtils {

    public static boolean isNatural(long number) {
        if (number > 0) {
            return true;
        }
        return false;
    }

    public static boolean isEven(long number) {
        return number % 2 == 0;
    }
}
