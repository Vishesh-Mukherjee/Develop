package com.gdgu.mvc.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.gdgu.mvc.entity.Tip;
import com.gdgu.mvc.service.DatabaseService;
import com.gdgu.mvc.util.Configuration;

public class TipPanel extends AbstractPanel {

    private JTextArea textArea = new JTextArea();

    private List<Tip> tips;

    private int count = 0;

    public TipPanel() {
        DatabaseService database = new DatabaseService();
        tips = database.getTips();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(Configuration.HORIZONTAL_WIDTH, 100));
        setBorder(new EmptyBorder(3, 3, 3, 3));
        setBackground(Configuration.BACKGROUND);

        textArea.setForeground(Configuration.FOREGROUND);
        textArea.setFont(new Font(Configuration.FONTSTYLE, Font.BOLD, 13));
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setBorder(new LineBorder(Configuration.BORDER_COLOR));
        textArea.setBackground(Configuration.BACKGROUND);

        JScrollPane notePane = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        notePane.setBorder(new LineBorder(Configuration.BORDER_COLOR, 1, true));

        textArea.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (count == tips.size()-1) {
                    count = 0;
                }
                textArea.setText(tips.get(count).getData());
            }
            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });

        add(notePane);
    }

    @Override
    public void setAir(boolean air) {
        this.air = air;
    }

    @Override
    public boolean getAir() {
        return air;
    }

    public void changeTip() {

    }

    @Override
    public void attackAndExecute() {
        // Nothing to execute
    }

}