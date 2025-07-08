package com.gdgu.mvc.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.gdgu.mvc.listener.ClockListener;
import com.gdgu.mvc.util.Settings;

public class ClockPanel extends JPanel{

    private JLabel clockLabel = new JLabel();

    private boolean air = false;

    private String timeFormat = "EEE  ||  MMM d  ||  HH:mm";

    private int counter = 0;

    public void updateCounter() {
        counter ++;
        if (counter > 2) {
            counter = 0;
        }
    }

    public void updateTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }

    public ClockPanel() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(230, 25));
        this.setBorder(new EmptyBorder(3, 3, 3, 3));
        this.setBackground(Settings.BACKGROUND);

        clockLabel.setForeground(Settings.FOREGROUND);
        clockLabel.setBorder(new LineBorder(Settings.BORDER_COLOR)); 
        clockLabel.setFont(new Font(Settings.FONTSTYLE, Font.BOLD, 12));
        clockLabel.setHorizontalAlignment(JLabel.CENTER);

        clockLabel.addMouseListener(new ClockListener(this, clockLabel));

        Timer timer = new Timer(1000, (e) -> {
            clockLabel.setText(getTime());
        });
        timer.start();

        this.add(clockLabel);
    }

    public void setAir(boolean air) {
        this.air= air;
    }

    public boolean getAir() {
        return air;
    }

    public String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timeFormat);
        if (counter == 0) {
            return LocalDateTime.now().format(formatter);
        }
        if (counter == 1) {
            return ZonedDateTime.now(ZoneId.of("Europe/Amsterdam")).format(formatter);
        }
        return ZonedDateTime.now(ZoneId.of("UTC")).format(formatter);
    }
}
