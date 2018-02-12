package edu.neu.ccs.cs5004.battleship.game.cell;

import java.util.Objects;

/**
 * Represents a cell on the map.
 *
 * @author Shufan Xing
 */

public interface Cell {

  /**
   * Check if a cell can hold a ship.
   *
   * @return true if the cell can hold a ship,
   * false otherwise
   */

  boolean getHoldShip();

  /**
   * Check what will happen, if a cell was attacked .
   *
   * @return "miss" if the cell is a water cell
   * "hit" if the cell is a ship cell and ship possessing this cell still
   * has none-hit cells.
   * "sunk" if the cell is a ship cell and all the ship possessing cells are hit.
   */

  String getAttackResult();


  /**
   * Mark a cell as hit, if a cell is attacked.
   *
   * @return a new cell with the cell itself and the ship in this cell marked with hit
   */
  Cell attack();

  /**
   * Getter for the location of a cell on the map.
   *
   * @return the location of a cell on the map
   */
  Location getLocation();

  /**
   * Represents the common properties of a cell.
   *
   * @author Shufan Xing
   */

  abstract class AbstractCell implements Cell {

    protected Location location;
    protected Hitable hit;

    /**
     * Create an AbstractCell with given Location, hit status.
     *
     * @param location the location of cell on the map
     * @param hit      whether a hitable cell is hit or not
     */

    public AbstractCell(Location location, Hitable hit) {
      this.location = location;
      this.hit = hit;
    }


    /**
     * Getter of the cell's location.
     *
     * @return the location of the cell
     */

    public Location getLocation() {
      return this.location;
    }

    /**
     * Getter for the information of whether the cell is hit.
     *
     * @return "This cell is hit" if the cell is hit or "This cell is not hit" if the cell is not hit
     */

    public String getHit() {
      return this.hit.getHitOrNot();
    }

    /**
     * To change an instance value belongs to an AbstractCell.
     *
     * @param location the location of a cell on the map.
     * @param hit      the hit status of a cell.
     * @return a new cell with the given information.
     */

    protected abstract Cell absCellFactory(Location location, Hitable hit);


    public Cell attack() {
      return absCellFactory(this.location, new IsHit());
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
        return true;
      }
      if (object == null || getClass() != object.getClass()) {
        return false;
      }
      AbstractCell that = (AbstractCell) object;
      return Objects.equals(location, that.location)
          && Objects.equals(hit, that.hit);
    }

    @Override
    public int hashCode() {

      return Objects.hash(location, hit);
    }

    @Override
    public String toString() {
      return "AbstractCell{"
          + "location="
          + location
          + ", hit="
          + hit
          + '}';
    }
  }
}
