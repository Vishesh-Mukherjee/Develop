package com.gdgu.mvc;

public class Controller {
    private Model model;
    private View view;

    private String developRequest;
    private String exDevelopRequest = "";

    private final String FORWARD_KEY = Setting.FORWARD_KEY; 
    private final String BACKWARD_KEY = Setting.BACKWARD_KEY;

    public Controller() {
    }

    public Model getModel() {
        return this.model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public View getView() {
        return this.view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void updateControllerView(String developRequest) {
        this.developRequest = developRequest;
        model.database(developRequest);
        requestController();
    }

    public void requestController() {
        if (exDevelopRequest.equals("")) {
            switch(developRequest) {
                case FORWARD_KEY + "exit": model.terminateApplication(); break;
                case FORWARD_KEY + "eval": beginEvalateExpression(); break;
                case FORWARD_KEY + "random": beginGenerateRandom(); break;
                case FORWARD_KEY + "notebook": beginNotebook(); break;
                case FORWARD_KEY + "clock": 
                    view.attachClock(); 
                    view.clearDisplay(); 
                    view.setClockState(true); break;
                case BACKWARD_KEY + "clock": 
                    view.detachClock(); 
                    view.clearDisplay(); break;
                case FORWARD_KEY + "system": 
                    view.attachSystemInfo(); 
                    view.setSystemInfo(model.getSystemInfo()); 
                    view.setSystemInfoState(true); break;
                case BACKWARD_KEY + "system": view.detachSystemInfo(); break;
            }
        }
        else {
            switch(exDevelopRequest) {
                case FORWARD_KEY + "eval": view.setResult(model.evaluateExpression(developRequest)); break;
                case FORWARD_KEY + "random": view.setResult(model.generateRandom(developRequest)); break;
                case FORWARD_KEY + "notebook": view.setNotes(model.getNotes(developRequest)); break;
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
}
