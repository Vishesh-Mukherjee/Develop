package com.gdgu.mvc.panel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.gdgu.mvc.util.Configuration;

import java.awt.*;

public class RequestPanel extends JPanel{
    private JTextField requestField = new JTextField();;

    public RequestPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(Configuration.HORIZONTAL_WIDTH, 28));
        setBorder(new EmptyBorder(3, 3, 3, 3));
        setBackground(Color.BLACK);

        requestField.setBackground(Configuration.BACKGROUND);
        requestField.setForeground(Configuration.FOREGROUND);
        requestField.setCaretColor(Configuration.CARET_COLOR); 
        requestField.setBorder(new LineBorder(Configuration.BORDER_COLOR)); 
        requestField.setFont(new Font(Configuration.FONTSTYLE, Font.BOLD, 14));

        add(requestField, BorderLayout.CENTER);
    }

    public void setText(String text) {
        requestField.setText(text);
    }

    public JTextField getRequestField() {
        return requestField;
    }

}