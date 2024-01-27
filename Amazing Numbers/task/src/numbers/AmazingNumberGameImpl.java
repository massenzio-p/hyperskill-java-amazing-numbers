package numbers;

import numbers.determiner.NumbersDeterminer;
import numbers.filter.Filter;
import numbers.printer.NumbersPrinter;

import java.util.Collection;

public class AmazingNumberGameImpl implements AmazingNumbersGame {

    private final Collection<NumbersDeterminer> determiners;

    private NumbersPrinter printer;
    private Filter filter;

    public AmazingNumberGameImpl(Collection<NumbersDeterminer> determiners) {
        this.determiners = determiners;
    }

    @Override
    public void playAmazingNumbers(long start, int number) {
        for (long num = 0; num < number; start++) {
            NumberReport report = new NumberReportModel(start);
            for (NumbersDeterminer determiner : determiners) {
                determiner.setPropertyInReport(report);
            }
            if (filter == null || filter.filter(report)) {
                this.printer.print(report);
                num++;
            }
        }
    }

    @Override
    public void setPrintStrategy(NumbersPrinter numbersPrinter) {
        this.printer = numbersPrinter;
    }

    @Override
    public void setFilter(Filter filter) {
        this.filter = filter;
    }
}
