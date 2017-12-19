package application;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;

/**
 * Programutvikling school project for HiOA
 *
 * @author Tetiana Bushuieva
 * 
 * Classname: GameOfLife. Description: Creates a new generation of cells to
 * "animate" the game both with and without threads. Contains elements to
 * control the animation the game. In this class there is a possibility to
 * choose which method to create a next generation of cells can be used:
 * getNextGen() or nextGenerationConcurrentPrintPerformance().
 */
public class GameOfLife {

    private Board gameBoard;
    private Timeline timeline;
    private Duration duration;
    static List<Thread> workers = new ArrayList<Thread>();

    /**
     * Constructor for GameOflife. Receive two parameters board and drawClass,
     * parameter board is used to create the new generation. The parameter board
     * also allows us to use the methods of Board class. Parameter drawClass is
     * used to draw the board. Method creates Timeline with game of life logic.
     * It handles UnsupportedBoardException and InterruptedException
     *
     * @param board - as the currant board.
     * @param drawClass - as the canvas .
     *
     */
    public GameOfLife(Board board, Draw drawClass) {

        duration = Duration.millis(150);

        this.gameBoard = board;
        timeline = new Timeline(new KeyFrame(duration, e -> {

            Board newBoard;
            try {
                long start = System.currentTimeMillis();

                newBoard = nextGenerationConcurrentPrintPerformance(board);
                board.setBoard(newBoard.getBoard());
                drawClass.draw(board);

                long elapsed = System.currentTimeMillis() - start;
                System.out.println("Counting time (ms): " + elapsed);

            } catch (UnsupportedBoardException ex) {
                timeline.stop();

                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setContentText(ex.getMessage());
                alert.showAndWait();

            } catch (InterruptedException ex) {
                timeline.stop();
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);

    }

    /**
     * Gets the value of Timeline,
     *
     * @return timeline - the value to start and stop the animation.
     */
    public Timeline getTimeline() {
        return timeline;
    }

    /**
     * Stops the animation.
     */
    public void stop() {
        timeline.stop();
    }

    /**
     * Resets the board by setting cells to false.
     */
    public void reset() {
        gameBoard.resetBoard();
    }

    /**
     * Creates the new generations of old board. The first ting the method does
     * is to check whether the oldBoard is dynamic or static. As long as the
     * oldBoard is dynamic or static it will not throw an
     * unsupportedBoardException. Then the method run a double nested for-loop
     * where it evaluates the state of the cells and its neighbours in order to
     * get the new living cells. It also creates a new board and sets new states
     * on the new board.
     *
     * @param oldBoard - current board.
     * @return nextBoard - new board with the new genereation.
     * @throws UnsupportedBoardException - exception for unsupported board.
     */
    public Board getNextGen(Board oldBoard) throws UnsupportedBoardException {
        if (oldBoard instanceof DynamicGameBoard) {
            if (oldBoard.getLength() < 500 && oldBoard.getWidth() < 500) {
                ((DynamicGameBoard) oldBoard).checkEdges();
            }
        }
        Board nextBoard;
        if (oldBoard instanceof GameBoard) {
            nextBoard = new GameBoard(oldBoard.getLength(), oldBoard.getWidth());
        } else if (oldBoard instanceof DynamicGameBoard) {
            nextBoard = new DynamicGameBoard(oldBoard.getLength(), oldBoard.getWidth());
        } else {
            throw new UnsupportedBoardException();
        }

        for (int x = 0; x < oldBoard.getLength(); x++) {
            for (int y = 0; y < oldBoard.getWidth(); y++) {
                boolean oldState = oldBoard.getIsAlive(x, y);
                boolean newState = oldBoard.getNewState(oldState, x, y);
                nextBoard.setState(newState, x, y);
            }
        }

        return nextBoard;
    }

    /**
     * Creates the new generations of old board. The first ting the method does
     * is to check whether the oldBoard is dynamic or static. As long as the
     * oldBoard is dynamic or static it will not throw an
     * unsupportedBoardException. Then the methods creates threads and runs
     * them. After they are finished, clears the threads and returns the new
     * board.
     *
     * @param oldBoard - current board.
     * @return nextBoard - new board with the new genereation.
     * @throws UnsupportedBoardException - exception for unsupported board.
     * @throws InterruptedException
     */
    private Board nextGenerationConcurrentPrintPerformance(Board oldBoard) throws UnsupportedBoardException, InterruptedException {
        if (oldBoard instanceof DynamicGameBoard) {
            if (oldBoard.getLength() < 600 && oldBoard.getWidth() < 600) {
                ((DynamicGameBoard) oldBoard).checkEdges();
            }
        }
        Board nextBoard;
        if (oldBoard instanceof GameBoard) {
            nextBoard = new GameBoard(oldBoard.getLength(), oldBoard.getWidth());
        } else if (oldBoard instanceof DynamicGameBoard) {
            nextBoard = new DynamicGameBoard(oldBoard.getLength(), oldBoard.getWidth());
        } else {
            throw new UnsupportedBoardException();
        }
        createWorkers(oldBoard, nextBoard);
        runWorkers();
        workers.clear();
        return nextBoard;
    }

    /**
     * This method creates four threads. Each thread calls threadLoop method
     * with different values. These values divides the board into four equal
     * parts and determine which part of board each thread will process.
     *
     * @param oldBoard - current board.
     * @param nextBoard - new board
     */
    private void createWorkers(Board oldBoard, Board nextBoard) {
        workers.add(new Thread(() -> {
            threadLoop(oldBoard, nextBoard, 0, oldBoard.getLength() / 4);
        }));
        workers.add(new Thread(() -> {
            threadLoop(oldBoard, nextBoard, oldBoard.getLength() / 4, oldBoard.getLength() / 2);
        }));
        workers.add(new Thread(() -> {
            threadLoop(oldBoard, nextBoard, oldBoard.getLength() / 2, oldBoard.getLength() * 3 / 4);
        }));
        workers.add(new Thread(() -> {
            threadLoop(oldBoard, nextBoard, oldBoard.getLength() * 3 / 4, oldBoard.getLength());
        }));

    }

    /**
     * This method runs all the created threads and waits until all the are
     * finished running
     *
     * @throws InterruptedException
     */
    private void runWorkers() throws InterruptedException {
        for (Thread t : workers) {
            t.start();
        }
        for (Thread t : workers) {
            t.join();
        }
    }

    /**
     * This method creates a double nestded loop and calculates the new state of
     * all the cells and sets them on the new board. Start and end position on
     * the board is determined by the parameters.
     *
     * @param oldBoard - the old board.
     * @param nextBoard - the new board.
     * @param startX - the start X position on board.
     * @param endX - the ending X position on the board.
     */
    private void threadLoop(Board oldBoard, Board nextBoard, int startX, int endX) {
        for (int x = startX; x < endX; x++) {
            for (int y = 0; y < oldBoard.getWidth(); y++) {
                boolean oldState = oldBoard.getIsAlive(x, y);
                boolean newState = oldBoard.getNewState(oldState, x, y);
                nextBoard.setState(newState, x, y); // oppdaterer cell på next board       
            }
        }
    }
}
