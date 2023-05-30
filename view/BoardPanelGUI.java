package cs3500.marblesolitaire.view;


import cs3500.marblesolitaire.controller.MarbleSolitaireGUIFeatures;

public interface BoardPanelGUI {
  void addFeature(MarbleSolitaireGUIFeatures feature);

  void setHighlightCoordinates(int row, int col);
}
