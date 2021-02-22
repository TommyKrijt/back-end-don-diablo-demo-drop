package nl.novi.krijt.exception;

public class FileStorageException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String msg;

    public FileStorageException(String msg, Exception exception) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}

