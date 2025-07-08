package com.gdgu.mvc.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.gdgu.mvc.entity.Task;
import com.gdgu.mvc.service.DatabaseService;

public class Action implements ActionListener {

    private JButton button;
    private State state;
    private DatabaseService database;
    private int id;

    public Action(JButton button, Task task) {
        this.id = task.getId();
        this.database = new DatabaseService();
        this.button = button;
        this.state = task.getState();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (state == State.UNKNOWN) {
            button.setBackground(Settings.ATTENDED);
            state = State.ATTENDED;
            database.updateTask(id, State.ATTENDED);
            return;
        } 
        if (state == State.ATTENDED) {   
            button.setBackground(Settings.NOT_ATTENDED);
            state = State.NOT_ATTENDED;
            database.updateTask(id, State.NOT_ATTENDED);
            return;
        }
        button.setBackground(Settings.UNKNOWN);
        state = State.UNKNOWN;
        database.updateTask(id, State.UNKNOWN);
    }
}
