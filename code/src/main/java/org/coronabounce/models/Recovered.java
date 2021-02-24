package org.coronabounce.models;
import java.util.Random;




public class Recovered extends Individual{


    // La personne encapsulée dans coc retrouve sa santé
    static void recover(CoquilleBille coc , long healing_duration, long durationNonContamination )
    {
            coc.setIndividual(new Recovered());
    }


}
