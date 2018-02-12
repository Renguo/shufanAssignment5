package edu.neu.ccs.cs5004.battleship.game.ship;

public enum ShipSize {
  ONE(1),
  TWO(2),
  THREE(3),
  FOUR(4);

  Integer size;

  ShipSize(Integer size) {
    this.size = size;
  }

  public Integer getSize() {
    return size;
  }

  public ShipSize getEnumShipSize(Integer index) {
    for (ShipSize shipSize : ShipSize.values()) {
      if (index.equals(shipSize.getSize())) {
        return shipSize;
      }
    }
    throw new IllegalArgumentException("This ship size is not valid");
  }
}
