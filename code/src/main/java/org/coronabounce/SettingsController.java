package org.coronabounce;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.coronabounce.controllers.Controller;
import org.coronabounce.mvcconnectors.Controllable;

import java.io.IOException;

public class SettingsController
{
    private MainController mainController;
    private Controllable c = null;

    @FXML TextField individualsNumberSettings;
    @FXML Slider sliderCovidDuration;
    @FXML Slider sliderContaminationRadius;
    @FXML Slider sliderHealingDuration;
    @FXML Slider sliderNonContaminationDuration;
    @FXML Slider sliderWallsNumber;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // takes settings from GUI and passes them to Controller
    @FXML
    public void passSettingsToController() throws IOException {
        // creates new current controller
        this.c = new Controller();
        
        // update his parameters from Settings
        c.setPersonsCount(Integer.parseInt(individualsNumberSettings.getText()));
        c.setDurationCovid((long)(sliderCovidDuration.getValue())*1000);
        c.setContaminationRadius(sliderContaminationRadius.getValue());
        c.setDurationHealing((long)(sliderCovidDuration.getValue())*1000);
        c.setDurationNonContamination((long)(sliderNonContaminationDuration.getValue())*1000);
        c.setWallsCount((int)sliderWallsNumber.getValue());

        // saves new settings and loads them into MainController
        mainController.setSettingsController(c);
        mainController.changeController(c);

        // inits graphPanel, fills mainGrid by graphPanel and draws new populations
        mainController.initNewPopulation();

        mainController.btnStart.setDisable(false);

        App.setRoot("corona bounce");
    }
}