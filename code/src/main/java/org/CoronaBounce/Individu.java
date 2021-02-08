package org.CoronaBounce;

public class Individu {
    public int PositionX;
    public int PositionY;
    public int Vitesse_deplacement;
    public String etat_sante;//je propose de mettre une enumération{ Sick,Recovered,Healthy}
    public Individu(int PosX,int PosY,int Vitesse,String etat_sante){
        this.PositionX=PosX;
        this.PositionY=PosY;
        this.Vitesse_deplacement=Vitesse;
        this.etat_sante=etat_sante;
    }
    public void Deplacer(int PosX,int PosY,int Vitesse){

    }
    public void Recover(long duree_guerison){
        try {
            Thread.sleep(duree_guerison);//aprés le moment de la contamination on appelle la méthode recover qui attend le temps de guerison pour que son etat de santé se modifie
            etat_sante="Recovered";
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
    public void Contaminate(long duree_contamination,long duree_guerison){
        try {
            Thread.sleep(duree_contamination);//le thread attend une un moment pour que la contamination sera faite et aprés on appelle la méthode recover
            etat_sante="Sick";
            this.Recover( duree_guerison);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void setEtat_sante(String etat_sante) {
        this.etat_sante = etat_sante;
    }

    public int getPositionX() {
        return PositionX;
    }

    public int getPositionY() {
        return PositionY;
    }
    public int getVitesse_deplacement() {
        return Vitesse_deplacement;
    }


}
