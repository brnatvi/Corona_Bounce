package org.coronabounce.models;

public class BilleDistancing extends CoquilleBille{
    public BilleDistancing(double speedX,double speedY, Individual individual){
        super(speedX,speedY,individual);
    }
    public void respectSocialDistancing(long SocialDistancing,CoquilleBille coc){
        if(distancePos(this.getPosition(),coc.getPosition())>=SocialDistancing){
          this.Rebound();
        }
    }
}
