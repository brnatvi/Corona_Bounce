package org.coronabounce.models;

import org.coronabounce.mvcconnectors.Controllable;

import java.util.TimerTask;

/**
 * Class which represents a virus carrier.
 * He can contaminate healthy persons, but has not yet any symptoms.
 * He transforms to Sick then duration of incubation expires.
 */
public class Incubating extends Individual{

    public Incubating(CoquilleBille coc, Population p){
        super(coc,p);
        p.nbIncubating++;/**increase the number of incubating individuals**/
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
    public boolean isSick() {return true;}

    /**
     * {@summary Override of Individual's method. Incubating person contaminates healthy persons. }
     */
    public void agitSur() {contaminate();}

    /**
     * A function that transforms an Individual to Incubating if :
     *<ul>
     *<li> It is a different Individual.
     *<li> It is close to this.
     *<li> It is a Healthy Individual.
     *<li> Isn't wall between them.
     *</ul>
     */
    private void contaminate(){
        for(CoquilleBille c : population.getAllPoints()){
             /**if the distance between two shells is less than the contamination radius then the diseased shell transmits the virus to the healthy shell**/
            if(!coc.equals(c) && population.distance(coc,c)<= population.getContaminationRadius()
                    && c.getIndividual() instanceof Healthy && !coc.isWallBetween(c)){
                 /**overwrite the healthy individual that exists in the shell and replace it with incubating individual**/
                c.setIndividual(new Incubating(c, population));
            }
        }
    }
}
