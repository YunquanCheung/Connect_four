/*

    Team of Yunquan Zhang, Zijian Feng and Jiaxuan Qiang
    Connect four game, Summer of 2021

    This class serves as a connection link between "Menu" and the "Player vs Computer" Game.
    People can input their names here before they start the game.
    It extends most things from Class "ChessBoard" which sets buttons and other things
    It overrides the parts that the input boxes of Player1 and Player2 get set editable randomly.

    It interacts with 1 Class"MenuView" when the "back" button get clicks
    It also interacts with 2 Class"Start" when the "play" button get clicks as Player
    vs Computer mode.

 */

package project;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Random;

public class ChessBoardComputer extends ChessBoard implements ActionListener {
  
  protected JTextField jtxt1 = new JTextField();
  protected JTextField jtxt2 = new JTextField();

  //Constructor of ChessBoardComputer
  public ChessBoardComputer() {
    //Message of instruction noticing that this is a Player vs Computer mode
    JOptionPane.showMessageDialog(ChessBoardComputer.this,
                                  "You can change BOARDSIZE on upper left! " 
                                    + "You will be assigned" 
                                    + " as Player1 or Player2 randomly! ",
                                  "This is PlAYER vs COMPUTER mode",
                                  JOptionPane.INFORMATION_MESSAGE);
    //Generate random boolean number determining which of the two input boxes
    //is editable and which is not
    Random ranGen = new Random();
    boolean isFirst = ranGen.nextBoolean();
    if (isFirst) {
      jtxt2.setSize(80, 30);
      jtxt2.setLocation(260, 0);
      jtxt2.setText("Computer");
      jtxt2.setEditable(false);
      jtxt2.setEnabled(false);
      add(jtxt2);
    } else {
      jtxt1.setSize(80, 30);
      jtxt1.setLocation(80, 0);
      jtxt1.setText("Computer");
      jtxt1.setEditable(false);
      jtxt1.setEnabled(false);
      add(jtxt1);
    }
  }
}
