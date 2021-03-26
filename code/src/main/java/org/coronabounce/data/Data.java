package org.coronabounce.data;

import java.util.ArrayList;

public class Data
{
    public class Slice
    {
        private int prcHealthy;
        private int prcSick;
        private int prcRecovered;

        private Slice(int h, int s, int r)
        {
            this.prcHealthy = h;
            this.prcSick = s;
            this.prcRecovered = r;
        }

        public int getPrcSick(){return prcSick;}

        public int getPrcRecovered() {return prcRecovered;}
    }

    private int nmbr;
    private ArrayList<Slice> fifo;

    public Data()
    {
        this.nmbr = 120;
        this.fifo = new ArrayList<Slice>();
    }

    public ArrayList<Slice> getFifo()
    {
        return this.fifo;
    }

    public int getNmbr() { return this.nmbr; }

    public synchronized void setData(int healthy, int sick, int rec)
    {
        if (fifo.size() >= nmbr)
        {
            fifo.remove(0);
        }
        fifo.add(new Slice(healthy, sick, rec));
    }
}
