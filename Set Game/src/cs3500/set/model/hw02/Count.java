package cs3500.set.model.hw02;

/**
 * This enum creates a Count object that represents the count of a card as
 *        one, two, or three.
 */
public enum Count {

  ONE("1"),
  TWO("2"),
  THREE("3");

  private final String count;

  Count(String count) {

    this.count = count;
  }


  public String getCountValue() {
    return this.count;
  }
}
