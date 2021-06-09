package com.gdgu.mvc;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

public class Model {
    private Controller controller;

    private List<String> notes = new ArrayList<>();

    private static final String BACKWARD_KEYKEY = Setting.BACKWARD_KEY;
    private final Database database = new Database();

    public Model() {
    }

    public Controller getController() {
        return this.controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void terminateApplication() {
        System.exit(0);
    }

    public String evaluateExpression(String expression) {
        if (expression.equals(BACKWARD_KEYKEY)) {
            controller.ceaseEvaluteExpression();
            return null;
        }
        else {
            Evaluator evaluator = new Evaluator();
            try {
                return evaluator.evaluate(expression);
            } catch (EvaluationException e) {
                return "Invalid Expression"; 
            }
        }
    }

    public String generateRandom(String str) {
        if (str.equals(BACKWARD_KEYKEY)) {
            controller.ceaseGenerateRandom();
            return null;
        } else {
            Random random = new Random();
            String[] myArray = str.split(" ");
            return myArray[random.nextInt(myArray.length)];
        }
    }

    public List<String> getNotes(String note) {
        if (note.equals(BACKWARD_KEYKEY)) {
            controller.ceaseNotebook();
            return null;
        } else {
            notes.add(note);
            return notes;
        }
    }

    public List<String> getSystemInfo() {
        File file;
        file = new File("C:\\");
        double cTotalSize = file.getTotalSpace() / (1024.0*1024*1024);
        double cUsableSize = file.getUsableSpace() / (1024.0*1024*1024);
        double cOccupiedSize = cTotalSize-cUsableSize;
        file = new File("D:\\");
        double dTotalSize = file.getTotalSpace() / (1024.0*1024*1024);
        double dUsableSize = file.getUsableSpace() / (1024.0*1024*1024);
        double dOccupiedSize = dTotalSize-dUsableSize;
        return Arrays.asList(
            "OS: " + System.getProperty("os.name"),
            "Core: " + System.getenv("NUMBER_OF_PROCESSORS") + " core",
            "C Drive:",
            "Total: " + String.format("%.2f", cTotalSize) + " GB",
            "Occupied: " + String.format("%.2f", cOccupiedSize) + " GB",
            "Free: " + String.format("%.2f", cUsableSize) + " GB",
            "D Drive",
            "Total: " + String.format("%.2f", dTotalSize) + " GB",
            "Occupied: " + String.format("%.2f", dOccupiedSize) + " GB",
            "Free: " + String.format("%.2f", dUsableSize) + " GB"

        );
    }

    public void database(String request) {
        database.addData(request);
    }
}