package org.coronabounce.models;

import org.coronabounce.controllers.Controller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.coronabounce.models.exceptions.ToMuchPointsException;

public class PositionTest extends Assertions{
  @Test
  public void testPosition(){
    Controller c = new Controller();
    Position.cleanListTakenPositions();
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
    Position.cleanListTakenPositions();
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
  @Test
  public void testPosition3(){
    Controller c = new Controller();
    Position.cleanListTakenPositions();
    c.setWidth(3);
    c.setHeigth(3);
    c.setRadiusDot(0.9);
    Position p = new Position(c);
    assertThrows(ToMuchPointsException.class, () -> {
        Position p2 = new Position(c);
    });
  }
  @Test
  public void testPosition4(){
    Controller c = new Controller();
    Position.cleanListTakenPositions();
    c.setWidth(5);
    c.setHeigth(5);
    c.setRadiusDot(2.2);
    assertThrows(IllegalArgumentException.class, () -> {
      Position p = new Position(c);
    });
  }

  @Test
  public void testPosition5(){
    Controller c = new Controller();
    Position.cleanListTakenPositions();
    c.setWidth(5);
    c.setHeigth(5);
    c.setRadiusDot(1.9);
    Position p = new Position(c);
    assertThrows(ToMuchPointsException.class, () -> {
        Position p2 = new Position(c);
    });
  }
  @Test
  public void testPosition6(){
    Controller c = new Controller();
    Position.cleanListTakenPositions();
    c.setWidth(5);
    c.setHeigth(5);
    c.setRadiusDot(1.9);
    Position p = new Position(c,false);
    //It work if we accept to have 2 CoquilleBille at the same place.
    Position p2 = new Position(c,false);
  }
}
