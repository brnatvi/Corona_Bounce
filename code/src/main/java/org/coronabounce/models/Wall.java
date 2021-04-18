package org.coronabounce.models;

import org.coronabounce.controllers.Controller;
import org.coronabounce.mvcconnectors.Controllable;

import java.util.*;
public class Wall  {
    private final int id;
    private static int cpt=0;
    private double thikness;
    private double positionX;
    private double positionY;
    private Controllable controller;

    public Wall(Controllable iController, double posX){//ce mur va separer la population en deux populations
       this.thikness=Controller.getThickness();
       this.positionX=posX;
       this.positionY=0;
       this.controller = iController;
       id=cpt++;
    }
    public String toString(){return id+" x="+positionX+" y="+positionY+" th="+thikness;}

    public void setPositionY(double positionY) { this.positionY = positionY; }

    public double getPositionX() { return this.positionX; }
    public double getPositionY() { return positionY; }
    public double getThickness() { return this.thikness; }
    public void makeWallGoDown(Population pop){
        TimerTask tt = null;
        pop.getT().schedule(tt = new TimerTaskWall(this.controller,this), 0, 100);
    }
    /**
    *{@summary Return true if it will cross the wall in x.}<br>
    *@param coc The CoquilleBille that we may make bounce.
    */
    public boolean willCrossWallInX(CoquilleBille coc){

      double curentX = coc.getPosition().getX();
      double futurX = curentX+coc.getMovingSpeedX();
      double radius = coc.getPop().getRadiusDot();
      radius/=2;
      if(curentX < positionX-thikness/2 && futurX > positionX-thikness/2){ return true;}
      if(curentX > positionX+thikness/2 && futurX < positionX+thikness/2){ return true;}
      if(curentX-radius < positionX-thikness/2 && futurX+radius > positionX+thikness/2){ return true;}
      if(curentX+radius > positionX+thikness/2 && futurX-radius < positionX+thikness/2){ return true;}
      return false;
    }




    /**
    *{@summary Return true if it will cross the wall in y.}<br>
    *It will hit in Y only if wall is enoth low.<br>
    *@param coc The CoquilleBille that we may make bounce.
    */
    public boolean willCrossWallInY(CoquilleBille coc){
      if(positionY > coc.getPosition().getY()){
        return true;
      }
      return false;
    }

}
class TimerTaskWall extends TimerTask{
    private Wall w;
    private Controllable controller;

    public TimerTaskWall(Controllable iController, Wall w){
        this.w = w;
        this.controller = iController;
    }
    @Override
    public synchronized void run(){
        if (this.controller.getState() == Controllable.eState.Working){
            w.setPositionY(w.getPositionY()+1);//le mur avance petit a petit pour aller de la postio y=0 et attendre y=zone.height
            if(w.getPositionY()>Controller.getHeight()){
                cancel();
            }
        }
    }
}
