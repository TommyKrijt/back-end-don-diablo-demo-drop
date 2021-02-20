package nl.novi.krijt.exception;

public class DatabaseErrorException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DatabaseErrorException() {
        super("Problem in database");
    }
}
