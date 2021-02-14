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
      //ca me parait un peu difficile a faire. Soit on doit supprimer des CoquilleBilles soit on doit en ajouter.
      //p.getNbIndividus()=nmbPersons;
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
    public void setDurationCovid(long time)
    {
      p.setDurationCovid(time);
    }

    @Override
    public long getDurationCovid()
    {
        return p.getDurationCovid();
    }

    @Override
    public void setDurationNonContamination(long time)
    {
     p.setDurationNonContamination(time);
    }

    @Override
    public long getDurationNonContamination()
    {
        return p.getDurationNonContamination();
    }
}
