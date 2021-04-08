package org.coronabounce.models;

import org.coronabounce.controllers.Controller;
import org.coronabounce.mvcconnectors.Controllable;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Timer;

public class CoquilleBille {

    /**
    * A moving speed in x to move faster or slower.
    */
    protected double movingSpeedX;
    /**
    * A moving speed in y to move faster or slower.
    */
    protected double movingSpeedY;
    private Individual individual;
    protected Position p;
    private final int id;
    private static int idCpt=0;
    private static Random r = new Random();
    private  final Position startingPosition;
    private Population pop=new Population();


    public CoquilleBille(double speedX,double speedY, Individual individual){

        this.p=new Position();
        this.startingPosition=new Position(0,0);
        this.startingPosition.setPosX(this.p.getX());
        this.startingPosition.setPosY(this.p.getY());
        //System.out.println(   this.getStartingPosition().getX());
        id=idCpt++;
        this.movingSpeedX=speedX;
        this.movingSpeedY=speedY;
        this.individual=individual;



    }

    public Population getPop() {
        return pop;
    }

    public Position getStartingPosition() {
       return startingPosition;
    }


    public CoquilleBille(Individual individual){
      this(getRandomMovingSpeed(5),getRandomMovingSpeed(5),individual);
    }

    public boolean equals(Object o){
      if(o!= null && o instanceof CoquilleBille){
        return getId()==((CoquilleBille)(o)).getId();
      }
      return false;
    }
    public static boolean outOfX(double x){
        if(x<=0 || x>= Controller.getWidth()){return true;}
        return false;
    }
    /**
     *{@summary Return true if y coordinate is out the the Zone.}
     */
    public static boolean outOfY(double y){
        if(y<=0 || y>= Controller.getHeight()){return true;}
        return false;
    }
    protected double distancePos() {
        double x1 = this.getPosition().getX();
        double x2 = this.getStartingPosition().getX();
        double y1 = this.getPosition().getY();
        double y2 = this.getStartingPosition().getY();
        return  Math.sqrt((x1 -x2) * (x1 -x2) + (y1 -y2) * (y1 -y2));
    }

    /**
    *{@summary The moving funtion.}<br>
    *Speed will be modify if this hurt a limit of the zone.
    */
    public void move(){
        bounceIfOutOfZone();
        /* POur essayer les mur décommenter cela et pop.separtate() dans Zone */
       /*if (pop.getNbZones() !=1) bounceIfHitWall();
      /* else*/  this.p.setPos(this.p.getX()+this.movingSpeedX,this.p.getY()+this.movingSpeedY);
    }


    /**
    *{@summary bounce if this hit a wall.}<br>
    */
    protected void bounceIfHitWall()
    {
        //Nombre de zones
        int number=pop.getNbZones();
        double curentX = p.getX();
        double futurX = curentX+movingSpeedX;
        double curentY = p.getY();
        double futurY = curentY+movingSpeedY;
        //TODO parcourir la liste des murs et si futurX ou futurY est de l'autre coté d'un mur faire rebondir.
// Le mur fait rebondir la boule dès qu'elle est à une distance <=1
        /* Si la boule etait une position inférieure à l'emplacement du mur et que sa futur position est plus grande
           On l'a fait rebondir*/
        int currentZone =InwhichZoneItis(curentX,number);
        int futurZone=InwhichZoneItis(futurX,number);
        double limitSup =repartInZones(number)[currentZone];
        double limitInf=repartInZones(number)[currentZone-1];

        if (futurZone!=currentZone) {

           if(futurZone>currentZone) p.setPos(limitSup-1,futurY);
           if(futurZone<currentZone) p.setPos(limitInf+1,futurY);
        }

        else this.p.setPos(this.p.getX()+this.movingSpeedX,this.p.getY()+this.movingSpeedY);

    }



    /** Crée nombre Zones **/
    public double[] repartInZones(int nombre)
    {
        //<>
    /* A table with delimiters */
    double [] limits = new double[nombre+1];
    limits[0]=0;
    for(int i=1;i<=nombre;i++)
    {
        limits[i]=  (i*(Controller.getWidth()/nombre));
    }


    return limits;

    }
  public int InwhichZoneItis(double posX,int nombre)
  {
  double [] tab=repartInZones(nombre);
  for(int i=0;i<nombre;i++)
  {
  if(posX>=tab[i] && posX<=tab[i+1]) return i+1 ;
  }
  return -1;/* Not in any Zone ! */
  }



    /**
    *{@summary bounce if this will go out of the zone.}<br>
    */
    protected void bounceIfOutOfZone(){
      if (outOfX(p.getX()+movingSpeedX)) {
        bounce(true);
      }
      if (outOfY(p.getY()+movingSpeedY)) {
        bounce(false);
      }
    }
    /**
    *{@summary bounce.}<br>
    *@param inX True if bounce in x coor.
    */
    protected void bounce(boolean inX){
      if(inX){ movingSpeedX*=-1; }
      else{ movingSpeedY*=-1; }
    }

    public double getMovingSpeed() {return Math.sqrt( (this.movingSpeedX*this.movingSpeedX)+(this.movingSpeedY*this.movingSpeedY));}
    public Individual getIndividual(){return individual;}
    public void setIndividual(Individual individual){ this.individual=individual;}
    public Position getPosition(){return this.p;}
    public int getId(){return id;}
    public void setMovingSpeed(double Vx, double Vy) { this.movingSpeedX=Vx; this.movingSpeedY=Vy; }
    public double getMovingSpeedX() { return this.movingSpeedX;}
    public double getMovingSpeedY() {  return this.movingSpeedY;}
    /**
    *{@summary Return a random moving speed between -maxSpeed & maxSpeed.}<br>
    *@param maxSpeed The max speed that can be return.
    */
    protected static double getRandomMovingSpeed(int maxSpeed){
      if(maxSpeed<1){maxSpeed=1;}
      if(r.nextBoolean()){maxSpeed=maxSpeed*(-1);}
      return r.nextDouble()*maxSpeed;
    }
}
