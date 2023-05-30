package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.Utils;

/**
 * A game model that uses a triangular shape. It still has two axes, but they are aligned at a
 * 45-degree angle and thus doesn't use Cartesian coordinates.
 */
public class TriangleSolitaireModel extends AMarbleSolitaireModel implements MarbleSolitaireModel {


  public TriangleSolitaireModel() {
    this(5, 0, 0);
  }

  public TriangleSolitaireModel(int dimensions) {
    this(dimensions, 0, 0);
  }

  public TriangleSolitaireModel(int sRow, int sCol) {
    this(5, sRow, sCol);
  }

  public TriangleSolitaireModel(int dimensions, int sRow, int sCol) {
    if (new Utils().invalidSlotTriangle(sRow, sCol)) {
      throw new IllegalArgumentException("Invalid empty cell position (" +
              sRow + " , " + sCol + " )");
    }
    if (dimensions < 1) {
      throw new IllegalArgumentException("Argument must be positive");
    }
    this.board = new SlotState[dimensions][dimensions];
    for (int row = 0; row < dimensions; row++) {
      for (int col = 0; col < dimensions; col++) {
        if (new Utils().invalidSlotTriangle(row, col)) {
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
      if (whichWay(fromRow, fromCol, toRow, toCol) > 0) {
        literalMove(fromRow, fromCol, toRow, toCol);
      } else {
        throw new IllegalArgumentException("Move is not possible");
      }
      if (whichWay(fromRow, fromCol, toRow, toCol) == 1) {
        this.board[fromRow][fromCol - 1] = SlotState.Empty;
      }
      if (whichWay(fromRow, fromCol, toRow, toCol) == 2) {
        this.board[fromRow][fromCol + 1] = SlotState.Empty;
      }
      if (whichWay(fromRow, fromCol, toRow, toCol) == 3) {
        this.board[fromRow - 1][fromCol] = SlotState.Empty;
      }
      if (whichWay(fromRow, fromCol, toRow, toCol) == 4) {
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
  // 0 = invalid move, 1 = left, 2 = right, 3 = down left, 4 = down right,
  private int whichWay(int fromRow, int fromCol, int toRow, int toCol) {
    if (toRow == fromRow) { // moving up or down
      if (toCol > fromCol) {
        return 1; // left
      } else {
        return 2; // right
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
            triangleMoves(fromRow, fromCol, toRow, toCol);
  }

  private boolean triangleMoves(int fromRow, int fromCol, int toRow, int toCol) {
    return ((toRow - fromRow) == 2) && ((toCol - fromCol) == 2) ||
            ((fromRow - toRow) == 2) && ((fromCol - toCol) == 2) ||
            (toRow == fromRow && Math.abs(toCol - fromCol) == 2) ||
            (toCol == fromCol && Math.abs(toRow - fromRow) == 2);
  }




}
