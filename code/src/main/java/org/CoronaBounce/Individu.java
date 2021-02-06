package org.CoronaBounce;

public class Individu {
    public int PositionX;
    public int PositionY;
    public int Vitesse_deplacement;
    public static int rayon_contagion;
    public String etat_sante;//je propose de mettre une enumération{ Sick,Recovered,Healthy}
    public Individu(int PosX,int PosY,int Vitesse,String etat_sante){
        this.PositionX=PosX;
        this.PositionY=PosY;
        this.Vitesse_deplacement=Vitesse;
        this.etat_sante=etat_sante;
    }
    public void Deplacer(int PosX,int PosY,int Vitesse){}
    public void Recover(int duree_guerison){
        etat_sante="Recovered";
    }
    public void Contaminate(int duree_contamination){
        etat_sante="Sick";
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

    public static int getRayon_contagion() {
        return rayon_contagion;
    }

    public int getVitesse_deplacement() {
        return Vitesse_deplacement;
    }
}
