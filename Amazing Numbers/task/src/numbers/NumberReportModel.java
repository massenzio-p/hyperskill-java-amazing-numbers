package numbers;

public class NumberReportModel implements NumberReport {

    private final long number;
    private boolean isEven;
    private boolean isBuzz;
    private boolean isDuck;
    private boolean isPalindromic;
    private boolean isGap;
    private boolean isSpy;
    private boolean isSquare;
    private boolean isSunny;
    private boolean isJumping;
    private boolean isHappy;

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
    public boolean isSquare() {
        return isSquare;
    }

    @Override
    public boolean isSunny() {
        return isSunny;
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
    public void setSquare(boolean isSquare) {
        this.isSquare = isSquare;
    }

    @Override
    public void setSunny(boolean isSunny) {
        this.isSunny = isSunny;
    }

    @Override
    public boolean isJumping() {
        return this.isJumping;
    }

    @Override
    public void setJumping(boolean isJumping) {
        this.isJumping = isJumping;
    }

    @Override
    public boolean isHappy() {
        return this.isHappy;
    }

    @Override
    public void setHappy(boolean isHappy) {
        this.isHappy = isHappy;
    }
}
