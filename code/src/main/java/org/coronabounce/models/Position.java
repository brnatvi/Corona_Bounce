package org.coronabounce.models;

import org.coronabounce.mvcconnectors.Controllable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Position   {
    // Pour savoir quelles positions de la Zone sont déjà prises(Il y'a déjà un individu dessus)
    private static List<Position> listTakenPositions =new ArrayList<>();
    private double posX;
    private double posY;
    private double minLimit;
    private double maxLimitX;
    private double maxLimitY;
    private static Random r = new Random();
    private Controllable controller;

    // CONSTRUCTORS ------------------------------------------------------------
    public Position(Controllable controller) {

        this.maxLimitX = controller.getSpaceSize()[0] - controller.getRadiusDot();
        this.maxLimitY = controller.getSpaceSize()[1] - controller.getRadiusDot();
        this.minLimit = controller.getRadiusDot();

        this.controller = controller;
        do {
            this.posX = Math.abs(r.nextInt((int) (maxLimitX - controller.getRadiusDot()))) + controller.getRadiusDot();
            this.posY = Math.abs(r.nextInt((int) (maxLimitY - controller.getRadiusDot()))) + controller.getRadiusDot();
        } while (!isEmpty() || isInWallOrOutOfZone());
        listTakenPositions.add(this);
        System.out.println(listTakenPositions.size());//@a
    }

    public Position(double posX, double posY, Controllable cont) {
        this.controller = cont;
        if (posX <= maxLimitX && posY <= maxLimitY) {
            this.posX = posX;
            this.posY = posY;
        } else {
            this.posX = minLimit;
            this.posY = minLimit;
            System.out.println("Unable to set position in Constructors of position for x:"+posX+" y:"+posY);
        }
        listTakenPositions.add(this);
        System.out.println(listTakenPositions.size());//@a
    }


    // GET SET -----------------------------------------------------------------
    public double getX() {return this.posX;}
    public double getY() {return this.posY;}

    public void setPos(double x, double y) {           //TODO add condition do not set in positions of walls
      if (x < 0) {x = minLimit;}
      else if (x > maxLimitX) {x = maxLimitX;}
      if (y < 0) {y = minLimit;}
      else if (y > maxLimitY){y = maxLimitY;}
      this.posX = x;
      this.posY = y;
    }

    public void setPosX(double posX) {
        if (posX <= maxLimitX ){
            this.posX = posX;
        }else{
            this.posX = minLimit;
        }
    }

    public void setPosY(double posY) {
        if(posY <= maxLimitY) {
            this.posY = posY;
        }else{
            this.posY = minLimit;
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
