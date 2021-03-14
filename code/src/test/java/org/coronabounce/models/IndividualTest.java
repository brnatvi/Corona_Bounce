package org.coronabounce.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.coronabounce.controllers.Controller;

public class IndividualTest extends Assertions{
  @Test
  public void testHealthState(){
    Individual i = new Sick();
    assertEquals("Sick",i.healthState());
    i = new Recovered();
    assertEquals("Recovered",i.healthState());
    i = new Healthy();
    assertEquals("Healthy",i.healthState());
  }
  @Test
  public void testIsSick(){
    Individual i = new Sick();
    assertTrue(i.isSick());
    i = new Recovered();
    assertTrue(!i.isSick());
    i = new Healthy();
    assertTrue(!i.isSick());
  }
  @Test
  public void testContact(){
    Controller c = new Controller();
    Population p = new Population(c, 0, 0, 0);
    Individual i1 = new Sick();
    Individual i2 = new Healthy();
    p.addIndividual(i1);
    p.addIndividual(i2);
    CoquilleBille coc1=null, coc2=null;
    assertEquals(2,p.getAllPoints().size());
    for (CoquilleBille coc : p.getAllPoints() ) {
      if(coc.getIndividual().isSick()){
        coc1 = coc;
      }else{
        coc2 = coc;
      }
    }
    assertTrue(coc1!=null);
    assertTrue(coc2!=null);
    coc1.getPosition().setPos(0,0);
    coc2.getPosition().setPos(1,1);
    assertTrue(coc1.getIndividual().isSick());
    assertTrue(!coc2.getIndividual().isSick());
    c.setContaminationRadius(0.2);
    p.interaction();
    assertTrue(coc1.getIndividual().isSick());
    assertTrue(!coc2.getIndividual().isSick());
    c.setContaminationRadius(10);
    p.interaction();
    assertTrue(coc1.getIndividual().isSick());
    assertTrue(coc2.getIndividual().isSick());
  }
}
