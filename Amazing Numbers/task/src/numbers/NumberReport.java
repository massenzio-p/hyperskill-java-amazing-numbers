package numbers;

public interface NumberReport {
    long getNumber();
    boolean isEven();
    boolean isBuzz();
    boolean isDuck();
    boolean isPalindromic();
    boolean isGap();
    boolean isSpy();
    boolean isSquare();
    boolean isSunny();
    boolean isJumping();
    boolean isHappy();
    void setEven(boolean isEven);
    void setBuzz(boolean isBuzz);
    void setDuck(boolean isDuck);
    void setPalindromic(boolean isPalindromic);
    void setGap(boolean isGap);
    void setSpy(boolean spy);
    void setSunny(boolean isSunny);
    void setSquare(boolean isSquare);
    void setJumping(boolean isJumping);
    void setHappy(boolean property);
}
