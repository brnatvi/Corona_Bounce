package org.coronabounce.models;

/**
 * Person which can produce some action and is not sick by default.
 * It is an abstraction for Healthy, Sick, Incubating and Recovered.
 * Moving capacity of person is attached to Individual by function setCoc(CoquilleBille coc).
 */

public abstract class Individual {
    protected CoquilleBille coc;
    protected Population population;

    public Individual(CoquilleBille coc, Population p){
        this.population = p;
        this.coc=coc;
    }

    /**
    *{@summary Returns the health state as a string.}
    */
    public String healthState(){
      return getClass().getSimpleName();
    }

    /**
    *{@summary To Override if we need to have an action in contact.}
    */
    public void agitSur(){}

    /**
     *{@summary Returns health state of individual. False by default. }
     */
    public boolean isSick(){return false;}

    /**
     *{@summary Set moving part of person (CoquilleBille). }
     */
    public void setCoc(CoquilleBille coc){this.coc=coc;}

    public Population getPopulation(){return population;}

}
