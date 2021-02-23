package org.coronabounce;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

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
}