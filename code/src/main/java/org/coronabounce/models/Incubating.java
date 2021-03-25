package org.coronabounce.models;
import org.coronabounce.controllers.Controller;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class Incubating extends Individual {
  public Incubating(CoquilleBille coc, Population p){
    super(coc,p);
    //p.nbHeealthy--;
    TimerTask timerTask;
    //become sick after p.getDurationCovid()
    p.getT().schedule(timerTask=new TimerTask() {
        @Override
        public void run() {
            coc.setIndividual(new Sick(coc,p));
           p.nbHealthy--;
        }
    },p.getDurationCovid());
  }
  @Override
  public boolean isSick(){return true;}
}
