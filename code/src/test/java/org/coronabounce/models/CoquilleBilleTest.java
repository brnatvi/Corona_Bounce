package org.coronabounce.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.coronabounce.controllers.Controller;

public class CoquilleBilleTest extends Assertions{
  @Test
  public void testEquals(){
    Controller c = new Controller();
    Population p = new Population(c, 0, 0, 0);
    Individual i1 = new Sick();
    Individual i2 = new Healthy();
    p.addIndividual(i1);
    p.addIndividual(i2);
    CoquilleBille coc1=null, coc2=null, coc3=null;
    assertEquals(2,p.getAllPoints().size());
    for (CoquilleBille coc : p.getAllPoints() ) {
      if(coc.getIndividual().isSick()){
        coc1 = coc;
      }else{
        coc2 = coc;
      }
    }
    coc3 = coc2;
    assertTrue(coc1.equals(coc1));
    assertTrue(!coc1.equals(coc2));
    assertTrue(!coc1.equals(coc3));
    assertTrue(coc2.equals(coc3));

    assertTrue(!coc1.equals(null));
    assertTrue(!coc1.equals(new Integer(-1)));
  }
}
