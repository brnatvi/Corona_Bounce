package org.coronabounce.models;

import java.util.Random;

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
    private static Random r = new Random();


    public CoquilleBille(double speedX,double speedY, Individual individual){
        this.p=new Position();
        this.movingSpeedX=speedX;
        this.movingSpeedY=speedY;
        this.individual=individual;
    }
    public CoquilleBille(Individual individual){
      this(getRandomMovingSpeed(5),getRandomMovingSpeed(5),individual);
    }


    /**
    *{@summary The moving funtion.}<br>
    *Speed will be modify if this hurt a limit of the map.
    */
    public void move(){
        if (Zone.outOfX(p.getX())) {
          movingSpeedX*=-1;
        }
        if (Zone.outOfY(p.getY())) {
          movingSpeedY*=-1;
        }
        this.p.setPos(this.p.getX()+this.movingSpeedX,this.p.getY()+this.movingSpeedY);
    }

    public double getMovingSpeed() {return Math.sqrt( (this.movingSpeedX*this.movingSpeedX)+(this.movingSpeedY*this.movingSpeedY));}
    public Individual getIndividual(){return individual;}
    public void setIndividual(Individual individual){ this.individual=individual;}
    public Position getPosition(){return this.p;}
    /**
    *{@summary Return a random moving speed between -maxSpeed & maxSpeed.}<br>
    *@param maxSpeed The max speed that can be return.
    */
    private static double getRandomMovingSpeed(int maxSpeed){
      if(maxSpeed<1){maxSpeed=1;}
      if(r.nextBoolean()){maxSpeed=maxSpeed*(-1);}
      return r.nextDouble()*maxSpeed;
    }
}
