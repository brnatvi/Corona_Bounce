package org.coronabounce.mvcconnectors;

public interface Controllable
{
    //======================================== First Priority ========================================================//

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

    /**
     * Set total number of persons in simulation to model
     * @param nmbPersons
     */
    public void setPersonsCount(int nmbPersons);

    /**
     * Get total number of persons in simulation from controller
     * @return nmbPersons
     */
    public int getPersonsCount();

    /**
     * Set radius of contamination around point from GUI to controller
     * @param pxls number of pixels
     */
    public void setRadius(double pxls);

    /**
     * Get radius of contamination around point trom comtroller
     */
    public double getRadius();

    /**
     * Set duration of sickness from GUI's to controller
     * @param time - number of milliseconds
     */
    public void setDurationCovid(long time);

    /**
     * Get duration of sickness from controller
     */
    public long getDurationCovid();

    /**
     * Set time of non-contamination after recovery
     * @param time - number of milliseconds
     */
    public void setDurationNonContamination(long time);

    /**
     * Get time of non-contamination after recovery from GUI's controller
     */
    public long getDurationNonContamination();

    /**
     * Get radius of dot
     */
    public double getRadiusDot();

    //======================================== Second Priority ========================================================//

  //  /**
  //   * Set point's speed to model
  //   * @param speed
  //   */
  //  public void setSpeedPoints(int speed);
  //
  //  /**
  //   * Get point's speed from GUI's controller
  //   */
  //  public int getSpeedPoints();
  //
  //
  //
  //  /**
  //   * Set state border to model
  //   * @param
  //   */
  //  public void setBorder(boolean isBordered);
  //
  //  /**
  //   * Get state border from GUI - is scenario with borders selected or not
  //   * @param
  //   */
  //  public boolean getBorder();
  //
  //  /**
  //   * Set door/gap in state border during opening of state border.
  //   * This gap opened automatically and gradually, so doesn't need take int from GUI, it depends of controller's timer
  //   * @param pxls number of pixels
  //   */
  //  public void setOpening(int pxls);
  //
  //  //percentage of active persons
  //  //percentage of isolated
  //  //percentage of students
}
