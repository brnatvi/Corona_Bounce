package org.coronabounce.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.coronabounce.controllers.Controller;

public class IndividualTest extends Assertions{
  @Test
  public void testHealthState(){
    Controller c = new Controller();
    Population p = new Population(c, 0, 0, 0);
    Individual i = new Sick(new CoquilleBille(null),p);
    assertEquals("Sick",i.healthState());
    i = new Recovered(new CoquilleBille(null),p);
    assertEquals("Recovered",i.healthState());
    i = new Healthy(new CoquilleBille(null),p);
    assertEquals("Healthy",i.healthState());
  }
  @Test
  public void testIsSick(){
    Controller c = new Controller();
    Population p = new Population(c, 0, 0, 0);
    Individual i = new Sick(new CoquilleBille(null),p);
    assertTrue(i.isSick());
    i = new Recovered(new CoquilleBille(null),p);
    assertTrue(!i.isSick());
    i = new Healthy(new CoquilleBille(null),p);
    assertTrue(!i.isSick());
  }
  @Test
  public void testContact(){
    Controller c = new Controller();
    Population p = new Population(c, 0, 0, 0);
    Individual i1 = new Sick(new CoquilleBille(null),p);
    Individual i2 = new Healthy(new CoquilleBille(null),p);
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
    i1.setCoc(coc1);
    coc2.getPosition().setPos(1,1);
    i2.setCoc(coc2);
    assertTrue(coc1.getIndividual().isSick());
    assertTrue(!coc2.getIndividual().isSick());
    c.setContaminationRadius(0.2);
    p.Contacts();
    assertTrue(coc1.getIndividual().isSick());
    assertTrue(!coc2.getIndividual().isSick());
    c.setContaminationRadius(10);
    p.Contacts();
    assertTrue(coc1.getIndividual().isSick());
    assertTrue(coc2.getIndividual().isSick());
  }
}
