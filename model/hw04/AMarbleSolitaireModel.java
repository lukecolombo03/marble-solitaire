package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.Utils;
import cs3500.marblesolitaire.view.AbstractView;

/**
 * Abstract class to represent a game model. Abstracts the fields, constructors, and some methods.
 */
public abstract class AMarbleSolitaireModel implements MarbleSolitaireModel {
  protected int armThickness;
  public SlotState[][] board;

  /**
   * Empty constructor to help TriangleSolitaireModel compile.
   */
  public AMarbleSolitaireModel() {
  }

  /**
   * Constructor that takes in 3 parameters.
   * @param armThickness armthickenss of the board.
   * @param sRow given row for the empty slot.
   * @param sCol given column for the empty slot.
   */
  public AMarbleSolitaireModel(int armThickness, int sRow, int sCol) {
    this.armThickness = armThickness;
    int sideLength = (3 * armThickness) - 2;
    this.board = new SlotState[sideLength][sideLength];
    for (int row = 0; row < sideLength; row++) {
      for (int col = 0; col < sideLength; col++) {
        if (new Utils().invalidSlotEnglish(row, col, armThickness)) {
          this.board[row][col] = SlotState.Invalid;
        } else {
          this.board[row][col] = SlotState.Marble;
        }
      }
    }
    this.board[sRow][sCol] = SlotState.Empty;
  }



  /**
   * Move a single marble from a given position to another given position.
   * A marble can move only if it jumps over an orthogonally adjacent marble. Both the
   * to and from positions must be valid, and there must be a marble to jump over.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException if the move is not possible
   */
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (positionsValid(fromRow, fromCol, toRow, toCol)) {
      if (upDownLeftRight(fromRow, fromCol, toRow, toCol) > 0) {
        literalMove(fromRow, fromCol, toRow, toCol);
      } else {
        throw new IllegalArgumentException("Move is not possible");
      }
      if (upDownLeftRight(fromRow, fromCol, toRow, toCol) == 1) {
        this.board[fromRow][fromCol - 1] = SlotState.Empty;
      }
      if (upDownLeftRight(fromRow, fromCol, toRow, toCol) == 2) {
        this.board[fromRow][fromCol + 1] = SlotState.Empty;
      }
      if (upDownLeftRight(fromRow, fromCol, toRow, toCol) == 3) {
        this.board[fromRow - 1][fromCol] = SlotState.Empty;
      }
      if (upDownLeftRight(fromRow, fromCol, toRow, toCol) == 4) {
        this.board[fromRow + 1][fromCol] = SlotState.Empty;
      }
    } else {
      throw new IllegalArgumentException("Move is not possible: invalid positions");
    }

  }


  // does the actual moving — the "from" slot becomes empty and the "to" slot becomes a marble
  private void literalMove(int fromRow, int fromCol, int toRow, int toCol) {
    this.board[fromRow][fromCol] = SlotState.Empty;
    this.board[toRow][toCol] = SlotState.Marble;
  }

  // given two slots where a move could occur, which way would the move be
  // 0 = invalid move, 1 = up, 2 = down, 3 = left, 4 = right
  private int upDownLeftRight(int fromRow, int fromCol, int toRow, int toCol) {
    if (toRow == fromRow) { // moving up or down
      if (toCol < fromCol) { // moving up
        return 1;
      } else {
        return 2;
      }
    } else if (toCol == fromCol) {  // moving left or right
      if (toRow < fromRow) {
        return 3;
      } else {
        return 4;
      }
    } else {
      return 0;
    }
  }


  /**
   * Checks:
   * 1) all the coordinates are within the board[][] array.
   * 2) the "from" slot has a marble.
   * 3) the "to" slot is empty.
   * 4) the "from" and "to" slots are exactly 2 spaces away.
   * 5) they are 2 spaces away orthogonally.
   *
   * @param fromRow row of "from" slot.
   * @param fromCol column of "from" slot.
   * @param toRow   row of "to" slot.
   * @param toCol   column of "to" slot.
   * @return true if these two slots are valid (pass checks 1-5).
   */
  public boolean positionsValid(int fromRow, int fromCol, int toRow, int toCol) {
    return inBoard(fromRow) && inBoard(fromCol) && inBoard(toRow) && inBoard(toCol) &&
            (this.board[fromRow][fromCol] == SlotState.Marble) &&
            (this.board[toRow][toCol] == SlotState.Empty) &&
            ((Math.abs(toRow - fromRow) == 2) ^ (Math.abs(toCol - fromCol) == 2)) &&
            ((toRow == fromRow) ^ (toCol == fromCol));
  }


  /**
   * Is it possible for this row or column coordinate be in the board.
   *
   * @param rowOrCol a coordinate - could be a row or column.
   * @return if this coordinate could be in the board.
   */
  protected boolean inBoard(int rowOrCol) {
    return rowOrCol >= 0 && rowOrCol < this.getBoardSize();
  }

  @Override
  public int getScore() {
    int marbles = 0;
    for (int i = 0; i < this.board.length; i++) {
      for (int j = 0; j < this.board.length; j++) {
        if (getSlotAt(i, j) == SlotState.Marble) {
          marbles = marbles + 1;
        }
      }
    }
    return marbles;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (!inBoard(row) || !inBoard(row) || !inBoard(col) || !inBoard(col)) {
      throw new IllegalArgumentException("Desired slot is beyond dimensions of the board");
    } else {
      return this.board[row][col];
    }
  }

  @Override
  public int getBoardSize() {
    return this.board.length;
  }

  /**
   * Are there any marbles adjacent to the slot at (row, col).
   *
   * @param row row coordinate.
   * @param col column coordinate.
   * @return true if there are marbles adjacent.
   */
  private boolean anyMarblesAdjacent(int row, int col) {
    return ((row + 1 < this.getBoardSize()) && this.board[row + 1][col] == SlotState.Marble) ||
            ((row - 1) >= 0 && this.board[row - 1][col] == SlotState.Marble) ||
            ((col - 1) >= 0 && this.board[row][col - 1] == SlotState.Marble) ||
            ((col + 1) < this.getBoardSize()) && this.board[row][col + 1] == SlotState.Marble;
  }

  /**
   * Checks if the game is over - meaning no more moves are possible.
   *
   * @return true if the game is over.
   */
  public boolean isGameOver() {
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (this.board[i][j] == SlotState.Marble) {
          if (anyMarblesAdjacent(i, j)) {
            return false;
          }
        }
      }
    }
    return true;
  }

}
