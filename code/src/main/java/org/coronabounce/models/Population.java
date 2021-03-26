package org.coronabounce.models;

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
    Wall mur= new Wall();
    private Timer timer;

    //========================= Constructors ==========================================================================/

    public Population(Controllable controller, int nbH, int nbS, int nbR) {
        this.controller = controller;
        data = new Data();
        timer = new Timer();
        for (int i = 0; i < nbH; i++) {
            CoquilleBille coc = new CoquilleBille(null);
            Individual in = new Healthy(coc,this);
            coc.setIndividual(in);
            listCoquille.add(coc);

        }
        for (int i = 0; i < nbS; i++) {
            CoquilleBille coc = new CoquilleBille(null);
            Individual in = new Sick(coc,this);
            coc.setIndividual(in);
            listCoquille.add(coc);

        }
        for (int i = 0; i < nbR; i++) {
            CoquilleBille coc = new CoquilleBille(null);
            Individual in = new Recovered(coc,this);
            coc.setIndividual(in);
            listCoquille.add(coc);

        }
    }

    public Population(Controllable controller, int nbIndividus) {
        this(controller, nbIndividus - 5, 5, 0);
    }


    public List<CoquilleBille> getAllPoints() {
        return listCoquille;
    }
    public Timer getT() {return timer;}


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

    public void separate()
    {
       for(CoquilleBille coc : listCoquille) {
          mur.separatePop(coc);
       }

    }

    public void  lockDown()
    {
        /** See what happens if only a part of the population can move
         For instance , 1/4 can move and 3/4 can't **/
        Random r = new Random();
        int prctg=r.nextInt(100);
        int cpt = (prctg * this.getNbIndividus()) / 100;

        while (cpt > 0) {


            int index = r.nextInt(this.getNbIndividus());
            //make sure to get a new coquille(check if the coquille has already been chosen or not)
            while (this.listCoquille.get(index).getMovingSpeed() == 0) index = r.nextInt(this.getNbIndividus());
            this.listCoquille.get(index).setMovingSpeed(0, 0);
            cpt--;
        }
    }
    /**
    *Close timer to stop using this population.
    */
    public void stopTimer(){
        if(timer==null){return;}
        timer.purge();
        timer.cancel();
        timer=null;
    }
    public double distance(CoquilleBille i1, CoquilleBille i2) {
        double x1 = i1.getPosition().getX();
        double x2 = i2.getPosition().getX();
        double y1 = i1.getPosition().getY();
        double y2 = i2.getPosition().getY();
        return  Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));

    }

    //========================= Prints ================================================================================/

    public void printPop() {
        int i = 0;
        for (CoquilleBille coc : listCoquille) {
            System.out.printf("Individu num : %d de position suivante  %.3f et  %.3f et de etat de sante  %s  Vitesse : %.3f \n", i, coc.getPosition().getX(), coc.getPosition().getY(), coc.getIndividual().healthState(), coc.getMovingSpeed());
           i++;
        }
        int count = getNbSick() + getNbRecovered() + getNbHealthy();
        System.out.println("le nombre des personnes contaminées:" + getNbSick() + " de personnes guéries :" + getNbRecovered() + " non contaminées :" + getNbHealthy());
        //System.out.println("Pourcentage de contamination: " + this.percentageSick() + " %  Pourcentage de guérison :" + this.percentageRecovered() + "% Pourcentage de non contamination est :" + this.percentageHealthy()+" %");
    }

    public void printMovement() {
        //System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        for (CoquilleBille coc : listCoquille) {
            coc.move();
        }


    }

    //========================= Population Statistics =================================================================/

   public int getNbIndividus() { return getAllPoints().size(); }

   public int getNbHealthy() { return nbHealthy; }

   public int getNbSick() { return nbSick; }

   public int getNbRecovered() { return nbRecovered; }

    @Override
    public void saveStatToData()
    {
        getT().schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                long a = System.currentTimeMillis();
                long b =  scheduledExecutionTime();
                data.setData(100, 100 * (nbSick + nbRecovered)/controller.getPersonsCount(), 100 * nbRecovered/controller.getPersonsCount());

                System.out.println("SaveHistory thread: " + Thread.currentThread().getName());
            }
        }, 0, 100);
    }

    @Override
    public Data getData()
    {
        return this.data;
    }
    
}
