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
    private List<Wall> listWall = new ArrayList<Wall>();//la liste des murs
    private Timer timer;
    private TimerTask timerTask = null;
    private static Random random = new Random();
   
    //============================= Constructors ======================================================================/

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
        if(isRestrictionMovement) {this.RestrictMovement();}// dans le cas ou on est dans le scénario Restrict Movement il suffit d appeler la methode RestrictMovement
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

    //===========================  Getters/Setters ====================================================================/

    /**
     *Share controller
     */
    public Controllable getController() { return controller; }

    public List<CoquilleBille> getAllPoints() { return listCoquille; }

    public double getRadiusDot() { return controller.getRadiusDot(); }
    
    public Timer getTimer() { return timer; }

    public List<Wall> getListWall() { return listWall; }

    //========================= Virus Getters/Setters =================================================================/

    public long getDurationIncubation() { return controller.getDurationIncubation(); }

    public long getDurationImmunity() { return controller.getDurationImmunity(); }

    public long getDurationHealing() { return controller.getDurationHealing(); }

    public double getContaminationRadius() { return controller.getContaminationRadius(); }

    //========================= Points Interactions ===================================================================/

    public void addIndividual(Individual i) { //la méthode permet de creer Une CoquilleBille et la remplir avec l individu i et l ajouter a la liste des CoquilleBille
        CoquilleBille coc = new CoquilleBille(i);
        listCoquille.add(coc);//ajouter la Coquille Bille a la liste des Coquille Bille
    }

    public void Contacts(){
        for(CoquilleBille coc:listCoquille){
            coc.getIndividual().agitSur();
            Rebound(coc);
        }
        
    }
    
    private double dist(Wall w,CoquilleBille coc){//Calcule la distance entre une CoquilleBille et le mur
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
            if(coc != c && this.distance(coc,c) <= (2* controller.getRadiusDot())){
                if(coc.InX(c)){
                coc.Bouncee(true);
                c.Bouncee(true);}
                if(coc.InY(c)){
                    coc.Bouncee(false);
                    c.Bouncee(false);}
                }
            
            if( ! listWall.isEmpty()){
                for(Wall wall:listWall){
                    if(dist(wall,coc) <= (2 * controller.getRadiusDot())){
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

    /**
     * @summary Print for debug, internal using function
     */
    private void printPop() {
       
        int i = 0;
        for (CoquilleBille coc : listCoquille) {
            System.out.printf("Individu num : %d de position suivante  %.3f et  %.3f et de etat de sante  %s  Vitesse : %.3f \n", i, coc.getPosition().getX(), coc.getPosition().getY(), coc.getIndividual().healthState(), coc.getMovingSpeed());
            i++;
        }
    }

    /**********************************************Moving of Billes*************************************************/
   
    public void makeBilleMove() {
        for (CoquilleBille coc : listCoquille) {
          coc.move();
          Rebound(coc);
        }
    }

    //================================ Functions for Walls ============================================================/

    /**
     *{@summary Create the walls.}<br>
     *All the wall will be create at equals distance from eatch other.<br>
     *@param numberOfWall the number of wall that will be add.
     */
    private boolean createWalls(int numberOfWall){
        double maxX = Controller.getWidth();
        for (int i=1; i<=numberOfWall; i++) {
            double posX = (maxX*i)/(numberOfWall+1);
            listWall.add(new Wall(this.controller, posX));
        }
        if(numberOfWall>0){
            return true;
        }
        else{
            return false;
        }
    }

    /**
    *{@summary Return how much zone there is.}
    */
    public int getNbZones(){ return this.getNbWall()+1; }

    /**
    *{@summary Return how much wall there is.}
    */
    public int getNbWall(){ return listWall.size(); }

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
     * Get number of infected people (sick + incubating).
     */
    public int getNbInfected() { return (nbSick + nbIncubating); }

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
        getTimer().schedule(new TimerTask()
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

    //============================= Timer Management ===================================================================/

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
}
