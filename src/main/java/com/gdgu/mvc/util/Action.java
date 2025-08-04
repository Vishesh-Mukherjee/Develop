package com.gdgu.mvc.util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import com.gdgu.mvc.entity.Task;
import com.gdgu.mvc.service.DatabaseService;

public class Action implements MouseListener {

    private JLabel button;
    private char currentState;
    private int index;
    private DatabaseService database;
    private int id;
    private Task task;

    public Action(JLabel button, Task task, char currentState, int index) {
        this.id = task.getId();
        this.task = task;
        this.database = new DatabaseService();
        this.button = button;
        this.currentState = currentState;
        this.index = index;
    }

    public State getNextState() {
        State state = State.getState(currentState);
        switch (state) {
            case IRRELEVANT:
                return State.NOT_STARTED;
            case NOT_STARTED:
                return State.IN_PROGRESS;
            case IN_PROGRESS:
                return State.COMPLETED;
            case COMPLETED:
                return State.IRRELEVANT;
            default:
                return State.IRRELEVANT;
        }

    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        State nextState = getNextState();
        currentState = nextState.getCharRepresentation(); 
        button.setBackground(nextState.getAssociateColor());  
        String updatedState = task.getStates().substring(0, index) + currentState + task.getStates().substring(index + 1);
        database.updateTask(id, updatedState);
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
    }
}
