package numbers;

import numbers.determiner.NumbersDeterminer;
import numbers.printer.NumbersPrinter;

import java.util.Collection;

public class AmazingNumberGameImpl implements AmazingNumbersGame {

    private final Collection<NumbersDeterminer> determiners;

    private NumbersPrinter printer;

    public AmazingNumberGameImpl(Collection<NumbersDeterminer> determiners,
                                 NumbersPrinter printer) {
        this.determiners = determiners;
        this.printer = printer;
    }

    @Override
    public void playAmazingNumbers(long[] numbers) {
        for (long num : numbers) {
            NumberReport report = new NumberReportModel(num);
            for (NumbersDeterminer determiner : determiners) {
                determiner.setPropertyInReport(report);
            }
            this.printer.print(report);
        }
    }

    @Override
    public void setPrintStrategy(NumbersPrinter numbersPrinter) {
        this.printer = numbersPrinter;
    }
}
