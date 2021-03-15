package org.coronabounce.models;
import org.coronabounce.controllers.Controller;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class Incubating extends Individual {
    @Override
    public boolean isSick(){return true;}
}
