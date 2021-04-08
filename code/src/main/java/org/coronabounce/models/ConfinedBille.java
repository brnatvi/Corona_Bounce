package org.coronabounce.models;

import org.coronabounce.controllers.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConfinedBille extends CoquilleBille {
  private static Random random = new Random();

    public ConfinedBille(double speedX, double speedY, Individual individual) {
        super(speedX, speedY, individual);
    }

    public ConfinedBille(Individual i) {
        super(i);
    }

    private double genererDouble() {
        return random.nextDouble();
    }

    private int genererInt(int borneInf, int borneSup) {
        int nb;
        nb = borneInf + random.nextInt(borneSup - borneInf);
        return nb;
    }

    private void reduce_speed() {
        int percentage = genererInt(60, 100);

        Random r = new Random();

        if ((this.getMovingSpeedX() * percentage / 100) <= 1 || (this.getMovingSpeedY() * percentage / 100) <= 1) {
            {

                this.setMovingSpeed(this.getMovingSpeedX(), this.getMovingSpeedX());
            }
        } else {
            this.setMovingSpeed((this.getMovingSpeedX() * percentage / 100), (this.getMovingSpeedY() * percentage / 100));

        }


    }

    @Override
    public void move() {
        bounceIfOutOfZone();
        // reduce_speed();
        stayNextToHome();
        this.getPosition().setPos(this.getPosition().getX() + this.getMovingSpeedX(), this.getPosition().getY() + this.getMovingSpeedY());
    }
    public void stayNextToHome2(){

      double b = 1.0;//0.9 + genererDouble()/5;
      double a = 0;//genererDouble() * b;
      double c;
      if (distancePos() >= Controller.getKilometrage()) {
          if ((this.getMovingSpeedX() - a) + this.getPosition().getX() > this.getStartingPosition().getX()+Controller.getDiametreX()) {
              //c=genererDouble()*b;
              //this.setMovingSpeed((this.getMovingSpeedX()) - a, (this.getMovingSpeedY()+c));
              bounce(true);
          }
          if ((this.getMovingSpeedY() - a) + this.getPosition().getY() > this.getStartingPosition().getY()+Controller.getDiametreY()) {
              // c=genererDouble()*a;
              // this.setMovingSpeed((this.getMovingSpeedX()+c), (this.getMovingSpeedY() - a));
              bounce(false);
          }
          if ((this.getMovingSpeedX() + a) + this.getPosition().getX() <= this.getStartingPosition().getX()+Controller.getDiametreX()) {
              //c=genererDouble()*b;
              //this.setMovingSpeed((this.getMovingSpeedX()) + a, (this.getMovingSpeedY()-c));
              bounce(true);
          }
          if ((this.getMovingSpeedY() + a) + this.getPosition().getY() <= this.getStartingPosition().getY()+Controller.getDiametreY()) {
              //c=genererDouble()*a;
              //this.setMovingSpeed((this.getMovingSpeedX()-c), (this.getMovingSpeedY() + a));
              bounce(false);
          }
          moveABitRandomly(0.2);
      }
    }
    public void stayNextToHome(){
      if (distancePos() >= Controller.getKilometrage()) {
        double curentX = p.getX();
        double futurX = curentX+movingSpeedX;
        double curentY = p.getY();
        double futurY = curentY+movingSpeedY;
        if((futurX > getStartingPosition().getX()+Controller.getDiametreX()) || (futurX < getStartingPosition().getX()-Controller.getDiametreX())){
          bounce(true);
        }
        if((futurY > getStartingPosition().getY()+Controller.getDiametreY()) || (futurY < getStartingPosition().getY()-Controller.getDiametreY())){
          bounce(false);
        }
        moveABitRandomly(0.6);
      }
    }
    public void moveABitRandomly(double variability){
      // System.out.println("move a bit");
      // System.out.println(getMovingSpeedX());
      // double varX = 1.0;
      // double varY = 1.0;
      // double varX = 1 - variability + genererDouble()/5;
      // double varY = 1 - variability + genererDouble()/5;
      double var = genererDouble() - 0.5;
      var*=variability;
      // var*=(getMovingSpeedX()+getMovingSpeedY());
      setMovingSpeed(getMovingSpeedX()+var,getMovingSpeedY()-var);
      // System.out.println(getMovingSpeedX());
    }
}
