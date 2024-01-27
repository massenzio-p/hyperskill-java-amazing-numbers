package numbers.config;

import numbers.filter.Filter;
import numbers.printer.NumbersPrinter;

import java.util.Collection;

public interface GameConfig {
    void setStartNumber(long number);
    long getStartNumber();
    void setGameMode(GameMode mode);
    GameMode getMode();
    void setRepeats(int repeats);
    int getRepeats();
    void setFilters(Collection<Filter> filters);
    Collection<Filter> getFilters();

    void setPrinter(NumbersPrinter printer);
    NumbersPrinter getPrinter();
}
