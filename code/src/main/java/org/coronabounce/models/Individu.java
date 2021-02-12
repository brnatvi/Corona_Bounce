package org.coronabounce.models;

import java.util.Random;

public class Individu {
    public String etat_sante;//je propose de mettre une enumération{ Sick,Recovered,Healthy}
    private static Random r=new Random();
    public Individu(String etat_sante){

        this.etat_sante=etat_sante;
    }


    public void setEtat_sante(String etat_sante) {
        this.etat_sante = etat_sante;
    }

    public String getEtat_sante(){
        return this.etat_sante;
    }


}
