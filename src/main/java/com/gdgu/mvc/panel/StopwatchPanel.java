package com.gdgu.mvc.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import com.gdgu.mvc.util.Settings;

public class StopwatchPanel extends JPanel {
    private boolean air = false;
    private boolean started = false;

    private JPanel topPanel, bottomPanel;
    private JLabel timeLabel, startLabel, resetLabel;

    private int elapsedTime = 0, seconds = 0, minutes = 0, hours = 0;
    
    private String seconds_string = String.format("%02d", seconds);
    private String minutes_string = String.format("%02d", minutes);
    private String hours_string = String.format("%02d", hours);
    private Timer timer;

    public StopwatchPanel() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(230, 50));
        this.setBorder(new EmptyBorder(3, 3, 3, 3));
        this.setBackground(Settings.BACKGROUND);

        timer = new Timer(1000,new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                elapsedTime=elapsedTime+1000;
                hours = (elapsedTime/3600000);
                minutes = (elapsedTime/60000) % 60;
                seconds = (elapsedTime/1000) % 60;
                seconds_string = String.format("%02d", seconds);
                minutes_string = String.format("%02d", minutes);
                hours_string = String.format("%02d", hours);
                timeLabel.setText(hours_string+" : "+minutes_string+" : "+seconds_string);
            }
        });

        topPanel = new JPanel();
        topPanel.setBackground(Settings.BACKGROUND);

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2));
        bottomPanel.setBackground(Settings.BACKGROUND);

        timeLabel = new JLabel();
        timeLabel.setFont(new Font(Settings.FONTSTYLE, Font.BOLD, 15));
        timeLabel.setForeground(Settings.FOREGROUND);

        startLabel = new JLabel("Start");
        startLabel.setOpaque(true);
        startLabel.setBackground(Settings.BACKGROUND);
        startLabel.setHorizontalAlignment(JLabel.CENTER);
        startLabel.setFont(new Font(Settings.FONTSTYLE, Font.BOLD, 15));
        startLabel.setForeground(Settings.FOREGROUND);
        startLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (!started) {
                    started = true;
                    timer.start();
                    startLabel.setText("Stop");
                }
                else {
                    started = false;
                    timer.stop();
                    startLabel.setText("Start");
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                startLabel.setForeground(Settings.BACKGROUND);
                startLabel.setBackground(Settings.FOREGROUND);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                startLabel.setForeground(Settings.FOREGROUND);
                startLabel.setBackground(Settings.BACKGROUND);
            }
        });

        resetLabel = new JLabel("Reset");
        resetLabel.setOpaque(true);
        resetLabel.setBackground(Settings.BACKGROUND);
        resetLabel.setHorizontalAlignment(JLabel.CENTER);
        resetLabel.setFont(new Font(Settings.FONTSTYLE, Font.BOLD, 15));
        resetLabel.setForeground(Settings.FOREGROUND);
        resetLabel.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                timer.stop();
                startLabel.setText("Start");
                elapsedTime = 0;
                hours = 0;
                minutes = 0;
                seconds = 0;
                started = false;
                seconds_string = String.format("%02d", seconds);
                minutes_string = String.format("%02d", minutes);
                hours_string = String.format("%02d", hours);
                timeLabel.setText(hours_string+" : "+minutes_string+" : "+seconds_string);
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                resetLabel.setForeground(Settings.BACKGROUND);
                resetLabel.setBackground(Settings.FOREGROUND);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                resetLabel.setForeground(Settings.FOREGROUND);
                resetLabel.setBackground(Settings.BACKGROUND);
            }
        });

        topPanel.add(timeLabel);
        bottomPanel.add(startLabel);
        bottomPanel.add(resetLabel);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(bottomPanel, BorderLayout.CENTER);

        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
        hours_string = String.format("%02d", hours);
        timeLabel.setText(hours_string+" : "+minutes_string+" : "+seconds_string);
    }

    public void setAir(boolean air) {
        this.air= air;
    }

    public boolean getAir() {
        return air;
    }
}