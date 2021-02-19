package org.coronabounce.views;
import org.coronabounce.models.CoquilleBille;
import org.coronabounce.mvcconnectors.Controllable;
import org.coronabounce.mvcconnectors.Displayable;

import java.util.ArrayList;
import java.util.List;

public class PopulationSpace
{
    private Displayable model;
    private Controllable controller;

    public PopulationSpace (Displayable m, Controllable c){
        this.model = m;
        this.controller = c;
    }


    //================= Getters from Model ============================================================================/

    public List<CoquilleBille> allIndividuals() { return model.getAllPoints(); }
    public void  getStateIndivid() { this.allIndividuals(); }


    //================= Own functions =================================================================================/


}
