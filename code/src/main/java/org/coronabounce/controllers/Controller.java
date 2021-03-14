package org.coronabounce.controllers;

import org.coronabounce.mvcconnectors.Controllable;

public class Controller implements Controllable
{
    //these constants are initials and will be changed during the changing the settings of program in GUI

    private double WIDTH = 465;                       // population space size (width)
    private double HEIGTH = 290;                      // population space size (height)
    private int COUNT = 20;                           // population size
    private double CONTAMINATION_RADIUS = 5;         // radius of contamination
    private long DURATION_COVID = 5000;               // has contact <-> sick  //TODO compare and converge with parameters in Population
    private long DURATION_HEALING = 10000;            // sick <-> recovered    //TODO compare and converge with parameters in Population
    private long DURATION_NON_CONTAMINATION = 15000;  // recovered <-> can be contaminate again
    private final int RADIUS_DOT = 4;                 // radius of point in GUI

    @Override
    public double[] getSpaceSize() { return new double[]{this.WIDTH, this.HEIGTH}; }

    @Override
    public void setSpaceSize(double w, double h)
    {
        WIDTH=w;
        HEIGTH=h;
    }


    @Override
    public int getPersonsCount() { return this.COUNT; }

    @Override
    public void setPersonsCount(int nmbPersons)
    {
        //TODO bring parameters from GUI settings
        this.COUNT = nmbPersons;
    }


    @Override
    public double getContaminationRadius() { return this.CONTAMINATION_RADIUS; }

    @Override
    public void setContaminationRadius(double pxls)
    {
        //TODO bring parameters from GUI settings
        CONTAMINATION_RADIUS=pxls;
    }


    @Override
    public long getDurationCovid() { return this.DURATION_COVID; }

    @Override
    public void setDurationCovid(long time)
    {
        //TODO bring parameters from GUI settings
        DURATION_COVID=time;
    }


    @Override
    public long getDurationNonContamination() { return this.DURATION_NON_CONTAMINATION; }


    @Override
    public void setDurationNonContamination(long time)
    {
        //TODO bring parameters from GUI settings
        DURATION_NON_CONTAMINATION=time;
    }
    @Override
    public long getDurationHealing(){ return DURATION_HEALING;}
    @Override
    public void setDurationHealing(long l){ }

    @Override
    public double getRadiusDot()
    {
      return this.RADIUS_DOT;
    }

}
