package org.coronabounce.models;

import java.util.Random;

public  abstract class Individual {

    /**
    *{@summary this will return the health state as a string.}
    */
    public String healthState(){
      return getClass().getSimpleName();
    }
    public abstract void contact (CoquilleBille coc,long durationCovid ,long durationHealing, long durationNonContamination);


    boolean isSick()
    {
        if(this instanceof  Sick) return true;
        return false;
    }

}
