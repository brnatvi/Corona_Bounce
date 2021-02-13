package org.coronabounce.models;

import org.coronabounce.mvcconnectors.Displayable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Population implements Displayable{
   public  final int nb_individus=20;// j'initialise le nombre des individus que la population possede
    public ArrayList<CoquilleBille> liste_Coquille=new ArrayList<CoquilleBille>();
    public  double Pourcentage_guerison;
    public  double Pourcentage_contamination;
    public  long duree_guerison;
    public  long duree_contamination;
    public  int nb_Sick=0;//le nombre des individu contaminé
    public  int nb_Healthy=0;//le nombre des individus non contaminés
    public  int nb_Recovered=0;//le nombre des individus guéri
    public  int rayon_contagion;
    private static Random r=new Random();

    public Population(int nbH, int nbS, int nbR){
      for (int i=0;i<nbH ;i++ ) {
        liste_Coquille.add(new CoquilleBille(0,0,0,new Individu("Healthy")));
      }
      for (int i=0;i<nbS ;i++ ) {
        liste_Coquille.add(new CoquilleBille(0,0,0,new Individu("Sick")));
      }
      for (int i=0;i<nbR ;i++ ) {
        liste_Coquille.add(new CoquilleBille(0,0,0,new Individu("Recover")));
      }
    }

    public Population(){
     for(int i=0;i<nb_individus;i++){
      int m=r.nextInt(2);
      if(m==0){
       liste_Coquille.add(new CoquilleBille(0,0,0,new Individu("Healthy")));
       nb_Healthy++;
      }
      else {
       if (m == 1 && nb_Sick < 2) {
        liste_Coquille.add(new CoquilleBille(0,0,0,new Individu("Sick")));
        nb_Sick++;
       }
       else{
        liste_Coquille.add(new CoquilleBille(0,0,0,new Individu("Recover")));
        nb_Recovered++;
       }
      }
     }

    }
    public void afficher_pop(){
     int i=0;
     for(CoquilleBille coc:liste_Coquille){
      System.out.println("Individu num :" +i+ "de position suivante "+coc.getPositionX()+ " et "+coc.getPositionY()+" et de etat de sante "+coc.v.getEtat_sante());
      i++;
     }
     System.out.println("le nombre des personnes contaminées:"+nb_Sick+" de personnes guéries :"+nb_Recovered+ " non contaminées :"+nb_Healthy);
     System.out.println("Pourcentage de contamination: "+this.pourcentage_contaminations()+" %  Pourcentage de guérison :"+this.pourcentage_guerisons()+ "% Pourcentage de non contamination est :"+(100-this.pourcentage_guerisons()-this.pourcentage_contaminations()+" %"));
    }
 public void afficher_deplacement (){
     System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
  int i=0;
  for(CoquilleBille coc:liste_Coquille){
   coc.Deplacer();
   System.out.println("Individu num :" +i+ "de position suivante "+coc.getPositionX()+ " et "+coc.getPositionY()+" et de etat de sante "+coc.v.getEtat_sante());
   i++;
  }
  System.out.println("le nombre des personnes contaminées:"+nb_Sick+" de personnes guéries :"+nb_Recovered+ " non contaminées :"+nb_Healthy);
  System.out.println("Pourcentage de contamination: "+this.pourcentage_contaminations()+" %  Pourcentage de guérison :"+this.pourcentage_guerisons()+ "% Pourcentage de non contamination est :"+(100-this.pourcentage_guerisons()-this.pourcentage_contaminations()+" %"));
 }
  public double distance (CoquilleBille i1,CoquilleBille i2){
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
  public void contamination(CoquilleBille i1,CoquilleBille i2){
         if(distance(i1,i2)<=rayon_contagion){
          i2.Contaminate(duree_contamination,duree_guerison);
         }
  }
  public double pourcentage_contaminations(){
   int cpt=0;
   for(CoquilleBille coc:liste_Coquille){
    if(coc.v.getEtat_sante().compareTo("Sick")==0){
     cpt++;
    }

   }

   nb_Sick=cpt;
    return (cpt*100)/nb_individus;
  }
   public double pourcentage_guerisons(){
    int cpt=0;
    for(CoquilleBille coc:liste_Coquille){
     if(coc.v.getEtat_sante().compareTo("Recovered")==0){
      cpt++;
     }

    }
    nb_Recovered=cpt;
    return (cpt*100)/nb_individus;
  }
  public ArrayList<CoquilleBille> getAllPoints(){
     return liste_Coquille;
  }
 public int getNbIndividus(){
   return nb_individus;
 }
 public int getNbHealthy(){
   return nb_Healthy;
 }
 public int getNbSick(){
   return nb_Sick;
 }
 public int getNbRecovered(){
   return nb_Recovered;
 }



}
