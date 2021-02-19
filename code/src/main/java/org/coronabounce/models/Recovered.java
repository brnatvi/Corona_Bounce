package org.coronabounce.models;
import java.util.Random;




public class Recovered extends Individual{


    // La personne encapsulée dans coc retrouve sa santé
    static void recover(CoquilleBille coc , long healing_duration, long durationNonContamination )
    {
        try {
            //aprés le moment de la contamination on appelle la méthode recover
            // qui attend le temps de guerison pour que son etat de santé se modifie
            Thread.sleep(healing_duration);
            coc.setIndividual(new Recovered());
            Healthy.healing(coc,durationNonContamination);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
