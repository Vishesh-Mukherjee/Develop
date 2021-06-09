package com.gdgu.mvc;

import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class View {
    private Controller controller;

    private JFrame developFrame = new JFrame();

    private RequestPanel requestPanel = new RequestPanel();
    private NotebookPanel notebookPanel = new NotebookPanel();
    private ClockPanel clockPanel = new ClockPanel();
    private SystemInfoPanel systemInfoPanel = new SystemInfoPanel();

    private int x, y, xMouse, yMouse;
    private static int heightCount;

    public View() {
        developFrame.setLayout(new BoxLayout(developFrame.getContentPane(), BoxLayout.Y_AXIS));
        developFrame.setUndecorated(true);
        developFrame.setOpacity(0.85f);
        developFrame.setAlwaysOnTop(true);
        developFrame.getContentPane().setBackground(Color.BLACK);
        developFrame.setVisible(true);
        developFrame.setSize(getDevelopDimension(requestPanel, true));
        developFrame.add(requestPanel);
        developFrame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                xMouse = evt.getX();
                yMouse = evt.getY();
            }         
        });
        developFrame.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent evt) {
                x = evt.getXOnScreen();
                y = evt.getYOnScreen();
                developFrame.setLocation(x-xMouse, y-yMouse);
            }
        });
        requestPanel.getRequestField().addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {                          
                    notifyController();
                }                               
            }
        });
    }

    public Controller getController() {
        return this.controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    private void notifyController() {
        controller.updateControllerView(requestPanel.getRequestField().getText());
    }

    private Dimension getDevelopDimension(JPanel panel, boolean bool) {
        Dimension dimension;
        if (bool) {
            dimension = new Dimension((int) panel.getPreferredSize().getWidth(), 
                                 (int) (heightCount + panel.getPreferredSize().getHeight()));
            heightCount = (int) dimension.getHeight();
            return dimension;
        } else {
            dimension = new Dimension((int) panel.getPreferredSize().getWidth(), 
                                 (int) (heightCount - panel.getPreferredSize().getHeight()));
            heightCount = (int) dimension.getHeight();
            return dimension;
        }
    } 

    public void setClockState(boolean bool) {
        clockPanel.setAir(bool);
    }

    public void attachClock() {
        developFrame.setSize(getDevelopDimension(clockPanel, true));
        developFrame.add(clockPanel);
    }

    public void detachClock() {
        if (clockPanel.getAir()) {
            developFrame.remove(clockPanel);
            developFrame.setSize(getDevelopDimension(clockPanel, false));
            clockPanel.setAir(false);
        }
    }

    public void attachNotebook() {                                                  
        developFrame.setSize(getDevelopDimension(notebookPanel, true));
        developFrame.add(notebookPanel);
    }

    public void detachNoteBook() {
        developFrame.remove(notebookPanel);
        developFrame.setSize(getDevelopDimension(notebookPanel, false));
    }

    public void setNotes(List<String> notes) {
        if (notes != null) {
            requestPanel.getRequestField().setText("");
            notebookPanel.setNotes(notes);
        }
    }

    public void setSystemInfoState(boolean bool) {
        systemInfoPanel.setAir(bool);
    }

    public void attachSystemInfo() {
        developFrame.setSize(getDevelopDimension(systemInfoPanel, true));
        developFrame.add(systemInfoPanel);
    }

    public void detachSystemInfo() {
        if (systemInfoPanel.getAir()) {
        requestPanel.getRequestField().setText("");
        developFrame.remove(systemInfoPanel);
        developFrame.setSize(getDevelopDimension(systemInfoPanel, false));
        systemInfoPanel.setAir(false);
        }
    }

    public void setSystemInfo(List<String> systemInfo) {
        requestPanel.getRequestField().setText("");
        systemInfoPanel.setSystemInfo(systemInfo);
    }

    public void inExecution(boolean bool) {
        requestPanel.getRequestField().setText("");
        if (bool) {
            ((JComponent)developFrame.getContentPane()).setBorder(new LineBorder(Setting.BORDER_COLOR));
        } else {
            ((JComponent)developFrame.getContentPane()).setBorder(BorderFactory.createEmptyBorder());
        }
    }

    public void clearDisplay() {
        requestPanel.getRequestField().setText("");
    }

    public void setResult(String result) {  
        if (result != null) {
            requestPanel.getRequestField().setText(result);
        }
    }
}