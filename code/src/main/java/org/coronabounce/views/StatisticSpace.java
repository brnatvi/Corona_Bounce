package org.coronabounce.views;
import org.coronabounce.mvcconnectors.Controllable;
import org.coronabounce.mvcconnectors.Displayable;

public class StatisticSpace
{
    private Displayable model;
    private Controllable controller;
    private Timer timer;

    public StatisticSpace (Displayable m, Controllable c){
        this.model = m;
        this.controller = c;
        this.timer = new Timer();
    }

    //================= Getters from Model ============================================================================/

    public int nmbPopulation() { return model.getNbIndividus(); }
    public int nmbHelthy() { return model.getNbHealthy(); }
    public int nmbSick() { return model.getNbSick(); }
    public int nmbRecovered() { return model.getNbRecovered(); }
    public int percHelthy() { return model.getNbHealthy()/model.getNbIndividus()*100; }
    public int percSick() { return model.getNbSick()/model.getNbIndividus()*100; }
    public int percRecovered() { return model.getNbRecovered()/model.getNbIndividus()*100; }

    //================= Setters to Controller =========================================================================/


    //================= Own functions =================================================================================/
}
