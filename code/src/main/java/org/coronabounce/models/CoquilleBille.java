package org.coronabounce.models;

import java.util.Random;

public class CoquilleBille {
    private double movingSpeed;
    private individual v;
    private Position p;
    private static Random r  =new Random();

    public CoquilleBille(double Vitesse, individual v){
        this.p=new Position();
        this.movingSpeed=Vitesse;
        this.v=v;
    }
    public void Deplacer(){
        int m=r.nextInt(5);
        int m1=r.nextInt(10);//bound=10 car notre fenetre de population est rectangulaire
        this.movingSpeed=Math.sqrt( (m*m)+(m1*m1));
       this.p=new Position(this.p.getX()+m1,this.p.getY()+m);
    }

    public double getMovingSpeed() {
        return movingSpeed;
    }
    public individual getV(){
      return v;
    }
    public Position getPosition(){
        return this.p;
    }
}
