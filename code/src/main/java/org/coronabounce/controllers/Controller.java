package org.coronabounce.controllers;
import org.coronabounce.models.Population;
import org.coronabounce.models.Position;
import org.coronabounce.models.Zone;
import org.coronabounce.mvcconnectors.Controllable;

public class Controller implements Controllable
{
    //these constants are initials and will be changed during the changing the settings of program

    private double WIDTH = 400;                       // population space size (width)
    private double HEIGTH = 250;                      // population space size (height)
    private int COUNT = 20;                           // population size
    private double RADIUS = 10;                       // radius of contamination
    private long DURATION_COVID = 5000;       // has contact <-> sick
    private long HEALING_DURATION = 10000;            // sick <-> recovered
    private long DURATION_NON_CONTAMINATION = 15000;  // recovered <-> can be contaminate again

    Zone z;

    @Override
    public void setSpaceSize(double w, double h)
    {
        //TODO bring parameters from GUI settings
    }

    @Override
    public double[] getSpaceSize() { return new double[]{this.WIDTH, this.HEIGTH}; }


    @Override
    public void setPersonsCount(int nmbPersons)
    {
        //TODO bring parameters from GUI settings
    }

    @Override
    public int getPersonsCount() { return this.COUNT; }

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
        //return z.getP().getDurationCovid();
        return this.DURATION_COVID;
    }

    @Override
    public void setDurationNonContamination(long time)
    {
     z.getP().setDurationNonContamination(time);
    }

    @Override
    public long getDurationNonContamination()
    {
        //return z.getP().getDurationNonContamination();
        return this.DURATION_NON_CONTAMINATION;
    }
}
