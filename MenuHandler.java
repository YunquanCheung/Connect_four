/*

    Team of Yunquan Zhang, Zijian Feng and Jiaxuan Qiang
    Connect four game, Summer of 2021

    This class takes charge of what happens after clicking the buttons in the Menu View.
    It interacts with 3 classes.
        If you click "Instruction" then it turns to 1."InstructionView" class that displays the instruction message.
        If you click "Player vs Player" then it turns to 2 "ChessBoard" class that execute Player vs Player;
        If you click "Instruction" then it turns to  3."ChessBoardComputer" class which executes Player vs Computer;"
        If you click "Exit" then it exits.
 */


package project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuHandler implements ActionListener {


    private MenuView menuView;
    public MenuHandler(MenuView menuView){

        this.menuView = menuView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("PLAYER VS PLAYER".equals(text)) {
            new ChessBoard();
            menuView.dispose();
            //System.out.println("PVP");
        }else if("PLAYER VS COMPUTER".equals(text)) {
            new ChessBoardComputer();
            menuView.dispose();
        }else if("INSTRUCTION, READ IT FIRST".equals(text)) {
            new InstructionView();
            menuView.dispose();
        }else if("EXIT".equals(text)) {
            System.exit(0);
        }
    }

}
