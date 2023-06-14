import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import cs3500.set.model.hw02.Card;
import cs3500.set.model.hw02.Coord;
import cs3500.set.model.hw02.Count;
import cs3500.set.model.hw02.Filling;
import cs3500.set.model.hw02.SetGameModel;
import cs3500.set.model.hw02.Shape;
import cs3500.set.view.SetGameTextView;
import cs3500.set.view.SetGameView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


/**
 * This class acts as an abstract tester class that supports the GeneralSetGameModelTest
 * as well as the SetThreeGameModelTest class.
 */
public class SetGameModelAbstractTest {

  /**
   * All of these objects are protected, so they can only be used by the other subclasses. The
   * subclasses and this abstract class are the only classes that use these objects so that is why
   * they are protected. The methods are also protected, so they can only be used by the subclasses
   * and here (the actual testing happens in the subclasses which are public, so it works out).
   */

  protected SetGameModel<Card> game;
  protected List<Card> cardDeck1;
  protected List<Card> cardDeck2;
  protected List<Card> cardDeck2Duplicate;
  protected List<Card> cardDeck3;
  protected List<Card> cardDeck4;
  protected List<Card> cardDeck5;
  protected List<Card> cardDeck6;
  protected List<Card> cardDeckFinished;
  protected Coord coord1;
  protected Coord coord2;
  protected Coord coord3;
  protected Coord coord4;
  protected Coord coord5;
  protected Coord coord6;
  protected Coord coord7;
  protected Coord coord8;
  protected Coord coord9;
  protected Coord coordWrong1;
  protected Coord coordWrong2;
  protected Coord coordWrong3;

  protected Card card1;
  protected Card card2;
  protected Card card3;
  protected Card card4;
  protected Card card5;
  protected Card card6;
  protected Card card7;
  protected Card card8;
  protected Card card9;
  protected Card card10;
  protected Card card11;
  protected Card card12;
  protected Card card13;
  protected Card card14;
  protected Card card15;
  protected Card card16;
  protected Card card17;
  protected Card card18;
  protected Card card19;
  protected Card card20;
  protected Card card21;
  protected Card card22;
  protected Card card23;
  protected Card card24;
  protected Card card25;
  protected Card card26;
  protected Card card27;

  protected SetGameView view;


  protected void init() {

    view = new SetGameTextView(game);

    cardDeck1 = new ArrayList<>();

    for (int i = 0; i < 8; i++) {
      cardDeck1.add(game.getCompleteDeck().get(i));
    }

    cardDeck2 = new ArrayList<>();

    for (int i = 0; i < 9; i++) {
      cardDeck2.add(game.getCompleteDeck().get(i));
    }

    cardDeck2Duplicate = new ArrayList<>();

    for (int i = 0; i < 9; i++) {
      cardDeck2Duplicate.add(game.getCompleteDeck().get(i));
    }


    coord1 = new Coord(0, 0);
    coord2 = new Coord(0, 1);
    coord3 = new Coord(0, 2);
    coord4 = new Coord(1, 0);
    coord5 = new Coord(1, 1);
    coord6 = new Coord(1, 2);
    coord7 = new Coord(2, 0);
    coord8 = new Coord(2, 1);
    coord9 = new Coord(2, 2);
    coordWrong1 = new Coord(4, 1);
    coordWrong2 = new Coord(1, -1);

    card1 = new Card(Count.ONE, Filling.EMPTY, Shape.SQUIGGLE);
    card2 = new Card(Count.ONE, Filling.EMPTY, Shape.OVAL);
    card3 = new Card(Count.ONE, Filling.EMPTY, Shape.DIAMOND);
    card4 = new Card(Count.ONE, Filling.STRIPED, Shape.SQUIGGLE);
    card5 = new Card(Count.ONE, Filling.STRIPED, Shape.OVAL);
    card6 = new Card(Count.ONE, Filling.STRIPED, Shape.DIAMOND);
    card7 = new Card(Count.ONE, Filling.FULL, Shape.SQUIGGLE);
    card8 = new Card(Count.ONE, Filling.FULL, Shape.OVAL);
    card9 = new Card(Count.ONE, Filling.FULL, Shape.DIAMOND);
    card10 = new Card(Count.TWO, Filling.EMPTY, Shape.SQUIGGLE);
    card11 = new Card(Count.TWO, Filling.EMPTY, Shape.OVAL);
    card12 = new Card(Count.TWO, Filling.EMPTY, Shape.DIAMOND);
    card13 = new Card(Count.TWO, Filling.STRIPED, Shape.SQUIGGLE);
    card14 = new Card(Count.TWO, Filling.STRIPED, Shape.OVAL);
    card15 = new Card(Count.TWO, Filling.STRIPED, Shape.DIAMOND);
    card16 = new Card(Count.TWO, Filling.FULL, Shape.SQUIGGLE);
    card17 = new Card(Count.TWO, Filling.FULL, Shape.OVAL);
    card18 = new Card(Count.TWO, Filling.FULL, Shape.DIAMOND);
    card19 = new Card(Count.THREE, Filling.EMPTY, Shape.SQUIGGLE);
    card20 = new Card(Count.THREE, Filling.EMPTY, Shape.OVAL);
    card21 = new Card(Count.THREE, Filling.EMPTY, Shape.DIAMOND);
    card22 = new Card(Count.THREE, Filling.STRIPED, Shape.SQUIGGLE);
    card23 = new Card(Count.THREE, Filling.STRIPED, Shape.OVAL);
    card24 = new Card(Count.THREE, Filling.STRIPED, Shape.DIAMOND);
    card25 = new Card(Count.THREE, Filling.FULL, Shape.SQUIGGLE);
    card26 = new Card(Count.THREE, Filling.FULL, Shape.OVAL);
    card27 = new Card(Count.THREE, Filling.FULL, Shape.DIAMOND);

    Card[] deck1 = {card1, card2, card3, card4, card5, card6, card7, card8, card9,
        card10, card11, card12, card13, card14, card15, card16, card17, card18,
        card19, card20, card21, card22, card23, card24, card25, card26, card27};

    Card[] deck5 = {card1, card8, card9, card18, card19, card25, card26, card27, card1, card2,
        card3};

    cardDeck5 = new ArrayList<>();

    Collections.addAll(cardDeck5, deck5);

    Card[] deck6 = {card4, card5, card6, card1, card8, card9, card18, card19, card25};

    cardDeck6 = new ArrayList<>();

    Collections.addAll(cardDeck6, deck6);

    cardDeck4 = new ArrayList<>();

    Collections.addAll(cardDeck4, deck1);

    cardDeckFinished = new ArrayList<>();

    cardDeckFinished.addAll(Arrays.asList(deck1).subList(0, 12));

  }

