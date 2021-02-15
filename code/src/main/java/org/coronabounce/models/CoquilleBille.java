package org.coronabounce.models;

import java.util.Random;

public class CoquilleBille {
    private double PositionX;
    private double PositionY;
    private double movingSpeed;
    private Individu v;
    private static Random r  =new Random();

    public CoquilleBille(double Vitesse,Individu v){
        Position p=new Position();
        this.PositionX=p.getX();
        this.PositionY=p.getY();
        this.movingSpeed=Vitesse;
        this.v=v;
    }
    public void Deplacer(){
        int m=r.nextInt(5);
        int m1=r.nextInt(10);//bound=10 car notre fenetre de population est rectangulaire
        this.movingSpeed=Math.sqrt( (m*m)+(m1*m1));
        this.PositionX=this.getPositionX()+m1;
        this.PositionY=this.getPositionY()+m;

    }


    public double getPositionX() {
        return PositionX;
    }

    public double getPositionY() {
        return PositionY;
    }
    public double getMovingSpeed() {
        return movingSpeed;
    }
    public Individu getV(){
      return v;
    }
}
