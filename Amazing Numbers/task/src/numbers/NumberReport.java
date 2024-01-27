package numbers;

public interface NumberReport {
    long getNumber();
    boolean isEven();
    boolean isBuzz();
    boolean isDuck();
    boolean isPalindromic();
    boolean isGap();
    boolean isSpy();
    void setEven(boolean isEven);
    void setOdd(boolean isOdd);
    void setBuzz(boolean isBuzz);
    void setDuck(boolean isDuck);
    void setPalindromic(boolean isPalindromic);
    void setGap(boolean isGap);
    void setSpy(boolean spy);
}
