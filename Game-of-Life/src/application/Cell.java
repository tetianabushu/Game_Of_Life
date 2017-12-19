package application;

/**
 * Programutvikling school project for HiOA
 *
 * @author Tetiana Bushuieva
 * 
 * Classname: Cell. Description: This class implements methods for Cell. This
 * class represents object our board is built of.
 */
public class Cell {

    private boolean isAlive;

    /**
     * Constructor for Cell.
     */
    public Cell() {
        isAlive = false;
    }

    /**
     * Gets the state of cell (true or false)
     *
     * @return isAlive - value of cell
     */
    public boolean getIsAlive() {
        return isAlive;
    }

    /**
     * Sets the state of cell (true or false)
     *
     * @param state - value of a cell (true or false)
     */
    public void setState(boolean state) {
        isAlive = state;
    }
}
