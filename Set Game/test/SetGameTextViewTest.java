import org.junit.Before;
import org.junit.Test;


import java.io.IOException;

import cs3500.set.model.hw02.Coord;
import helpers.BadAppendable;
import cs3500.set.model.hw02.SetThreeGameModel;
import cs3500.set.model.hw02.SetGameModel;
import cs3500.set.view.SetGameTextView;
import cs3500.set.view.SetGameView;

import static org.junit.Assert.assertEquals;

/**
 * This class represents tests for the class: SetGameTextView.
 */
public class SetGameTextViewTest {

  private SetGameModel defaultModel =
          new SetThreeGameModel();
  private SetGameModel modelWithValidSetClaimed =
          new SetThreeGameModel();

  private SetGameView viewWithDefaultModel;
  private SetGameView viewWithValidSetClaimed;

  private SetGameView viewWithModelAndAppendable;
  private SetGameView viewWithModelAndAppendableWithValidSetClaimed;
  private Appendable goodAppendable;
  private Appendable badAppendable;


  @Before
  public void init() {

    defaultModel.startGameWithDeck(this.defaultModel.getCompleteDeck(), 3, 3);
    modelWithValidSetClaimed.startGameWithDeck(this.defaultModel.getCompleteDeck(), 3, 3);

    viewWithDefaultModel =
            new SetGameTextView(this.defaultModel);

    // performing a move on one of the models and then passing that into the view
    modelWithValidSetClaimed.claimSet(new Coord(0, 0),
            new Coord(0, 1), new Coord(0, 2));

    viewWithValidSetClaimed = new SetGameTextView(modelWithValidSetClaimed);

    // views constructed using the constructor that also takes in a specific appendable
    goodAppendable = new StringBuilder();
    badAppendable = new BadAppendable();
    viewWithModelAndAppendable =
            new SetGameTextView(this.defaultModel, goodAppendable);
    viewWithModelAndAppendableWithValidSetClaimed =
            new SetGameTextView(this.defaultModel, goodAppendable);
    viewWithValidSetClaimed =
            new SetGameTextView(this.modelWithValidSetClaimed, goodAppendable);
  }


  ////////////////////////// Tests for the Constructor ////////////////////////////

  // testing properties of a view with the constructor that was constructed properly when given a
  // model
  @Test
  public void testValidConstructorOnlyModel() {

    assertEquals(
            "1EQ 1EO 1ED\n" +
                    "1SQ 1SO 1SD\n" +
                    "1FQ 1FO 1FD",
            this.viewWithDefaultModel.toString());
  }

  // testing for an exception when the view is passed a model state which is null
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    SetGameView view = new SetGameTextView(null);
  }

  @Test
  public void testValidConstructorModelAndAppendable() {
    // since the fields are private, we must use the public methods in the interface to ensure
    // that the object was constructed properly (this is a little redundant with the tests for
    // the public methods in the MarbleSolitaireView interface)
    assertEquals(
            "1EQ 1EO 1ED\n" +
                    "1SQ 1SO 1SD\n" +
                    "1FQ 1FO 1FD",
            this.viewWithModelAndAppendable.toString());
  }

  // testing for an exception when the view is passed a model state which is null but an
  // appendable which is not null
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor2() {
    SetGameView view = new SetGameTextView(null, goodAppendable);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor3() {
    SetGameView view = new SetGameTextView(this.defaultModel, null);
  }

  // testing for an exception when the view is passed a model state and an appendable which are
  // both null
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor4() {
    SetGameView view = new SetGameTextView(null, null);
  }

  ////////////////////////// Tests for toString  ////////////////////////////

  // testing the toString method on a view containing a model where a valid move has been done
  // and there are now more than 1 empty slots
  @Test
  public void testToStringForViewWithValidMoveDone() {
    assertEquals(
            "2EQ 2EO 2ED\n" +
                    "1SQ 1SO 1SD\n" +
                    "1FQ 1FO 1FD",
            this.viewWithValidSetClaimed.toString());
  }

  @Test
  public void testToStringForViewWithModelAndAppendable() {
    assertEquals(
            "1EQ 1EO 1ED\n" +
                    "1SQ 1SO 1SD\n" +
                    "1FQ 1FO 1FD",
            this.viewWithModelAndAppendable.toString());
  }

  @Test
  public void testToStringForViewWithModelAndAppendableWithValidSetClaimed() {
    assertEquals(
            "1EQ 1EO 1ED\n" +
                    "1SQ 1SO 1SD\n" +
                    "1FQ 1FO 1FD",
            this.viewWithModelAndAppendableWithValidSetClaimed.toString());
  }

  ////////////////////////// Tests for renderGrid   ////////////////////////////

  // testing that renderGrid works correctly at the start of a game
  @Test
  public void testRenderGrid() throws IOException {
    viewWithModelAndAppendable.renderGrid();
    assertEquals("1EQ 1EO 1ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n", goodAppendable.toString());
  }

  @Test
  public void testRenderGridAfterClaimedSet() throws IOException {
    viewWithModelAndAppendableWithValidSetClaimed.renderGrid();
    assertEquals("1EQ 1EO 1ED\n" +
            "1SQ 1SO 1SD\n" +
            "1FQ 1FO 1FD\n", goodAppendable.toString());
  }

  @Test(expected = IOException.class)
  public void testFailedRenderGrid() throws IOException {
    SetGameView view = new SetGameTextView<>(modelWithValidSetClaimed,
            badAppendable);
    view.renderGrid();
  }


  //////////////////////////// Tests for renderMessage   ////////////////////////////

  @Test
  public void testRenderMessage() throws IOException {
    Appendable ap = new StringBuilder("");
    SetGameView view = new SetGameTextView(defaultModel, ap);
    view.renderMessage("wefhuiwefiuh");
    assertEquals("", goodAppendable.toString());
  }

  // testing that renderMessage works with a word
  @Test
  public void testRenderMessage2() throws IOException {
    SetGameView view = new SetGameTextView(defaultModel,
            goodAppendable);
    view.renderMessage("hi!");
    assertEquals("hi!", goodAppendable.toString());
  }

  // testing that renderMessage works with a full sentence
  @Test
  public void testRenderMessage3() throws IOException {
    SetGameView view = new SetGameTextView(defaultModel,
            goodAppendable);
    view.renderMessage("OOD class");
    assertEquals("OOD class", goodAppendable.toString());
  }

  // testing for an exception when renderMessage has trouble transmitting an output
  @Test(expected = IOException.class)
  public void testFailedRenderMessage() throws IOException {
    SetGameView view = new SetGameTextView(modelWithValidSetClaimed,
            badAppendable);
    view.renderMessage("hi");
  }
}