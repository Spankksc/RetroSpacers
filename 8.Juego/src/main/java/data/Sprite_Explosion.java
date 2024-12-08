package data;

import javax.swing.*;
import java.awt.*;

public class Sprite_Explosion extends Thread{

    ImageIcon[] imagenes;
    int delay;
    int x;
    int y;
    int tamano;

    boolean haFinalizado=false;

    int indice=0;

    public Sprite_Explosion(ImageIcon[] imagenes, int delay, int x, int y, int tamaño) {
        this.imagenes = imagenes;
        this.delay = delay;
        this.x = x;
        this.y = y;
        this.tamano = tamaño;

    }


    public void pintar_Explosion(Graphics g){
        g.drawImage(imagenes[indice].getImage(), x, y, tamano, tamano, null);

    }

    @Override
    public void run() {

        try{
            for (int i = 0; i < imagenes.length; i++) {
                //System.out.println("Explosion sin finalizar");
                indice=i;
                //System.out.println(i);
                Thread.sleep(delay);
            }
            haFinalizado=true;

        } catch (Exception e) {
            System.out.println("Error en la explosion");
        }

    }
}
