package edu.neu.ccs.cs5004.battleship.game.gameControl;


import edu.neu.ccs.cs5004.battleship.game.customerplayer.User;

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
