/*

  Team of Zijian Feng,Yunquan Zhang and Jiaxuan Qiang
  Connect four game, Summer of 2021

  This class serves as a connection link between "Menu" and the "Player vs Player" mode Game.
  People can input their names here before they start the game.

  It interacts with 1 "MenuView" Class when the "back" button get clicks
  It also interacts with 2 "Start" when Class the "play" button get clicks as Player vs Player mode.

 */


package project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessBoard extends JFrame implements ActionListener {
  //Variables declaration
  private Start cbBoard;
  // 7*6 == 0 ; 8*7 == 1
  private int boardSize = 0;
  private int first = 1;
  //Initializing All the things in the BAR, it has game(exit inside) and boardsize(8*7 7*6 inside).
  private JMenu menu1 = new JMenu("Game");
  private JMenu menu11 = new JMenu("BoardSize");
  private JMenuItem item111 = new JMenuItem("7*6");
  private JMenuItem item112 = new JMenuItem("8*7");
  private JMenuItem item12 = new JMenuItem("Exit");
  private JMenuBar bar = new JMenuBar();
  //Initializing All the labels saying Player1 Player2  Win...
  private JLabel jlbl1 = new JLabel("Player1:");
  private JLabel jlbl2 = new JLabel("Player2:");
  private JLabel jlblWin = new JLabel("Win");
  //Two input boxes
  protected JTextField jtxt1 = new JTextField();
  protected JTextField jtxt2 = new JTextField();
  protected JTextField jtxtWin = new JTextField();
  //Buttons initializing
  private JButton jbtnPlay = new JButton("Play");
  private JButton jbtnRestart = new JButton("back");

  //Constructor!! It sets all the things declared above.
  public ChessBoard() {
    //Add 7*6 8*7 and exit Listener
    super("ConnectFour");
    item111.addActionListener(this);
    item112.addActionListener(this);
    item12.addActionListener(this);
    //Add 8*7 and 7*6 into "BoardSize"
    menu11.add(item111);
    menu11.add(item112);
    //Add exit into "game"
    menu1.add(item12);
    //Add "BoardSize" and "game" into the bar
    bar.add(menu1);
    bar.add(menu11);
    this.setJMenuBar(bar);
    //window setting
    this.setBounds(0, 0, 738, 685);
    this.setResizable(false);
    this.setVisible(true);
    this.setLayout(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //Labels setting
    jlbl1.setSize(55, 30);
    jlbl1.setLocation(20, 0);
    add(jlbl1);
    jlbl2.setSize(55, 30);
    jlbl2.setLocation(200, 0);
    add(jlbl2);
    jlblWin.setSize(50, 30);
    jlblWin.setLocation(700, 250);
    add(jlblWin);
    //Input box setting
    jtxt1.setSize(80, 30);
    jtxt1.setLocation(80, 0);
    add(jtxt1);
    jtxt2.setSize(80, 30);
    jtxt2.setLocation(260, 0);
    add(jtxt2);
    jtxtWin.setSize(80, 30);
    jtxtWin.setLocation(620, 250);
    jtxtWin.setEditable(false);
    add(jtxtWin);
    //Buttons "back" setting
    jbtnRestart.setSize(80, 30);
    jbtnRestart.setLocation(620, 300);
    //Listener of "back" button that go back to main menu
    jbtnRestart.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new MenuView();
        ChessBoard.this.dispose();
      }
    });
    add(jbtnRestart);
    //Listener of "play" button that start the game
    jbtnPlay.setSize(60, 30);
    jbtnPlay.setLocation(400, 0);
    jbtnPlay.setEnabled(true);
    jbtnPlay.setVisible(true);
    jbtnPlay.addActionListener(btPlayListener());//call btPlayListener belows which illustrates how it works there
    add(jbtnPlay);
  }

  //It listens to Play buttons and will turn to "Start" class if the "play" button is clicked.
  private ActionListener btPlayListener() {
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        jbtnPlay.setEnabled(false);
        String msg = "";
        if (jtxt1.getText().equals("") && jtxt2.getText().equals("")) {
          msg = "Please input your name!!!";
          jbtnPlay.setEnabled(true);
          JOptionPane.showMessageDialog(
                  ChessBoard.this, msg, "WARN", JOptionPane.INFORMATION_MESSAGE);
        } else if (jtxt1.getText().equals(jtxt2.getText())) {
          msg = "Player1's and Player2's name should not be the same!";
          jbtnPlay.setEnabled(true);
          JOptionPane.showMessageDialog(
                  ChessBoard.this, msg, "WARN", JOptionPane.INFORMATION_MESSAGE);
        } else {
            jtxt1.setEditable(false);
            jtxt2.setEditable(false);

            //start Player vs Player first
            if (!jtxt1.getText().equals("") && !jtxt2.getText().equals("")) {
              cbBoard = new Start(boardSize, ChessBoard.this, 0, first);
            } else {
                //Start player vs Computer version, an Hidden Easter egg to avoid people only inputting one name
                if (jtxt1.getText().equals("")) {
                  first = 2;
                  jtxt1.setText("Computer");
                } else {
                  jtxt2.setText("Computer");
                }
                cbBoard = new Start(boardSize, ChessBoard.this, 1, first);
            }

            ChessBoard.this.add(cbBoard);
            ChessBoard.this.repaint();
          }
          }
    };
  }
  //It Listens to the click of"exit" and click of "7*6" and "8*7"
  @Override
  public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();
    if (source == item12) {
      System.exit(0);
    } else if (source == item111) {
      ChessBoard.this.boardSize = 0;
    } else if (source == item112) {
      ChessBoard.this.boardSize = 1;
    }
  }
}
