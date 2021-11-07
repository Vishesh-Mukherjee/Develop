package com.gdgu.mvc;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;

public class ClockPanel extends JPanel{

    private JLabel clockLabel = new JLabel();

    private boolean air = false;

    public ClockPanel() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(230, 25));
        this.setBorder(new EmptyBorder(3, 3, 3, 3));
        this.setBackground(Settings.BACKGROUND);

        clockLabel.setForeground(Settings.FOREGROUND);
        clockLabel.setBorder(new LineBorder(Settings.BORDER_COLOR)); 
        clockLabel.setFont(new Font(Settings.FONTSTYLE, Font.BOLD, 12));
        clockLabel.setHorizontalAlignment(JLabel.CENTER);

        SimpleDateFormat clock = new SimpleDateFormat("EEE  ||  MMM d  ||  HH:mm");
        clockLabel.addMouseListener(new ClockAction(this, clockLabel, clock));

        Timer timer = new Timer(1000, (e) -> clockLabel.setText(clock.format(Calendar.getInstance().getTime())));
        timer.start();

        this.add(clockLabel);
    }

    public void setAir(boolean air) {
        this.air= air;
    }

    public boolean getAir() {
        return air;
    }
}

class ClockAction implements MouseListener {

    private JPanel panel;
    private JLabel label;
    private SimpleDateFormat dateFormat;

    public ClockAction(JPanel panel, JLabel label, SimpleDateFormat dateFormat) {
        this.panel = panel;
        this.label = label;
        this.dateFormat = dateFormat;
    } 

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        dateFormat.applyPattern("d MMMMM yyyy || hh:mm:ss aaa");
        label.setForeground(Settings.BACKGROUND);
        panel.setBackground(Settings.FOREGROUND);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        dateFormat.applyPattern("EEE  ||  MMM d  ||  HH:mm");
        label.setForeground(Settings.FOREGROUND);
        panel.setBackground(Settings.BACKGROUND);
    }
    
}