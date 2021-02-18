package nl.novi.krijt.execption;

public class DatabaseErrorException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DatabaseErrorException() {
        super("Problem in database");
    }
}
