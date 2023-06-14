import org.junit.Before;
import org.junit.Test;

import cs3500.set.model.hw02.SetThreeGameModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * All the tests for the SetThreeGameModel class are presented below.
 */
public class SetThreeGameModelTest extends SetGameModelAbstractTest {

  @Before
  public void init() {
    game = new SetThreeGameModel();
    super.init();

  }

  @Test
  public void testInvalidStartGameWithDeck() {

    super.testInvalidStartGameWithDeck();

    try {
      game.startGameWithDeck(game.getCompleteDeck(), 2, 3);
      fail("Game created with wrong height");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid game construction", e.getMessage());
    }

  }

  @Test
  public void testValidStartGameWithDeck() {


    game.startGameWithDeck(cardDeck2, 3, 3);

    assertEquals("Expected the following value: ", "1EQ 1EO 1ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD", view.toString());

  }

  @Test
  public void testValidClaimSet() {

    super.testValidClaimSet();

    assertEquals("2EQ 1EO 1ED\n" +
                    "1SQ 2EO 1SD\n" +
                    "1FQ 1FO 2ED",
            view.toString());

  }

  @Test
  public void testInvalidClaimSet() {

    super.testInvalidClaimSet();

    try {
      game.claimSet(coord1, coord4, coord5);
      fail("Invalid coordinates given, yet accepted coordinates");
    } catch (IllegalArgumentException e) {
      assertEquals("The coordinates entered are not a set.", e.getMessage());
    }

  }

  @Test
  public void testValidGetWidth() {
    game.startGameWithDeck(game.getCompleteDeck(), 3, 3);
    assertEquals("Expected the following value: ", 3, game.getWidth());

  }

  @Test
  public void testInvalidGetWidth() {

    try {
      game.getWidth();
      fail("Game returned width without game starting");
    } catch (IllegalStateException e) {
      assertEquals("The game has not been started.", e.getMessage());
    }

  }

  @Test
  public void testValidGetHeight() {
    game.startGameWithDeck(game.getCompleteDeck(), 3, 3);
    assertEquals("Expected the following value: ", 3, game.getHeight());

  }

  @Test
  public void testInvalidGetHeight() {

    try {
      game.getHeight();
      fail("Game returned height without game starting");
    } catch (IllegalStateException e) {
      assertEquals("The game has not been started.", e.getMessage());
    }

  }

  @Test
  public void testValidGetScore() {

    super.testValidGetScore();

    game.startGameWithDeck(cardDeck2, 3, 3);

    assertEquals("Expected the following value", 0,
            game.getScore());

    game.claimSet(coord1, coord2, coord3);

    assertEquals("Expected the following value", 1,
            game.getScore());

    game.claimSet(coord1, coord2, coord3);


  }

  @Test
  public void testInvalidGetScore() {

    try {
      game.getScore();
      fail("Game returned score without game starting");
    } catch (IllegalStateException e) {
      assertEquals("The game has not been started.", e.getMessage());
    }

  }

  @Test
  public void testInvalidAnySetsPresent() {

    try {
      game.anySetsPresent();
      fail("Game not started, yet still ran method");
    } catch (IllegalStateException e) {
      assertEquals("The game has not been started.", e.getMessage());
    }

  }

  @Test
  public void testValidAnySetsPresent() {

    super.testValidAnySetsPresent();

    assertEquals("Expected the following value", true,
            game.anySetsPresent());

  }

  @Test
  public void testValidIsValidSet() {

    game.startGameWithDeck(game.getCompleteDeck(), 3, 3);

    assertEquals("Expected the following value", true,
            game.isValidSet(coord1, coord2, coord3));
    assertEquals("Expected the following value", true,
            game.isValidSet(coord4, coord5, coord6));
    assertEquals("Expected the following value", false,
            game.isValidSet(coord4, coord2, coord1));
    assertEquals("Expected the following value", true,
            game.isValidSet(coord1, coord4, coord7));
    assertEquals("Expected the following value", true,
            game.isValidSet(coord2, coord5, coord8));

  }

  @Test
  public void testInvalidIsValidSet() {

    super.testInvalidIsValidSet();

    try {
      game.isValidSet(coordWrong3, coord2, coord3);
      fail("Game returned null coordinate");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid coordinates were given.", e.getMessage());
    }

  }

  @Test
  public void testValidGetCardAtCoord() {

    super.testValidGetCardAtCoord();

    assertEquals("Expected the following value", "2EO",
            game.getCardAtCoord(coord4).toString());


  }

  @Test
  public void testInvalidGetCardAtCoord() {

    super.testInvalidGetCardAtCoord();

    try {
      game.getCardAtCoord(coordWrong1);
      fail("Invalid coordinates given, yet still ran method");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid coordinate was provided.", e.getMessage());
    }

  }

  @Test
  public void testIsGameOver() {

    super.testIsGameOver();

    assertEquals("Expected the following value", true,
            game.isGameOver());


  }

  @Test
  public void testGetCompleteDeck() {

    assertEquals("Expected", cardDeck4.toString(), game.getCompleteDeck().toString());

  }


}
