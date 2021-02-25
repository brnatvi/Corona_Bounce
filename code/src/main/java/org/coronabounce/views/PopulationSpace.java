package org.coronabounce.views;

import javafx.collections.ObservableList;
import javafx.scene.shape.Circle;
import org.coronabounce.models.*;
import org.coronabounce.mvcconnectors.Controllable;
import org.coronabounce.mvcconnectors.Displayable;
import java.util.*;

import static javafx.scene.paint.Paint.valueOf;

public class PopulationSpace
{
    private Displayable model;
    private Controllable controller;
    private ObservableList<CoquilleBille> allPoints;
    private Timer timer;
    private TimerTask timerTask;
    private double DOT_RADIUS = 2.0;


    public PopulationSpace (Displayable m, Controllable c)
    {
        this.model = m;
        this.controller = c;
        this.allPoints = (ObservableList<CoquilleBille>) model.getAllPoints();
        this.timer = new Timer();
        this.timerTask = new TimerTask()
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

                }
            }
            //TODO how to delete point on previous position ?
        };
    }


    /**
     * This function interrogate observable list of points 30 times per seconds.
     */
    public void scanState()
    {
        this.timer.schedule(timerTask, 0, 33);
    }
}
