package org.coronabounce.models;
import org.coronabounce.controllers.Controller;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class Sick extends Individual {

  public Sick(CoquilleBille coc, Population p){
      super(coc,p);
      TimerTask timerTask;
      //become Recovered after p.getDurationCovid()+p.getDurationHealing()
      p.getT().schedule(timerTask=new TimerTask() {
          @Override
          public void run() {
              coc.setIndividual(new Recovered(coc,p));
          }
      },p.getDurationCovid()+p.getDurationHealing());
    }

    public void contact(CoquilleBille coc,Population p) {
        contaminate(coc,p);
    }
    /**
    *A function that transform to Sick an Individual if :
    *<ul>
    *<li> It is a  different Individual.
    *<li> It is close to this.
    *<li> It is a Healthy Individual.
    *</ul>
    */
    public void contaminate(CoquilleBille coc,Population p){
        for(CoquilleBille c : p.getListCoquille()){
            if(!coc.equals(c) && p.distance(coc,c)<= p.getContaminationRadius() && c.getIndividual() instanceof Healthy){
                c.setIndividual(new Incubating(c,p));
                //p.nbSick++; //c'est actualisé dans population maintenant
                //Recovered.recover(coc,p.getDurationHealing(),p.getDurationNonContamination());
            }
        }
    }
    @Override
    public boolean isSick(){return true;}
    @Override
    public void updateState(CoquilleBille coc){
      //TODO if est malade depuis longtemps
      //coc.setIndividual(new Recovered());
    }

}
