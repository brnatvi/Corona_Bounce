package org.coronabounce.models;


import java.util.Random;

public class Position {

    private double posX;
    private double posY;
    private static   double width;
    private static  double  height;

   double getX(){ return this.posX;}
   double getY() { return this.posY;}

    public static void setHeight(double height) {
        Position.height = height;
    }

    public static void setWidth(double width){
        Position.width = width;
    }

    void setPos(int x, int y){ this.posY=x;this.posY=y;}

   public Position ()
   {
    Random r= new Random();
    this.posX=r.nextInt((int) width);
    this.posY=r.nextInt((int)height);

   }





}
