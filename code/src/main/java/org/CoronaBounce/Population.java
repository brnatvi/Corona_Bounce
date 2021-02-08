package org.CoronaBounce;

import java.util.ArrayList;

public class Population {
   public  final int nb_individus=20;// j'initialise le nombre des individus que la population possede
    public ArrayList<Individu> liste_individu=new ArrayList<Individu>();
    public static double Pourcentage_guerison;
    public static double Pourcentage_contamination;
    public static int duree_guerison;
    public static int duree_contamination;
    public static int nb_Sick;//le nombre des individu contaminé
    public static int nb_Healthy;//le nombre des individus non contaminés
    public static int nb_Recovered;//le nombre des individus guéri

    public Population(){

    }
    public void contamination(Individu i1,Individu i2){//i1 contamine i2

    }
    public double Pourcentage_contaminations(){
     int cpt=0;
     for(Individu individu:liste_individu){
      if(individu.etat_sante.compareTo("Sick")==0){
       cpt++;
      }

     }
     nb_Sick=cpt;
      return (cpt/liste_individu.size())*100;
    }
     public double Pourcentage_guerisons(){
      int cpt=0;
      for(Individu individu:liste_individu){
       if(individu.etat_sante.compareTo("Recovered")==0){
        cpt++;
       }

      }
      nb_Recovered=cpt;
      return (cpt/liste_individu.size())*100;
    }




}
