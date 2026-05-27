package main;

import ui.ConsoleUI;

// Punto de entrada de la aplicación. Totalmente separado de la lógica y los modelos.
public class Main {
    public static void main(String[] args) {
        // Se instancia la interfaz de usuario que internamente inicializa los servicios.
        ConsoleUI console = new ConsoleUI();
        console.start();
    }
}

//GABRIEL OMAR VENEGAS GONZÁLEZ
//LUIS MANUEL CORZO CASTRO