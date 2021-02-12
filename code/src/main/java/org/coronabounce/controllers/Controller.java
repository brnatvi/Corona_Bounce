package org.coronabounce.controllers;
import org.coronabounce.models.Population;
import org.coronabounce.mvcconnectors.Controllable;

public class Controller implements Controllable
{
    @Override
    public void setSpaceSize(int w, int h)
    {

    }

    @Override
    public void setPersonsCount(int nmbPersons)
    {

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
    public void setDurationCovid(int time)
    {

    }

    @Override
    public int getDurationCovid()
    {
        return 0;
    }

    @Override
    public void setDurationNonContamination(int time)
    {

    }

    @Override
    public int getDurationNonContamination()
    {
        return 0;
    }
}
