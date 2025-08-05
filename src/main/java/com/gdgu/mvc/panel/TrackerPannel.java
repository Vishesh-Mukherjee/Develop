package com.gdgu.mvc.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.gdgu.mvc.entity.Task;
import com.gdgu.mvc.service.DatabaseService;
import com.gdgu.mvc.util.Action;
import com.gdgu.mvc.util.Configuration;
import com.gdgu.mvc.util.State;
import com.gdgu.mvc.util.Util;

public class TrackerPannel extends JPanel {
    private boolean air;

    private DatabaseService database;

    private final List<String> ROW_HEADER = Arrays.asList("-", "PAY", "RULE", "ROUTE", "PROF", "REP", "WRAP", "FRON");
    private final List<String> COLUMN_HEADER = Arrays.asList("-", "DEV", "QA", "PROD");

    private int currentPageCount = 0;

    private List<List<JLabel>> taskButtons;

    private List<Task> tasks;

    public TrackerPannel() {

        database = new DatabaseService();
        taskButtons = new ArrayList<>();

        intializeTaskMap();
        tasks = database.getTasks();

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(3, 3, 3, 3));
        setBackground(Configuration.BACKGROUND);
        
        setLayout(new BorderLayout());
        setBackground(Configuration.BACKGROUND);

        add(getTopPanel(), BorderLayout.NORTH);
        add(getTaskPanel(), BorderLayout.CENTER);
    }

    private void intializeTaskMap() {
        tasks = database.getTasks();
    }

    private JPanel getTopPanel() {
        final JLabel pageCountButton = Util.getLabel("" + currentPageCount);

        pageCountButton.setLayout(new BorderLayout());
        pageCountButton.setBorder(new EmptyBorder(3, 3, 3, 3));
        pageCountButton.setBackground(Configuration.BACKGROUND);

        JLabel backwardButton = Util.getLabel("<");
        backwardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (currentPageCount > 0) {
                    currentPageCount--;
                } else {
                    currentPageCount = tasks.size()-1;
                }
                pageCountButton.setText("" + currentPageCount);
                removeAction();
                intializeTaskMap();
                updateTaskButton();
            }         

            @Override
            public void mouseEntered(MouseEvent e) {
                backwardButton.setForeground(Configuration.BACKGROUND);
                backwardButton.setBackground(Configuration.FOREGROUND);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backwardButton.setForeground(Configuration.FOREGROUND);
                backwardButton.setBackground(Configuration.BACKGROUND);
            }
        });
        JLabel forwardButton = Util.getLabel(">");
        forwardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                System.out.println(database.getTasks());
                currentPageCount++;
                if (currentPageCount >= tasks.size()) {
                    currentPageCount = 0;
                }
                pageCountButton.setText("" + currentPageCount);    
                removeAction();
                intializeTaskMap();
                updateTaskButton();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                forwardButton.setForeground(Configuration.BACKGROUND);
                forwardButton.setBackground(Configuration.FOREGROUND);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                forwardButton.setForeground(Configuration.FOREGROUND);
                forwardButton.setBackground(Configuration.BACKGROUND);
            }
            
        }); 

        pageCountButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                currentPageCount = 0;
                pageCountButton.setText("" + currentPageCount);    
                removeAction();
                updateTaskButton();
            }
        });

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 3));
        topPanel.add(backwardButton);
        topPanel.add(pageCountButton);
        topPanel.add(forwardButton); 

        return topPanel;
    }

    private JPanel getTaskPanel() {
        JPanel taskPanel = new JPanel();
        taskPanel.setLayout(new GridLayout(ROW_HEADER.size(), COLUMN_HEADER.size()));
        for (int i = 0; i < ROW_HEADER.size(); i ++) {
            List<JLabel> buttons = new ArrayList<>();
            for (int j = 0; j < COLUMN_HEADER.size(); j ++) {
                JLabel button = Util.getLabel("");
                button.setBackground(Configuration.BACKGROUND);
                button.setBorder(new EmptyBorder(3, 3, 3, 3));
                button.setForeground(Configuration.FOREGROUND);
                button.setHorizontalAlignment(JLabel.CENTER);
                button.setText("-");
                buttons.add(button);
                taskPanel.add(button);
                taskPanel.setEnabled(false);
            }
            taskButtons.add(buttons);
        }
        for (int i = 0; i < COLUMN_HEADER.size(); i ++) {
           taskButtons.get(0).get(i).setText(COLUMN_HEADER.get(i));
           taskButtons.get(0).get(i).setEnabled(false);
        }
        for (int i = 0; i < ROW_HEADER.size(); i ++) {
           taskButtons.get(i).get(0).setText(ROW_HEADER.get(i));
           taskButtons.get(i).get(0).setEnabled(false);
        }
        updateTaskButton();;
        return taskPanel;
    }

    private void updateTaskButton() {
        Task task = tasks.get(currentPageCount);
        PanelFactory.getRequestPanel().getRequestField().setText(task.getDescription());
        int index = 0;
        String states = task.getStates();
        for (int i = 1; i < ROW_HEADER.size(); i ++) {
            for (int j = 1; j < COLUMN_HEADER.size(); j ++) { 
                JLabel button = taskButtons.get(i).get(j);
                if (index < states.length()) {
                    char charRepresentation = states.charAt(index);
                    button.addMouseListener(new Action(button, task, charRepresentation, index));
                    button.setBackground(State.getColor(charRepresentation));    
                    index ++;
                }
                button.setForeground(Configuration.BACKGROUND);
            }
        }
    }

    private void removeAction() {
        for (int i = 1; i < ROW_HEADER.size(); i ++) {
            for (int j = 1; j < COLUMN_HEADER.size(); j ++) {
                JLabel button = taskButtons.get(i).get(j);
                MouseListener[] actionListenerList = button.getMouseListeners();
                for (MouseListener actionListener: actionListenerList) {
                    button.removeMouseListener(actionListener);
                }
            }
        }
    }

    public void addTask(String description) {
        database.addTask(description);
    }

    public void setAir(boolean air) {
        this.air= air;
    }

    public boolean getAir() {
        return air;
    }
}