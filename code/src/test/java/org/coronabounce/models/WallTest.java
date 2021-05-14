package org.coronabounce.models;

import org.coronabounce.controllers.Controller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WallTest extends Assertions{
  private Controller c;
  private Population p;
  private CoquilleBille coc;
  private Wall w;
  private void ini(){
    c = new Controller();
    c.setWidth(50);
    c.setHeigth(50);
    c.setThickness(2);
    c.setRadiusDot(2);
    p = new Population(c, 0, 0, 0,false,false,false);
    coc = new CoquilleBille(null, p);
    Individual i = new Sick(coc,p);
    coc.setIndividual(i);
    coc.setPos(10,10);
    w = new Wall(c,15, p.getAllPoints());
    w.setPositionY(5);
  }
  @Test
  public void testIsIntoTheWallUnder(){
    ini();
    assertTrue(!w.isIntoTheWall(coc));
    coc.setPos(15,10);
    assertTrue(!w.isIntoTheWall(coc));
    coc.setPos(15,7);
    assertTrue(!w.isIntoTheWall(coc));
    coc.setPos(15,6.99);
    assertTrue(w.isIntoTheWall(coc));
    coc.setPos(15,6);
    assertTrue(w.isIntoTheWall(coc));
  }
  @Test
  public void testIsIntoTheWallUnder2(){
    ini();
    c.setThickness(8);
    assertTrue(!w.isIntoTheWall(coc));
    coc.setPos(15,10);
    assertTrue(!w.isIntoTheWall(coc));
    coc.setPos(15,7);
    assertTrue(!w.isIntoTheWall(coc));
    coc.setPos(15,6.99);
    assertTrue(w.isIntoTheWall(coc));
    coc.setPos(15,6);
    assertTrue(w.isIntoTheWall(coc));
  }
  @Test
  public void testIsIntoTheWallLeft(){
    ini();
    coc.setPos(14,1);
    assertTrue(w.isIntoTheWall(coc));
    coc.setPos(14,2);
    assertTrue(w.isIntoTheWall(coc));
    coc.setPos(14,0);
    assertTrue(w.isIntoTheWall(coc));
    coc.setPos(14,5);
    assertTrue(w.isIntoTheWall(coc));
    coc.setPos(14,4);
    assertTrue(w.isIntoTheWall(coc));
    coc.setPos(13,1);
    assertTrue(w.isIntoTheWall(coc));
    coc.setPos(12.01,1);
    assertTrue(w.isIntoTheWall(coc));
    coc.setPos(12,1);
    assertTrue(!w.isIntoTheWall(coc));
  }
  @Test
  public void testIsIntoTheWallCorner(){
    ini();
    coc.setPos(14,6.99);
    assertTrue(w.isIntoTheWall(coc));
    coc.setPos(13,6.99);
    assertTrue(w.isIntoTheWall(coc));
    coc.setPos(12.01,6.99);
    assertTrue(w.isIntoTheWall(coc));
    coc.setPos(12,6.99);
    assertTrue(!w.isIntoTheWall(coc));
    coc.setPos(10,6.99);
    assertTrue(!w.isIntoTheWall(coc));
    coc.setPos(2,6.99);
    assertTrue(!w.isIntoTheWall(coc));
  }
  @Test
  public void testIsIntoTheWallRigth(){
    ini();
    coc.setPos(18,1);
    assertTrue(!w.isIntoTheWall(coc));
    coc.setPos(17.999,1);
    assertTrue(w.isIntoTheWall(coc));
  }
  @Test
  public void testWillGoIntoTheWallWithoutMouving(){
    ini();
    coc.setMovingSpeed(0,0);
    coc.setPos(18,1);
    assertTrue(!w.willGoIntoTheWall(coc));
    coc.setPos(17.999,1);
    assertTrue(w.willGoIntoTheWall(coc));

    coc.setPos(12,6.99);
    assertTrue(!w.willGoIntoTheWall(coc));
    coc.setPos(10,6.99);
    assertTrue(!w.willGoIntoTheWall(coc));

    coc.setPos(15,7);
    assertTrue(!w.willGoIntoTheWall(coc));
    coc.setPos(15,6.99);
    assertTrue(w.willGoIntoTheWall(coc));
  }
  @Test
  public void testWillGoIntoTheWallY1(){
    ini();
    coc.setMovingSpeed(0,0.5);
    coc.setPos(15,7);
    assertTrue(!w.willGoIntoTheWall(coc));
    coc.setPos(15,6.99);
    assertTrue(!w.willGoIntoTheWall(coc));
    coc.setPos(15,6.5);
    assertTrue(!w.willGoIntoTheWall(coc));
    coc.setPos(15,6.49);
    assertTrue(w.willGoIntoTheWall(coc));
  }
  @Test
  public void testWillGoIntoTheWallY2(){
    ini();
    coc.setMovingSpeed(0,-0.5);
    coc.setPos(15,7.49);
    assertTrue(w.willGoIntoTheWall(coc));
    coc.setPos(15,6.99);
    assertTrue(w.willGoIntoTheWall(coc));
    coc.setPos(15,7.5);
    assertTrue(!w.willGoIntoTheWall(coc));
    coc.setPos(15,8);
    assertTrue(!w.willGoIntoTheWall(coc));
    coc.setPos(15,15);
    assertTrue(!w.willGoIntoTheWall(coc));
  }
  @Test
  public void testWillGoIntoTheWallX1(){
    ini();
    coc.setMovingSpeed(2,0);
    coc.setPos(10.01,4);
    assertTrue(w.willGoIntoTheWall(coc));
    coc.setPos(10,4);
    assertTrue(!w.willGoIntoTheWall(coc));
    coc.setPos(10.2,5);
    assertTrue(w.willGoIntoTheWall(coc));
    coc.setPos(10.2,7.5);
    assertTrue(!w.willGoIntoTheWall(coc));
  }
  @Test
  public void testWillGoIntoTheWallXY1(){
    ini();
    coc.setMovingSpeed(2,-1);
    coc.setPos(10.01,4);
    assertTrue(w.willGoIntoTheWall(coc));
    coc.setPos(10,4);
    assertTrue(!w.willGoIntoTheWall(coc));
    coc.setPos(10.2,5);
    assertTrue(w.willGoIntoTheWall(coc));
    coc.setPos(10.2,7.5);
    assertTrue(w.willGoIntoTheWall(coc));
    coc.setPos(10.2,7.99);
    assertTrue(w.willGoIntoTheWall(coc));
    coc.setPos(10,8);
    assertTrue(!w.willGoIntoTheWall(coc));
  }
  // @Test
  // public void testWillCrossWallInY(){
  //   ini();
  //   coc.setMovingSpeed(0,-1);
  //   coc.setPos(4,6.99);
  //   assertTrue(!w.willCrossWallInY(coc));
  //   coc.setPos(4,7.01);
  //   assertTrue(w.willCrossWallInY(coc));
  //   coc.setPos(4,7.99);
  //   assertTrue(w.willCrossWallInY(coc));
  //   coc.setPos(4,8);
  //   assertTrue(!w.willCrossWallInY(coc));
  // }
}
