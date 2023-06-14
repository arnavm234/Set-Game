package cs3500.set.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import cs3500.set.model.hw02.Coord;
import cs3500.set.model.hw02.SetGameModel;
import cs3500.set.view.SetGameView;

/**
 * This class represents an implementation of a set game.
 */

public class SetGameControllerImpl implements SetGameController {

  private final SetGameModel model;
  private final SetGameView view;
  private final Readable in;
  private final ArrayList<Integer> dimensions;
  private final ArrayList<Integer> coords;
  private boolean started = false;

  /**
   * This class represents a controller for a game of Set.
   * @param model the SetGameModel object
   * @param view the SetGameView in which the user can see the game
   * @param in an input or readable
   * @throws IllegalArgumentException if the model, view, or input is null
   */
  public SetGameControllerImpl(SetGameModel model, SetGameView view, Readable in)
          throws IllegalArgumentException {
    if (model == null || view == null || in == null) {
      throw new IllegalArgumentException("Invalid controller made.");
    }

    this.model = model;
    this.view = view;
    this.in = in;
    this.dimensions = new ArrayList<Integer>();
    this.coords = new ArrayList<Integer>();

  }

  /**
   * This method plays the game and allows the controller to display the view
   *          and work with the model.
   * @throws IllegalStateException if an IOException is thrown
   */
  public void playGame() throws IllegalStateException {


    Scanner sc = new Scanner(in);
    this.showStartingScreen();

    //check for valid heights and widths
    //try to see if a start game can validly be made

    while (!started) {

      String next;

      if (sc.hasNext()) {
        try {
          next = sc.next();
        } catch (NoSuchElementException e) {
          throw new IllegalStateException("No more inputs!");
        }

        // if the input is a q, then display the necessary information and then end the method
        if (next.equalsIgnoreCase("q")) {
          this.quitGameBeforeGameStarted();
          sc.close();
          return;
        }

        // checking all other inputs if is not a q, more specifically, looking for a valid number
        else {
          this.isValidDimension(next);
        }
      }

      // if the readable is empty and the user hasn't quit or ended the game, throw an exception

    }

    this.showGridAndScore();

    while (!this.model.isGameOver()) {

      if (sc.hasNext()) {
        String next = sc.next();

        // if the input is a q, then display the necessary information and then end the method
        if (next.equalsIgnoreCase("q")) {
          this.quitGame();
          sc.close();
          return;
        }

        // checking all other inputs if is not a q, more specifically, looking for a valid number
        else {
          this.isValidCoord(next);
        }
      }

      // if the readable is empty and the user hasn't quit or ended the game, throw an exception
      else {
        throw new IllegalStateException("No more inputs!");
      }

    }

    // if the while loop ends meaning the game is over and was not quit manually, then display the
    // necessary information at the end of the game
    this.showGameOver();
    sc.close();

  }


