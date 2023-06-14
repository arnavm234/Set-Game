package cs3500.set.model.hw02;

/**
 * This enum creates a Filling object that represents the filling of a card as
 *        empty, striped, or full.
 */

public enum Filling {

  EMPTY("E"),
  STRIPED("S"),
  FULL("F");

  private final String filling;

  Filling(String filling) {

    this.filling = filling;
  }

  public String getFillingValue() {
    return this.filling;
  }


}
