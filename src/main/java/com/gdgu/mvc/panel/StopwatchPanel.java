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

import com.gdgu.mvc.util.Configuration;

public class StopwatchPanel extends AbstractPanel {
    private boolean started = false;

    private JPanel topPanel, bottomPanel;
    private JLabel timeLabel, startLabel, resetLabel;

    private int elapsedTime = 0, seconds = 0, minutes = 0, hours = 0;
    
    private String seconds_string = String.format("%02d", seconds);
    private String minutes_string = String.format("%02d", minutes);
    private String hours_string = String.format("%02d", hours);
    private Timer timer;

    public StopwatchPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(Configuration.HORIZONTAL_WIDTH, 50));
        setBorder(new EmptyBorder(3, 3, 3, 3));
        setBackground(Configuration.BACKGROUND);

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
        topPanel.setBackground(Configuration.BACKGROUND);

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2));
        bottomPanel.setBackground(Configuration.BACKGROUND);

        timeLabel = new JLabel();
        timeLabel.setFont(new Font(Configuration.FONTSTYLE, Font.BOLD, 15));
        timeLabel.setForeground(Configuration.FOREGROUND);

        startLabel = new JLabel("Start");
        startLabel.setOpaque(true);
        startLabel.setBackground(Configuration.BACKGROUND);
        startLabel.setHorizontalAlignment(JLabel.CENTER);
        startLabel.setFont(new Font(Configuration.FONTSTYLE, Font.BOLD, 15));
        startLabel.setForeground(Configuration.FOREGROUND);
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
                startLabel.setForeground(Configuration.BACKGROUND);
                startLabel.setBackground(Configuration.FOREGROUND);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                startLabel.setForeground(Configuration.FOREGROUND);
                startLabel.setBackground(Configuration.BACKGROUND);
            }
        });

        resetLabel = new JLabel("Reset");
        resetLabel.setOpaque(true);
        resetLabel.setBackground(Configuration.BACKGROUND);
        resetLabel.setHorizontalAlignment(JLabel.CENTER);
        resetLabel.setFont(new Font(Configuration.FONTSTYLE, Font.BOLD, 15));
        resetLabel.setForeground(Configuration.FOREGROUND);
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
                resetLabel.setForeground(Configuration.BACKGROUND);
                resetLabel.setBackground(Configuration.FOREGROUND);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                resetLabel.setForeground(Configuration.FOREGROUND);
                resetLabel.setBackground(Configuration.BACKGROUND);
            }
        });

        topPanel.add(timeLabel);
        bottomPanel.add(startLabel);
        bottomPanel.add(resetLabel);

        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.CENTER);

        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
        hours_string = String.format("%02d", hours);
        timeLabel.setText(hours_string+" : "+minutes_string+" : "+seconds_string);
    }

    @Override
    public void setAir(boolean air) {
        this.air= air;
    }

    @Override
    public boolean getAir() {
        return air;
    }

    @Override
    public void attackAndExecute() {
        // Nothing to execute
    }
}