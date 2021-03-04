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
        //this.direction=r.randInt(360);
    }
    public CoquilleBille(Individual individual){
      this(getRandomMovingSpeed(5),getRandomMovingSpeed(5),individual);
    }


    /**
    * The moving funtion.
    */

    public void move(){
      //TODO update move to use direction.
        /*Random r1=new Random();
        int m0=r.nextInt(5)+1;
        this.movingSpeedX=r1.nextDouble()*m0;
        this.movingSpeedY=r1.nextDouble()*m0;
        Boolean bool1= r1.nextBoolean();
        Boolean bool2=r1.nextBoolean();
        if(bool1){
            this.movingSpeedX= -this.movingSpeedX ;
        }
        if (bool2) {
            this.movingSpeedY= -this.movingSpeedY;
        }*/
        movingSpeedX=getRandomMovingSpeed(5);
        movingSpeedY=getRandomMovingSpeed(5);
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
