package numbers;

import numbers.config.GameConfig;
import numbers.determiner.NumbersDeterminer;
import numbers.filter.Filter;
import numbers.printer.NumbersPrinter;

import java.util.Collection;

public class AmazingNumberGameImpl implements AmazingNumbersGame {

    private final Collection<NumbersDeterminer> determiners;

    public AmazingNumberGameImpl(Collection<NumbersDeterminer> determiners) {
        this.determiners = determiners;
    }

    @Override
    public void playAmazingNumbers(GameConfig config) {
        for (long num = config.getStartNumber(), counter = 0; counter < config.getRepeats(); num++) {
            NumberReport report = new NumberReportModel(num);
            for (NumbersDeterminer determiner : determiners) {
                determiner.setPropertyInReport(report);
            }

            Collection<Filter> filters = config.getFilters();
            if (filters != null && !filters.isEmpty()) {
                boolean continueLoop = false;
                for (Filter filter : filters) {
                    if (!filter.filter(report)) {
                        continueLoop = true;
                        break;
                    }
                }
                if (continueLoop) continue;
            }
            counter++;
            config.getPrinter().print(report);
        }
    }
}
