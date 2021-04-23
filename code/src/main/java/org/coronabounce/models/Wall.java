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

    public void makeWallGoDown(Population pop){
        TimerTask tt = null;
        pop.getTimer().schedule(tt = new TimerTaskWall(this.controller,this), 0, 100);
    }
    /**
    *{@summary Return true if it will cross the wall in x.}<br>
    *@param coc The CoquilleBille that we may make bounce.
    */
    public boolean willCrossWallInX(CoquilleBille coc){

        double curentX = coc.getPosition().getX();
        double futurX = curentX+coc.getMovingSpeedX();
        double radius = coc.getPopulation().getRadiusDot();
        // radius/=2;
        //part without thinking about CoquilleBille radius.
        // if(curentX < positionX-thikness/2 && futurX > positionX-thikness/2){ return true;}
        // if(curentX > positionX+thikness/2 && futurX < positionX+thikness/2){ return true;}
        //part with thinking about CoquilleBille radius.
        if(curentX < positionX- thickness /2 && futurX+radius > positionX- thickness /2){ return true;}
        if(curentX > positionX+ thickness /2 && futurX-radius < positionX+ thickness /2){ return true;}

        // if (intersect(futurX)) {
        //     if (futurX<positionX) futurX=positionX-thikness/2-radius-1;
        //     else if(futurX >positionX) futurX=positionX+thikness/2+radius+1;
        //     return true;
        // }
        // if(intersect(curentX)){return  true;}

        return false;
    }

    private boolean isBetween(double c,double a , double b) {
        if( c<=b && c>=a ) return true;
        return false;
    }

    private boolean intersect(double X1)
    {
        double radius=controller.getRadiusDot();
        if((isBetween(X1,X1-radius,X1+radius)) && (isBetween(X1,positionX- thickness /2-radius,positionX+ thickness /2+radius))) return true;
        return false;
    }


    /**
    *{@summary Return true if it will cross the wall in y.}<br>
    *It will hit in Y only if wall is enoth low.<br>
    *@param coc The CoquilleBille that we may make bounce.
    */
    public boolean willCrossWallInY(CoquilleBille coc){
      if(positionY > (coc.getPosition().getY() )){
        return true;
      }
      return false;
    }

}
class TimerTaskWall extends TimerTask{
    private Wall wall;
    private Controllable controller;

    public TimerTaskWall(Controllable c, Wall wall){
        this.wall = wall;
        this.controller = c;
    }
    @Override
    public synchronized void run(){
        if (this.controller.getState() == Controllable.eState.Working){
            wall.setPositionY(wall.getPositionY()+1);//le mur avance petit a petit pour aller de la postio y=0 et attendre y=zone.height
            if(wall.getPositionY()>controller.getSpaceSize()[1]){
                cancel();
            }
        }
    }
}
