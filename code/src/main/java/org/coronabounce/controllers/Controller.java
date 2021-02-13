package org.coronabounce.controllers;
import org.coronabounce.models.Population;
import org.coronabounce.mvcconnectors.Controllable;

public class Controller implements Controllable
{
    Population p;
    @Override
    public void setSpaceSize(int w, int h)
    {

    }

    @Override
    public void setPersonsCount(int nmbPersons)
    {
      p.nb_individus=nmbPersons;
    }

    @Override
    public void setRadius(int pxls)
    {

    }


    @Override
    public int getRadius()
    {
        return 0;
    }

    @Override
    public void setDurationCovid(long  time)
    {
      p.duree_contamination=time;
    }

    @Override
    public long  getDurationCovid()
    {
        return p.duree_contamination;
    }

    @Override
    public void setDurationNonContamination(long time)
    {
     p.duree_guerison=time;
    }

    @Override
    public long getDurationNonContamination()
    {
        return p.duree_guerison;
    }
}
