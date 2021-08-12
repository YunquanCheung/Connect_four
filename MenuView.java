/*

    Team of Yunquan Zhang, Zijian Feng and Jiaxuan Qiang
    Connect four game, Summer of 2021

    MenuView.java displays the start menu that shows "instruction""Player vs Player"
    "Player vs Computer" and "exit" BUTTONS.

    This class is only interacted with Class "MenuHandler".
    "MenuHandler" defines what would do after clicking the buttons in the Menu View.
*/



package project;

import javax.swing.*;
import java.awt.*;

public class MenuView extends JFrame {

    JLabel nameLabel = new JLabel("Welcome to ConnectFour Game",JLabel.CENTER);
    SpringLayout springlayout = new SpringLayout();
    JPanel centerPanel = new JPanel(springlayout);
    JButton pvpBtn = new JButton("INSTRUCTION, READ IT FIRST");
    JButton pvcBtn = new JButton("PLAYER VS PLAYER");
    JButton boardBtn = new JButton("PLAYER VS COMPUTER");
    JButton backBtn = new JButton("EXIT");
    private JMenu menu = new JMenu("Game");
    MenuHandler menuHandler;

    public MenuView(){

        super("ConnectFour");
        menuHandler = new MenuHandler(this);
        Container contentPane = getContentPane();

        //Set label and buttons style
        nameLabel.setFont(new Font("Brush Script Std",Font.PLAIN,50));
        nameLabel.setPreferredSize(new Dimension(0,200));

        //Set Style of words in buttons
        Font cenFont = new Font("Times New Roman", Font.PLAIN, 16);
        pvpBtn.setFont(cenFont);
        pvcBtn.setFont(cenFont);
        boardBtn.setFont(cenFont);
        backBtn.setFont(cenFont);

        //Add all the buttons into Panel
        Dimension preferredSize=new Dimension(260, 60);    //set buttons' size
        pvpBtn.setPreferredSize(preferredSize);    //set buttons' size
        pvcBtn.setPreferredSize(preferredSize);
        boardBtn.setPreferredSize(preferredSize);
        backBtn.setPreferredSize(preferredSize);

        //Add all the buttons into the center Panel
        pvpBtn.addActionListener(menuHandler);
        centerPanel.add(pvpBtn);

        pvcBtn.addActionListener(menuHandler);
        centerPanel.add(pvcBtn);

        boardBtn.addActionListener(menuHandler);
        centerPanel.add(boardBtn);

        backBtn.addActionListener(menuHandler);
        centerPanel.add(backBtn);

        //Spring layout code
        LayoutCenter();
        //board layout code
        contentPane.add(nameLabel,BorderLayout.NORTH);

        contentPane.add(centerPanel,BorderLayout.CENTER);
        //windows' setting
        this.setBounds(0, 0, 738, 685);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setLayout(null);
    }

    //This method does nothing other than Layout settings.
    private void LayoutCenter() {
        //Spring Layout
        Spring Width = Spring.width(pvpBtn);
        int offsetX = Width.getValue() / 2;

        //Layout pvp Button
        springlayout.putConstraint(SpringLayout.WEST, pvpBtn, -offsetX,
                SpringLayout.HORIZONTAL_CENTER, centerPanel);
        springlayout.putConstraint(SpringLayout.NORTH, pvpBtn, 30,
                SpringLayout.NORTH, centerPanel);

        // pvc button
        springlayout.putConstraint(SpringLayout.WEST, pvcBtn, -offsetX,
                SpringLayout.HORIZONTAL_CENTER, centerPanel);
        springlayout.putConstraint(SpringLayout.NORTH, pvcBtn, 30,
                SpringLayout.SOUTH, pvpBtn);
        // board button
        springlayout.putConstraint(SpringLayout.WEST, boardBtn, -offsetX,
                SpringLayout.HORIZONTAL_CENTER, centerPanel);
        springlayout.putConstraint(SpringLayout.NORTH, boardBtn, 30,
                SpringLayout.SOUTH, pvcBtn);
        // board button
        springlayout.putConstraint(SpringLayout.WEST, backBtn, -offsetX,
                SpringLayout.HORIZONTAL_CENTER, centerPanel);
        springlayout.putConstraint(SpringLayout.NORTH, backBtn, 30,
                SpringLayout.SOUTH, boardBtn);
    }

}
