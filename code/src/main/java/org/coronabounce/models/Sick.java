package org.coronabounce.models;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class Sick extends Individual {



    public void contact(CoquilleBille coc,Population p) {
        contaminate(coc,p);
    }
    //La personne malade contamine l'individual encapsulé par coc
    public void contaminate(CoquilleBille coc,Population p)
    {
        for(CoquilleBille c : p.getListCoquille()){
            if( coc!=c && p.distance(coc,c)<= p.getContaminationRadius() && coc.getIndividual().isSick()){
                Timer t=new Timer();
                t.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        coc.setIndividual(new Sick());
                        Population.nbSick++;
                        Population.nbHealthy--;
                    }
                },p.getDurationCovid());
                Recovered.recover(coc,p.getDurationHealing(),p.getDurationNonContamination());
            }
        }
        // La personne va se rétablir,( fin de la durée de contamination
    }
    @Override
    public boolean isSick(){return true;}

}
