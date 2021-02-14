package org.coronabounce.models;

import java.util.Random;

public class CoquilleBille {
    private int PositionX;
    private int PositionY;
    private double movingSpeed;
    private Individu v;
    private static Random r  =new Random();

    public CoquilleBille(int PosX, int PosY,double Vitesse,Individu v){
        this.PositionX=PosX;
        this.PositionY=PosY;
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
    public void Recover(long duree_guerison){
        try {
            Thread.sleep(duree_guerison);//aprés le moment de la contamination on appelle la méthode recover qui attend le temps de guerison pour que son etat de santé se modifie
            v.setEtatSante("Recovered");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
    public void Contaminate(long duree_contamination,long duree_guerison){
        try {
            Thread.sleep(duree_contamination);//le thread attend un moment pour que la contamination sera faite et aprés on appelle la méthode recover
            v.setEtatSante("Sick");
            this.Recover( duree_guerison);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public int getPositionX() {
        return PositionX;
    }

    public int getPositionY() {
        return PositionY;
    }
    public double getMovingSpeed() {
        return movingSpeed;
    }
    public Individu getV(){
      return v;
    }
}
