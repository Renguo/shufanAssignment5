package edu.neu.ccs.cs5004.battleship.game.cell;

/**
 * Represents a cell not marked as a sunk cell.
 *
 * @author Shufan
 */

public class NotSunkCell implements Sunkable {
  @Override
  public String getSunk() {
    return "This cell does not belong to a sunken ship";
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
    return 37;
  }
}
