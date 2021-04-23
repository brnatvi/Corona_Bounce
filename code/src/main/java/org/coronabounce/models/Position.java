package org.coronabounce.models;

import org.coronabounce.mvcconnectors.Controllable;
import org.coronabounce.models.exceptions.ToMuchPointsException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Position implements Cloneable{
    // Pour savoir quelles positions de la Zone sont déjà prises(Il y'a déjà un individu dessus)
    private static List<Position> listTakenPositions =new ArrayList<>();
    private double posX;
    private double posY;
    private static double minLimit;
    private static double maxLimitX;
    private static double maxLimitY;
    private static Random r = new Random();

    // CONSTRUCTORS ------------------------------------------------------------
    public Position(Controllable controller, boolean chooseAUniquePosition) {

        // initial position according bounds of Zone & radius of point
        // (because it is the center of point who takes position)
        this.minLimit = controller.getRadiusDot();
        this.maxLimitX = controller.getSpaceSize()[0] - minLimit;
        this.maxLimitY = controller.getSpaceSize()[1] - minLimit;

        // takes random number in interval from minLimit to maxLimit
        if(chooseAUniquePosition){
          int k=0;
          do {
            k++;
            this.posX = Math.abs(r.nextInt((int) (maxLimitX - minLimit))) + minLimit;
            this.posY = Math.abs(r.nextInt((int) (maxLimitY - minLimit))) + minLimit;
            // posX=(int)(posX/10)*10;
            // posY=(int)(posY/10)*10;
          } while ((!isEmpty() || isInWall()) && k<1000);
          if(k>=1000){
            throw new ToMuchPointsException();
          }
        }else{
          this.posX = Math.abs(r.nextInt((int) (maxLimitX - minLimit))) + minLimit;
          this.posY = Math.abs(r.nextInt((int) (maxLimitY - minLimit))) + minLimit;
        }
        listTakenPositions.add(this);
    }
    public Position(Controllable c){
      this(c, true);
    }
    private Position(double x, double y){
      posX=x;
      posY=y;
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

    // FUNCTIONS ---------------------------------------------------------------

    /**
     * Check if position of instance is distinguished from all other point's one
     * @return
     */
    private boolean isEmpty(){
        //TODO use thinkness.
        for (Position pos : this.listTakenPositions)
        {
            if(pos.posX==this.posX && pos.posY==this.posY) return false;//Position déjà prise
        }
        return true;
    }

    private boolean isInWall(){
      //We already check that it will not be out of zone.
      // if(posX < minLimit || posX > maxLimitX){
      //   return true;
      // }
      // if(posY < minLimit || posY > maxLimitY){
      //   return true;
      // }
      //is in wall part :
      //todo only if we whant to make existing wall.
      return false;
    }
    @Override
    public Position clone(){
      return new Position(posX,posY);
    }
}
