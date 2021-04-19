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
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static MainController controllerMain;
    private static SettingsController controllerSettings;
    private static Parent parentMain, parentSettings;


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

    static void setRoot(String fxml) {
        if(fxml == "corona bounce") {
            scene.setRoot(parentMain);
        } else {
            scene.setRoot(parentSettings);
        }
    }

    private static Parent loadMain() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("corona bounce.fxml"));
        Parent p = fxmlLoader.load();
        controllerMain = fxmlLoader.getController();
        return p;
    }

    private static Parent loadSettings() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("settings.fxml"));
        Parent p = fxmlLoader.load();
        controllerSettings = fxmlLoader.getController();
        return p;
    }

    public static void main(String[] args) {
        launch();
    }
}
