package edu.neu.ccs.cs5004.battleship.debug.gameControl;


import edu.neu.ccs.cs5004.battleship.debug.players.User;

/**
 * Represents the battle ship game
 *
 * @author Shufan
 */
public interface BattleShipGame {

  /**
   * Create a map with only OpenWaterCell.
   *
   * @return a Map with only OpenWaterCell and without any ship placed on the map
   */
  User placingShip();


}
