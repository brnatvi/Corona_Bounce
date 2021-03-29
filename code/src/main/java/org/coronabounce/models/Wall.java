package org.coronabounce.models;

import org.coronabounce.controllers.Controller;

public class Wall {


    public void separatePop(CoquilleBille coc) {
        double Vx = coc.getMovingSpeedX();
        double Vy = coc.getMovingSpeedY();
/**To do : changer la répartition de la population de sorte qu'on ait deux (ou plus)Zones(Les individus de peuvent bouger que dans leur zone respective**/

        if (coc.getPosition().getX() < (Controller.getWidth() / 2) && Math.abs(coc.getPosition().getX() - (Controller.getWidth() / 2)) <= 1)
            coc.setMovingSpeed(-1 * Vx, Vy);
        if (coc.getPosition().getX() > (Controller.getWidth() / 2) && Math.abs(coc.getPosition().getX() - (Controller.getWidth() / 2)) <= 1)
            coc.setMovingSpeed(-1 * Vx, Vy);

    }




}
