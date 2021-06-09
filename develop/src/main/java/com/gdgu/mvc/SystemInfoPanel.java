package com.gdgu.mvc;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.util.List;

public class SystemInfoPanel extends JPanel{
    
    private JTextArea systemInfoArea = new JTextArea();

    private boolean air = false;

    public SystemInfoPanel() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(230, 170));
        this.setBorder(new EmptyBorder(3, 3, 3, 3));
        this.setBackground(Setting.BACKGROUND);

        systemInfoArea.setForeground(Setting.FOREGROUND);
        systemInfoArea.setFont(new Font(Setting.FONTSTYLE, Font.BOLD, 13));
        systemInfoArea.setLineWrap(true);
        systemInfoArea.setEditable(false);
        systemInfoArea.setBorder(new LineBorder(Setting.BORDER_COLOR));
        systemInfoArea.setBackground(Setting.BACKGROUND);

        this.add(systemInfoArea, BorderLayout.CENTER);
    }

    public JTextArea getSystemInfoArea() {
        return this.systemInfoArea;
    }

    public void setSystemInfoArea(JTextArea systemInfoArea) {
        this.systemInfoArea = systemInfoArea;
    }

    public void setAir(boolean air) {
        this.air= air;
    }

    public boolean getAir() {
        return air;
    }

    public void setSystemInfo(List<String> systemInfo) {
        systemInfoArea.setText("");
        for(int i = 0; i < systemInfo.size(); i ++) {  
        systemInfoArea.append("-> " + systemInfo.get(i));
        systemInfoArea.append("\n"); 
        }
    }
}
