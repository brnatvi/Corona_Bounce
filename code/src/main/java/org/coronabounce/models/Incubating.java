package org.coronabounce.models;

import java.util.TimerTask;


public class Incubating extends Individual{
  public Incubating(CoquilleBille coc, Population p){
    super(coc,p);
    p.getT().schedule(new TimerTask() {
        @Override
        public void run() {
            coc.setIndividual(new Sick(coc,p));
            p.nbHealthy--;
        }
    }, p.getDurationCovid());
  }
  
  @Override
  public boolean isSick(){return true;}
}
