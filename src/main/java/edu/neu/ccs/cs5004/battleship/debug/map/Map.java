package edu.neu.ccs.cs5004.battleship.debug.map;


import edu.neu.ccs.cs5004.battleship.debug.ConsolePrinter;
import edu.neu.ccs.cs5004.battleship.debug.cell.Cell;
import edu.neu.ccs.cs5004.battleship.debug.cell.Location;
import edu.neu.ccs.cs5004.battleship.debug.cell.NotHit;
import edu.neu.ccs.cs5004.battleship.debug.cell.OpenSeaCell;

/**
 * Represents a map for the game.
 *
 * @author Shufan
 */

public class Map implements IMap {

  private Cell[][] cells = new Cell[Row.TEN.getRowNum()][Column.J.getColumnNum()];

  /**
   * Create the Map given the cells information.
   *
   * @param cells all cells on a 2D map
   */
  public Map(Cell[][] cells) {
    this.cells = cells;
  }

  /**
   * Initialize the Cells array of a Map to (not hit) water cell.
   */

  public Map() {
    for (int i = Row.ONE.getRowNum() - 1; i < Row.TEN.getRowNum(); i++) {
      for (int j = Column.A.getColumnNum() - 1; j < Column.J.getColumnNum(); j++) {
        cells[i][j] = new OpenSeaCell(new Location(Row.getRow(i + 1),
            Column.getColumn(j + 1)), new NotHit());
      }
    }
  }

  /**
   * Getter for cells in the map.
   *
   * @return the cells array
   */
  public Cell[][] getCells() {
    return this.cells;
  }

  /**
   * Find the corresponding cell information, given the location of a cell.
   *
   * @param loc the location of a cell
   * @return the corresponding cell information on the given location
   * @throws IllegalArgumentException when the given loc is not on the map
   */
  public Cell getter(Location loc) throws IllegalArgumentException {
    for (int i = Row.ONE.getRowNum() - 1; i < Row.TEN.getRowNum(); i++) {
      for (int j = Column.A.getColumnNum() - 1; j < Column.J.getColumnNum(); j++) {
        if ((cells[i][j].getLocation()).equals(loc)) {
          return cells[i][j];
        }
      }
    }
    throw new IllegalArgumentException("The given location is not valid for the map.");
  }

  /**
   * Set the cell on a given location of map to a new status.
   *
   * @param loc     the location of a cell on the map
   * @param newCell the cell with new settings
   * @throws IllegalArgumentException When the given location is
   *                                  out of map boundary or the location is not
   *                                  same to the location in the cell
   */

  public void setMap(Location loc, Cell newCell) throws IllegalArgumentException {
    if (loc.equals(newCell.getLocation())) {
      cells[loc.getX().getRowNum() - 1][loc.getY().getColumnNum() - 1] = newCell;
    } else {
      throw new IllegalArgumentException("The given cell is not "
          + "the same cell to be set");
    }
  }

  /**
   * Print the map.
   *
   * @param printer the printer handling the information of the instance map
   */
  public void prettyPrinter(ConsolePrinter printer) {
    printer.toConsole(this);
  }

  private boolean sameArray(Cell[][] array1, Cell[][] array2) {
    if (array1.length != array2.length) {
      return false;
    }

    for (int i = 0; i < array1.length; i++) {
      if (array1[i].length != array2[i].length) {
        return false;
      }
      for (int j = 0; j < array1[i].length; j++) {
        if (!array1[i][j].equals(array2[i][j])) {
          return false;
        }
      }
    }

    return true;
  }


  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    Map map = (Map) object;
    return this.sameArray(cells, map.cells);
  }

  @Override
  public int hashCode() {
    int hashCode = 0;
    for (int i = 0; i < cells.length; i++) {
      for (int j = 0; j < cells[i].length; j++) {
        hashCode += this.cells[i][j].hashCode();
      }
    }
    return hashCode;
  }

  private String stringBuilder(String str, Map map) {
    Cell[][] cells = map.getCells();

    StringBuilder strBuilder = new StringBuilder(str);
    for (int i = 0; i < cells.length; i++) {
      for (int j = 0; j < cells[i].length; j++) {
        strBuilder = strBuilder.append(cells[i][j].toString());
      }
    }
    return strBuilder.toString();
  }


  @Override
  public String toString() {
    return this.stringBuilder("Map is : \n", this);

  }
}
