package cs3500.set;

import java.io.InputStreamReader;

import cs3500.set.controller.SetGameController;
import cs3500.set.controller.SetGameControllerImpl;
import cs3500.set.model.hw02.SetGameModel;
import cs3500.set.model.hw02.SetThreeGameModel;
import cs3500.set.model.hw03.GeneralSetGameModel;
import cs3500.set.view.SetGameTextView;
import cs3500.set.view.SetGameView;

/**
 * This class holds the main method and creates different styles of a Set Game.
 */
public final class SetGame {

  /**
   * This method takes in string arguments and plays the respective game based on if they enter
   *        three or general.
   * @param args string arguments that dictate what game is played
   */
  public static void main(String[] args) {


    for (String s : args) {
      if (s.equals("three")) {
        SetGameModel model = new SetThreeGameModel();
        SetGameView view = new SetGameTextView(model);
        SetGameController controller = new SetGameControllerImpl(model, view,
                new InputStreamReader(System.in));
        controller.playGame();
      } else if (s.equals("general")) {
        SetGameModel model = new GeneralSetGameModel();
        SetGameView view = new SetGameTextView(model);
        SetGameController controller = new SetGameControllerImpl(model, view,
                new InputStreamReader(System.in));
        controller.playGame();

      }
    }


  }
}
