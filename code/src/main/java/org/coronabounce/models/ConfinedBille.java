package org.coronabounce.models;

import org.coronabounce.controllers.Controller;

import java.util.Random;

public class ConfinedBille extends CoquilleBille{

        public ConfinedBille(double speedX,double speedY, Individual individual){
            super(speedX,speedY,individual);
        }
        public void   reduce_speed(double percentage){
           this.setMovingSpeed (this.getMovingSpeedX()-(this.getMovingSpeedX()*percentage)/100,this.getMovingSpeedY()-(this.getMovingSpeedY()*percentage)/100);
        }
       public void pass_zone(double X) {
           if (this.distancePos() > X) {
               this.bounceIfOutOfZone();
           }
       }
        public void move() {
            reduce_speed(Controller.getPercentage());
            super.move();
            pass_zone(Controller.getKilometrage());
           }

           }


