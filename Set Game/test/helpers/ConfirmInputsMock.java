package helpers;

import java.util.List;

import cs3500.set.model.hw02.Coord;
import cs3500.set.model.hw02.SetGameModel;

/**
 * This mock class represents a SetGameModel that finds all the transmissions via a log.
 */
public class ConfirmInputsMock implements SetGameModel {

  private StringBuilder log;

  /**
   * This contructor creates the mock with a given log.
   * @param log a string builder object that helps store the transmissions.
   */
  public ConfirmInputsMock(StringBuilder log) {

    this.log = log;

  }


  @Override
  public void claimSet(Coord coord1, Coord coord2, Coord coord3) {

    this.log.append("A set claim was attempted at coordinates: "
            + coord1.toString() + ", " + coord2.toString()
            + ", " + coord3.toString() + ".\n");

  }

  @Override
  public void startGameWithDeck(List deck, int height, int width) throws IllegalArgumentException {
    this.log.append("A new game creation was attempted with the deck of cards "
            + deck.toString() + ", height: " + height
            + ", and width: " + width + ".\n");

  }

  @Override
  public int getWidth() throws IllegalStateException {
    this.log.append("Controller obtained the width from the model.\n");
    return 0;
  }

  @Override
  public int getHeight() throws IllegalStateException {
    this.log.append("Controller obtained the height from the model.\n");
    return 0;
  }

  @Override
  public int getScore() throws IllegalStateException {
    this.log.append("Controller obtained the score from the model.\n");
    return 0;
  }

  @Override
  public boolean anySetsPresent() {
    this.log.append("Controller attempted to determine if any sets could be created.\n");
    return false;
  }

  @Override
  public boolean isValidSet(Coord coord1, Coord coord2, Coord coord3) throws
          IllegalArgumentException {
    this.log.append("Controller attempted to determine if a set could be created from coordinates: "
            + coord1.toString() + ", " + coord2.toString()
            + ", " + coord3.toString() + ".\n");
    return false;
  }

  @Override
  public Object getCardAtCoord(int row, int col) {
    this.log.append("Controller attempted to obtain card at row:" + row + ", and column: "
            + col + ".\n");
    return null;
  }

  @Override
  public Object getCardAtCoord(Coord coord) {
    this.log.append("Controller attempted to obtain card coordinate:"
            + coord.toString() + ".\n");
    return null;
  }

  @Override
  public boolean isGameOver() {
    this.log.append("Controller asked if the game is over.\n");
    return false;
  }

  @Override
  public List getCompleteDeck() {
    this.log.append("Controller attempted to obtain a full deck of cards.\n");
    return null;
  }
}
