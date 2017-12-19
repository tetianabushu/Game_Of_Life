
package application;

/**
 * Programutvikling school project for HiOA
 *
 * @author Tetiana Bushuieva
 * 
 * Classname:PatternFormatException. Description: This class contains a
 * constructor and method for a invalid pattern in file. This class extends
 * Exception class and creates own message for the Exception.
 */
public class PatternFormatException extends Exception {

    /**
     * Constructor for PatternFormatExeption. Recevies an exception message.
     *
     * @param s - exception message.
     */

    public PatternFormatException(String s) {
        super(s);
    }

    /**
     * Gets the exception message.
     *
     * @return message - exception message
     */
    @Override
    public String getMessage() {
        String message = "Pattern Format Exception in chosen file.\n" + super.getMessage();
        return message;
    }
}
