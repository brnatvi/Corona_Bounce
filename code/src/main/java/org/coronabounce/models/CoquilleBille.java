package org.coronabounce.models;

import java.util.Random;

public class CoquilleBille {
    public int PositionX;
    public int PositionY;
    public double Vitesse_deplacement;
    Individu v;
    public static Random r  =new Random();
    public CoquilleBille(int PosX, int PosY,double Vitesse,Individu v){
        this.PositionX=PosX;
        this.PositionY=PosY;
        this.Vitesse_deplacement=Vitesse;
        this.v=v;
    }
    public void Deplacer(){
        int m=r.nextInt(5);
        int m1=r.nextInt(10);//bound=10 car notre fenetre de population est rectangulaire
        this.Vitesse_deplacement=Math.sqrt( (m*m)+(m1*m1));
        this.PositionX=this.getPositionX()+m1;
        this.PositionY=this.getPositionY()+m;

    }
    public void Recover(long duree_guerison){
        try {
            Thread.sleep(duree_guerison);//aprés le moment de la contamination on appelle la méthode recover qui attend le temps de guerison pour que son etat de santé se modifie
            v.etatSante="Recovered";
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
    public void Contaminate(long duree_contamination,long duree_guerison){
        try {
            Thread.sleep(duree_contamination);//le thread attend un moment pour que la contamination sera faite et aprés on appelle la méthode recover
            v.etatSante="Sick";
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
    public double getVitesse_deplacement() {
        return Vitesse_deplacement;
    }
}
