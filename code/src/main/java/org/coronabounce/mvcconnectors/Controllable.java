package org.coronabounce.mvcconnectors;

/**
 * Interface provide functions necessaries to pass parameters and manage threads.
 */
public interface Controllable
{
    /**
     * {@summary Enum used to manage thread/timer states.}
     */
    enum eState
    {
        Working,
        Paused
    }

    /**
     * {@summary Returns state of the controller, has to be checked in each thread/timer}
     */
    public Controllable.eState getState();

    /**
     * {@summary Set new state}
     * @param newState - new controller state
     */
    public void setState(Controllable.eState newState);

    //====================================== Space size Settings ======================================================/

    /**
     * {@summary Set width and height of field to controller}
     * @param w, h - width, height
     */
    public void setSpaceSize(double w, double h);
    /**
     * {@summary Get width and height of field from controller}
     * @return width, height in simple array
     */
    public double[] getSpaceSize();

    //===================================== Population Settings =======================================================/

    /**
     * {@summary Set total number of persons in simulation to controller.}
     * @param nmbPersons size of population.
     */
    public void setPersonsCount(int nmbPersons);

    /**
     * {@summary Get total number of persons in simulation from controller.}
     * @return the size of population.
     */
    public int getPersonsCount();

    /**
     * {@summary Get radius of dot/individual.}
     * @return radius dot.
     */
    public double getRadiusDot();

    //========================================= Virus Settings ========================================================/

    /**
     * {@summary Set radius of contamination around point.}
     * @param pxls number of pixels.
     */
    public void setContaminationRadius(double pxls);

    /**
     * {@summary Get radius of contamination around point.}
     * @return contamination radius.
     */
    public double getContaminationRadius();

    /**
     * {@summary Set duration of sickness to controller.}
     * @param time - number of milliseconds.
     */
    public void setDurationIncubation(long time);

    /**
     * {@summary Get duration of sickness from controller.}
     */
    public long getDurationIncubation();

    /**
     * {@summary Set duration of non-contamination after recovery.}
     * @param time - number of milliseconds.
     */
    public void setDurationImmunity(long time);

    /**
     * {@summary Get duration of non-contamination after recovery.}
     */
    public long getDurationImmunity();

    /**
     * {@summary Set duration of recovery.}
     * @param time - duration in milliseconds.
     */
    public void setDurationHealing(long time);

    /**
     * {@summary Get duration of recovery.}
     */
    public long getDurationHealing();


    //========================================= Wall Settings =========================================================/

    /**
     * {@summary Get number of walls.}
     * @return number of walls.
     */
    public int getWallsCount();

    /**
     * {@summary Set number of walls.}
     * @param count - new number of walls.
     */
    public void setWallsCount(int count);

    /**
     * {@summary Getter of thickness of walls.}
     */
    public double getThickness();

    /**
     * {@summary Getter of walls closing speed.}
     */
    public int getWallSpeed();

    /**
     * {@summary Setter of walls closing speed.}
     */
    public void setWallSpeed (int speed);

    //===================================== Scenarios Settings ======================================================/

    /**
     * {@summary Getter of distance authorized for moving during lockdown.}
     */
    public double getKilometrage();

    public double getDiameterX();

    public double getDiameterY();

}
