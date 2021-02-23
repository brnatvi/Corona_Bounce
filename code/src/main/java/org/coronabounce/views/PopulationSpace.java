package org.coronabounce.views;

import javafx.collections.ObservableList;
import javafx.scene.shape.Circle;
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

   // class Dot extends Circle
   // {
   //
   // }

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
                 //   if (state.equals("Healthy"))
                 //   {
                 //       // TODO repaint Dot
                 //   }
                 //   if (state.equals("Recovered"))
                 //   {
                 //       // TODO repaint Dot
                 //   }
                 //   if (state.equals("Sick"))
                 //   {
                 //       // TODO repaint Dot
                 //   }

                    // TODO change position
                }
            }
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
