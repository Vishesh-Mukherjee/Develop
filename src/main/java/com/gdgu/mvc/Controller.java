package com.gdgu.mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.sourceforge.jeval.Evaluator;

public class Controller {

    private List<String> notes = new ArrayList<>();

    private static final String BACKWARD_KEYKEY = Settings.BACKWARD_KEY;
    private final Database database = new Database();

    private View view;

    private String developRequest;
    private String exDevelopRequest = "";

    private final String FORWARD_KEY = Settings.FORWARD_KEY; 
    private final String BACKWARD_KEY = Settings.BACKWARD_KEY;

    public View getView() {
        return this.view;
    }

    public void setView(View view) {
        this.view = view;
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
                    view.attachClock(); 
                    view.clearDisplay(); break;
                case BACKWARD_KEY + "clock": 
                    view.detachClock(); 
                    view.clearDisplay(); break;
                case FORWARD_KEY + "system": 
                    view.attachSystemInfo(); 
                    view.setSystemInfo(getSystemInfo()); 
                    view.setSystemInfoState(true); break;
                case BACKWARD_KEY + "system": view.detachSystemInfo(); break;
                case FORWARD_KEY + "tictactoe":
                    view.attachTictactoe();
                    view.clearDisplay(); break;
                case BACKWARD_KEY + "tictactoe":
                    view.detachTictactoe();
                    view.clearDisplay(); break;
                case FORWARD_KEY + "stopwatch":
                    view.attachStopwatch();
                    view.clearDisplay(); break;
                case BACKWARD_KEY + "stopwatch":
                    view.detachstopwatch();
                    view.clearDisplay(); break;
                case FORWARD_KEY + "battery":
                    view.attachBattery();
                    view.clearDisplay(); break;
                case BACKWARD_KEY + "battery":
                    view.detachBattery();
                    view.clearDisplay(); break;
            }
        }
        else {
            switch(exDevelopRequest) {
                case FORWARD_KEY + "eval": view.setResult(evaluateExpression(developRequest)); break;
                case FORWARD_KEY + "random": view.setResult(generateRandom(developRequest)); break;
                case FORWARD_KEY + "notebook": view.setNotes(getNotes(developRequest)); break;
            }
        }
    } 

    public void beginEvalateExpression() {
        view.inExecution(true);
        exDevelopRequest = developRequest;
    }

    public void ceaseEvaluteExpression() {
        exDevelopRequest = "";
        view.inExecution(false);
    }

    public void beginGenerateRandom() {
        this.beginEvalateExpression();
    }

    public void ceaseGenerateRandom() {
        this.ceaseEvaluteExpression();
    }

    public void beginNotebook() {
        this.beginEvalateExpression();
        view.attachNotebook();
    }

    public void ceaseNotebook() {
        this.ceaseEvaluteExpression();
        view.detachNoteBook();
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

    public List<String> getSystemInfo() {
        return List.of(
            "foo",
            "bar",
            "baz",
            "qux",
            "quux"
        );
    }

    public void database(String request) {
        database.addData(request);
    }
}
