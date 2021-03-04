package org.coronabounce.models;

import org.coronabounce.controllers.Controller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ZoneTest extends Assertions{
  @Test
  public void testSetWidth(){
    Zone zone = new Zone(new Controller());
    double w=zone.getWidth();
    zone.setWidth(-100);
    assertEquals(w,zone.getWidth());
    zone.setWidth(0.3);
    assertEquals(w,zone.getWidth());
    zone.setWidth(0.99999);
    assertEquals(w,zone.getWidth());
    zone.setWidth(2);
    assertEquals(2.0,zone.getWidth());
    zone.setWidth(100);
    assertEquals(100.0,zone.getWidth());
  }
  @Test
  public void testOutOfX(){
    Zone zone = new Zone(new Controller());
    zone.setWidth(1);
    assertTrue(zone.outOfX(-1));
    assertTrue(zone.outOfX(0.0));
    assertTrue(!zone.outOfX(0.0000001));
    assertTrue(!zone.outOfX(0.9999999));
    assertTrue(zone.outOfX(1));
    assertTrue(zone.outOfX(10));
    assertTrue(zone.outOfX(100000000));

    zone.setWidth(100);
    assertTrue(zone.outOfX(0.0));
    assertTrue(!zone.outOfX(1));
    assertTrue(!zone.outOfX(99.999));
    assertTrue(zone.outOfX(100));
  }
}
