package numbers.determiner;

import numbers.NumberReport;

public class SunnyDeterminer implements NumbersDeterminer {

    private final NumbersDeterminer squareDeterminer;

    public SunnyDeterminer(NumbersDeterminer numberDeterminer) {
        this.squareDeterminer = numberDeterminer;
    }

    @Override
    public boolean getProperty(long number) {
        return this.squareDeterminer.getProperty(number + 1);
    }

    @Override
    public void setPropertyInReport(NumberReport report) {
        report.setSunny(getProperty(report.getNumber()));
    }
}
