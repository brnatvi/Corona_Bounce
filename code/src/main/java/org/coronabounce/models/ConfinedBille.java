package org.coronabounce.models;

import org.coronabounce.controllers.Controller;
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

    private void reduceSpeed() {
        int percentage = genererInt(60, 100);

        if ((this.getMovingSpeedX() * percentage / 100) <= 1 || (this.getMovingSpeedY() * percentage / 100) <= 1) {
                this.setMovingSpeed(this.getMovingSpeedX(), this.getMovingSpeedX());
        } else {
            this.setMovingSpeed((this.getMovingSpeedX() * percentage / 100), (this.getMovingSpeedY() * percentage / 100));
        }


    }

    @Override
    public void move() {

        reduceSpeed();
        stayNextToHome();
     super.move();


    }
    public void stayNextToHome(){
        double b = genererDouble();
        double a = genererDouble() * b;
        double c;
        if (distancePos() >= Controller.getKilometrage()) {
            if ((this.getMovingSpeedX() - a) + this.getPosition().getX() > this.getStartingPosition().getX()+Controller.getDiametreX()) {
                c=genererDouble()*b;

                this.setMovingSpeed((this.getMovingSpeedX()) - a, (this.getMovingSpeedY()+c));
            }
            if ((this.getMovingSpeedY() - a) + this.getPosition().getY() > this.getStartingPosition().getY()+Controller.getDiametreY()) {

                c=genererDouble()*a;
                this.setMovingSpeed((this.getMovingSpeedX()+c), (this.getMovingSpeedY() - a));
            }
            if ((this.getMovingSpeedX() + a) + this.getPosition().getX() <= this.getStartingPosition().getX()+Controller.getDiametreX()) {
                c=genererDouble()*b;

                this.setMovingSpeed((this.getMovingSpeedX()) + a, (this.getMovingSpeedY()-c));
            }
            if ((this.getMovingSpeedY() + a) + this.getPosition().getY() <= this.getStartingPosition().getY()+Controller.getDiametreY()) {

                c=genererDouble()*a;
                this.setMovingSpeed((this.getMovingSpeedX()-c), (this.getMovingSpeedY() + a));
            }
           // moveABitRandomly();
        }
        //this.getPosition().setPos(this.getPosition().getX() + this.getMovingSpeedX(), this.getPosition().getY() + this.getMovingSpeedY());
    }

}
