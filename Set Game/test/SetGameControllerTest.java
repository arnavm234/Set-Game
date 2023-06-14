import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import cs3500.set.controller.SetGameController;
import cs3500.set.controller.SetGameControllerImpl;
import cs3500.set.model.hw02.SetGameModel;
import cs3500.set.model.hw02.SetThreeGameModel;
import cs3500.set.view.SetGameTextView;
import cs3500.set.view.SetGameView;
import helpers.BadAppendable;
import helpers.BadReadable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class represents tests for a set game controller.
 */
public class SetGameControllerTest {

  private SetGameModel model;
  private SetGameView view;
  private SetGameView badView;
  private Readable readable;


  @Before
  public void init() {
    Readable badReadable;
    model = new SetThreeGameModel();
    model.startGameWithDeck(this.model.getCompleteDeck(), 3, 3);
    view = new SetGameTextView(model, new StringBuilder());
    badView = new SetGameTextView(model, new BadAppendable());
    readable = new StringReader("");
    badReadable = new BadReadable("this is an invalid readable");
  }


  ////////////////////////////// tests for the constructor //////////////////////////////

  @Test
  public void testValidConstructor() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("q");
    SetGameView view = new SetGameTextView(model, ap);
    SetGameController controller = new SetGameControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("Welcome to this game of Set!\n" +
            "First, enter 2 numbers that represent the height and width of the game with a " +
            "space in the middle.\n" +
            "Then enter 3 pairs of coordinates with a space in between.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the " +
            "second row/column, etc.\n" +
            "Enter q or Q at any moment to quit the game.\n" +
            "Game quit!\n" +
            "Score: 0", ap.toString());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testInvalidController1() {
    SetGameController controller =
            new SetGameControllerImpl(null, view, readable);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testInvalidController2() {
    SetGameController controller =
            new SetGameControllerImpl(model, null, readable);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidController3() {
    SetGameController controller =
            new SetGameControllerImpl(model, view, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidController4() {
    SetGameController controller =
            new SetGameControllerImpl(null, null, readable);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidController5() {
    SetGameController controller =
            new SetGameControllerImpl(null, view, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidController6() {
    SetGameController controller =
            new SetGameControllerImpl(model, null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidController7() {
    SetGameController controller =
            new SetGameControllerImpl(null, null, null);
  }


  ////////////////////////////// tests for the playGame method //////////////////////////////

  //  @Test(expected = IllegalStateException.class)
  //  public void testBadReadable() {
  //    SetGameController badController = new SetGameControllerImpl(model, view,
  //            badReadable);
  //    badController.playGame();
  //  }

  @Test(expected = IllegalStateException.class)
  public void testBadTransmission() {
    SetGameController badController = new SetGameControllerImpl(model, badView,
            readable);
    badController.playGame();
  }

  @Test(expected = IllegalStateException.class)
  public void testValidClaimSetNoEnd() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("3 3 1 1 1 2 1 3");
    SetGameView view = new SetGameTextView(model, ap);
    SetGameController c = new SetGameControllerImpl(model, view, rd);
    c.playGame();
  }


  @Test(expected = IllegalStateException.class)
  public void testInvalidClaimSetNoEnd() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("3 3 1 1 2 1 2 3");
    SetGameView view = new SetGameTextView(model, ap);
    SetGameController c = new SetGameControllerImpl(model, view, rd);
    c.playGame();
  }

  //  @Test(expected = IllegalStateException.class)
  //  public void testNegativeInputNoEnd() {
  //    Appendable ap = new StringBuilder();
  //    Readable rd = new StringReader("-20 1");
  //    SetGameView view = new SetGameTextView(model, ap);
  //    SetGameController c = new SetGameControllerImpl(model, view, rd);
  //    c.playGame();
  //  }
  //
  //  @Test(expected = IllegalStateException.class)
  //  public void testErrorStringNoEnd() {
  //    Appendable ap = new StringBuilder();
  //    Readable rd = new StringReader("arnav mishra");
  //    SetGameView view = new SetGameTextView(model, ap);
  //    SetGameController c = new SetGameControllerImpl(model, view, rd);
  //    c.playGame();
  //  }


  @Test(expected = IllegalStateException.class)
  public void testMultipleInputTypesNoEnd() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("arnav -20 3 3 1 1 1 2");
    SetGameView view = new SetGameTextView(model, ap);
    SetGameController c = new SetGameControllerImpl(model, view, rd);
    c.playGame();
  }

  @Test(expected = IllegalStateException.class)
  public void testIncompleteCoords() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("3 3 4");
    SetGameView view = new SetGameTextView(model, ap);
    SetGameController c = new SetGameControllerImpl(model, view, rd);
    c.playGame();
  }


  ////////////////////////// testing different sequences with a q at the end /////////////////////

  @Test
  public void testQuit() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("q");
    SetGameView view = new SetGameTextView(model, ap);
    SetGameController c = new SetGameControllerImpl(model, view, rd);
    c.playGame();
    assertEquals("Welcome to this game of Set!\n" +
            "First, enter 2 numbers that represent the height and width of the game with " +
            "a space " +
            "in the middle.\n" +
            "Then enter 3 pairs of coordinates with a space in between.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to represent " +
            "the second row/column, etc.\n" +
            "Enter q or Q at any moment to quit the game.\n" +
            "Game quit!\n" +
            "Score: 0", ap.toString());
  }

  // testing quitting the game with a capital q
  @Test
  public void testQuitCapitalQ() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("Q");
    SetGameView view = new SetGameTextView(model, ap);
    SetGameController c = new SetGameControllerImpl(model, view, rd);
    c.playGame();
    assertEquals("Welcome to this game of Set!\n" +
            "First, enter 2 numbers that represent the height and width of the game with " +
            "a space " +
            "in the middle.\n" +
            "Then enter 3 pairs of coordinates with a space in between.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to represent " +
            "the second row/column, etc.\n" +
            "Enter q or Q at any moment to quit the game.\n" +
            "Game quit!\n" +
            "Score: 0", ap.toString());
  }

  @Test
  public void testQuitAsHeight() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("3 q");
    SetGameView view = new SetGameTextView(model, ap);
    SetGameController c = new SetGameControllerImpl(model, view, rd);
    c.playGame();
    assertEquals("Welcome to this game of Set!\n" +
            "First, enter 2 numbers that represent the height and width" +
            " of the game with a space in the middle.\n" +
            "Then enter 3 pairs of coordinates with a space in between.\n" +
            "The numbers should begin at 1 to represent the first row/column, " +
            "2 to represent the second row/column, etc.\n" +
            "Enter q or Q at any moment to quit the game.\n" +
            "Game quit!\n" +
            "Score: 0", ap.toString());
  }

  @Test
  public void testQuitAfterValidHeightAndWidth() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("3 3 q");
    SetGameView view = new SetGameTextView(model, ap);
    SetGameController c = new SetGameControllerImpl(model, view, rd);
    c.playGame();
    assertEquals("Welcome to this game of Set!\n" +
            "First, enter 2 numbers that represent the height and width of " +
            "the game with a space in the middle.\n" +
            "Then enter 3 pairs of coordinates with a space in between.\n" +
            "The numbers should begin at 1 to represent the first row/column, " +
            "2 to represent the second row/column, etc.\n" +
            "Enter q or Q at any moment to quit the game.\n" +
            "1EQ 1EO 1ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 0\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "1EQ 1EO 1ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 0\n", ap.toString());
  }

  @Test
  public void testQuitAfter1Set() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("3 3 1 1 1 2 1 3 q");
    SetGameView view = new SetGameTextView(model, ap);
    SetGameController c = new SetGameControllerImpl(model, view, rd);
    c.playGame();
    assertEquals("Welcome to this game of Set!\n" +
            "First, enter 2 numbers that represent the height and width of " +
            "the game with a space in the middle.\n" +
            "Then enter 3 pairs of coordinates with a space in between.\n" +
            "The numbers should begin at 1 to represent the first row/column, " +
            "2 to represent the second row/column, etc.\n" +
            "Enter q or Q at any moment to quit the game.\n" +
            "1EQ 1EO 1ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 0\n" +
            "2EQ 2EO 2ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 1\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "2EQ 2EO 2ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 1\n", ap.toString());
  }

  @Test
  public void testQuitAfter2Sets() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("3 3 1 1 1 2 1 3 1 1 1 2 1 3 q");
    SetGameView view = new SetGameTextView(model, ap);
    SetGameController c = new SetGameControllerImpl(model, view, rd);
    c.playGame();
    assertEquals("Welcome to this game of Set!\n" +
            "First, enter 2 numbers that represent the height and width of " +
            "the game with a space in the middle.\n" +
            "Then enter 3 pairs of coordinates with a space in between.\n" +
            "The numbers should begin at 1 to represent the first row/column, " +
            "2 to represent the second row/column, etc.\n" +
            "Enter q or Q at any moment to quit the game.\n" +
            "1EQ 1EO 1ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 0\n" +
            "2EQ 2EO 2ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 1\n" +
            "2SQ 2SO 2SD\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 2\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "2SQ 2SO 2SD\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 2\n", ap.toString());
  }


  @Test
  public void testQuitInCoord() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("4 2 q 4");
    SetGameView view = new SetGameTextView(model, ap);
    SetGameController c = new SetGameControllerImpl(model, view, rd);
    c.playGame();
    assertEquals("Welcome to this game of Set!\n" +
            "First, enter 2 numbers that represent the height and width of the game with a " +
            "space in the middle.\n" +
            "Then enter 3 pairs of coordinates with a space in between.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to represent " +
            "the second row/column, etc.\n" +
            "Enter q or Q at any moment to quit the game.\n" +
            "Invalid height/width. Try again.\n" +
            "Game quit!\n" +
            "Score: 0", ap.toString());

  }

  @Test
  public void testInvalidHeight() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("-1 3 q 4");
    SetGameView view = new SetGameTextView(model, ap);
    SetGameController c = new SetGameControllerImpl(model, view, rd);
    c.playGame();
    assertEquals("Welcome to this game of Set!\n" +
            "First, enter 2 numbers that represent the height and width " +
            "of the game with a space in the middle.\n" +
            "Then enter 3 pairs of coordinates with a space in between.\n" +
            "The numbers should begin at 1 to represent the first row/column, " +
            "2 to represent the second row/column, etc.\n" +
            "Enter q or Q at any moment to quit the game.\n" +
            "Invalid Input: -1. Please re-enter that value again.\n" +
            "Game quit!\n" +
            "Score: 0", ap.toString());
  }

