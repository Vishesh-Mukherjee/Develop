package com.gdgu.mvc.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.gdgu.mvc.entity.Tip;
import com.gdgu.mvc.service.DatabaseService;
import com.gdgu.mvc.util.Settings;

public class TipPanel extends JPanel{

    private boolean air = false;

    private JTextArea textArea = new JTextArea();

    private List<Tip> tips;

    private int count = 0;

    public TipPanel() {
        DatabaseService database = new DatabaseService();
        tips = database.getTips();
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(230, 100));
        this.setBorder(new EmptyBorder(3, 3, 3, 3));
        this.setBackground(Settings.BACKGROUND);

        textArea.setForeground(Settings.FOREGROUND);
        textArea.setFont(new Font(Settings.FONTSTYLE, Font.BOLD, 13));
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setBorder(new LineBorder(Settings.BORDER_COLOR));
        textArea.setBackground(Settings.BACKGROUND);

        JScrollPane notePane = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        notePane.setBorder(new LineBorder(Settings.BORDER_COLOR, 1, true));

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

        this.add(notePane);
    }

    public void setAir(boolean air) {
        this.air= air;
    }

    public boolean getAir() {
        return air;
    }

    public void changeTip() {

    }

}