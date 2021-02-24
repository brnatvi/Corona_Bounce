package org.coronabounce.models;

import org.coronabounce.mvcconnectors.Displayable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Population implements Displayable{
    private List<CoquilleBille> listCoquille=new ArrayList<CoquilleBille>();
    private long durationCovid;// cobien dure la maldaie
    private long durationNonContamination;//pour passer de Recovered a healthy
    private long durationHealing;//combien dure la guerison ex:14 jours

    private double contaminationRadius;

    //========================= Constructors ==========================================================================/

    public Population(int nbH, int nbS, int nbR){
        for (int i=0;i<nbH ;i++ ) {

            listCoquille.add(new CoquilleBille(0,0,new Healthy()));
        }
        for (int i=0;i<nbS ;i++ ) {
            listCoquille.add(new CoquilleBille(0,0,new Sick()));
        }
        for (int i=0;i<nbR ;i++ ) {
            listCoquille.add(new CoquilleBille(0,0,new Recovered()));
        }
    }

    public Population(int nbIndividus){
        this(nbIndividus-1, 1,0);
    }


    public List<CoquilleBille> getAllPoints(){ return listCoquille; }



    public void addIndividual(Individual i){
        CoquilleBille coc=new CoquilleBille(0,0,i);
        listCoquille.add(coc);
    }



    //========================= Virus Getters/Setters==================================================================/

    public long getDurationCovid(){ return durationCovid; }

    public void setDurationCovid(long l){ durationCovid=l; }

    public long getDurationNonContamination(){ return durationNonContamination; }

    public void setDurationNonContamination(long l){ durationNonContamination=l; }

    public void setContaminationRadius(double contaminationRadius){ this.contaminationRadius = contaminationRadius; }

    public double getContaminationRadius() { return contaminationRadius; }
    public List<CoquilleBille> getListCoquille(){
        return this.listCoquille;
    }


    //========================= Points Interactions ===================================================================/

    public double distance (CoquilleBille i1,CoquilleBille i2){
     double x1= i1.getPosition().getX();
     double x2=i2.getPosition().getX();
     double y1=i1.getPosition().getY();
     double y2=i2.getPosition().getY();
     double dist=Math.sqrt((x1-x2)*(x1-x2)-(y1-y2)*(y1-y2));
     return dist;
    }

    public void interaction( CoquilleBille i1, CoquilleBille i2)
    {
        if(i1.getIndividual().isSick() && distance(i1, i2) <= contaminationRadius) {

            i1.getIndividual().contact(i2,durationCovid,durationHealing,durationNonContamination);
        }
    }

    //========================= Prints ================================================================================/

    public void printPop(){
        int i=0;
        for(CoquilleBille coc:listCoquille){
            System.out.println("Individu num :" +i+ "de position suivante "+coc.getPosition().getX()+ " et "+coc.getPosition().getY()+" et de etat de sante "+coc.getIndividual().healthState()+ " Vitesse : "+coc.getMovingSpeed());
            i++;
        }
        System.out.println("le nombre des personnes contaminées:"+getNbSick()+" de personnes guéries :"+getNbRecovered()+ " non contaminées :"+getNbHealthy());
        System.out.println("Pourcentage de contamination: "+this.percentageSick()+" %  Pourcentage de guérison :"+this.percentageRecovered()+ "% Pourcentage de non contamination est :"+(100-this.percentageRecovered()-this.percentageSick()+" %"));
    }

    public void printMovement (){
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        for(CoquilleBille coc:listCoquille){
            coc.Move();
        }
        printPop();
    }

    //========================= Population Statistics =================================================================/

    public int getNbIndividus() { return getAllPoints().size(); }
    public double percentageSick() { return (getNbSick()*100)/getNbIndividus(); }
    public double percentageRecovered() { return (getNbRecovered()*100)/getNbIndividus(); }
    public double percentageHealthy() { return (getNbHealthy()*100)/getNbIndividus(); }

    public int getNbHealthy() {
        int cpt=0;
        for(CoquilleBille coc : listCoquille)
        {
            if(coc.getIndividual() instanceof Healthy)
            {
                cpt++;
            }
        }
        return cpt;
    }

    public int getNbSick() {
        int cpt=0;
        for(CoquilleBille coc : listCoquille)
        {
            if(coc.getIndividual() instanceof Sick)
            {
                cpt++;
            }
        }
        return cpt;
    }



    public int getNbRecovered() {
        int cpt=0;
        for(CoquilleBille coc : listCoquille)
        {
            if(coc.getIndividual() instanceof Recovered)
            {
                cpt++;
            }
        }
        return cpt;
     }

}
