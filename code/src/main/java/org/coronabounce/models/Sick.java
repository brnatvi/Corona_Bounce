package org.coronabounce.models;

import org.coronabounce.mvcconnectors.Controllable;

import java.util.TimerTask;


public class Sick extends Individual {

  public Sick(CoquilleBille coc, Population p){
      super(coc,p);
      p.nbSick++;
      p.getTimer().schedule(new TimerTask()
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
      },p.getDurationHealing());
  }
  

    public void agitSur() {
        contaminate();
    }
    /**
    *A function that transform to Incubating an Individual if :
    *<ul>
    *<li> It is a  different Individual.
    *<li> It is close to this.
    *<li> It is a Healthy Individual.
    *</ul>
    */
    private void contaminate(){
        for(CoquilleBille c : population.getAllPoints()){
            if(!coc.equals(c) && population.distance(coc,c)<= population.getContaminationRadius() && c.getIndividual() instanceof Healthy){
                c.setIndividual(new Incubating(c, population));
            }
        }
    }
    @Override
    public boolean isSick(){return true;}

}
