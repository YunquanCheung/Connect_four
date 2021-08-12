
/*

    Team of Yunquan Zhang, Zijian Feng and Jiaxuan Qiang
    Connect four game, Summer of 2021

    InstructionView.java displays the instruction message and a "back" button to
    return to MenuView.

    This class is only interacted with Class "MainView". After clicking "back" button, it
    will return to MenuView.

*/

package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InstructionView extends JFrame {
  //Initializing label and layout setting
  JLabel nameLabel = new JLabel("Instruction", JLabel.CENTER);
  SpringLayout springlayout = new SpringLayout();
  JPanel centerPanel = new JPanel(springlayout);
  //instruction message
  String instruMsg = "<html>Welcome to the connectFour Game. The game name says it,"
          +
          " what you have to do \"connect four\" "
          +
          "to win the game. After selecting your mode Player vs Player or Player vs"
          +
          " Computer, you need to input your name"
          +
          " first. Then you can click the \"play\" button. Player1 will make the first"
          +
          " move with black pieces and Player2 will "
          +
          "move their white pieces next.<br><br>"
          +
          "Each turn, the player" + " will get the chance to drop and make the row "
          +
          "of his checkers, but sometimes instead of making your row, " +  "the player "
          +
          "has to stop another opponent "
          +
          "from making his 4 checkers row by dropping your color checker" + " in that place. "
          +
          "If you are able to connect a row of four pieces of the same color before your opponent, "
          +
          "then you win the game! The rows can be done vertically,horizontally or"
          +
          " diagonally.<br><br>"
          +
          " We also have a Player vs Computer mode for you, you need to input your "
          +
          "name in player1's"
          +
          " box that click the \"play\" button but you probably think that the"
          +
          " computer is stupid because"
          +
          " the algorithm is very simple. I would suggest you play the PVP one"
          +
          " if you are with your friends"
          +
          "<br><br>Another tip here is that you can choose to change the board "
          +
          "size in the upper left corner"
          +
          " at the start of the game before you click the \"play\" button.</html>";

  JLabel insLabel = new JLabel(instruMsg);
  JButton backBtn = new JButton("Back");
  private JMenu menu = new JMenu("Game");
  
  public InstructionView() {
    //title
    super("ConnectFour");
    //Set label and buttons style
    nameLabel.setFont(new Font("Brush Script Std", Font.PLAIN, 80));
    nameLabel.setPreferredSize(new Dimension(0, 100));
    Font cenFont = new Font("Times New Roman", Font.PLAIN, 18);
    insLabel.setFont(cenFont);
    backBtn.setFont(cenFont);

    //Add all the buttons into Panel
    Dimension preferredSize = new Dimension(600, 450);    //set text' size for InstructionMsg
    insLabel.setPreferredSize(preferredSize);

    Dimension preferredSize1 = new Dimension(100, 50);    //set text' size for "back"button
    backBtn.setPreferredSize(preferredSize1);

    //add instruction to the panel
    centerPanel.add(insLabel);

    //add "back" button to the panel
    backBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
            new MenuView();
            InstructionView.this.dispose();
        }
    });
    centerPanel.add(backBtn);

    //Spring layout code
    layoutCenter();
    //boarder layout code
    Container contentPane = getContentPane();
    contentPane.add(nameLabel, BorderLayout.NORTH);
    contentPane.add(centerPanel, BorderLayout.CENTER);
    //windows' setting
    this.setBounds(0, 0, 738, 685);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setVisible(true);
    this.setLayout(null);
  }
  //Spring Layout setting
  private void layoutCenter() {

    Spring childWidth = Spring.width(insLabel);
    int offsetX = childWidth.getValue() / 2;

    //Layout instruction notes
    springlayout.putConstraint(SpringLayout.WEST, insLabel, -offsetX,
            SpringLayout.HORIZONTAL_CENTER, centerPanel);
    springlayout.putConstraint(SpringLayout.NORTH, insLabel, 10,
            SpringLayout.NORTH, centerPanel);

    // Layout "back" button
    springlayout.putConstraint(SpringLayout.WEST, backBtn, -offsetX,
            SpringLayout.HORIZONTAL_CENTER, centerPanel);
    springlayout.putConstraint(SpringLayout.NORTH, backBtn, 7,
            SpringLayout.SOUTH, insLabel);
  }
}

