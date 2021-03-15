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
    *<li> It is a  different Individual.
    *<li> It is close to this.
    *<li> It is a Healthy Individual.
    *</ul>
    */
    public void contaminate(CoquilleBille coc,Population p){
        for(CoquilleBille c : p.getListCoquille()){
            if(!coc.equals(c) && p.distance(coc,c)<= p.getContaminationRadius() && c.getIndividual() instanceof Healthy){
                c.setIndividual(new Incubating());
                //p.nbSick++; //c'est actualisé dans population maintenant
                //Recovered.recover(coc,p.getDurationHealing(),p.getDurationNonContamination());
                TimerTask timerTask;
                //become sick after p.getDurationCovid()
                t.schedule(timerTask=new TimerTask() {
                    @Override
                    public void run() {
                        c.setIndividual(new Sick());
                    }
                },p.getDurationCovid());
                //become Recovered after p.getDurationCovid()+p.getDurationHealing()
                t.schedule(timerTask=new TimerTask() {
                    @Override
                    public void run() {
                        c.setIndividual(new Recovered());
                    }
                },p.getDurationCovid()+p.getDurationHealing());
            }
        }
    }
    @Override
    public boolean isSick(){return true;}
    @Override
    public void updateState(CoquilleBille coc){
      //TODO if est malade depuis longtemps
      //coc.setIndividual(new Recovered());
    }

}
