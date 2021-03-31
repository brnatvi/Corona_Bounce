package org.coronabounce.models;

import java.util.Random;

public class ConfinedBille extends CoquilleBille{

        public ConfinedBille(double speedX,double speedY, Individual individual){
            super(speedX,speedY,individual);
        }
        public void   reduce_speed(double percentage){
           this.setMovingSpeed (this.getMovingSpeedX()-(this.getMovingSpeedX()*percentage)/100,this.getMovingSpeedY()-(this.getMovingSpeedY()*percentage)/100);
        }
       public void pass_zone(double X){
        if(distancePos(this.getPosition(),this.getStartingPosition())>X){
            this.Rebound();
        }

    }

}
