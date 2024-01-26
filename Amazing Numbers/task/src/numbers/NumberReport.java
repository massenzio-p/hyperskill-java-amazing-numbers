package numbers;

public interface NumberReport {
    long getNumber();
    boolean isEven();
    boolean isBuzz();
    boolean isDuck();
    boolean isPalindromic();
    boolean isGap();
    void setEven(boolean isEven);
    void setBuzz(boolean isBuzz);
    void setDuck(boolean isDuck);
    void setPalindromic(boolean isPalindromic);
    void setGap(boolean isGap);
}
