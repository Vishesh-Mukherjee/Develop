package com.gdgu.mvc.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import com.gdgu.mvc.panel.ClockPanel;
import com.gdgu.mvc.util.Settings;

public class ClockListener implements MouseListener {

    private ClockPanel panel;
    private JLabel label;

    public ClockListener(ClockPanel panel, JLabel label) {
        this.panel = panel;
        this.label = label;
    } 

    @Override
    public void mouseClicked(MouseEvent e) {
        panel.updateCounter();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        panel.updateTimeFormat("d MMMM yyyy '||' hh:mm:ss a");
        label.setForeground(Settings.BACKGROUND);
        panel.setBackground(Settings.FOREGROUND);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        panel.updateTimeFormat("EEE  ||  MMM d  ||  HH:mm");
        label.setForeground(Settings.FOREGROUND);
        panel.setBackground(Settings.BACKGROUND);
    }
    
}
