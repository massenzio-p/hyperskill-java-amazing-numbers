package numbers.determiner;

import numbers.NumberReport;

class DuckNumberDeterminer implements NumbersDeterminer {

    @Override
    public boolean getProperty(long number) {
        for (char ch : Long.toString(number).toCharArray()) {
            if (ch == '0') return true;
        }
        return false;
    }

    @Override
    public void setPropertyInReport(NumberReport report) {
        report.setDuck(getProperty(report.getNumber()));
    }
}
