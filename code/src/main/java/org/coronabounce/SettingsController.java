package org.coronabounce;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.coronabounce.controllers.Controller;
import org.coronabounce.mvcconnectors.Controllable;

public class SettingsController
{
    private MainController mainController;
    private Controllable c = null;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML TextField individualsNumberSettings;
    @FXML Slider sliderCovidDuration;
    @FXML Slider sliderContaminationRadius;
    @FXML Slider sliderHealingDuration;
    @FXML Slider sliderNonContaminationDuration;

//    public void attributeIndividualsNumber() {
//        int newIndividualsNumber = Integer.parseInt(individualsNumberSettings.getText());
//    }

    @FXML
    public void passSettingsToController(MouseEvent mouseEvent) throws IOException {
        this.c = new Controller();
//        int newIndividualsNumber = 20;
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

        // save new settings in currentController of MainController
        mainController.setSettingsController(c);
        mainController.changeController(c);


        // init graphPanel, fil mainGrid by graphPanel and draw new populations
        mainController.initNewPopulation();
        mainController.changeEnableDisable(mainController.btnStart);

        App.setRoot("corona bounce");
    }

    public void performAction(ActionEvent actionEvent) throws IOException {

        MenuItem target  = (MenuItem) actionEvent.getSource();
        System.out.println("Clicked On Item:"+target.getId());

        Controllable c = new Controller();
        c.setPersonsCount(99);

        mainController.changeController(c);
        App.setRoot("corona bounce");
    }
}