package org.coronabounce.models;


import java.util.Random;

public class Position {

    private int posX;
    private int posY;

   int getX(){ return this.posX;}
   int getY() { return this.posY;}
   void setPos(int x, int y){ this.posY=x;this.posY=y;}

   public Position ()
   {
    Random r= new Random();
    this.posX=r.nextInt(50);
    this.posY=r.nextInt(25);

   }





}
