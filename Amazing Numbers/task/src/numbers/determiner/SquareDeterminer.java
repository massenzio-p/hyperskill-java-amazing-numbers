package numbers.determiner;

import numbers.NumberReport;

public class SquareDeterminer implements NumbersDeterminer {
    @Override
    public boolean getProperty(long number) {
        return number % Math.sqrt(number) == 0;
    }

    @Override
    public void setPropertyInReport(NumberReport report) {
        report.setSquare(getProperty(report.getNumber()));
    }
}
