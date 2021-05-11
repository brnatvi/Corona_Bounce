package org.coronabounce.models;

import org.coronabounce.mvcconnectors.Controllable;

import java.util.Random;

/**
 * Class which represents moving capacities of person/individual.
 */
public class CoquilleBille {

    /** A moving speed in x to move faster or slower. */
    private double movingSpeedX;
    /** A moving speed in y to move faster or slower. */
    private double movingSpeedY;
    /** Current controller contains all parameters. **/
    private Controllable controller;
    /** Current population where Shell reside. **/
    private Population population;
    /** Individual that the Shell will contain. **/
    private Individual individual;
    /** Current position of the Shell. **/
    private Position currentPosition;
    /** Shell identifier (in order to redefine equals). **/
    private final int id;
    /** The number of Shell already created. **/
    private static int idCpt=0;
    // private boolean canBounceMore;
    /** Random number to set speed points. **/
    private Random r = new Random();
    private double minReboundSpeed=3;
    /** Upper bound to set speed points. **/
    private static int MAX_SPEED = 4;
    private boolean canRebound;

    /**
     * {@summary Internal use constructor. Instantiates a new Coquille bille.}
     * @param speedX     the speed by x
     * @param speedY     the speed by y
     * @param individual the individual
     * @param pop        the population
     */
    public CoquilleBille(double speedX,double speedY, Individual individual, Population pop){

        this.population = pop;
        this.individual = individual;
        this.controller = pop.getController();
        try {
          this.currentPosition = new Position(controller);
        }catch (Exception e) {
          this.currentPosition = new Position(controller,false);
          System.out.println("Position of the point have been set, but it fail to fined a free space.");
        }

        id=idCpt++;                     /**increment the number of Shells that exist**/
        this.movingSpeedX = speedX;       /**update movingSpeedX**/
        this.movingSpeedY = speedY;       /** update movingSpeedY**/
        this.canRebound = true;
    }

    /**
     * {@summary External use constructor. Instantiates Coquille bille by Individual and set him a random speed. }
     * @param individual the individual
     * @param pop        the population
     */
    public CoquilleBille(Individual individual, Population pop){
        this(0,0,individual, pop);
        this.addSpeedX(MAX_SPEED);
        this.addSpeedY(MAX_SPEED);
    }

    //============================================ Getters / Setters =================================================//

    /**
     * {@summary Gets id int.}
     * @return the id
     */
    public int getId(){return id;}

    /**
     * {@summary Gets individual.}
     * @return the individual
     */
    public Individual getIndividual() {return individual;}

    /**
     * {@summary Sets individual.}
     * @param individual the individual
     */
    public void setIndividual(Individual individual) { this.individual=individual; }

    /**
     * {@summary Gets population population.}
     * @return the population
     */
    public Population getPopulation(){ return individual.getPopulation(); }

    /**
     * {@summary Gets current position.}
     * @return the current position
     */
    public Position getCurrentPosition() {return this.currentPosition;}

    /**
     * {@summary Gets moving speed in x.}
     * @return the moving speed x
     */
    public double getMovingSpeedX() { return this.movingSpeedX;}

    /**
     * {@summary Gets moving speed in y.}
     * @return the moving speed y
     */
    public double getMovingSpeedY() { return this.movingSpeedY;}

    /**
     * {@summary Gets resulting moving speed.}
     * @return the moving speed
     */
    public double getMovingSpeed() {
        return Math.sqrt((this.movingSpeedX*this.movingSpeedX)+(this.movingSpeedY*this.movingSpeedY));
    }

    /**
     * {@summary Sets moving speed by x and by y.}
     * @param Vx the moving speed in x
     * @param Vy the moving speed in y
     */
    public void setMovingSpeed(double Vx, double Vy) {
        this.movingSpeedX=Vx;
        this.movingSpeedY=Vy;
    }

    /**
     * {@summary Setter of position. Used in tests}<br>
     */
    public void setPos(double x, double y){getCurrentPosition().setPos(x,y);}

    /**
     * {@summary Capability to rebound getter. }
     */
    public boolean getCanRebound(){return this.canRebound;}

    /**
     * {@summary Capability to rebound setter. }
     */
    public void setCanRebound(boolean canRebound){this.canRebound = canRebound;}

