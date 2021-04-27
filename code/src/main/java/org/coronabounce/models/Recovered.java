package org.coronabounce.models;

import org.coronabounce.mvcconnectors.Controllable;

import java.util.TimerTask;


public class Recovered extends Individual{

    public Recovered(CoquilleBille coc, Population p){
      super(coc,p);
       p.nbRecovered++;/**increase the number of Recovered individuals**/
       p.getTimer().schedule(new TimerTask() {/**plan an action that will take place after the immunity period**/
          @Override
          public void run() {
              if (p.getController().getState() == Controllable.eState.Working) {
                  coc.setIndividual(new Healthy(coc, p));/**overwrite the Recovered   individual that exists in the shell and replace it with Healthy individual**/
                  p.nbRecovered--;/**decrease the number of Recovered Individuals**/
              }
          }
       },p.getDurationImmunity());


    }

}
