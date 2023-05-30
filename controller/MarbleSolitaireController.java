package cs3500.marblesolitaire.controller;


/**
 * Represents a controller to mediate the view and model.
 */
public interface MarbleSolitaireController {

  /**
   * Play a new game of Marble Solitaire.
   * @throws IllegalStateException ONLY if the controller is unable to read input or transmit
   *         output.
   */
  void playGame() throws IllegalStateException;

}
