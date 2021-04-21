package org.coronabounce.models;

import org.coronabounce.mvcconnectors.Controllable;

import java.util.TimerTask;


public class Incubating extends Individual{
  public Incubating(CoquilleBille coc, Population p){
    super(coc,p);
    p.nbIncubating++;
    p.nbHealthy--;
    p.getTimer().schedule(new TimerTask() {
        @Override
        public void run() {
            if (Incubating.super.p.getController().getState() == Controllable.eState.Working){
                coc.setIndividual(new Sick(coc, p));
                p.nbIncubating--;
            }
        }
    }, p.getDurationCovid());
  }
  
  @Override
  public boolean isSick(){return true;}
}
