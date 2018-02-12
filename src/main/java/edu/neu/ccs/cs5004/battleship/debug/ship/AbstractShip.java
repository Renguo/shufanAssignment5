package edu.neu.ccs.cs5004.battleship.debug.ship;

import java.util.Objects;

/**
 * represents the common properties of a ship.
 *
 * @author Shufan Xing
 */

public abstract class AbstractShip implements Ship {
  protected Integer shipSize;
  protected Integer hitCellNum;
  protected Direction direction;


  /**
   * Create an AbstractShip given the size of a ship and the hit cells number of a ship,
   * and the placement method of a ship on the map.
   *
   * @param shipSize   the size of ship (how many cells the ship occupy; each kind of
   *                   ship has fix cell number)
   * @param hitCellNum the hit cell number of a ship (none negative integer and
   *                   no larger than the ship's size)
   * @param direction  the placement of a ship, which can be horizontal or vertical
   */

  protected AbstractShip(Integer shipSize, Integer hitCellNum, Direction direction) {
    this.hitCellNum = hitCellNum;
    this.shipSize = shipSize;
    this.direction = direction;
  }

  public void setHitCellNum(Integer hitCellNum) {
    this.hitCellNum = hitCellNum;
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  /**
   * Change the hitCellNum value of a ship.
   *
   * @param hitCellNum the new hitCellNum value
   * @return a ship with new hitCellNum value
   */

  protected abstract Ship absShipFactory(Integer hitCellNum);

  /**
   * Getter for the hit cells number of a ship.
   *
   * @return hit cells number
   */

  public Integer getHitCellNum() {
    return this.hitCellNum;
  }

  /**
   * Getter for the ship's size.
   *
   * @return the cell-occupation number of a ship
   */

  public Integer getShipSize() {
    return this.shipSize;
  }

  /**
   * Getter for the placement of a ship.
   *
   * @return how the ship was placed on the map
   */

  public Direction getDirection() {
    return this.direction;
  }


  public Ship hit() {
    if (this.getShipSize() > this.hitCellNum) {
      return absShipFactory(this.hitCellNum + 1);
    } else {
      return this;
    }
  }


  public Boolean isSunk() {
    if (this.hitCellNum >= this.shipSize) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    AbstractShip that = (AbstractShip) object;
    return Objects.equals(shipSize, that.shipSize)
        && Objects.equals(hitCellNum, that.hitCellNum)
        && Objects.equals(direction, that.direction);
  }

  @Override
  public int hashCode() {

    return Objects.hash(shipSize, hitCellNum, direction);
  }

  @Override
  public String toString() {
    return "AbstractShip{"
        + "shipSize="
        + shipSize
        + ", hitCellNum="
        + hitCellNum
        + ", direction='"
        + direction.getDirection()
        + '\''
        + '}';
  }
}
