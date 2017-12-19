package application;

/**
 * Programutvikling school project for HiOA
 *
 * @author Tetiana Bushuieva
 * 
 * Class name: UnsopportedBoardException. Description: This class contains a
 * constructors for unsupported boards. This class extends Exception class and
 * creates own message to Exception.

 */
public class UnsupportedBoardException extends Exception {

    /**
     * Constructor for UnsupportedBoardException without recieving any
     * parameters.
     *
     */
    public UnsupportedBoardException() {
        super();
    }

    /**
     * Constructor for UnsupportedBoardException with a detail message
     *
     * @param message - the detail message.
     */
    public UnsupportedBoardException(String message) {
        super(message);
    }

    /**
     * Constructor for UnsupportedBoardException with a detail message and a
     * cause.
     *
     * @param message - the detail message.
     * @param cause - the cause.
     */
    public UnsupportedBoardException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor for UnsupportedBoardException with a specified cause.
     *
     * @param cause - the cause.
     */
    public UnsupportedBoardException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor for UnsupportedBoardException with a message, cause,
     * enableSuppression and writable stack trace.
     *
     * @param message - the massage.
     * @param cause - the cause.
     * @param enableSuppression - the enableSuppression(suppression is enable or
     * disabled).
     * @param writableStackTrace - whether or not the stack trace should be
     * writable.
     */
    public UnsupportedBoardException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
