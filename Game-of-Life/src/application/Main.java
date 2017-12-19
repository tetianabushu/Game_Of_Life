package application;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Programutvikling school project for HiOA
 *
 * @author Tetiana Bushuieva 
 * 
 * Classname:Main. Description: This class extends the Application This class is
 * responsible for automatically loading of the FXML.fxml file.
 */
public class Main extends Application {

    @Override
    /**
     * Starts FXML file
     */
    public void start(Stage stage) throws Exception {
        URL url = getClass().getResource("FXML.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * Executing of the launch method starts the JavaFx application, the UI
     * construction will be performed this is where the whole application starts
     * and ends.
     *
     * @param args - Takes an array of String arguments
     */
    public static void main(String[] args) {
        launch(args);

    }

}
