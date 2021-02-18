package org.coronabounce.models;
import java.util.Random;




public class Healthy extends Individual {

    @Override
    public void contact(CoquilleBille coc, long durationCovid,long durationHealing, long durationNonContamination)
    {}
    static void healing(CoquilleBille coc, long durationNonContamination)
    {
        try{

            //le thread attend un moment pour que la contamination sera faite et aprés on appelle la méthode recover

            Thread.sleep(durationNonContamination);
            coc.setIndividual(new Healthy());


        } catch (Exception e) {
            e.printStackTrace();
        }



    }

}
