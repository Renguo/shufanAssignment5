package edu.neu.ccs.cs5004.battleship.debug.cell;

/**
 * Represents a water cell with adjacent ship cell.
 *
 * @author Shufan Xing
 */

public class GapCell extends WaterCell {
  public GapCell(Location location, Hitable hit) {
    super(location, hit);
  }


  @Override
  public boolean getHoldShip() {
    return false;
  }

  @Override
  public Cell absCellFactory(Location location, Hitable hit) {
    return new GapCell(location, hit);
  }

  @Override
  public String toString() {
    return "GapCell{"
        + "location="
        + location
        + ", hit="
        + this.getHit()
        + "}";
  }
}
