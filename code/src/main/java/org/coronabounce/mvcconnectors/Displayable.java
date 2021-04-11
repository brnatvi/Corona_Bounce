package org.coronabounce.mvcconnectors;

import org.coronabounce.data.Data;
import org.coronabounce.models.Wall;

import java.util.List;
import java.util.Timer;

public interface Displayable
{
    //======================================== First Priority ========================================================//

    /**
     * Get list of points
     * @param
     * @return
     */
    public List getAllPoints();

    /**
     * Get total number of points / number od Individu.
     * @return
     */
    public int getNbIndividus();

    /**
     * Get number of healthy
     * @return
     */
    public int getNbHealthy();

    /**
     * Get number of contagious / sick people.
     * @return
     */
    public int getNbSick();

    /**
     * Get number of recovered
     * @return
     */
    public int getNbRecovered();

    public void saveStatToData();

    public Data getData();

    public void stopTimer(boolean b_StopTimer);

    public void pauseThread() throws InterruptedException;

    //======================================== Second Priority ========================================================//

    /**
     * @return
     */
    public List<Wall> getListWall();

}
