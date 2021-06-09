package com.gdgu.mvc;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;

public class ClockPanel extends JPanel{

    private JLabel clockLabel = new JLabel();

    private boolean air = false;

    public ClockPanel() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(230, 25));
        this.setBorder(new EmptyBorder(3, 3, 3, 3));
        this.setBackground(Setting.BACKGROUND);

        clockLabel.setBackground(Setting.BACKGROUND);
        clockLabel.setForeground(Setting.FOREGROUND);
        clockLabel.setBorder(new LineBorder(Setting.BORDER_COLOR)); 
        clockLabel.setFont(new Font(Setting.FONTSTYLE, Font.BOLD, 12));
        clockLabel.setHorizontalAlignment(JLabel.CENTER);

        SimpleDateFormat clock = new SimpleDateFormat("EEE  ||  MMM d  ||  hh:mm");
        Timer timer = new Timer(1000, (e) -> clockLabel.setText(clock.format(Calendar.getInstance().getTime())));
        timer.start();

        this.add(clockLabel);
    }

    public void setClockLabel(JLabel clockLabel) {
        this.clockLabel = clockLabel;
    }

    public JLabel getClockLabel() {
        return clockLabel;
    }

    public void setAir(boolean air) {
        this.air= air;
    }

    public boolean getAir() {
        return air;
    }
}
