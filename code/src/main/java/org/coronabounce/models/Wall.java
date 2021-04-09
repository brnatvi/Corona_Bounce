package org.coronabounce.models;

import org.coronabounce.controllers.Controller;

public class Wall {
    private double thikness;
    private double positionX;
    private double positionY;

    public Wall(double thickness, double positionX){//ce mur va separer la population en deux populations
       this.thikness=thickness;
       this.positionX=positionX;//je fixe le mur pour qu il soit au milieu de la surafcce de la population
       this.positionY=290;
       System.out.println("new wall in "+positionX);
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }
    public void makeWall(){
       if(this.positionY+1<=Controller.getHeight()) {//le mur va du haut au bas et avance petit a petit
           long start = System.nanoTime();
           while ((System.nanoTime() - start) < 3000) ;//chaque 3 secondes le mur avance d un pixel
            setPositionY(this.positionY+1);//le mur avance petit a petit pour aller de la postio y=0 et attendre y=zone.height
           System.out.println(this.positionY);
       }
    }
    /**
    *{@summary Make CoquilleBille bounce if it will hit a wall.}
    *@param coc The CoquilleBille that we may make bounce.
    */
    public void separatePop(CoquilleBille coc) {
        // if(willCrossWallInX(coc) && willCrossWallInY(coc)){
        //   coc.bounce(true);
        // }
    }
    /**
    *{@summary Return true if it will cross the wall in x.}<br>
    *@param coc The CoquilleBille that we may make bounce.
    */
    public boolean willCrossWallInX(CoquilleBille coc){
      // Population pop=new Population(); //on devrait pas avoir besoin de recréer une population.
      // double posX=coc.getPosition().getX();
      // int nbzones=pop.getNbZones();
      // int zone=coc.InwhichZoneItis(posX,nbzones);
      // double limitInf=coc.repartInZones(nbzones)[zone-1];
      // double limitSup=coc.repartInZones(nbzones)[zone];
      // if(coc.getPosition().getX()<limitSup && Math.abs(posX-limitSup)<=1){return true;}
      // else if(coc.getPosition().getX()>limitInf && Math.abs(posX-limitInf)<=1){return true;}
      double curentX = coc.getPosition().getX();
      double futurX = curentX+coc.getMovingSpeedX();
      if(curentX < positionX && futurX > positionX){ return true;}
      if(curentX > positionX && futurX < positionX){ return true;}
      return false;
    }
    /**
    *{@summary Return true if it will cross the wall in y.}<br>
    *It will hit in Y only if wall is enoth low.<br>
    *@param coc The CoquilleBille that we may make bounce.
    */
    public boolean willCrossWallInY(CoquilleBille coc){
      if(positionY > coc.getPosition().getY()){
        return true;
      }
      return false;
    }

}
