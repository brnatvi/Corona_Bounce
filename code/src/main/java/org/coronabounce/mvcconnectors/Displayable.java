package org.coronabounce.mvcconnectors;

import org.coronabounce.data.Data;
import org.coronabounce.models.Wall;

import java.util.ArrayList;
import java.util.List;

public interface Displayable
{
    //======================================= Population =============================================================//

    /**
     * Get list of points
     * @return the list of all points
     */
    public List getAllPoints();

    /**
     * Get total number of points / number of Individuals.
     */
    public int getNbIndividus();

    /**
     * Get number of healthy
     */
    public int getNbHealthy();

    /**
     * Get number of contagious / sick people.
     */
    public int getNbInfected();

    /**
     * Get number of recovered
     */
    public int getNbRecovered();

    /**
     * {@summary Transfers NbSick and NbRecovered to Data to save them to draw AreaChart.}
     * To show the layers of AreaChart correctly with superposition we take
     * NbRecovered = NbRecovered,
     * nbSick = nbSick + nbRecovered,
     * all the rest: NbHealthy + NbIncubating takes like 100%
     */
    public void saveStatToData();

    /**
     * Get saved statistics (history)
     * @return Data - history
     */
    public Data getData();

    //=========================================== Timers =============================================================//

    public void stopTimer(boolean b_StopTimer);

    //============================================ Walls =============================================================//

    /**
     * @return List of walls
     */
    public List<Wall> getListWall();

    /**
     * {@summary Return list of positions on axis of abscissas of all walls.}
     */
    public ArrayList<Double> getPositionsOfWalls();

    /**
     * {@summary Return list of heights of all walls.}
     */
    public ArrayList<Double> getHeigthsOfWalls();

    /**
     * {@summary Return list of thicknesses of all walls.}
     */
    public ArrayList<Double> getThicknessesOfWalls();
}
