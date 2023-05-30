package cs3500.marblesolitaire.model.hw02;

/**
 * Utilities class.
 */
public class Utils {

  /**
   * Checks if (row, col) is an invalid slot. Used in EnglishSolitaireModel constructors.
   *
   * @param row          the given row.
   * @param col          given column.
   * @param armThickness arm thickness of the board.
   * @return whether (row, col) is invalid in a board with armThickness arm thickness.
   */
  public boolean invalidSlotEnglish(int row, int col, int armThickness) {
    return (((row < (armThickness - 1)) && (col < (armThickness - 1))) || // top left
            ((row < (armThickness - 1)) && (col > ((2 * armThickness) - 2))) || // top right
            ((row > ((2 * armThickness) - 2)) && (col < (armThickness - 1))) || // bottom left
            ((row > ((2 * armThickness) - 2)) && (col > ((2 * armThickness) - 2)))); // bottom right
  }

  /**
   * Checks if (row, col) is an invalid slot. Used in EuropeanSolitaireModel constructors.
   *
   * @param row          the given row.
   * @param col          the given column.
   * @param armThickness arm thickness of the board.
   * @return whether (row, col) is invalid in a board with armThickness arm thickness.
   */
  public boolean invalidSlotEuropean(int row, int col, int armThickness) {
    int y = (armThickness - 1) / 2;
    int twoY = 2 * y;
    int size = (3 * armThickness) - 3;

    boolean leftOne = (row < y && (col < twoY || col > (size - twoY)));
    boolean leftTwo = (row < twoY && (col < y || col > (size - y)));
    boolean rightOne = (row > (size - twoY) && (col < y || col > (size - y)));
    boolean rightTwo = (row > (size - y) && (col < twoY || col > (size - twoY)));

    return (leftOne || leftTwo || rightOne || rightTwo);
  }

  /**
   * Checks if (row, col) would be invalid in a triangle shaped board. Since it's a triangle shape,
   * it doesn't matter the armThickness.
   *
   * @param row given row.
   * @param col given column.
   * @return if (row, col) would be invalid in a triangle shaped board.
   */
  public boolean invalidSlotTriangle(int row, int col) {
    return (col > row);
  }

  public Integer[] getModelArgs(String... args) {
    MarbleSolitaireModel result;
    int modelArg1;
    int modelArg2;
    int modelArg3;

    switch (args.length) {
      case 3:
        modelArg1 = Integer.parseInt(args[2]);
        modelArg2 = 3;
        modelArg3 = 3;
        break;
      case 6:
        modelArg1 = 3;
        modelArg2 = Integer.parseInt(args[4]);
        modelArg3 = Integer.parseInt(args[5]);
        break;
      default:
        modelArg1 = 3;
        modelArg2 = 3;
        modelArg3 = 3;
    }
    return new Integer[]{modelArg1, modelArg2, modelArg3};
  }



}
