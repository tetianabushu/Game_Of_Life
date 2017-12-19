package application;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Programutvikling school project for HiOA
 *
 * @author Tetiana Bushuieva
 * 
 * Class name:GameBoardTest. Descrption: This class contains a setup. In the
 * setup method its created a board that we use for checking the other method
 * and make sure that they are implemented correct. The set the size of board to
 * 4x4.
 */
public class GameBoardTest {

    GameBoard gameBoard;

    Cell cell;

    public GameBoardTest() {
    }

    @Before
    public void setUp() {
        gameBoard = new GameBoard(4, 4);
        gameBoard.setState(true, 0, 1);
        gameBoard.setState(true, 1, 2);
        gameBoard.setState(true, 2, 0);
        gameBoard.setState(true, 2, 1);
        gameBoard.setState(true, 2, 2);
    }

    /**
     * Test of getLength method, of class GameBoard.
     */
    @Test
    public void testGetLength() {
        int expectedLength = 4;
        assertEquals(expectedLength, gameBoard.getLength());
    }

    /**
     * Test of getWidth method, of class GameBoard.
     */
    @Test
    public void testGetWidth() {
        int expectedWidth = 4;
        assertEquals(expectedWidth, gameBoard.getLength());
    }

    /**
     * Test of setLength method, of class GameBoard.
     */
    @Test
    public void testSetLength() {
        int expectedWidth = 7;
        gameBoard.setLength(7);
        assertEquals(expectedWidth, gameBoard.getLength());
    }

    /**
     * Test of setWidth method, of class GameBoard.
     */
    @Test
    public void testSetWidth() {
        int expectedWidth = 7;
        gameBoard.setWidth(7);
        assertEquals(expectedWidth, gameBoard.getWidth());
    }

    /**
     * Test of getNewState method, of class GameBoard.
     */
    @Test
    public void testGetNewState() {
        boolean expectedForPos00 = false;
        boolean expectedForPos22 = true;
        boolean expectedForPos31 = true;
        boolean actualForPos00 = gameBoard.getNewState(gameBoard.getIsAlive(0, 0), 0, 0);
        boolean actualForPos22 = gameBoard.getNewState(gameBoard.getIsAlive(2, 2), 2, 2);
        boolean actualForPos31 = gameBoard.getNewState(gameBoard.getIsAlive(3, 1), 3, 1);
        assertEquals(expectedForPos00, actualForPos00);
        assertEquals(expectedForPos22, actualForPos22);
        assertEquals(expectedForPos31, actualForPos31);
    }

    /**
     * Test of getLiveNeighbours method, of class GameBoard.
     */
    @Test
    public void testGetLiveNeighbours() {
        int expectedForPos11 = 5;
        int actualForPos11 = gameBoard.getLiveNeighbours(1, 1);
        assertEquals(expectedForPos11, actualForPos11);

    }

    /**
     * Test of alive method, of class GameBoard.
     */
    @Test
    public void testAlive() {
        boolean expectedBoolean = true;
        boolean expectedBoolean1 = false;
        boolean alive = gameBoard.alive(2);
        boolean alive1 = gameBoard.alive(5);
        assertEquals(expectedBoolean, alive);
        assertEquals(expectedBoolean1, alive1);
    }

    /**
     * Test of die method, of class GameBoard.
     */
    @Test
    public void testDie() {

        boolean expectedBoolean = true;
        boolean expectedBoolean1 = false;
        boolean die = gameBoard.die(3);
        boolean die1 = gameBoard.die(2);
        assertEquals(expectedBoolean, die);
        assertEquals(expectedBoolean1, die1);

    }

    @Test
    public void testGetBoard() {
        Cell[][] expectedGetBoard = {{new Cell(), new Cell(), new Cell(), new Cell()},
        {new Cell(), new Cell(), new Cell(), new Cell()},
        {new Cell(), new Cell(), new Cell(), new Cell()},
        {new Cell(), new Cell(), new Cell(), new Cell()}};
        expectedGetBoard[0][1].setState(true);
        expectedGetBoard[1][2].setState(true);
        expectedGetBoard[2][0].setState(true);
        expectedGetBoard[2][1].setState(true);
        expectedGetBoard[2][2].setState(true);

        for (int i = 0; i < expectedGetBoard.length; i++) {
            for (int j = 0; j < expectedGetBoard[0].length; j++) {
                assertEquals(expectedGetBoard[i][j].getIsAlive(), gameBoard.getIsAlive(i, j));
            }
        }

    }

    @Test
    public void testSetBoard() {
        Cell[][] expectedSetBoard = {{new Cell(), new Cell(), new Cell(), new Cell()},
        {new Cell(), new Cell(), new Cell(), new Cell()},
        {new Cell(), new Cell(), new Cell(), new Cell()},
        {new Cell(), new Cell(), new Cell(), new Cell()}};
        expectedSetBoard[1][0].setState(true);
        expectedSetBoard[2][1].setState(true);
        expectedSetBoard[3][1].setState(true);
        expectedSetBoard[1][2].setState(true);
        expectedSetBoard[2][2].setState(true);

        gameBoard.setBoard(expectedSetBoard);

        for (int i = 0; i < expectedSetBoard.length; i++) {
            for (int j = 0; j < expectedSetBoard[0].length; j++) {
                assertEquals(expectedSetBoard[i][j].getIsAlive(), gameBoard.getIsAlive(i, j));
            }
        }

    }

    /**
     * Test of resetBoard method, of class GameBoard.
     */
    @Test
    public void testresetBoard() {
        Cell[][] expectedFalseBoard = {{new Cell(), new Cell(), new Cell(), new Cell()},
        {new Cell(), new Cell(), new Cell(), new Cell()},
        {new Cell(), new Cell(), new Cell(), new Cell()},
        {new Cell(), new Cell(), new Cell(), new Cell()}};
        gameBoard.resetBoard();
        for (int i = 0; i < expectedFalseBoard.length; i++) {
            for (int j = 0; j < expectedFalseBoard[0].length; j++) {
                assertEquals(expectedFalseBoard[i][j].getIsAlive(), gameBoard.getIsAlive(i, j));
            }
        }

    }

    /**
     * Test of getIsAlive method, of class GameBoard.
     */
    @Test
    public void testGetIsAlive() {
        boolean expectedState = true;
        boolean expectedState1 = false;
        gameBoard.getIsAlive(0, 1);
        gameBoard.getIsAlive(1, 1);
        assertEquals(expectedState, gameBoard.getIsAlive(0, 1));
        assertEquals(expectedState1, gameBoard.getIsAlive(1, 1));

    }

    /**
     * Test of setState method, of class GameBoard.
     */
    @Test
    public void testSetState() {
        boolean expectedState = true;
        gameBoard.setState(true, 1, 1);
        gameBoard.setState(true, 2, 3);
        gameBoard.getIsAlive(1, 1);
        gameBoard.getIsAlive(2, 3);
        assertEquals(expectedState, gameBoard.getIsAlive(1, 1));
        assertEquals(expectedState, gameBoard.getIsAlive(2, 3));
    }

}
