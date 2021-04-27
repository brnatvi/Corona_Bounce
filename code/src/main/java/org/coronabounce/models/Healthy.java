package org.coronabounce.models;


public class Healthy extends Individual {

    public Healthy(CoquilleBille coc, Population p){
      super(coc,p);
      p.nbHealthy++;/**increase the number of healthy individuals that exist**/
    }
}
