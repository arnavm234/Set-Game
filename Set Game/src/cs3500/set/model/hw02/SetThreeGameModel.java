package cs3500.set.model.hw02;

import java.util.List;

import cs3500.set.model.hw03.SetGameModelAbstract;

/**
 * This class creates a version of the set game to include
 * a 3 by 3 grid design. It still follows the general rules of Set.
 */
public class SetThreeGameModel extends SetGameModelAbstract<Card> {


  /**
   * This constructor initializes the game model with a 3 x 3 grid and sets
   * up the start flag and score counter.
   */

  public SetThreeGameModel() {
    super();
    this.height = 3;
    this.width = 3;
    this.grid = new Card[this.height][this.width];
  }

  /**
   * Begins the game using the cards given by the deck creates a grid specified
   *        by the height and width parameters.
   * @param deck the list of cards in the order they will be played
   * @param height the height of the board for this game
   * @param width the width of the board for this game
   * @throws IllegalArgumentException if height is not equal to 3, if width is not equal to 3,
   *        the deck is null or if the deck size is smaller than what fits onto a grid of 9 cards.
   */

  @Override
  public void startGameWithDeck(List deck, int height, int width)
          throws IllegalArgumentException {

    if (height != this.height || width != this.width || deck == null
            || deck.size() < this.height * this.width) {
      throw new IllegalArgumentException("Invalid game construction");
    }

    this.score = 0;
    this.start = true;
    this.deck = deck;

    for (int row = 0; row < this.height; row++) {
      for (int column = 0; column < this.width; column++) {
        this.grid[row][column] = this.deck.remove(0);
      }
    }

  }

  @Override
  public boolean isGameOver() {

    return this.start && (!this.anySetsPresent() ||
            this.nullCards() && deck.size() < CARDSINSET);
  }

}
