package org.coronabounce.views;

public interface View
{
    //======================================== First Priority ========================================================//

    /**
     * initialize view (new Windows for GUI)
     * @return true if everything work well.
     */
    public boolean initialize();
    /**
     * Repaint all the view.
     * @return true if everything work well.
     */
    public boolean repaint();
    /**
     * Close the windows or the Cli interface.
     * @return true if everything work well.
     */
    public boolean close();


    //======================================== Second Priority ========================================================//


}
