package com.gdgu.mvc;

public class App {
    
    public static void main( String[] args ) {
        View view = new View();
        Controller controller = new Controller();
        Model model = new Model();

        view.setController(controller);
        controller.setView(view);
        controller.setModel(model);
        model.setController(controller);
    }
}
