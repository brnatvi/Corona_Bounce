package org.coronabounce.models;


import java.util.Random;

public class Position {

    private int posX;
    private int posY;


    public Position(int x, int y)
    {

     this.posX=x;
     this.posY=y;

    }

   int getX(){ return this.posY;}
   int getY() { return this.posY;}
   void setPos(int x, int y){ this.posY=x;this.posY=y;}

   public Position randomPos()
   {
    Random r= new Random();
    return new Position(r.nextInt(50), r.nextInt(25));

   }





}
