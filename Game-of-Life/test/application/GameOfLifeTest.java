package application;

import javafx.scene.canvas.Canvas;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Programutvikling school project for HiOA
 *
 * @author Tetiana Bushuieva
 * 
 * Class name:GameOfLifeTest. Descrption: This class contains a setup. In the
 * setup method its created a board that we use for checking the other method
 * and make sure that they are implemented correct. The set the size of board to
 * 4x4.
 */
public class GameOfLifeTest {

    DynamicGameBoard gameBoard;
    GameOfLife gameOfLife;

    public GameOfLifeTest() {
    }

    @Before
    public void setUp() {
        gameBoard = new DynamicGameBoard(4, 4);
        gameOfLife = new GameOfLife(gameBoard, new Draw(new Canvas().getGraphicsContext2D(), 10, 10));
        gameBoard.getBoard().get(0).get(1).setState(true);
        gameBoard.getBoard().get(1).get(2).setState(true);
        gameBoard.getBoard().get(2).get(0).setState(true);
        gameBoard.getBoard().get(2).get(1).setState(true);
        gameBoard.getBoard().get(2).get(2).setState(true);
    }

    /**
     * Test of getNextGen method, of class GameOfLife.
     *
     * @throws application.UnsupportedBoardException
     */
    @Test
    public void testGetNextGen() throws UnsupportedBoardException {
        DynamicGameBoard expectedBoard = new DynamicGameBoard(6, 6);

        expectedBoard.setState(true, 2, 1);
        expectedBoard.setState(true, 2, 3);
        expectedBoard.setState(true, 4, 2);
        expectedBoard.setState(true, 3, 3);
        expectedBoard.setState(true, 3, 2);

        Board newBoard = gameOfLife.getNextGen(gameBoard);
        gameBoard.setBoard(newBoard.getBoard());

        for (int i = 0; i < expectedBoard.getBoard().size(); i++) {
            for (int j = 0; j < expectedBoard.getBoard().get(0).size(); j++) {
                assertEquals(expectedBoard.getBoard().get(i).get(j).getIsAlive(), gameBoard.getBoard().get(i).get(j).getIsAlive());
            }
        }
    }

    /**
     * Test of nextGenerationConcurrentPrintPerformance method, of class
     * GameOfLife.
     */
    @Test
    public void testNextGenerationConcurrentPrintPerformance() throws Exception {
        DynamicGameBoard expectedBoard = new DynamicGameBoard(6, 6);

        expectedBoard.setState(true, 2, 1);
        expectedBoard.setState(true, 2, 3);
        expectedBoard.setState(true, 4, 2);
        expectedBoard.setState(true, 3, 3);
        expectedBoard.setState(true, 3, 2);

        Board newBoard = gameOfLife.getNextGen(gameBoard);
        gameBoard.setBoard(newBoard.getBoard());

        for (int i = 0; i < expectedBoard.getBoard().size(); i++) {
            for (int j = 0; j < expectedBoard.getBoard().get(0).size(); j++) {
                assertEquals(expectedBoard.getBoard().get(i).get(j).getIsAlive(), gameBoard.getBoard().get(i).get(j).getIsAlive());
            }
        }
    }

}
