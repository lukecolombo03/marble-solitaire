package cs3500.marblesolitaire.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import cs3500.marblesolitaire.controller.MarbleSolitaireGUIFeatures;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

public class BoardPanel extends JPanel implements BoardPanelGUI {
  private MarbleSolitaireModelState modelState;
  private Image emptySlot, marbleSlot, blankSlot;
  private final int cellDimension;
  private int originX,originY;
  private MarbleSolitaireGUIFeatures feature;
  private int highlightX, highlightY;

  public BoardPanel(MarbleSolitaireModelState state) throws IllegalStateException {
    super();
    this.modelState = state;
    this.setBackground(Color.WHITE);
    this.cellDimension = 50;
    try {
      emptySlot = ImageIO.read(new FileInputStream("res/empty.png"));
      emptySlot = emptySlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      marbleSlot = ImageIO.read(new FileInputStream("res/marble.png"));
      marbleSlot = marbleSlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      blankSlot = ImageIO.read(new FileInputStream("res/blank.png"));
      blankSlot = blankSlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      this.setPreferredSize(
              new Dimension((this.modelState.getBoardSize() + 4) * cellDimension
                      , (this.modelState.getBoardSize() + 4) * cellDimension));
    } catch (IOException e) {
      throw new IllegalStateException("Icons not found!");
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    originX = (int) (this.getPreferredSize().getWidth() / 2 - this.modelState.getBoardSize() * cellDimension / 2);
    originY = (int) (this.getPreferredSize().getHeight() / 2 - this.modelState.getBoardSize() * cellDimension / 2);


    //your code to the draw the board should go here.
    //The originX and originY is the top-left of where the cell (0,0) should start
    //cellDimension is the width (and height) occupied by every cell
    for (int row = 0; row < this.modelState.getBoardSize(); row++) {
      for (int col = 0; col < this.modelState.getBoardSize(); col++) {
        switch(modelState.getSlotAt(row, col)) {
          case Marble:
            g.drawImage(marbleSlot, adjustForOrigin(col, true),
                    adjustForOrigin(row, false), this);
            break;
          case Empty:
            g.drawImage(emptySlot, adjustForOrigin(col, true),
                    adjustForOrigin(row, false), this);
            break;
          case Invalid:
            g.drawImage(blankSlot, adjustForOrigin(col, true),
                    adjustForOrigin(row, false), this);
            break;
          default:
            //this is an enum so there's no other case
        }
      }
    }
    Color myColor = new Color(0, 255, 255, 100);
    g.setColor(myColor);
    // if both coordinates are 0, don't display the highlight square (b/c this is the start)
    if (highlightY != 0 || highlightX != 0) {
      g.fillRect(highlightX, highlightY, cellDimension, cellDimension);
    }
  }


  //this converts a coordinate on the marble solitaire board (0-7) to a coordinate on the Swing view
  //true for X, false for Y coordinate
  private int adjustForOrigin(int coordinate, boolean xTrue) {
    if (xTrue) {
      return coordinate * cellDimension + originX;
    } else {
      return coordinate * cellDimension + originY;
    }
  }

  public void setHighlightCoordinates(int row, int col) {
    this.highlightX = adjustForOrigin(col, true);
    this.highlightY = adjustForOrigin(row, false);
  }


  @Override
  public void addFeature(MarbleSolitaireGUIFeatures feature) {
    this.feature = feature;
    this.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        //use originX, originY, and cellDimension to determine which cell the mouse is in
        //then call feature.move(x, y) with them
        int x = (e.getX() - originX) / cellDimension;
        int y = (e.getY() - originY) / cellDimension;
        feature.move(y, x);
      }
    });
  }
}
