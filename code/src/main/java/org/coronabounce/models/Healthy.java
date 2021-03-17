package org.coronabounce.models;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class Healthy extends Individual {

    public Healthy(CoquilleBille coc, Population p){
      super(coc,p);
    }
    @Override
    public void contact(CoquilleBille coc, Population p) {
        healing(coc,p);
    }

    public  void healing(CoquilleBille coc,Population p)
    {

    }
}
