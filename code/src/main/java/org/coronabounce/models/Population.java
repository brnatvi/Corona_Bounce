package org.coronabounce.models;

import org.coronabounce.controllers.Controller;
import org.coronabounce.data.Data;
import org.coronabounce.mvcconnectors.Controllable;
import org.coronabounce.mvcconnectors.Displayable;

import java.util.*;

public class Population implements Displayable {

    private Controllable controller;
    private List<CoquilleBille> listCoquille = new ArrayList<CoquilleBille>();// Contient la liste des Coquille Billes de la population
    private Data data;
    public int nbSick;// le nombre de personnes malades
    public int nbHealthy;//le nombre de personnes saines
    public int nbRecovered;//le nombre de personnes guéries
    public int nbIncubating;//le nombre de personnes qui portent le virus
    private List<Wall> listWall = new ArrayList<Wall>();//la lsite des murs
    private Timer timer;
    private TimerTask timerTask = null;
    private static Random random = new Random();
   
    //========================= Constructors ==========================================================================/

    public Population(Controllable controller, int nbH, int nbS, int nbR,boolean isLockDown, boolean isWall, boolean isRestrictionMovement) {
        this.controller = controller;
        data = new Data();
        timer = new Timer();

        if(isLockDown){// dans le cas ou on est dans le scénario lockdown il suffit de creer des ConfinedBille
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
                CoquilleBille coc = new CoquilleBille(null);// creer d'abord une Coquille Bille vide
                Individual in = new Healthy(coc, this);// creer un individu Healthy
                coc.setIndividual(in);//Remplir la coquille vide avec un individu de type Healthy
                listCoquille.add(coc);// ajouter la coquille qui contient l individu a la liste des CoquilleBille
            }
            for (int i = 0; i < nbS; i++) {
                CoquilleBille coc = new CoquilleBille(null);// creer d'abord une Coquille Bille vide
                Individual in = new Sick(coc, this);// creer un individu Sick
                coc.setIndividual(in);//Remplir la coquille vide avec un individu de type Sick
                listCoquille.add(coc);// ajouter la coquille qui contient l individu a la liste des CoquilleBille
            }
            for (int i = 0; i < nbR; i++) {
                CoquilleBille coc = new CoquilleBille(null);// creer d'abord une Coquille Bille vide
                Individual in = new Recovered(coc, this);// creer un individu Recovered
                coc.setIndividual(in);//Remplir la coquille vide avec un individu de type Recovered
                listCoquille.add(coc);// ajouter la coquille qui contient l individu a la liste des CoquilleBille
            }

        }
        if(isRestrictionMovement) {this.RestrictMovement();}// dans le cas ou on est dans le scénarios Restrict Movement il suffait d appeler la methode RestrictMovement
        if(isWall){// dans le cas ou on est dans le scénarios Walls ,on crée des murs
          createWalls(controller.getWallsCount());// obtenir d abord le nombre de mur a aprtir de Controller et les ajouter a la aliste des murs
          for (Wall wall : listWall ) {
            wall.makeWallGoDown(this);//faire descendre les murs petit a petit
          }
        }

    }
    public Population(Controllable controller, int nbIndividus,boolean isLockDown, boolean isWall, boolean isRestrictionMovement) {
        this(controller, nbIndividus - 1, 1, 0,isLockDown,isWall,isRestrictionMovement);
    }
    public Population(){}
  
    /*******************************************************************************************************************************************/
   

   
  

    public void addIndividual(Individual i) { //la méthode permet de creer Une CoquilleBille et la remplir avec l individu i et l ajouter a la liste des CoquilleBille
        CoquilleBille coc = new CoquilleBille(i);
        listCoquille.add(coc);
    }


    //========================= Virus Getters/Setters==================================================================/
    public List<CoquilleBille> getAllPoints() {
        return listCoquille;
    }

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
    public Timer getT() {return timer;}
    public List<Wall> getListWall() { return listWall; }

    //========================= Points Interactions ===================================================================/
    public void Contacts(){
        for(CoquilleBille coc:listCoquille){
            coc.getIndividual().agitSur();
        }
    }
    //========================================================intermediate methods=======================================//
    
    public double dist(Wall w,CoquilleBille coc){//Calcule la distance entre une CoquilleBille et le mur
        double x1 = coc.getPosition().getX();//Position X de la coquilleBille
        double x2 = w.getPositionX();//Position X du mur
        double y1 = coc.getPosition().getY();//Position Y de la coquilleBille
        double y2 = w.getPositionY();//Position Y du mur
        return  Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));

    }

   
    public double distance(CoquilleBille i1, CoquilleBille i2) {//Calcule la distance entre les postions de deux Coquilles billes
        double x1 = i1.getPosition().getX();//Position X de la coquilleBille1
        double x2 = i2.getPosition().getX();//Position X de la coquilleBille2
        double y1 = i1.getPosition().getY();//Position Y de la coquilleBille1
        double y2 = i2.getPosition().getY();//Position Y de la coquilleBille2
        return  Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }
    public void Rebound (CoquilleBille c){
        for(CoquilleBille coc:listCoquille){
            if(coc != c && this.distance(coc,c)<5){
                coc.bounce();
                c.bounce();
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
    //*****************************************************Strict lockdown*************************************************/
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

    public void printPop() {// methode qui affiche la position et l etat de chaque Coquille Bille sur la console
       
        int i = 0;
        for (CoquilleBille coc : listCoquille) {
            System.out.printf("Individu num : %d de position suivante  %.3f et  %.3f et de etat de sante  %s  Vitesse : %.3f \n", i, coc.getPosition().getX(), coc.getPosition().getY(), coc.getIndividual().healthState(), coc.getMovingSpeed());
            i++;
        }
    }
    /**********************************************Moving of Billes*************************************************/
   
    public void Moving_Bille() {
      
        for (CoquilleBille coc : listCoquille) {
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