  protected void testInvalidStartGameWithDeck() {

    try {
      game.startGameWithDeck(game.getCompleteDeck(), -2, 3);
      fail("Game created with an invalid height");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid game construction", e.getMessage());
    }

    try {
      game.startGameWithDeck(game.getCompleteDeck(), -2, -3);
      fail("Game created with an invalid dimensions");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid game construction", e.getMessage());
    }

    try {
      game.startGameWithDeck(game.getCompleteDeck(), 3, -2);
      fail("Game created with invalid width");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid game construction", e.getMessage());
    }

    try {
      game.startGameWithDeck(game.getCompleteDeck(), 0, 0);
      fail("Game created with invalid dimensions");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid game construction", e.getMessage());
    }

    try {
      game.startGameWithDeck(cardDeck1, 1, 2);
      fail("Size of grid is less than 3");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid game construction", e.getMessage());
    }

    try {
      game.startGameWithDeck(cardDeck3, 3, 3);
      fail("Null deck of cards was given.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid game construction", e.getMessage());
    }

  }

  protected void testInvalidGetCardAtCoord() {

    try {
      game.getCardAtCoord(2, 1);
      fail("Game not started, yet still ran method");
    } catch (IllegalStateException e) {
      assertEquals("Game has not started yet.", e.getMessage());
    }

    try {
      game.getCardAtCoord(40, -28);
      fail("Game not started, yet still ran method");
    } catch (IllegalStateException e) {
      assertEquals("Game has not started yet.", e.getMessage());
    }

    try {
      game.getCardAtCoord(coord7);
      fail("Game not started, yet still ran method");
    } catch (IllegalStateException e) {
      assertEquals("Game has not started yet.", e.getMessage());
    }

    game.startGameWithDeck(game.getCompleteDeck(), 3, 3);

    try {
      game.getCardAtCoord(28, 1);
      fail("Invalid coordinates given, yet still ran method");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid coordinate was provided.", e.getMessage());
    }

    try {
      game.getCardAtCoord(1, -4);
      fail("Invalid coordinates given, yet still ran method");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid coordinate was provided.", e.getMessage());
    }

  }

  protected void testValidGetCardAtCoord() {

    game.startGameWithDeck(game.getCompleteDeck(), 3, 3);

    assertEquals("Expected the following value", "1EQ",
            game.getCardAtCoord(coord1).toString());
    assertEquals("Expected the following value", "1SO",
            game.getCardAtCoord(1, 1).toString());
    assertEquals("Expected the following value", "1FQ",
            game.getCardAtCoord(coord7).toString());
    assertEquals("Expected the following value", "1EO",
            game.getCardAtCoord(0, 1).toString());

    game.claimSet(coord1, coord4, coord7);

    assertEquals("Expected the following value", "2ED",
            game.getCardAtCoord(coord7).toString());
  }

