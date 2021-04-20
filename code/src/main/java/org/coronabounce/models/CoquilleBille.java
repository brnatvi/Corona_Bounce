package org.coronabounce.models;

import org.coronabounce.controllers.Controller;
import java.util.Random;

public class CoquilleBille {

    /**
    * A moving speed in x to move faster or slower.
    */
    private double movingSpeedX;//le vecteur vitesse en Vx de la Coquille
    /**
    * A moving speed in y to move faster or slower.
    */

    private double movingSpeedY;//le vecteur vitesse Vy de la Coquille
    private Individual individual;//l individu que la Coquille va contenir
    private Position p;// la position e X et et en Y de la Coquille
    private final int id;// identifiant de la Coquille (afin de  redefinir equals )
    private static int idCpt=0;// le nombre de Coquille déja crées
    private static Random r = new Random();
    private final Position startingPosition;// memoriser la position de départ de la Coquille


    public CoquilleBille(double speedX,double speedY, Individual individual){

        this.p=new Position();
        this.startingPosition=new Position(0,0);
        this.startingPosition.setPosX(this.p.getX());
        this.startingPosition.setPosY(this.p.getY());
        id=idCpt++;//incrémonter le nombre de Coquilles qui existent
        this.movingSpeedX=speedX;
        this.movingSpeedY=speedY;
        this.individual=individual;

    }

    public CoquilleBille(Individual individual){
      this(getRandomMovingSpeed(5),getRandomMovingSpeed(5),individual);
    }

    public boolean equals(Object o){// redefinition de equals
      if(o!= null && o instanceof CoquilleBille){
        return getId()==((CoquilleBille)(o)).getId();//Comparer par rapport a l'id de la Coquille
      }
      return false;
    }
    //=====================================================Rebondissements=============================================//
    public boolean outOfX(double x){
        if(x<=getPop().getRadiusDot() || x>= Controller.getWidth()-getPop().getRadiusDot()){return true;}//verifie si la position de la Coquille dépasse les bornes de la zone par rapport a X
        return false;
    }
    /**
     *{@summary Return true if y coordinate is out the the Zone.}
     */
    public boolean outOfY(double y){//verifie si la position de la Coquille dépasse les bornes de la zone par rapport a Y
        if(y<=getPop().getRadiusDot() || y>= Controller.getHeight()-getPop().getRadiusDot()){return true;}
        return false;
    }
     protected void bounceIfHitWall() {
          for (Wall wall : getPop().getListWall()) {
               if (wall.willCrossWallInX(this) && wall.willCrossWallInY(this)) {
                    bounce(true);
               }
          }
     }
       public boolean InY(CoquilleBille coc){
         if((this.getPosition().getY()-coc.getPosition().getY()<10)&& (this.getPosition().getY()-coc.getPosition().getY()>=0)||(this.getPosition().getY()-coc.getPosition().getY()<10)&& (coc.getPosition().getY()-coc.getPosition().getY()>=0) ){
              return true;
         }else{
              return false;
         }
     }
     public boolean InX(CoquilleBille coc){
          if((this.getPosition().getX()-coc.getPosition().getX()<10)&& (this.getPosition().getX()-coc.getPosition().getX()>=0)||(this.getPosition().getX()-coc.getPosition().getX()<10)&& (coc.getPosition().getX()-coc.getPosition().getX()>=0) ){
               return true;
          }else{
               return false;
          }
     }
     public void Bouncee(boolean bool){
         if(bool){
              this.setMovingSpeed(this.getMovingSpeedX()*-1,this.getMovingSpeedY());
     
         }else{
              this.setMovingSpeed(this.getMovingSpeedX(),this.getMovingSpeedY()*-1);
     
         }
     }
     /**
      *{@summary bounce if this will go out of the zone.}<br>
      */
     protected void bounceIfOutOfZone(){//verifie si la position de la Coquille atteint la borne de la zone pour rebondir
          if (outOfX(p.getX()+movingSpeedX)) {
               bounce(true);//rebondir selon X
          }
          if (outOfY(p.getY()+movingSpeedY)) {
               bounce(false);//Rebondir selon Y
          }
     }
     /**
      *{@summary bounce.}<br>
      *@param inX True if bounce in x coor.
      */
     protected void bounce(boolean inX){// elle sert a gerer les rebondissemnt
          if(inX){ movingSpeedX*=-1;//inverser le vecteur vitesse Vx en le multipliant pas -1
          }
          else{ movingSpeedY*=-1; }//inverser le vecteur vitesse Vy en le multipliant pas -1
     }
     /**
      *{@summary bounce.}<br>
      */
     protected void bounce(){
          bounce(true);
          bounce(false);
     }
     //******************************************************************************************************************************//
    protected double distancePos() {//cette methode calcule la distance entre la position courante de la Coquille et la position de départ
        double x1 = this.getPosition().getX();
        double x2 = this.getStartingPosition().getX();
        double y1 = this.getPosition().getY();
        double y2 = this.getStartingPosition().getY();
        return  Math.sqrt((x1 -x2) * (x1 -x2) + (y1 -y2) * (y1 -y2));
    }
     /**
      *{@summary Return a random moving speed between -maxSpeed & maxSpeed.}<br>
      *@param maxSpeed The max speed that can be return.
      */
     protected static double getRandomMovingSpeed(int maxSpeed){// elle permet de generer une vitesse qui ne depasse pas maxspeed
          if(maxSpeed<1){maxSpeed=1;}
          if(r.nextBoolean()){maxSpeed=maxSpeed*(-1);}
          return r.nextDouble()*maxSpeed;
     }

    /**
    *{@summary The moving funtion.}<br>
    *Speed will be modify if this hurt a limit of the zone.
    */

    public void move(){
        bounceIfOutOfZone();
        if (getPop().getNbZones() !=1) bounceIfHitWall();
        this.p.setPos(this.p.getX()+this.movingSpeedX,this.p.getY()+this.movingSpeedY);
    }


    /**
    *{@summary bounce if this hit a wall.}<br>
    */
  
    //==================================================getters=====================================================================//
    public double getMovingSpeed() {return Math.sqrt( (this.movingSpeedX*this.movingSpeedX)+(this.movingSpeedY*this.movingSpeedY));}
    public Individual getIndividual(){return individual;}
    public void setIndividual(Individual individual){ this.individual=individual;}
    public Position getPosition(){return this.p;}
    public int getId(){return id;}
    public void setMovingSpeed(double Vx, double Vy) { this.movingSpeedX=Vx; this.movingSpeedY=Vy; }
    public double getMovingSpeedX() { return this.movingSpeedX;}
    public double getMovingSpeedY() {  return this.movingSpeedY;}
    public Population getPop() {
          return individual.getPop();
     }
     public Position getStartingPosition() {
          return startingPosition;
     }
     
    
}