    //===================================== Moving ===================================================================//

    /**
     * {@summary The moving function.}<br>
     * Speed will be modified if this hurt a limit of the zone.
     */
    public void move(){
        //bounceIfHitOtherCoquilleBille();
        ricochetAll();
        bounceIfOutOfZone();
        bounceIfHitWall();
        // if(false){
        //   // System.out.println("Can not bounce: "+id);//@a
        //   canBounceMore=false;
        // }else{
          // System.out.println("Bounce: "+id);//@a
          // canBounceMore=true;
        // }
        this.currentPosition.setPos(this.currentPosition.getX()+this.movingSpeedX,this.currentPosition.getY()+this.movingSpeedY);
    }

    //=================================== Bounce off walls ===========================================================//

    /**
     * {@summary Bounce if this will go out of the zone.}<br>
     */
    protected boolean bounceIfOutOfZone(){
        boolean haveBounce = false;
        if (outOfX(currentPosition.getX()+movingSpeedX)) {/**check if the position X of the Shell reaches the boundary X mark of the rebound zone**/
            bounce(true);/**bounce along X**/
            haveBounce=true;
        }
        if (outOfY(currentPosition.getY()+movingSpeedY)) {/**check if the position Y of the Shell reaches the boundary Y mark of the rebound zone**/
            bounce(false);/**bounce along Y **/
            haveBounce=true;
        }
        return haveBounce;
    }

    /**
     * {@summary Bounce if this will hit a wall.}<br>
     */
    protected boolean bounceIfHitWall(){
        for (Wall wall : getPopulation().getListWall()) {
            switch (wall.needToBounceBecauseOfWall(this)) {
                case 0:
                bounce(false);
                return true;
                case 1:
                bounce(true);
                return true;
                case 2:
                bounce();
                return true;
            }
        }
        return false;
    }
//    /**
//     *{@summary bounce if this will hit an other CoquilleBille.}<br>
//     *All CoquilleBille that this can hit are in population.
//     */
//   public void bounceIfHitOtherCoquilleBille(){
//     for(CoquilleBille coc : population.getAllPoints()){
//       if(!coc.equals(this) && getCurrentPosition().distanceFrom(coc.getCurrentPosition()) <= (2* controller.getRadiusDot())){
//         if(coc.InX(this)){
//         coc.bounce(true);
//         this.bounce(true);
//         }
//         if(coc.InY(this)){
//           coc.bounce(false);
//           this.bounce(false);
//         }
//       }
//     }
//   }

    /**
     * {@summary Bounce.}<br>
     * @param inX True if bounce in x coor.
     */
    protected void bounce(boolean inX){// elle sert a gerer les rebondissemnt
         if(inX){ movingSpeedX*=-1;}//inverser le vecteur vitesse Vx en le multipliant par -1
         else{ movingSpeedY*=-1; }//inverser le vecteur vitesse Vy en le multipliant par -1
    }

    /**
     * {@summary Bounce in x and y.}<br>
     */
    protected void bounce(){
         bounce(true);
         bounce(false);
    }

    //==================================== Mutual Bounces ============================================================//

    /**
     * {@summary Makes ricochet if necessary with all other points.}
     */
    public void ricochetAll()
    {
        for (CoquilleBille coc : population.getAllPoints())
        {
            if (!this.equals(coc))
            {
                this.ricochet(coc, false);
            }
        }
    }

    /**
     * {@summary Makes ricochet if it does not made yet.}
     * @param coc        coquilleBille to make ricochet with.
     * @param isRicochet the is ricochet
     * @return true if ricochet was made.
     */
    public boolean ricochet(CoquilleBille coc, boolean isRicochet)
    {
        boolean isDone = false;
        if (!isRicochet && isNear(coc))
        {
            if (this.canRebound && coc.getCanRebound())
            {
                double tmpX = coc.getMovingSpeedX();
                double tmpY = coc.getMovingSpeedY();
                coc.setMovingSpeed(this.movingSpeedX, this.movingSpeedY);
                this.setMovingSpeed(tmpX, tmpY);
            }
            else if (!coc.getCanRebound())
            {
                this.bounce(true);
                this.bounce(false);
            }
            isDone = true;
        }
        return isDone;
    }

