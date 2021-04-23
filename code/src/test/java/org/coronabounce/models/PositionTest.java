package org.coronabounce.models;

import org.coronabounce.controllers.Controller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PositionTest extends Assertions{
  @Test
  public void testPosition(){
    Controller c = new Controller();
    c.setWidth(1000);
    c.setHeigth(5);
    c.setRadiusDot(0);
    for (int i=0; i<100; i++) {
      Position p = new Position(c);
      assertTrue(p.getX()>=0);
      assertTrue(p.getX()<=1000);
      assertTrue(p.getY()>=0);
      assertTrue(p.getY()<=5);
    }
  }
  @Test
  public void testPosition2(){
    Controller c = new Controller();
    c.setWidth(10);
    c.setHeigth(500);
    c.setRadiusDot(2);
    for (int i=0; i<100; i++) {
      Position p = new Position(c);
      assertTrue(p.getX()>=2);
      assertTrue(p.getX()<=8);
      assertTrue(p.getY()>=2);
      assertTrue(p.getY()<=498);
    }
  }

}
