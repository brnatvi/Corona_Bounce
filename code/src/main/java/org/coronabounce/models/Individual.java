package org.coronabounce.models;

import java.util.Random;

public  abstract class Individual {

    public abstract void contact (CoquilleBille coc,long durationCovid , long durationNonContamination);


    boolean isSick()
    {
        if(this instanceof  Sick) return true;
        return false;
    }
// Ce sera juste utilisé lors de l'affichage des statistiques de la population ( void printPop)

    String healthState()
    {
        if(this instanceof  Sick) return "sick";
        else if(this instanceof  Healthy) return "healthy";
        return "recovered";

    }


}