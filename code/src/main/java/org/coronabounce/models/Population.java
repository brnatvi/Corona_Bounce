package org.coronabounce.models;

import org.coronabounce.mvcconnectors.Displayable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Population implements Displayable {
   public  final int nb_individus=20;// j'initialise le nombre des individus que la population possede
    public ArrayList<Individu> liste_individu=new ArrayList<Individu>();
    public static double Pourcentage_guerison;
    public static double Pourcentage_contamination;
    public static long duree_guerison;
    public static long duree_contamination;
    public static int nb_Sick=0;//le nombre des individu contaminé
    public static int nb_Healthy=0;//le nombre des individus non contaminés
    public static int nb_Recovered=0;//le nombre des individus guéri
    public static int rayon_contagion;
    private static Random r=new Random();

    public Population(int nbH, int nbS, int nbR){
      for (int i=0;i<nbH ;i++ ) {
        liste_individu.add(new Individu(0,0,0,"Healthy"));
      }
      for (int i=0;i<nbS ;i++ ) {
        liste_individu.add(new Individu(0,0,0,"Sick"));
      }
      for (int i=0;i<nbR ;i++ ) {
        liste_individu.add(new Individu(0,0,0,"Recover"));
      }
    }

    public Population(){
     for(int i=0;i<nb_individus;i++){
      int m=r.nextInt(2);
      if(m==0){
       liste_individu.add(new Individu(0,0,0,"Healthy"));
       nb_Healthy++;
      }
      else {
       if (m == 1 && nb_Sick < 2) {
        liste_individu.add(new Individu(0, 0, 0, "Sick"));
        nb_Sick++;
       }
       else{
        liste_individu.add(new Individu(0,0,0,"Recovered"));
        nb_Recovered++;
       }
      }
     }

    }
    public void afficher_pop(){
     int i=0;
     for(Individu individu:liste_individu){
      System.out.println("Individu num :" +i+ "de position suivante "+individu.getPositionX()+ " et "+individu.getPositionY()+" et de etat de sante "+individu.getEtat_sante());
      i++;
     }
     System.out.println("le nombre des personnes contaminées:"+nb_Sick+" de personnes guéries :"+nb_Recovered+ " non contaminées :"+nb_Healthy);
     System.out.println("Pourcentage de contamination: "+this.pourcentage_contaminations()+" %  Pourcentage de guérison :"+this.pourcentage_guerisons()+ "% Pourcentage de non contamination est :"+(100-this.pourcentage_guerisons()-this.pourcentage_contaminations()+" %"));
    }
 public void afficher_deplacement (){
     System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
  int i=0;
  for(Individu individu:liste_individu){
   individu.Deplacer();
   System.out.println("Individu num :" +i+ "de position suivante "+individu.getPositionX()+ " et "+individu.getPositionY()+" et de etat de sante "+individu.getEtat_sante());
   i++;
  }
  System.out.println("le nombre des personnes contaminées:"+nb_Sick+" de personnes guéries :"+nb_Recovered+ " non contaminées :"+nb_Healthy);
  System.out.println("Pourcentage de contamination: "+this.pourcentage_contaminations()+" %  Pourcentage de guérison :"+this.pourcentage_guerisons()+ "% Pourcentage de non contamination est :"+(100-this.pourcentage_guerisons()-this.pourcentage_contaminations()+" %"));
 }
  public double distance (Individu i1,Individu i2){
   int x1= i1.getPositionX();
   int x2=i2.getPositionX();
   int y1=i1.getPositionY();
   int y2=i2.getPositionY();
   double dist=Math.sqrt((x1-x2)*(x1-x2)-(y1-y2)*(y1-y2));
   return dist;

  }
  /*public void parcours(){
   for(int i=0;i<nb_individus;i++){
     for(int j=0;j<nb_individus;j++){

     }
   }
  }*/
  public void contamination(Individu i1,Individu i2){//i1 contamine i2
         if(distance(i1,i2)<=rayon_contagion){
          i2.Contaminate(duree_contamination,duree_guerison);
         }
  }
  public double pourcentage_contaminations(){
   int cpt=0;
   for(Individu individu:liste_individu){
    if(individu.getEtat_sante().compareTo("Sick")==0){
     cpt++;
    }

   }

   nb_Sick=cpt;
    return (cpt*100)/nb_individus;
  }
   public double pourcentage_guerisons(){
    int cpt=0;
    for(Individu individu:liste_individu){
     if(individu.getEtat_sante().compareTo("Recovered")==0){
      cpt++;
     }

    }
    nb_Recovered=cpt;
    return (cpt*100)/nb_individus;
  }
  public ArrayList<Individu> getAllPoints(){
     return liste_individu;
  }
  public int getNmbPoints(){
     return nb_individus;
  }
  public int getNmbHealthy(){
     return nb_Healthy;
  }
  public int getNmbContagious(){
     return nb_Sick;
  }
  public int getNmbRecovered(){
     return nb_Recovered;
  }



}
