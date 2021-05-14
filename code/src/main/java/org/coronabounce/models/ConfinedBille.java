package org.coronabounce.models;

import org.coronabounce.mvcconnectors.Controllable;

import java.util.Random;

/**
 * Class which represents moving capacities of individual participated in Lockdown.
 */
public class ConfinedBille extends CoquilleBille {
    private static Random random = new Random();
    /** Current controller, contains all parameters of model. **/
    private Controllable controller;
    /** Memorize the starting position of the Shell. **/
    private Position startingPosition;

    //=================================Constructors==========================================//

    /**
     * {@summary Internal use constructor. Instantiates new ConfinedBille.}
     */
    public ConfinedBille(double speedX, double speedY, Individual individual, Population pop) {
        super(speedX, speedY, individual, pop);
        this.controller = pop.getController();
    }

    /**
     * {@summary External use constructor. Instantiates CoquilleBille by Individual and instantiates his starting position.}
     */
    public ConfinedBille(Individual i, Population pop) {
        super(i, pop);
        this.controller = pop.getController();
        this.startingPosition = this.getCurrentPosition().clone();
    }

    //================================== Intermediate methods====================================//

    /**
     * {@summary This method allows to calculate the distance between the current position of the Shell with its starting position .}
     */
    private  double distancePos() {//cette methode calcule la distance entre la position courante de la Coquille et la position de départ
        return getCurrentPosition().distanceFrom(getStartingPosition());
    }

    private Position getStartingPosition() {return this.startingPosition;}

    private double genererDouble() {//cette methode permet de generer un reel entre 0 et 1
        return random.nextDouble();
    }

    /**
     * {@summary This method is used to generate an integer between terminalInf and terminalSup.}
     */
    private int genererInt(int borneInf, int borneSup) {
        int nb;
        nb = borneInf + random.nextInt(borneSup - borneInf);
        return nb;
    }

    /**
     * {@summary This method allows to reduce the speed of movement of the shells by a random percentage between 60% and 100%.}
     */
    private void reduceSpeed() {
        int percentage = genererInt(60, 100);/**generate an integer between 60 and 100**/

        if ((this.getMovingSpeedX() * percentage / 100) <= 1 || (this.getMovingSpeedY() * percentage / 100) <= 1) {/**check if the speed is not almost zero**/
            this.setMovingSpeed(this.getMovingSpeedX(), this.getMovingSpeedX());/**leave the current speed of the Shell**/
        } else {
            this.setMovingSpeed((this.getMovingSpeedX() * percentage / 100), (this.getMovingSpeedY() * percentage / 100));/**update current shell speed**/
        }
    }

    /**
     * {@summary This method allows to calculate the distance between the current position of the Shell with its starting position if it exceeds a certain mileage then it must be brought back to its starting area .}
     */
    private void stayNextToHome(){
        double b = genererDouble();
        double a = genererDouble() * b;
        double c;
        if (distancePos() >= controller.getKilometrage()) {/** in the case disrance (current position, starting position)> Mileage**/
            if ((this.getMovingSpeedX() - a) + this.getCurrentPosition().getX() > this.getStartingPosition().getX()+controller.getDiameterX()) {
                c=genererDouble()*b;
                
                this.setMovingSpeed((this.getMovingSpeedX()) - a, (this.getMovingSpeedY()+c));/**move back in X to reach starting position**/
            }
            if ((this.getMovingSpeedY() - a) + this.getCurrentPosition().getY() > this.getStartingPosition().getY()+controller.getDiameterY()) {
                
                c=genererDouble()*a;
                this.setMovingSpeed((this.getMovingSpeedX()+c), (this.getMovingSpeedY() - a));/**move back in Y to reach starting position**/
            }
            if ((this.getMovingSpeedX() + a) + this.getCurrentPosition().getX() <= this.getStartingPosition().getX()+controller.getDiameterX()) {
                c=genererDouble()*b;
                
                this.setMovingSpeed((this.getMovingSpeedX()) + a, (this.getMovingSpeedY()-c));/**move forward in X to reach the starting position**/
            }
            if ((this.getMovingSpeedY() + a) + this.getCurrentPosition().getY() <= this.getStartingPosition().getY()+controller.getDiameterY()) {
                
                c=genererDouble()*a;
                this.setMovingSpeed((this.getMovingSpeedX()-c), (this.getMovingSpeedY() + a));/**move forward in Y to reach the starting position**/
            }
        }
    }

    @Override
    public void move() {
        reduceSpeed();/**reduce the speed of the CoquilleBille**/
        stayNextToHome();/**each time you check is in your starting area otherwise you have to return it to your area**/
        super.move();/**update the CoquilleBille position**/
    }
}
