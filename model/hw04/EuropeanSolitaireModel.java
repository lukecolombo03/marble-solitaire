package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.Utils;

/**
 * Similar to EnglishSolitaireModel, but it has the corners filled in, to create an octagon shape.
 */
public class EuropeanSolitaireModel extends AMarbleSolitaireModel implements MarbleSolitaireModel {

  /**
   * Constructor with no arguments: defaults to a board with armthickness of 3 and empty slot in the
   * center.
   */
  public EuropeanSolitaireModel() {
    super(3, 3, 3);
    this.addEuropeanSlots();

  }

  /**
   * Constructor with one argument to specify the arm thickness. Has an empty slot in the center.
   * @param armThickness given arm thickness.
   */
  public EuropeanSolitaireModel(int armThickness) {
    super(armThickness, ((3 * armThickness) - 3) / 2, ((3 * armThickness) - 3) / 2);
    this.addEuropeanSlots();
    if (armThickness < 0 || (armThickness % 2 == 0)) {
      throw new IllegalArgumentException("Arm thickness must be a positive odd number");
    }
  }

  /**
   * Constructor with two arguments to specify where the empty slot should go. Arm thickness of 3.
   * @param sRow row of the empty slot.
   * @param sCol column of the empty slot.
   */
  public EuropeanSolitaireModel(int sRow, int sCol) {
    super(3, sRow, sCol);
    this.addEuropeanSlots();
    if (new Utils().invalidSlotEuropean(sRow, sCol, armThickness)) {
      throw new IllegalArgumentException("Invalid empty cell position (" +
              sRow + " , " + sCol + " )");
    }
  }

  /**
   * Constructor with three arguments to specify both the arm thickness and position of empty slot.
   * @param armThickness given arm thickness.
   * @param sRow row of empty slot.
   * @param sCol column of empty slot.
   */
  public EuropeanSolitaireModel(int armThickness, int sRow, int sCol) {
    super(armThickness, sRow, sCol);
    this.addEuropeanSlots();
    if (new Utils().invalidSlotEuropean(sRow, sCol, armThickness)) {
      throw new IllegalArgumentException("Invalid empty cell position (" +
              sRow + " , " + sCol + " )");
    }
    if (armThickness < 0 || (armThickness % 2 == 0)) {
      throw new IllegalArgumentException("Arm thickness must be a positive odd number");
    }
  }

  private void addEuropeanSlots() {
    int extraWidth = ((this.armThickness - 1) / 2);
    int totalLength = (3 * this.armThickness) - 2;
    if (this.armThickness > 1) {
      for (int row = extraWidth; row < extraWidth + extraWidth; row++) {
        for (int col = extraWidth; col < extraWidth + extraWidth; col++) {
          this.board[row][col] = SlotState.Marble;
        }
      }
      for (int row = totalLength - (extraWidth * 2); row < totalLength - extraWidth; row++) {
        for (int col = extraWidth; col < extraWidth + extraWidth; col++) {
          this.board[row][col] = SlotState.Marble;
        }
      }
      for (int row = extraWidth; row < extraWidth + extraWidth; row++) {
        for (int col = totalLength - (extraWidth * 2); col < totalLength - extraWidth; col++) {
          this.board[row][col] = SlotState.Marble;
        }
      }
      for (int row = totalLength - (extraWidth * 2); row < totalLength - extraWidth; row++) {
        for (int col = totalLength - (extraWidth * 2); col < totalLength - extraWidth; col++) {
          this.board[row][col] = SlotState.Marble;
        }
      }
    }
  }




}
