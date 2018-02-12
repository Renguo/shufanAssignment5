package edu.neu.ccs.cs5004.battleship.debug.gameControl;

import edu.neu.ccs.cs5004.battleship.debug.players.Computer;
import edu.neu.ccs.cs5004.battleship.debug.players.User;

public abstract class AbstractBattleShipGame implements BattleShipGame {
  private User player1;
  private Computer player2;

  public AbstractBattleShipGame(User player1, Computer player2) {
    this.player1 = player1;
    this.player2 = player2;
  }

  public User placingShip() {
    return null;

  }
}
