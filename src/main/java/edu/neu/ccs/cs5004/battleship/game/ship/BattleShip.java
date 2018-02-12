package edu.neu.ccs.cs5004.battleship.game.ship;

/**
 * Represents a battle ship, which has the size of 4 cells.
 *
 * @author Shufan Xing
 */

public class BattleShip extends AbstractShip {

  private static final Integer battleShipsize = 4;

  private BattleShip(Integer shipSize, Integer hitCellNum, Direction direction) {
    super(shipSize, hitCellNum, direction);
  }

  public BattleShip() {
    this(battleShipsize, 0, null);
  }


  @Override
  public Ship absShipFactory(Integer hitCellNum) {
    return new BattleShip(this.shipSize, hitCellNum, this.direction);
  }

  @Override
  public String toString() {
    return "BattleShipGame{"
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
