package numbers;

public class NumberReportModel implements NumberReport {

    private final long number;
    private boolean isEven;
    private boolean isOdd;
    private boolean isBuzz;
    private boolean isDuck;
    private boolean isPalindromic;
    private boolean isGap;
    private boolean isSpy;

    public NumberReportModel(long number) {
        this.number = number;
    }

    @Override
    public long getNumber() {
        return this.number;
    }

    @Override
    public boolean isEven() {
        return this.isEven;
    }
    @Override
    public boolean isBuzz() {
        return this.isBuzz;
    }
    @Override
    public boolean isDuck() {
        return this.isDuck;
    }
    @Override
    public boolean isPalindromic() {
        return this.isPalindromic;
    }
    @Override
    public boolean isGap() {
        return this.isGap;
    }
    @Override
    public void setEven(boolean isEven) {
        this.isEven = isEven;
    }
    @Override
    public void setBuzz(boolean isBuzz) {
        this.isBuzz = isBuzz;
    }
    @Override
    public void setDuck(boolean isDuck) {
        this.isDuck = isDuck;
    }
    @Override
    public void setPalindromic(boolean isPalindromic) {
        this.isPalindromic = isPalindromic;
    }
    @Override
    public void setGap(boolean isGap) {
        this.isGap = isGap;
    }
    @Override
    public boolean isSpy() {
        return this.isSpy;
    }
    @Override
    public void setSpy(boolean spy) {
        this.isSpy = spy;
    }
    @Override
    public void setOdd(boolean isOdd) {
        this.isOdd = isOdd;
    }
}
