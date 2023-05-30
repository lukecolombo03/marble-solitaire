package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.view.MarbleSolitaireGuiView;

public class GUIController implements MarbleSolitaireGUIFeatures {
  private final MarbleSolitaireModel model;
  private final MarbleSolitaireGuiView view;
  private int fromRow;
  private int fromCol;

  public GUIController(MarbleSolitaireModel model, MarbleSolitaireGuiView view) {
    // check for null
    this.model = model;
    this.view = view;
    this.view.addFeature(this);
  }

  @Override
  public void move(int row, int col) {
    if (model.isGameOver()) {
      this.view.renderMessage("Game over!");
    } else {
      if (this.model.getSlotAt(row, col) == MarbleSolitaireModelState.SlotState.Marble) {
        fromRow = row;
        fromCol = col;
        //get info about which cell was clicked, then tell the view to highlight that cell
        this.view.highlight(row, col);
        System.out.println("hi");
        this.view.refresh();
      } else if (this.model.getSlotAt(row, col) == MarbleSolitaireModelState.SlotState.Empty) {
        this.view.refresh();
        try {
          this.model.move(fromRow, fromCol, row, col);
          this.view.refresh();
        } catch (Exception e) {
          this.view.renderMessage("Invalid move. Please try again");
        }
      }
    }
  }

}
