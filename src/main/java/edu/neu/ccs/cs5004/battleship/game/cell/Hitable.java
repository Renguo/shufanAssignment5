package edu.neu.ccs.cs5004.battleship.game.cell;

/**
 * Represents the hitable property of a cell.
 *
 * @author Shufan
 */

public interface Hitable {

  /**
   * Show the hit status of a cell.
   *
   * @return the information about if a cell was hit
   */

  String getHitOrNot();
}
