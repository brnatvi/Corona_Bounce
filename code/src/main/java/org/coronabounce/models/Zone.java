package org.coronabounce.models;

import org.coronabounce.mvcconnectors.Controllable;

import java.util.Timer;
import java.util.TimerTask;

/**
 *{@summary Zone where CoquilleBille can move.}
 *It have a timer to make CoquilleBille move in it.
 */
public class Zone  {

    private Controllable controller;
    /**
     *Population that is in the Zone.
     */
    private Population p;
    /**
     *Width of all Zones.
     */
    private static double width=1;
    /**
     *Heigth of all Zones.
     */
    private static double height=1;
    private Timer timer;
    private TimerTask timerTask;

    // CONSTRUCTORS ------------------------------------------------------------
    public Zone (Controllable controller,boolean isLockDown, boolean isWall, boolean isRestrictionMovement)
    {
        timer=new Timer();
        this.controller = controller;
        setWidth(controller.getSpaceSize()[0]);
        setHeight(controller.getSpaceSize()[1]);
        if(isLockDown) {
            int nbpersons=controller.getPersonsCount();
            this.p = new Population(controller,nbpersons-nbpersons/6,nbpersons/6,0,isLockDown,isWall,isRestrictionMovement);
        }else{
            this.p = new Population(controller, controller.getPersonsCount(),isLockDown, isWall, isRestrictionMovement);
        }
    }

    // GET SET -----------------------------------------------------------------
    public static double getWidth() { return width; }
    public static void setWidth(double w){ if(w>=1){width = w;}}
    public static double getHeight() { return height; }
    public static void setHeight(double h) { if(h>=1){height = h;}}
    public Population getPopulation() {return p;}

    // FUNCTIONS ---------------------------------------------------------------
    /**
     *Stop timer task
     *@param stopTimerTo If true also stop the timer itself.
     */
    public void stopTimerTask(boolean stopTimerTo){
      if (null != this.timerTask){
        if (!this.timerTask.cancel()){
          System.out.println("Can't cancel task!");
        }
        this.timerTask = null;
        this.timer.purge();
      }
      if (stopTimerTo){
        this.timer.cancel();
        this.timer = null;
      }
    }
    /**
     *Make Coquille move every 33 ms.
     */
    public void moving(){
        stopTimerTask(false); //Stop last timer task.
        this.timer.schedule(this.timerTask=new TimerTask() {
            @Override
            public void run(){
                if (controller.getState() == Controllable.eState.Working){
                    p.Contacts();
                    p.makeBilleMove();
                }
            }
        },0,33);
    }
}
