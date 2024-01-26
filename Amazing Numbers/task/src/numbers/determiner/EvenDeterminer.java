package numbers.determiner;

import numbers.NumberReport;
import numbers.NumberUtils;

public class EvenDeterminer implements NumbersDeterminer {
    @Override
    public boolean getProperty(long number) {
        return NumberUtils.isEven(number);
    }

    @Override
    public void setPropertyInReport(NumberReport report) {
        report.setEven(getProperty(report.getNumber()));
    }
}
