package numbers.determiner;

import numbers.NumberReport;

public class SpyDeterminer implements NumbersDeterminer {
    @Override
    public boolean getProperty(long number) {
        String stringNum = Long.toString(number);
        long sum = 0;
        long mult = 1;
        for (int i = 0; i < stringNum.length(); i++) {
            sum += Long.parseLong(String.valueOf(stringNum.charAt(i)));
            mult *= Long.parseLong(String.valueOf(stringNum.charAt(i)));
        }
        return sum == mult;
    }

    @Override
    public void setPropertyInReport(NumberReport report) {
        report.setSpy(getProperty(report.getNumber()));
    }
}
