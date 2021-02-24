package org.coronabounce.models;
import java.util.Random;




public class Healthy extends Individual {

    static void healing(CoquilleBille coc, long durationNonContamination)
    {
            coc.setIndividual(new Healthy());
    }

}
