package com.gdgu.mvc.panel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.gdgu.mvc.util.Settings;

import java.awt.*;
import java.util.List;

public class NotebookPanel extends JPanel{
    
    private JTextArea noteArea = new JTextArea();

    public NotebookPanel() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(230, 120));
        this.setBorder(new EmptyBorder(3, 3, 3, 3));
        this.setBackground(Settings.BACKGROUND);

        noteArea.setForeground(Settings.FOREGROUND);
        noteArea.setFont(new Font(Settings.FONTSTYLE, Font.BOLD, 13));
        noteArea.setLineWrap(true);
        noteArea.setEditable(false);
        noteArea.setBorder(new LineBorder(Settings.BORDER_COLOR));
        noteArea.setBackground(Settings.BACKGROUND);
        
        JScrollPane notePane = new JScrollPane(noteArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        notePane.setBorder(new LineBorder(Settings.BORDER_COLOR, 1, true));

        this.add(notePane, BorderLayout.CENTER);
    }

    public JTextArea getNoteArea() {
        return this.noteArea;
    }

    public void setNoteArea(JTextArea noteArea) {
        this.noteArea = noteArea;
    }

    public void setNotes(List<String> notes) {
        noteArea.setText("");
        for(int i = 0; i < notes.size(); i ++) {  
        noteArea.append(i+1 + ".) " + notes.get(i));
        noteArea.append("\n"); 
        }
    }

}
