package edu.neu.ccs.cs5004.battleship.debug.cell;

/**
 * Represents the IsHit status of a cell.
 *
 * @author Shufan
 */

public class IsHit implements Hitable {
  @Override
  public String getHitOrNot() {
    return "This cell is hit";
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
    return 3;
  }
}
