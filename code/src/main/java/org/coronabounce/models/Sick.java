package org.coronabounce.models;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class Sick extends Individual {


    @Override
    public void contact(Population p, long durationCovid, long durationHealing,long durationNonContamination) {
        contaminate(p,durationCovid);
    }
    //La personne malade contamine l'individual encapsulé par coc
    public static  void contaminate( Population p, long durationCovid)
    {
        for(CoquilleBille coc: p.getListCoquille()){
            for(CoquilleBille c : p.getListCoquille()){
                if(coc !=c && p.distance(coc,c)<= p.getContaminationRadius() && coc.getIndividual().isSick()){
                    Timer t=new Timer();
                    t.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            coc.setIndividual(new Sick());
                        }
                    },durationCovid);
                }
            }
        }


            // La personne va se rétablir,( fin de la durée de contamination
    }


}
