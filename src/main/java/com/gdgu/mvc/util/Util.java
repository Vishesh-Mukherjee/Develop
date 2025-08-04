package com.gdgu.mvc.util;

import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import java.awt.Font;

public class Util {

    public static JLabel getLabel(String text) {
        JLabel label = new JLabel(text);
        label.setOpaque(true);
        label.setFont(new Font(Configuration.FONTSTYLE, Font.BOLD, 12));
        label.setBackground(Configuration.BACKGROUND);
        label.setBorder(new EmptyBorder(3, 3, 3, 3));
        label.setForeground(Configuration.FOREGROUND);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setText("-");
        return label;
    }

}
