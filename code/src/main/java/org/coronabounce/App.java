package org.coronabounce;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
//import org.coronabounce.models.musique;
import java.io.IOException;

/**
 * JavaFX Application class
 */
public class App extends Application {

    /** Scene of the application. **/
    private static Scene scene;
    /** Controller of main fxml file. **/
    private static MainController controllerMain;
    /** Controller of settings fxml file. **/
    private static SettingsController controllerSettings;
    /** Parent of main / settings nodes. **/
    private static Parent parentMain, parentSettings;

    /**
     * {@summary Redefinition of the main entry point for application.}
     * <ul>
     * <li> first loads main window parent and settings window parent (at the same tme)
     * <li> loads scene basing main parent
     * <li> defines optimal size of windows based on the screen size, min/max dimensions and resizability
     * <li> sets onCloseRequest
     * <li> shows
     * </ul>
     * @param stage of application.
     */
    @Override
    public void start(Stage stage) throws IOException {
        parentMain = loadMain();
        parentSettings = loadSettings();
        controllerSettings.setMainController(controllerMain);

        double heightRatio = .80;
        double widthRatio = .80;

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        double sceneHeight = primaryScreenBounds.getHeight() * heightRatio;
        double sceneWidth = primaryScreenBounds.getWidth() * widthRatio;
        scene = new Scene(parentMain, sceneWidth, sceneHeight);

        scene.getStylesheets().add(getClass().getResource("style.css").toString());
        stage.setScene(scene);

        stage.setMinHeight(600);
        stage.setMinWidth(1000);

        stage.sizeToScene();
        stage.setResizable(true);
        stage.show();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try
                {
                    Platform.exit();
                    System.exit(0);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
       // musique.start();
    }

    /**
     * {@summary Setter of scene's root.}
     * @param fxml name of fxml file.
     */
    static void setRoot(String fxml) {
        if(fxml == "corona bounce") {
            scene.setRoot(parentMain);
        } else {
            scene.setRoot(parentSettings);
        }
    }

    /**
     * {@summary Loader of main window.}
     */
    private static Parent loadMain() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("corona bounce.fxml"));
        Parent p = fxmlLoader.load();
        controllerMain = fxmlLoader.getController();
        return p;
    }

    /**
     * {@summary Loader of settings window.}
     */
    private static Parent loadSettings() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("settings.fxml"));
        Parent p = fxmlLoader.load();
        controllerSettings = fxmlLoader.getController();
        return p;
    }

    /**
     * {@summary Launcher of application.}
     */
    public static void main(String[] args) {
        launch();
    }
}
