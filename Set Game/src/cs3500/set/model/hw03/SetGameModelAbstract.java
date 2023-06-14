package cs3500.set.model.hw03;

import java.util.ArrayList;
import java.util.List;

import cs3500.set.model.hw02.Card;
import cs3500.set.model.hw02.Coord;
import cs3500.set.model.hw02.Count;
import cs3500.set.model.hw02.Filling;
import cs3500.set.model.hw02.SetGameModel;
import cs3500.set.model.hw02.Shape;

/**
 * This class acts as an abstract class for the GeneralSetGameModel and SetThreeGameModel classes.
 * @param <C> The card type used for the current game
 */

public abstract class SetGameModelAbstract<C> implements SetGameModel<Card> {

  /**
   * All of these fields are used ONLY in this class and its subclasses, so it makes sense to use
   * protected fields instead of public fields. These fields are also used in the same manner for
   * both subclasses, so they are in this abstract class to reduce code duplication. It also makes
   * it easier to use these fields rather than initializing it with super and keeping these
   * variables private.
   */
  protected int score;
  protected List<Card> deck;
  protected Card[][] grid;
  protected int height;
  protected int width;

  protected boolean start;
  protected final int CARDSINSET = 3;

  public SetGameModelAbstract() {
    this.score = 0;
    this.start = false;
  }

  @Override
  public void claimSet(Coord coord1, Coord coord2, Coord coord3) {
    if (!start) {
      throw new IllegalStateException("The game has not yet started.");
    } else if (this.isGameOver()) {
      throw new IllegalStateException("The game has ended.");
    } else if (!this.isValidSet(coord1, coord2, coord3)) {
      throw new IllegalArgumentException("The coordinates entered are not a set.");
    }

    this.score++;

    Coord[] coords = {coord1, coord2, coord3};

    for (Coord coord : coords) {
      this.grid[coord.row][coord.col] = null;
      if (deck.size() > 0) {
        this.grid[coord.row][coord.col] = this.deck.get(0);
        this.deck.remove(0);
      }
    }
  }

  @Override
  public abstract void startGameWithDeck(List<Card> deck, int height, int width)
          throws IllegalArgumentException;

  @Override
  public abstract boolean isGameOver();


  @Override
  public int getScore() throws IllegalStateException {
    if (!this.start) {
      throw new IllegalStateException("The game has not been started.");
    }
    return this.score;
  }

