package org.coronabounce.models;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class Healthy extends Individual {

    public Healthy(CoquilleBille coc, Population p){
      super(coc,p);
      p.nbHealthy++;
    }
}
