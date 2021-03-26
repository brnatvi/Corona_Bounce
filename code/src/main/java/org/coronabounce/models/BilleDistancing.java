package org.coronabounce.models;

public class BilleDistancing extends CoquilleBille{
    public BilleDistancing(double speedX,double speedY, Individual individual){
        super(speedX,speedY,individual);
    }
    public double distancePos(Position p1,Position p2) {
        double x1 = p1.getX();
        double x2 = p2.getX();
        double y1 = p1.getY();
        double y2 = p2.getY();
        return  Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));

    }
    public void Rebound(){
        if (outOfX(this.getPosition().getX())) {
            this.setMovingSpeed(this.getMovingSpeedX()*-1,this.getMovingSpeedY());
        }
        if (outOfY(this.getPosition().getY())) {
            this.setMovingSpeed(this.getMovingSpeedX(),this.getMovingSpeedY()*-1);
        }
        this.getPosition().setPos(this.getPosition().getX()+this.getMovingSpeedX(),this.getPosition().getY()+this.getMovingSpeedY());
    }
    public void respectSocialDistancing(long SocialDistancing){
        if(distancePos(this.getPosition(),this.getStartingPosition())>=SocialDistancing){
          this.Rebound();
        }
    }
}
