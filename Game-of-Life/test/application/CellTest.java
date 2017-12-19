package application;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Programutvikling school project for HiOA
 *
 * @author Tetiana Bushuieva
 * 
 * Class name: CellTest. Descrption: This class contains a setup. In the setup
 * method its created a board that we use for checking the other method and make
 * sure that they are implemented correct. The set the size of board to 4x4.
 */
public class CellTest {

    GameBoard gameBoard;
    Cell cell;
    double cellSize = 4.0;

    public CellTest() {
    }

    @Before
    public void setUp() {
        cell = new Cell();

        gameBoard = new GameBoard(4, 4);
        gameBoard.getBoard()[0][1].setState(true);
        gameBoard.getBoard()[1][2].setState(true);
        gameBoard.getBoard()[2][0].setState(true);
        gameBoard.getBoard()[2][1].setState(true);
        gameBoard.getBoard()[2][2].setState(true);
    }

    /**
     * Test of getIsAlive method, of class Cell.
     */
    @Test
    public void testGetIsAlive() {
        boolean expectedBoolean = false;
        assertEquals(expectedBoolean, cell.getIsAlive());

    }

    /**
     * Test of setState method, of class Cell.
     */
    @Test
    public void testSetState() {
        boolean expectedBoolean = true;
        cell.setState(true);
        assertEquals(expectedBoolean, cell.getIsAlive());

    }

}
