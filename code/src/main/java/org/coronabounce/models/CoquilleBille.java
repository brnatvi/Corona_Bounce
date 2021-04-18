package org.coronabounce.models;

import org.coronabounce.controllers.Controller;
import org.coronabounce.mvcconnectors.Controllable;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Timer;

public class CoquilleBille {

    /**
    * A moving speed in x to move faster or slower.
    */
    private double movingSpeedX;
    /**
    * A moving speed in y to move faster or slower.
    */

    private double movingSpeedY;
    private Individual individual;
    private Position p;
    private final int id;
    private static int idCpt=0;
    private static Random r = new Random();
    private final Position startingPosition;


    public CoquilleBille(double speedX,double speedY, Individual individual){

        this.p=new Position();
        this.startingPosition=new Position(0,0);
        this.startingPosition.setPosX(this.p.getX());
        this.startingPosition.setPosY(this.p.getY());

        id=idCpt++;
        this.movingSpeedX=speedX;
        this.movingSpeedY=speedY;
        this.individual=individual;

    }

    public Population getPop() {
        return individual.getPop();
    }

    public Position getStartingPosition() {
       return startingPosition;
    }


    public CoquilleBille(Individual individual){
      this(getRandomMovingSpeed(5),getRandomMovingSpeed(5),individual);
    }

    public boolean equals(Object o){
      if(o!= null && o instanceof CoquilleBille){
        return getId()==((CoquilleBille)(o)).getId();
      }
      return false;
    }
    public boolean outOfX(double x){
        if(x<=getPop().getRadiusDot() || x>= Controller.getWidth()-getPop().getRadiusDot()){return true;}
        return false;
    }
    /**
     *{@summary Return true if y coordinate is out the the Zone.}
     */
    public boolean outOfY(double y){
        if(y<=getPop().getRadiusDot() || y>= Controller.getHeight()-getPop().getRadiusDot()){return true;}
        return false;
    }
    protected double distancePos() {
        double x1 = this.getPosition().getX();
        double x2 = this.getStartingPosition().getX();
        double y1 = this.getPosition().getY();
        double y2 = this.getStartingPosition().getY();
        return  Math.sqrt((x1 -x2) * (x1 -x2) + (y1 -y2) * (y1 -y2));
    }

    /**
    *{@summary The moving funtion.}<br>
    *Speed will be modify if this hurt a limit of the zone.
    */

    public void move(){
        bounceIfOutOfZone();
        if (getPop().getNbZones() !=1) bounceIfHitWall();
        this.p.setPos(this.p.getX()+this.movingSpeedX,this.p.getY()+this.movingSpeedY);
    }


    /**
    *{@summary bounce if this hit a wall.}<br>
    */
    protected void bounceIfHitWall(){
      for (Wall wall : getPop().getListWall() ) {
        if(wall.willCrossWallInX(this) && wall.willCrossWallInY(this)){
          // System.out.print("from wall "+wall+" ");//@a
          bounce(true);
        }
      }
    }

    /**
    *{@summary bounce if this will go out of the zone.}<br>
    */
    protected void bounceIfOutOfZone(){
      if (outOfX(p.getX()+movingSpeedX)) {
        bounce(true);
      }
      if (outOfY(p.getY()+movingSpeedY)) {
        bounce(false);
      }
    }
    /**
    *{@summary bounce.}<br>
    *@param inX True if bounce in x coor.
    */
    protected void bounce(boolean inX){
      if(inX){ movingSpeedX*=-1;
        // System.out.println(id+" bounce in x : "+(int)(p.getX()+movingSpeedX)+"from"+(int)p.getX());//@a
      }
      else{ movingSpeedY*=-1; }
    }
    protected void bounce(){
         movingSpeedX*=-1;
            // System.out.println(id+" bounce in x : "+(int)(p.getX()+movingSpeedX)+"from"+(int)p.getX());//@a
        movingSpeedY*=-1;
    }

    protected void bounce1(boolean haut, CoquilleBille coc)


    {
        if(coc.getMovingSpeed()!=0) {
            if (haut) {

                {
                    movingSpeedX += 0.5;
                    movingSpeedY += 0.5;

                }
            } else {

                {
                    movingSpeedX -= 0.5;
                    movingSpeedY -= 0.5;
                }
            }
        }}




    public double getMovingSpeed() {return Math.sqrt( (this.movingSpeedX*this.movingSpeedX)+(this.movingSpeedY*this.movingSpeedY));}
    public Individual getIndividual(){return individual;}
    public void setIndividual(Individual individual){ this.individual=individual;}
    public Position getPosition(){return this.p;}
    public int getId(){return id;}
    public void setMovingSpeed(double Vx, double Vy) { this.movingSpeedX=Vx; this.movingSpeedY=Vy; }
    public double getMovingSpeedX() { return this.movingSpeedX;}
    public double getMovingSpeedY() {  return this.movingSpeedY;}
    /**
    *{@summary Return a random moving speed between -maxSpeed & maxSpeed.}<br>
    *@param maxSpeed The max speed that can be return.
    */
    protected static double getRandomMovingSpeed(int maxSpeed){
      if(maxSpeed<1){maxSpeed=1;}
      if(r.nextBoolean()){maxSpeed=maxSpeed*(-1);}
      return r.nextDouble()*maxSpeed;
    }
}
