package edu.neu.ccs.cs5004.battleship.debug.cell;

/**
 * Represents the sunkable property of a ship cell.
 *
 * @author Shufan Xing
 */
public interface Sunkable {
  /**
   * Check if a ship cell is marked sunk.
   *
   * @return a string with information about whether this cell is marked as a sunk cell
   */
  String getSunk();
}
