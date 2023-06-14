package cs3500.set.model.hw03;

import java.util.List;

import cs3500.set.model.hw02.Card;
import cs3500.set.model.hw02.Coord;

/**
 * This class creates a version of the set game to include a general set gamedesign. It can create
 *          games of different heights and widths and even adds a row at the bottom of the grid
 *          if there are no sets present.
 */
public class GeneralSetGameModel extends SetGameModelAbstract<Card> {

  public GeneralSetGameModel() {
    super();
  }


  /**
   * Begins the game using the cards given by the deck creates a grid specified
   *        by the height and width parameters.
   * It is also important to note that if there are no valid sets present on the grid, the game
   *        will add a row of cards on the bottom of the grid. If there are not enough cards in the
   *        deck to do this, the game will automatically be over.
   * @param deck the list of cards in the order they will be played
   * @param height the height of the board for this game
   * @param width the width of the board for this game
   * @throws IllegalArgumentException if height is smaller than or equal to 0,
   *        if width is less than 0, if the deck is null, if the initial deck has less cards than
   *        needed to fill the grid, or if the given height and width do not create a grid that can
   *        hold 3 cards (height * width is not equal to 3 or greater).
   */

  @Override
  public void startGameWithDeck(List deck, int height, int width)
          throws IllegalArgumentException {

    if (height <= 0 || width <= 0 || deck == null
            || deck.size() < height * width || height * width < CARDSINSET) {
      throw new IllegalArgumentException("Invalid game construction");
    }

    this.start = true;
    this.deck = deck;
    this.score = 0;
    this.height = height;
    this.width = width;
    this.grid = new Card[this.height][this.width];

    for (int row = 0; row < this.height; row++) {
      for (int column = 0; column < this.width; column++) {
        this.grid[row][column] = this.deck.remove(0);
      }
    }

    this.addRow();

  }

  private void addRow() {

    if (!anySetsPresent()) {

      this.height += 1;

      Card[][] newGrid = new Card[this.height][this.width];

      for (int row = 0; row < this.height - 1; row++) {
        newGrid[row] = this.grid[row];
      }

      for (int column = 0; column < this.width; column++) {
        newGrid[this.height - 1][column] = this.deck.get(0);
        this.deck.remove(0);
      }

      this.grid = newGrid;
    }

  }

  /**
   * If the cards at the specified coordinates form a valid set, claim it,
   *          and replace those cards with new cards from the deck, if possible.
   * It is also important to note that if there are no valid sets present on the new grid after
   *          a set is claimed, the game will add a row of cards on the bottom of the grid.
   *          If there are not enough cards in the deck to do this, the game will automatically
   *          be over.
   * @param coord1 the coordinates of the first card
   * @param coord2 the coordinates of the second card
   * @param coord3 the coordinates of the third card
   */

  @Override
  public void claimSet(Coord coord1, Coord coord2, Coord coord3) {
    super.claimSet(coord1,coord2,coord3);
    this.addRow();
  }

  @Override
  public boolean isGameOver() {

    return this.start && (!this.anySetsPresent()
            || this.nullCards() && deck.size() < this.width);

  }


}
