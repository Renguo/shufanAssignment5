package edu.neu.ccs.cs5004.battleship.game.gameControl;

import edu.neu.ccs.cs5004.battleship.game.customerplayer.User;
import edu.neu.ccs.cs5004.battleship.game.computerplayer.Computer;

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
