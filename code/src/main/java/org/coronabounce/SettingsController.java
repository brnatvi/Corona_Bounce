package org.coronabounce;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import org.coronabounce.controllers.Controller;
import org.coronabounce.mvcconnectors.Controllable;

public class SettingsController
{

    MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    public void passSettingsToController(MouseEvent mouseEvent) throws IOException {
        //MainController mc = new MainController();
        Controllable c = new Controller();
        c.setPersonsCount(9);
        //App.setRoot("corona bounce");

        mainController.changeController(c);
        App.setRoot("corona bounce");
    }

//    @FXML
//    public int individualsNumberSettings () {
//        return 0;
//    }
//

    public TextField individualsNumberSettings;

    public void attributeIndividualsNumber() {
        int newIndividualsNumber = Integer.parseInt(individualsNumberSettings.getText());
    }

}