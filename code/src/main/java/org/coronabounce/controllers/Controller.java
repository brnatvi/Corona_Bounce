package org.coronabounce.controllers;
import org.coronabounce.models.Population;
import org.coronabounce.models.Position;
import org.coronabounce.models.Zone;
import org.coronabounce.mvcconnectors.Controllable;

public class Controller implements Controllable
{
    Zone z;
    @Override
    public void setSpaceSize(double w, double h)
    {
        Zone.setWidth(w);
        Zone.setHeight(h);
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
      z.getP().setContaminationRadius(pxls);
    }


    @Override
    public double getRadius()
    {
        return z.getP().getContaminationRadius();
    }

    @Override
    public void setDurationCovid(long time)
    {
      z.getP().setDurationCovid(time);
    }

    @Override
    public long getDurationCovid()
    {
        return z.getP().getDurationCovid();
    }

    @Override
    public void setDurationNonContamination(long time)
    {
     z.getP().setDurationNonContamination(time);
    }

    @Override
    public long getDurationNonContamination()
    {
        return z.getP().getDurationNonContamination();
    }
}
