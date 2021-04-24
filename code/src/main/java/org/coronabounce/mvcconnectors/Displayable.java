package org.coronabounce.mvcconnectors;

import org.coronabounce.data.Data;
import org.coronabounce.models.Wall;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface provide functions necessaries to display model (populations) in GUI and manage its timers.
 */
public interface Displayable
{
    //======================================= Population =============================================================//

    /**
     * {@summary Get list of points.}
     * @return the list of all points.
     */
    public List getAllPoints();

    /**
     * {@summary Get total number of points / number of Individuals.}
     */
    public int getNbIndividus();

    /**
     * {@summary Get number of healthy.}
     */
    public int getNbHealthy();

    /**
     * {@summary Get number of contagious / sick people.}
     */
    public int getNbInfected();

    /**
     * {@summary Get number of recovered.}
     */
    public int getNbRecovered();

    /**
     * {@summary Transfers NbSick and NbRecovered to Data to save them to draw AreaChart.}
     * To show correctly superposed layers in AreaChart we take:
     * <ul>
     * <li> NbHealthy taken as 100% (bottom layer)
     * <li> nbSick = nbSick + NbIncubating + nbRecovered (middle layer)
     * <li> NbRecovered = NbRecovered (top layer)
     * </ul>
     * Superposed they present ratio of these tree values (nbHealthy, nbSick/Incubating and nbRecovered) in 100%.
     */
    public void saveStatToData();

    /**
     * {@summary Get saved statistics (history).}
     * @return Data - history.
     */
    public Data getData();

    //=========================================== Timers =============================================================//

    /**
     * {@summary Timer's interrupter.}
     */
    public void stopTimer(boolean b_StopTimer);

    //============================================ Walls =============================================================//

    /**
     * {@summary Return list walls.}
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
