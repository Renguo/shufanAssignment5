package edu.neu.ccs.cs5004.battleship.game.cell;


import edu.neu.ccs.cs5004.battleship.game.map.Column;
import edu.neu.ccs.cs5004.battleship.game.map.Row;

/**
 * Represents a cell's location on the map.
 *
 * @author Shufan Xing
 */

public class Location {

  private Row locx;
  private Column locy;

  /**
   * Creates a location given the x and y location on the map.
   *
   * @param locx the cell numbers to the left bound of the map.
   *             It should be smaller than the max number of cells in a row.
   * @param locy the cell numbers to the upper bound of the map.
   *             It should be smaller than the max number of cells in a column.
   */

  public Location(Row locx, Column locy) {
    this.locx = locx;
    this.locy = locy;
  }

  /**
   * Getter for a location's x value.
   *
   * @return x
   */

  public Row getX() {
    return locx;
  }

  /**
   * Getter for a location's y value.
   *
   * @return y
   */

  public Column getY() {
    return locy;
  }

  public Location moveLeft(Integer cellNum) {
    return new Location(this.locx, Column.getColumn(this.locy.getColumnNum() - cellNum));
  }

  public Location moveRight(Integer cellNum) {
    return new Location(this.locx, Column.getColumn(this.locy.getColumnNum() + cellNum));
  }

  public Location moveDown(Integer cellNum) {
    return new Location(Row.getRow(this.locx.getRowNum() + cellNum), this.locy);
  }

  public Location moveUp(Integer cellNum) {
    return new Location(Row.getRow(this.locx.getRowNum() - cellNum), this.locy);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }

    Location location = (Location) object;

    if (this.getX() != null ? !getX().equals(location.getX()) : location.getX() != null) {
      return false;
    }
    return getY() != null ? getY().equals(location.getY()) : location.getY() == null;
  }

  @Override
  public int hashCode() {
    int result = getX() != null ? getX().hashCode() : 0;
    result = 31 * result + (getY() != null ? getY().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Location{"
        + "x="
        + locx
        + ", y="
        + locy
        + '}';
  }

}
