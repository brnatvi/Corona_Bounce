package org.coronabounce.models;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class Recovered extends Individual{


    // La personne encapsulée dans coc retrouve sa santé
    static void recover(CoquilleBille coc , long healing_duration, long durationNonContamination)
    {     Timer t=new Timer();
    TimerTask timerTask;
        t.schedule(timerTask=new TimerTask() {
            @Override
            public void run() {
                coc.setIndividual(new Recovered());
                Population.nbRecovered++;
                Population.nbSick--;
            }
        },healing_duration);
        Healthy.healing(coc,durationNonContamination);

    }


}
