package org.coronabounce.models;

import org.coronabounce.mvcconnectors.Controllable;

import java.util.*;
import java.awt.Rectangle;

/**
 * Boundary / wall which separate Zone by sections and so it limit individual's moving.
 * It has a fix X position and grows up progressively.
 */
public class Wall  {
    /** Unique id */
    private final int id;
    /** Id counter */
    private static int cpt=0;
    /** Thickness of the wall */
    private final double thickness;
    /** X coordinate of the wall */
    private final double positionX;
    /** Height of the wall */
    private double positionY;
    /** Current controller */
    private Controllable controller;

    // CONSTRUCTORS ------------------------------------------------------------
    public Wall(Controllable controller, double posX){
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
    public Rectangle getRectangle(){ return new Rectangle((int)(getPositionX()-getThickness()-1),0,(int)(getThickness()*2+1),(int)(getPositionY()));}

    // FUNCTIONS ---------------------------------------------------------------
    public String toString(){return id+" x="+positionX+" y="+positionY+" th="+ thickness;}

    /**
    *{@summary Schedule a new timerTask that will make wall go down every 100ms.}<br>
    *@param pop Population to use the timer.
    */
    public void makeWallGoDown(Population pop){
        TimerTask tt = null;
        pop.getTimer().schedule(tt = new TimerTaskWall(this.controller,this), 0, 100);
    }

    /**
    *{@summary Return a byte to know how to bounce.}<br>
    *@param coc The CoquilleBille that we may make bounce.
    *@return 0 to bounce in y, 1 to bounce in x, 2 to bounce in x and y, everything else to do nothing.
    */
    public byte needToBounceBecauseOfWall(CoquilleBille coc){
      if(willGoIntoTheWall(coc)){
        if(isIntoTheWall(coc)){
          // System.out.println("A bille was in a wall: "+coc);//@a
          return -1;
        }
        if (willCrossWallInXY(coc)){
          return 2;
        }else if (willCrossWallInX(coc)){
          // if(willCrossWallInY(coc)){
          //   System.out.println("BOUUNCE 2222222222222222");//@a
          //   return 2;
          // }
          return 1;
        }else{
          return 0;
        }
      }
      return -1;
    }

    // TEST FUNCTIONS -----------------------------------------------------------------

    /**
    *{@summary Return true if it will cross the wall in x. Used only by tests. }<br>
    *@param coc The CoquilleBille that we may make bounce.
    */
    //public only for test.
    public boolean willCrossWallInX(CoquilleBille coc){
        double curentX = coc.getCurrentPosition().getX();
        double futurX = curentX+coc.getMovingSpeedX();
        double radius = coc.getPopulation().getRadiusDot();
        if(curentX < positionX- thickness /2 && futurX+radius > positionX- thickness /2){ return true;}
        if(curentX > positionX+ thickness /2 && futurX-radius < positionX+ thickness /2){ return true;}
        // double wallBorder1 = positionX-thickness/2;
        // double wallBorder2 = positionX+thickness/2;
        // if(futurX+radius > wallBorder1){ return true;}
        // if(futurX-radius < wallBorder2){ return true;}
        return false;
    }
    public boolean willCrossWallInXY(CoquilleBille coc){
      if(willCrossWallInX(coc) && willCrossWallInY(coc)){
        return true;
      }
      return false;
    }

    /**
    *{@summary Return true if it will cross the wall in y.}<br>
    *@param coc The CoquilleBille that we may make bounce.
    */
    //public only for test.
    public boolean willCrossWallInY(CoquilleBille coc){
        double curentY = coc.getCurrentPosition().getY();
        double futurY = curentY+coc.getMovingSpeedY();
        double radius = coc.getPopulation().getRadiusDot();
        if(positionY > curentY && positionY < futurY){return true;}
        if(positionY < curentY && positionY > futurY){return true;}
        // System.out.println("curent "+(curentY-radius));
        // System.out.println("futur "+(futurY-radius));
        //positionY < curentY-radius &&
        //si est plus bas que le bord haut du point.
        // if(positionY > futurY-radius){return true;}
        return false;
    }

    /**
    *{@summary Return true if it will go into the wall. Used by tests. }<br>
    *It will return true if coc is already in the wall.<br>
    *@param coc The CoquilleBille that we test.
    */
    //public only for test.
    public boolean willGoIntoTheWall(CoquilleBille coc){
      double curentX = coc.getCurrentPosition().getX();
      double futurX = curentX+coc.getMovingSpeedX();
      double curentY = coc.getCurrentPosition().getY();
      double futurY = curentY+coc.getMovingSpeedY();
      double radius = coc.getPopulation().getRadiusDot();
      if(futurY-radius < positionY){
        if(futurX+radius > positionX-thickness/2 && futurX-radius < positionX+thickness/2){
          return true;
        }
      }
      // Rectangle r2 = new Rectangle((int)(futurX-radius),(int)(futurY-radius),(int)(radius*2+1),(int)(radius*2+1));
      // Rectangle r = getRectangle();
      // if(r.intersects(r2)){
      //   return true;
      // }
      return false;
    }

    /**
    *{@summary Return true if it is into the wall. Used only by tests. }<br>
    *@param coc The CoquilleBille that we test.
    */
    //public only for test.
    public boolean isIntoTheWall(CoquilleBille coc){
        double curentX = coc.getCurrentPosition().getX();
        double curentY = coc.getCurrentPosition().getY();
        double radius = coc.getPopulation().getRadiusDot();
        if(curentY-radius < positionY){
            if(curentX+radius > positionX-thickness/2 && curentX-radius < positionX+thickness/2){
            return true;
            }
        }
        return false;
    }
}

/**
 *{@summary Timer task to make wall goes down.}
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
     * {@summary Makes wall go down from wallSpeed every time we call it.}<br>
     * Task will auto destroy itself if it reach the limits of the Zone.<br>
     */
    @Override
    public synchronized void run(){
        if (this.controller.getState() == Controllable.eState.Working){
            wall.setPositionY(wall.getPositionY()+controller.getWallSpeed());
            //TODO check that no CoquilleBille will be crush by the wall or make it move (or give it more speed).
            if(wall.getPositionY()>controller.getSpaceSize()[1]){
                cancel();
            }
        }
    }
}
