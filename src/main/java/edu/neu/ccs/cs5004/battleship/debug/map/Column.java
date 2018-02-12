package edu.neu.ccs.cs5004.battleship.debug.map;

/**
 * Represents the column items.
 *
 * @author Shufan
 */

public enum Column {
  A(1),
  B(2),
  C(3),
  D(4),
  E(5),
  F(6),
  G(7),
  H(8),
  I(9),
  J(10);

  private int columnNum;

  /**
   * Create the column with given column value.
   *
   * @param columnNum the value of column item
   */

  Column(int columnNum) {
    this.columnNum = columnNum;
  }

  /**
   * Getter for the column value.
   *
   * @return the integer value of the column item
   */

  public int getColumnNum() {
    return columnNum;
  }

  /**
   * Get the enum column name by given the column number.
   *
   * @param index the column number in int
   * @return the column number with enum column
   */

  public static Column getColumn(int index) {
    for (Column col : Column.values()) {
      if (col.getColumnNum() == index) {
        return col;
      }
    }
    throw new IllegalArgumentException("index not found.");
  }

  @Override
  public String toString() {
    return "Column{"
        + "columnNum="
        + columnNum
        + "} "
        + super.toString();
  }
}
