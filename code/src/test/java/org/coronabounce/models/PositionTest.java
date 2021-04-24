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

  @Test
  public void testCleanListTakenPositions(){
    Controller c = new Controller();
    Position.cleanListTakenPositions();
    Position p = new Position(c,false);
    assertEquals(1,p.getListTakenPositionsSize());
    Position.cleanListTakenPositions();
    assertEquals(0,p.getListTakenPositionsSize());
  }
  @Test
  public void testDistanceFrom(){
    Controller c = new Controller();
    c.setRadiusDot(0);
    Position p = new Position(c,false);
    Position p2 = new Position(c,false);
    p.setPos(0,0);
    p2.setPos(1,1);
    assertEquals(Math.sqrt(2),p.distanceFrom(p2));
    p2.setPos(10,1);
    assertEquals(Math.sqrt(10*10 + 1),p.distanceFrom(p2));
    p.setPos(2,3);
    assertEquals(Math.sqrt(8*8 + 2*2),p.distanceFrom(p2));
    assertEquals(Math.sqrt(8*8 + 2*2),p2.distanceFrom(p));
  }
  @Test
  public void testSetPos(){
    Controller c = new Controller();
    c.setRadiusDot(0);
    c.setWidth(5);
    c.setHeigth(5);
    Position p = new Position(c,false);
    p.setPos(0,0);
    assertEquals(0,p.getX());
    assertEquals(0,p.getY());
    p.setPos(1,3);
    assertEquals(1,p.getX());
    assertEquals(3,p.getY());
    p.setPos(-1,-6);
    assertEquals(0,p.getX());
    assertEquals(0,p.getY());
    p.setPos(6,10);
    assertEquals(5,p.getX());
    assertEquals(5,p.getY());
  }
  @Test
  public void testSetPos2(){
    Controller c = new Controller();
    c.setRadiusDot(1);
    c.setWidth(5);
    c.setHeigth(5);
    Position p = new Position(c,false);
    p.setPos(0,0);
    assertEquals(1,p.getX());
    assertEquals(1,p.getY());
    p.setPos(1,3);
    assertEquals(1,p.getX());
    assertEquals(3,p.getY());
    p.setPos(-1,-6);
    assertEquals(1,p.getX());
    assertEquals(1,p.getY());
    p.setPos(6,10);
    assertEquals(4,p.getX());
    assertEquals(4,p.getY());
    p.setPos(6,-345678);
    assertEquals(4,p.getX());
    assertEquals(1,p.getY());
  }
}
