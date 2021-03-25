package org.coronabounce.data;

import java.util.ArrayList;

public class Data
{
    private int nmbr;
    private ArrayList<Slice> fifo;

   // private ArrayList<Integer> listHealthy;
   // private ArrayList<Integer> listSick;
   // private ArrayList<Integer> listRecov;


    public Data()
    {
        this.nmbr = 120;
        this.fifo = new ArrayList<Slice>();
       // this.listHealthy = new ArrayList<>();
       // this.listSick = new ArrayList<>();
       // this.listRecov = new ArrayList<>();
    }

    public ArrayList<Slice> getFifo()
    {
        return this.fifo;
    }

    public int getHelthy(int index)
    {
        return fifo.get(index).getPrcHealthy();
    }

    public int getSick(int index)
    {
        return fifo.get(index).getPrcSick();
    }

    public int getRecovered(int index)
    {
        return fifo.get(index).getPrcRecovered();
    }



  //  public ArrayList<Integer> getListHelthy()
  //  {
  //      for (int i = 0; i < fifo.size(); i++)
  //      {
  //          this.listHealthy.add(i, fifo.get(i).getPrcHealthy());
  //      }
  //      return listHealthy;
  //  }
  //
  //  public ArrayList<Integer> getListSick()
  //  {
  //      for (int i = 0; i < fifo.size(); i++)
  //      {
  //          this.listSick.add(i, fifo.get(i).getPrcSick());
  //      }
  //      return listSick;
  //  }
  //
  //  public ArrayList<Integer> getListRecov()
  //  {
  //      for (int i = 0; i < fifo.size(); i++)
  //      {
  //          this.listRecov.add(i, fifo.get(i).getPrcRecovered());
  //      }
  //      return listRecov;
  //  }

    public int getNmbr() { return this.nmbr; }

    class Slice
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

        public int getPrcHealthy() {return prcHealthy;}

        public int getPrcSick(){return prcSick;}

        public int getPrcRecovered() {return prcRecovered;}
    }


    public void setData(int healthy, int sick, int rec)
    {
        Slice newSl = new Slice(healthy, sick, rec);
        if (fifo.size() < nmbr)
        {
            fifo.add(newSl);
        }
        else if (fifo.size() == nmbr)
        {
            fifo.remove(0);
            fifo.add(nmbr - 1, newSl);
        }
    }
}