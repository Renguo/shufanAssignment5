package edu.neu.ccs.cs5004.battleship.debug.cell;


/**
 * Represents a cell without any adjacent ship cell.
 *
 * @author Shufan Xing
 */

public class OpenSeaCell extends WaterCell {
  public OpenSeaCell(Location location, Hitable hit) {
    super(location, hit);
  }

  @Override
  public boolean getHoldShip() {
    return true;
  }

  @Override
  public Cell absCellFactory(Location location, Hitable hit) {
    return new OpenSeaCell(location, hit);
  }

  @Override
  public String toString() {
    return "OpenSeaCell{"
        + "location="
        + location
        + ", hit="
        + hit.getHitOrNot()
        + "}";
  }
}
