package org.coronabounce.models;

import org.coronabounce.mvcconnectors.Controllable;

import java.util.*;

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

    private List<CoquilleBille> listCoquille;

    // CONSTRUCTORS ------------------------------------------------------------
    public Wall(Controllable controller, double posX, List<CoquilleBille> listCoquille){
       this.controller = controller;
       this.thickness =controller.getThickness();
       this.positionX=posX;
       this.positionY=0;
       id=cpt++;
       this.listCoquille=listCoquille;
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
    *@param pop Population to use the timer.
    */
    public void makeWallGoDown(Population pop){
        TimerTask tt = null;
        pop.getTimer().schedule(tt = new TimerTaskWall(this.controller,this, listCoquille), 0, 100);
    }

    /**
    *{@summary Return a byte to know how to bounce.}<br>
    *@param coc The CoquilleBille that we may make bounce.
    *@return 0 to bounce in y, 1 to bounce in x, 2 to bounce in x and y, everything else to do nothing.
    */
    public byte needToBounceBecauseOfWall(CoquilleBille coc){
      // pushOutOfTheWallIfNeed(coc); //curently we don't have any issues with CoquilleBille into wall. But if we had we only need to uncomment this line.
      if(willGoIntoTheWall(coc)){
        if (willCrossWallInX(coc)){
          if (willCrossWallInY(coc)){
            return 2;
          }
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

    /**
    *{@summary Return true if it will cross the wall in y.}<br>
    *@param coc The CoquilleBille that we may make bounce.
    */
    //public only for test.
    public boolean willCrossWallInY(CoquilleBille coc){
        double curentY = coc.getCurrentPosition().getY();
        double futurY = curentY+coc.getMovingSpeedY();
        double radius = coc.getPopulation().getRadiusDot();
        double curentX = coc.getCurrentPosition().getX();
        double futurX = curentX+coc.getMovingSpeedX();
        // if(positionY > curentY-radius && positionY < futurY-radius){return true;}
        // if(positionY < curentY+radius && positionY > futurY+radius){return true;}
        //if(point en haut a droite will be in wall)
        if(positionY > futurY-radius && positionX-thickness < futurX+radius){
          if(positionY < curentY-radius){
            // System.out.println("point en haut a droite dans le murs");
            return true;
          }
        }
        if(positionY > futurY-radius && positionX+thickness > futurX-radius){
          if(positionY < curentY-radius){
            System.out.println("point en haut a gauche dans le murs");
            return true;
          }
        }
        // System.out.println("pas de point");
        //if(point en haut a gauche will be in wall)
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
    public void pushOutOfTheWallIfNeed(CoquilleBille coc){
      if(isIntoTheWall(coc)){
        System.out.println("A bille was in a wall: "+coc);//@a
        if(coc.getCurrentPosition().getX()>positionX){
          coc.getCurrentPosition().setX(positionX+thickness+controller.getRadiusDot()+0.01);
        }else{
          coc.getCurrentPosition().setX(positionX-thickness-controller.getRadiusDot()-0.01);
        }
      }
    }
}

/**
 *{@summary Timer task to make wall goes down.}
 */
class TimerTaskWall extends TimerTask{
    private Wall wall;
    private static Controllable controller;
    private List<CoquilleBille> listCoquille;

    /**
     * {@summary Main constructor.}
     * @param controller the controller used to get wall speed.
     * @param wall the Wall that need to move.
     */
    public TimerTaskWall(Controllable controller, Wall wall, List<CoquilleBille> listCoquille){
        this.wall = wall;
        this.controller = controller;
        this.listCoquille = listCoquille;
    }

    /**
     * {@summary Makes wall go down from wallSpeed every time we call it.}<br>
     * Task will auto destroy itself if it reach the limits of the Zone.<br>
     * If a CoquilleBille will be crush into the wall, the wall push it out.<br>
     */
    @Override
    public synchronized void run(){
        if (this.controller.getState() == Controllable.eState.Working){
            wall.setPositionY(wall.getPositionY()+controller.getWallSpeed());
            pushCoquilleBilleInYIfNeed();
            if(controller.getSpaceSize()[1]-wall.getPositionY()-controller.getWallSpeed() <= 2*controller.getRadiusDot()){
              pushCoquilleBilleInXIfNeed();
            }
            if(wall.getPositionY()>controller.getSpaceSize()[1]){
                cancel();
            }
        }
    }
    /**
     * {@summary push down coquilleBille that will be crush into the wall if they stay here.}<br>
     */
    private void pushCoquilleBilleInYIfNeed(){
      for (CoquilleBille coc : listCoquille ) {
        if(wall.isIntoTheWall(coc)){
          coc.getCurrentPosition().setY(coc.getCurrentPosition().getY()+controller.getWallSpeed());
        }
      }
    }
    /**
     * {@summary push left or rigth coquilleBille that will be crush into the wall if they stay here.}<br>
     * Left or rigth side is choose by searching the smaler path to go out.<br>
     */
    private void pushCoquilleBilleInXIfNeed(){
      for (CoquilleBille coc : listCoquille ) {
        if(wall.isIntoTheWall(coc)){
          // System.out.print("push in x "+coc.getId()+"    x:"+coc.getCurrentPosition().getX());//@a
          if(coc.getCurrentPosition().getX()>wall.getPositionX()){
            coc.getCurrentPosition().setX(wall.getPositionX()+wall.getThickness()+controller.getRadiusDot()+0.01);
          }else{
            coc.getCurrentPosition().setX(wall.getPositionX()-wall.getThickness()-controller.getRadiusDot()-0.01);
          }
          // System.out.println(" x:"+coc.getCurrentPosition().getX());//@a
        }
      }
    }
}
