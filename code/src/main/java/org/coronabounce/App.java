package org.coronabounce;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.coronabounce.controllers.Controller;
import org.coronabounce.models.Population;
import org.coronabounce.models.Zone;
import org.coronabounce.mvcconnectors.Controllable;
import org.coronabounce.mvcconnectors.Displayable;

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
        ((SettingsController) controllerSettings).setMainController(controllerMain);

        scene = new Scene(parentMain, 1000, 600);
        stage.setScene(scene);
        stage.show();

    }

    static void setRoot(String fxml) throws IOException {
        if(fxml == "corona bounce") {
            scene.setRoot(parentMain);
        } else {
            scene.setRoot(parentSettings);
        }

    }

    private static Parent loadMain() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("corona bounce.fxml"));
        Parent p = (Parent) fxmlLoader.load();
        controllerMain = fxmlLoader.getController();
        return p;
    }

    private static Parent loadSettings() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("settings.fxml"));
        Parent p = (Parent) fxmlLoader.load();
        controllerSettings = fxmlLoader.getController();
        return p;
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
     //   Displayable model = new Population(13, 4, 3);
     //   Controllable cont = new Controller();

        launch();
       //Zone z=new Zone(200,100,20);
       // z.moving();
        //z.test();

    }

}
