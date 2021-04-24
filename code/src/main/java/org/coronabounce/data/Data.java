package org.coronabounce.data;

import java.util.Vector;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class for storage statistics for drawing the graphs.
 * Important!! This numbers aren't coherent with true statistics. See below.
 */
public class Data
{
    public class Slice
    {
        private int prcSick;          // statistic of sick (in reality sick + recovered to draw correctly superposed layers)
        private int prcRecovered;     // statistic of recovered

        private Slice(int s, int r)
        {
            this.prcSick = s;
            this.prcRecovered = r;
        }

        public int getPrcSick(){return prcSick;}

        public int getPrcRecovered() {return prcRecovered;}
    }

    private int nmbr;                                      // volume of queue
    private Vector<Slice> fifo;                            // queue (FIFO)
    final ReentrantLock lock = new ReentrantLock();        // variable to make lock/unlock the threads

    //========================== Constructor ==========================================================================/

    public Data()
    {
        this.nmbr = 120;
        this.fifo = new Vector<Slice>();
    }

    //========================= Getters ===============================================================================/

    public int getNmbr() { return this.nmbr; }

    public Vector<Slice> getFifo()
    {
        return this.fifo;
    }

    //========================= Own functions =========================================================================/

    /**
     * {@summary Remove the first element of queue and add new values (sick, recovered) on the end.}
     * Made thread-saved because data is shared by two processes:
     *          reading for drawing graph and writing to update queue.
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
     * @summary Lock the thread of the processes there data will be used.
     */
    public void Lock() {lock.lock();}

    /**
     * @summary Unlock the thread of the processes there data will be used.
     */
    public void unLock() {lock.unlock();}
}
