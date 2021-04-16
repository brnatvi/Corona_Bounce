package org.coronabounce.models;

import org.coronabounce.controllers.Controller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ZoneTest extends Assertions{
  @Test
  public void testSetWidth(){
    Zone zone = new Zone(new Controller(),false,false,false,1);
    double w=Controller.getWidth();
    Controller.setWidth(-100);
    assertEquals(w,Controller.getWidth());
    Controller.setWidth(0.3);
    assertEquals(w,Controller.getWidth());
    Controller.setWidth(0.99999);
    assertEquals(w,Controller.getWidth());
    Controller.setWidth(2);
    assertEquals(2.0,Controller.getWidth());
    Controller.setWidth(100);
    assertEquals(100.0,Controller.getWidth());
  }
  @Test
  public void testOutOfX(){
    Zone zone = new Zone(new Controller(),false,false,false,1);
    Controller.setWidth(1);
    Controller c = new Controller();
    c.setRadiusDot(0);
    Population p = new Population(c, 0, 0, 0,false,false,false,1);
    Individual in = new Sick(new CoquilleBille(null),p);
    CoquilleBille coc = new CoquilleBille(in);
    assertTrue(coc.outOfX(-1));
    assertTrue(coc.outOfX(0.0));
    assertTrue(!coc.outOfX(0.0000001));
    assertTrue(!coc.outOfX(0.9999999));
    assertTrue(coc.outOfX(1));
    assertTrue(coc.outOfX(10));
    assertTrue(coc.outOfX(100000000));

   Controller.setWidth(100);
    assertTrue(coc.outOfX(0.0));
    assertTrue(!coc.outOfX(1));
    assertTrue(!coc.outOfX(99.999));
    assertTrue(coc.outOfX(100));
  }
}
