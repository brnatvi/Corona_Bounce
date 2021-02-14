package org.coronabounce.models;

import org.coronabounce.mvcconnectors.Displayable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Population implements Displayable{
    public  int nb_individus=20;// j'initialise le nombre des individus que la population possede
    public ArrayList<CoquilleBille> listCoquille=new ArrayList<CoquilleBille>();
    public  long duree_guerison;
    public  long duree_contamination;
    public  int nb_Sick=0;//le nombre des individu contaminé
    public  int nb_Healthy=0;//le nombre des individus non contaminés
    public  int nb_Recovered=0;//le nombre des individus guéri
    public  int rayon_contagion;
    private int x,  y;
    private Position s = new Position(0,0);
    private static Random r=new Random();

    public Population(int nbH, int nbS, int nbR){
      for (int i=0;i<nbH ;i++ ) {

          x=s.randomPos().getX();
          y=s.randomPos().getY();

        listCoquille.add(new CoquilleBille(x,y,0,new Individu("Healthy")));
      }
      for (int i=0;i<nbS ;i++ ) {

          x=s.randomPos().getX();
          y=s.randomPos().getY();
        listCoquille.add(new CoquilleBille(x,y,0,new Individu("Sick")));
      }
      for (int i=0;i<nbR ;i++ ) {
          x=s.randomPos().getX();
          y=s.randomPos().getY();
        listCoquille.add(new CoquilleBille(x,y,0,new Individu("Recover")));
      }
    }

    public Population(){
     for(int i=0;i<nb_individus;i++){
      int m=r.nextInt(2);
      if(m==0){
          x=s.randomPos().getX();
          y=s.randomPos().getY();

       listCoquille.add(new CoquilleBille(x,y,0,new Individu("Healthy")));
       nb_Healthy++;
      }
      else {
       if (m == 1 && nb_Sick < 2) {
           x=s.randomPos().getX();
           y=s.randomPos().getY();
        listCoquille.add(new CoquilleBille(x,y,0,new Individu("Sick")));
        nb_Sick++;
       }
       else{
           x=s.randomPos().getX();
           y=s.randomPos().getY();
        listCoquille.add(new CoquilleBille(x,y,0,new Individu("Recover")));
        nb_Recovered++;
       }
      }
     }

    }
    public void printPop(){
     int i=0;
     for(CoquilleBille coc:listCoquille){
      System.out.println("Individu num :" +i+ "de position suivante "+coc.getPositionX()+ " et "+coc.getPositionY()+" et de etat de sante "+coc.v.getEtatSante());
      i++;
     }
     System.out.println("le nombre des personnes contaminées:"+nb_Sick+" de personnes guéries :"+nb_Recovered+ " non contaminées :"+nb_Healthy);
     System.out.println("Pourcentage de contamination: "+this.percentageSick()+" %  Pourcentage de guérison :"+this.percentageRecovered()+ "% Pourcentage de non contamination est :"+(100-this.percentageRecovered()-this.percentageSick()+" %"));
    }
 public void printMovement (){
     System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
  int i=0;
  for(CoquilleBille coc:listCoquille){
   coc.Deplacer();
   System.out.println("Individu num :" +i+ "de position suivante "+coc.getPositionX()+ " et "+coc.getPositionY()+" et de etat de sante "+coc.v.getEtatSante());
   i++;
  }
  System.out.println("le nombre des personnes contaminées:"+nb_Sick+" de personnes guéries :"+nb_Recovered+ " non contaminées :"+nb_Healthy);
  System.out.println("Pourcentage de contamination: "+this.percentageSick()+" %  Pourcentage de guérison :"+this.percentageRecovered()+ "% Pourcentage de non contamination est :"+(100-this.percentageRecovered()-this.percentageSick()+" %"));
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
  public double percentageSick(){
   int cpt=0;
   for(CoquilleBille coc:listCoquille){
    if(coc.v.getEtatSante().compareTo("Sick")==0){
     cpt++;
    }

   }

   nb_Sick=cpt;
    return (cpt*100)/nb_individus;
  }
   public double percentageRecovered(){
    int cpt=0;
    for(CoquilleBille coc:listCoquille){
     if(coc.v.getEtatSante().compareTo("Recovered")==0){
      cpt++;
     }

    }
    nb_Recovered=cpt;
    return (cpt*100)/nb_individus;
  }
  public ArrayList<CoquilleBille> getAllPoints(){
     return listCoquille;
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
