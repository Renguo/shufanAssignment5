package edu.neu.ccs.cs5004.battleship.game.ship;

public enum Direction {
  Horizontal("Horizontal"),
  Vertical("Vertical");

  String direction;

  Direction(String direction) {
    this.direction = direction;
  }

  public String getDirection() {
    return this.direction;
  }

  public static Direction getDirection(String input) {
    for (Direction direction : Direction.values()) {
      if (input.equals(direction.getDirection())) {
        return direction;
      }
    }
    throw new IllegalArgumentException("Ship can only be placed vertical or horizontal");
  }
}
