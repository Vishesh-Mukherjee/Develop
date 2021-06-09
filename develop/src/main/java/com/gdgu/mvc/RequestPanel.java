package com.gdgu.mvc;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;

public class RequestPanel extends JPanel{
    private JTextField requestField = new JTextField();;

    public RequestPanel() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(230, 28));
        this.setBorder(new EmptyBorder(3, 3, 3, 3));
        this.setBackground(Color.BLACK);

        requestField.setBackground(Setting.BACKGROUND);
        requestField.setForeground(Setting.FOREGROUND);
        requestField.setCaretColor(Setting.CARET_COLOR); 
        requestField.setBorder(new LineBorder(Setting.BORDER_COLOR)); 
        requestField.setFont(new Font(Setting.FONTSTYLE, Font.BOLD, 14));

        this.add(requestField, BorderLayout.CENTER);
    }

    public JTextField getRequestField() {
        return this.requestField;
    }

    public void setRequestField(JTextField requestField) {
        this.requestField = requestField;
    }
}