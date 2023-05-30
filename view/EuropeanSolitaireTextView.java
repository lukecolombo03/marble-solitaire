package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * View for the European board.
 */
public class EuropeanSolitaireTextView extends AbstractView implements MarbleSolitaireView {

  /**
   * Constructor that takes in a model and defaults the destination to System.out.
   * @param model a game model.
   */
  public EuropeanSolitaireTextView(MarbleSolitaireModelState model) {
    super(model);
  }

  /**
   * Constructor that takes in both a model and destination.
   * @param model a game model.
   * @param destination an appendable destination for the view to transmit to.
   */
  public EuropeanSolitaireTextView(MarbleSolitaireModelState model, Appendable destination) {
    super(model, destination);
  }


  /**
   * Represents a MarbleSolitaireModel as a string board.
   *
   * @return a string representation with a space for invalid slots, "O" for marble slots,
   * and "_" for empty slots.
   */
  public String toString() {
    StringBuilder result = new StringBuilder();
    int size = model.getBoardSize();
    int armThickness = (size + 2) / 3;
    int y = (armThickness - 1) / 2; // 6 of these make up the entire size
    int twoY = 2 * y;


    for (int row = 0; row < size; row++) {
      if (row < y || row > (size - y - 1)) {
        for (int counter = 0; counter < twoY * 2; counter++) {
          result.append(" ");
        }
      } else if (row < twoY || row > (size - twoY - 1)) {
        for (int counter = 0; counter < twoY; counter++) {
          result.append(" ");
        }
      }
      for (int col = 0; col < size; col++) { // we're in a specific row, now going column by column
        switch (model.getSlotAt(row, col)) {
          case Marble:                      // we're at a specific slot and it's invalid
            if (col == size - 1) {
              result.append("O");
            } else {
              result.append("O ");
            }
            break;
          case Empty:
            if (col == size - 1) {
              result.append("_");
            } else {
              result.append("_ ");
            }
            break;
          case Invalid:
            result.append("");
            break;
          default:
            // SlotState is an Enum, so the default will never happen.
        }
      }
      if (row < size - 1) {
        result.append("\n");
      }
    }
    return result.toString();
  }



}
