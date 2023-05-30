package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.AMarbleSolitaireModel;

/**
 * Implementation of MarbleSolitaireModel.
 */
public class EnglishSolitaireModel extends AMarbleSolitaireModel implements MarbleSolitaireModel {


  /**
   * Constructs a board of arm thickness 3, with an empty slot in the center.
   */
  public EnglishSolitaireModel() {
    super(3, 3, 3);
  }

  /**
   * Constructs a board with arm thickness 3 and an empty slot at (sRow, sCol).
   *
   * @param sRow the given row.
   * @param sCol the given column.
   * @throws IllegalArgumentException if the row or column is in an invalid position.
   */
  public EnglishSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    super(3, sRow, sCol);
    if (new Utils().invalidSlotEnglish(sRow, sCol, armThickness)) {
      throw new IllegalArgumentException("Invalid empty cell position (" +
              sRow + " , " + sCol + " )");
    }
  }

  /**
   * Constructs a board with a given arm thickness and an empty slot in the center.
   *
   * @param armThickness the arm thickness.
   * @throws IllegalArgumentException if the argument is even or negative.
   */
  public EnglishSolitaireModel(int armThickness) throws IllegalArgumentException {
    super(armThickness, ((3 * armThickness) - 3) / 2, ((3 * armThickness) - 3) / 2);
    if (armThickness < 0 || (armThickness % 2 == 0)) {
      throw new IllegalArgumentException("Arm thickness must be a positive odd number");
    }
  }

  /**
   * Constructs a board with a given arm thickness and an empty slot at (sRow, sCol).
   *
   * @param armThickness the arm thickness.
   * @param sRow         the given row.
   * @param sCol         the given column.
   * @throws IllegalArgumentException if armThickness is negative or even.
   * @throws IllegalArgumentException if (sRow, sCol) is an invalid position.
   */
  public EnglishSolitaireModel(int armThickness, int sRow, int sCol) {
    super(armThickness, sRow, sCol);
    if (new Utils().invalidSlotEnglish(sRow, sCol, armThickness)) {
      throw new IllegalArgumentException("Invalid empty cell position (" +
              sRow + " , " + sCol + " )");
    }
    if (armThickness < 0 || (armThickness % 2 == 0)) {
      throw new IllegalArgumentException("Arm thickness must be a positive odd number");
    }
  }








}

