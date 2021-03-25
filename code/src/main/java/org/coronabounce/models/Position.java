package org.coronabounce.models;

import org.coronabounce.controllers.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class Position {
    // Pour savoir quelles positions de la Zone sont déjà prises(Il y'a déjà un individu dessus)

    private List<Position> listTakenPositions =new ArrayList<>();
    private double posX;
    private double posY;


    public double getX() {
        return this.posX;
    }

    public double getY() {
        return this.posY;
    }


    public void setPos(double x, double y) {
      if(x<0){x=0;}
      else if(x> Controller.getWidth()){x=Controller.getWidth();}
      if(y<0){y=0;}
      else if(y>Controller.getHeight()){y=Controller.getHeight();}
      this.posX = x;
      this.posY = y;
        /*if (x >= 10 && x <= Zone.getWidth() && y >= 10 && y <= Zone.getHeight()) {
            this.posX = x;
            this.posY = y;
        }*/

    }

    public Position() {

        Random r = new Random();
        this.posX = Math.abs(r.nextInt((int) Controller.getWidth()));
        this.posY = Math.abs(r.nextInt((int) Controller.getHeight()));



        while( !isEmpty(posX,posY))
        {

            this.posX = Math.abs(r.nextInt((int) Controller.getWidth()));
            this.posY = Math.abs(r.nextInt((int) Controller.getHeight()));
        }

    }

    public Position(double PosX, double PosY) {
        if (PosX <= Controller.getWidth() && PosY <= Controller.getHeight()) {
            this.posX = PosX;
            this.posY = PosY;
        } else {
            this.posX = 0;
            this.posY = 0;
        }
    }

    public boolean isEmpty(double PosX, double PosY)
    {
        for (Position pos : this.listTakenPositions)
        {
            if(pos.posX==PosX && pos.posY==posY) return false;//Position déjà prise
        }
        listTakenPositions.add(new Position(PosX,PosY));
        return true;
    }


}
