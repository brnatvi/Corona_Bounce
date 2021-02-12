package org.coronabounce.models;

import org.coronabounce.mvcconnectors.Displayable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Population implements Displayable {
    //public final int getNbIndividus()=20;// j'initialise le nombre des individus que la population possede
    private ArrayList<Individu> liste_individu=new ArrayList<Individu>();
    private double Pourcentage_guerison;
    private double Pourcentage_contamination;
    private long duree_guerison;
    private long duree_contamination;
    //public static int getNbSick()=0;//le nombre des individu contaminé
    //public static int getNbHealthy()=0;//le nombre des individus non contaminés
    //public static int getNbRecovered()=0;//le nombre des individus guéri
    private int rayon_contagion;
    private static Random r=new Random();

    // CONSTRUCTORS --------------------------------------------------------------
    /**
    * {@summary Main constructor for Population.}<br>
    * All the other constructor use it. <br>
    */
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
    /**
    * {@summary constructor for Population that need 1 args, number of Individu.}<br>
    * We always starts with 1 sick and nbIndividus-1 healthy.
    */
    public Population(int nbIndividus){
      this(nbIndividus-1,1,0);
     /*for(int i=0;i<nbIndividus;i++){
      int m=r.nextInt(2);
      if(m==0){
       liste_individu.add(new Individu(0,0,0,"Healthy"));
       nb_Healthy++;
      }
      else {
       if (m == 1 && getNbSick() < 2) {
        liste_individu.add(new Individu(0, 0, 0, "Sick"));
        nb_Sick++;
       }
       else{
        liste_individu.add(new Individu(0,0,0,"Recovered"));
        nb_Recovered++;
       }
      }
    }*/
    }
    /**
    * {@summary default constructor for Population, 1 sick and 19 healthy.} <br>
    */
    public Population(){
      this(20);
    }
    // GET SET -------------------------------------------------------------------
    public ArrayList<Individu> getAllPoints(){
       return liste_individu;
    }
    // FUNCTIONS -----------------------------------------------------------------
    public void afficher_pop(){
     int i=0;
     for(Individu individu:liste_individu){
      System.out.println("Individu num :" +i+ "de position suivante "+individu.getPositionX()+ " et "+individu.getPositionY()+" et de etat de sante "+individu.getEtat_sante());
      i++;
     }
     System.out.println("le nombre des personnes contaminées:"+getNbSick()+" de personnes guéries :"+getNbRecovered()+ " non contaminées :"+getNbHealthy());
     System.out.println("Pourcentage de contamination: "+this.percentageSick()+" %  Pourcentage de guérison :"+this.percentageRecovered()+ "% Pourcentage de non contamination est :"+(100-this.percentageRecovered()-this.percentageSick()+" %"));
    }
 public void afficher_deplacement (){
     System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
  int i=0;
  for(Individu individu:liste_individu){
   individu.Deplacer();
   System.out.println("Individu num :" +i+ "de position suivante "+individu.getPositionX()+ " et "+individu.getPositionY()+" et de etat de sante "+individu.getEtat_sante());
   i++;
  }
  System.out.println("le nombre des personnes contaminées:"+getNbSick()+" de personnes guéries :"+getNbRecovered()+ " non contaminées :"+getNbHealthy());
  System.out.println("Pourcentage de contamination: "+this.percentageSick()+" %  Pourcentage de guérison :"+this.percentageRecovered()+ "% Pourcentage de non contamination est :"+(100-this.percentageRecovered()-this.percentageSick()+" %"));
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
   for(int i=0;i<getNbIndividus();i++){
     for(int j=0;j<getNbIndividus();j++){

     }
   }
  }*/
  public void contamination(Individu i1,Individu i2){//i1 contamine i2
         if(distance(i1,i2)<=rayon_contagion){
          i2.Contaminate(duree_contamination,duree_guerison);
         }
  }
  // methode about Population statistics.
  public double percentageHealthy(){
    return (getNbHealthy()*100)/getNbIndividus();
  }
  public double percentageSick(){
    return (getNbSick()*100)/getNbIndividus();
  }
  public double percentageRecovered(){
    return (getNbRecovered()*100)/getNbIndividus();
  }
  public int getNbIndividus(){
     return getAllPoints().size();
  }
  public int getNbHealthy(){
    int cpt=0;
    for(Individu individu:liste_individu){
      if(individu.getEtat_sante().compareTo("Healthy")==0){
        cpt++;
      }
    }
    return cpt;
  }
  public int getNbSick(){
    int cpt=0;
    for(Individu individu:liste_individu){
      if(individu.getEtat_sante().compareTo("Sick")==0){
        cpt++;
      }
    }
    return cpt;
  }
  public int getNbRecovered(){
    int cpt=0;
    for(Individu individu:liste_individu){
      if(individu.getEtat_sante().compareTo("Recovered")==0){
        cpt++;
      }
    }
    return cpt;
  }


}
