package org.coronabounce.models;

import org.coronabounce.controllers.Controller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ZoneTest extends Assertions{
  @Test
  public void testSetWidth(){
    Controller c = new Controller();
    Zone zone = new Zone(c,false,false,false);
    double w=c.getWidth();
    c.setWidth(-100);
    assertEquals(1,c.getWidth());
    c.setWidth(0.3);
    assertEquals(1,c.getWidth());
    c.setWidth(0.99999);
    assertEquals(1,c.getWidth());
    c.setWidth(2);
    assertEquals(2.0,c.getWidth());
    c.setWidth(100);
    assertEquals(100.0,c.getWidth());
  }
  @Test
  public void testOutOfX(){
    Controller c = new Controller();
    Zone zone = new Zone(c,false,false,false);
    c.setWidth(1);
    c.setRadiusDot(0);
    Population p = new Population(c, 0, 0, 0,false,false,false);
    Individual in = new Sick(new CoquilleBille(null, p),p);
    CoquilleBille coc = new CoquilleBille(in, p);
    coc.setMovingSpeed(0,0);
    assertTrue(coc.outOfX(-1));
    assertTrue(coc.outOfX(0.0));
    assertTrue(!coc.outOfX(0.0000001));
    assertTrue(!coc.outOfX(0.9999999));
    assertTrue(coc.outOfX(100000000));
    assertTrue(coc.outOfX(10));
    assertTrue(coc.outOfX(1));

    c.setWidth(100);
    assertTrue(coc.outOfX(0.0));
    assertTrue(!coc.outOfX(1));
    assertTrue(!coc.outOfX(99.999));
    assertTrue(coc.outOfX(100));
  }

  @Test
  public void testOutOfX2(){
    Controller c = new Controller();
    Zone zone = new Zone(c,false,false,false);
    c.setWidth(100);
    c.setRadiusDot(10);
    Population p = new Population(c, 0, 0, 0,false,false,false);
    Individual in = new Sick(new CoquilleBille(null, p),p);
    CoquilleBille coc = new CoquilleBille(in, p);
    coc.setMovingSpeed(0,0);
    assertTrue(coc.outOfX(-1));
    assertTrue(coc.outOfX(0.0));
    assertTrue(coc.outOfX(8));
    assertTrue(coc.outOfX(10));
    assertTrue(!coc.outOfX(10.000001));
    assertTrue(!coc.outOfX(50.6));
    assertTrue(!coc.outOfX(89.999999));
    assertTrue(coc.outOfX(90));
    assertTrue(coc.outOfX(90.000001));
    assertTrue(coc.outOfX(100000000));

  }
}
