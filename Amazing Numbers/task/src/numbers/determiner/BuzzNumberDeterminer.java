package numbers.determiner;

import numbers.NumberReport;

class BuzzNumberDeterminer implements NumbersDeterminer {

    private final static int DIVISIBLE_CODE = 1;
    private final static int ENDS_WITH_7_CODE = 2;

    @Override
    public boolean getProperty(long number) {
        return isBuzz(number);
    }

    @Override
    public void setPropertyInReport(NumberReport report) {
        report.setBuzz(getProperty(report.getNumber()));
    }

    private boolean isBuzz(long number) {
        int resCode = 0;
        if (number % 7 == 0) {
            resCode += DIVISIBLE_CODE;
        }
        if ((number - 7) % 10 == 0) {
            resCode += ENDS_WITH_7_CODE;
        }
        return resCode > 0;
    }
}
