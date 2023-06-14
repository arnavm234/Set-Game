import org.junit.Before;
import org.junit.Test;

import cs3500.set.model.hw02.Coord;
import cs3500.set.model.hw03.GeneralSetGameModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * All the tests for the GeneralSetGameModelTest class are presented below.
 */
public class GeneralSetGameModelTest extends SetGameModelAbstractTest {

  @Before
  public void init() {

    game = new GeneralSetGameModel();
    super.init();

  }

  @Test
  public void testInvalidStartGameWithDeck() {
    super.testInvalidStartGameWithDeck();

    try {
      game.startGameWithDeck(cardDeck3, 3, 3);
      fail("Null deck of cards was given.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid game construction", e.getMessage());
    }

  }

  @Test
  public void testValidStartGameWithDeck() {

    game.startGameWithDeck(this.game.getCompleteDeck(), 4, 4);

    assertEquals("1EQ 1EO 1ED 1SQ\n" +
            "1SO 1SD 1FQ 1FO\n" +
            "1FD 2EQ 2EO 2ED\n" +
            "2SQ 2SO 2SD 2FQ", view.toString());

    game.startGameWithDeck(this.game.getCompleteDeck(), 1, 3);

    assertEquals("1EQ 1EO 1ED", view.toString());

    game.startGameWithDeck(this.game.getCompleteDeck(), 2, 4);

    assertEquals("1EQ 1EO 1ED 1SQ\n" +
            "1SO 1SD 1FQ 1FO", view.toString());

    game.startGameWithDeck(this.game.getCompleteDeck(), 4, 1);

    assertEquals("1EQ\n" +
            "1EO\n" +
            "1ED\n" +
            "1SQ", view.toString());

    game.startGameWithDeck(this.game.getCompleteDeck(), 9, 3);

    assertEquals("1EQ 1EO 1ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "2EQ 2EO 2ED\n" +
            "2SQ 2SO 2SD\n" +
            "2FQ 2FO 2FD\n" +
            "3EQ 3EO 3ED\n" +
            "3SQ 3SO 3SD\n" +
            "3FQ 3FO 3FD", view.toString());





  }

  @Test
  public void testValidStartGameWithNoSetsPresent() {

    game.startGameWithDeck(cardDeck5, 1, 3);

    assertEquals("1EQ 1FO 1FD\n" +
            "2FD 3EQ 3FQ", view.toString());

  }

