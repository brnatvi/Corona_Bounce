package org.coronabounce;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import org.coronabounce.models.CoquilleBille;
import org.coronabounce.models.Population;
import org.coronabounce.models.Zone;
import org.coronabounce.mvcconnectors.Displayable;

import static javafx.scene.paint.Paint.valueOf;


public class MainController
{
    @FXML
    Pane panel;

    @FXML
    private void initialize()
    {
        Zone z = new Zone(400,300,20);
        Population pop = z.getPopulation();
        pop.setContaminationRadius(10);
        Displayable model = z.getPopulation();
        List<CoquilleBille> allPoints = model.getAllPoints();

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                for (CoquilleBille cb : allPoints)
                {
                    String state = cb.getIndividual().healthState();
                    double coordX = cb.getPosition().getX();
                    double coordY = cb.getPosition().getY();

                    Circle point = new Circle(coordX, coordY, 5);

                    if (state.equals("Healthy")) { point.setFill(valueOf("#1abd38")); }
                    if (state.equals("Recovered")) { point.setFill(valueOf("#ff8000")); }
                    if (state.equals("Sick")) { point.setFill(valueOf("#14902b")); }

                    panel.getChildren().add(point);
                }
            }
        };

        timer.schedule(timerTask, 1, 33);
        z.moving();
    }

                         
    @FXML
    private void switchToSettings() throws IOException {
        App.setRoot("settings");
    }

}
