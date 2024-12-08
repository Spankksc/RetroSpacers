package data;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLOutput;

public class Nave extends Rectangle implements Runnable{



    final int alto=150;
    final int ancho=150;

    ImageIcon[] imagenes = new ImageIcon[3];

    int indice_nave = 0;

    boolean finalizar = false;

    Sprite s;

    Monitor m;

    int desplazamiento_x=0;
    int desplazamiento_y=0;

    int vidas=3;

    public Nave(int x, int y, Monitor m) {
       this.x = x;
        this.y = y;
        this.height = 75;
        this.width = 75;
        this.m=m;
        imagenes[0] = new ImageIcon(".//res//nave1.png");
        imagenes[1] = new ImageIcon(".//res//nave2.png");
        imagenes[2] = new ImageIcon(".//res//nave3.png");

        s = new Sprite(3, 50);
        s.start();

    }


    public void pintar_Nave(Graphics g){
        if (s!=null){
            g.drawImage(imagenes[s.indice].getImage(), this.x, this.y, ancho, alto, null);

        }


    }

    @Override
    public void run() {
        while(!finalizar){
            //System.out.println("Nave sin finalizar");
            m.bloquear();
            this.x+=desplazamiento_x;
            this.y+=desplazamiento_y;

            desplazamiento_x=0;
            desplazamiento_y=0;

            try {
                Thread.sleep(16);



            } catch (InterruptedException ex) {
                System.out.println("Error en la nave");
            }
        }

        s.finalizar=true;

    }


    public void moverDerecha(){
        desplazamiento_x=10;
    }

    public void moverIzquierda(){
        desplazamiento_x=-10;
    }

    public void moverArriba(){
        desplazamiento_y=-10;
    }

    public void moverAbajo(){
        desplazamiento_y=10;
    }

    public boolean intersects(Meteorito meteorito) {
        Rectangle naveRect = new Rectangle(x, y, ancho-50, alto-50);
        Rectangle meteoritoRect = new Rectangle(meteorito.x, meteorito.y, meteorito.ancho-40, meteorito.alto-50);
        return naveRect.intersects(meteoritoRect);
    }

}