  @Test
  public void testValidGetWidth() {
    game.startGameWithDeck(game.getCompleteDeck(), 2, 2);
    assertEquals("Expected the following value: ", 2, game.getWidth());

    game.startGameWithDeck(game.getCompleteDeck(), 1, 27);
    assertEquals("Expected the following value: ", 27, game.getWidth());

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
    game.startGameWithDeck(game.getCompleteDeck(), 4, 2);
    assertEquals("Expected the following value: ", 4, game.getHeight());

    game.startGameWithDeck(game.getCompleteDeck(), 10, 2);
    assertEquals("Expected the following value: ", 10, game.getHeight());

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
  public void testInvalidGetCardAtCoord() {

    super.testInvalidGetCardAtCoord();

    try {
      game.getCardAtCoord(coordWrong1);
      fail("Invalid coordinates given, yet still ran method");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid coordinate was provided.", e.getMessage());
    }

    game.startGameWithDeck(game.getCompleteDeck(), 4, 5);

    try {
      game.getCardAtCoord(coordWrong1);
      fail("Invalid coordinates given, yet still ran method");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid coordinate was provided.", e.getMessage());
    }



  }

  @Test
  public void testValidGetCardAtCoord() {
    super.testValidGetCardAtCoord();

    game.startGameWithDeck(game.getCompleteDeck(), 4, 5);

    assertEquals("Expected the following value", "3EO",
            game.getCardAtCoord(3,4).toString());
    assertEquals("Expected the following value", "2SO",
            game.getCardAtCoord(2,3).toString());
    assertEquals("Expected the following value", "2EQ",
            game.getCardAtCoord(1,4).toString());


  }

  @Test
  public void testIsValid() {
    game.startGameWithDeck(this.game.getCompleteDeck(), 3, 3);

    try {
      game.isValidSet(coordWrong1, coord1, coord2);
      fail("Game returned coordinate not in range");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid coordinates were given.", e.getMessage());
    }

    try {
      game.isValidSet(coord1, coordWrong1, coord2);
      fail("Game returned coordinate not in range");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid coordinates were given.", e.getMessage());
    }

    try {
      game.isValidSet(coordWrong3, coordWrong1, coordWrong2);
      fail("Game returned coordinate not in range");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid coordinates were given.", e.getMessage());
    }

    try {
      game.isValidSet(coordWrong3, coordWrong2, coordWrong2);
      fail("Game returned coordinate not in range");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid coordinates were given.", e.getMessage());
    }

    try {
      game.isValidSet(coord7, coord1, coordWrong2);
      fail("Game returned coordinate not in range");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid coordinates were given.", e.getMessage());
    }

    try {
      game.isValidSet(coord4, coordWrong3, coord2);
      fail("Game returned null coordinate");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid coordinates were given.", e.getMessage());
    }

    try {
      game.isValidSet(coord4, coord2, coordWrong3);
      fail("Game returned null coordinate");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid coordinates were given.", e.getMessage());
    }

    try {
      game.isValidSet(coordWrong3, coord2, coord3);
      fail("Game returned null coordinate");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid coordinates were given.", e.getMessage());
    }
  }


  @Test
  public void testGetCompleteDeck() {

    assertEquals("Expected", cardDeck4.toString(), game.getCompleteDeck().toString());

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

    game.startGameWithDeck(this.game.getCompleteDeck(), 4, 5);

    try {
      game.isValidSet(new Coord(4,5), coord2, coord3);
      fail("Game returned null coordinate");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid coordinates were given.", e.getMessage());
    }

    try {
      game.isValidSet(coord1, new Coord(5,6), coord3);
      fail("Game returned null coordinate");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid coordinates were given.", e.getMessage());
    }

    try {
      game.isValidSet(coord1, coord2, new Coord(5,6));
      fail("Game returned null coordinate");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid coordinates were given.", e.getMessage());
    }

  }

  @Test
  public void testValidGetScore() {

    super.testValidGetScore();

    game.startGameWithDeck(cardDeck2, 1, 4);

    assertEquals("Expected the following value", 0,
            game.getScore());

    game.claimSet(coord1, coord2, coord3);

    assertEquals("Expected the following value", 1,
            game.getScore());

    game.claimSet(coord1, coord2, new Coord(0,3));

    assertEquals("Expected the following value", 2,
            game.getScore());


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
  public void testIsGameOver() {

    super.testIsGameOver();

    assertEquals("Expected the following value", true,
            game.isGameOver());

    init();

    game.startGameWithDeck(cardDeck2, 1, 4);

    assertEquals("Expected the following value", false,
            game.isGameOver());

    game.claimSet(coord1, coord2, coord3);

    assertEquals("Expected the following value", false,
            game.isGameOver());

    game.claimSet(coord1, coord2, new Coord(0,3));

    assertEquals("Expected the following value", true,
            game.isGameOver());

    game.startGameWithDeck(cardDeck6, 1, 3);

    assertEquals("1EQ 1FO 1FD\n" +
            "2FD 3EQ 3FQ", view.toString());


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
  public void testValidClaimSet() {

    super.testValidClaimSet();

    assertEquals("2EQ 1EO 1ED\n" +
                    "1SQ 2EO 1SD\n" +
                    "1FQ 1FO 2ED",
            view.toString());

    game.startGameWithDeck(this.game.getCompleteDeck(), 4 ,4);

    assertEquals("1EQ 1EO 1ED 1SQ\n" +
                    "1SO 1SD 1FQ 1FO\n" +
                    "1FD 2EQ 2EO 2ED\n" +
                    "2SQ 2SO 2SD 2FQ",
            view.toString());

    game.claimSet(new Coord(0,3), new Coord(1,3), new Coord(0,2));

    assertEquals("1EQ 1EO 3EQ 2FO\n" +
                    "1SO 1SD 1FQ 2FD\n" +
                    "1FD 2EQ 2EO 2ED\n" +
                    "2SQ 2SO 2SD 2FQ",
            view.toString());

    game.startGameWithDeck(cardDeck6, 1 ,3);

    // this checks if a row is added when a set is claimed and no sets available.
    assertEquals("1SQ 1SO 1SD",
            view.toString());

    game.claimSet(coord1, coord2, coord3);

    assertEquals("1EQ 1FO 1FD\n" +
                    "2FD 3EQ 3FQ",
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



}
