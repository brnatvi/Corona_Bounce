package org.coronabounce.models;

public class Wall {


    public void separatePop(CoquilleBille coc) {
        double Vx = coc.getMovingSpeedX();
        double Vy = coc.getMovingSpeedY();
//<>
        /**Todo : changer la répartition de la population de sorte qu'on ait deux Zones(Les individus de peuvent bouger que dans leur zone respective
         Ce sera géré lors de la création des individus(répartir les individus de manière assez équilibrée entre les 2 et avoir un paramètre
         qui dit si tel individu est dans Zone 1 ou 2 **/

        if (coc.getPosition().getX() < (Zone.getWidth() / 2) && Math.abs(coc.getPosition().getX() - (Zone.getWidth() / 2)) < 1)
            coc.setMovingSpeed(-1 * Vx, Vy);
        if (coc.getPosition().getX() > (Zone.getWidth() / 2) && Math.abs(coc.getPosition().getX() - (Zone.getWidth() / 2)) < 1)
            coc.setMovingSpeed(-1 * Vx, Vy);

    }
}







