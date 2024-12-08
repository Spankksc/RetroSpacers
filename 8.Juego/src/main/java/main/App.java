package main;

import GUI.Ventana;
import data.Controlador;

public class App extends Thread {


    Ventana v;

    public App() {
        v = new Ventana(this);

    }

    public static void main(String[] args) {
        App ap=new App();
        ap.start();
    }

    @Override
    public void run() {

        v.setVisible(true);


    }


}