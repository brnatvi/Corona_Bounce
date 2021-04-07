package org.coronabounce.models;

import org.coronabounce.controllers.Controller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ZoneTest extends Assertions{
  @Test
  public void testSetWidth(){
    Zone zone = new Zone(new Controller(),false);
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
    Zone zone = new Zone(new Controller(),false);
    Controller.setWidth(1);
    assertTrue(CoquilleBille.outOfX(-1));
    assertTrue(CoquilleBille.outOfX(0.0));
    assertTrue(!CoquilleBille.outOfX(0.0000001));
    assertTrue(!CoquilleBille.outOfX(0.9999999));
    assertTrue(CoquilleBille.outOfX(1));
    assertTrue(CoquilleBille.outOfX(10));
    assertTrue(CoquilleBille.outOfX(100000000));

   Controller.setWidth(100);
    assertTrue(CoquilleBille.outOfX(0.0));
    assertTrue(!CoquilleBille.outOfX(1));
    assertTrue(!CoquilleBille.outOfX(99.999));
    assertTrue(CoquilleBille.outOfX(100));
  }
}