  @Test
  public void testInvalidHeight2() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("-1 3 3 1 1 1 2 1 3 q");
    SetGameView view = new SetGameTextView(model, ap);
    SetGameController c = new SetGameControllerImpl(model, view, rd);
    c.playGame();
    assertEquals("Welcome to this game of Set!\n" +
            "First, enter 2 numbers that represent the height and width of " +
            "the game with a space in the middle.\n" +
            "Then enter 3 pairs of coordinates with a space in between.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 " +
            "to represent the second row/column, etc.\n" +
            "Enter q or Q at any moment to quit the game.\n" +
            "Invalid Input: -1. Please re-enter that value again.\n" +
            "1EQ 1EO 1ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 0\n" +
            "2EQ 2EO 2ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 1\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "2EQ 2EO 2ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 1\n", ap.toString());
  }

  @Test
  public void testInvalidWidth() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("3 -1 q 4");
    SetGameView view = new SetGameTextView(model, ap);
    SetGameController c = new SetGameControllerImpl(model, view, rd);
    c.playGame();
    assertEquals("Welcome to this game of Set!\n" +
            "First, enter 2 numbers that represent the height and width " +
            "of the game with a space in the middle.\n" +
            "Then enter 3 pairs of coordinates with a space in between.\n" +
            "The numbers should begin at 1 to represent the first row/column," +
            " 2 to represent the second row/column, etc.\n" +
            "Enter q or Q at any moment to quit the game.\n" +
            "Invalid Input: -1. Please re-enter that value again.\n" +
            "Game quit!\n" +
            "Score: 0", ap.toString());

  }

  @Test
  public void testInvalidWidthAndHeight() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("-1 -1 q 4");
    SetGameView view = new SetGameTextView(model, ap);
    SetGameController c = new SetGameControllerImpl(model, view, rd);
    c.playGame();
    assertEquals("Welcome to this game of Set!\n" +
            "First, enter 2 numbers that represent the height and width of " +
            "the game with a space in the middle.\n" +
            "Then enter 3 pairs of coordinates with a space in between.\n" +
            "The numbers should begin at 1 to represent the first row/column," +
            " 2 to represent the second row/column, etc.\n" +
            "Enter q or Q at any moment to quit the game.\n" +
            "Invalid Input: -1. Please re-enter that value again.\n" +
            "Invalid Input: -1. Please re-enter that value again.\n" +
            "Game quit!\n" +
            "Score: 0", ap.toString());

  }

  @Test
  public void testQuitInFromHeight() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("q 2 4 4");
    SetGameView view = new SetGameTextView(model, ap);
    SetGameController c = new SetGameControllerImpl(model, view, rd);
    c.playGame();
    assertEquals("Welcome to this game of Set!\n" +
            "First, enter 2 numbers that represent the height and width of " +
            "the game with a space in the middle.\n" +
            "Then enter 3 pairs of coordinates with a space in between.\n" +
            "The numbers should begin at 1 to represent the first row/column," +
            " 2 to represent the second row/column, etc.\n" +
            "Enter q or Q at any moment to quit the game.\n" +
            "Game quit!\n" +
            "Score: 0", ap.toString());
    //should output game over and score = 0
  }

  @Test
  public void testQuitInWidth() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("3 q 4 3");
    SetGameView view = new SetGameTextView(model, ap);
    SetGameController c = new SetGameControllerImpl(model, view, rd);
    c.playGame();
    assertEquals("Welcome to this game of Set!\n" +
            "First, enter 2 numbers that represent the height and width of" +
            " the game with a space in the middle.\n" +
            "Then enter 3 pairs of coordinates with a space in between.\n" +
            "The numbers should begin at 1 to represent the first row/column, " +
            "2 to represent the second row/column, etc.\n" +
            "Enter q or Q at any moment to quit the game.\n" +
            "Game quit!\n" +
            "Score: 0", ap.toString());
  }

  @Test
  public void testValidClaimSet() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("3 3 1 1 1 2 1 3 q");
    SetGameView view = new SetGameTextView(model, ap);
    SetGameController c = new SetGameControllerImpl(model, view, rd);
    c.playGame();
    assertEquals("Welcome to this game of Set!\n" +
            "First, enter 2 numbers that represent the height and width of " +
            "the game with a space in the middle.\n" +
            "Then enter 3 pairs of coordinates with a space in between.\n" +
            "The numbers should begin at 1 to represent the first row/column, " +
            "2 to represent the second row/column, etc.\n" +
            "Enter q or Q at any moment to quit the game.\n" +
            "1EQ 1EO 1ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 0\n" +
            "2EQ 2EO 2ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 1\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "2EQ 2EO 2ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 1\n", ap.toString());
  }

  @Test
  public void testInvalidMove() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("3 3 -1 4 q");
    SetGameView view = new SetGameTextView(model, ap);
    SetGameController c = new SetGameControllerImpl(model, view, rd);
    c.playGame();
    assertEquals("Welcome to this game of Set!\n" +
            "First, enter 2 numbers that represent the height and width " +
            "of the game with a space in the middle.\n" +
            "Then enter 3 pairs of coordinates with a space in between.\n" +
            "The numbers should begin at 1 to represent the first row/column, " +
            "2 to represent the second row/column, etc.\n" +
            "Enter q or Q at any moment to quit the game.\n" +
            "1EQ 1EO 1ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 0\n" +
            "Invalid Input: -1. Please re-enter that value again.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "1EQ 1EO 1ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 0\n", ap.toString());
  }

  @Test
  public void testNegativeNumberInput() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("-5 q");
    SetGameView view = new SetGameTextView(model, ap);
    SetGameController c = new SetGameControllerImpl(model, view, rd);
    c.playGame();
    assertEquals("Welcome to this game of Set!\n" +
            "First, enter 2 numbers that represent the height and width " +
            "of the game with a space in the middle.\n" +
            "Then enter 3 pairs of coordinates with a space in between.\n" +
            "The numbers should begin at 1 to represent the first row/column," +
            " 2 to represent the second row/column, etc.\n" +
            "Enter q or Q at any moment to quit the game.\n" +
            "Invalid Input: -5. Please re-enter that value again.\n" +
            "Game quit!\n" +
            "Score: 0", ap.toString());
  }

  @Test
  public void testErrorStringInput() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("arnav OOD q");
    SetGameView view = new SetGameTextView(model, ap);
    SetGameController c = new SetGameControllerImpl(model, view, rd);
    c.playGame();
    assertEquals("Welcome to this game of Set!\n" +
            "First, enter 2 numbers that represent the height and " +
            "width of the game with a space in the middle.\n" +
            "Then enter 3 pairs of coordinates with a space in between.\n" +
            "The numbers should begin at 1 to represent the first row/column, " +
            "2 to represent the second row/column, etc.\n" +
            "Enter q or Q at any moment to quit the game.\n" +
            "Invalid Input: arnav. Please re-enter that value again.\n" +
            "Invalid Input: OOD. Please re-enter that value again.\n" +
            "Game quit!\n" +
            "Score: 0", ap.toString());
  }

  @Test
  public void testMultipleInputsWithEnd() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("arnav 3 3 4 4 1 1 1 1 -45 a b c 5 100 q");
    SetGameView view = new SetGameTextView(model, ap);
    SetGameController c = new SetGameControllerImpl(model, view, rd);
    c.playGame();
    assertEquals("Welcome to this game of Set!\n" +
            "First, enter 2 numbers that represent the height and width of the " +
            "game with a space in the middle.\n" +
            "Then enter 3 pairs of coordinates with a space in between.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 " +
            "to represent the second row/column, etc.\n" +
            "Enter q or Q at any moment to quit the game.\n" +
            "Invalid Input: arnav. Please re-enter that value again.\n" +
            "1EQ 1EO 1ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 0\n" +
            "Invalid claim. Try again.\n" +
            "Invalid Input: -45. Please re-enter that value again.\n" +
            "Invalid Input: a. Please re-enter that value again.\n" +
            "Invalid Input: b. Please re-enter that value again.\n" +
            "Invalid Input: c. Please re-enter that value again.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "1EQ 1EO 1ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 0\n", ap.toString());
  }

  @Test
  public void testInputAfterQ() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("q arnav 1 1 1 1 4 2 4 4 wefwef");
    SetGameView view = new SetGameTextView(model, ap);
    SetGameController c = new SetGameControllerImpl(model, view, rd);
    c.playGame();
    assertEquals("Welcome to this game of Set!\n" +
            "First, enter 2 numbers that represent the height and width " +
            "of the game with a space in the middle.\n" +
            "Then enter 3 pairs of coordinates with a space in between.\n" +
            "The numbers should begin at 1 to represent the first row/column" +
            ", 2 to represent the second row/column, etc.\n" +
            "Enter q or Q at any moment to quit the game.\n" +
            "Game quit!\n" +
            "Score: 0", ap.toString());
  }

  @Test
  public void testInputAfterQAndBeforeQ() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader(" 4 2 4 4 hi -9 0 q arnav 1 1 1 1 4 2 4 4 hello! -25");
    SetGameView view = new SetGameTextView(model, ap);
    SetGameController c = new SetGameControllerImpl(model, view, rd);
    c.playGame();
    assertEquals("Welcome to this game of Set!\n" +
            "First, enter 2 numbers that represent the height and width of " +
            "the game with a space in the middle.\n" +
            "Then enter 3 pairs of coordinates with a space in between.\n" +
            "The numbers should begin at 1 to represent the first row/column," +
            " 2 to represent the second row/column, etc.\n" +
            "Enter q or Q at any moment to quit the game.\n" +
            "Invalid height/width. Try again.\n" +
            "Invalid height/width. Try again.\n" +
            "Invalid Input: hi. Please re-enter that value again.\n" +
            "Invalid Input: -9. Please re-enter that value again.\n" +
            "Invalid Input: 0. Please re-enter that value again.\n" +
            "Game quit!\n" +
            "Score: 0", ap.toString());
  }

  @Test
  public void testInvalidAmongstValid() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader(" 3 3 apple 1 1 q");
    SetGameView view = new SetGameTextView(model, ap);
    SetGameController c = new SetGameControllerImpl(model, view, rd);
    c.playGame();
    assertEquals("Welcome to this game of Set!\n" +
            "First, enter 2 numbers that represent the height and width of the " +
            "game with a space in the middle.\n" +
            "Then enter 3 pairs of coordinates with a space in between.\n" +
            "The numbers should begin at 1 to represent the first row/column, " +
            "2 to represent the second row/column, etc.\n" +
            "Enter q or Q at any moment to quit the game.\n" +
            "1EQ 1EO 1ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 0\n" +
            "Invalid Input: apple. Please re-enter that value again.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "1EQ 1EO 1ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 0\n", ap.toString());
  }


  ////////////////////////////// testing different sequences of game over //////////////////////////

  @Test
  public void testGameOver() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("3 3 1 1 1 2 1 3 1 1 1 2 1 3 1 1 1 2 1 3 1 1 1 2 1 3 " +
            "1 1 1 2 1 3 1 1 1 2 1 3 1 1 1 2 1 3");
    SetGameView view = new SetGameTextView(model, ap);
    SetGameController c = new SetGameControllerImpl(model, view, rd);
    c.playGame();
    assertTrue(model.isGameOver());
    assertEquals("Welcome to this game of Set!\n" +
            "First, enter 2 numbers that represent the height and width " +
            "of the game with a space in the middle.\n" +
            "Then enter 3 pairs of coordinates with a space in between.\n" +
            "The numbers should begin at 1 to represent the first row/column," +
            " 2 to represent the second row/column, etc.\n" +
            "Enter q or Q at any moment to quit the game.\n" +
            "1EQ 1EO 1ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 0\n" +
            "2EQ 2EO 2ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 1\n" +
            "2SQ 2SO 2SD\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 2\n" +
            "2FQ 2FO 2FD\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 3\n" +
            "3EQ 3EO 3ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 4\n" +
            "3SQ 3SO 3SD\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 5\n" +
            "3FQ 3FO 3FD\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 6\n" +
            "Game over!\n" +
            "Score: 7\n", ap.toString());
  }

  @Test
  public void testGameOverWithInvalidClaimSet() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("3 3 1 1 1 2 1 3 1 1 1 2 1 3 1 1 1 2 1 3 1 1 1 2 1 3 \" +\n" +
            " 1 1 1 2 1 3 1 1 1 2 1 3 1 1 1 2 2 3 1 1 1 2 1 3 q");
    SetGameView view = new SetGameTextView(model, ap);
    SetGameController c = new SetGameControllerImpl(model, view, rd);
    c.playGame();
    assertTrue(model.isGameOver());
    assertEquals("Welcome to this game of Set!\n" +
            "First, enter 2 numbers that represent the height and width of the game with a " +
            "space in the middle.\n" +
            "Then enter 3 pairs of coordinates with a space in between.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to represent " +
            "the second row/column, etc.\n" +
            "Enter q or Q at any moment to quit the game.\n" +
            "1EQ 1EO 1ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 0\n" +
            "2EQ 2EO 2ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 1\n" +
            "2SQ 2SO 2SD\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 2\n" +
            "2FQ 2FO 2FD\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 3\n" +
            "3EQ 3EO 3ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 4\n" +
            "Invalid Input: \". Please re-enter that value again.\n" +
            "Invalid Input: +. Please re-enter that value again.\n" +
            "3SQ 3SO 3SD\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 5\n" +
            "3FQ 3FO 3FD\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 6\n" +
            "Invalid claim. Try again.\n" +
            "Game over!\n" +
            "Score: 7\n", ap.toString());
  }

  @Test
  public void testGameOverWithGameOverAndQuitAfter() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("3 3 1 1 1 2 1 3 1 1 1 2 1 3 1 1 1 2 1 3 1 1 1 2 1 3 \" +\n" +
            " 1 1 1 2 1 3 1 1 1 2 1 3 1 1 1 2 2 3 1 1 1 2 1 3 q");
    SetGameView view = new SetGameTextView(model, ap);
    SetGameController c = new SetGameControllerImpl(model, view, rd);
    c.playGame();
    assertTrue(model.isGameOver());
    assertEquals("Welcome to this game of Set!\n" +
            "First, enter 2 numbers that represent the height and width of the game with a " +
            "pace in the middle.\n" +
            "Then enter 3 pairs of coordinates with a space in between.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to represent " +
            "the second row/column, etc.\n" +
            "Enter q or Q at any moment to quit the game.\n" +
            "1EQ 1EO 1ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 0\n" +
            "2EQ 2EO 2ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 1\n" +
            "2SQ 2SO 2SD\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 2\n" +
            "2FQ 2FO 2FD\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 3\n" +
            "3EQ 3EO 3ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 4\n" +
            "Invalid Input: \". Please re-enter that value again.\n" +
            "Invalid Input: +. Please re-enter that value again.\n" +
            "3SQ 3SO 3SD\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 5\n" +
            "3FQ 3FO 3FD\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 6\n" +
            "Invalid claim. Try again.\n" +
            "Game over!\n" +
            "Score: 7\n", ap.toString());
  }

  @Test
  public void testGameOverWithErrorInputs() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("3 3 1 1 1 2 1 3 1 1 1 2 1 3 1 1 1 2 1 3 1 1 1 2 1 3 " +
            " 1 1 1 2 1 3 1 1 1 2 1 3 1 1 1 2 2 3 -1 1 1 1 2 1 3");
    SetGameView view = new SetGameTextView(model, ap);
    SetGameController c = new SetGameControllerImpl(model, view, rd);
    c.playGame();
    assertTrue(model.isGameOver());
    assertEquals("Welcome to this game of Set!\n" +
            "First, enter 2 numbers that represent the height and " +
            "width of the game with a space in the middle.\n" +
            "Then enter 3 pairs of coordinates with a space in between.\n" +
            "The numbers should begin at 1 to represent the first row/column, " +
            "2 to represent the second row/column, etc.\n" +
            "Enter q or Q at any moment to quit the game.\n" +
            "1EQ 1EO 1ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 0\n" +
            "2EQ 2EO 2ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 1\n" +
            "2SQ 2SO 2SD\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 2\n" +
            "2FQ 2FO 2FD\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 3\n" +
            "3EQ 3EO 3ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 4\n" +
            "3SQ 3SO 3SD\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 5\n" +
            "3FQ 3FO 3FD\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 6\n" +
            "Invalid claim. Try again.\n" +
            "Invalid Input: -1. Please re-enter that value again.\n" +
            "Game over!\n" +
            "Score: 7\n", ap.toString());
  }

  @Test
  public void testGameOverWithNoInvalidClaimSetAndExtraText() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("3 3 1 1 1 2 1 3 1 1 1 2 1 3 1 1 1 2 1 3 1 1 1 2 1 3" +
            " 1 1 1 2 efiwef -2 1 3 1 1 1 2 1 3 1 1 1 2 2 3 -1 1 1 1 2 1 3");
    SetGameView view = new SetGameTextView(model, ap);
    SetGameController c = new SetGameControllerImpl(model, view, rd);
    c.playGame();
    assertTrue(model.isGameOver());
    assertEquals("Welcome to this game of Set!\n" +
            "First, enter 2 numbers that represent the height and width of the game with a " +
            "space in the middle.\n" +
            "Then enter 3 pairs of coordinates with a space in between.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to represent the" +
            " second row/column, etc.\n" +
            "Enter q or Q at any moment to quit the game.\n" +
            "1EQ 1EO 1ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 0\n" +
            "2EQ 2EO 2ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 1\n" +
            "2SQ 2SO 2SD\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 2\n" +
            "2FQ 2FO 2FD\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 3\n" +
            "3EQ 3EO 3ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 4\n" +
            "Invalid Input: efiwef. Please re-enter that value again.\n" +
            "Invalid Input: -2. Please re-enter that value again.\n" +
            "3SQ 3SO 3SD\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 5\n" +
            "3FQ 3FO 3FD\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 6\n" +
            "Invalid claim. Try again.\n" +
            "Invalid Input: -1. Please re-enter that value again.\n" +
            "Game over!\n" +
            "Score: 7\n", ap.toString());
  }

  @Test
  public void testGameOverWithNoInvalidClaimSetAndQuitSequenceAfter() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("3 3 1 1 1 2 1 3 1 1 1 2 1 3 1 1 1 2 1 3 1 1 1 2 1 3" +
            " 1 1 1 2 1 3 1 1 1 2 1 3 1 1 1 2 2 3 1 1 1 2 1 3 q");
    SetGameView view = new SetGameTextView(model, ap);
    SetGameController c = new SetGameControllerImpl(model, view, rd);
    c.playGame();
    assertTrue(model.isGameOver());
    assertEquals("Welcome to this game of Set!\n" +
            "First, enter 2 numbers that represent the height and width " +
            "of the game with a space in the middle.\n" +
            "Then enter 3 pairs of coordinates with a space in between.\n" +
            "The numbers should begin at 1 to represent the first row/column, " +
            "2 to represent the second row/column, etc.\n" +
            "Enter q or Q at any moment to quit the game.\n" +
            "1EQ 1EO 1ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 0\n" +
            "2EQ 2EO 2ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 1\n" +
            "2SQ 2SO 2SD\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 2\n" +
            "2FQ 2FO 2FD\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 3\n" +
            "3EQ 3EO 3ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 4\n" +
            "3SQ 3SO 3SD\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 5\n" +
            "3FQ 3FO 3FD\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 6\n" +
            "Invalid claim. Try again.\n" +
            "Game over!\n" +
            "Score: 7\n", ap.toString());
  }

  @Test
  public void testGameOverSequenceWithQEmbeddedInsideAndQAtEnd() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("3 3 1 1 1 2 1 3 q 2 3 2 1 2 2 Q");
    SetGameView view = new SetGameTextView(model, ap);
    SetGameController c = new SetGameControllerImpl(model, view, rd);
    c.playGame();
    assertEquals("Welcome to this game of Set!\n" +
            "First, enter 2 numbers that represent the height and width of the game with a " +
            "space in the middle.\n" +
            "Then enter 3 pairs of coordinates with a space in between.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to represent " +
            "the second row/column, etc.\n" +
            "Enter q or Q at any moment to quit the game.\n" +
            "1EQ 1EO 1ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 0\n" +
            "2EQ 2EO 2ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 1\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "2EQ 2EO 2ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n" +
            "Score: 1\n", ap.toString());
  }

  ///////////////// tests for the mock ////////////////////////

  //  @Test
  //  public void testInputsOfValidMove() {
  //    Readable in = new StringReader("3 3 1 1 q");
  //    StringBuilder log = new StringBuilder();
  //    ConfirmInputsMock mock = new ConfirmInputsMock(log);
  //    SetGameController c = new SetGameControllerImpl(mock, view, in);
  //
  //    c.playGame();
  //    assertEquals("Controller obtained the height from the model.\n" +
  //                    "Controller obtained the score from the model.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller obtained the score from the model.\n",
  //            log.toString());
  //  }

  //  @Test
  //  public void testInputsOfGameOverSequence() {
  //    Readable in = new StringReader("4 2 1 1 1 2 1 3 q");
  //    StringBuilder log = new StringBuilder();
  //    ConfirmInputsMock mock = new ConfirmInputsMock(log);
  //    SetGameController c = new SetGameControllerImpl(mock, view, in);
  //
  //    c.playGame();
  //    assertEquals("Controller obtained the height from the model.\n" +
  //                    "Controller obtained the score from the model.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "A set claim was attempted at coordinates: (r3,c1), (r0,c0), (r0,c1).\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller obtained the score from the model.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller obtained the score from the model.\n",
  //            log.toString());
  //  }

  //  @Test
  //  public void testEmptyInputWhenOnly4Numbers() {
  //    Readable in = new StringReader("4 2 4 -10 q");
  //    StringBuilder log = new StringBuilder();
  //    ConfirmInputsMock mock = new ConfirmInputsMock(log);
  //    SetGameController c = new SetGameControllerImpl(mock, view, in);
  //
  //    c.playGame();
  //    assertEquals("Controller obtained the height from the model.\n" +
  //                    "Controller obtained the score from the model.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller obtained the score from the model.",
  //            log.toString());
  //  }

  //  @Test
  //  public void testControllerNotPassingInvalidInputsToModel() {
  //
  //    Readable in = new StringReader("4 -10 2 arnav silly 4 q");
  //    StringBuilder log = new StringBuilder();
  //    ConfirmInputsMock mock = new ConfirmInputsMock(log);
  //    SetGameController c = new SetGameControllerImpl(mock, view, in);
  //
  //    c.playGame();
  //    assertEquals("Controller obtained the height from the model.\n" +
  //                    "Controller obtained the score from the model.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller obtained the score from the model.\n",
  //            log.toString());
  //  }

  //  @Test
  //  public void testControllerNotPassingOnlyInvalidInputsToModel() {
  //
  //    Readable in = new StringReader("hi my name is Arnav and i have -1 but my friend is 0" +
  //            " and my professor is 0.5 q");
  //    StringBuilder log = new StringBuilder();
  //    ConfirmInputsMock mock = new ConfirmInputsMock(log);
  //    SetGameController c = new SetGameControllerImpl(mock, view, in);
  //
  //    c.playGame();
  //    assertEquals("Controller obtained the height from the model.\n" +
  //                    "Controller obtained the score from the model.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller asked if the game is over.\n" +
  //                    "Controller obtained the score from the model.\n",
  //            log.toString());
  //  }
}

