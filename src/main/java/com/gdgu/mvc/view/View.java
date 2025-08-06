package com.gdgu.mvc.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;

import com.gdgu.mvc.panel.AbstractPanel;
import com.gdgu.mvc.panel.ClockPanel;
import com.gdgu.mvc.panel.NotebookPanel;
import com.gdgu.mvc.panel.RequestPanel;
import com.gdgu.mvc.panel.StopwatchPanel;
import com.gdgu.mvc.panel.TipPanel;
import com.gdgu.mvc.panel.TrackerPannel;
import com.gdgu.mvc.util.Configuration;
import com.gdgu.mvc.util.Constants;

import net.sourceforge.jeval.Evaluator;

public class View extends JFrame {

    private int x, y, xMouse, yMouse;
    private static int heightCount;

    private List<String> notes = new ArrayList<>();

    private String developRequest;
    private String exDevelopRequest = "";

    private NotebookPanel notebookPanel = new NotebookPanel();
    private RequestPanel requestPanel = new RequestPanel();

    private Map<String, AbstractPanel> panels;

    public View() {
        notebookPanel = new NotebookPanel();
        requestPanel = new RequestPanel();
        panels = Map.of(
            "clock", new ClockPanel(),
            "tracker", new TrackerPannel(requestPanel.getRequestField()),
            "stopwatch", new StopwatchPanel(),
            "tip", new TipPanel());
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setUndecorated(true);
        setOpacity(0.85f);
        setAlwaysOnTop(true);
        getContentPane().setBackground(Color.BLACK);
        setVisible(true);
        setSize(getDevelopDimension(requestPanel.getPreferredSize().getHeight(), true));
        setLocationRelativeTo(null);
        add(requestPanel);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                xMouse = evt.getX();
                yMouse = evt.getY();
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent evt) {
                x = evt.getXOnScreen();
                y = evt.getYOnScreen();
                setLocation(x - xMouse, y - yMouse);
            }
        });
        requestPanel.getRequestField().addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    String text = requestPanel.getRequestField().getText();
                    if (text.length() >= 1) {
                        requestController(requestPanel.getRequestField().getText());
                    }
                }
            }
        });
    }

    private Dimension getDevelopDimension(double height, boolean bool) {
        Dimension dimension;
        if (bool) {
            dimension = new Dimension(Configuration.HORIZONTAL_WIDTH,
                    (int) (heightCount + height));
            heightCount = (int) dimension.getHeight();
            return dimension;
        } else {
            dimension = new Dimension(Configuration.HORIZONTAL_WIDTH,
                    (int) (heightCount - height));
            heightCount = (int) dimension.getHeight();
            return dimension;
        }
    }

    public void attachNotebook() {
        setSize(getDevelopDimension(notebookPanel.getPreferredSize().getHeight(), true));
        add(notebookPanel);
    }

    public void detachNoteBook() {
        remove(notebookPanel);
        setSize(getDevelopDimension(notebookPanel.getPreferredSize().getHeight(), false));
    }

    public void setNotes(List<String> notes) {
        if (notes != null) {
            requestPanel.getRequestField().setText("");
            notebookPanel.setNotes(notes);
        }
    }

    public <T extends AbstractPanel> void attachPanel(T panel) {
        if (!panel.getAir()) {
            setSize(getDevelopDimension(panel.getPreferredSize().getHeight(), true));
            add(panel);
            panel.setAir(true);
        }
        requestPanel.setText("");
    }

    public <T extends AbstractPanel> void detachPanel(T panel) {
        if (panel.getAir()) {
            requestPanel.getRequestField().setText("");
            remove(panel);
            setSize(getDevelopDimension(panel.getPreferredSize().getHeight(), false));
            panel.setAir(false);
        }
        requestPanel.setText("");
    }

    public void inExecution(boolean bool) {
        requestPanel.getRequestField().setText("");
        if (bool) {
            ((JComponent) getContentPane()).setBorder(new LineBorder(Configuration.BORDER_COLOR));
        } else {
            ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder());
        }
    }

    public void handleHyperMode() {
        if ((Configuration.FORWARD_KEY + "eval").equals(exDevelopRequest)) {
            requestPanel.setText(evaluateExpression(developRequest));
            return;
        }
        if ((Configuration.FORWARD_KEY + "random").equals(exDevelopRequest)) {
            requestPanel.setText(generateRandom(developRequest));
            return;
        }
        if ((Configuration.FORWARD_KEY + "notebook").equals(exDevelopRequest)) {
            setNotes(getNotes(developRequest));
            return;
        }
    }

    public void requestController(String request) {
        this.developRequest = request;
        if (!"".equals(exDevelopRequest)) {
            handleHyperMode();
        }
        switch(request) {
            case Constants.EVAL_TASK:
            case Constants.RANDOM_TASK:
                inExecution(true);
                exDevelopRequest = request;
                break;
            case Constants.EXIT_TASK:
                System.exit(0);
                break;
            case Constants.NOTEBOOK_TASK:
                inExecution(true);
                exDevelopRequest = request;
                attachNotebook();
            break;
        }
        if (panels.containsKey(request.substring(1))) {
            AbstractPanel panel = panels.get(request.substring(1));
            if (request.charAt(0) == '>') {
                attachPanel(panel);
                return;
            }
            if (request.charAt(0) == '<') {
                detachPanel(panel);
                return;
            }
        }
    }

    public String evaluateExpression(String expression) {
        if (Configuration.BACKWARD_KEY.equals(expression)) {
            exDevelopRequest = "";
            inExecution(false);
            return null;
        } else {
            Evaluator evaluator = new Evaluator();
            try {
                return evaluator.evaluate(expression);
            } catch (Exception e) {
                return "Invalid Expression";
            }
        }
    }

    public String generateRandom(String str) {
        if (Configuration.BACKWARD_KEY.equals(str)) {
            exDevelopRequest = "";
            inExecution(false);
            return null;
        } else {
            Random random = new Random();
            String[] myArray = str.split(" ");
            return myArray[random.nextInt(myArray.length)];
        }
    }

    public List<String> getNotes(String note) {
        if (Configuration.BACKWARD_KEY.equals(note)) {
            exDevelopRequest = "";
            inExecution(false);
            detachNoteBook();
            return null;
        } else if (note.equals("clear__notebook")) {
            notes.clear();
            return notes;
        } else {
            notes.add(note);
            return notes;
        }
    }

}