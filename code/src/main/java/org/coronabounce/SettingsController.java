package org.coronabounce;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;

public class SettingsController
{

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    public void passSettingsToController(MouseEvent mouseEvent)
    {

    }

    @FXML
    public int individualsNumberSettings () {
    return 0;
    }

    public TextField individualsNumberSettings;

    int newIndividualsNumber = Integer.parseInt(individualsNumberSettings.getText());
}