package org.coronabounce.models;

import java.util.Random;

public  abstract class Individual {

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
