package edu.neu.ccs.cs5004.battleship.game.cell;


/**
 * Represents a Water Cell holding no ship on the map.
 *
 * @author Shufan Xing
 */
public abstract class WaterCell extends Cell.AbstractCell implements IWaterCell {
  public WaterCell(Location location, Hitable hit) {
    super(location, hit);
  }

  /**
   * Setter for the water cell location.
   *
   * @param loc the location of a water cell
   */
  public void setWaterCell(Location loc) {
    this.location = loc;
  }


  public String getAttackResult() {
    return (new EnemyShipCell.Miss()).attackResult();
  }
}
