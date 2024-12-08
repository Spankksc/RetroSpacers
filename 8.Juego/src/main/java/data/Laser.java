package data;

import javax.swing.*;
import java.awt.*;

public class Laser extends Rectangle implements Runnable{



    int tamano = 50;

    boolean finalizar = false;

    ImageIcon imagen;

    Monitor m;

    public Laser(int x, int y, ImageIcon imagen, Monitor m) {
        this.x = x;
        this.y = y;
        this.height = tamano;
        this.width = tamano;
        this.m = m;
        this.imagen = imagen;
    }

    public void pintar_Laser(Graphics graphics){
        graphics.drawImage(imagen.getImage(), x, y, tamano, tamano, null);
    }

    @Override
    public void run() {
        while(!finalizar){
            //System.out.println("Laser sin finalizar");
            m.bloquear();
            y-=5;

            if(y==-50){
                finalizar=true;

            }


            try {
                Thread.sleep(16);
            } catch (InterruptedException ex) {
                System.out.println("Error en el laser");
            }
        }



        //System.out.println("Laser finalizado");

    }
}
