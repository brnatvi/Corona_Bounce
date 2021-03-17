package org.coronabounce.models;

import java.util.Random;
import java.util.Timer;

public  abstract class Individual {
    private CoquilleBille coc;
    private Population p;

    public Individual(CoquilleBille coc, Population p){
      this.coc=coc;
      this.p=p;
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
    public void contact (CoquilleBille coc,Population p){}

    public void updateState (CoquilleBille coc){}

    public boolean isSick(){return false;}

}
