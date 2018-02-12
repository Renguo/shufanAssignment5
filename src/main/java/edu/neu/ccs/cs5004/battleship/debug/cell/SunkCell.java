package edu.neu.ccs.cs5004.battleship.debug.cell;

/**
 * Represents a ship cell marked as sunk.
 *
 * @author Shufan Xing
 */

public class SunkCell implements Sunkable {
  @Override
  public String getSunk() {
    return "This cell belongs to a sunken ship";
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
    return 29;
  }
}