  protected void testInvalidIsValidSet() {

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
  }

  protected void testValidGetScore() {

    game.startGameWithDeck(game.getCompleteDeck(), 3, 3);
    assertEquals("Expected the following value", 0,
            game.getScore());

    game.claimSet(coord1, coord2, coord3);
    assertEquals("Expected the following value", 1,
            game.getScore());

    game.claimSet(coord4, coord5, coord6);
    assertEquals("Expected the following value", 2,
            game.getScore());

    game.claimSet(coord1, coord2, coord3);
    assertEquals("Expected the following value", 3,
            game.getScore());

    game.claimSet(coord1, coord2, coord3);
    assertEquals("Expected the following value", 4,
            game.getScore());

    game.claimSet(coord1, coord2, coord3);
    assertEquals("Expected the following value", 5,
            game.getScore());

    game.claimSet(coord1, coord2, coord3);
    assertEquals("Expected the following value", 6,
            game.getScore());

    game.claimSet(coord1, coord2, coord3);

    assertEquals("Expected the following value", 7,
            game.getScore());

  }

  protected void testIsGameOver() {

    game.startGameWithDeck(cardDeckFinished, 3, 3);

    assertFalse("Expected the following value", game.isGameOver());

    game.claimSet(coord1, coord4, coord7);

    assertFalse("Expected the following value", game.isGameOver());

    game.claimSet(coord1, coord4, coord7);

    assertTrue("Expected the following value", game.isGameOver());

    game.startGameWithDeck(cardDeck2, 3, 3);

    assertFalse("Expected the following value", game.isGameOver());

    game.claimSet(coord1, coord4, coord7);


  }

  protected void testValidAnySetsPresent() {

    game.startGameWithDeck(cardDeckFinished, 3, 3);

    assertTrue("Expected the following value", game.anySetsPresent());
    game.claimSet(coord1, coord4, coord7);

    assertTrue("Expected the following value", game.anySetsPresent());
    game.claimSet(coord1, coord4, coord7);

  }

  protected void testValidStartGameWithDeck() {


    game.startGameWithDeck(cardDeck2, 3, 3);

    assertEquals("Expected the following value: ", "1EQ 1EO 1ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD", view.toString());

  }

  @Test
  protected void testValidClaimSet() {

    game.startGameWithDeck(cardDeckFinished, 3, 3);

    game.claimSet(coord1, coord4, coord7);

    assertEquals("Expected the following value", 0,
            cardDeckFinished.size());

    assertEquals("Expected the following value", "2EQ 1EO 1ED\n" +
                    "2EO 1SO 1SD\n" +
                    "2ED 1FO 1FD",
            view.toString());

    game.startGameWithDeck(this.game.getCompleteDeck(), 3, 3);

    game.claimSet(coord1, coord2, coord3);

    assertEquals("Expected the following value", "2EQ 2EO 2ED\n" +
                    "1SQ 1SO 1SD\n" +
                    "1FQ 1FO 1FD",
            view.toString());

    game.claimSet(coord7, coord8, coord9);

    assertEquals("Expected the following value", "2EQ 2EO 2ED\n" +
                    "1SQ 1SO 1SD\n" +
                    "2SQ 2SO 2SD",
            view.toString());

    game.claimSet(coord7, coord8, coord9);
    game.claimSet(coord7, coord8, coord9);
    game.claimSet(coord7, coord8, coord9);
    game.claimSet(coord7, coord8, coord9);

    assertEquals("Expected the following value", "2EQ 2EO 2ED\n" +
                    "1SQ 1SO 1SD\n" +
                    "3FQ 3FO 3FD",
            view.toString());

    // all three different attributes:

    game.claimSet(coord1, coord5, coord9);

    game.startGameWithDeck(this.game.getCompleteDeck(), 3, 3);

    assertEquals("1EQ 1EO 1ED\n" +
                    "1SQ 1SO 1SD\n" +
                    "1FQ 1FO 1FD",
            view.toString());

    // two different attributes:

    game.claimSet(coord1, coord5, coord9);

  }

  protected void testInvalidClaimSet() {

    try {
      game.claimSet(coord1, coord2, coord3);
      fail("Game not started, yet accepted coordinates");
    } catch (IllegalStateException e) {
      assertEquals("The game has not yet started.", e.getMessage());
    }

    game.startGameWithDeck(cardDeck2, 3, 3);
    game.claimSet(coord1, coord2, coord3);

    try {
      game.claimSet(coord1, coord2, coord3);
      fail("Game has ended, yet accepted coordinates");
    } catch (IllegalStateException e) {
      assertEquals("The game has ended.", e.getMessage());
    }

    init();

    game.startGameWithDeck(cardDeck2, 3, 3);

  }


}
