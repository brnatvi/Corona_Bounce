package org.coronabounce.models;

import org.coronabounce.controllers.Controller;
import org.coronabounce.data.Data;
import org.coronabounce.mvcconnectors.Controllable;
import org.coronabounce.mvcconnectors.Displayable;

import java.util.*;

import static org.coronabounce.models.CoquilleBille.getRandomMovingSpeed;

public class Population implements Displayable {

    private Controllable controller;
    private List<CoquilleBille> listCoquille = new ArrayList<CoquilleBille>();
    private Data data;
    public int nbSick;
    public int nbHealthy;
    public int nbRecovered;
    private List<Wall> listWall = new ArrayList<Wall>();
    private Timer timer;
    private TimerTask timerTask = null;
    private static Random random = new Random();
    Wall mur =new Wall();
    private int nbZones;

    //========================= Constructors ==========================================================================/

    public Population(Controllable controller, int nbH, int nbS, int nbR,boolean Confinement,boolean RestrictionMouvement,int nbZones) {
        //this.nbZones=nbZones;
        this.controller = controller;
        data = new Data();
        timer = new Timer();


        if(Confinement){
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
          }else {

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


                if(RestrictionMouvement) this.RestrictMouvement();


        }


            createWalls(3);
            for (Wall wall : listWall ) {
              wall.makeWallGoDown(this);
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
            listWall.add(new Wall());
          }
        }


    public Population(Controllable controller, int nbIndividus,boolean Confinement,boolean RestrictionMouvement,int nbZones) {
        this(controller, nbIndividus - 1, 1, 0,Confinement,RestrictionMouvement,nbZones);
    }
    public Population(){}
    public List<CoquilleBille> getAllPoints() {
        return listCoquille;
    }
    public Timer getT() {return timer;}
    public List<Wall> getListWall(){return listWall;}



    public void pauseThread() throws InterruptedException
    {
        Thread.currentThread().sleep(2000);
    }

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

    public List<CoquilleBille> getListCoquille() {
        return this.listCoquille;
    }


    //========================= Points Interactions ===================================================================/



    public void Contacts(){
        for(CoquilleBille coc:listCoquille){
            coc.getIndividual().agitSur();
        }
    }
public void separate(int nbZones)
{
    for(CoquilleBille coc : listCoquille) {
        mur.HitWallInX(coc,nbZones);
        mur.separatePop1(coc,nbZones);
    }
}






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

    public double distance(CoquilleBille i1, CoquilleBille i2) {
        double x1 = i1.getPosition().getX();
        double x2 = i2.getPosition().getX();
        double y1 = i1.getPosition().getY();
        double y2 = i2.getPosition().getY();
        return  Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));

    }

    /** @summary
     *
     * RestrictMouvement() prevent a big part of the population from moving
     olny(doctors, cops ..) go to work and people go out rarely and just out of necessity **/

    public void  RestrictMouvement()
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
        }


    }

    //========================= Population Statistics =================================================================/



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
   public int getNbIndividus() { return getAllPoints().size(); }

   public int getNbHealthy() { return nbHealthy; }

   public int getNbSick() { return nbSick; }

   public int getNbRecovered() { return nbRecovered; }

    @Override
    public void saveStatToData()
    {
        getT().schedule(this.timerTask = new TimerTask()
        {
            @Override
            public synchronized void run(){
                data.setData(100 * (nbSick + nbRecovered)/controller.getPersonsCount(), 100 * nbRecovered/controller.getPersonsCount());
            }
        }, 0, 100);
    }

    @Override
    public Data getData()
    {
        return this.data;
    }

}
