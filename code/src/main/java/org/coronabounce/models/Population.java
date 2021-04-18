package org.coronabounce.models;

import org.coronabounce.controllers.Controller;
import org.coronabounce.data.Data;
import org.coronabounce.mvcconnectors.Controllable;
import org.coronabounce.mvcconnectors.Displayable;

import java.util.*;

public class Population implements Displayable {

    private Controllable controller;
    private List<CoquilleBille> listCoquille = new ArrayList<CoquilleBille>();
    private Data data;
    public int nbSick;
    public int nbHealthy;
    public int nbRecovered;
    public int nbIncubating;
    private List<Wall> listWall = new ArrayList<Wall>();
    private Timer timer;
    private TimerTask timerTask = null;
    private static Random random = new Random();
    // Wall mur =new Wall(); //si vous voulez 1 seul mur ajouter en 1 seul lors de l'appel a createWalls();
    private int nbZones;

    //========================= Constructors ==========================================================================/

    public Population(Controllable controller, int nbH, int nbS, int nbR,boolean isLockDown, boolean isWall, boolean isRestrictionMovement,int nbZones) {
        //this.nbZones=nbZones;
        this.controller = controller;
        data = new Data();
        timer = new Timer();

        if(isLockDown){
            for (int i = 0; i < nbH; i++) {
                CoquilleBille coc = new ConfinedBille(null);
                Individual in = new Healthy(coc, this);
                coc.setIndividual(in);
                listCoquille.add(coc);
            }
            for (int i = 0; i < nbS; i++) {
                CoquilleBille coc = new ConfinedBille(null);

                Individual in = new Sick(coc, this);
                coc.setIndividual(in);
                listCoquille.add(coc);
            }
            for (int i = 0; i < nbR; i++) {
                CoquilleBille coc = new ConfinedBille(null);
                Individual in = new Recovered(coc, this);
                coc.setIndividual(in);
                listCoquille.add(coc);
            }
        }
        else {
            for (int i = 0; i < nbH; i++) {
                CoquilleBille coc = new CoquilleBille(null);
                Individual in = new Healthy(coc, this);
                coc.setIndividual(in);
                listCoquille.add(coc);
            }
            for (int i = 0; i < nbS; i++) {
                CoquilleBille coc = new CoquilleBille(null);
                Individual in = new Sick(coc, this);
                coc.setIndividual(in);
                listCoquille.add(coc);
            }
            for (int i = 0; i < nbR; i++) {
                CoquilleBille coc = new CoquilleBille(null);
                Individual in = new Recovered(coc, this);
                coc.setIndividual(in);
                listCoquille.add(coc);
            }

        }
        if(isRestrictionMovement) {this.RestrictMovement();}
        if(isWall){
          createWalls(controller.getWallsCount());
          for (Wall wall : listWall ) {
            wall.makeWallGoDown(this);
          }
        }

    }
    /**
    *{@summary Create the walls.}<br>
    *All the wall will be create at equals distance from eatch other.<br>
    *@param numberOfWall the number of wall that will be add.
    */
    private void createWalls(int numberOfWall){
      double maxX = Controller.getWidth();
      for (int i=1; i<=numberOfWall; i++) {
        double posX = (maxX*i)/(numberOfWall+1);
        listWall.add(new Wall(this.controller, posX));
      }
    }


    public Population(Controllable controller, int nbIndividus,boolean isLockDown, boolean isWall, boolean isRestrictionMovement,int nbZones) {
        this(controller, nbIndividus - 1, 1, 0,isLockDown,isWall,isRestrictionMovement,nbZones);
    }
    public Population(){}
    public List<CoquilleBille> getAllPoints() {
        return listCoquille;
    }
    public Timer getT() {return timer;}
    public List<Wall> getListWall() { return listWall; }

    public void addIndividual(Individual i) {
        CoquilleBille coc = new CoquilleBille(i);
        listCoquille.add(coc);
    }


    //========================= Virus Getters/Setters==================================================================/

    public long getDurationCovid() {
        return controller.getDurationCovid();
    }

    public long getDurationNonContamination() {
        return controller.getDurationNonContamination();
    }

    public long getDurationHealing() {
        return controller.getDurationHealing();
    }

    public double getContaminationRadius() {
      return controller.getContaminationRadius();
    }
    public double getRadiusDot() {
      return controller.getRadiusDot();
    }

    public List<CoquilleBille> getListCoquille() {
        return this.listCoquille;
    }


    //========================= Points Interactions ===================================================================/

