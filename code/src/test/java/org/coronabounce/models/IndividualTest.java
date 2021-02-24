package org.coronabounce.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IndividualTest extends Assertions{
  @Test
  public void testHealthState(){
    Individual i = new Sick();
    assertEquals("Sick",i.healthState());
    i = new Recovered();
    assertEquals("Recovered",i.healthState());
    i = new Healthy();
    assertEquals("Healthy",i.healthState());
  }
  @Test
  public void testIsSick(){
    Individual i = new Sick();
    assertTrue(i.isSick());
    i = new Recovered();
    assertTrue(!i.isSick());
    i = new Healthy();
    assertTrue(!i.isSick());
  }

}
