package org.coronabounce.models;

import org.coronabounce.mvcconnectors.Controllable;

import java.util.TimerTask;


public class Recovered extends Individual{

    public Recovered(CoquilleBille coc, Population p){
      super(coc,p);
       p.nbRecovered++;
       p.getTimer().schedule(new TimerTask() {
          @Override
          public void run() {
              if (p.getController().getState() == Controllable.eState.Working) {
                  coc.setIndividual(new Healthy(coc, p));
                  p.nbRecovered--;
              }
          }
       },p.getDurationNonContamination());


    }

}
