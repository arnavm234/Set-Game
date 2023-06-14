package cs3500.set.view;

import java.io.IOException;

import cs3500.set.model.hw02.SetGameModelState;

/**
 * This class uses the current SetGameModelState to create a more readable version
 * of the grid of cards. The 2-D array is shown in a more concise form and through
 * strings.
 */
public class SetGameTextView<C> implements SetGameView {

  private SetGameModelState<C> state;
  private Appendable out;

  /**
   * This is the default constructor of the set game as a text view. It only takes in a model.
   *
   * @param state the model
   * @throws IllegalArgumentException if the state (model) is null
   */
  public SetGameTextView(SetGameModelState<C> state) throws IllegalArgumentException {
    if (state == null) {
      throw new IllegalArgumentException("State or Appendable object is null");
    }
    this.state = state;
    this.out = System.out;
  }

  /**
   * This constructor takes in the state (model) as well as an appendable.
   *
   * @param state the model
   * @param out   an Appendable object
   * @throws IllegalArgumentException if the model or appendable are null
   */
  public SetGameTextView(SetGameModelState<C> state, Appendable out)
          throws IllegalArgumentException {
    if (state == null || out == null) {
      throw new IllegalArgumentException("State or Appendable object is null");
    }

    this.state = state;
    this.out = out;
  }


  @Override
  public String toString() {
    String str = "";
    for (int row = 0; row < state.getHeight(); row++) {
      for (int column = 0; column < state.getWidth(); column++) {

        if (this.state.getCardAtCoord(row, column).toString() == null) {
          str += "   ";
        } else {
          str += this.state.getCardAtCoord(row, column).toString();
        }

        if (column < state.getWidth() - 1) {
          str += " ";
        }

      }
      if (row < state.getHeight() - 1) {
        str += "\n";
      }
    }
    return str;
  }

  @Override
  public void renderGrid() throws IOException {

    this.out.append(this.toString() + System.lineSeparator());

  }

  @Override
  public void renderMessage(String message) throws IOException {

    this.out.append(message);

  }
}

