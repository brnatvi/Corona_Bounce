package org.coronabounce.models;

import org.coronabounce.mvcconnectors.Controllable;

import java.util.*;

/**
 *{@summary Stop CoquilleBille by making them bounce.}
 *It have a fix X position but can be moved in y.
 */
public class Wall  {
    /** Unique id */
    private final int id;
    /** Id counter */
    private static int cpt=0;
    /** How much thik is the wall */
    private final double thickness;
    private final double positionX;
    private double positionY;
    private Controllable controller;

    // CONSTRUCTORS ------------------------------------------------------------
    public Wall(Controllable controller, double posX){//ce mur va separer la population en deux populations
       this.controller = controller;
       this.thickness =controller.getThickness();
       this.positionX=posX;
       this.positionY=0;
       id=cpt++;
    }

    // GET SET -----------------------------------------------------------------
    public double getPositionX() { return this.positionX; }
    public double getPositionY() { return positionY; }
    public void setPositionY(double positionY) { this.positionY = positionY; }
    public double getThickness() { return this.thickness; }

    // FUNCTIONS ---------------------------------------------------------------
    public String toString(){return id+" x="+positionX+" y="+positionY+" th="+ thickness;}
    /**
    *{@summary Schedule a new timerTask that will make wall go down every 100ms.}<br>
    *@param pop Popu
    */
    public void makeWallGoDown(Population pop){
        TimerTask tt = null;
        pop.getTimer().schedule(tt = new TimerTaskWall(this.controller,this), 0, 100);
    }
    /**
    *{@summary Return true if it will cross the wall in x.}<br>
    *@param coc The CoquilleBille that we may make bounce.
    */
    public boolean willCrossWallInX(CoquilleBille coc){
        double curentX = coc.getCurrentPosition().getX();
        double futurX = curentX+coc.getMovingSpeedX();
        double radius = coc.getPopulation().getRadiusDot();
        if(curentX < positionX- thickness /2 && futurX+radius > positionX- thickness /2){ return true;}
        if(curentX > positionX+ thickness /2 && futurX-radius < positionX+ thickness /2){ return true;}
        return false;
    }

    /**
    *{@summary Return true if it will cross the wall in y.}<br>
    *It will hit in Y only if wall is enoth low.<br>
    *@param coc The CoquilleBille that we may make bounce.
    */
    public boolean willCrossWallInY(CoquilleBille coc){
        if(positionY > (coc.getCurrentPosition().getY() )){
            return true;
        }
        return false;
    }
    public boolean willGoIntoTheWall(){
      //TODO to have thiker wall than dot.
      return false;
    }

}
/**
 *{@summary Timer task to make wall go down.}
 */
class TimerTaskWall extends TimerTask{
    private Wall wall;
    private static Controllable controller;
    /**
     * {@summary Main constructor.}
     * @param controller the controller used to get wall speed.
     * @param wall the Wall that need to move.
     */
    public TimerTaskWall(Controllable controller, Wall wall){
        this.wall = wall;
        this.controller = controller;
    }
    /**
     * {@summary Make wall go down from wallSpeed every time we call it.}<br>
     * Task will auto destroy itself if it reach the limits of the Zone.<br>
     */
    @Override
    public synchronized void run(){
        if (this.controller.getState() == Controllable.eState.Working){
            wall.setPositionY(wall.getPositionY()+controller.getWallSpeed());
            if(wall.getPositionY()>controller.getSpaceSize()[1]){
                cancel();
            }
        }
    }
}
