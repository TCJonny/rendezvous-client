package jw.app.rendezvousclient;

/**
 *  Generic exception use to implement the
 *  bounded buffer representing the user's
 *  pages.
 */
public class InvalidPageCountException extends Exception {

    public InvalidPageCountException() {}

    public InvalidPageCountException(String message) {
        super(message);
    }

    public InvalidPageCountException(Throwable cause) {
        super(cause);
    }

    public InvalidPageCountException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
