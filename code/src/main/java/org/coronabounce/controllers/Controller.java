package org.coronabounce.controllers;

import org.coronabounce.mvcconnectors.Controllable;

import java.util.concurrent.locks.ReentrantLock;

public class Controller implements Controllable
{
    //these constants are initials and will be changed during the changing the settings of program in GUI

    private double WIDTH = 465 ;                      // population space size (width)
    private double HEIGHT = 290 ;                     // population space size (height)
    private int COUNT = 30;                           // population size
    private double CONTAMINATION_RADIUS = 10;         // radius of contamination
    private long DURATION_INCUBATION = 3000;          // incubation <-> sick
    private long DURATION_HEALING = 8000;             // sick <-> recovered
    private long DURATION_IMMUNITY = 5000;            // recovered <-> healthy (can be contaminate again)
    private double RADIUS_DOT = 3;                    // radius of point in GUI
    private double KILOMETRAGE = 50;
    private double DIAMETER_X = 15;
    private double DIAMETER_Y = 10;
    private double THICKNESS = 4;                     // thickness of the walls
    private int WALLS_COUNT = 4;                      // number of boundaries

    private Controllable.eState state = Controllable.eState.Paused;   // enum variable to control all our timers at the same time
    ReentrantLock statLock = new ReentrantLock();                   // variable to make lock/unlock the threads

    //==================================== Timer Management ===========================================================/

    /**
     * @summary Get value of enum state.
     * Made thread-save because state is shared by many processes.
     */
    @Override
    public Controllable.eState getState()
    {
        statLock.lock();
        Controllable.eState st = state;
        statLock.unlock();
        return st;
    }

    /**
     * @summary Change value of enum state.
     * Made thread-save because state is shared by many processes.
     */
    @Override
    public void setState(Controllable.eState newState)
    {
        statLock.lock();
        state = newState;
        statLock.unlock();
    }

    //====================================== Space Settings ===========================================================/

    @Override
    public double[] getSpaceSize() { return new double[]{this.WIDTH, this.HEIGHT}; }

    @Override
    public void setSpaceSize(double w, double h)
    {
        WIDTH = w;
        HEIGHT = h;
    }

    @Override
    public double getThickness() {
        return THICKNESS;
    }

  //  public static double getWidth() { return WIDTH; }

  //  public static double getHeight() { return HEIGTH; }

 //   public static void setWidth(double w) { if (w >= 1) { WIDTH = w; }}


    //====================================== Scenarios Settings =======================================================/

    @Override
    public double getKilometrage() {
        return KILOMETRAGE;
    }

    @Override
    public double getDiameterX() {
        return DIAMETER_X;
    }
    
    @Override
    public double getDiameterY() {
        return DIAMETER_Y;
    }

    //===================================== Population Settings =======================================================/

    @Override
    public int getPersonsCount() { return COUNT; }

    @Override
    public void setPersonsCount(int nmbPersons) { COUNT = nmbPersons;}

    @Override
    public double getRadiusDot() { return RADIUS_DOT; }

    public void setRadiusDot(double x) { RADIUS_DOT = x; }

    //========================================= Virus Settings ========================================================/

    @Override
    public double getContaminationRadius() { return CONTAMINATION_RADIUS; }

    @Override
    public void setContaminationRadius(double pxls) { CONTAMINATION_RADIUS = pxls; }

    @Override
    public long getDurationIncubation() { return DURATION_INCUBATION; }

    @Override
    public void setDurationIncubation(long time) { DURATION_INCUBATION = time; }

    @Override
    public long getDurationImmunity() { return DURATION_IMMUNITY; }

    @Override
    public void setDurationImmunity(long time) { DURATION_IMMUNITY = time; }

    @Override
    public long getDurationHealing() { return DURATION_HEALING;}

    @Override
    public void setDurationHealing(long time) { DURATION_HEALING = time; }

    //====================================== Walls Settings ===========================================================/

    @Override
    public int getWallsCount() { return WALLS_COUNT;}

    @Override
    public void setWallsCount(int count) { WALLS_COUNT = count;}
}
