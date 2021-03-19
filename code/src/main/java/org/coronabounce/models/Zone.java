package org.coronabounce.models;

import org.coronabounce.mvcconnectors.Controllable;

import java.util.Timer;
import java.util.TimerTask;

public class Zone  {

    private Controllable controller;
    private Population p;
    private static double width=1;
    private static double height=1;
    private Timer t=new Timer();
    private TimerTask timerTask = null;

    public Zone (Controllable controller)
    {
        System.out.println("Mew zone");
        this.controller = controller;
        setWidth(controller.getSpaceSize()[0]);
        setHeight(controller.getSpaceSize()[1]);
        this.p = new Population(controller, controller.getPersonsCount());
    }

    public void stop(boolean b_StopTimer)
    {
        if (null != this.timerTask)
        {
            if (!this.timerTask.cancel())
            {
                System.out.println("Can't cancel task!\n");
            }
            this.timerTask = null;
            this.t.purge();
        }
        if (b_StopTimer)
        {
            this.t.cancel();
            this.t = null;
        }
    }

    public static double getWidth() { return width; }
    public static void setWidth(double w){ if(w>=1){width = w;}}
    public static double getHeight() { return height; }
    public static void setHeight(double h) { if(h>=1){height = h;}}
    public Population getPopulation() {return p;}


    public void moving(){
        /** if we apply this (p.socialDistancing()) with 100persons for example, it is quiet visible that the contamination rate slows down **/
       // p.lockDown();
        stop(false);
        this.t. schedule(this.timerTask=new TimerTask() {
            @Override
            public void run() {
                //p.interaction(controller.getDurationCovid(),10000, controller.getDurationNonContamination()); // ses informations sont sauvegardé dans Population, on n'as pas besoin de les transmettre a chaque fois.

        //Pour le mur        //p.separate();
                p.interaction();
                //p.updateState();
                p.printMovement();
               // System.out.println("Task hash:" + this.hashCode());
            }
        },0,1*33);
    }

    public void test(){

        //p.printPop();
        this.moving();
    }
    /**
    *{@summary Return true if x coordinate is out the the Zone.}
    */
    public static boolean outOfX(double x){
      if(x<=0 || x>=width){return true;}
      return false;
    }
    /**
    *{@summary Return true if y coordinate is out the the Zone.}
    */
    public static boolean outOfY(double y){
      if(y<=0 || y>=height){return true;}
      return false;
    }
}
