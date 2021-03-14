package org.coronabounce.models;

import org.coronabounce.mvcconnectors.Controllable;
import org.coronabounce.mvcconnectors.Displayable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Population implements Displayable {

    private Controllable controller;
    private List<CoquilleBille> listCoquille = new ArrayList<CoquilleBille>();
    private double contaminationRadius;

    //========================= Constructors ==========================================================================/

    public Population(Controllable controller, int nbH, int nbS, int nbR) {
        this.controller = controller;
        for (int i = 0; i < nbH; i++) {

            listCoquille.add(new CoquilleBille(new Healthy()));

        }
        for (int i = 0; i < nbS; i++) {
            listCoquille.add(new CoquilleBille(new Sick()));

        }
        for (int i = 0; i < nbR; i++) {
            listCoquille.add(new CoquilleBille(new Recovered()));

        }
    }

    public Population(Controllable controller, int nbIndividus) {
        this(controller, nbIndividus - 5, 5, 0);
    }


    public List<CoquilleBille> getAllPoints() {
        return listCoquille;
    }


    public void addIndividual(Individual i) {
        CoquilleBille coc = new CoquilleBille(i);
        listCoquille.add(coc);
    }


    //========================= Virus Getters/Setters==================================================================/

    public long getDurationCovid() {
        return controller.getDurationCovid();
    }
    public void setDurationCovid(long l) {
        controller.setDurationCovid(l);
    }
    public long getDurationNonContamination() {
        return controller.getDurationNonContamination();
    }
    public void setDurationNonContamination(long l) {
        controller.setDurationNonContamination(l);
    }
    public long getDurationHealing() {
        return controller.getDurationHealing();
    }
    public void setDurationHealing(long l) {
        controller.setDurationHealing(l);
    }
    public double getContaminationRadius() {
      return controller.getContaminationRadius();
    }
    public void setContaminationRadius(double d) {
        controller.setContaminationRadius(d);
    }
    public List<CoquilleBille> getListCoquille() {
        return this.listCoquille;
    }


    //========================= Points Interactions ===================================================================/

    public double distance(CoquilleBille i1, CoquilleBille i2) {
        double x1 = i1.getPosition().getX();
        double x2 = i2.getPosition().getX();
        double y1 = i1.getPosition().getY();
        double y2 = i2.getPosition().getY();
        double dist = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        return dist;
    }

    public void interaction(){
        for(CoquilleBille coc:listCoquille){
            coc.getIndividual().contact(coc,this);
        }
    }
    public void updateState(){
        for(CoquilleBille coc:listCoquille){
            coc.getIndividual().updateState(coc);
        }
    }
    //========================= Prints ================================================================================/

    public void printPop() {
        //int i = 0;
        //for (CoquilleBille coc : listCoquille) {
        //    System.out.printf("Individu num : %d de position suivante  %.3f et  %.3f et de etat de sante  %s  Vitesse : %.3f \n", i, coc.getPosition().getX(), coc.getPosition().getY(), coc.getIndividual().healthState(), coc.getMovingSpeed());
        //    i++;
        //}
        int count = getNbSick() + getNbRecovered() + getNbHealthy();
        System.out.println("le nombre des personnes:" + count);
        //System.out.println("le nombre des personnes contaminées:" + getNbSick() + " de personnes guéries :" + getNbRecovered() + " non contaminées :" + getNbHealthy());
        //System.out.println("Pourcentage de contamination: " + this.percentageSick() + " %  Pourcentage de guérison :" + this.percentageRecovered() + "% Pourcentage de non contamination est :" + this.percentageHealthy()+" %");
    }

    public void printMovement() {
        //System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        for (CoquilleBille coc : listCoquille) {
            coc.move();
            //coc.contact(this, durationCovid, healingDuration, durationNonContamination);
        }
        printPop();
    }

    //========================= Population Statistics =================================================================/

   public int getNbIndividus() { return getAllPoints().size(); }

   public int[] countStatistique()
   {
       int health = 0;
       int sick = 0;
       int recover = 0;
       for(CoquilleBille coc : listCoquille)
       {
           if (coc.getIndividual() instanceof Healthy) { health++; }
           if (coc.getIndividual() instanceof Sick) { sick++; }
           if (coc.getIndividual() instanceof Recovered) { recover++; }
       }
       return new int[] {health, sick, recover};
   }

   public int getNbHealthy() { return countStatistique()[0]; }

   public int getNbSick() { return countStatistique()[1]; }

   public int getNbRecovered() { return countStatistique()[2]; }
}
