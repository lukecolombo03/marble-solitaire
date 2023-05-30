package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Abstraction of the triangle, european, and english views.
 */
public abstract class AbstractView implements MarbleSolitaireView {
  protected MarbleSolitaireModelState model;
  protected Appendable destination;

  /**
   * Constructor that only takes in a model, defaults destination to System.out.
   * @param model a game model.
   */
  public AbstractView(MarbleSolitaireModelState model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    } else {
      this.model = model;
    }
    this.destination = System.out;
  }

  /**
   * Constructor that takes in both a model and destination.
   * @param model a game model.
   * @param destination where to transmit the view to.
   */
  public AbstractView(MarbleSolitaireModelState model, Appendable destination) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    } else {
      this.model = model;
    }
    if (destination == null) {
      throw new IllegalArgumentException("Object cannot be null");
    } else {
      this.destination = destination;
    }
  }


  /**
   * Helper method for toString that appends a "  ", " O", or " _" based on the SlotState of the
   * specified coordinate.
   *
   * @param row  the row coordinate of the model.
   * @param col  the column coordinate of the model.
   * @param sb a StringBuilder.
   */
  protected void addCharWithSpace(int row, int col, StringBuilder sb) {
    switch (model.getSlotAt(row, col)) {
      case Invalid:
        sb.append("  ");
        break;
      case Empty:
        sb.append("_ ");
        break;
      case Marble:
        sb.append("O ");
        break;
      default:
        // SlotState is an Enum, so the default will never happen.
    }
  }

  /**
   * Same as addCharWithSpace, but it appends either a " ", "O", or "_".
   * @param row row coordinate of model.
   * @param col column coordinate of model.
   * @param sb a StringBuilder.
   */
  protected void addCharNoSpace(int row, int col, StringBuilder sb) {
    switch(model.getSlotAt(row, col)) {
      case Invalid:
        sb.append(" ");
        break;
      case Empty:
        sb.append("_");
        break;
      case Marble:
        sb.append("O");
        break;
      default:
        // SlotState is an Enum, so the default will never happen.
    }
  }

  /**
   * Transmits the board output (as a string) to the destination. Also adds a new line to help with
   * playGame().
   * @throws IOException if there's an input/output error.
   */
  @Override
  public void renderBoard() throws IOException {
    this.destination.append(this.toString()).append("\n");
  }

  /**
   * Transmits a String message to the destination.
   * @param message the message to be transmitted
   * @throws IOException if there's an input/output error.
   */
  @Override
  public void renderMessage(String message) throws IOException {
    this.destination.append(message);
  }
}
