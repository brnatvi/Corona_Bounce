package org.coronabounce.models;

public  abstract class Individual {
    protected CoquilleBille coc;
    protected Population population;

    public Individual(CoquilleBille coc, Population p){
        this.population = p;
        this.coc=coc;
    }

    /**
    *{@summary this will return the health state as a string.}
    */
    public String healthState(){
      return getClass().getSimpleName();
    }
    /**
    *{@summary To Override if we need to have an action in contact.}
    */
    public void agitSur(){}

    public boolean isSick(){return false;}

    public void setCoc(CoquilleBille coc){this.coc=coc;}
    public Population getPopulation(){return population;}

}
