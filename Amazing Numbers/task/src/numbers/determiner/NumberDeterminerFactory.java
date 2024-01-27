package numbers.determiner;


import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class NumberDeterminerFactory {

    public static Collection<NumbersDeterminer> getAllTypes() {
        return Arrays.stream(DeterminerType.values())
                .map(NumberDeterminerFactory::createNumberDeterminer)
                .collect(Collectors.toSet());
    }

    public enum DeterminerType {
        BUZZ, DUCK, PALINDROMIC, GAP, EVEN, SPY, SUNNY, SQUARE
    }

    public static NumbersDeterminer createNumberDeterminer(DeterminerType type) {
        return switch (type) {
            case BUZZ -> new BuzzNumberDeterminer();
            case DUCK -> new DuckNumberDeterminer();
            case PALINDROMIC -> new PalindromicNumberDeterminer();
            case GAP -> new GapNumberDeterminer();
            case EVEN -> new EvenDeterminer();
            case SPY -> new SpyDeterminer();
            case SQUARE -> new SquareDeterminer();
            case SUNNY -> new SunnyDeterminer(createNumberDeterminer(DeterminerType.SQUARE));
        };
    }
}
