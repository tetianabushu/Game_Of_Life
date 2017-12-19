package application;

import java.io.File;
import java.io.FileNotFoundException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Programutvikling school project for HiOA
 *
 * @author Tetiana Bushuieva
 * 
 * Class name:GoLFileReaderTest. Descrption: This class contains a setup. In the
 * setup method its created a board that we use for checking the other method
 * and make sure that they are implemented correct. The set the size of board to
 * 4x4.
 */
public class GoLFileReaderTest {

    File file;
    GoLFileReader GoLReader = new GoLFileReader();

    public GoLFileReaderTest() {

    }

    @Before
    public void setUp() throws FileNotFoundException {
        file = new File("Glider.rle");
    }

    /**
     * Test of readGameBoardFromFile method, of class GoLFileReader.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testReadGameBoardFromFile() throws Exception {
        Cell[][] expectedBoard = {{new Cell(), new Cell(), new Cell()},
        {new Cell(), new Cell(), new Cell()},
        {new Cell(), new Cell(), new Cell()}};
        expectedBoard[1][0].setState(true);
        expectedBoard[2][1].setState(true);
        expectedBoard[0][2].setState(true);
        expectedBoard[1][2].setState(true);
        expectedBoard[2][2].setState(true);

        Board newBoard = GoLReader.readGameBoardFromFile(file);

        for (int i = 0; i < expectedBoard.length; i++) {
            for (int j = 0; j < expectedBoard[0].length; j++) {
                assertEquals(expectedBoard[i][j].getIsAlive(), newBoard.getIsAlive(i, j));
            }
        }
    }

}
