package org.coronabounce;

import java.io.IOException;
import javafx.fxml.FXML;

public class MainController
{

    @FXML
    private void switchToSettings() throws IOException {
        App.setRoot("settings");
    }
}
