package edu.neu.ccs.cs5004.battleship.debug.players;

import edu.neu.ccs.cs5004.battleship.debug.map.Column;
import edu.neu.ccs.cs5004.battleship.debug.ConsolePrinter;
import edu.neu.ccs.cs5004.battleship.debug.map.Map;
import edu.neu.ccs.cs5004.battleship.debug.map.Row;
import edu.neu.ccs.cs5004.battleship.debug.cell.*;
import edu.neu.ccs.cs5004.battleship.debug.ship.*;

import java.io.IOException;
import java.util.Random;

/**
 * Represents the computer player.
 *
 * @author Shufan
 */

public class Computer {
  private Map fleetMap;

  private Integer shipNum;

  private static final Ship[] fleet = {new BattleShip(), new Cruiser(),
      new Cruiser(), new Submarine(), new Submarine(),
      new Submarine(), new Destroyer(), new Destroyer(), new Destroyer(), new Destroyer()};

  private Computer(Map fleetMap, Integer shipNum) {
    this.fleetMap = fleetMap;
    this.shipNum = shipNum;
  }

  public Computer() {
    this(new Map(), 0);
  }

  public void setShipNum(Integer shipNum) {
    this.shipNum = shipNum;
  }


  public void randomPopulateFleetMap() {
    Ship newShip;
    Location newLocation;
    for (int i = 0; i < 10; i++) {
      newShip = Computer.fleet[i];

      Random rand = new Random();
      int n = rand.nextInt(2) + 1;
      if (n == 1) {
        newShip.setDirection(Direction.Horizontal);
      } else {
        newShip.setDirection(Direction.Vertical);
      }

      do {
        int randomRow = rand.nextInt(10);
        int randomColumn = rand.nextInt(10);
        newLocation = new Location(Row.getRow(randomRow + 1), Column.getColumn(randomColumn + 1));
      }
      while (!this.cellIsRightForShip(this.fleetMap, newLocation, newShip) ||
          !(this.fleetMap.getter(newLocation) instanceof OpenSeaCell));

      this.fleetMap.setMap(newLocation,
          new SpecificShipCell(newLocation, new NotHit(), new NotSunkCell(), newShip));
      this.updateMap(this.fleetMap, newLocation, newShip);

    }
  }


  public boolean cellIsRightForShip(Map map, Location loc, Ship newShip) {
    if (newShip.getDirection().equals(Direction.Horizontal)) {
      try {
        if (newShip instanceof Destroyer) {
          return true;
        } else if ((newShip instanceof Submarine) &&
            (map.getter(loc.moveRight(1)) instanceof OpenSeaCell)) {
          return true;
        } else if ((newShip instanceof Cruiser) &&
            (map.getter(loc.moveRight(2)) instanceof OpenSeaCell)) {
          return true;

        } else if ((newShip instanceof BattleShip) &&
            (map.getter(loc.moveRight(3)) instanceof OpenSeaCell)) {
          return true;

        } else {
          return false;
        }
      } catch (IllegalArgumentException e) {
        return false;
      }
    } else {
      try {
        if (newShip instanceof Destroyer) {
          return true;
        } else if ((newShip instanceof Submarine) &&
            (map.getter(loc.moveDown(1)) instanceof OpenSeaCell)) {
          return true;
        } else if ((newShip instanceof Cruiser) &&
            (map.getter(loc.moveDown(2)) instanceof OpenSeaCell)) {
          return true;

        } else if ((newShip instanceof BattleShip) &&
            (map.getter(loc.moveDown(3)) instanceof OpenSeaCell)) {
          return true;

        } else {
          return false;
        }
      } catch (IllegalArgumentException e) {
        return false;
      }

    }

  }

