package org.coronabounce.models;
import java.util.Random;


public class Sick extends Individual {


    @Override
    public void contact(CoquilleBille coc, long durationCovid, long durationHealing,long durationNonContamination) {
        contaminate(coc,durationCovid,durationHealing,durationNonContamination);
    }
    //La personne malade contamine l'individual encapsulé par coc
    public static  void contaminate(CoquilleBille coc, long durationCovid,long durationHealing,long durationNonContamination)
    {

        try{

            //le thread attend un moment pour que la contamination sera faite et aprés on appelle la méthode recover

            Thread.sleep(durationCovid);
            coc.setIndividual(new Sick());

            // La personne va se rétablir,( fin de la durée de contamination)
            Recovered.recover(coc,durationHealing,durationNonContamination);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }


}
