package application;

/**
 * Programutvikling school project for HiOA
 *
 * @author Tetiana Bushuieva
 * 
 * Class name: Board. Description: This class contains definition of abstract methods
 * of Board. The methods are implemented in GameBoard and DynamicBoard. I chose
 * to implement a Board class as an abstract class because I needed to have an
 * access to all common methods of GameBoard and DynamicGameBoard(static and
 * dymanic boards).
 *
 */
public abstract class Board {

    /**
     * Gets the length of board.
     *
     * @return length - length of board.
     */
    public abstract int getLength();

    /**
     * Gets the width of board.
     *
     * @return width - width of board.
     */
    public abstract int getWidth();

    /**
     * Sets a new length of board.
     *
     * @param length - length of board.
     */
    public abstract void setLength(int length);

    /**
     * Sets a new width of board.
     *
     * @param width - width of board.
     */
    public abstract void setWidth(int width);

    /**
     * Checks for living neighbours and returns the new state of cells.
     *
     * @param currentState - current state of cell (alive or dead).
     * @param x - x- coordinate of the cell.
     * @param y - y- coordinate of the cell.
     * @return alive or dead as the new state of cells.
     */
    public abstract boolean getNewState(boolean currentState, int x, int y);

    /**
     * Gets the state of cell (true or false) from a given coordinate.
     *
     * @param x - x-coordinate of the cell.
     * @param y - x-coordinate of the cell.
     * @return the state(true or false) of the coordinate.
     */
    public abstract boolean getIsAlive(int x, int y);

    /**
     * Sets the state of cell (true or false) on a given coordinate.
     *
     * @param state - state of cell.
     * @param x - x-coordinate of the cell.
     * @param y - y-coordinate of the cell.
     */
    public abstract void setState(boolean state, int x, int y);

    /**
     * Checks for living neighbours. This method is done by if- statements too
     * check the 8 neighbours for a cell. The method counts the number of living
     * neighbours according to a position on the board. The
     * method-getLiveNeighbours is necessary for the animation to run.
     *
     * @param x - x-coordinate of cell.
     * @param y - y-coordinate of cell.
     * @return a - the amount of living neighbours.
     */
    public abstract int getLiveNeighbours(int x, int y);

    /**
     * Returns the boolean true when int is between 1 and 4.
     *
     * @param a - amount of living neighbours.
     * @return true as the state of cell.
     */
    public abstract boolean alive(int a);

    /**
     * Returns the boolean false as long a = 3.
     *
     * @param a - amount of living neightbours.
     * @return false as the state of cell.
     */
    public abstract boolean die(int a);

    /**
     * Gets the current board.
     *
     * @param <T> - describes type parameter.
     * @return board - current board.
     */
    public abstract <T> T getBoard();

    /**
     * Sets a board as current board.
     *
     * @param <T> - describes type parameter.
     * @param board - the new board current board.
     */
    public abstract <T> void setBoard(T board);

    /**
     * Reset board by setting all cells to false.
     */
    public abstract void resetBoard();
}
