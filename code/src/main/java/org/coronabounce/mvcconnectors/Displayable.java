package org.coronabounce.mvcconnectors;

import org.coronabounce.data.Data;

import java.util.ArrayList;
import java.util.List;

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

    //======================================== Second Priority ========================================================//

 //   /**
 //    * @return
 //    */
 //   public int[] getBorder();

}
