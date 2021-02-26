package org.coronabounce.models;

import java.util.Timer;
import java.util.TimerTask;

public class Zone {
    private Population p;
    private static double width;
    private static double height;
    Timer t=new Timer();
    TimerTask timerTask;
    public Zone(double w,double h , int nbIndividual){
        this.width=w;
        this.height=h;
        this.p=new Population(nbIndividual);
    }
    public static void setHeight(double height) {Zone.height = height;
    }

    public static void setWidth(double width){
        Zone.width = width;
    }

    public static double getWidth() {
        return width;
    }

    public Population getPopulation() {return p;}
    

    public static double getHeight() {
        return height;
    }
    public void moving(){
        this.t=new Timer();
        this.t.schedule(this.timerTask=new TimerTask() {
            @Override
            public void run() {
                Sick.contaminate(p,5000,10000,15000);
                p.printMovement();
            }
        },0,1*150);

    }


    public Population getP() {
        return p;
    }


    public void test(){
        p.setContaminationRadius(10);
        p.printPop();
        this.moving();

    }
}
