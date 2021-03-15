package org.coronabounce.models;
import org.coronabounce.controllers.Controller;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class Sick extends Individual {



    public void contact(CoquilleBille coc,Population p) {
        contaminate(coc,p);
    }
    /**
    *A function that transform to Sick an Individual if :
    *<ul>
    *<li> It is a different Individual.
    *<li> It is close to this.
    *<li> It is a Healthy Individual.
    *</ul>
    *It will update nbSick and nbHealthy Population value.
    */
    public void contaminate(CoquilleBille coc,Population p){
        for(CoquilleBille c : p.getListCoquille()){
            if(!coc.equals(c) && p.distance(coc,c)<= p.getContaminationRadius() && c.getIndividual() instanceof Healthy){
                //c.setIndividual(new Sick());
                //p.nbSick++; //c'est actualisé dans population maintenant
                //p.nbHealthy--;
                //Recovered.recover(coc,p.getDurationHealing(),p.getDurationNonContamination());
                TimerTask timerTask;
                t.schedule(timerTask=new TimerTask() {
                    @Override
                    public void run() {
                        c.setIndividual(new Sick());
                        //Population.nbRecovered++;
                        //Population.nbSick--;
                    }
                },p.getDurationCovid());
                c.setIndividual(new Recovered());
            }
        }
        // La personne va se rétablir,( fin de la durée de contamination
    }
    @Override
    public boolean isSick(){return true;}
    @Override
    public void updateState(CoquilleBille coc){
      //TODO if est malade depuis longtemps
      //coc.setIndividual(new Recovered());
    }

}
