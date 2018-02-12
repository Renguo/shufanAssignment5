package edu.neu.ccs.cs5004.battleship.debug.cell;

/**
 * Represents the not hit status of a cell.
 *
 * @author Shufan
 */

public class NotHit implements Hitable {

  @Override
  public String getHitOrNot() {
    return "This cell is not hit";
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    return 11;
  }
}
