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

}
