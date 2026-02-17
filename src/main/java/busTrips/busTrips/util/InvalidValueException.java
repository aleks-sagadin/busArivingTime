package busTrips.busTrips.util;

public class InvalidValueException extends RuntimeException {
    public InvalidValueException(String property, String value) {
        super(String.format("Invalid value of '%s' (%s)", property, value));
    }

    public InvalidValueException(String message) {
        super(message);
    }
}
