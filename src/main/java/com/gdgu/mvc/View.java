package com.gdgu.mvc;

import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;

import com.gdgu.mvc.panel.ClockPanel;
import com.gdgu.mvc.panel.NotebookPanel;
import com.gdgu.mvc.panel.PanelFactory;
import com.gdgu.mvc.panel.RequestPanel;
import com.gdgu.mvc.panel.StopwatchPanel;
import com.gdgu.mvc.panel.TipPanel;
import com.gdgu.mvc.panel.TrackerPannel;
import com.gdgu.mvc.util.Settings;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class View {
    private Controller controller;

    private JFrame developFrame = new JFrame();

    private int x, y, xMouse, yMouse;
    private static int heightCount;

    public View(Controller controller) {
        this.controller = controller;
        developFrame.setLayout(new BoxLayout(developFrame.getContentPane(), BoxLayout.Y_AXIS));
        developFrame.setUndecorated(true);
        developFrame.setOpacity(0.85f);
        developFrame.setAlwaysOnTop(true);
        developFrame.getContentPane().setBackground(Color.BLACK);
        developFrame.setVisible(true);
        developFrame.setSize(getDevelopDimension(PanelFactory.getRequestPanel(), true));
        developFrame.setLocationRelativeTo(null);
        developFrame.add(PanelFactory.getRequestPanel());
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
        PanelFactory.getRequestPanel().getRequestField().addKeyListener(new KeyAdapter() {
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

    private void notifyController() {
        controller.updateControllerView(PanelFactory.getRequestPanel().getRequestField().getText());
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

    public void attachClock() {
        ClockPanel clockPanel = PanelFactory.getClockPanel();
        if (!clockPanel.getAir()) {
            developFrame.setSize(getDevelopDimension(clockPanel, true));
            developFrame.add(clockPanel);
            clockPanel.setAir(true);
        }
    }

    public void detachClock() {
        ClockPanel clockPanel = PanelFactory.getClockPanel();
        if (clockPanel.getAir()) {
            developFrame.remove(clockPanel);
            developFrame.setSize(getDevelopDimension(clockPanel, false));
            clockPanel.setAir(false);
        }
    }

    public void attackTip() {
        TipPanel tipPanel = PanelFactory.getTipPanel();
        if (!tipPanel.getAir()) {
            developFrame.setSize(getDevelopDimension(tipPanel, true));
            developFrame.add(tipPanel);
            tipPanel.setAir(true);
        }
    }

    public void detachTip() {
        TipPanel tipPanel = PanelFactory.getTipPanel();
        if (tipPanel.getAir()) {
            developFrame.remove(tipPanel);
            developFrame.setSize(getDevelopDimension(tipPanel, false));
            tipPanel.setAir(false);
        }
    }

    public void attachNotebook() {                                                  
        NotebookPanel notebookPanel = PanelFactory.getNotebookPanel();
        developFrame.setSize(getDevelopDimension(notebookPanel, true));
        developFrame.add(notebookPanel);
    }

    public void detachNoteBook() {
        NotebookPanel notebookPanel = PanelFactory.getNotebookPanel();
        developFrame.remove(notebookPanel);
        developFrame.setSize(getDevelopDimension(notebookPanel, false));
    }

    public void setNotes(List<String> notes) {
        NotebookPanel notebookPanel = PanelFactory.getNotebookPanel();
        RequestPanel requestPanel = PanelFactory.getRequestPanel();
        if (notes != null) {
            requestPanel.getRequestField().setText("");
            notebookPanel.setNotes(notes);
        }
    }

    public void addTask(String task) {
    }

    public void attachStopwatch() {
        StopwatchPanel watchPanel = PanelFactory.getStopWatchPanel();
        if (!watchPanel.getAir()) {
            developFrame.setSize(getDevelopDimension(watchPanel, true));
            developFrame.add(watchPanel);
            watchPanel.setAir(true);
        }
    }

    public void detachstopwatch() {
        StopwatchPanel watchPanel = PanelFactory.getStopWatchPanel();
        RequestPanel requestPanel = PanelFactory.getRequestPanel();
        if (watchPanel.getAir()) {
            requestPanel.getRequestField().setText("");
            developFrame.remove(watchPanel);
            developFrame.setSize(getDevelopDimension(watchPanel, false));
            watchPanel.setAir(false);
        }
    }

    public void attachTask() {
        TrackerPannel batteryPanel = PanelFactory.getTaskPanel();
        if (!batteryPanel.getAir()) {
            developFrame.setSize(getDevelopDimension(batteryPanel, true));
            developFrame.add(batteryPanel);
            batteryPanel.setAir(true);
        }
    }

    public void detachTask() {
        TrackerPannel taskPanel = PanelFactory.getTaskPanel();
        RequestPanel requestPanel = PanelFactory.getRequestPanel();
        if (taskPanel.getAir()) {
            requestPanel.getRequestField().setText("");
            developFrame.remove(taskPanel);
            developFrame.setSize(getDevelopDimension(taskPanel, false));
            taskPanel.setAir(false);
        }
    }

    public void handleTask() {

    }

    public void inExecution(boolean bool) {
        RequestPanel requestPanel = PanelFactory.getRequestPanel();
        requestPanel.getRequestField().setText("");
        if (bool) {
            ((JComponent)developFrame.getContentPane()).setBorder(new LineBorder(Settings.BORDER_COLOR));
        } else {
            ((JComponent)developFrame.getContentPane()).setBorder(BorderFactory.createEmptyBorder());
        }
    }

    public void clearDisplay() {
        RequestPanel requestPanel = PanelFactory.getRequestPanel();
        requestPanel.getRequestField().setText("");
    }

    public void setResult(String result) {  
        RequestPanel requestPanel = PanelFactory.getRequestPanel();
        if (result != null) {
            requestPanel.getRequestField().setText(result);
        }
    }
}