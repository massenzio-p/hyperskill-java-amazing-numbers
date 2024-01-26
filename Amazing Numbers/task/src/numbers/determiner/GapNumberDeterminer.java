package numbers.determiner;

import numbers.NumberReport;

class GapNumberDeterminer implements NumbersDeterminer {
    @Override
    public boolean getProperty(long number) {
        String strNumber = Long.toString(number);

        if (strNumber.length() < 3) return false;

        int divider = Integer.parseInt(String.format(
                "%c%c",
                strNumber.charAt(0),
                strNumber.charAt(strNumber.length() - 1)));
        return number % divider == 0;
    }

    @Override
    public void setPropertyInReport(NumberReport report) {
        report.setGap(getProperty(report.getNumber()));
    }
}
