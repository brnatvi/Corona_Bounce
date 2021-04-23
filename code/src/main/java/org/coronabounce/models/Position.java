package org.coronabounce.models;

import org.coronabounce.mvcconnectors.Controllable;
import org.coronabounce.models.exceptions.ToMuchPointsException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Position implements Cloneable{
    /** To know if there is already an Individual at this location. */
    private static List<Position> listTakenPositions = new ArrayList<Position>();
    // the 2 important value.
    private double posX;
    private double posY;
    //static value for lest space use.
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
    public int getListTakenPositionsSize(){return listTakenPositions.size();}

    // FUNCTIONS ---------------------------------------------------------------

    /**
     * Check if position of instance is distinguished from all other point's one
     * @return
     */
    private boolean isEmpty(){
        for (Position pos : this.listTakenPositions){
          if(distanceFrom(pos) < minLimit*2.0){return false;}
        }
        return true;
    }

    private boolean isInWall(){
      //is in wall part :
      //todo only if we whant to make existing wall.
      return false;
    }
    @Override
    public Position clone(){
      return new Position(posX,posY);
    }
    public double distanceFrom(Position pos){
      double x1 = getX();
      double x2 = pos.getX();
      double y1 = getY();
      double y2 = pos.getY();
      return  Math.sqrt((x1 -x2) * (x1 -x2) + (y1 -y2) * (y1 -y2));
    }
    public static void cleanListTakenPositions(){
      listTakenPositions = new ArrayList<Position>();
    }
}
