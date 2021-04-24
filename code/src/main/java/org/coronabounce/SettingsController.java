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
    @FXML Slider sliderIncubationDuration;
    @FXML Slider sliderContaminationRadius;
    @FXML Slider sliderHealingDuration;
    @FXML Slider sliderImmunityDuration;
    @FXML Slider sliderWallsNumber;
    @FXML Slider sliderWallsSpeed;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * {@summary takes settings from GUI to renitialize mainController.}
     */
    @FXML
    public void passSettingsToController() {
        this.c = new Controller();
        int newIndividualsNumber = Integer.parseInt(individualsNumberSettings.getText());
        c.setPersonsCount(newIndividualsNumber);
        double newIncubationDuration = (sliderIncubationDuration.getValue())*1000;
        c.setDurationIncubation((long)newIncubationDuration);
        double newContaminationRadius = (sliderContaminationRadius.getValue());
        c.setContaminationRadius(newContaminationRadius);
        double newHealingDuration = (sliderIncubationDuration.getValue())*1000;
        c.setDurationHealing((long)newHealingDuration);
        double newImmunityDuration = (sliderImmunityDuration.getValue())*1000;
        c.setDurationImmunity((long)newImmunityDuration);
        double newWallsNumber = (sliderWallsNumber.getValue());
        c.setWallsCount((int)newWallsNumber);
        double newWallsSpeed = sliderWallsSpeed.getValue();
        c.setWallSpeed((int)newWallsSpeed);

        // saves new settings in currentController of MainController
        mainController.setSettingsController(c);
        mainController.changeController(c);

        // inits graphPanel, fills mainGrid by graphPanel and draw new populations
        mainController.initialize();
        mainController.btnStart.setDisable(false);

        App.setRoot("corona bounce");
    }

}
