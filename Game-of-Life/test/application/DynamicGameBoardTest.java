package application;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Programutvikling school project for HiOA
 *
 * @author Tetiana Bushuieva
 * 
 * Class name: DynamicGameBoardTest. Descrption: This class contains a setup. In
 * the setup method its created a board that we use for checking the other
 * method and make sure that they are implemented correct. The set the size of
 * board to 4x4.
 */
public class DynamicGameBoardTest {

    DynamicGameBoard board;
    GameOfLife gameOfLife;
    Cell cell;

    public DynamicGameBoardTest() {
    }

    @Before
    public void setUp() {
        board = new DynamicGameBoard(4, 4);
        board.setState(true, 0, 1);
        board.setState(true, 1, 2);
        board.setState(true, 2, 0);
        board.setState(true, 2, 1);
        board.setState(true, 2, 2);

    }

    /**
     * Test of getLength method, of class DynamicGameBoard.
     */
    @Test
    public void testGetLength() {
        int expectedLength = 4;
        assertEquals(expectedLength, board.getLength());
    }

    /**
     * Test of getWidth method, of class DynamicGameBoard.
     */
    @Test
    public void testGetWidth() {
        int expectedWidth = 4;
        assertEquals(expectedWidth, board.getLength());
    }

    /**
     * Test of setLength method, of class DynamicGameBoard.
     */
    @Test
    public void testSetLength() {
        int expectedWidth = 7;
        board.setLength(7);
        assertEquals(expectedWidth, board.getLength());
    }

    /**
     * Test of setWidth method, of class DynamicGameBoard.
     */
    @Test
    public void testSetWidth() {
        int expectedWidth = 7;
        board.setWidth(7);
        assertEquals(expectedWidth, board.getWidth());
    }

    /**
     * Test of getNewState method, of class DynamicGameBoard.
     */
    @Test
    public void testGetNewState() {
        boolean expectedForPos00 = false;
        boolean expectedForPos22 = true;
        boolean expectedForPos31 = true;
        boolean actualForPos00 = board.getNewState(board.getIsAlive(0, 0), 0, 0);
        boolean actualForPos22 = board.getNewState(board.getIsAlive(2, 2), 2, 2);
        boolean actualForPos31 = board.getNewState(board.getIsAlive(3, 1), 3, 1);
        assertEquals(expectedForPos00, actualForPos00);
        assertEquals(expectedForPos22, actualForPos22);
        assertEquals(expectedForPos31, actualForPos31);
    }

    /**
     * Test of getLiveNeighbours method, of class DynamicGameBoard.
     */
    @Test
    public void testGetLiveNeighbours() {
        int expectedForPos11 = 5;
        int actualForPos11 = board.getLiveNeighbours(1, 1);
        assertEquals(expectedForPos11, actualForPos11);
    }

    /**
     * Test of alive method, of class DynamicGameBoard.
     */
    @Test
    public void testAlive() {
        boolean expectedBoolean = true;
        boolean expectedBoolean1 = false;
        boolean alive = board.alive(2);
        boolean alive1 = board.alive(5);
        assertEquals(expectedBoolean, alive);
        assertEquals(expectedBoolean1, alive1);
    }

    /**
     * Test of die method, of class DynamicGameBoard.
     */
    @Test
    public void testDie() {
        boolean expectedBoolean = true;
        boolean expectedBoolean1 = false;
        boolean die = board.die(3);
        boolean die1 = board.die(2);
        assertEquals(expectedBoolean, die);
        assertEquals(expectedBoolean1, die1);
    }

    /**
     * Test of getBoard method, of class DynamicGameBoard.
     */
    @Test
    public void testGetBoard() {
        List<List<Cell>> expectedBoard = new ArrayList<>();
        for (int i = 0; i < board.getLength(); i++) {
            expectedBoard.add(new ArrayList<>());
            for (int j = 0; j < board.getWidth(); j++) {
                expectedBoard.get(i).add(new Cell());
            }
        }
        expectedBoard.get(0).get(1).setState(true);
        expectedBoard.get(1).get(2).setState(true);
        expectedBoard.get(2).get(0).setState(true);
        expectedBoard.get(2).get(1).setState(true);
        expectedBoard.get(2).get(2).setState(true);

        for (int i = 0; i < expectedBoard.size(); i++) {
            for (int j = 0; j < expectedBoard.get(0).size(); j++) {
                assertEquals(expectedBoard.get(i).get(j).getIsAlive(), board.getIsAlive(i, j));
            }
        }
    }

    /**
     * Test of setBoard method, of class DynamicGameBoard.
     */
    @Test
    public void testSetBoard() {
        List<List<Cell>> expectedSetBoard = new ArrayList<>();
        for (int i = 0; i < board.getLength(); i++) {
            expectedSetBoard.add(new ArrayList<>());
            for (int j = 0; j < board.getWidth(); j++) {
                expectedSetBoard.get(i).add(new Cell());
            }
        }
        expectedSetBoard.get(1).get(0).setState(true);
        expectedSetBoard.get(2).get(1).setState(true);
        expectedSetBoard.get(3).get(1).setState(true);
        expectedSetBoard.get(1).get(2).setState(true);
        expectedSetBoard.get(2).get(2).setState(true);

        board.setBoard(expectedSetBoard);

        for (int i = 0; i < expectedSetBoard.size(); i++) {
            for (int j = 0; j < expectedSetBoard.get(0).size(); j++) {
                assertEquals(expectedSetBoard.get(i).get(j).getIsAlive(), board.getIsAlive(i, j));
            }
        }

    }

    /**
     * Test of resetBoard method, of class DynamicGameBoard.
     */
    @Test
    public void testResetBoard() {
        List<List<Cell>> expectedResetBoard = new ArrayList<>();
        for (int i = 0; i < board.getLength(); i++) {
            expectedResetBoard.add(new ArrayList<>());
            for (int j = 0; j < board.getWidth(); j++) {
                expectedResetBoard.get(i).add(new Cell());
            }
        }

        board.resetBoard();
        for (int i = 0; i < expectedResetBoard.size(); i++) {
            for (int j = 0; j < expectedResetBoard.get(0).size(); j++) {
                assertEquals(expectedResetBoard.get(i).get(j).getIsAlive(), board.getIsAlive(i, j));
            }
        }
    }

    /**
     * Test of getIsAlive method, of class DynamicGameBoard.
     */
    @Test
    public void testGetIsAlive() {
        boolean expectedState = true;
        board.getIsAlive(0, 1);
        assertEquals(expectedState, board.getIsAlive(0, 1));
    }

    /**
     * Test of setState method, of class DynamicGameBoard.
     */
    @Test
    public void testSetState() {
        boolean expectedState = true;
        board.setState(true, 1, 1);
        board.getIsAlive(1, 1);
        assertEquals(expectedState, board.getIsAlive(1, 1));
    }

    /**
     * Test of checkEdges method, of class DynamicGameBoard.
     */
    @Test
    public void testCheckEdges() {

        int expectedSize = 6;
        board.checkEdges();

        assertEquals(expectedSize, board.getBoard().size());

    }
}
