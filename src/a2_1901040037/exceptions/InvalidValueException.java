package a2_1901040037.exceptions;

public class InvalidValueException extends RuntimeException{

    public InvalidValueException(String value, String type) {
        super("Invalid value: " + value + " of type " + type );
    }
}
