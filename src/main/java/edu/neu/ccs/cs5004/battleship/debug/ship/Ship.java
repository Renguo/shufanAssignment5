package edu.neu.ccs.cs5004.battleship.debug.ship;

/**
 * Represents a ship in the game.
 *
 * @author Shufan Xing
 */

public interface Ship {

  /**
   * Hit a ship.
   *
   * @return a new Ship with corresponding change to the number of hit cells of a ship
   */

  Ship hit();

  /**
   * Check if a ship is a sunken ship.
   *
   * @return true if the ship is sunk, false otherwise.
   */
  Boolean isSunk();

  void setHitCellNum(Integer hitCellNum);

  Direction getDirection();

  void setDirection(Direction direction);


  static Ship createShip(Direction direction, Integer size) {
    Ship newShip;
    if (size.equals(ShipSize.ONE.getSize())) {
      newShip = new Destroyer();
      newShip.setDirection(direction);
    } else if (size.equals(ShipSize.TWO.getSize())) {
      newShip = new Submarine();
      newShip.setDirection(direction);
    } else if (size.equals(ShipSize.THREE.getSize())) {
      newShip = new Cruiser();
      newShip.setDirection(direction);
    } else {
      newShip = new BattleShip();
      newShip.setDirection(direction);
    }
    return newShip;
  }
}
