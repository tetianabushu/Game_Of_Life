package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Programutvikling school project for HiOA
 *
 * @author Tetiana
 * 
 * Classname: Draw. Description: This class implements draw functionality and
 * methods to draw the board
 */
public class Draw {

    private GraphicsContext gsC;
    private double pixel;
    private Paint color;

    /**
     * Constructor for Draw.
     *
     * Creats an object and draws on a canvas. Defines pixel and sets the size
     * of each cell on the canvas
     *
     * @param graphicsContext - canvas graphicsContext
     * @param boardWidth - boardwidth to the array in the board class, is used
     * to define pixel size.
     * @param length - boardlength to the array in the board class, is used to
     * define pixel size.
     */
    public Draw(GraphicsContext graphicsContext, int boardWidth, int length) {
        int size;
        if (boardWidth >= length) {
            size = boardWidth;
        } else {
            size = length;
        }
        this.gsC = graphicsContext; //setter verdi av parameter til en variabel av den klasse
        pixel = (int) graphicsContext.getCanvas().getHeight() / size;
        /*pixel verdi må også kastes til int for at formel, som er brukt forMouseEvent fungerer på alle brett størrelser*/
        color = Color.valueOf("331a80");
    }

    /**
     * Updates pixel value. This method checks either length or width has larger
     * value, and takes the larger value to calculate size of pixel. In pixel
     * formula it is necessary to cast value to integer for the right
     * calculation. returns new value of pixel
     *
     * @param boardWidth - width of the board.
     * @param length - length of the board.
     * @return pixel - size of the cell.
     */
    public double updatePixelSize(int boardWidth, int length) {
        int size;
        if (boardWidth >= length) {
            size = boardWidth;
        } else {
            size = length;
        }
        pixel = (int) gsC.getCanvas().getHeight() / size;
        return pixel;

    }

    /**
     * Gets the pixel value
     *
     * @return pixel - size in pixel.
     */
    public double getPixel() {
        return pixel;
    }

    /**
     * Sets the value of pixel
     *
     * @param pixel - a double we use to define pixels value.
     */
    public void setPixel(double pixel) {
        this.pixel = pixel;

    }

    /**
     * Sets the value of color
     *
     * @param col - a Color object which contains the current color of the
     * active cell.
     */
    public void setColor(Color col) {
        color = col;
    }

    /**
     * Draws the board and colors living cells on canvas.
     *
     * @param board - the current board
     */
    public void draw(Board board) { //sender inn board istedenfor Cell [][] og bruker board sin lengde og vidde

        gsC.clearRect(0, 0, gsC.getCanvas().getWidth(), gsC.getCanvas().getHeight());
        gsC.setFill(color);
        gsC.strokeLine(0.1, 0.1, 0.1, 0.1);
        gsC.setStroke(Color.valueOf("99b3ff"));

        for (int x = 0; x < board.getLength(); x++) {
            for (int y = 0; y < board.getWidth(); y++) {
                gsC.strokeRect(x * pixel, y * pixel, pixel, pixel);
                if (board.getIsAlive(x, y)) {
                    gsC.fillRect(x * pixel,
                            y * pixel,
                            pixel,
                            pixel);
                }

            }
        }
    }
}
