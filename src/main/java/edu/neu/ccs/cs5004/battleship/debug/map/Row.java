package edu.neu.ccs.cs5004.battleship.debug.map;

/**
 * Represents the row number of a map.
 *
 * @author Shufan
 */

public enum Row {
  ONE(1),
  TWO(2),
  THREE(3),
  FOUR(4),
  FIVE(5),
  SIX(6),
  SEVEN(7),
  EIGHT(8),
  NINE(9),
  TEN(10);

  private int rowNum;

  /**
   * Create the Row with given row number.
   *
   * @param rowNum the row number of a map
   */

  Row(int rowNum) {
    this.rowNum = rowNum;
  }

  /**
   * Getter for the row number value.
   *
   * @return the value of a row
   */

  public int getRowNum() {
    return rowNum;
  }

  /**
   * Get the enum row name for a row number.
   *
   * @param index the integer row number
   * @return the enum row name corresponding to the give row number
   */
  public static Row getRow(int index) {
    for (Row row : Row.values()) {
      if (row.getRowNum() == index) {
        return row;
      }
    }
    throw new IllegalArgumentException("index not found.");
  }

  @Override
  public String toString() {
    return "Row{"
        + "row="
        + rowNum
        + "} "
        + super.toString();
  }
}