  private void showStartingScreen() throws IllegalStateException {
    try {
      this.view.renderMessage(
              "Welcome to this game of Set!"
                      + System.lineSeparator() + "First, enter 2 numbers that represent the height"
                      + " and width of the game with a space in the middle."
                      + System.lineSeparator() + "Then enter 3 pairs of coordinates with a space"
                      + " in between."
                      + System.lineSeparator() + "The numbers should begin at 1 to represent the "
                      + "first row/column, 2 to represent the second row/column, etc."
                      + System.lineSeparator() + "Enter q or Q at any moment to quit the game."
                      + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalStateException("Transmission of the starting screen to the view failed!");
    }
  }

  private void quitGame() throws IllegalStateException {
    try {
      this.view.renderMessage("Game quit!" + System.lineSeparator());
      this.view.renderMessage("State of game when quit:" + System.lineSeparator());
      this.showGridAndScore();
    } catch (IOException e) {
      throw new IllegalStateException("Transmission while quitting game failed!");
    }
  }

  private void quitGameBeforeGameStarted() throws IllegalStateException {
    try {
      this.view.renderMessage("Game quit!" + System.lineSeparator());
      this.view.renderMessage("Score: 0");
    } catch (IOException e) {
      throw new IllegalStateException("Transmission while quitting game failed!");
    }

  }

  private void isValidCoord(String next) throws IllegalStateException {

    // making sure to catch a NumberFormatException if we try to parse a string to an integer
    // when that's not possible
    try {

      // parse the string to an int, and it is greater than zero, then add num - 1 to
      // the list of numbers since we index at 0 but user input starts at 1, and see if a move can
      // be performed right now
      int num = Integer.parseInt(next);
      if (num > 0) {
        this.coords.add(num - 1);
        this.tryClaimSet();
      }

      // the number is not greater than 0 and thus represents an invalid input
      else {
        this.catchIOMessageException("Invalid Input: " + next + ". Please re-enter that value " +
                "again.");
      }
    }

    // the string cannot be parsed to a number and thus represents an invalid input
    catch (NumberFormatException e) {
      this.catchIOMessageException("Invalid Input: " + next + ". Please re-enter that value " +
              "again.");
    }
  }

  private void tryClaimSet() throws IllegalStateException {
    if (this.coords.size() >= 6) {

      int row1 = this.coords.remove(0);
      int col1 = this.coords.remove(0);
      int row2 = this.coords.remove(0);
      int col2 = this.coords.remove(0);
      int row3 = this.coords.remove(0);
      int col3 = this.coords.remove(0);

      Coord coord1 = new Coord(row1, col1);
      Coord coord2 = new Coord(row2, col2);
      Coord coord3 = new Coord(row3, col3);

      try {
        this.model.claimSet(coord1, coord2, coord3);
        if (!this.model.isGameOver()) {
          this.showGridAndScore();
        }
      } catch (IllegalArgumentException | IllegalStateException e) {
        this.catchIOMessageException("Invalid claim. Try again.");
      }
    }
  }

  private void isValidDimension(String next) throws IllegalStateException {

    // making sure to catch a NumberFormatException if we try to parse a string to an integer
    // when that's not possible
    try {

      // parse the string to an int, and it is greater than zero, then add num - 1 to
      // the list of numbers since we index at 0 but user input starts at 1, and see if a move can
      // be performed right now
      int num = Integer.parseInt(next);
      if (num > 0) {
        this.dimensions.add(num);
        this.tryStartGame();
      }

      // the number is not greater than 0 and thus represents an invalid input
      else {
        this.catchIOMessageException("Invalid Input: " + next + ". Please re-enter that value " +
                "again.");
      }
    }

    // the string cannot be parsed to a number and thus represents an invalid input
    catch (NumberFormatException e) {
      this.catchIOMessageException("Invalid Input: " + next + ". Please re-enter that value " +
              "again.");
    }
  }

  private void tryStartGame() {

    if (this.dimensions.size() >= 2) {
      // removing the 4 numbers for the move from the list and setting them to local variables
      // which can be passed to the move method
      int height = this.dimensions.remove(0);
      int width = this.dimensions.remove(0);

      try {
        this.model.startGameWithDeck(this.model.getCompleteDeck(), height, width);
        started = true;
      } catch (IllegalArgumentException e) {
        this.catchIOMessageException("Invalid height/width. Try again.");
      }

    }

  }

  private boolean canRunSampleMethod() {
    try {
      this.model.getHeight();
      return true;
    } catch (IllegalStateException e) {
      return false;
    }
  }


  private void showGameOver() throws IllegalStateException {
    try {
      this.view.renderMessage("Game over!" + System.lineSeparator());
      this.view.renderMessage("Score: " + this.model.getScore() + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalStateException("Transmission of the game over information to the view " +
              "failed!");
    }
  }


  private void showGridAndScore() throws IllegalStateException {
    try {
      this.view.renderGrid();
      this.view.renderMessage("Score: " + this.model.getScore() + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalStateException("Transmission of the grid and score failed");
    }
  }

  private void catchIOMessageException(String message) {
    try {
      this.view.renderMessage(message + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalStateException("Transmission to view failed");
    }
  }


}
