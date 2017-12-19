package application;

import java.util.ArrayList;
import java.util.List;

/**
 * Programutvikling school project for HiOA
 *
 * @author Tetiana Bushuieva
 * 
 * Class name: DynamicGameBoard. Description: This class implements methods for
 * DynamicGameBoard. Class DynamicGameBoard extends Board abstract class and
 * implements Rules interface.
 */
public class DynamicGameBoard extends Board implements Rules {

    List<List<Cell>> board;
    private int length;
    private int width;

    /**
     * Gets the length of board.
     *
     * @return length - length of board.
     */
    @Override
    public int getLength() {
        return length;
    }

    /**
     * Gets the width of board.
     *
     * @return width - width of board.
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * Sets a new length of board.
     *
     * @param length - length of board.
     */
    @Override
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Sets a new width of board.
     *
     * @param width - width of board.
     */
    @Override
    public void setWidth(int width) {
        this.width = width;

    }

    /**
     * Constructor for DynamicGameBoard. Takes in two parameters x and y and
     * creates the board with false cells. create a board of a new Arraylist,
     * sets empty cells to false into board.
     *
     * @param x - number of rows.
     * @param y - number of columns.
     */
    public DynamicGameBoard(int x, int y) {
        length = x;
        width = y;

        board = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            board.add(new ArrayList<>());
            for (int j = 0; j < width; j++) {
                board.get(i).add(new Cell());
            }

        }
    }

    /**
     * Checks for living neighbours and returns the new state of cells.
     *
     * @param currentState - current state of cell (alive or die).
     * @param x - x- coordinate of the cell.
     * @param y - y- coordinate of the cell.
     * @return alive or die as the new state of cells.
     */
    @Override
    public boolean getNewState(boolean currentState, int x, int y) {
        int a;
        //Flytter if statements som sjekker naboer til egen metode getLiveNeighbours()      
        a = getLiveNeighbours(x, y);

        if (currentState) {
            return alive(a);
        } else {
            return die(a);
        }
    }

    /**
     * Checks for living neighbours. This method is done by if- statements to
     * check the 8 neighbours for a cell. The method counts the number of living
     * neighbours according to a position on the board.
     *
     * @param x - x-coordinate of cell.
     * @param y - y-coordinate of cell.
     * @return a - the amount of living neighbours.
     */
    @Override
    public int getLiveNeighbours(int x, int y) {
        int a = 0;
        if ((x - 1) >= 0 && (y + 1) < width && board.get(x - 1).get(y + 1).getIsAlive()) {
            a++;
        }
        if ((y + 1) < width && board.get(x).get(y + 1).getIsAlive()) {
            a++;
        }
        if ((x + 1) < length && (y + 1) < width && board.get(x + 1).get(y + 1).getIsAlive()) {
            a++;
        }
        if ((x - 1) >= 0 && board.get(x - 1).get(y).getIsAlive()) {
            a++;
        }
        if ((x + 1) < length && board.get(x + 1).get(y).getIsAlive()) {
            a++;
        }
        if ((x - 1) >= 0 && (y - 1) >= 0 && board.get(x - 1).get(y - 1).getIsAlive()) {
            a++;
        }
        if ((y - 1) >= 0 && board.get(x).get(y - 1).getIsAlive()) {
            a++;
        }
        if ((x + 1) < length && (y - 1) >= 0 && board.get(x + 1).get(y - 1).getIsAlive()) {
            a++;
        }
        return a;
    }

    /**
     * Returns the boolean true when int is between 1 and 4.
     *
     * @param a - amount of living neighbours.
     * @return true as the state of cell.
     */
    @Override
    public boolean alive(int a) {
        return a > 1 && a < 4;
    }

    /**
     * Returns the boolean true as long as a = 3.
     *
     * @param a - amount of living neightbours.
     * @return false as the state of cell.
     */
    @Override
    public boolean die(int a) {
        return a == 3;
    }

    /**
     * Gets the current board.
     *
     * @return board - current board.
     */
    @Override
    public List<List<Cell>> getBoard() {
        return board;
    }

    /**
     * Sets a board as current board and updates length and width of the board.
     *
     * @param <T> - describes type parameter.
     * @param board - the new board current board.
     */
    @Override
    public <T> void setBoard(T board) {
        this.board = (List<List<Cell>>) board;
        length = this.board.size();
        width = this.board.get(0).size();
    }

    /**
     * Resets the board by setting all cells to false.
     */
    @Override
    public void resetBoard() {
        board.stream().forEach((cellList) -> {
            cellList.stream().forEach((cell) -> {
                cell.setState(false);
            });
        });

    }

    /**
     * Get state of cell (true or false) from a given coordinate.
     *
     * @param x - x-coordinate of the cell.
     * @param y - x-coordinate of the cell.
     * @return the state(true or false) of the coordinate.
     */
    @Override
    public boolean getIsAlive(int x, int y) {
        return board.get(x).get(y).getIsAlive();
    }

    /**
     * Adds rows to the board when the cells is on the edge.
     */
    private void addRows() {
        int size = length;
        board.add(0, new ArrayList<>());
        board.add(new ArrayList<>());
        for (int i = 0; i < width; i++) {
            board.get(0).add(new Cell());
            board.get(size + 1).add(new Cell());
        }
        length = board.size();
    }

    /**
     * Adds columns to the board when the cells is on the edge.
     */
    private void addColumns() {

        for (int i = 0; i < length; i++) {
            board.get(i).add(0, new Cell());
            board.get(i).add(new Cell());
        }
        width = board.get(0).size();
    }

    /**
     * Checks if there are living cells along the edges if the given statement
     * is true then it adds rows and columns.
     */
    public void checkEdges() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {

                if ((i == 0 || j == 0 || j == width - 1 || i == length - 1) && getIsAlive(i, j)) {
                    addRows();
                    addColumns();
                }
            }
        }
    }

    /**
     * Set state of cell (true or false) on a given coordinate.
     *
     * @param state - state of cell.
     * @param x - x-coordinate of the cell.
     * @param y - y-coordinate of the cell.
     */
    @Override
    public void setState(boolean state, int x, int y) {
        board.get(x).get(y).setState(state);

    }
}
