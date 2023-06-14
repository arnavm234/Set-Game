package cs3500.set.model.hw02;
/**
 * This enum creates a Shape object that represents the shape of a card as
 *        a squiggle, oval, or diamond.
 */

public enum Shape {

  SQUIGGLE("Q"),
  OVAL("O"),
  DIAMOND("D");

  private final String shape;

  Shape(String shape) {

    this.shape = shape;
  }

  public String getShapeValue() {

    return this.shape;
  }


}

