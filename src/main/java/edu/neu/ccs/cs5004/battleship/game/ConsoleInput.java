package edu.neu.ccs.cs5004.battleship.game;


import edu.neu.ccs.cs5004.battleship.game.cell.Location;
import edu.neu.ccs.cs5004.battleship.game.map.Column;
import edu.neu.ccs.cs5004.battleship.game.map.Row;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInput {
  public static String readConsoleShipType() throws IOException, IllegalArgumentException {
    BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));
    String shipType;
    try {
      System.out.print(
          "Enter the Ship type (1 for Destroyer\n 2 for Submarine\n 3 for Cruiser\n 4 for BattleShip): ");

      shipType = bufReader.readLine();
    } catch (IllegalArgumentException e) {
      System.out.print(
          "Enter the Ship type (1 for Destroyer\n 2 for Submarine\n 3 for Cruiser\n 4 for BattleShip): ");
      shipType = bufReader.readLine();

    }

    return shipType;
  }

  public static String readConsoleShipDirection() throws IOException, IllegalArgumentException {
    String shipDirection;
    BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));
    try {
      System.out.print(
          "Enter the Ship direction ( Horizontal or Vertical): ");
      shipDirection = bufReader.readLine();
    } catch (IOException e) {
      System.out.println("Please enter valid ship direction ( Horizontal or Vertical)");
      shipDirection = bufReader.readLine();
    } catch (IllegalArgumentException e) {
      System.out.println("Please enter valid ship direction ( Horizontal or Vertical)");
      shipDirection = bufReader.readLine();
    }


    return shipDirection;
  }

  public static Location readConsoleShipLocation() throws IOException, IllegalArgumentException {
    BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));
    Row row;
    Column column;
    try {
      System.out.print(
          "Enter the Ship Location ( Row and Column): ");
      System.out.print(
          "Enter Row number (1, 2, 3, 4, 5, 6, 7, 8, 9 or 10) ");
      String rowNum = bufReader.readLine();
      row = Row.getRow(Integer.valueOf(rowNum));
    } catch (IOException e) {

      System.out.print(
          "please enter the row number again: ");
      System.out.print(
          "Enter Row number (1, 2, 3, 4, 5, 6, 7, 8, 9 or 10) ");
      String rowNum = bufReader.readLine();
      row = Row.getRow(Integer.valueOf(rowNum));

    } catch (IllegalArgumentException e) {
      System.out.print(
          "please enter the row number again: ");
      System.out.print(
          "Enter Row number (1, 2, 3, 4, 5, 6, 7, 8, 9 or 10) ");
      String rowNum = bufReader.readLine();
      row = Row.getRow(Integer.valueOf(rowNum));

    }


    try {
      System.out.print(
          "Enter Column number (1, 2, 3, 4, 5, 6, 7, 8, 9 or 10) ");
      String columnNum = bufReader.readLine();
      column = Column.getColumn(Integer.valueOf(columnNum));
    } catch (IOException e) {
      System.out.println("This location is invalid, please enter the Column number again");
      String columnNum = bufReader.readLine();
      column = Column.getColumn(Integer.valueOf(columnNum));

    } catch (IllegalArgumentException e) {
      System.out.println("This location is invalid, please enter the Column number again");
      System.out.print(
          "Enter Column number (1, 2, 3, 4, 5, 6, 7, 8, 9 or 10) ");
      String columnNum = bufReader.readLine();
      column = Column.getColumn(Integer.valueOf(columnNum));

    }


    return new Location(row, column);

  }


}
