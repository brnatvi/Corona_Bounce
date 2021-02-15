package org.coronabounce.models;

import org.coronabounce.mvcconnectors.Displayable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Population implements Displayable{
    private int nbIndividus=20;// j'initialise le nombre des individus que la population possede
    private ArrayList<CoquilleBille> listCoquille=new ArrayList<CoquilleBille>();
    private long durationCovid;
    private long durationNonContamination;
    private int contaminationRadius;
    private static Random r=new Random();

    public Population(int nbH, int nbS, int nbR){
      for (int i=0;i<nbH ;i++ ) {

        listCoquille.add(new CoquilleBille(0,new Individu("Healthy")));
      }
      for (int i=0;i<nbS ;i++ ) {
        listCoquille.add(new CoquilleBille(0,new Individu("Sick")));
      }
      for (int i=0;i<nbR ;i++ ) {
        listCoquille.add(new CoquilleBille(0,new Individu("Recover")));
      }
    }

    public Population(int nbIndividus){
      this(nbIndividus-1, 1,0);
    }
    public Population(){
      this(20);
    }
    public void printPop(){
     int i=0;
     for(CoquilleBille coc:listCoquille){
      System.out.println("Individu num :" +i+ "de position suivante "+coc.getPositionX()+ " et "+coc.getPositionY()+" et de etat de sante "+coc.getV().getEtatSante());
      i++;
     }
     System.out.println("le nombre des personnes contaminées:"+getNbSick()+" de personnes guéries :"+getNbRecovered()+ " non contaminées :"+getNbHealthy());
     System.out.println("Pourcentage de contamination: "+this.percentageSick()+" %  Pourcentage de guérison :"+this.percentageRecovered()+ "% Pourcentage de non contamination est :"+(100-this.percentageRecovered()-this.percentageSick()+" %"));
    }
  public void printMovement (){
    System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
    for(CoquilleBille coc:listCoquille){
      coc.Deplacer();
    }
    printPop(); //il faut évité de recopier des morceaux de code et plutot réutilisé les fonctions existantes.
  }
  public double distance (CoquilleBille i1,CoquilleBille i2){
   int x1= i1.getPositionX();
   int x2=i2.getPositionX();
   int y1=i1.getPositionY();
   int y2=i2.getPositionY();
   double dist=Math.sqrt((x1-x2)*(x1-x2)-(y1-y2)*(y1-y2));
   return dist;

  }

  public void contamination(CoquilleBille i1,CoquilleBille i2){
    if(distance(i1,i2)<=contaminationRadius){
      i2.contaminate(durationCovid,durationNonContamination);
    }
  }
  public double percentageSick(){
    return (getNbSick()*100)/getNbIndividus();
  }
  public double percentageRecovered(){
    return (getNbRecovered()*100)/getNbIndividus();
  }
  public double percentageHealthy(){
    return (getNbHealthy()*100)/getNbIndividus();
  }
  public ArrayList<CoquilleBille> getAllPoints(){
     return listCoquille;
  }
  public int getNbIndividus(){
    return getAllPoints().size();
  }
 public int getNbHealthy(){
   int cpt=0;
   for(CoquilleBille coc:listCoquille){
    if(coc.getV().getEtatSante().compareTo("Healthy")==0){
     cpt++;
    }
   }
   return cpt;
 }
 public int getNbSick(){
   int cpt=0;
   for(CoquilleBille coc:listCoquille){
    if(coc.getV().getEtatSante().compareTo("Sick")==0){
     cpt++;
    }
   }
   return cpt;
 }
 public int getNbRecovered(){
   int cpt=0;
   for(CoquilleBille coc:listCoquille){
    if(coc.getV().getEtatSante().compareTo("Recovered")==0){
     cpt++;
    }
   }
   return cpt;
 }

 public long getDurationCovid(){
   return durationCovid;
 }
 public void setDurationCovid(long l){
   durationCovid=l;
 }
 public long getDurationNonContamination(){
   return durationNonContamination;
 }
 public void setDurationNonContamination(long l){
   durationNonContamination=l;
 }

 public void Add_individu(Individu i){
        CoquilleBille coc=new CoquilleBille(0,i);
        listCoquille.add(coc);
        this.nbIndividus++;
 }


}
