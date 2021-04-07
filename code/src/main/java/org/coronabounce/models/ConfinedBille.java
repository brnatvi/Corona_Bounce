package org.coronabounce.models;

import org.coronabounce.controllers.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConfinedBille extends CoquilleBille {

    public ConfinedBille(double speedX, double speedY, Individual individual) {
        super(speedX, speedY, individual);
    }

    public ConfinedBille(Individual i) {
        super(i);
    }

    double genererDouble() {
        Random random = new Random();
        return random.nextDouble();
    }

    int genererInt(int borneInf, int borneSup) {
        Random random = new Random();
        int nb;
        nb = borneInf + random.nextInt(borneSup - borneInf);
        return nb;
    }

    public void reduce_speed() {
        int percentage = genererInt(60, 100);

        Random r = new Random();

        if ((this.getMovingSpeedX() * percentage / 100) <= 1 || (this.getMovingSpeedY() * percentage / 100) <= 1) {
            {

                this.setMovingSpeed(this.getMovingSpeedX(), this.getMovingSpeedX());
            }
        } else {
            this.setMovingSpeed((this.getMovingSpeedX() * percentage / 100), (this.getMovingSpeedY() * percentage / 100));
           // System.out.println("je sui kenza");
        }


    }


    public void move() {
        bounceIfOutOfZone();
        reduce_speed();
        double b = genererDouble();
        double a = genererDouble() * b;
        double c;
        if (distancePos() >= Controller.getKilometrage()) {
            if ((this.getMovingSpeedX() - a) + this.getPosition().getX() > this.getStartingPosition().getX()+Controller.getDiametreX()) {
                c=genererDouble()*b;
                //System.out.println("je suis la 1");
                this.setMovingSpeed((this.getMovingSpeedX()) - a, (this.getMovingSpeedY()+c));
            }
            if ((this.getMovingSpeedY() - a) + this.getPosition().getY() > this.getStartingPosition().getY()+Controller.getDiametreY()) {
                //System.out.println("je suis la 2");
                c=genererDouble()*a;
                this.setMovingSpeed((this.getMovingSpeedX()+c), (this.getMovingSpeedY() - a));
            }
            if ((this.getMovingSpeedX() + a) + this.getPosition().getX() <= this.getStartingPosition().getX()+Controller.getDiametreX()) {
                c=genererDouble()*b;
               // System.out.println("je suis la3 ");
                this.setMovingSpeed((this.getMovingSpeedX()) + a, (this.getMovingSpeedY()-c));
            }
            if ((this.getMovingSpeedY() + a) + this.getPosition().getY() <= this.getStartingPosition().getY()+Controller.getDiametreY()) {
                //System.out.println("je suis la4 ");
                c=genererDouble()*a;
                this.setMovingSpeed((this.getMovingSpeedX()-c), (this.getMovingSpeedY() + a));
            }

        }
        this.getPosition().setPos(this.getPosition().getX() + this.getMovingSpeedX(), this.getPosition().getY() + this.getMovingSpeedY());
        if (this.getStartingPosition().getX() == this.getPosition().getX() && this.getPosition().getY() == this.getStartingPosition().getY()) {
            //this.setMovingSpeed(0,0);
            System.out.println("je suis la 1");
            //long start1 = System.nanoTime();
            //while ((System.nanoTime() - start1) < 20000000) ;
            this.setMovingSpeed(getRandomMovingSpeed(5), getRandomMovingSpeed(5));
            this.getPosition().setPos(this.getPosition().getX() + this.getMovingSpeedX(), this.getPosition().getY() + this.getMovingSpeedY());

        }

    }
}

