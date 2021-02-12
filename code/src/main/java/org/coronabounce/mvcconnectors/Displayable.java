package org.coronabounce.mvcconnectors;

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
    public ArrayList getAllPoints();

    /**
     * Get total number of points
     * @return
     */
    public int getNmbPoints();

    /**
     * Get number of healthy
     * @return
     */
    public int getNmbHealthy();

    /**
     * Get number of contagious
     * @return
     */
    public int getNmbContagious();

    /**
     * Get number of recovered
     * @return
     */
    public int getNmbRecovered();

    

    //======================================== Second Priority ========================================================//

 //   /**
 //    * @return
 //    */
 //   public int[] getBorder();

}
