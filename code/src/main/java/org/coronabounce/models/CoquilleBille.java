package org.coronabounce.models;

import java.util.Random;

public class CoquilleBille {
    /**
    * A moving speed to move faster or slower. It need to be &#62;0.
    */
    private double movingSpeed;
    /**
    * A direction were to move between 0° & 359°.
    */
    private int direction;
    private Individual individual;
    private Position p;
    private static Random r = new Random();

    public CoquilleBille(double speed, Individual individual){
        this.p=new Position();
        this.movingSpeed=speed;
        this.individual=individual;
        //this.direction=r.randInt(360);
    }
    /**
    * The moving funtion.
    */
    public void Move(){
      //TODO update move to use direction.
        int m=r.nextInt(5);
        int m1=r.nextInt(10);//bound=10 car notre fenetre de population est rectangulaire
        this.movingSpeed=this.movingSpeed+Math.sqrt( (m*m)+(m1*m1));
        //this.p=new Position(this.p.getX()+m1,this.p.getY()+m);
        this.p.setPos(this.p.getX()+m1,this.p.getY()+m);
    }

    public double getMovingSpeed() {return movingSpeed;}
    public int getDirection() {return direction;}
    public Individual getIndividual(){return individual;}
    public void setIndividual(Individual individual){ this.individual=individual;}
    public Position getPosition(){return this.p;}


}
