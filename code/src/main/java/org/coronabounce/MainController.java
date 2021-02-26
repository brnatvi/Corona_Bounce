package org.coronabounce;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
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
        Zone z = new Zone(400,250,20);
        Population pop = z.getPopulation();
        pop.setContaminationRadius(10);
        Displayable model = z.getPopulation();
        List<CoquilleBille> allPoints = model.getAllPoints();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(33), ev -> {
            panel.getChildren().retainAll();
            for (CoquilleBille cb : allPoints)
            {

                String state = cb.getIndividual().healthState();
                double coordX = cb.getPosition().getX();
                double coordY = cb.getPosition().getY();
                Circle point = new Circle(coordX, coordY, 4);
                if (state.equals("Healthy")) { point.setFill(valueOf("#4ACE11")); }    //green
                if (state.equals("Recovered")) { point.setFill(valueOf("#ff8000")); }  //orange
                if (state.equals("Sick")) { point.setFill(valueOf("#FF0040")); }      // red
                panel.getChildren().add(point);
            }

        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        //timer.schedule(timerTask, 0, 33);
        z.moving();
    }

                         
    @FXML
    private void switchToSettings() throws IOException {
        App.setRoot("settings");
    }

}
