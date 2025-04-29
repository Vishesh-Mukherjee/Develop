package com.gdgu.mvc;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BatteryPanel extends JPanel {
    private boolean air;
    private JLabel batteryLabel;
    private int count = 0;

    public BatteryPanel() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(230, 25));
        this.setBorder(new EmptyBorder(3, 3, 3, 3));
        this.setBackground(Settings.BACKGROUND);

        batteryLabel = new JLabel();
        batteryLabel.setForeground(Settings.FOREGROUND);
        batteryLabel.setBorder(new LineBorder(Settings.BORDER_COLOR)); 
        batteryLabel.setFont(new Font(Settings.FONTSTYLE, Font.BOLD, 10));
        batteryLabel.setHorizontalAlignment(JLabel.CENTER);

        batteryLabel.setBackground(Settings.BACKGROUND);
        batteryLabel.setForeground(Settings.FOREGROUND);
        batteryLabel.setBorder(new LineBorder(Settings.BORDER_COLOR)); 
        batteryLabel.setFont(new Font(Settings.FONTSTYLE, Font.BOLD, 12));
        batteryLabel.setHorizontalAlignment(JLabel.LEFT);

        Timer timer = new Timer(1000, (e) -> batteryLabel.setText(calculate()));
        timer.start();

        this.add(batteryLabel);
    }

    private String calculate() {
        String result = " Battery Status: ";

        int batteryStatus = 0;

        switch (batteryStatus) {
            case (0):
                if (count == 0) {
                    result += "Depleting";
                    count ++;
                } else if (count == 1) {
                    result += "Depleting.";
                    count ++;
                } else if (count == 2) {
                    result += "Depleting..";
                    count ++;
                } else {
                    result += "Depleting...";
                    count = 0;
                } break;
            case (1): 
                if (count == 0) {
                    result += "Charging";
                    count ++;
                }  else if (count == 1) {
                    result += "Charging.";
                    count ++;
                } else if (count == 2) {
                    result += "Charging..";
                    count ++;
                } else {
                    result += "Charging...";
                    count = 0;
                } break;
            default: result += "Unknown";
        }

        return result;
    }

    public void setAir(boolean air) {
        this.air= air;
    }

    public boolean getAir() {
        return air;
    }
}