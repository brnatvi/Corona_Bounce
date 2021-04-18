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

    private double genererDouble() {//cette methode permet de generer un reel entre 0 et 1
        return random.nextDouble();
    }

    private int genererInt(int borneInf, int borneSup) {//Cette methode permet de generer un entier entre borneInf et borneSup
        int nb;
        nb = borneInf + random.nextInt(borneSup - borneInf);
        return nb;
    }

    private void reduceSpeed() {// cette emthode permet de reduire la vitesse de déplacement  des coquilles d un pourcentage alétoire entre 60% et 100%
        int percentage = genererInt(60, 100);//generer un entier entre 60 et 100

        if ((this.getMovingSpeedX() * percentage / 100) <= 1 || (this.getMovingSpeedY() * percentage / 100) <= 1) {//verifier si la vitesse n est pas presque nulle
                this.setMovingSpeed(this.getMovingSpeedX(), this.getMovingSpeedX());//laisser la vitesse courante de la Coquille
        } else {
            this.setMovingSpeed((this.getMovingSpeedX() * percentage / 100), (this.getMovingSpeedY() * percentage / 100));//mettre a jour la vitesse courante de la coquille
        }


    }

    @Override
    public void move() {

        reduceSpeed();//reduire la vitesse de la Coquille
        stayNextToHome();//verifier a cahque fois est dans sa zone de départ sinon il faut la rendre a sa zone
        super.move();//mettre a jour la position de la Coquille


    }
    public void stayNextToHome(){//cette methode permet de calculer la distance entre la position courante de la Coquille avce sa postion de départ si elle dépasse un certain kilometrage alors il faut rendre la c
        double b = genererDouble();
        double a = genererDouble() * b;
        double c;
        if (distancePos() >= Controller.getKilometrage()) {// dans le cas disrance(position courante ,position de depart)>Kilometrange
            if ((this.getMovingSpeedX() - a) + this.getPosition().getX() > this.getStartingPosition().getX()+Controller.getDiametreX()) {
                c=genererDouble()*b;

                this.setMovingSpeed((this.getMovingSpeedX()) - a, (this.getMovingSpeedY()+c));//reculer en X pour atteindre position de depart
            }
            if ((this.getMovingSpeedY() - a) + this.getPosition().getY() > this.getStartingPosition().getY()+Controller.getDiametreY()) {

                c=genererDouble()*a;
                this.setMovingSpeed((this.getMovingSpeedX()+c), (this.getMovingSpeedY() - a));//reculer en Y pour atteindre la position de départ
            }
            if ((this.getMovingSpeedX() + a) + this.getPosition().getX() <= this.getStartingPosition().getX()+Controller.getDiametreX()) {
                c=genererDouble()*b;

                this.setMovingSpeed((this.getMovingSpeedX()) + a, (this.getMovingSpeedY()-c));//avancer en X pour atteindre la position de départ
            }
            if ((this.getMovingSpeedY() + a) + this.getPosition().getY() <= this.getStartingPosition().getY()+Controller.getDiametreY()) {

                c=genererDouble()*a;
                this.setMovingSpeed((this.getMovingSpeedX()-c), (this.getMovingSpeedY() + a));//avancer en Y pour atteindre la position de départ
            }
          
        }
        
    }

}
