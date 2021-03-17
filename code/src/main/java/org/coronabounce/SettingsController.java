package org.coronabounce;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
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

    @FXML TextField individualsNumberSettings;
    @FXML Slider sliderCovidDuration;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

//    public void attributeIndividualsNumber() {
//        int newIndividualsNumber = Integer.parseInt(individualsNumberSettings.getText());
//    }

    @FXML
    public void passSettingsToController(MouseEvent mouseEvent) throws IOException {
        Controllable c = new Controller();
//        int newIndividualsNumber = 20;
        int newIndividualsNumber = Integer.parseInt(individualsNumberSettings.getText());
        c.setPersonsCount(newIndividualsNumber);
        double newCovidDuration = (sliderCovidDuration.getValue())*1000;
//        c.setPersonsCount(8);
        c.setDurationCovid((long)newCovidDuration);


        mainController.changeController(c);
        App.setRoot("corona bounce");
    }

//    @FXML
//    public int individualsNumberSettings () {
//        return 0;
//    }
//

//    public TextField individualsNumberSettings;



}