package edu.neu.ccs.cs5004.battleship.debug;


import edu.neu.ccs.cs5004.battleship.debug.cell.Location;
import edu.neu.ccs.cs5004.battleship.debug.map.Column;
import edu.neu.ccs.cs5004.battleship.debug.map.Row;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInput {
  public static String readConsoleShipType() throws IOException {
    BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));
    System.out.print(
        "Enter the Ship type (1 for Destroyer\n 2 for Submarine\n 3 for Cruiser\n 4 for BattleShip): ");

    String shipType = bufReader.readLine();

    return shipType;
  }

  public static String readConsoleShipDirection() throws IOException {
    BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));
    System.out.print(
        "Enter the Ship direction ( Horizontal or Vertical): ");
    String shipDirection = bufReader.readLine();

    return shipDirection;
  }

  public static Location readConsoleShipLocation() throws IOException {
    BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));
    System.out.print(
        "Enter the Ship Location ( Row and Column): ");
    System.out.print(
        "Enter Row number (1, 2, 3, 4, 5, 6, 7, 8, 9 or 10) ");
    String rowNum = bufReader.readLine();
    Row row = Row.getRow(Integer.valueOf(rowNum));

    System.out.print(
        "Enter Column number (1, 2, 3, 4, 5, 6, 7, 8, 9 or 10) ");
    String columnNum = bufReader.readLine();
    Column column = Column.getColumn(Integer.valueOf(columnNum));

    return new Location(row, column);

  }
}
