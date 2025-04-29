package com.gdgu.mvc;

public class App {
    
    public static void main( String[] args ) {
        Controller controller = new Controller();
        View view = new View(controller);

        controller.setView(view);
    }
}
