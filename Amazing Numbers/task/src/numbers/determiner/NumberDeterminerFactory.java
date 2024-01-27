package numbers.determiner;


public class NumberDeterminerFactory {

    public enum DeterminerType {
        BUZZ, DUCK, PALINDROMIC, GAP, EVEN, SPY
    }

    public static NumbersDeterminer createNumberDeterminer(DeterminerType type) {
        return switch (type) {
            case BUZZ -> new BuzzNumberDeterminer();
            case DUCK -> new DuckNumberDeterminer();
            case PALINDROMIC -> new PalindromicNumberDeterminer();
            case GAP -> new GapNumberDeterminer();
            case EVEN -> new EvenDeterminer();
            case SPY -> new SpyDeterminer();
        };
    }
}
