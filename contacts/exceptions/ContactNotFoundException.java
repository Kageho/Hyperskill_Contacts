package contacts.exceptions;

public class ContactNotFoundException extends Exception {
    private static final String MESSAGE = "There is no such contact!";

    public ContactNotFoundException(String message) {
        super(message);
    }

    public ContactNotFoundException() {
        this(MESSAGE);
    }
}
