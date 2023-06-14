package cs3500.set.model.hw02;

/**
 * This class creates a Card object that consists of a count, filling,
 *      and shape for the game Set.
 */
public class Card {

  private Count count;
  private Filling filling;

  Shape shape;

  /**
   * This constructor returns a card with the parameters: count, filling, and shape.
   * @param count a type of Count that is one, two, or three
   * @param filling a type of filling that is striped, empty, or full
   * @param shape a type of shape that is an oval, a squiggle, or a diamond
   */

  public Card(Count count, Filling filling, Shape shape) {

    if (count == null || filling == null || shape == null) {
      throw new IllegalArgumentException("Invalid card construction");
    }
    this.count = count;
    this.filling = filling;
    this.shape = shape;
  }

  /**
   * This method provides a string version of a card.
   * @return the string version of a card with more concise notation
   *        (i.e. 1ED to represent one, empty, diamond)
   */
  public String toString() {
    return "" + this.count.getCountValue() +
            this.filling.getFillingValue() + this.shape.getShapeValue();
  }

  /**
   * This getter method provides the count of a card.
   * @return the Count object
   */

  public Count getCount() {
    return this.count;
  }

  /**
   * This getter method provides the filling of a card.
   * @return the Filling object
   */
  public Filling getFilling() {
    return this.filling;
  }

  /**
   * This getter method provides the shape of a card.
   * @return the Shape object
   */
  public Shape getShape() {

    return this.shape;
  }


}
