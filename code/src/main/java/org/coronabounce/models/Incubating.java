package org.coronabounce.models;

import org.coronabounce.mvcconnectors.Controllable;

import java.util.TimerTask;


public class Incubating extends Individual{
  public Incubating(CoquilleBille coc, Population p){
    super(coc,p);
    p.nbIncubating++;/**increase the number of incubating individuals//
    p.nbHealthy--;/**decrease the number of healthy individuals**/
    p.getTimer().schedule(new TimerTask() {/**plan an action that will take place after the incubation period**/
        @Override
        public void run() {
            if (Incubating.super.population.getController().getState() == Controllable.eState.Working){
                coc.setIndividual(new Sick(coc, p)); /**overwrite the Incubating  individual that exists in the shell and replace it with Sick individual**/
                p.nbIncubating--;/**decrease the number of Incubating individuals**/
            }
        }
    }, p.getDurationIncubation());
  }
  
  @Override
  public boolean isSick(){return true;}

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
             /**if the distance between two shells is less than the contamination radius then the diseased shell transmits the virus to the healthy shell**/
            if(!coc.equals(c) && population.distance(coc,c)<= population.getContaminationRadius() && c.getIndividual() instanceof Healthy){
                 /**overwrite the healthy individual that exists in the shell and replace it with incubating individual**/
                c.setIndividual(new Incubating(c, population));
            }
        }
    }
}
