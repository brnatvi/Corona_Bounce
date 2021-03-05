package org.coronabounce.models;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class Healthy extends Individual {
   /* @Override
    public void contact(CoquilleBille coc, Population p, long durationCovid, long durationHealing, long durationNonContamination) {
        healing(coc,  durationNonContamination);
    }*/

    public static void healing(CoquilleBille coc, long durationNonContamination)
    {  Timer t=new Timer();
        TimerTask timerTask;
        t.schedule(timerTask=new TimerTask() {
            @Override
            public void run() {
                coc.setIndividual(new Healthy());
                Population.nbHealthy++;
                Population.nbRecovered--;
            }
        },durationNonContamination);

    }

    }


