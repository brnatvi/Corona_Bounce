package org.coronabounce.models;

import org.coronabounce.controllers.Controller;

public class Wall {
    private double thikness;
    private double positionX;
    private double positionY;


    public Wall(double thickness,double positionX,double positionY){//ce mur va separer la population en deux populations
       this.thikness=thickness;
       this.positionX=positionX;//je fixe le mur pour qu il soit au milieu de la surafcce de la population
       this.positionY=positionY;

    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }
    public static  void makeWall(int n, Wall mur){
         final Wall_Simulations w=new Wall_Simulations();
        switch (n){
            case 0:

                //Wall mur0=new Wall(Controller.getThickness(),0,Controller.getHeight()/2);
                w.makeWallX(mur);
                break;

            case 1:
               // Wall mur1=new Wall(Controller.getThickness(),Controller.getWidth()/2,0);
                w.makeWallY(mur);
                break;
            case 2:
                w.makeWallOposite();
                break;
            default:break;
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
