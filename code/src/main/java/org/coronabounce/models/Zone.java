package org.coronabounce.models;

import org.coronabounce.mvcconnectors.Controllable;

import java.util.Timer;
import java.util.TimerTask;

public class Zone  {

    private Controllable controller;
    private Population p;
    private static double width;
    private static double height;
    Timer t=new Timer();
    TimerTask timerTask;

    public Zone (Controllable controller)
    {
        this.controller = controller;
        this.width = controller.getSpaceSize()[0];
        this.height = controller.getSpaceSize()[1];
        this.p = new Population(controller, controller.getPersonsCount());
    }
    public static void setHeight(double h) { Zone.height = h;}

    public static void setWidth(double w){ Zone.width = w; }

    public static double getWidth() { return width; }

    public Population getPopulation() {return p;}

    public static double getHeight() { return height; }
    
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
}
