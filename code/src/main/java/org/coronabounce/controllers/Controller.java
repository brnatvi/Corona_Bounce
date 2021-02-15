package org.coronabounce.controllers;
import org.coronabounce.models.Population;
import org.coronabounce.models.Position;
import org.coronabounce.mvcconnectors.Controllable;

public class Controller implements Controllable
{
    Population p;
    @Override
    public void setSpaceSize(double w, double h)
    {
        Position.setWidth(w);
        Position.setHeight(h);
    }

    @Override
    public void setPersonsCount(int nmbPersons)
    {
      //ca me parait un peu difficile a faire. Soit on doit supprimer des CoquilleBilles soit on doit en ajouter.
      //p.getNbIndividus()=nmbPersons;
    }

    @Override
    public void setRadius(double pxls)
    {
      p.setContaminationRadius(pxls);
    }


    @Override
    public double getRadius()
    {
        return p.getContaminationRadius();
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