    public void Contacts(){
        for(CoquilleBille coc:listCoquille){
            coc.getIndividual().agitSur();
        }
    }
    public double dist(Wall w,CoquilleBille coc){
        double x1 = coc.getPosition().getX();
        double x2 = w.getPositionX();
        double y1 = coc.getPosition().getY();
        double y2 = w.getPositionY();
        return  Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));

    }

    public void Rebound (CoquilleBille c){
        for(CoquilleBille coc:listCoquille){
            if(coc != c && this.distance(coc,c)<5 ){
               //coc.bounce();
              // c.bounce();
               coc.bounce1(true,coc);
               coc.bounce1(false,c);
            }
            if(listWall.size()>0 ){
                for(Wall wall:listWall){
                    if(dist(wall,coc)<5){
                        coc.bounce();
                    }
                }

            }
        }
    }


    public void separate(int nbZones)
    {
        for(CoquilleBille coc : listCoquille) {
            // mur.HitWallInX(coc,nbZones);
            // mur.separatePop1(coc,nbZones);
        }
    }

    public double distance(CoquilleBille i1, CoquilleBille i2) {
        double x1 = i1.getPosition().getX();
        double x2 = i2.getPosition().getX();
        double y1 = i1.getPosition().getY();
        double y2 = i2.getPosition().getY();
        return  Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    /** @summary
     *
     * RestrictMovement() prevent a big part of the population from moving
     olny(doctors, cops ..) go to work and people go out rarely and just out of necessity **/

    public void  RestrictMovement()
    {

        int prctg=random.nextInt(10)+70;
        int cpt = (prctg * this.getNbIndividus()) / 100;

        while (cpt > 0) {
            int index = random.nextInt(this.getNbIndividus());
            //make sure to get a new coquille(check if the coquille has already been chosen or not)
            while (this.listCoquille.get(index).getMovingSpeed() == 0) index = random.nextInt(this.getNbIndividus());
            this.listCoquille.get(index).setMovingSpeed(0, 0);
            cpt--;
        }
    }


    //========================= Prints ================================================================================/

    public void printPop() {
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");

        int i = 0;
        for (CoquilleBille coc : listCoquille) {
            System.out.printf("Individu num : %d de position suivante  %.3f et  %.3f et de etat de sante  %s  Vitesse : %.3f \n", i, coc.getPosition().getX(), coc.getPosition().getY(), coc.getIndividual().healthState(), coc.getMovingSpeed());
           i++;
        }
       // System.out.println("le nombre des personnes contaminées:" + getNbSick() + " de personnes guéries :" + getNbRecovered() + " non contaminées :" + getNbHealthy());
        //System.out.println("Pourcentage de contamination: " + this.percentageSick() + " %  Pourcentage de guérison :" + this.percentageRecovered() + "% Pourcentage de non contamination est :" + this.percentageHealthy()+" %");
    }

    public void Moving_Bille() {
        //System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        for (CoquilleBille coc : listCoquille) {
          // for (Wall wall : getListWall() ) {
          //   wall.makeWall();
          // }
          coc.move();
          Rebound(coc);
        }
    }

    //================================ Walls ==========================================================================/
    /**
    *{@summary Return how much zone there is.}
    */
    public int getNbZones(){ return this.getNbWall()+1;}
    /**
    *{@summary Return how much wall there is.}
    */
    public int getNbWall(){ return listWall.size();}
    /**
    *{@summary Return true if there is 1 or more wall.}
    */
    public boolean thereisWall(){ return getNbWall()!=0;}

    /**
     * Return list of positions on axis of abscissas of all walls
     */
    public ArrayList<Double> getPositionsOfWalls()
    {
        ArrayList<Double> rez = new ArrayList();
        for (int i = 0; i < listWall.size(); i++)
        {
            rez.add(listWall.get(i).getPositionX());
        }
        return rez;
    }

    /**
     * Return list of heights of all walls
     */
    public ArrayList<Double> getHeigthsOfWalls()
    {
        ArrayList<Double> rez = new ArrayList();
        for (int i = 0; i < listWall.size(); i++)
        {
            rez.add(listWall.get(i).getPositionY());
        }
        return rez;
    }

    /**
     * Return list of thicknesses of all walls
     */
    public ArrayList<Double> getThicknessesOfWalls()
    {
        ArrayList<Double> rez = new ArrayList();
        for (int i = 0; i < listWall.size(); i++)
        {
            rez.add(listWall.get(i).getThickness());
        }
        return rez;
    }


    //========================= Population Statistics =================================================================/

    /**
     * Get total number of points / number of Individuals.
     */
    public int getNbIndividus() { return getAllPoints().size(); }

    /**
     * Get number of healthy
     */
    public int getNbHealthy() { return nbHealthy; }

    /**
     * Get number of sick people.
     */
    public int getNbSick() { return nbSick; }

    /**
     * Get number of recovered
     */
    public int getNbRecovered() { return nbRecovered; }

    /**
     * @summary Transfers NbSick and NbRecovered to Data to save them to draw AreaChart.
     * To show correctly superposed layers in AreaChart we take:
     *      - NbHealthy taken as 100% (bottom layer)
     *      - nbSick = nbSick + NbIncubating + nbRecovered (middle layer)
     *      - NbRecovered = NbRecovered (top layer)
     * Superposed they present ratio of these tree values (nbHealthy, nbSick/Incubating and nbRecovered) in 100%
     */
    @Override
    public void saveStatToData()
    {
        getT().schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                if (controller.getState() == Controllable.eState.Working){
                    data.setData(100 * (nbSick + nbIncubating + nbRecovered) / controller.getPersonsCount(), 100 * nbRecovered / controller.getPersonsCount());
                    //System.out.println("Statistic Thread run " + Thread.currentThread().getId());
                }
            }
        }, 0, 100);
    }

    /**
     * Get saved statistics (history)
     * @return Data - history
     */
    @Override
    public Data getData() { return this.data; }

    //============================= Time Management ===================================================================/

    /**
     *Close timer to stop using this population.
     */
    public void stopTimer(boolean b_StopTimer)
    {
        if (null != this.timerTask)
        {
            if (!this.timerTask.cancel())
            {
                System.out.println("Can't cancel task!\n");
            }
            this.timerTask = null;
            this.timer.purge();
        }
        if (b_StopTimer)
        {
            this.timer.cancel();
            this.timer = null;
        }
    }

    /**
     *Share controller
     */
    public Controllable getController()
    {
        return controller;
    }

}
