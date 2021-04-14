package org.coronabounce.controllers;

import org.coronabounce.mvcconnectors.Controllable;

import java.util.concurrent.locks.ReentrantLock;

public class Controller implements Controllable
{
    //these constants are initials and will be changed during the changing the settings of program in GUI

    private static double WIDTH = 465 ;               // population space size (width)
    private static double HEIGTH = 290 ;              // population space size (height)
    private int COUNT = 30;                           // population size
    private double CONTAMINATION_RADIUS = 10;         // radius of contamination
    private long DURATION_COVID = 3000;               // has contact <-> sick
    private long DURATION_HEALING = 8000;             // sick <-> recovered
    private long DURATION_NON_CONTAMINATION = 5000;   // recovered <-> can be contaminate again
    private double RADIUS_DOT = 3;                 // radius of point in GUI
    private static double Kilometrage = 50;
    private static double diametreX = 15;
    private static double diametreY = 10;
    private static double thickness = 4;
    private Controllable.eState state = Controllable.eState.Idle;
    ReentrantLock statLock = new ReentrantLock();

    //==================================== Timer Management ===========================================================/
    
    @Override
    public Controllable.eState getState()
    {
        statLock.lock();
        Controllable.eState st = state;
        statLock.unlock();
        return st;
    }

    @Override
    public void setState(Controllable.eState newState)
    {
        statLock.lock();
        state = newState;
        statLock.unlock();
    }

    //====================================== Space Settings ===========================================================/

    @Override
    public double[] getSpaceSize() { return new double[]{this.WIDTH, this.HEIGTH}; }

    @Override
    public void setSpaceSize(double w, double h)
    {
        WIDTH=w;
        HEIGTH=h;
    }

    public static double getThickness() {
        return thickness;
    }


    public static double getWidth() { return WIDTH; }

    public static double getHeight() { return HEIGTH; }

    public static void setHeight(double h) { if(h>=1){HEIGTH = h;}}

    public static void setWidth(double w) { if(w>=1){WIDTH = w;}}


    //====================================== Scenarios Settings =======================================================/

    public static double getKilometrage() {
        return Kilometrage;
    }

    public static double getDiametreX() {
        return diametreX;
    }

    public static double getDiametreY() {
        return diametreY;
    }

    //===================================== Population Settings =======================================================/

    @Override
    public int getPersonsCount() { return this.COUNT; }

    @Override
    public void setPersonsCount(int nmbPersons) { this.COUNT = nmbPersons;}

    @Override
    public double getRadiusDot() { return this.RADIUS_DOT; }

    public void setRadiusDot(double x){RADIUS_DOT=x;}

    //========================================= Virus Settings ========================================================/

    @Override
    public double getContaminationRadius() { return this.CONTAMINATION_RADIUS; }

    @Override
    public void setContaminationRadius(double pxls) { CONTAMINATION_RADIUS=pxls; }

    @Override
    public long getDurationCovid() { return this.DURATION_COVID; }

    @Override
    public void setDurationCovid(long time) { DURATION_COVID=time; }

    @Override
    public long getDurationNonContamination() { return this.DURATION_NON_CONTAMINATION; }

    @Override
    public void setDurationNonContamination(long time) { DURATION_NON_CONTAMINATION=time; }

    @Override
    public long getDurationHealing() { return DURATION_HEALING;}

    @Override
    public void setDurationHealing(long time) { DURATION_HEALING = time; }

}
