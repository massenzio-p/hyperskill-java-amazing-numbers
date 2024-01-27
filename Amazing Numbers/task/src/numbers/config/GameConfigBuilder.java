package numbers.config;

import numbers.exception.InvalidInputException;

public interface GameConfigBuilder {
    GameConfig buildConfig(String[] input) throws InvalidInputException;
}
