package org.coronabounce.models;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class Healthy extends Individual {
    @Override
    public void contact(CoquilleBille coc, Population p) {
        healing(coc,p);
    }

    public  void healing(CoquilleBille coc,Population p)
    {
        TimerTask timerTask;
        t.schedule(timerTask=new TimerTask() {
            @Override
            public void run() {
                coc.setIndividual(new Healthy());
                //Population.nbHealthy++;
                //Population.nbRecovered--;
            }
        },p.getDurationNonContamination());

    }

    }
