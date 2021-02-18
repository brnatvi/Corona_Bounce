package org.coronabounce.models;
import java.util.Random;




public class Recovered extends Individual{



    public void contact(CoquilleBille coc, long durationCovid, long durationNonContamination)
    {}
    // La personne encapsulée dans coc retrouve sa santé
    static void recover(CoquilleBille coc , long durationNonContamination)
    {


        try {

            //aprés le moment de la contamination on appelle la méthode recover
            // qui attend le temps de guerison pour que son etat de santé se modifie
            Thread.sleep(durationNonContamination);

            coc.setIndividual(new Recovered());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


}