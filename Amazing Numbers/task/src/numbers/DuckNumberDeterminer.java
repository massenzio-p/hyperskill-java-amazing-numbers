package numbers;

public class DuckNumberDeterminer implements NumbersDeterminer {

    @Override
    public boolean getProperty(int number) {
        for (char ch : Integer.toString(number).toCharArray()) {
            if (ch == '0') return true;
        }
        return false;
    }
}
