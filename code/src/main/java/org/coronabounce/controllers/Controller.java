package org.coronabounce.controllers;

import org.coronabounce.mvcconnectors.Controllable;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Class Controller is a auxiliary to MainController and SettingsController.
 * It provide initial values of constants and methods to change them in the course of using the program.
 */
public class Controller implements Controllable
{
    /**
     * Constants are initials and will be changed during the changing the settings of program in GUI
     */
    
    /** Population space size (width) **/
    private double WIDTH = 400 ;
    /** Population space size (height) **/
    private double HEIGHT = 300 ;
    /** Population size (number of persons) **/
    private int COUNT = 30;
    /** Radius of contamination **/
    private double CONTAMINATION_RADIUS = 10;
    /** Duration of incubation ( from incubation to sick ) **/
    private long DURATION_INCUBATION = 3000;
    /** Duration of healing ( from sick to recovered ) **/
    private long DURATION_HEALING = 8000;
    /** Duration of immunity ( from recovered to healthy, can be contaminate again ) **/
    private long DURATION_IMMUNITY = 5000;
    /** Radius of point in GUI **/
    private double RADIUS_DOT = 3;
    /** Permitted moving off distance in lockdown **/
    private double KILOMETRAGE = 50;
    private double DIAMETER_X = 15;
    private double DIAMETER_Y = 10;
    /** Thickness of the walls **/
    private double THICKNESS = 4;
    /** Number of boundaries **/
    private int WALLS_COUNT = 4;
    /** Speed of boundaries closing **/
    private int WALL_SPEED = 1;
    /** Enum variable to control all our timers at the same time **/
    private Controllable.eState state = Controllable.eState.Paused;
    /** Variable to make lock/unlock threads **/
    private ReentrantLock statLock = new ReentrantLock();

    //==================================== Timer Management ===========================================================/

    /**
     * {@summary Get value of enum state.}
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
     * {@summary Change value of enum state.}
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

    /**
     * {@summary Space size getter.}
     */
    @Override
    public double[] getSpaceSize() { return new double[]{this.WIDTH, this.HEIGHT}; }

    /**
     * {@summary Space size setter.}
     */
    @Override
    public void setSpaceSize(double w, double h)
    {
        WIDTH = w;
        HEIGHT = h;
    }

    /**
     * {@summary Gets width. Used in tests.}
     * @return the width
     */
    public double getWidth() { return WIDTH; }

    /**
     * {@summary Set width. Used in tests.}
     * @param x the width.
     */
    public void setWidth(double x){WIDTH=x; if(WIDTH<1){WIDTH=1;}}

    /**
     * {@summary Gets height. Used in tests.}
     * @return the height.
     */
    public double getHeight() { return HEIGHT; }

    /**
     * {@summary Set heigth. Used in tests.}
     * @param x the height.
     */
    public void setHeigth(double x){HEIGHT=x; if(HEIGHT<1){HEIGHT=1;}}

    //====================================== Scenarios Settings =======================================================/

    /**
     * {@summary Getter of distance authorized for moving during lockdown.}
     */
    @Override
    public double getKilometrage() { return KILOMETRAGE; }

    @Override
    public double getDiameterX() { return DIAMETER_X; }

    @Override
    public double getDiameterY() { return DIAMETER_Y; }

    //===================================== Population Settings =======================================================/

    /**
     * {@summary Get total number of persons in simulation from controller.}
     * @return the size of population.
     */
    @Override
    public int getPersonsCount() { return COUNT; }

    /**
     * {@summary Set total number of persons in simulation to controller.}
     * @param nmbPersons size of population.
     */
    @Override
    public void setPersonsCount(int nmbPersons) { COUNT = nmbPersons;}

    /**
     * {@summary Get radius of dot/individual.}
     * @return radius dot.
     */
    @Override
    public double getRadiusDot() { return RADIUS_DOT; }

    /**
     * {@summary Sets radius dot.}
     * Used in tests.
     * @param x the radius of dot.
     */
    public void setRadiusDot(double x) { RADIUS_DOT = x; }

    //========================================= Virus Settings ========================================================/

    /**
     * {@summary Get radius of contamination around point.}
     * @return contamination radius.
     */
    @Override
    public double getContaminationRadius() { return CONTAMINATION_RADIUS; }

    /**
     * {@summary Set radius of contamination around point.}
     * @param pxls number of pixels.
     */
    @Override
    public void setContaminationRadius(double pxls) { CONTAMINATION_RADIUS = pxls; }

    /**
     * {@summary Get duration of incubation. }
     */
    @Override
    public long getDurationIncubation() { return DURATION_INCUBATION; }

    /**
     * {@summary Set duration of incubation to controller.}
     * @param time - number of milliseconds.
     */
    @Override
    public void setDurationIncubation(long time) { DURATION_INCUBATION = time; }

    /**
     * {@summary Get duration of non-contamination after recovery.}
     */
    @Override
    public long getDurationImmunity() { return DURATION_IMMUNITY; }

    /**
     * {@summary Set duration of non-contamination after recovery.}
     * @param time - number of milliseconds.
     */
    @Override
    public void setDurationImmunity(long time) { DURATION_IMMUNITY = time; }

    /**
     * {@summary Get duration of recovery.}
     */
    @Override
    public long getDurationHealing() { return DURATION_HEALING;}

    /**
     * {@summary Set duration of recovery.}
     * @param time - duration in milliseconds.
     */
    @Override
    public void setDurationHealing(long time) { DURATION_HEALING = time; }

    //====================================== Walls Settings ===========================================================/

    /**
     * {@summary Get number of walls.}
     * @return number of walls.
     */
    @Override
    public int getWallsCount() { return WALLS_COUNT;}

    /**
     * {@summary Set number of walls.}
     * @param count - new number of walls.
     */
    @Override
    public void setWallsCount(int count) { WALLS_COUNT = count;}

    /**
     * {@summary Getter of walls closing speed.}
     */
    @Override
    public int getWallSpeed() {return WALL_SPEED;}

    /**
     * {@summary Setter of walls closing speed.}
     */
    @Override
    public void setWallSpeed(int speed) {WALL_SPEED = speed;}

    /**
     * {@summary Getter of thickness of walls.}
     */
    @Override
    public double getThickness() { return THICKNESS; }

}
