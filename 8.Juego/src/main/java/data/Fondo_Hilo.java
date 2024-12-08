package data;

import javax.swing.*;
import java.awt.*;

public class Fondo_Hilo extends Thread{

    int x;
    int y;
    int ancho;
    int alto;

    boolean finalizar=false;


    ImageIcon imagen;

    public Fondo_Hilo(int ancho, int alto, ImageIcon imagen){
        this.x = 0;
        this.y = 0;
        this.ancho = ancho;
        this.alto = alto;
        this.imagen = imagen;
    }


    public void pintar_Fondo(Graphics g){



        g.drawImage(imagen.getImage(), x, y, ancho, alto, null);


        g.drawImage(imagen.getImage(), x, y - alto, ancho, alto, null);


    }


    @Override
    public void run() {
        while(!finalizar){
            //System.out.println("Fondo sin finalizar");
            y+=5;
            if(y>=alto){
                y=0;
            }

            try {
                Thread.sleep(16);
            } catch (InterruptedException ex) {
                System.out.println("Error en el fondo");
            }
        }
    }
}
