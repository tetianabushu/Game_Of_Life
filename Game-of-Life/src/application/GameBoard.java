package application;

/**
 * Programutvikling school project for HiOA
 *
 * @author Tetiana Bushuieva
 * 
 * Class name: GameBoard. Description: This class implements methods for board.
 * Class DynamicGameBoard extends Board abstract class and implements Rules
 * interface.
 */
@Deprecated
public class GameBoard extends Board implements Rules {

    private Cell[][] board;
    private int length;
    private int width;

    /**
     * Gets the length value of board.
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
     * Sets the length of board..
     *
     * @param length - length of board.
     */
    @Override
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Sets the width of board.
     *
     * @param width - width of board.
     */
    @Override
    public void setWidth(int width) {
        this.width = width;

    }

    /**
     * Constructor for GameBoard. Takes in two parameters x and y and creates
     * the board with false cells. Sets x to equal lenght and y to be equal
     * width. Create a board of a new cellobject, sets empty new cellobject
     * values into board.
     *
     * @param x - number of rows.
     * @param y - number of columns.
     */
    public GameBoard(int x, int y) {
        length = x;
        width = y;

        board = new Cell[x][y];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = new Cell();
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
     * Get state of cell (true or false) from a given coordinate.
     *
     * @param x - x-coordinate of the cell.
     * @param y - x-coordinate of the cell.
     * @return the state(true or false) of the coordinate.
     */
    @Override
    public boolean getIsAlive(int x, int y) { // 
        return board[x][y].getIsAlive();

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
        board[x][y].setState(state);
    }

    /**
     * Checks for living neighbours. This method is done by if- statements too
     * check the 8 neighbours for a cell. The method counts the number of living
     * neighbours according to a position on the board. The
     * method-getLiveNeighbours is necessary for the simulation to run.
     *
     * @param x - x-coordinate of cell.
     * @param y - y-coordinate of cell.
     * @return a - the amount of living neighbours.
     */
    @Override
    public int getLiveNeighbours(int x, int y) {
        int a = 0;
        if ((x - 1) >= 0 && (y + 1) < width && board[x - 1][y + 1].getIsAlive()) {
            a++;
        }
        if ((y + 1) < width && board[x][y + 1].getIsAlive()) {
            a++;
        }
        if ((x + 1) < length && (y + 1) < width && board[x + 1][y + 1].getIsAlive()) {
            a++;
        }
        if ((x - 1) >= 0 && board[x - 1][y].getIsAlive()) {
            a++;
        }
        if ((x + 1) < length && board[x + 1][y].getIsAlive()) {
            a++;
        }
        if ((x - 1) >= 0 && (y - 1) >= 0 && board[x - 1][y - 1].getIsAlive()) {
            a++;
        }
        if ((y - 1) >= 0 && board[x][y - 1].getIsAlive()) {
            a++;
        }
        if ((x + 1) < length && (y - 1) >= 0 && board[x + 1][y - 1].getIsAlive()) {
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
     * Returns the boolean false as long a = 3.
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
    public Cell[][] getBoard() {
        return board;
    }

    /**
     * Sets a new board as current board.
     *
     * @param <T> - describes type of the parameter.
     * @param newBoard - the new current board.
     */
    @Override
    public <T> void setBoard(T newBoard) {
        this.board = (Cell[][]) newBoard;

    }

    /**
     * Resets board by setting all cells to false.
     */
    @Override
    public void resetBoard() {
        for (Cell[] cellArray : board) {
            for (Cell cell : cellArray) {
                cell.setState(false);
            }
        }
    }

}
