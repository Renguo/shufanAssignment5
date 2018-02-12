package edu.neu.ccs.cs5004.battleship.debug.cell;

import java.util.Objects;

/**
 * Represents the common properties of a cell occupied by a ship.
 *
 * @author Shufan Xing
 */

public abstract class ShipCell extends Cell.AbstractCell implements IShipCell {

  protected Sunkable sunk;

  /**
   * Create a ShipCell with given information of location,hit,sunk.
   *
   * @param location the location of a ship cell on a map
   * @param hit      the hit status of a ship cell.
   * @param sunk     if sunkable status of a ship cell.
   */
  public ShipCell(Location location, Hitable hit, Sunkable sunk) {
    super(location, hit);
    this.sunk = sunk;
  }

  /**
   * Check the sunkable status of a ship cell.
   *
   * @return the information of a cell's sunkable status
   */

  public String sunkOrNot() {
    return this.sunk.getSunk();
  }

  /**
   * Change the sunkable status of a ship cell.
   *
   * @param sunk the status of sunk.
   * @return a ShipCell marked as sunk
   */
  protected abstract IShipCell shipCellFactory(Sunkable sunk);

  /**
   * Change an instance's sunk field value.
   *
   * @return a new ShipCell marked with sunkCell
   */
  @Override
  public IShipCell setSunkOfCell() {
    return shipCellFactory(new SunkCell());
  }

  @Override
  public boolean getHoldShip() {
    return false;
  }


  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    if (!super.equals(object)) {
      return false;
    }
    ShipCell shipCell = (ShipCell) object;
    return Objects.equals(sunk, shipCell.sunk);
  }

  @Override
  public int hashCode() {

    return Objects.hash(super.hashCode(), sunk);
  }

  @Override
  public String toString() {
    return "ShipCell{"
        + "sunk="
        + this.sunk.getSunk()
        + ", location="
        + location
        + ", hit="
        + this.getHit()
        + "}";
  }
}


