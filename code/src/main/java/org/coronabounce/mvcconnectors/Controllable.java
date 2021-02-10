package org.coronabounce.mvcconnectors;

public interface Controllable
{
    //======================================== First Priority ========================================================//

    /**
     * Set width and height of field
     * @param w, h - width, height
     */
    public void setSpaceSize(int w, int h);

    /**
     * Set total number of persons in simulation to model
     * @param nmbPersons
     */
    public void setPersonsCount(int nmbPersons);

    /**
     * Set radius of contamination around point to model
     * @param pxls number of pixels
     */
    public void setRadius(int pxls);

    /**
     * Get radius of contamination around point from GUI ()
     */
    public int getRadius();

    /**
     * Set duration of sickness
     * @param time - number of milliseconds
     */
    public void setDurationCovid(int time);

    /**
     * Get duration of sickness from GUI's controller
     */
    public int getDurationCovid();

    /**
     * Set time of non-contamination after recovery
     * @param time - number of milliseconds
     */
    public void setDurationNonContamination(int time);

    /**
     * Get time of non-contamination after recovery from GUI's controller
     */
    public int getDurationNonContamination();



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
