package org.coronabounce.models;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class Recovered extends Individual{
    @Override
    public void contact(CoquilleBille coc, Population p) {
        recover(coc,p);
    }

    // La personne encapsulée dans coc retrouve sa santé
     public void recover(CoquilleBille coc ,Population p)
    {
        TimerTask timerTask;
        t.schedule(timerTask=new TimerTask() {
            @Override
            public void run() {
                //coc.setIndividual(new Recovered());
                //Population.nbRecovered++;
                //Population.nbSick--;
            }
        },p.getDurationHealing());


    }


}
