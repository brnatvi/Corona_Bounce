package org.coronabounce.models;

import org.coronabounce.mvcconnectors.Controllable;

import java.util.TimerTask;


public class Sick extends Individual {

  public Sick(CoquilleBille coc, Population p){
      super(coc,p);
      p.nbSick++;
      p.nbIncubating--;
      p.getT().schedule(new TimerTask()
      {
          @Override
          public void run()
          {
              if (p.getController().getState() == Controllable.eState.Working)
              {
                  coc.setIndividual(new Recovered(coc, p));
                  p.nbSick--;
              }
          }
      },p.getDurationCovid()+p.getDurationHealing());
  }

    public void run() {
        
    }


    public void agitSur() {
        contaminate();
    }
    /**
    *A function that transform to Sick an Individual if :
    *<ul>
    *<li> It is a  different Individual.
    *<li> It is close to this.
    *<li> It is a Healthy Individual.
    *</ul>
    */
    private void contaminate(){
        for(CoquilleBille c : p.getListCoquille()){
            if(!coc.equals(c) && p.distance(coc,c)<= p.getContaminationRadius() && c.getIndividual() instanceof Healthy){
                c.setIndividual(new Incubating(c,p));
            }
        }
    }
    @Override
    public boolean isSick(){return true;}

}
