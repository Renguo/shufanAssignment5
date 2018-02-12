package edu.neu.ccs.cs5004.battleship.debug.cell;


/**
 * Represents ship cell in the game.
 *
 * @author Shufan
 */

public interface IShipCell extends Cell {
  /**
   * Change an instance's sunk field value to sunk.
   *
   * @return a new ShipCell marked with sunkCell
   */
  IShipCell setSunkOfCell();
}
