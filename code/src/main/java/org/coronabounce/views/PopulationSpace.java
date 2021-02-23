package org.coronabounce.views;

import javafx.collections.ObservableList;
import org.coronabounce.models.CoquilleBille;
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
                    cb.getIndividual().healthState();
                }
            }
        };
    }

    //================= Getters from Model ============================================================================/


    //================= Own functions =================================================================================/

    /**
     * This function interrogate observable list of points 30 times per seconds.
     */
    public void scanState()
    {
        this.timer.schedule(timerTask, 0, 33);
    }
}
