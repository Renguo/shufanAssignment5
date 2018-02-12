package edu.neu.ccs.cs5004.battleship.game.customerplayer;


import edu.neu.ccs.cs5004.battleship.game.ConsolePrinter;
import edu.neu.ccs.cs5004.battleship.game.ConsoleInput;
import edu.neu.ccs.cs5004.battleship.game.cell.*;
import edu.neu.ccs.cs5004.battleship.game.map.Column;
import edu.neu.ccs.cs5004.battleship.game.map.Map;
import edu.neu.ccs.cs5004.battleship.game.map.Row;
import edu.neu.ccs.cs5004.battleship.game.ship.*;


import java.io.IOException;

public class User {
  private static final Ship[] fleet = {new BattleShip(), new Cruiser(),
      new Cruiser(), new Submarine(), new Submarine(),
      new Submarine(), new Destroyer(), new Destroyer(), new Destroyer(), new Destroyer()};

  private Integer TOTALSHIPNUM = fleet.length;
  private Map fleetMap;
  private Integer shipNum;

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

  public void setShipDirection(int index) {


    try {
      this.fleet[index].setDirection(Direction.getDirection(ConsoleInput.readConsoleShipDirection()));
    } catch (IOException e) {
      System.out.println("Please enter the ship direction again");
      this.setShipDirection(index);
    } catch (IllegalArgumentException e) {
      this.setShipDirection(index);
    }

  }

  public Location chooseLocation() {
    try {
      return ConsoleInput.readConsoleShipLocation();
    } catch (IOException e) {
      System.out.println("The input of Ship location is not valid");
      return chooseLocation();
    }
  }


  public void populateFleetMap() {


    int index = 0;
    while (index < 10) {
      Location newLoc;
      this.setShipDirection(index);
      Ship newShip = this.fleet[index];
      newLoc = this.chooseLocation();


      while (this.cellIsRightForShip(this.fleetMap, newLoc, newShip) && this.fleetMap.getter(newLoc).getHoldShip()) {

        this.putShipToFleetMap(newLoc, newShip);
        updateMap(this.fleetMap, newLoc, newShip);
        index++;
        this.fleetMap.prettyPrinter(new ConsolePrinter());
      }


    }
  }


  public void putShipToFleetMap(Location loc, Ship newShip) {

    if (!this.fleetMap.getter(loc).getHoldShip()) {
      System.out.println("This cell can not hold ship.");
      ;
    } else if (this.cellIsRightForShip(this.fleetMap, loc, newShip)) {
      this.fleetMap.setMap(loc, new SpecificShipCell(loc, new NotHit(), new NotSunkCell(), newShip));

      this.setShipNum(this.shipNum + 1);
    } else {
      System.out.println("This cell can not hold ship.");
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
    User player1 = new User();
    player1.populateFleetMap();

    System.out.println(player1.fleetMap.toString());

  }

}


