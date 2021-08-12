/*
       Team of Zijian Feng,Yunquan Zhang and Jiaxuan Qiang
       Connect four game, Summer of 2021

       This class is what the program would run after clicking the "play" button.
       It draws all the board and pieces after player or computer make a move.
       Then it judges whether there is a winner or whether the board is filled(tie).
       If not, then it keeps going.

       It interacts with 2 classes.
       Both "ChessBoard" and "ChessBoardComputer" will generates this "Start" 
       class to start the game.

 */


package project;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Start extends JPanel {
  //Initialize
  private ChessBoard cb;
  //Default using 7*6 Board
  private int boardRow = 6;
  private int boardCol = 7;
  //Board declaration
  private int[][] chesses = new int[boardRow][boardCol];
  //BoardSize setting, boardsize = 0 means 7*6 ; boardsize = 1 means 8*7
  private int boardSize = 0;
  //Color and integer correlation
  public static final int BLACK = 1;
  public static final int WHITE = -1;
  public static final int NONE = 0;
  //current color
  private int currentColor = BLACK;
  //flag of win
  private boolean isWin = false;

  //  Player vs Player default player 1 do the first, Player vs Computer default(Player first)
  private int first;
  //  Player vs Player then mode == 0, Player vs Computer then mode ==1
  private int mode;
  
  private int width = 70;
  private int hight = 60;
  
  //It states the current Row and Current Colum
  private int currRow;
  private int currCol;
  
  //representing winner's color
  private int winner = NONE;
  //pieces' image declaration
  private BufferedImage whiteImage = null;
  private BufferedImage blackImage = null;
  //declare button 1 to 8
  private JButton jbtn1 = new JButton("1");
  private JButton jbtn2 = new JButton("2");
  private JButton jbtn3 = new JButton("3");
  private JButton jbtn4 = new JButton("4");
  private JButton jbtn5 = new JButton("5");
  private JButton jbtn6 = new JButton("6");
  private JButton jbtn7 = new JButton("7");
  private JButton jbtn8 = new JButton("8");
  //color initializing
  private int playerOneColor;
  private int playerTwoColor;
  private int aiColor;

  //Constructor of Start class; it execute what after the "Play" button
  //get clicked. It has 4 parameters that receives the ChessBoard size
  //Then it receives the mode( PVP or PVC) and also receives(who move first)
  //Then it creates a board and people can make a move here.
  public Start(int boardSize, ChessBoard cb, int mode, int first) {
    //Initialize the board.
    this.boardSize = boardSize;
    this.cb = cb;
    this.mode = mode;
    this.first = first;
    setSize(565, 500);
    setLocation(20, 40);
    setVisible(true);
    //Set buttons 1 to 8
    jbtn1.setSize(64, 35);
    jbtn1.setLocation(3, 0);
    jbtn2.setSize(64, 35);
    jbtn2.setLocation(73, 0);
    jbtn3.setSize(64, 35);
    jbtn3.setLocation(143, 0);
    jbtn4.setSize(64, 35);
    jbtn4.setLocation(213, 0);
    jbtn5.setSize(64, 35);
    jbtn5.setLocation(283, 0);
    jbtn6.setSize(64, 35);
    jbtn6.setLocation(353, 0);
    jbtn7.setSize(64, 35);
    jbtn7.setLocation(423, 0);
    jbtn8.setSize(64, 35);
    jbtn8.setLocation(493, 0);
    this.add(jbtn1);
    this.add(jbtn2);
    this.add(jbtn2);
    this.add(jbtn3);
    this.add(jbtn4);
    this.add(jbtn5);
    this.add(jbtn6);
    this.add(jbtn7);
    this.add(jbtn8);

    //Set button able to detect CLICK
    jbtn1.addActionListener(buttonNumberHandler(1));
    jbtn2.addActionListener(buttonNumberHandler(2));
    jbtn3.addActionListener(buttonNumberHandler(3));
    jbtn4.addActionListener(buttonNumberHandler(4));
    jbtn5.addActionListener(buttonNumberHandler(5));
    jbtn6.addActionListener(buttonNumberHandler(6));
    jbtn7.addActionListener(buttonNumberHandler(7));
    jbtn8.addActionListener(buttonNumberHandler(8));

    //Initialize the key board Size
    if (this.boardSize == 0) {
      jbtn8.setEnabled(false);
    } else {
      Start.this.boardRow = 7;
      Start.this.boardCol = 8;
      chesses = new int[boardRow][boardCol];
    }
    //ImageIO.read may throw an exception, hence using try_catch here
    try {
      blackImage = ImageIO.read(Start.class.getResourceAsStream("/project/b.jpg"));
      whiteImage = ImageIO.read(Start.class.getResourceAsStream("/project/w.jpg"));

    } catch (IOException e) {
      e.printStackTrace();
    }
    //Player vs Player setting
    if (mode == 0) {
      playerOneColor = BLACK;
      playerTwoColor = WHITE;
      aiColor = NONE;

      //Player vs Computer, Player move first no matter if it is Player1 or Player2
    } else { // mode == 1
      if (first == 2) {
        playerOneColor = NONE;
        playerTwoColor = BLACK;
        aiColor = WHITE;
      } else {
        playerOneColor = BLACK;
        playerTwoColor = NONE;
        aiColor = WHITE;
      }
    }
  }

  //Listening to the number button that move a piece
  private ActionListener  buttonNumberHandler(int buttonNumber) {
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (chesses[0][ buttonNumber - 1] != NONE && !isWin) {

          JOptionPane.showMessageDialog(Start.this, "This column is full!!",
                                        "WARN", JOptionPane.INFORMATION_MESSAGE);
        } else {
          for (int i = boardRow - 1; i >= 0; i--) {
            if (chesses[i][ buttonNumber - 1] == NONE) {
              chesses[i][ buttonNumber - 1] = currentColor;
              goOneStep(i,  buttonNumber - 1, currentColor);
              currentColor = -currentColor;
              break;
            }
          }
          if (mode == 1 && !isWin) {
            aiGoOneStep();
          }
        }
      }
    };
  }

  //Using the paintComponets class to draw the board
  //Then draws the pieces.
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponents(g);
    //draw horizontal line
    for (int i = 0; i <= 8; i++) {
      g.drawLine(0, i * 60 + 40, 560, i * 60 + 40);
    }
    //Drawing vertical line
    for (int i = 0; i <= 9; i++) {
      g.drawLine(i * 70, 40, i * 70, 460);
    }
    for (int i = 0; i < boardRow; i++) {
      for (int j = 0; j < boardCol; j++) {
        if (chesses[i][j] == BLACK) {
          drawPiece(i, j, blackImage, g);
        } else if (chesses[i][j] == WHITE) {
          drawPiece(i, j, whiteImage, g);
        }
      }
    }
  }
  
  // It has 3 parameters: int row, col, image and an object from Graphics class
  // Drawing piece in (row,col) with image using draImage method.
  private void drawPiece(int row, int col, BufferedImage image, Graphics g) {
    
    int x = col * 70 + 3;
    int y = row * 60 + 43;
    g.drawImage(image, x, y, this);
    
  }

  //It has 3 parameters: row col and color
  //Moving a piece(color of black or white) into (row,col)
  //Then calling isWin to check whether there exists a winner.（End with a winner)
  //Then calling isFlag to check if all the board is filled.(End in A Tie)
  private void goOneStep(int row, int col, int color)  {
    chesses[row][col] = color;
    currRow = row;
    currCol = col;
    cb.repaint();
    this.repaint();
    isWin = isWin(currRow, currCol, color);
    if (isWin) {
      winner = color;
      String msg;
      if (winner == aiColor) {
        cb.jtxtWin.setText("Computer");
      } else if (winner == playerOneColor) {
        JOptionPane.showMessageDialog(Start.this, "Player1: " + cb.jtxt1.getText() + " wins!!",
                                      "Game over", JOptionPane.INFORMATION_MESSAGE);
        cb.jtxtWin.setText(cb.jtxt1.getText());
      } else {
        JOptionPane.showMessageDialog(Start.this, "Player2: " + cb.jtxt2.getText() + " wins!!",
                                      "Game over", JOptionPane.INFORMATION_MESSAGE);
        cb.jtxtWin.setText(cb.jtxt2.getText());
      }
      jbtn1.setEnabled(false);
      jbtn2.setEnabled(false);
      jbtn3.setEnabled(false);
      jbtn4.setEnabled(false);
      jbtn5.setEnabled(false);
      jbtn6.setEnabled(false);
      jbtn7.setEnabled(false);
      jbtn8.setEnabled(false);
    }

    if (isFull()) {
      jbtn1.setEnabled(false);
      jbtn2.setEnabled(false);
      jbtn3.setEnabled(false);
      jbtn4.setEnabled(false);
      jbtn5.setEnabled(false);
      jbtn6.setEnabled(false);
      jbtn7.setEnabled(false);
      jbtn8.setEnabled(false);
      JOptionPane.showMessageDialog(Start.this, "End in a tie", 
                                    "Game over", JOptionPane.INFORMATION_MESSAGE);
    }
  }


  //It has 3 parameters row, col and color.
  //Determine whether one side of player was win after moving a piece 
  //(color of black or white) to (row, col).
  //Win means there are 4 pieces in a line(vertically, horizontally, or diagonally).
  //It returns a boolean variable demonstrating if it is win(true) or not(false).
  private boolean isWin(int row, int col, int color) {
    int max = 0;
    int temp = 0;
    //determining if vertically connect four
    for (int i = 0; i < boardCol; i++) {
      if (chesses[row][i] == color) {
        temp++;
        if (max < temp) {
          max = temp;
        }
      } else {
        temp = 0;
      }
    }
    if (max >= 4) {
      return true;
    }
    //determining if horizontally connect four
    temp = 0;
    max = 0;
    for (int i = 0; i < boardRow; i++) {
      if (chesses[i][col] == color) {
        temp++;
        if (max < temp) {
          max = temp;
        }
      } else {
        temp = 0;
      }
    }
    if (max >= 4) {
      return true;
    }
    //determine if diagonally connect four
    if (isDiagonalWin(row, col, color)) {
      return true;
    }
    return false;
  }

  //It has 3 parameters row, col and color.
  //Determine whether one side of player connect four pieces diagonally
  // after moving a piece (color of black or white) to (row, col).
  private boolean isDiagonalWin(int row, int col, int color) {
    
    int max = 0;
    int x = row;
    int y = col;
    while (x >= 0 && x < boardRow && y >= 0 && y < boardCol && chesses[x][y] == color) {
      x--;
      y--;
    }
    x++;
    y++;
    while (x >= 0 && x < boardRow && y >= 0 && y < boardCol && chesses[x][y] == color) {
      max++;
      x++;
      y++;
    }
    if (max >= 4) {
      return true;
    }
    
    x = row;
    y = col;
    max = 0;
    while (x >= 0 && x < boardRow && y >= 0 && y < boardCol && chesses[x][y] == color) {
      x--;
      y++;
    }
    x++;
    y--;
    while (x >= 0 && x < boardRow && y >= 0 && y < boardCol && chesses[x][y] == color) {
      max++;
      x++;
      y--;
    }
    if (max >= 4) {
      return true;
    }
    return false;
  }

  //Computer makes a move
  private void aiGoOneStep() {
    jbtn1.setEnabled(false);
    jbtn2.setEnabled(false);
    jbtn3.setEnabled(false);
    jbtn4.setEnabled(false);
    jbtn5.setEnabled(false);
    jbtn6.setEnabled(false);
    jbtn7.setEnabled(false);
    jbtn8.setEnabled(false);
    int cal = 0;
    int row = 0;
    //generate random number to move a piece
    do {
      cal = new Random().nextInt(boardCol);
    } while (chesses[0][cal] != NONE);
    
    for (int i = boardRow - 1; i >= 0; i--) {
      if (chesses[i][cal] == NONE) {
        chesses[i][cal] = aiColor;
        row = i;
        break;
      }
    }
    //take turn
    currentColor = - currentColor;
    //Move a piece for computer
    goOneStep(row, cal, aiColor);
    if (!isWin) {
      jbtn1.setEnabled(true);
      jbtn2.setEnabled(true);
      jbtn3.setEnabled(true);
      jbtn4.setEnabled(true);
      jbtn5.setEnabled(true);
      jbtn6.setEnabled(true);
      jbtn7.setEnabled(true);
      if (boardSize == 1) {
        jbtn8.setEnabled(true);
      }
    } else {
      JOptionPane.showMessageDialog(Start.this, "Computer wins!!",
                                    "Game over", JOptionPane.INFORMATION_MESSAGE);
    }
  }

  //Determining if the board is Full!!
  private boolean isFull() {
    for (int i = 0; i < boardCol; i++) {
      if (chesses[0][i] == NONE) {
        return false;
      }
    }
    return true;
  }
}
