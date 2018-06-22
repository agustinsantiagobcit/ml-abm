package cl.metlife.abm.business.execution.exception;

public class FileTypeException extends Exception {

    public FileTypeException(String message) {
        super(message);
    }

    public FileTypeException(String message, Throwable cause) {
        super(message, cause);
    }

}
