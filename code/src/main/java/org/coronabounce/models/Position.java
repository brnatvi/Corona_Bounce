package org.coronabounce.models;


import java.util.Random;

public class Position {

    private double posX;
    private double posY;

   public double getX(){ return this.posX;}
   public double getY() { return this.posY;}


    void setPos(double x, double y){
        if(x <= Zone.getWidth() && y <=Zone.getHeight()) {
            this.posX = x;
            this.posY = y;
        }

   }

   public Position ()
   {
    Random r= new Random();
    this.posX=r.nextInt((int) Zone.getWidth());
    this.posY=r.nextInt((int) Zone.getHeight());

   }
   public Position(double PosX,double PosY){
       if(PosX <= Zone.getWidth() && PosY <=Zone.getHeight()) {
           this.posX = PosX;
           this.posY = PosY;
       }else{
           this.posX=0;
           this.posY=0;
       }
   }





}
