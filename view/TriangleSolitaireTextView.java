package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * The view for a triangle board.
 */
public class TriangleSolitaireTextView extends AbstractView
        implements MarbleSolitaireView {

  public TriangleSolitaireTextView(MarbleSolitaireModelState model) {
    super(model);
  }

  public TriangleSolitaireTextView(MarbleSolitaireModelState model, Appendable destination) {
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
    int lineLength = 0;
    int european = (armThickness - 1) / 2; // 6 of these make up the entire size

    for (int row = 0; row < size; row++) {
      for (int counter = 0; counter < (size - row); counter++) {
        result.append(" ");
      }
      for (int col = 0; col < size; col++) {
        switch (model.getSlotAt(row, col)) {
          case Marble:
            if (col >= row) {
              result.append("O");
            }
            else {
              result.append("O ");
            }
            break;
          case Empty:
            if (col >= row) {
              result.append("_");
            }
            else {
              result.append("_ ");
            }
            break;
          case Invalid:
            result.append("");
            break;
          default:
        }
      }
      if (row < size - 1) {
        result.append("\n");
      }
    }
    return result.toString();
  }
}

