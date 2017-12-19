package application;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Programutvikling school project for HiOA
 * 
 * @author Tetiana Bushuieva

 * Class name:PatternFormatExceptionTest. Description: This class test whether
 * the exception message that are recieved its the one that are sent out.
 */
public class PatternFormatExceptionTest {

    /**
     * Test of getMessage method, of class PatternFormatException.
     */
    @Test
    public void testGetMessage() {
        System.out.println("getMessage");
        PatternFormatException instance = new PatternFormatException("Test message");
        String expResult = "Pattern Format Exception in chosen file.\nTest message";
        String result = instance.getMessage();
        assertEquals(expResult, result);

    }

}
