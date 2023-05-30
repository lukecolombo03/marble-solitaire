package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * Implementation of the controller. Takes user input and communicates this to the view and model.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private final MarbleSolitaireView view;
  private final MarbleSolitaireModel model;
  private final Readable input;

  /**
   * Constructor taking in a model, view, and input.
   *
   * @param model a game model.
   * @param view  a game view.
   * @param input user input, in the form of a Readable.
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view,
                                       Readable input) {
    if (view == null) {
      throw new IllegalArgumentException("View is null");
    } else {
      this.view = view;
    }
    if (model == null) {
      throw new IllegalArgumentException("Model is null");
    } else {
      this.model = model;
    }
    if (input == null) {
      throw new IllegalArgumentException("Input is null");
    } else {
      this.input = input;
    }
  }


  /**
   * Run a new game in the following sequence. Every transmission should end with "\n".
   * 1. Render the current state of the game (using view).
   * 2. Transmit "Score: N" (using view).
   * 3. If the game is ongoing, obtain the next user input from this.input.
   * A user input consists of four values: fromRow, fromCol, toRow, toCol.
   * 4. Pass this info to the model, which actually does the move.
   * 5. If the game is over, transmit the following in order: "Game over!", the final game state,
   * and the message "Score: N". The method then ends. If the game isn't over, the method continues.
   *
   * @throws IllegalStateException if the controller is unable to read input or transmit output.
   */
  @Override
  public void playGame() throws IllegalStateException {
    Scanner scanner = new Scanner(this.input);
    ArrayList<Integer> inputs = new ArrayList<>();
    int current;

    while (!this.model.isGameOver()) {
      this.initialRender();
      while (inputs.size() < 4) {
        if (scanner.hasNext("q") || scanner.hasNext("Q")) {
          try {
            this.view.renderMessage("Game quit!\n");
            this.view.renderMessage("State of game when quit:\n");
            this.view.renderBoard();
            this.view.renderMessage("Score: " + this.model.getScore() + "\n");
          } catch (IOException d) {
            throw new IllegalStateException("Unable to read or transmit to input/output.");
          }
          return;
        } else if (!scanner.hasNext() && !this.model.isGameOver()) {    //there's no inputs left
          throw new IllegalStateException("Ran out of inputs");
        } else if (scanner.hasNextInt()) {                    // there's another int left in inputs
          current = scanner.nextInt();
          if (current > 0) {
            inputs.add(current - 1);
          }
        } else if (scanner.hasNext()) {                     // no int, but something else - invalid
          try {
            this.view.renderMessage("Invalid input, please re-enter.\n");
            scanner.next();
          } catch (IOException e) {
            throw new IllegalStateException("Unable to read or transmit to input/output.");
          }
        }
      }
      this.afterFourInputs(inputs, scanner);
    }
    this.endGame();
  }

  /**
   * Renders the board, before any inputs.
   */
  private void initialRender() {
    try {
      this.view.renderBoard();
      this.view.renderMessage("Score: " + this.model.getScore() + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Unable to read or transmit to input/output");
    }
  }

  /**
   * Does 4 things:
   *  1. Tries to make a move.
   *  2. Renders the board and score.
   *  3. Clears inputs.
   *  4. Throws IllegalStateException if the scanner ran out of inputs.
   * @param inputs inputs from user.
   * @param scanner the scanner from playGame.
   * @throws IllegalStateException if the scanner runs out of inputs.
   */
  private void afterFourInputs(ArrayList<Integer> inputs, Scanner scanner)
          throws IllegalStateException {
    try {
      this.model.move(inputs.get(0), inputs.get(1), inputs.get(2), inputs.get(3));
      try {
        this.view.renderBoard();
        this.view.renderMessage("Score: " + this.model.getScore() + "\n");
      } catch (IOException e) {
        throw new IllegalStateException("Unable to read or transmit to input/output");
      }
    } catch (IllegalArgumentException f) {
      try {
        this.view.renderMessage("Invalid move. Play again. To be valid, a move must jump " +
                "orthogonally over another marble, onto an empty space.\n");
      } catch (IOException g) {
        throw new IllegalStateException("Unable to read or transmit to input/output.");
      }
    }
    inputs.clear();
    if (!scanner.hasNext() && !this.model.isGameOver()) {
      throw new IllegalStateException("Ran out of inputs");
    }
  }

  /**
   * Renders the game after it has ended.
   */
  private void endGame() {
    try {
      this.view.renderMessage("Game over!\n");
      this.view.renderBoard();
      this.view.renderMessage("Score: " + this.model.getScore() + "\n");
    } catch (IOException c) {
      throw new IllegalStateException("Unable to read or transmit to input/output.");
    }
  }



}







