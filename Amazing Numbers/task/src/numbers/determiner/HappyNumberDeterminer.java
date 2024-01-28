package numbers.determiner;

import numbers.NumberReport;

public class HappyNumberDeterminer implements NumbersDeterminer {
    @Override
    public boolean getProperty(long baseNumber) {
        long buffNumber = baseNumber;
        while (true) {
            buffNumber = proceedNumber(buffNumber);

            // the baseNumber is happy
            if (buffNumber == 1) return true;
            // the baseNumber is unhappy
            if (buffNumber == 4) return false;
        }
    }

    private long proceedNumber(long stringNumber) {
        long res = 0;
        long buffNumber;
        for (char number : Long.toString(stringNumber).toCharArray()) {
            buffNumber = Long.parseLong(String.valueOf(number));
            res += buffNumber * buffNumber;
        }
        return res;
    }

    @Override
    public void setPropertyInReport(NumberReport report) {
            report.setHappy(getProperty(report.getNumber()));
    }
}
