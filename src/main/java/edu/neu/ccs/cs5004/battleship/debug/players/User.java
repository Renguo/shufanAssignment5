package edu.neu.ccs.cs5004.battleship.debug.players;


import edu.neu.ccs.cs5004.battleship.debug.*;
import edu.neu.ccs.cs5004.battleship.debug.cell.*;
import edu.neu.ccs.cs5004.battleship.debug.map.Column;
import edu.neu.ccs.cs5004.battleship.debug.map.Map;
import edu.neu.ccs.cs5004.battleship.debug.map.Row;
import edu.neu.ccs.cs5004.battleship.debug.ship.*;

import java.io.IOException;

public class User {
  private Map fleetMap;
  private Integer shipNum;
  private Integer TOTALSHIPNUM = 5;

  private User(Map fleetMap, Integer shipNum) {
    this.fleetMap = fleetMap;
    this.shipNum = shipNum;
  }

  public User() {
    this(new Map(), 0);
  }

  public void setShipNum(Integer shipNum) {
    this.shipNum = shipNum;
  }

  public Ship chooseShip() {
    Ship newShip;

    try {
      newShip = Ship.createShip(Direction.getDirection(
          ConsoleInput.readConsoleShipDirection()),
          Integer.valueOf(ConsoleInput.readConsoleShipType()));
      return newShip;
    } catch (IOException e) {
      throw new RuntimeException("No Proper Ship was built");
    } catch (IllegalArgumentException e) {

      return chooseShip();
    }
  }

  public Location chooseLocation() {
    try {
      return ConsoleInput.readConsoleShipLocation();
    } catch (IOException e) {
      System.out.println("The input of Ship location is not valid");
      return this.chooseLocation();
    } catch (IllegalArgumentException e) {
      System.out.println("The input of Ship location is not valid");
      return this.chooseLocation();
    }
  }


  public void populateFleetMap() {

    System.out.println(this.shipNum + "************");
    while (this.shipNum < TOTALSHIPNUM) {
      Location newLoc = this.chooseLocation();
      Ship newShip = this.chooseShip();
      putShipToFleetMap(newLoc, newShip);
      updateMap(this.fleetMap, newLoc, newShip);
      this.fleetMap.prettyPrinter(new ConsolePrinter());

    }

  }


  public void putShipToFleetMap(Location loc, Ship newShip) {

    if (!this.fleetMap.getter(loc).getHoldShip()) {
      System.out.println("This cell can not hold ship.");
      ;
    } else if (User.cellIsRightForShip(this.fleetMap, loc, newShip)) {
      this.fleetMap.setMap(loc, new SpecificShipCell(loc, new NotHit(), new NotSunkCell(), newShip));
      updateMap(this.fleetMap, loc, newShip);
      this.setShipNum(this.shipNum + 1);
    } else {
      System.out.println("This cell can not hold ship.");
    }
  }


  public static boolean cellIsRightForShip(Map map, Location loc, Ship newShip) {
    if (newShip.getDirection().equals(edu.neu.ccs.cs5004.battleship.game.ship.Direction.Horizontal)) {
      try {
        if (newShip instanceof edu.neu.ccs.cs5004.battleship.game.ship.Destroyer) {
          return true;
        } else if ((newShip instanceof edu.neu.ccs.cs5004.battleship.game.ship.Submarine) &&
            (map.getter(loc.moveRight(1)) instanceof edu.neu.ccs.cs5004.battleship.game.cell.OpenSeaCell)) {
          return true;
        } else if ((newShip instanceof edu.neu.ccs.cs5004.battleship.game.ship.Cruiser) &&
            (map.getter(loc.moveRight(2)) instanceof edu.neu.ccs.cs5004.battleship.game.cell.OpenSeaCell)) {
          return true;

        } else if ((newShip instanceof edu.neu.ccs.cs5004.battleship.game.ship.BattleShip) &&
            (map.getter(loc.moveRight(3)) instanceof edu.neu.ccs.cs5004.battleship.game.cell.OpenSeaCell)) {
          return true;

        } else {
          return false;
        }
      } catch (IllegalArgumentException e) {
        return false;
      }
    } else {
      try {
        if (newShip instanceof edu.neu.ccs.cs5004.battleship.game.ship.Destroyer) {
          return true;
        } else if ((newShip instanceof edu.neu.ccs.cs5004.battleship.game.ship.Submarine) &&
            (map.getter(loc.moveDown(1)) instanceof edu.neu.ccs.cs5004.battleship.game.cell.OpenSeaCell)) {
          return true;
        } else if ((newShip instanceof edu.neu.ccs.cs5004.battleship.game.ship.Cruiser) &&
            (map.getter(loc.moveDown(2)) instanceof edu.neu.ccs.cs5004.battleship.game.cell.OpenSeaCell)) {
          return true;

        } else if ((newShip instanceof edu.neu.ccs.cs5004.battleship.game.ship.BattleShip) &&
            (map.getter(loc.moveDown(3)) instanceof edu.neu.ccs.cs5004.battleship.game.cell.OpenSeaCell)) {
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
    User player1 = new User();
    player1.populateFleetMap();

    System.out.println(player1.fleetMap.toString());

  }

}


