import org.junit.Before;
import org.junit.Test;

import cs3500.set.model.hw02.Card;
import cs3500.set.model.hw02.Count;
import cs3500.set.model.hw02.Filling;
import cs3500.set.model.hw02.Shape;

import static org.junit.Assert.assertEquals;

/**
 * All the tests for the Card class are presented below.
 */
public class CardTest {

  Card c1;
  Card c2;
  Card c3;

  @Before
  public void init() {
    c1 = new Card(Count.ONE, Filling.STRIPED, Shape.DIAMOND);
    c2 = new Card(Count.TWO, Filling.EMPTY, Shape.SQUIGGLE);
    c3 = new Card(Count.THREE, Filling.FULL, Shape.OVAL);


  }

  @Test
  public void testValidCardConstruction() {

    assertEquals("Expected the following value", "1SD", this.c1.toString());
    assertEquals("Expected the following value", "2EQ", this.c2.toString());
    assertEquals("Expected the following value", "3FO", this.c3.toString());

    assertEquals("Expected the following value", Count.ONE, this.c1.getCount());
    assertEquals("Expected the following value", Filling.STRIPED, this.c1.getFilling());
    assertEquals("Expected the following value", Shape.DIAMOND, this.c1.getShape());

    assertEquals("Expected the following value", "1",
            this.c1.getCount().getCountValue());
    assertEquals("Expected the following value", "S",
            this.c1.getFilling().getFillingValue());
    assertEquals("Expected the following value", "D",
            this.c1.getShape().getShapeValue());

  }


}
