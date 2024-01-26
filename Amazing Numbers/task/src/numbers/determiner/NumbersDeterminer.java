package numbers.determiner;

import numbers.NumberReport;

public interface NumbersDeterminer {
    boolean getProperty(long number);
    void setPropertyInReport(NumberReport report);
}
