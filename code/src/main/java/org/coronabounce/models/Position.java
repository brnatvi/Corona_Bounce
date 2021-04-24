package org.coronabounce.models;

import org.coronabounce.mvcconnectors.Controllable;
import org.coronabounce.models.exceptions.ToMuchPointsException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * {@summary Class used to save an x &#38; an y.}<br>
 * Position are always between x &#38; y limits of the controller.<br>
 */
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
    /**
     * {@summary Main constructor.}
     * @param controller the controller used to fix limits.
     * @param chooseAUniquePosition If true it will try to fined a unique position in the Zone.
     */
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
    /**
     * {@summary Secondary constructor with chooseAUniquePosition at true.}
     * @param controller the controller used to fix limits.
     */
    public Position(Controllable controller){
      this(controller, true);
    }
    /**
     * {@summary Private constructor use only by clone.}
     */
    private Position(double x, double y){
      posX=x;
      posY=y;
    }

    // GET SET -----------------------------------------------------------------
    public double getX() {return this.posX;}
    public double getY() {return this.posY;}
    /**
     * {@summary Set a new x &#38; y.}<br>
     * It will set position only in the limits of the Zone.<br>
     * Dot radius is used to avoid GUI glitch.<br>
     */
    public void setPos(double x, double y) {
      //TODO add condition do not set in positions of walls
      if (x < minLimit) {x = minLimit;}
      else if (x > maxLimitX) {x = maxLimitX;}
      if (y < minLimit) {y = minLimit;}
      else if (y > maxLimitY){y = maxLimitY;}
      this.posX = x;
      this.posY = y;
    }
    public int getListTakenPositionsSize(){return listTakenPositions.size();}

    // FUNCTIONS ---------------------------------------------------------------

    /**
     * Check if position of instance is distinguished from all other point's one
     * @return True if curent position (see as a circle) is empty.
     */
    private boolean isEmpty(){
        for (Position pos : this.listTakenPositions){
          if(distanceFrom(pos) < minLimit*2.0){return false;}
        }
        return true;
    }
    /**
     * Check if position is in wall
     * @return True if curent position is in a wall.
     */
    private boolean isInWall(){
      //is in wall part :
      //todo only if we whant to make existing wall.
      return false;
    }
    /**
     * Standard clone function.
     */
    @Override
    public Position clone(){
      return new Position(posX,posY);
    }
    /**
     * {@summary Get distance from an other position.}
     * @param pos the other Position.
     * @return the distance from this to pos.
     */
    public double distanceFrom(Position pos){
      double x1 = getX();
      double x2 = pos.getX();
      double y1 = getY();
      double y2 = pos.getY();
      return  Math.sqrt((x1 -x2) * (x1 -x2) + (y1 -y2) * (y1 -y2));
    }
    /**
     * {@summary Clean the list of the taken positions.}
     * It allowed to create new Position without checking if old Position interfer with this 1.
     */
    public static void cleanListTakenPositions(){
      listTakenPositions = new ArrayList<Position>();
    }
}
