package org.coronabounce.models.exceptions;

/**
*{@summary exceptions class for a to much point exeption.}<br>
*/
public class ToMuchPointsException extends RuntimeException {
  /**
  *{@summary Constructs a new runtime exception with a detail message.}<br>
  */
  public ToMuchPointsException(){
    super("There is to much point on the map to fined a new free Position.");
  }
}
