package org.coronabounce.models;

import java.util.Random;

public class CoquilleBille {

    /**
    * A moving speed to move faster or slower. It need to be &#62;0.
    */
    private double movingSpeedX;
    private double movingSpeedY;

    /**
    * A direction were to move between 0° & 359°.
    */
    private int direction;
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


    /**
    * The moving funtion.
    */

    public void move(){
      //TODO update move to use direction.
        Random r1=new Random();
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
        }

        this.p.setPos(this.p.getX()+this.movingSpeedX,this.p.getY()+this.movingSpeedY);
    }

    public double getMovingSpeed() {return Math.sqrt( (this.movingSpeedX*this.movingSpeedX)+(this.movingSpeedY*this.movingSpeedY));}
    public int getDirection() {return direction;}
    public Individual getIndividual(){return individual;}
    public void setIndividual(Individual individual){ this.individual=individual;}
    public Position getPosition(){return this.p;}


}
