package application;

/**
 * Programutvikling school project for HiOA
 *
 * @author Tetiana Bushuieva
 * 
 * Classname: Rules. Description: This class contains information about the
 * rules. This class is an interface, the Rules class is implemented in
 * GameBoard and DynamicGameBoard.
 */
public interface Rules {

    /**
     * Returns the boolean true when int is between 1 and 4.
     *
     * @param a - amount of living neighbours.
     * @return true as the state of cell.
     */
    public boolean alive(int a);

    /**
     * Returns the boolean false as long a = 3.
     *
     * @param a - amount of dead neightbours.
     * @return false as the state of cell..
     */
    public boolean die(int a);

}
