package edu.neu.ccs.cs5004.battleship.debug.cell;


/**
 * Represents an Enemyshipcell with a none explicit enemy ship.
 *
 * @author Shufan Xing
 */

public class EnemyShipCell extends ShipCell {

  public EnemyShipCell(Location location, Hitable hit,
                       Sunkable sunk) {
    super(location, hit, sunk);
  }

  /**
   * Check if the cell belong to a sunken ship.
   *
   * @return "This cell does not belong to a sunken ship." or "This cell belongs to a sunken ship."
   */
  public String belongToSunkShipOrNot() {
    if (this.sunk.equals(new NotSunkCell())) {
      return "This cell does not belong to a sunken ship.";
    } else {
      return "This cell belongs to a sunken ship.";
    }
  }

  @Override
  public String getAttackResult() {
    if (this.sunk.equals(new SunkCell())) {
      return (new Sunk()).attackResult();
    } else {
      return (new Hit()).attackResult();
    }

  }

  @Override
  protected Cell absCellFactory(Location location, Hitable hit) {
    return new EnemyShipCell(location, hit, this.sunk);
  }

  @Override
  public ShipCell shipCellFactory(Sunkable sunk) {
    return new EnemyShipCell(this.location, new IsHit(), sunk);
  }

  @Override
  public boolean equals(Object object) {
    return super.equals(object);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public String toString() {
    return "EnemyShipCell{"
        + "sunk="
        + sunk.getSunk()
        + ", location="
        + location
        + ", hit="
        + hit.getHitOrNot()
        + "}";
  }

  /**
   * Nothing was hit in this cell.
   *
   * @author Shufan Xing
   */

  public static class Miss implements AttackResult {
    @Override
    public String attackResult() {
      return "Miss";
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
        return true;
      }
      if (object == null || getClass() != object.getClass()) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      return 5;
    }
  }

  /**
   * Reprsent the sunk status of a ship's attack results.
   *
   * @author Shufan
   */

  public static class Sunk implements AttackResult {
    @Override
    public String attackResult() {
      return "Sunk";
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
        return true;
      }
      if (object == null || getClass() != object.getClass()) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      return 17;
    }
  }

  /**
   * Represents the attack result of a cell.
   * The result can be "Miss","Hit" or "Sunk".
   *
   * @author Shufan Xing
   */

  public static interface AttackResult {
    /**
     * Show the information of an attack result.
     *
     * @return the attack result of a cell
     */
    String attackResult();
  }

  /**
   * Represents the attack result Hit.
   *
   * @author Shufan Xing
   */

  public static class Hit implements AttackResult {
    @Override
    public String attackResult() {
      return "Hit";
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
        return true;
      }
      if (object == null || getClass() != object.getClass()) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      return 7;
    }
  }
}
