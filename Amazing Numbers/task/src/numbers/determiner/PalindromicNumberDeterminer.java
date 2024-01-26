package numbers.determiner;

import numbers.NumberReport;

class PalindromicNumberDeterminer implements NumbersDeterminer {
    @Override
    public boolean getProperty(long number) {
        String stringNumber = Long.toString(number);
        for (int i = 0; i < stringNumber.length() / 2; i++) {
            if (stringNumber.charAt(i) != stringNumber.charAt(stringNumber.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void setPropertyInReport(NumberReport report) {
        report.setPalindromic(getProperty(report.getNumber()));
    }
}
