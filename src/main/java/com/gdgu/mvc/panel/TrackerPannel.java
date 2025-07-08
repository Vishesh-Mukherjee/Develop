package com.gdgu.mvc.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.gdgu.mvc.entity.Task;
import com.gdgu.mvc.service.DatabaseService;
import com.gdgu.mvc.util.Action;
import com.gdgu.mvc.util.Settings;
import com.gdgu.mvc.util.State;

public class TrackerPannel extends JPanel {
    private boolean air;

    private DatabaseService database;

    private int rowCount = 5, currentPageCount = 1;

    private List<JButton> taskButtons;

    public TrackerPannel() {

        database = new DatabaseService();
        taskButtons = new ArrayList<>();
        
        this.setLayout(new BorderLayout());
        this.setSize(230, 150);
        this.setBackground(Settings.BACKGROUND);

        this.add(getTopPanel(), BorderLayout.NORTH);
        this.add(getTaskPanel(), BorderLayout.CENTER);
        this.setVisible(true);
    }

    private JPanel getTopPanel() {
        final JButton pageCountButton = new JButton("" + currentPageCount);

        JButton backwardButton = new JButton("<");
        backwardButton.setBackground(Color.WHITE);
        backwardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPageCount > 1) {
                    currentPageCount--;
                    pageCountButton.setText("" + currentPageCount);
                    removeAction();
                    updateTaskButton();
                }
            }
        });

        JButton forwardButton = new JButton(">");
        forwardButton.setBackground(Color.WHITE);
        forwardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(database.getTasks(10, currentPageCount));
                currentPageCount++;
                pageCountButton.setText("" + currentPageCount);    
                removeAction();
                updateTaskButton();
            }
        });


        pageCountButton.setHorizontalAlignment(JLabel.CENTER);
        pageCountButton.setBackground(Color.WHITE);
        pageCountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentPageCount = 1;
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
        taskPanel.setLayout(new GridLayout(rowCount, 1));
        for (int i = 0; i < rowCount; i ++) {
            JButton button = new JButton();
            button.setText("-");
            taskPanel.add(button);
            taskButtons.add(button);
            taskPanel.setEnabled(false);
        }
        updateTaskButton();;
        return taskPanel;
    }

    private void updateTaskButton() {
        List<Task> tasks = database.getTasks(rowCount, currentPageCount-1);
        for (int i = 0; i < tasks.size(); i ++) {
            JButton button = taskButtons.get(i);
            Task task = tasks.get(i);
            button.setText(task.getDescription());
            button.addActionListener(new Action(button, task));
            System.out.println(task.getDescription());
            if (task.getState() == State.UNKNOWN) {
                System.out.println("SETTING TO UNKOWN");
                button.setBackground(Settings.UNKNOWN);
            } else if (task.getState() == State.ATTENDED) {
                System.out.println("SETTING TO ATTENDED");
                button.setBackground(Settings.ATTENDED);
            } else {
                System.out.println("SETTING TO NOT ATTENDED");
                button.setBackground(Settings.NOT_ATTENDED);
            }
        }
    }

    private void removeAction() {
        for (int i = 0; i < taskButtons.size(); i ++) {
            JButton button = taskButtons.get(i);
            ActionListener[] actionListenerList = button.getActionListeners();
            for (ActionListener actionListener: actionListenerList) {
                button.removeActionListener(actionListener);
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