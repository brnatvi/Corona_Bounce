package org.coronabounce.models;

import java.util.Random;


/** Elle sera abstract **/
public   class  Individu {
    private String etatSante;//je propose de mettre une enumération{ Sick,Recovered,Healthy}
    private static Random r=new Random();
    public Individu(String etatSante){
      this.etatSante=etatSante;
   }


    public void setEtatSante(String etatSante) {
        this.etatSante = etatSante;
    }
    public String getEtatSante(){
        return this.etatSante;
    }
    /**********************************************
 On mettra juste
  Individu CreateIndividual(); //Creer un healthy, sick ou recovered person

    public Individu actionOn(Individu x); // L'action (contaminer x ou pas)
     +d'autres éventuelles méthodes....
***************************************************/
}
