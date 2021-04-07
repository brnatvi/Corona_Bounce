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
        int percentage = genererInt(90, 100);

        Random r = new Random();

        if ((this.getMovingSpeedX() * percentage / 100) <= 1 || (this.getMovingSpeedY() * percentage / 100) <= 1) {
            {
                this.setMovingSpeed(this.getMovingSpeedX(), this.getMovingSpeedY());
            }
        } else {
            this.setMovingSpeed((this.getMovingSpeedX() * percentage / 100), (this.getMovingSpeedY() * percentage / 100));
        }


    }


    public void move() {
        bounceIfOutOfZone();
        reduce_speed();
        double b = genererDouble();
        double a = genererDouble() * b;
        //System.out.println(distancePos());
        if (distancePos() >= Controller.getKilometrage()) {
            if ((this.getMovingSpeedX() - a) + this.getPosition().getX() > this.getStartingPosition().getX()) {
                this.setMovingSpeed((this.getMovingSpeedX()) - a, (this.getMovingSpeedY()));
            }
            if ((this.getMovingSpeedY() - a) + this.getPosition().getY() > this.getStartingPosition().getY()) {
                this.setMovingSpeed((this.getMovingSpeedX()), (this.getMovingSpeedY() - a));
            }
            if ((this.getMovingSpeedX() + a) + this.getPosition().getX() <= this.getStartingPosition().getX()) {
                this.setMovingSpeed((this.getMovingSpeedX()) + a, (this.getMovingSpeedY()));
            }
            if ((this.getMovingSpeedY() + a) + this.getPosition().getY() <= this.getStartingPosition().getY()) {
                this.setMovingSpeed((this.getMovingSpeedX()), (this.getMovingSpeedY() + a));
            }

        }

        this.getPosition().setPos(this.getPosition().getX() + this.getMovingSpeedX(), this.getPosition().getY() + this.getMovingSpeedY());

    }
}

