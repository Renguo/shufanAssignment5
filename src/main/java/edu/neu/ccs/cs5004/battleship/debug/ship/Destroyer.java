package edu.neu.ccs.cs5004.battleship.debug.ship;

/**
 * Represents a destroyer, with the size of 1 cell.
 *
 * @author Shufan
 */

public class Destroyer extends AbstractShip {

  private static final Integer destroyerSize = 1;

  private Destroyer(Integer shipSize, Integer hitCellNum, Direction direction) {
    super(shipSize, hitCellNum, direction);
  }

  public Destroyer() {
    this(destroyerSize, 0, null);
  }


  @Override
  public Ship absShipFactory(Integer hitCellNum) {
    return new Destroyer(this.shipSize, hitCellNum, this.direction);
  }

  @Override
  public String toString() {
    return "Destroyer{"
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
