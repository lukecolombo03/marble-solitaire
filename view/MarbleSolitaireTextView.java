package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Represents a text view of the marble solitaire game. Works for both English and European models.
 */
public class MarbleSolitaireTextView extends AbstractView implements MarbleSolitaireView {

  public MarbleSolitaireTextView(MarbleSolitaireModelState model) {
    super(model);
  }

  public MarbleSolitaireTextView(MarbleSolitaireModelState model, Appendable destination) {
    super(model, destination);
  }


  /**
   * Represents a MarbleSolitaireModel as a string board.
   *
   * @return a string representation with a space for invalid slots, "O" for marble slots,
   *     and "_" for empty slots.
   */
  public String toString() {
    StringBuilder result = new StringBuilder();
    int size = model.getBoardSize();
    int armThickness = (size + 2) / 3;
    int y = (armThickness - 1) / 2; // 6 of these make up the entire size
    int twoY = 2 * y;


    for (int row = 0; row < size; row++) {
      if (row < twoY || row > (size - twoY - 1)) {
        for (int counter = 0; counter < twoY * 2; counter++) {
          result.append(" ");
        }
      }
      for (int col = 0; col < size; col++) { // we're in a specific row, now going column by column
        switch (model.getSlotAt(row, col)) {
          case Marble:                      // we're at a specific slot and it's invalid
            if (row < twoY || row > (size - twoY - 1)) {
              if (col >= size - twoY - 1) {
                result.append("O");
              } else {
                result.append("O ");
              }
            } else {
              if (col >= size - 1) {
                result.append("O");
              } else {
                result.append("O ");
              }
            }
            break;
          case Empty:
            if (row < twoY || row > (size - twoY - 1)) {
              if (col >= size - twoY - 1) {
                result.append("_");
              } else {
                result.append("_ ");
              }
            } else {
              if (col >= size - 1) {
                result.append("_");
              } else {
                result.append("_ ");
              }
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

