package edu.neu.ccs.cs5004.battleship.game;


import edu.neu.ccs.cs5004.battleship.game.cell.*;
import edu.neu.ccs.cs5004.battleship.game.map.Column;
import edu.neu.ccs.cs5004.battleship.game.map.Map;
import edu.neu.ccs.cs5004.battleship.game.map.Row;

/**
 * Represents a printer which accept the printing information.
 *
 * @author Shufan
 */

public class ConsolePrinter {

  /**
   * Print the given map on the screen.
   *
   * @param map the map to be printed on the screen
   */

  public void toConsole(Map map) {
    for (int i = Row.ONE.getRowNum() - 1; i < Row.TEN.getRowNum(); i++) {
      String result = "";
      for (int j = Column.A.getColumnNum() - 1; j < Column.J.getColumnNum(); j++) {
        result = stringBuilder(result,
            map.getter(new Location(Row.getRow(i + 1), Column.getColumn(j + 1))));
      }
      System.out.println(result);
    }
  }

  /**
   * Set the printing method of a given cell on the map.
   * G: gap cell
   * O: open water cell
   * E: enemy ship cell
   * S: specific ship cell
   *
   * @param str  a basic string to be printed on the screen.
   * @param cell the cell on a map
   * @return the string representing different cell type
   */

  public String stringBuilder(String str, Cell cell) {
    StringBuilder strBuilder = new StringBuilder(str);
    if (cell instanceof GapCell) {
      strBuilder = strBuilder.append(" G");
    } else if (cell instanceof OpenSeaCell) {
      strBuilder = strBuilder.append(" O");
    } else if (cell instanceof EnemyShipCell) {
      strBuilder = strBuilder.append(" E");
    } else {
      strBuilder = strBuilder.append(" S");
    }
    return strBuilder.toString();
  }

  @Override
  public String toString() {
    return "ConsolePrinter{}";
  }
}