    /**
     * {@summary Measure distance between current point and point passing as parameter.}
     * @param coc coquilleBille to measure distance with.
     * @return true if distance less than or equal to two radius of points (so they are contiguous).
     */
    public boolean isNear(CoquilleBille coc)
    {
        if (getCurrentPosition().distanceFrom(coc.getCurrentPosition()) <= 2*controller.getRadiusDot())
        {
            return true;
        }
        return false;
    }

    //======================================= Auxiliary functions ====================================================//

    /**
     * {@summary Return true if x coordinate is out the the Zone at next move.}
     * Public only for test.
     * @param x the x coordinate.
     * @return the boolean.
     */
    public boolean outOfX(double x){
        if(x<= getPopulation().getRadiusDot() || x>= controller.getSpaceSize()[0]- getPopulation().getRadiusDot()){return true;}//verifie si la position de la Coquille dépasse les bornes de la zone par rapport a X
        return false;
    }

    /**
     *{@summary Return true if y coordinate is out the the Zone at next move.}
     *@param y the y coordinate.
     * @return the boolean.
     */
    private boolean outOfY(double y){//verifie si la position de la Coquille dépasse les bornes de la zone par rapport a Y
        if(y<= getPopulation().getRadiusDot() || y>= controller.getSpaceSize()[1]- getPopulation().getRadiusDot()){return true;}
        return false;
    }

    /**
     *{@summary Return true if there is obstacle (boundary) to contaminate another CoquilleBille.}
     *@param coc some coquillebille.
     *@return the boolean.
     */
    public boolean isWallBetween(CoquilleBille coc)
    {
        // is scenario with boundaries
        if (population.getIsWall())
        {
            // istance is enough to contaminate
            if (this.isOnContaminationRadius(coc))
            {
                //wall length is enough
                if (this.getCurrentPosition().getY() <= this.population.getHeigthsOfWalls().get(0))
                {
                    //if some wall is between two points
                    for (int i = 0; i < controller.getWallsCount(); i++)
                    {
                        double wallX = this.population.getPositionsOfWalls().get(i);
                        if (this.getCurrentPosition().getX() <= wallX && wallX <= coc.getCurrentPosition().getX()
                        || coc.getCurrentPosition().getX() <= wallX && wallX <= this.getCurrentPosition().getX())
                        {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     *{@summary Check if two coquillebille(s) are on a distance enough to contamination.}
     *@param coc some coquillebille.
     *@return the boolean.
     */
    private boolean isOnContaminationRadius(CoquilleBille coc)
    {
        if (getCurrentPosition().distanceFrom(coc.getCurrentPosition()) <= controller.getContaminationRadius())
        {
            return true;
        }
        return false;
    }
    //******************************************************************************************************************************//

    /**
     * {@summary Set a random moving speed to SpeedX between -maxSpeed &#38; maxSpeed.}<br>
     * @param maxSpeed the max speed to add.
     */
    public void addSpeedX(int maxSpeed){
        if(maxSpeed<1){maxSpeed=1;}
        if(r.nextBoolean()){maxSpeed=maxSpeed*(-1);}
        this.movingSpeedX = r.nextDouble()*maxSpeed;
    }

    /**
     * {@summary Set a random moving speed to SpeedY between -maxSpeed &#38; maxSpeed.}<br>
     * @param maxSpeed the max speed to add.
     */
    public void addSpeedY(int maxSpeed){
        if(maxSpeed<1){maxSpeed=1;}
        if(r.nextBoolean()){maxSpeed=maxSpeed*(-1);}
        this.movingSpeedY = r.nextDouble()*maxSpeed;
    }

    /**
     * {@summary Redefinition of toString. }
     */
    public String toString(){
        return id+"pos: "+currentPosition.toString()+" speed: "+movingSpeedX+" "+movingSpeedY+"\t"+individual+"\t"+getPopulation();
    }

    /**
     * {@summary Redefinition of equals. }
     * @return true if o is CoquilleBille.
     */
    @Override
    public boolean equals(Object o){
        if(o!= null && o instanceof CoquilleBille){
            /**Compare Against Shell Id**/
            return getId()==((CoquilleBille)(o)).getId();
        }
        return false;
    }
}
