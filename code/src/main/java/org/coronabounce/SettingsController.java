package org.coronabounce;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.coronabounce.controllers.Controller;
import org.coronabounce.mvcconnectors.Controllable;

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

    /**
     * @summary takes settings from GUI to renitialize mainController
     */
    @FXML
    public void passSettingsToController() {
        this.c = new Controller();
        int newIndividualsNumber = Integer.parseInt(individualsNumberSettings.getText());
        c.setPersonsCount(newIndividualsNumber);
        double newCovidDuration = (sliderCovidDuration.getValue())*1000;
        c.setDurationCovid((long)newCovidDuration);
        double newContaminationRadius = (sliderContaminationRadius.getValue());
        c.setContaminationRadius(newContaminationRadius);
        double newHealingDuration = (sliderCovidDuration.getValue())*1000;
        c.setDurationHealing((long)newHealingDuration);
        double newNonContaminationDuration = (sliderNonContaminationDuration.getValue())*1000;
        c.setDurationNonContamination((long)newNonContaminationDuration);
        double newWallsNumber = (sliderWallsNumber.getValue());
        c.setWallsCount((int)newWallsNumber);

        // saves new settings in currentController of MainController
        mainController.setSettingsController(c);
        mainController.changeController(c);

        // inits graphPanel, fills mainGrid by graphPanel and draw new populations
        mainController.initialize();
        mainController.btnStart.setDisable(false);

        App.setRoot("corona bounce");
    }

}