package org.coronabounce.models;

import org.coronabounce.mvcconnectors.Controllable;

import java.util.Timer;
import java.util.TimerTask;

public class Zone  {

    private Controllable controller;
    private Population p;
    private static double width;
    private static double height;
    private Timer t=new Timer();
    private TimerTask timerTask;

    public Zone (Controllable controller)
    {
        this.controller = controller;
        this.width = controller.getSpaceSize()[0];
        this.height = controller.getSpaceSize()[1];
        this.p = new Population(controller, controller.getPersonsCount());
    }
    public static double getWidth() { return width; }
    public static void setWidth(double w){ Zone.width = w; }
    public static double getHeight() { return height; }
    public static void setHeight(double h) { Zone.height = h;}
    public Population getPopulation() {return p;}


    public void moving(){
        this.t=new Timer();
        this.t.schedule(this.timerTask=new TimerTask() {
            @Override
            public void run() {
                Sick.contaminate(p, controller.getDurationCovid(),10000, controller.getDurationNonContamination());
                p.printMovement();
            }
        },0,1*150);

    }

    public void test(){
       // p.setContaminationRadius(10);
        p.printPop();
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
