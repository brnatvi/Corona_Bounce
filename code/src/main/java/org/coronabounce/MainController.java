package org.coronabounce;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import org.coronabounce.models.CoquilleBille;
import org.coronabounce.mvcconnectors.Controllable;
import org.coronabounce.mvcconnectors.Displayable;

import static javafx.scene.paint.Paint.valueOf;


public class MainController
{
    private Displayable model;
    private Controllable controller;
    private ObservableList<CoquilleBille> allPoints;
    private double DOT_RADIUS = 2.0;

    @FXML
    private Pane panel;

    public MainController (Displayable m, Controllable c)
    {
        this.model = m;
        this.controller = c;
        this.allPoints = (ObservableList<CoquilleBille>) model.getAllPoints();
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

                    Circle point = new Circle(coordX, coordY, DOT_RADIUS);

                    if (state.equals("Healthy")) { point.setFill(valueOf("#1abd38")); }
                    if (state.equals("Recovered")) { point.setFill(valueOf("#ff8000")); }
                    if (state.equals("Sick")) { point.setFill(valueOf("#14902b")); }

                    panel.getChildren().add(point);
                }
            }
        };

        timer.schedule(timerTask, 0, 33);
    }

                         
    @FXML
    private void switchToSettings() throws IOException {
        App.setRoot("settings");
    }

}
