package org.coronabounce.mvcconnectors;

public interface Controllable
{
    enum eState
    {
        Idle,
        Working,
        Paused
    }

    /**
     * Returns state of the controller, has to be checked in each thread/timer
     */
    public Controllable.eState getState();

    /**
     * Set new state
     * @param newState - new controller state
     */
    public void setState(Controllable.eState newState);

    //====================================== Space size Settings ======================================================/

    /**
     * Set width and height of field to controller
     * @param w, h - width, height
     */
    public void setSpaceSize(double w, double h);
    /**
     * Get width and height of field from controller
     * @return width, height in simple array
     */
    public double[] getSpaceSize();

    //===================================== Population Settings =======================================================/

    /**
     * Set total number of persons in simulation to controller
     * @param nmbPersons
     */
    public void setPersonsCount(int nmbPersons);

    /**
     * Get total number of persons in simulation from controller
     * @return nmbPersons
     */
    public int getPersonsCount();

    /**
     * Get radius of dot/individual
     */
    public double getRadiusDot();

    //========================================= Virus Settings ========================================================/

    /**
     * Set radius of contamination around point
     * @param pxls number of pixels
     */
    public void setContaminationRadius(double pxls);

    /**
     * Get radius of contamination around point
     */
    public double getContaminationRadius();

    /**
     * Set duration of sickness to controller
     * @param time - number of milliseconds
     */
    public void setDurationCovid(long time);

    /**
     * Get duration of sickness from controller
     */
    public long getDurationCovid();

    /**
     * Set duration of non-contamination after recovery
     * @param time - number of milliseconds
     */
    public void setDurationNonContamination(long time);

    /**
     * Get duration of non-contamination after recovery
     */
    public long getDurationNonContamination();

    /**
     * Set duration of recovery
     */
    public void setDurationHealing(long time);

    /**
     * Get duration of recovery
     */
    public long getDurationHealing();

}
