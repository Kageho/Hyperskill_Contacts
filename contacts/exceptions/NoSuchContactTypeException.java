package contacts.exceptions;

public class NoSuchContactTypeException extends Exception {
    private static final String MESSAGE = "There is no such type of contact!";

    public NoSuchContactTypeException(String message) {
        super(message);
    }

    public NoSuchContactTypeException() {
        this(MESSAGE);
    }
}
