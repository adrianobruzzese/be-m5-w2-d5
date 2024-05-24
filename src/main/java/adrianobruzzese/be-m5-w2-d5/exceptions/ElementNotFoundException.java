package adrianobruzzese.be-m5-w2-d5.exceptions;

public class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException(long id) {
        super("Element with id " + id + " not found!");
    }
}
