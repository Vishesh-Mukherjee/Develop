package com.gdgu.mvc;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.event.*;
import java.util.Random;
import java.awt.*;

public class TicTacToePanel extends JPanel implements ActionListener{

    private boolean air = false;

    private JPanel labelPanel, buttonPanel;
    private JLabel label;
    MyButton[] button = new MyButton[9];

    Random random = new Random();

    boolean xTurn;

    public TicTacToePanel() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(230, 140));
        this.setBorder(new EmptyBorder(3, 3, 3, 3));
        this.setBackground(Settings.BACKGROUND);

        labelPanel = new JPanel();
        labelPanel.setBackground(Color.BLACK);
        labelPanel.setLayout(new BorderLayout());
        labelPanel.setBorder(new LineBorder(Settings.BORDER_COLOR, 1, true));
    
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3));
        buttonPanel.setBackground(Settings.BORDER_COLOR);

        label = new JLabel();
        label.setFont(new Font(Settings.FONTSTYLE, Font.PLAIN, 15));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(Settings.FOREGROUND);
        label.addMouseListener(new MouseListener() {
            String lastmsg;
            @Override
            public void mouseClicked(MouseEvent e) {
                reset();
            }
            @Override 
            public void mouseEntered(MouseEvent e) {
                lastmsg = label.getText();
                label.setText("Reset");
                labelPanel.setBackground(Settings.FOREGROUND);
                label.setBackground(Settings.FOREGROUND);
                label.setForeground(Settings.BACKGROUND);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                label.setText(lastmsg);
                labelPanel.setBackground(Settings.BACKGROUND);
                label.setBackground(Settings.BACKGROUND);
                label.setForeground(Settings.FOREGROUND);
            }
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) { }
        });

        initializeButton();

        labelPanel.add(label);
        add(labelPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        
        firstTurn();
    }

    private void initializeButton() {
        for (int i = 0; i < 9; i++) {
            button[i] = new MyButton();
            button[i].getButton().setBackground(Settings.BACKGROUND);
            button[i].getButton().setFont(new Font(Settings.FONTSTYLE, Font.BOLD, 20));
            button[i].getButton().setForeground(Settings.FOREGROUND);
            button[i].getButton().setBorder(new LineBorder(Settings.BORDER_COLOR, 1, true));
            button[i].getButton().addActionListener(this);    
            buttonPanel.add(button[i].getButton());
        }
    }

    void firstTurn() {
        if (random.nextInt(2) == 0) {
            label.setText("X Turn");
            xTurn = true;
        }
        else {
        label.setText("O Turn");
        xTurn = false;
        }
    }

    void check() {
        if (button[0].getButton().getText().equals("O") && button[1].getButton().getText().equals("O") && button[2].getButton().getText().equals("O")) {
            oWins(0, 1, 2);
        }
        if (button[3].getButton().getText().equals("O") && button[4].getButton().getText().equals("O") && button[5].getButton().getText().equals("O")) {
            oWins(3, 4, 5);
        }
        if (button[6].getButton().getText().equals("O") && button[7].getButton().getText().equals("O") && button[8].getButton().getText().equals("O")) {
            oWins(6, 7, 8);
        }
        if (button[0].getButton().getText().equals("O") && button[3].getButton().getText().equals("O") && button[6].getButton().getText().equals("O")) {
            oWins(0, 3, 6);
        }
        if (button[1].getButton().getText().equals("O") && button[4].getButton().getText().equals("O") && button[7].getButton().getText().equals("O")) {
            oWins(1, 4, 7);
        }
        if (button[2].getButton().getText().equals("O") && button[5].getButton().getText().equals("O") && button[8].getButton().getText().equals("O")) {
            oWins(2, 5, 8);
        }
        if (button[0].getButton().getText().equals("O") && button[4].getButton().getText().equals("O") && button[8].getButton().getText().equals("O")) {
            oWins(0, 4, 8);
        }
        if (button[2].getButton().getText().equals("O") && button[4].getButton().getText().equals("O") && button[6].getButton().getText().equals("O")) {
            oWins(2, 4, 6);
        }


        if (button[0].getButton().getText().equals("X") && button[1].getButton().getText().equals("X") && button[2].getButton().getText().equals("X")) {
            xWins(0, 1, 2);
        }
        if (button[3].getButton().getText().equals("X") && button[4].getButton().getText().equals("X") && button[5].getButton().getText().equals("X")) {
            xWins(3, 4, 5);
        }
        if (button[6].getButton().getText().equals("X") && button[7].getButton().getText().equals("X") && button[8].getButton().getText().equals("X")) {
            xWins(6, 7, 8);
        }
        if (button[0].getButton().getText().equals("X") && button[3].getButton().getText().equals("X") && button[6].getButton().getText().equals("X")) {
            xWins(0, 3, 6);
        }
        if (button[1].getButton().getText().equals("X") && button[4].getButton().getText().equals("X") && button[7].getButton().getText().equals("X")) {
            xWins(1, 4, 7);
        }
        if (button[2].getButton().getText().equals("X") && button[5].getButton().getText().equals("X") && button[8].getButton().getText().equals("X")) {
            xWins(2, 5, 8);
        }
        if (button[0].getButton().getText().equals("X") && button[4].getButton().getText().equals("X") && button[8].getButton().getText().equals("X")) {
            xWins(0, 4, 8);
        }
        if (button[2].getButton().getText().equals("X") && button[4].getButton().getText().equals("X") && button[6].getButton().getText().equals("X")) {
            xWins(2, 4, 6);
        }
    }

    void xWins(int a, int b, int c) {
        button[a].getButton().setBackground(Settings.FOREGROUND);
        button[b].getButton().setBackground(Settings.FOREGROUND);
        button[c].getButton().setBackground(Settings.FOREGROUND);

        for (int i = 0; i < 9; i ++) {
            button[i].getButton().setEnabled(false);
            button[i].removeAction();
        }

        label.setText("X Wins");
    }

    void oWins(int a, int b, int c) {
        button[a].getButton().setBackground(Settings.FOREGROUND);
        button[b].getButton().setBackground(Settings.FOREGROUND);
        button[c].getButton().setBackground(Settings.FOREGROUND);

        for (int i = 0; i < 9; i ++) {
            button[i].getButton().setEnabled(false);
            button[i].removeAction();
        }
        label.setText("O Wins");
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        for (int i = 0; i < 9; i++) {
            if (evt.getSource() == button[i].getButton()) {
                if (button[i].getButton().getText().equals("")) {
                    if (xTurn) {
                        button[i].getButton().setText("X");
                        label.setText("O Turn");
                        xTurn = false;
                        check();
                    } 
                    else {
                        button[i].getButton().setText("O");
                        label.setText("X Turn");
                        xTurn = true;
                        check();
                    }
                }
            }
        }
    }

    private void reset() {
        for (int i = 0; i < 9; i++) {
            buttonPanel.remove(button[i].getButton());
        }
        initializeButton();
        firstTurn();
    }

    public boolean getAir() {
        return air;
    }

    public void setAir(boolean air) {
        this.air = air;
    }    
}

class MyButton {
    private JButton button;
    private MouseAdapter mouseAdapter;

    public MyButton() {
        button = new JButton();
        mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Settings.FOREGROUND); 
                button.setForeground(Settings.BACKGROUND);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(Settings.BACKGROUND);
                button.setForeground(Settings.FOREGROUND);
            }
        };
        button.addMouseListener(mouseAdapter);
    }

    public void removeAction() {
        button.removeMouseListener(mouseAdapter);
    }

    public JButton getButton() {
        return button;
    }
}