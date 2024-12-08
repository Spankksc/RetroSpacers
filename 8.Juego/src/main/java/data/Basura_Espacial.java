package data;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Basura_Espacial extends Rectangle implements Runnable{



    boolean finalizar = false;

    Sprite s;

    Monitor m;

    ImageIcon[] imagenes;

    int velocidad_desplazamiento;

    int tam_y;
    int tam_x;
    public Basura_Espacial( Monitor m, ImageIcon[] imagenes, int tam_y, int tam_x) {
        this.tam_y = tam_y;
        this.tam_x = tam_x;
        this.m=m;
        this.imagenes=imagenes;
        Random r=new Random();
        this.width=25;
        this.height=25;

        if (r.nextInt(2)==0){

            this.x=0;
            velocidad_desplazamiento=3;
        }else{

            this.x=tam_x;
            velocidad_desplazamiento=-3;
        }

        this.y=r.nextInt(tam_y);



        s=new Sprite(4, 100);
        s.start();

        this.height=50;
        this.width=50;

    }

    public void pintar_Basura(Graphics g){
        if (s!=null){
            //System.out.println(s.indice);
            g.drawImage(imagenes[s.indice].getImage(), x, y, 50, 50, null);
        }
    }


    @Override
    public void run() {
        while(!finalizar){
            //System.out.println("Basura sin finalizar");
            m.bloquear();
            this.x+=velocidad_desplazamiento;

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        //System.out.println("Basura finalizada");
        s.finalizar=true;
    }
}
