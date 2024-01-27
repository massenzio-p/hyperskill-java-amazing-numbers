package numbers.exception;

public class InvalidInputException extends Exception {
    public InvalidInputException(String inputMustExist) {
        super(inputMustExist);
    }
}
