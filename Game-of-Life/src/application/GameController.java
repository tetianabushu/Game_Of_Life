package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Programutvikling school project for HiOA
 *
 * @author Tetiana Bushuieva
 * 
 * Classname: GameController. Description: This class contains properties and
 * methods to controll the animation. This class implements intializable Via
 * this class the game logic is connected with GUI which is built in FXML
 */
public class GameController implements Initializable {

    @FXML
    private Canvas canvas;
    @FXML
    private Slider speedSlider;
    @FXML
    private Slider zoomSlider;
    @FXML
    private Board board;
    @FXML
    private ColorPicker colorPicker;
    private GraphicsContext graphicsContext;
    private Draw drawClass;
    private GameOfLife gameOfLife;
    private double pixel;

    /**
     * Starts the animation. Connected with GUI, calles timeline method to start
     * the game.
     */
    @FXML
    private void startGame() {

        gameOfLife.getTimeline().play();
    }

    /**
     * Loads the pattern from a rle-file. User can select a file from the file
     * chooser and send it to GoLFileReader for reading and processing. After
     * the file is read it inisializes the board. It handles IOException and
     * PatternFormatException.
     *
     *
     */
    @FXML
    private void loadPattern() {
        stopGame();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open resource file");
        try {
            Stage st = new Stage();
            File chosenFile = fileChooser.showOpenDialog(st);

            if (chosenFile != null) {
                GoLFileReader boardReader = new GoLFileReader();

                board = boardReader.readGameBoardFromFile(chosenFile);
                InitializeWithBoard();
            } else {
                throw new IOException("File not found.");
            }
        } catch (IOException ex) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();

        } catch (PatternFormatException ex) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * Stops the animation. Connected with GUI, invokes the timeline method to
     * stop the game.
     */
    @FXML
    private void stopGame() {
        gameOfLife.getTimeline().stop();
    }

    /**
     * Connected with GUI, invokes the timeline method to stop the game. Resets
     * canvas and draws an new empty board on it.
     */
    @FXML
    private void resetGame() {
        gameOfLife.getTimeline().stop();
        gameOfLife.reset();
        drawClass.draw(board);
    }

    /**
     * The method initializes the board and all the events on the board. This
     * method is called just onec on the start og application and creates a
     * board of size(20*20)
     *
     * It contains two parameters, one to initialize an input stream from a URL.
     * The other one is used to localize the root of the object.
     *
     * @param url - initializes an input stream from a URL
     * @param resource - resource is used to localize the root of object.
     */
    @Override
    public void initialize(URL url, ResourceBundle resource) {
        int numWorkers = Runtime.getRuntime().availableProcessors();
        System.out.println(numWorkers);
        graphicsContext = canvas.getGraphicsContext2D();
        board = new DynamicGameBoard(20, 20);

        InitializeWithBoard();
    }

    /**
     * It creats and draws a board on the canvas using values of
     * GraphicsContext, length and width of the board. Updates the current value
     * of pixel to calculate the right size of the cells. Sets up value of the
     * GameOfLife object and using it calles timeline method to control the
     * speed of animation. It registers mouseEvent: MousePressedEvent,
     * MouseDraggedEvent, Zoom and ColorPicker
     *
     */
    private void InitializeWithBoard() {
        drawClass = new Draw(canvas.getGraphicsContext2D(), board.getWidth(), board.getLength());
        pixel = drawClass.updatePixelSize(board.getWidth(), board.getLength());
        drawClass.draw(board);
        gameOfLife = new GameOfLife(board, drawClass);

        gameOfLife.getTimeline().rateProperty().bind(speedSlider.valueProperty());

        setMousePressedEvent();
        setMouseDraggedEvent();
        setOnZoom();
        setColorPicker();
    }

    /**
     * This method gives the user a possibility to choose the color of cells in
     * the game. Sets the color on the cells. Resiaters an setOnAction event,
     * changes the color and draws board again with new color
     *
     *
     */
    public void setColorPicker() {
        colorPicker.setOnAction(new EventHandler() {
            public void handle(Event t) {
                drawClass.setColor(colorPicker.getValue());
                drawClass.draw(board);
            }
        });
    }

    /**
     * Allows the user to zoom in or zoom out the board. This method registers a
     * mouse drag event on the zoomslider which gets the current value of zoom.
     * Also it updates pixel value and draws the board again. The board adjusts
     * after the selected value.
     *
     */
    public void setOnZoom() {
        zoomSlider.setOnMouseDragged((MouseEvent event) -> {
            double currentValue = zoomSlider.valueProperty().getValue();
            drawClass.setPixel(currentValue / 4);
            drawClass.draw(board);
            event.consume();
        });
    }

    /**
     * The method registers setOnMousePressed event. The event determines the
     * cell's state when a mouse press is registered on the board. It calculates
     * the cell number from mouse click coordinates and sets a cell alive if it
     * is dead from before and vice versa. After updating cell the GUI is
     * updated. It has exception handling. It doesn't have any custom
     * exceptions, and it only catches an unknown exception, if there is any.
     *
     */
    public void setMousePressedEvent() {
        canvas.setOnMousePressed((MouseEvent event) -> {

            try {
                int cellX = ((int) event.getX() / (int) pixel);
                int cellY = ((int) event.getY() / (int) pixel);

                int x = cellX * (int) pixel;
                int y = cellY * (int) pixel;

                if (cellX < board.getLength() && cellY < board.getWidth()
                        && cellX >= 0 && cellY >= 0) {

                    if (board.getIsAlive(cellX, cellY) != true) { //endret metode
                        board.setState(true, cellX, cellY);// endret metode

                        graphicsContext.fillRect(x, y, pixel, pixel);

                    } else {
                        board.setState(false, cellX, cellY);

                        graphicsContext.clearRect(x, y, pixel, pixel);
                    }
                    graphicsContext.setStroke(Color.valueOf("99b3ff"));
                    graphicsContext.strokeRect(x, y, pixel, pixel);
                    graphicsContext.strokeRect(x, y, pixel, pixel);
                }
            } catch (Exception exc) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, exc);
            }
        });
    }

    /**
     * The method registers setMouseDragged event. The event determines the
     * cell's state when a mouse drag is registered on the board. It calculates
     * the cell number from mouse click coordinates and sets a cell alive if it
     * is dead from before. After updating cell the GUI is updated. It has
     * exception handling. It doesn't have any custom exceptions, and it only
     * catches an unknown exception, if there is any.
     *
     */
    public void setMouseDraggedEvent() {
        canvas.setOnMouseDragged((MouseEvent drag) -> {
            try {
                pixel = drawClass.getPixel();
                int cellX = ((int) drag.getX() / (int) pixel);
                int cellY = ((int) drag.getY() / (int) pixel);

                int x = cellX * (int) pixel;
                int y = cellY * (int) pixel;
                if (cellX < board.getLength() && cellY < board.getWidth()
                        && cellX >= 0 && cellY >= 0) {
                    if (board.getIsAlive(cellX, cellY) == false) {

                        board.setState(true, cellX, cellY);

                        graphicsContext.fillRect(x, y, pixel, pixel);
                        graphicsContext.setStroke(Color.valueOf("99b3ff"));
                        graphicsContext.strokeRect(x, y, pixel, pixel);
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
