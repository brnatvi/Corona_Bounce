package org.coronabounce.models;

import org.coronabounce.controllers.Controller;
import org.coronabounce.mvcconnectors.Controllable;

import java.util.Timer;
import java.util.TimerTask;

public class Zone  {

    private final Controllable controller;
    private Population p;
    private Timer timer=new Timer();
    private TimerTask timerTask = null;

    public Zone (Controllable controller)
    {
        System.out.println("Mew zone");
        this.controller = controller;

        this.p = new Population(controller, controller.getPersonsCount());
    }

    public void stopTimer(boolean b_StopTimer)
    {
        if (null != this.timerTask)
        {
            if (!this.timerTask.cancel())
            {
                System.out.println("Can't cancel task!\n");
            }
            this.timerTask = null;
            this.timer.purge();
        }
        if (b_StopTimer)
        {
            this.timer.cancel();
            this.timer = null;
        }
    }


    public Population getPopulation() {return p;}


    public void moving(){
        /** if we apply this (p.socialDistancing()) with 100persons for example, it is quiet visible that the contamination rate slows down **/
       // p.lockDown();
        stopTimer(false);
        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //p.interaction(controller.getDurationCovid(),10000, controller.getDurationNonContamination()); // ses informations sont sauvegardé dans Population, on n'as pas besoin de les transmettre a chaque fois.

        //Pour le mur        //p.separate();
                p.interaction();
                p.printMovement();

            }
        },0,1*33);
    }

    public void test(){
        this.moving();
    }
    /**
    *{@summary Return true if x coordinate is out the the Zone.}
    */

}
