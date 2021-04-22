package org.coronabounce.models;

import org.coronabounce.controllers.Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class Position   {
    // Pour savoir quelles positions de la Zone sont déjà prises(Il y'a déjà un individu dessus)
    private static List<Position> listTakenPositions =new ArrayList<>();
    private double posX;
    private double posY;
    private static Random r = new Random();

    // CONSTRUCTORS ------------------------------------------------------------
    public Position() {
        do {
            this.posX = Math.abs(r.nextInt((int) Controller.getWidth()));
            this.posY = Math.abs(r.nextInt((int) Controller.getHeight()));
        } while (!isEmpty() || isInWallOrOutOfZone());
        listTakenPositions.add(this);
        System.out.println(listTakenPositions.size());//@a
    }

    public Position(double posX, double posY) {
        if (posX <= Controller.getWidth() && posY <= Controller.getHeight()) {
            this.posX = posX;
            this.posY = posY;
        } else {
            this.posX = 0;
            this.posY = 0;
            System.out.println("Unable to set position in Constructors of position for x:"+posX+" y:"+posY);
        }
        listTakenPositions.add(this);
        System.out.println(listTakenPositions.size());//@a
    }


    // GET SET -----------------------------------------------------------------
    public double getX() {return this.posX;}
    public double getY() {return this.posY;}

    public void setPos(double x, double y) {
      if(x<0){x=0;}
      else if(x> Controller.getWidth()){x=Controller.getWidth();}
      if(y<0){y=0;}
      else if(y>Controller.getHeight()){y=Controller.getHeight();}
      this.posX = x;
      this.posY = y;
    }

    public void setPosX(double posX) {
        if (posX <= Controller.getWidth() ){
            this.posX = posX;
        }else{
            this.posX = 0;
        }
    }

    public void setPosY(double posY) {
        if(posY <= Controller.getHeight()) {
            this.posY = posY;
        }else{
            this.posY = 0;
        }
    }

    // FUNCTIONS ---------------------------------------------------------------
    private boolean isEmpty()
    {
        for (Position pos : this.listTakenPositions)
        {
            if(pos.posX==this.posX && pos.posY==this.posY) return false;//Position déjà prise
        }
        return true;
    }
    private boolean isInWallOrOutOfZone(){
      //TODO
      return false;
    }
}
