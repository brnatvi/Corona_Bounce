package org.coronabounce.data;

import java.util.Vector;
import java.util.concurrent.locks.ReentrantLock;

public class Data
{
    public class Slice
    {
        private int prcSick;
        private int prcRecovered;

        private Slice(int s, int r)
        {
            this.prcSick = s;
            this.prcRecovered = r;
        }

        public int getPrcSick(){return prcSick;}

        public int getPrcRecovered() {return prcRecovered;}
    }

    private int nmbr;
    private Vector<Slice> fifo;
    final ReentrantLock lock = new ReentrantLock();

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

    public void Lock() {lock.lock();}

    public void unLock() {lock.unlock();}
}
