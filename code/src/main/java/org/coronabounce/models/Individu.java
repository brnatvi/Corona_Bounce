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
    public void Recover(long duree_guerison){
        try {
            Thread.sleep(duree_guerison);//aprés le moment de la contamination on appelle la méthode recover qui attend le temps de guerison pour que son etat de santé se modifie
            this.setEtatSante("Recovered");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
    public void Contaminate(long duree_contamination,long duree_guerison){
        try {
            Thread.sleep(duree_contamination);//le thread attend un moment pour que la contamination sera faite et aprés on appelle la méthode recover
            this.setEtatSante("Sick");
            this.Recover( duree_guerison);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    /**********************************************
 On mettra juste
  Individu CreateIndividual(); //Creer un healthy, sick ou recovered person

    public Individu actionOn(Individu x); // L'action (contaminer x ou pas)
     +d'autres éventuelles méthodes....
***************************************************/
}
