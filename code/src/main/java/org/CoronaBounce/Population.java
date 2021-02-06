package org.CoronaBounce;

import java.util.ArrayList;

public class Population {
   public  final int nb_individus=20;// j'initialise le nombre des individus que la population possede
    public ArrayList<Individu> liste_individu=new ArrayList<Individu>();
    public static int Pourcentage_guerison;
    public static int Pourcentage_contamination;
    public static int duree_guerison;
    public static int duree_contamination;

    public Population(){

    }
    public void contamination(Individu i1,Individu i2){//i1 contamine i2

    }
    public int Pourcentage_contaminations(){
      return 0;
    }
     public int Pourcentage_guerisons(){
       return 0;
    }




}
