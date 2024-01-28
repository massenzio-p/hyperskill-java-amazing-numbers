package numbers.determiner;

import numbers.NumberReport;

public class JumpingNumberDeterminer implements NumbersDeterminer {

    @Override
    public boolean getProperty(long number) {
        if (number < 10) return true;
        char[] charNumbers = Long.toString(number).toCharArray();

        for (int i = 1; i < charNumbers.length; i++) {
            int previousNumber = Integer.parseInt(String.valueOf(charNumbers[i - 1]));
            int currentNumber = Integer.parseInt(String.valueOf(charNumbers[i]));

            if (Math.abs(currentNumber - previousNumber) != 1) return false;
        }
        return true;
    }

    @Override
    public void setPropertyInReport(NumberReport report) {
        report.setJumping(getProperty(report.getNumber()));
    }
}
