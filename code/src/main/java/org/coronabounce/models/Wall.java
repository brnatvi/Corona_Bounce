package org.coronabounce.models;

import org.coronabounce.controllers.Controller;

public class Wall {
    private  double thikness;
    private double PositionX;
   public Wall(double thickness, double PositionX){
       this.thikness=thickness;
       this.PositionX=PositionX;

   }

    public void separatePop(CoquilleBille coc) {
        double Vx = coc.getMovingSpeedX();
        double Vy = coc.getMovingSpeedY();
        double posX=coc.getPosition().getX();
        Population pop =new Population();
        int nbzones=pop.getNbZones();

        int zone=coc.InwhichZoneItis(posX,nbzones);
        double limitInf= coc.repartInZones(nbzones)[zone-1];
        double limitSup=coc.repartInZones(nbzones)[zone];

        if(coc.getPosition().getX()<limitSup && Math.abs(posX-limitSup)<=1)
            coc.setMovingSpeed(-1*Vx,Vy);
        else if(coc.getPosition().getX()>limitInf && Math.abs(posX-limitInf)<=1)
            coc.setMovingSpeed(-1*Vx,Vy);

    }




}
