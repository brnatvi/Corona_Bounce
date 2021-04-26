package org.coronabounce;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.coronabounce.controllers.Controller;
import org.coronabounce.mvcconnectors.Controllable;

/**
 * Settings controller class. Being related to the Settings window, represents settings possible to apply.
 */
public class SettingsController
{
    /** MainController and Controller as parameters of SettingsController**/
    private MainController mainController;
    private Controllable c = null;

    /** Text field for a new individuals number **/
    @FXML TextField individualsNumberSettings;
    /** Slider for a new incubating  duration **/
    @FXML Slider sliderIncubationDuration;
    /** Slider for a new contamination radius **/
    @FXML Slider sliderContaminationRadius;
    /** Slider for a new healing duration **/
    @FXML Slider sliderHealingDuration;
    /** Slider for a new immunity duration**/
    @FXML Slider sliderImmunityDuration;
    /** Slider for a new walls number **/
    @FXML Slider sliderWallsNumber;
    /** Slider for a new walls speed **/
    @FXML Slider sliderWallsSpeed;

    /** Sets a new Main Controller. **/
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * {@summary takes settings from GUI to reinitialize mainController.}
     */
    @FXML
    public void passSettingsToController() {
        // a new Controller is created
        this.c = new Controller();
        // following settings are passed to Controller c :
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
