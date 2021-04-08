package org.coronabounce.models;

import org.coronabounce.controllers.Controller;

public class Wall {
    private  double thikness;
    private double PositionX;
    private  double PositionY;
   public Wall(double thickness, double PositionX){//ce mur va separer la population en deux populations
       this.thikness=thickness;
       this.PositionX=PositionX;//je fixe le mur pour qu il soit au milieu de la surafcce de la population
       this.PositionY=0;

   }

    public void setPositionY(double positionY) {
        PositionY = positionY;
    }
    public void makeWall(){
       if(this.PositionY+1<Controller.getHeight()) {//le mur va du haut au bas et avance petit a petit
           long start = System.nanoTime();
           while ((System.nanoTime() - start) < 3000) ;//chaque 3 secondes le mur avance d un pixel
            setPositionY(this.PositionY+1);//le mur avance petit a petit pour aller de la postio y=0 et attendre y=zone.height
       }
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