  public void updateMap(Map map, Location loc, Ship newShip) {
    if (newShip.getDirection().equals(Direction.Horizontal)) {
      if (newShip instanceof Destroyer) {
        for (int i = (loc.getX().getRowNum() - 2 < 0 ? 0 : loc.getX().getRowNum() - 2);
             i <= (loc.getX().getRowNum() > 9 ? 9 : loc.getX().getRowNum());
             i++) {
          for (int j = (loc.getY().getColumnNum() - 2 < 0 ? 0 : loc.getY().getColumnNum() - 2);
               j <= (loc.getY().getColumnNum() > 9 ? 9 : loc.getY().getColumnNum()); j++) {
            map.setMap(new Location(Row.getRow(i + 1), Column.getColumn(j + 1)),
                new GapCell(new Location(Row.getRow(i + 1), Column.getColumn(j + 1)), new NotHit()));
          }
        }
        map.setMap(loc, new SpecificShipCell(loc, new NotHit(), new NotSunkCell(), newShip));

      } else if (newShip instanceof Submarine) {
        for (int i = (loc.getX().getRowNum() - 2 < 0 ? 0 : loc.getX().getRowNum() - 2);
             i <= (loc.getX().getRowNum() > 9 ? 9 : loc.getX().getRowNum());
             i++) {
          for (int j = (loc.getY().getColumnNum() - 2 < 0 ? 0 : loc.getY().getColumnNum() - 2);
               j <= (loc.getY().getColumnNum() + 1 > 9 ? 9 : loc.getY().getColumnNum() + 1);
               j++) {
            map.setMap(new Location(Row.getRow(i + 1), Column.getColumn(j + 1)),
                new GapCell(new Location(Row.getRow(i + 1), Column.getColumn(j + 1)), new NotHit()));
          }
        }

        map.setMap(loc, new SpecificShipCell(loc, new NotHit(), new NotSunkCell(), newShip));
        map.setMap(loc.moveRight(1), new SpecificShipCell(loc.moveRight(1), new NotHit(), new NotSunkCell(), newShip));


      } else if (newShip instanceof Cruiser) {
        for (int i = (loc.getX().getRowNum() - 2 < 0 ? 0 : loc.getX().getRowNum() - 2);
             i <= (loc.getX().getRowNum() > 9 ? 9 : loc.getX().getRowNum());
             i++) {
          for (int j = (loc.getY().getColumnNum() - 2 < 0 ? 0 : loc.getY().getColumnNum() - 2);
               j <= (loc.getY().getColumnNum() + 2 > 9 ? 9 : loc.getY().getColumnNum() + 2);
               j++) {
            map.setMap(new Location(Row.getRow(i + 1), Column.getColumn(j + 1)),
                new GapCell(new Location(Row.getRow(i + 1), Column.getColumn(j + 1)), new NotHit()));
          }
        }

        for (int i = 0; i <= 2; i++) {
          map.setMap(loc.moveRight(i), new SpecificShipCell(loc.moveRight(i), new NotHit(), new NotSunkCell(), newShip));
        }
      } else if (newShip instanceof BattleShip) {
        for (int i = (loc.getX().getRowNum() - 2 < 0 ? 0 : loc.getX().getRowNum() - 2);
             i <= (loc.getX().getRowNum() > 9 ? 9 : loc.getX().getRowNum());
             i++) {
          for (int j = (loc.getY().getColumnNum() - 2 < 0 ? 0 : loc.getY().getColumnNum() - 2);
               j <= (loc.getY().getColumnNum() + 3 > 9 ? 9 : loc.getY().getColumnNum() + 3);
               j++) {
            map.setMap(new Location(Row.getRow(i + 1), Column.getColumn(j + 1)),
                new GapCell(new Location(Row.getRow(i + 1), Column.getColumn(j + 1)), new NotHit()));
          }
        }
        for (int i = 0; i <= 3; i++) {
          map.setMap(loc.moveRight(i), new SpecificShipCell(loc.moveRight(i), new NotHit(), new NotSunkCell(), newShip));
        }

      } else {
        ;
      }
    } else {
      if (newShip instanceof Destroyer) {
        for (int i = (loc.getX().getRowNum() - 2 < 0 ? 0 : loc.getX().getRowNum() - 2);
             i <= (loc.getX().getRowNum() > 9 ? 9 : loc.getX().getRowNum());
             i++) {
          for (int j = (loc.getY().getColumnNum() - 2 < 0 ? 0 : loc.getY().getColumnNum() - 2);
               j <= (loc.getY().getColumnNum() > 9 ? 9 : loc.getY().getColumnNum());
               j++) {
            map.setMap(new Location(Row.getRow(i + 1), Column.getColumn(j + 1)),
                new GapCell(new Location(Row.getRow(i + 1), Column.getColumn(j + 1)), new NotHit()));
          }
        }
        map.setMap(loc, new SpecificShipCell(loc, new NotHit(), new NotSunkCell(), newShip));

      } else if (newShip instanceof Submarine) {
        for (int i = (loc.getX().getRowNum() - 2 < 0 ? 0 : loc.getX().getRowNum() - 2);
             i <= (loc.getX().getRowNum() + 1 > 9 ? 9 : loc.getX().getRowNum() + 1);
             i++) {
          for (int j = (loc.getY().getColumnNum() - 2 < 0 ? 0 : loc.getY().getColumnNum() - 2);
               j <= (loc.getY().getColumnNum() > 9 ? 9 : loc.getY().getColumnNum());
               j++) {
            map.setMap(new Location(Row.getRow(i + 1), Column.getColumn(j + 1)),
                new GapCell(new Location(Row.getRow(i + 1), Column.getColumn(j + 1)), new NotHit()));
          }
        }

        map.setMap(loc, new SpecificShipCell(loc, new NotHit(), new NotSunkCell(), newShip));
        map.setMap(loc.moveDown(1), new SpecificShipCell(loc.moveDown(1), new NotHit(), new NotSunkCell(), newShip));

      } else if (newShip instanceof Cruiser) {
        for (int i = (loc.getX().getRowNum() - 2 < 0 ? 0 : loc.getX().getRowNum() - 2);
             i <= (loc.getX().getRowNum() + 2 > 9 ? 9 : loc.getX().getRowNum() + 2);
             i++) {
          for (int j = (loc.getY().getColumnNum() - 2 < 0 ? 0 : loc.getY().getColumnNum() - 2);
               j <= (loc.getY().getColumnNum() > 9 ? 9 : loc.getY().getColumnNum());
               j++) {
            map.setMap(new Location(Row.getRow(i + 1), Column.getColumn(j + 1)),
                new GapCell(new Location(Row.getRow(i + 1), Column.getColumn(j + 1)), new NotHit()));
          }
        }

        for (int i = 0; i <= 2; i++) {
          map.setMap(loc.moveDown(i), new SpecificShipCell(loc.moveDown(i), new NotHit(), new NotSunkCell(), newShip));
        }
      } else if (newShip instanceof BattleShip) {
        for (int i = (loc.getX().getRowNum() - 2 < 0 ? 0 : loc.getX().getRowNum() - 2);
             i <= (loc.getX().getRowNum() + 3 > 9 ? 9 : loc.getX().getRowNum() + 3);
             i++) {
          for (int j = (loc.getY().getColumnNum() - 2 < 0 ? 0 : loc.getY().getColumnNum() - 2);
               j <= (loc.getY().getColumnNum() > 9 ? 9 : loc.getY().getColumnNum());
               j++) {
            map.setMap(new Location(Row.getRow(i + 1), Column.getColumn(j + 1)),
                new GapCell(new Location(Row.getRow(i + 1), Column.getColumn(j + 1)), new NotHit()));
          }
        }
        for (int i = 0; i <= 3; i++) {
          map.setMap(loc.moveDown(i), new SpecificShipCell(loc.moveDown(i), new NotHit(), new NotSunkCell(), newShip));
        }

      } else {
        ;
      }

    }
  }


  public static void main(String[] arg) {
    Computer player2 = new Computer();
    player2.randomPopulateFleetMap();
    player2.fleetMap.prettyPrinter(new ConsolePrinter());

    System.out.println(player2.fleetMap.toString());

  }
}
