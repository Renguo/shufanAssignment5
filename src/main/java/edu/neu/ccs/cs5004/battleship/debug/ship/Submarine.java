package edu.neu.ccs.cs5004.battleship.debug.ship;

/**
 * Represents a submarine, with the size of 2 cells.
 *
 * @author Shufan Xing
 */

public class Submarine extends AbstractShip {
  public static final Integer submarineSize = 2;

  private Submarine(Integer shipSize, Integer hitCellNum, Direction direction) {
    super(shipSize, hitCellNum, direction);
  }

  public Submarine() {
    this(submarineSize, 0, null);
  }

  @Override
  protected Ship absShipFactory(Integer hitCellNum) {
    return new Submarine(this.shipSize, hitCellNum, this.direction);
  }

  @Override
  public String toString() {
    return "Submarine{"
        + "shipSize="
        + shipSize
        + ", hitCellNum="
        + hitCellNum
        + ", direction='"
        + direction.getDirection()
        + '\''
        + "}";
  }
}

