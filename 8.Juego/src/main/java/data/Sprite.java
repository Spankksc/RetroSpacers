package data;

import javax.swing.*;

public class Sprite extends Thread{

    int num_imagenes;
    int delay;
    int indice;
    boolean finalizar = false;



    public Sprite(int num_imagenes, int delay) {
        this.num_imagenes = num_imagenes;
        this.delay = delay;
        indice=0;
    }

    @Override
    public void run() {
        while (!finalizar){
            try {
                //System.out.println("Sprite sin finalizar");
                Thread.sleep(delay);


                if(indice==num_imagenes-1){
                    indice=0;
                }else{
                    indice++;
                }

            } catch (InterruptedException ex) {
                System.out.println("Error en la nave");
            }
        }
        //System.out.println("Sprite finalizado");
    }
}
