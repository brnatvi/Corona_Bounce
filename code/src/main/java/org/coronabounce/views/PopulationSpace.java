package org.coronabounce.views;

import javafx.collections.ObservableList;
import org.coronabounce.models.*;
import org.coronabounce.mvcconnectors.Controllable;
import org.coronabounce.mvcconnectors.Displayable;
import java.util.*;

public class PopulationSpace
{
    private Displayable model;
    private Controllable controller;
    private ObservableList<CoquilleBille> allPoints;
    private Timer timer;
    private TimerTask timerTask;

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

                    Dot point = new Dot(coordX, coordY);

                    if (state.equals("Recovered")) { point.changeColor("#ff1f40"); }
                    if (state.equals("Sick")) { point.changeColor("DODGERBLUE"); }
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
