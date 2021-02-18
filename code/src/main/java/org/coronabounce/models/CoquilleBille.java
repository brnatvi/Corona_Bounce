package org.coronabounce.models;

import java.util.Random;

public class CoquilleBille {
    private double movingSpeed;
    private Individual v;
    private Position p;
    private static Random r  =new Random();

    public CoquilleBille(double Vitesse, Individual v){
        this.p=new Position();
        this.movingSpeed=Vitesse;
        this.v=v;
    }
    public void Deplacer(){
        int m=r.nextInt(5);
        int m1=r.nextInt(10);//bound=10 car notre fenetre de population est rectangulaire
        this.movingSpeed=this.movingSpeed+Math.sqrt( (m*m)+(m1*m1));
        //this.p=new Position(this.p.getX()+m1,this.p.getY()+m);
        this.p.setPos(this.p.getX()+m1,this.p.getY()+m);
    }

    public double getMovingSpeed() {
        return movingSpeed;
    }
    public Individual getV(){
        return v;
    }
    public void setIndividual(Individual v){ this.v=v;}
    public Position getPosition(){
        return this.p;
    }


}