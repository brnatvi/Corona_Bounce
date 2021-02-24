package org.coronabounce.models;

import org.coronabounce.views.Dot;

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

    public static double getHeight() {
        return height;
    }
    public  void moving(){
        this.t=new Timer();
        this.t.schedule(this.timerTask=new TimerTask() {
            @Override
            public void run() {
                p.printMovement();
            }
        },0,1*5000);

    }


    public Population getP() {
        return p;
    }


    public void test(){
        p.printPop();
        this.moving();

    }
}
