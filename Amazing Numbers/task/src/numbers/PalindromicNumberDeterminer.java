package numbers;

import java.util.Locale;

public class PalindromicNumberDeterminer implements NumbersDeterminer {
    @Override
    public boolean getProperty(long number) {
        String stringNumber = Long.toString(number);
        for (int i = 0; i < stringNumber.length() / 2; i++) {
            if (stringNumber.charAt(i) != stringNumber.charAt(stringNumber.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }
}
