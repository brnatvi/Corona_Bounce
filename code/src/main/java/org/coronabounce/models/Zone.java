package org.coronabounce.models;

import org.coronabounce.mvcconnectors.Controllable;

import java.util.Timer;
import java.util.TimerTask;

public class Zone  {

    private Controllable controller;
    private Population p;
    private static double width=1;
    private static double height=1;
    private Timer timer=new Timer();
    private TimerTask timerTask = null;
    private int nbZones;

    public Zone (Controllable controller,boolean isLockDown, boolean isWall, boolean isRestrictionMovement)
    {   /*** nbZones c'est le nombre de petites zones, si on a 2 zones ca veut dire qu'on a un mur
    c'est à dire  nbmurs=nbZones-1 **/
        this.nbZones=nbZones;
        this.controller = controller;
        setWidth(controller.getSpaceSize()[0]);
        setHeight(controller.getSpaceSize()[1]);
        this.p = new Population(controller, controller.getPersonsCount(),isLockDown, isWall, isRestrictionMovement);
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

    public static double getWidth() { return width; }
    public static void setWidth(double w){ if(w>=1){width = w;}}
    public static double getHeight() { return height; }
    public static void setHeight(double h) { if(h>=1){height = h;}}
    public Population getPopulation() {return p;}

    public void moving(){
        /** if we apply this (p.socialDistancing()) with 100persons for example, it is quiet visible that the contamination rate slows down **/

        stopTimer(false);
        this.timer.schedule(this.timerTask=new TimerTask() {
            @Override
            public void run(){
                if (controller.getState() == Controllable.eState.Working){
                    p.Contacts();
                    p.Moving_Bille();
                }
            }
        },0,1*33);
    }

    public void test(){

        this.moving();
    }
    
}
