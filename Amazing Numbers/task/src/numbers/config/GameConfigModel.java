package numbers.config;

import numbers.filter.Filter;
import numbers.printer.NumbersPrinter;

import java.util.Collection;

public class GameConfigModel implements GameConfig {

    private long startNumber;
    private int repeats;
    private GameMode gameMode;
    private Collection<Filter> filters;
    private NumbersPrinter printer;

    @Override
    public void setStartNumber(long number) {
        this.startNumber = number;
    }
    @Override
    public long getStartNumber() {
        return this.startNumber;
    }
    @Override
    public void setGameMode(GameMode mode) {
        this.gameMode = mode;
    }
    @Override
    public GameMode getMode() {
        return this.gameMode;
    }
    @Override
    public void setRepeats(int repeats) {
        this.repeats = repeats;
    }
    @Override
    public int getRepeats() {
        return this.repeats;
    }
    @Override
    public void setFilters(Collection<Filter> filters) {
        this.filters = filters;
    }
    @Override
    public Collection<Filter> getFilters() {
        return this.filters;
    }

    @Override
    public void setPrinter(NumbersPrinter printer) {
        this.printer = printer;
    }

    @Override
    public NumbersPrinter getPrinter() {
        return this.printer;
    }
}
