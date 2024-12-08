package data;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Meteorito extends Rectangle implements Runnable{



    int alto = 100;
    int ancho = 50;

    boolean finalizar = false;

    ImageIcon[] imagenes;

    int indice=0;

    Sprite s;

    Monitor m;

    int tam_x;

    public Meteorito(ImageIcon[] imagenes, Monitor m, int tam_x) {

        this.m=m;
        this.tam_x=tam_x;
        this.imagenes = imagenes;
        Random r=new Random();

        this.x=r.nextInt(tam_x);

        this.y=0;

        s=new Sprite(4, 100);
        s.start();

        this.height=alto;
        this.width=25;


    }

    public void pintar_Meteorito(Graphics g){
        if (s!=null){
            g.drawImage(imagenes[s.indice].getImage(), x, y, ancho, alto, null);
        }
    }

    @Override
    public void run() {

        while(!finalizar){
            //System.out.println("Meteorito sin finalizar");
            m.bloquear();
            this.y+=3;

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }




        }
        //System.out.println("Meteorito finalizado");
        s.finalizar=true;

    }

    public void comprobar_Colision(Rectangle r){
        if(this.intersects(r)){
            System.out.println("Colision");
        }
    }

}
