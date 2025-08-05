package com.gdgu.mvc.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.gdgu.mvc.panel.ClockPanel;
import com.gdgu.mvc.panel.NotebookPanel;
import com.gdgu.mvc.panel.PanelFactory;
import com.gdgu.mvc.panel.StopwatchPanel;
import com.gdgu.mvc.panel.TipPanel;
import com.gdgu.mvc.panel.TrackerPannel;
import com.gdgu.mvc.service.DatabaseService;
import com.gdgu.mvc.util.Configuration;

import net.sourceforge.jeval.Evaluator;

public class View extends JFrame {

    private int x, y, xMouse, yMouse;
    private static int heightCount;

    private List<String> notes = new ArrayList<>();

    private static final String BACKWARD_KEYKEY = Configuration.BACKWARD_KEY;
    private final DatabaseService database = new DatabaseService();

    private String developRequest;
    private String exDevelopRequest = "";

    private final String FORWARD_KEY = Configuration.FORWARD_KEY; 
    private final String BACKWARD_KEY = Configuration.BACKWARD_KEY;

    public View() {
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        setUndecorated(true);
        setOpacity(0.85f);
        setAlwaysOnTop(true);
        getContentPane().setBackground(Color.BLACK);
        setVisible(true);
        setSize(getDevelopDimension(PanelFactory.getRequestPanel(), true));
        setLocationRelativeTo(null);
        add(PanelFactory.getRequestPanel());
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
                setLocation(x-xMouse, y-yMouse);
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

    private void notifyController() {
        updateControllerView(PanelFactory.getRequestPanel().getRequestField().getText());
    }

    private Dimension getDevelopDimension(JPanel panel, boolean bool) {
        Dimension dimension;
        if (bool) {
            dimension = new Dimension(Configuration.HORIZONTAL_WIDTH, 
                                 (int) (heightCount + panel.getPreferredSize().getHeight()));
            heightCount = (int) dimension.getHeight();
            return dimension;
        } else {
            dimension = new Dimension(Configuration.HORIZONTAL_WIDTH, 
                                 (int) (heightCount - panel.getPreferredSize().getHeight()));
            heightCount = (int) dimension.getHeight();
            return dimension;
        }
    } 

    public void attachClock() {
        ClockPanel clockPanel = PanelFactory.getClockPanel();
        if (!clockPanel.getAir()) {
            setSize(getDevelopDimension(clockPanel, true));
            add(clockPanel);
            clockPanel.setAir(true);
        }
    }

    public void detachClock() {
        ClockPanel clockPanel = PanelFactory.getClockPanel();
        if (clockPanel.getAir()) {
            remove(clockPanel);
            setSize(getDevelopDimension(clockPanel, false));
            clockPanel.setAir(false);
        }
    }

    public void detachTracker() {
        TrackerPannel trackerPanel = PanelFactory.getTrackerPanel();
        if (trackerPanel.getAir()) {
            remove(trackerPanel);
            setSize(getDevelopDimension(trackerPanel, false));
            trackerPanel.setAir(false);
        }
    }

    public void attackTip() {
        TipPanel tipPanel = PanelFactory.getTipPanel();
        if (!tipPanel.getAir()) {
            setSize(getDevelopDimension(tipPanel, true));
            add(tipPanel);
            tipPanel.setAir(true);
        }
    }

    public void detachTip() {
        TipPanel tipPanel = PanelFactory.getTipPanel();
        if (tipPanel.getAir()) {
            remove(tipPanel);
            setSize(getDevelopDimension(tipPanel, false));
            tipPanel.setAir(false);
        }
    }

    public void attachNotebook() {                                                  
        NotebookPanel notebookPanel = PanelFactory.getNotebookPanel();
        setSize(getDevelopDimension(notebookPanel, true));
        add(notebookPanel);
    }

    public void detachNoteBook() {
        NotebookPanel notebookPanel = PanelFactory.getNotebookPanel();
        remove(notebookPanel);
        setSize(getDevelopDimension(notebookPanel, false));
    }

    public void setNotes(List<String> notes) {
        NotebookPanel notebookPanel = PanelFactory.getNotebookPanel();
        if (notes != null) {
            PanelFactory.getRequestPanel().getRequestField().setText("");
            notebookPanel.setNotes(notes);
        }
    }

    public void addTask(String task) {
    }

    public void attachStopwatch() {
        StopwatchPanel watchPanel = PanelFactory.getStopWatchPanel();
        if (!watchPanel.getAir()) {
            setSize(getDevelopDimension(watchPanel, true));
            add(watchPanel);
            watchPanel.setAir(true);
        }
    }

    public void detachstopwatch() {
        StopwatchPanel watchPanel = PanelFactory.getStopWatchPanel();
        if (watchPanel.getAir()) {
            PanelFactory.getRequestPanel().getRequestField().setText("");
            remove(watchPanel);
            setSize(getDevelopDimension(watchPanel, false));
            watchPanel.setAir(false);
        }
    }

    public void attachTracker() {
        TrackerPannel trackerPanel = PanelFactory.getTrackerPanel();
        if (!trackerPanel.getAir()) {
            setSize(getDevelopDimension(trackerPanel, true));
            add(trackerPanel);
            trackerPanel.setAir(true);
        }
    }

    public void inExecution(boolean bool) {
        PanelFactory.getRequestPanel().getRequestField().setText("");
        if (bool) {
            ((JComponent)this.getContentPane()).setBorder(new LineBorder(Configuration.BORDER_COLOR));
        } else {
            ((JComponent)this.getContentPane()).setBorder(BorderFactory.createEmptyBorder());
        }
    }

    public void clearDisplay() {
        PanelFactory.getRequestPanel().getRequestField().setText("");
    }

    public void setResult(String result) {  
        if (result != null) {
            PanelFactory.getRequestPanel().getRequestField().setText(result);
        }
    }

    public void updateControllerView(String developRequest) {
        this.developRequest = developRequest;
        database(developRequest);
        requestController();
    }

    public void requestController() {
        if (exDevelopRequest.equals("")) {
            switch(developRequest) {
                case FORWARD_KEY + "exit": terminateApplication(); break;
                case FORWARD_KEY + "eval": beginEvalateExpression(); break;
                case FORWARD_KEY + "random": beginGenerateRandom(); break;
                case FORWARD_KEY + "notebook": beginNotebook(); break;
                case FORWARD_KEY + "clock": 
                    attachClock(); 
                    clearDisplay(); break;
                case BACKWARD_KEY + "clock": 
                    detachClock(); 
                    clearDisplay(); break;
                case FORWARD_KEY + "tracker": 
                    attachTracker();
                    clearDisplay(); break;
                case BACKWARD_KEY + "tracker": 
                    detachTracker(); 
                    clearDisplay(); break;
                case FORWARD_KEY + "stopwatch":
                    attachStopwatch();
                    clearDisplay(); break;
                case BACKWARD_KEY + "stopwatch":
                    detachstopwatch();
                    clearDisplay(); break;
                case FORWARD_KEY + "tip": 
                    attackTip(); 
                    clearDisplay(); break;
                case BACKWARD_KEY + "tip":
                    detachTip();
                    clearDisplay(); break;
            }
        }
        else {
            switch(exDevelopRequest) {
                case FORWARD_KEY + "eval": setResult(evaluateExpression(developRequest)); break;
                case FORWARD_KEY + "random": setResult(generateRandom(developRequest)); break;
                case FORWARD_KEY + "notebook": setNotes(getNotes(developRequest)); break;
            }
        }
    } 

    public void beginEvalateExpression() {
        inExecution(true);
        exDevelopRequest = developRequest;
    }

    public void ceaseEvaluteExpression() {
        exDevelopRequest = "";
        inExecution(false);
    }

    public void beginGenerateRandom() {
        this.beginEvalateExpression();
    }

    public void ceaseGenerateRandom() {
        this.ceaseEvaluteExpression();
    }

    public void beginNotebook() {
        this.beginEvalateExpression();
        attachNotebook();
    }

    public void ceaseNotebook() {
        this.ceaseEvaluteExpression();
        detachNoteBook();
    }

    // Model methods

    public void terminateApplication() {
        System.exit(0);
    }

    public String evaluateExpression(String expression) {
        if (expression.equals(BACKWARD_KEYKEY)) {
            ceaseEvaluteExpression();
            return null;
        }
        else {
            Evaluator evaluator = new Evaluator();
            try {
                return evaluator.evaluate(expression);
            } catch (Exception e) {
                return "Invalid Expression"; 
            }
        }
    }

    public String generateRandom(String str) {
        if (str.equals(BACKWARD_KEYKEY)) {
            ceaseGenerateRandom();
            return null;
        } else {
            Random random = new Random();
            String[] myArray = str.split(" ");
            return myArray[random.nextInt(myArray.length)];
        }
    }

    public List<String> getNotes(String note) {
        if (note.equals(BACKWARD_KEYKEY)) {
            ceaseNotebook();
            return null;
        } else if(note.equals("clear__notebook")) {
            notes.clear();
            return notes;
        } else {
            notes.add(note);
            return notes;
        }
    }

    public void database(String request) {
        database.addData(request);
    }
}