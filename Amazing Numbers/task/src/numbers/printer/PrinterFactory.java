package numbers.printer;

public class PrinterFactory {

    public enum PrinterMode {
        BRIEF, DETAILED
    }

    public static NumbersPrinter createPrintStrategy(PrinterMode mode) {
        return switch (mode) {
            case BRIEF -> new BriefNumberPrinter();
            default -> new DetailedNumberPrinter();
        };
    }
}
