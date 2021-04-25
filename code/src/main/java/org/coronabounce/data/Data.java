package org.coronabounce.data;

import java.util.Vector;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class for storage statistics for drawing the graphs.
 * Important!! This numbers aren't coherent with true statistics.
 * To show correctly superposed layers in AreaChart we take:
 * <ul>
 * <li> NbHealthy taken as 100% (bottom layer)
 * <li> nbSick = nbSick + NbIncubating + nbRecovered (middle layer)
 * <li> NbRecovered = NbRecovered (top layer)
 * </ul>
 * Superposed they present ratio of these tree values (nbHealthy, nbSick/Incubating and nbRecovered) in 100%.
 */
public class Data
{
    /**
     * Class which collect values.
     */
    public class Slice
    {
        /** Statistic of sick (in reality sick + incubating + recovered to draw correctly superposed layers) **/
        private int prcSick;
        /** Statistic of recovered **/
        private int prcRecovered;

        /**
         * Instantiates a new Slice.
         */
        private Slice(int s, int r)
        {
            this.prcSick = s;
            this.prcRecovered = r;
        }

        /**
         * Get percentage of sick (sick + incubating + reco).
         * @return the int
         */
        public int getPrcSick(){return prcSick;}

        /**
         * Gets percentage of recovered.
         * @return the prc recovered
         */
        public int getPrcRecovered() {return prcRecovered;}
    }

    /** Volume of queue **/
    private int nmbr;
    /** Queue (FIFO) **/
    private Vector<Slice> fifo;
    /** Variable to make lock/unlock the threads **/
    final ReentrantLock lock = new ReentrantLock();

    //========================== Constructor ==========================================================================/

    /**
     * Instantiates a new Data.
     */
    public Data()
    {
        this.nmbr = 120;
        this.fifo = new Vector<Slice>();
    }

    //========================= Getters ===============================================================================/

    /**
     * Gets volume of queue.
     * @return the nmbr
     */
    public int getNmbr() { return this.nmbr; }

    /**
     * Gets fifo.
     * @return the fifo
     */
    public Vector<Slice> getFifo()
    {
        return this.fifo;
    }

    //========================= Own functions =========================================================================/

    /**
     * {@summary Remove the first slice of queue and add new slice on the end.}
     * Made thread-saved because data is shared by two processes:
     * reading for drawing graph and writing to update queue.
     * @param sick the sick
     * @param rec  the recovered
     */
    public void setData(int sick, int rec)
    {
        this.Lock();
        if (fifo.size() >= nmbr)
        {
            fifo.remove(0);
        }
        fifo.add(new Slice(sick, rec));
        this.unLock();
    }

    /**
     * {@summary Lock the thread of the processes there data will be used.}
     */
    public void Lock() {lock.lock();}

    /**
     * {@summary Unlock the thread of the processes there data will be used.}
     */
    public void unLock() {lock.unlock();}
}
