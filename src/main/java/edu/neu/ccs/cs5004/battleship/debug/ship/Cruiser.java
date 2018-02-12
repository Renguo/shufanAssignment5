package edu.neu.ccs.cs5004.battleship.debug.ship;

/**
 * Represents a Cruiser ship , the size of a Cruiser is 3 cells.
 *
 * @author Shufan Xing
 */

public class Cruiser extends AbstractShip {
  private static final Integer cruiserSize = 3;

  private Cruiser(Integer shipSize, Integer hitCellNum, Direction direction) {
    super(shipSize, hitCellNum, direction);
  }

  public Cruiser() {
    this(cruiserSize, 0, null);
  }


  @Override
  public Ship absShipFactory(Integer hitCellNum) {
    return new Cruiser(this.shipSize, hitCellNum, this.direction);
  }

  @Override
  public String toString() {
    return "Cruiser{"
        + "shipSize="
        + shipSize
        + ", hitCellNum="
        + hitCellNum
        + ", direction='"
        + direction
        + '\''
        + "}";
  }
}
