package edu.neu.ccs.cs5004.battleship.debug.cell;

import edu.neu.ccs.cs5004.battleship.debug.ship.Ship;

import java.util.Objects;

/**
 * Represents a cell occupied by a surely existing ship of player himself.
 *
 * @author Shufan Xing
 */

public class SpecificShipCell extends ShipCell {
  private Ship ship;

  /**
   * Create a SpecificShipCell with given location, hit, sunk, ship fields information.
   *
   * @param location the location of a SpecificShipCell on the map
   * @param hit      the hit status of a SpecificShipCell
   * @param sunk     the sunkable status of a SpecificShipCell
   * @param ship     the ship in the cell
   */

  public SpecificShipCell(Location location, Hitable hit, Sunkable sunk, Ship ship) {
    super(location, hit, sunk);
    this.ship = ship;
  }

  @Override
  public Cell absCellFactory(Location location, Hitable hit) {
    if (this.ship.hit().isSunk()) {
      return new SpecificShipCell(location, hit, new SunkCell(), this.ship.hit());
    } else {
      return new SpecificShipCell(location, hit, this.sunk, this.ship.hit());
    }
  }

  @Override
  public ShipCell shipCellFactory(Sunkable sunk) {
    if (this.ship.isSunk()) {
      return new SpecificShipCell(this.location, this.hit, sunk, this.ship);
    } else {
      return this;
    }
  }

  @Override
  public String getAttackResult() {
    if (this.ship.hit().isSunk() || this.ship.isSunk()) {
      return (new EnemyShipCell.Sunk()).attackResult();
    } else {
      return (new EnemyShipCell.Hit()).attackResult();
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
    if (!super.equals(object)) {
      return false;
    }
    SpecificShipCell that = (SpecificShipCell) object;
    return Objects.equals(ship, that.ship);
  }

  @Override
  public int hashCode() {

    return Objects.hash(super.hashCode(), ship);
  }

  @Override
  public String toString() {
    return "SpecificShipCell{"
        + "ship="
        + ship
        + ", sunk="
        + sunk.getSunk()
        + ", location="
        + location
        + ", hit="
        + hit.getHitOrNot()
        + "}";
  }
}