  @Override
  public boolean anySetsPresent() {

    if (!this.start) {
      throw new IllegalStateException("The game has not been started.");
    }


    List<Coord> tempList = new ArrayList<>();

    for (int row = 0; row < this.height; row++) {
      for (int column = 0; column < this.width; column++) {
        tempList.add(new Coord(row, column));
      }
    }

    for (int i = 0; i < tempList.size(); i++) {
      Coord coord1 = tempList.get(i);
      for (int j = i + 1; j < tempList.size(); j++) {
        Coord coord2 = tempList.get(j);
        for (int k = j + 1; k < tempList.size(); k++) {
          Coord coord3 = tempList.get(k);
          if (this.isValidSet(coord1, coord2, coord3)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  @Override
  public boolean isValidSet(Coord coord1, Coord coord2, Coord coord3)
          throws IllegalArgumentException {

    if (!this.isValidCoord(coord1, coord2, coord3)) {
      throw new IllegalArgumentException("Invalid coordinates were given.");
    }

    return this.isValidSetByCount(coord1, coord2, coord3) &&
            this.isValidSetByFilling(coord1, coord2, coord3) &&
            this.isValidSetByShape(coord1, coord2, coord3);
  }

  /**
   * This method supplements the isValidSet method by determining
   * if a set has been made by just looking at the count.
   *
   * @param coord1 coordinate of Card 1
   * @param coord2 coordinate of Card 1
   * @param coord3 coordinate of Card 1
   * @return true if the three cards all have the
   *        same count or all have different counts and false otherwise..
   */
  private boolean isValidSetByCount(Coord coord1, Coord coord2, Coord coord3) {
    return (this.getCardAtCoord(coord2) == null || this.getCardAtCoord(coord3) == null ||
            this.getCardAtCoord(coord1) == null) ||
            (this.getCardAtCoord(coord1).getCount() != this.getCardAtCoord(coord2).getCount()
                    && this.getCardAtCoord(coord1).getCount() !=
                    this.getCardAtCoord(coord3).getCount()
                    && this.getCardAtCoord(coord2).getCount() !=
                    this.getCardAtCoord(coord3).getCount()) ||
            (this.getCardAtCoord(coord1).getCount() == this.getCardAtCoord(coord2).getCount()
                    && this.getCardAtCoord(coord2).getCount() ==
                    this.getCardAtCoord(coord3).getCount());
  }

  /**
   * This method supplements the isValidSet method by determining
   *        if a set has been made by just looking at the filling.
   * @param coord1 coordinate of Card 1
   * @param coord2 coordinate of Card 1
   * @param coord3 coordinate of Card 1
   * @return true if the three cards all have the
   *        same filling or all have different fillings and false otherwise..
   */
  private boolean isValidSetByFilling(Coord coord1, Coord coord2, Coord coord3) {
    return (this.getCardAtCoord(coord2) == null || this.getCardAtCoord(coord3) == null ||
            this.getCardAtCoord(coord1) == null) ||
            (this.getCardAtCoord(coord1).getFilling() != this.getCardAtCoord(coord2).getFilling()
                    && this.getCardAtCoord(coord1).getFilling()
                    != this.getCardAtCoord(coord3).getFilling()
                    && this.getCardAtCoord(coord2).getFilling()
                    != this.getCardAtCoord(coord3).getFilling())
            ||
            (this.getCardAtCoord(coord1).getFilling() == this.getCardAtCoord(coord2).getFilling()
                    && this.getCardAtCoord(coord2).getFilling() ==
                    this.getCardAtCoord(coord3).getFilling());
  }

  /**
   * This method supplements the isValidSet method by determining
   *        if a set has been made by just looking at the shape.
   * @param coord1 coordinate of Card 1
   * @param coord2 coordinate of Card 1
   * @param coord3 coordinate of Card 1
   * @return true if the three cards all have the
   *        same shape or all have different shape and false otherwise.
   */
  private boolean isValidSetByShape(Coord coord1, Coord coord2, Coord coord3) {
    return (this.getCardAtCoord(coord2) == null || this.getCardAtCoord(coord3) == null ||
            this.getCardAtCoord(coord1) == null) ||
            (this.getCardAtCoord(coord1).getShape() != this.getCardAtCoord(coord2).getShape()
                    && this.getCardAtCoord(coord1).getShape()
                    != this.getCardAtCoord(coord3).getShape()
                    && this.getCardAtCoord(coord2).getShape()
                    != this.getCardAtCoord(coord3).getShape())
            ||
            (this.getCardAtCoord(coord1).getShape() == this.getCardAtCoord(coord2).getShape()
                    && this.getCardAtCoord(coord2).getShape() ==
                    this.getCardAtCoord(coord3).getShape());
  }

  /**
   * This method supplements the isValidSet method by determining
   *        if the coordinates given are valid and fit in the
   *        restraints of the game size.
   * @param coord1 coordinate of Card 1
   * @param coord2 coordinate of Card 1
   * @param coord3 coordinate of Card 1
   * @return true if the three cards have coordinates less than 3
   *        but greater than 0 and false otherwise
   */
  private boolean isValidCoord(Coord coord1, Coord coord2, Coord coord3) {
    return (coord1 != null && coord2 != null && coord3 != null &&
            coord1.row < this.height && coord1.row >= 0 &&
            coord2.row < this.height && coord2.row >= 0 &&
            coord3.row < this.height && coord3.row >= 0 &&
            coord1.col < this.width && coord1.col >= 0 &&
            coord2.col < this.width && coord2.col >= 0 &&
            coord3.col < this.width && coord3.col >= 0); //check if null first

  }

  /**
   * This method finds if the given coordinate is a valid coordinate for the game.
   * @param coord1 a Coordinate object
   * @return true if the coordinate is not null and fits in the bounds of the game
   */
  private boolean isValidCoord(Coord coord1) {
    return coord1 != null &&
            coord1.row < height && coord1.row >= 0 &&
            coord1.col < width && coord1.col >= 0;


  }

  /**
   * This method finds if any cards on the grid are null.
   * @return true if there are any cards that are null and false otherwise
   */

  protected boolean nullCards() {

    for (int row = 0; row < height; row++) {
      for (int column = 0; column < width; column++) {
        if (this.getCardAtCoord(row, column) == null) {
          return true;
        }
      }
    }
    return false;

  }

  @Override
  public Card getCardAtCoord(int row, int col) {

    if (!this.start) {
      throw new IllegalStateException("Game has not started yet.");
    } else if (row < 0 || row >= this.height || col < 0 || col >= this.width) {
      throw new IllegalArgumentException("Invalid coordinate was provided.");
    }

    return grid[row][col];
  }

  @Override
  public Card getCardAtCoord(Coord coord) {

    if (!this.start) {
      throw new IllegalStateException("Game has not started yet.");
    } else if (!this.isValidCoord(coord)) {
      throw new IllegalArgumentException("Invalid coordinate was provided.");
    }

    return grid[coord.row][coord.col];

  }

  @Override
  public List<Card> getCompleteDeck() {
    List<Card> completeDeck = new ArrayList<>();

    for (Count c : Count.values()) {
      for (Filling f : Filling.values()) {
        for (Shape s : Shape.values()) {
          completeDeck.add(new Card(c, f, s));
        }
      }
    }
    return completeDeck;
  }

  @Override
  public int getWidth() throws IllegalStateException {
    if (!this.start) {
      throw new IllegalStateException("The game has not been started.");
    }
    return this.width;
  }

  @Override
  public int getHeight() throws IllegalStateException {
    if (!this.start) {
      throw new IllegalStateException("The game has not been started.");
    }
    return this.height;
  }
}
