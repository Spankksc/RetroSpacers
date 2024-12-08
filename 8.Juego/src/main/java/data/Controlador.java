package data;

import GUI.Pantalla;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.Timer;

public class Controlador extends Thread{

    Pantalla p;
    Nave nave;

    /*int tam_pantalla_ancho=800;
    int tam_pantalla_altura=600;*/

    List<Laser> laseres;
    List<Meteorito> meteoritos;
    List<Sprite_Explosion> explosiones;
    List<Basura_Espacial> basuras;

    boolean finalizar=false;

    int Score=0;

    ImageIcon[] imagen_Explosion={
            new ImageIcon(".//res/fuego_1.png"),
            new ImageIcon(".//res/fuego_2.png"),
            new ImageIcon(".//res/fuego_3.png"),
            new ImageIcon(".//res/fuego_4.png"),
            new ImageIcon(".//res/fuego_5.png"),
            new ImageIcon(".//res/fuego_6.png"),
            new ImageIcon(".//res/fuego_7.png"),
            new ImageIcon(".//res/fuego_8.png"),
            new ImageIcon(".//res/fuego_9.png"),
            new ImageIcon(".//res/fuego_10.png"),
            new ImageIcon(".//res/fuego_11.png")
    };

    ImageIcon imagen_laser = new ImageIcon(".//res/laser.png");
    ImageIcon[] imagen_meteorito={
      new ImageIcon(".//res/meteorito_1.png"),
      new ImageIcon(".//res/meteorito_2.png"),
      new ImageIcon(".//res/meteorito_3.png"),
      new ImageIcon(".//res/meteorito_4.png")
    };

    ImageIcon[] basura_espacial={
            new ImageIcon(".//res/roca_1.png"),
            new ImageIcon(".//res/roca_2.png"),
            new ImageIcon(".//res/roca_3.png"),
            new ImageIcon(".//res/roca_4.png"),
            new ImageIcon(".//res/roca_5.png"),
            new ImageIcon(".//res/roca_6.png"),
            new ImageIcon(".//res/roca_7.png"),
            new ImageIcon(".//res/roca_8.png"),
            new ImageIcon(".//res/roca_9.png"),
            new ImageIcon(".//res/roca_10.png"),
            new ImageIcon(".//res/roca_11.png"),
            new ImageIcon(".//res/roca_12.png")

    };

    ImageIcon corazones=new ImageIcon(".//res/corazon.png");

    ImageIcon fondo=new ImageIcon(".//res/fondo_estrellas.png");


    File fuente=new File(".//res//fuente_retro.ttf");


    Timer timer2;
    Timer timer;
    Timer timer3;

    Fondo_Hilo fondo_h;

    boolean pintando_laser=false;

    Monitor m=new Monitor();

    Reproductor reproductor;

    int tam_x;
    int tam_y;

    public Controlador(Pantalla p) {
        this.p = p;
        tam_x = p.tam_x();
        tam_y = p.tam_y();
        reproductor = new Reproductor(3);
        reproductor.start();



        fondo_h = new Fondo_Hilo(tam_x, tam_y, fondo);
        fondo_h.start();
        nave = new Nave(320,400, m);
        new Thread(nave).start();
        meteoritos=new ArrayList<>();
        laseres=new ArrayList<>();
        explosiones=new ArrayList<>();
        basuras=new ArrayList<>();

        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Meteorito met = new Meteorito(imagen_meteorito, m, tam_x);
                new Thread(met).start();
                meteoritos.add(met);
            }
        };

        timer.schedule(task, 0, 500);

        timer2 = new Timer();
        TimerTask task2= new TimerTask() {
            @Override
            public void run() {
                Basura_Espacial basura = new Basura_Espacial(m, basura_espacial, tam_y, tam_x);
                new Thread(basura).start();
                basuras.add(basura);
            }
        };

        timer2.schedule(task2,0,1000);

        timer3 = new Timer();
        TimerTask task3 = new TimerTask() {
            @Override
            public void run() {
                Score+=1000;
            }
        };

        task3.run();
        timer3.schedule(task3, 0, 1000);
        nave.x=(tam_x/2)-75;
    }

    @Override
    public void run() {

        while(!finalizar){

            //p.getGg().drawImage(imagen_laser.getImage(), nave.x, nave.y, nave.ancho, nave.alto, null);
            //System.out.println("x: "+tam_x+" y: "+tam_y);



            fondo_h.pintar_Fondo(p.getGg());
            p.getGg().setFont(new Font("Retro Gaming", Font.PLAIN, 20));
            p.getGg().setColor(Color.WHITE);
            p.getGg().drawString("Score: "+Score,tam_x-200 , 50);


            if(nave!=null){
                nave.pintar_Nave(p.getGg());
                for (int i = 0; i < nave.vidas; i++) {
                    p.getGg().drawImage(corazones.getImage(), 10+(i*30), 10, 30, 30, null);
                }
            }else{
                reproductor.finalizar=true;
                reproductor.finalizar_sonido();
                /*System.out.printf("Cantidad Meteoritos:"+meteoritos.size()+"\n");
                System.out.printf("Cantidad Basuras:"+basuras.size()+"\n");
                System.out.printf("Cantidad Lasers:"+laseres.size()+"\n");*/

                for (int i = 0; i < meteoritos.size(); i++) {
                    meteoritos.get(i).finalizar=true;
                }
                for (int i = 0; i < laseres.size(); i++) {
                    laseres.get(i).finalizar=true;
                }
                for (int i = 0; i < basuras.size(); i++) {
                    basuras.get(i).finalizar=true;
                }

                fondo_h.finalizar=true;

                int explosiones_cont=0;

                for (int i = 0; i < explosiones.size(); i++) {
                    if (explosiones.get(i).haFinalizado==false){
                        explosiones_cont++;
                    }
                }

                if (explosiones_cont==0){
                    finalizar=true;
                }

                explosiones_cont=0;

                timer.cancel();
                timer2.cancel();
                timer3.cancel();


            }




            for (int i = 0; i < meteoritos.size(); i++) {

                meteoritos.get(i).pintar_Meteorito(p.getGg());
                    //p.getGg().drawImage(imagen_meteorito[meteoritos.get(i).s.indice].getImage(), meteoritos.get(i).x, meteoritos.get(i).y, meteoritos.get(i).ancho, meteoritos.get(i).alto, null);
            }

            for (int i = 0; i < basuras.size(); i++) {
                basuras.get(i).pintar_Basura(p.getGg());
            }

            for (int i = 0; i < explosiones.size(); i++) {
                if (explosiones.get(i).haFinalizado){
                    explosiones.remove(i);
                }else{
                    explosiones.get(i).pintar_Explosion(p.getGg());

                }
            }

            for (int i = 0; i < basuras.size(); i++) {
                if (basuras.get(i).velocidad_desplazamiento>0 && basuras.get(i).x>=tam_x){
                    basuras.get(i).finalizar=true;
                    basuras.remove(i);

                } else if (basuras.get(i).velocidad_desplazamiento<0 && basuras.get(i).x<=0){
                    basuras.get(i).finalizar=true;
                    basuras.remove(i);
                }
            }




            if (!pintando_laser){
                for (int i = 0; i < laseres.size(); i++) {

                    if (laseres.get(i).finalizar){
                        laseres.remove(i);
                        /*System.out.println("Laser eliminado");
                        System.out.println("Tamaño de la lista de laseres: "+laseres.size());*/
                    }else{
                        laseres.get(i).pintar_Laser(p.getGg());
                    }


                }
            }

            for (int i = 0; i < meteoritos.size(); i++) {

                if (meteoritos.get(i).y>tam_y){
                    meteoritos.get(i).finalizar=true;
                    meteoritos.remove(i);
                }

            }

            m.setBloquear();

            if (nave!=null){
                for (int i = 0; i < meteoritos.size(); i++) {

                    boolean terminar_bucle=false;

                    if (nave!=null && nave.intersects(meteoritos.get(i))){

                        if (nave.vidas==1){


                        meteoritos.get(i).finalizar=true;
                        meteoritos.remove(i);

                        Sprite_Explosion e = new Sprite_Explosion(imagen_Explosion, 100, nave.x, nave.y,150);
                        this.explosiones.add(e);
                        this.explosiones.get(explosiones.size()-1).start();
                            this.reproducir_explosion();
                            this.nave.finalizar=true;
                        this.nave=null;
                        terminar_bucle=true;
                        }else{
                            nave.vidas--;
                            meteoritos.get(i).finalizar=true;
                            meteoritos.remove(i);
                            terminar_bucle=true;
                        }
                    }

                    if (terminar_bucle){
                        break;
                    }

                }

                for (int i = 0; i < basuras.size(); i++) {

                    boolean terminar_bucle=false;

                    if (nave!=null && nave.intersects(basuras.get(i))){

                        if (nave.vidas==1){
                            basuras.get(i).finalizar=true;
                            basuras.remove(i);

                            Sprite_Explosion e = new Sprite_Explosion(imagen_Explosion, 100, nave.x, nave.y,150);
                            this.explosiones.add(e);
                            this.explosiones.get(explosiones.size()-1).start();
                            this.reproducir_explosion();
                            this.nave.finalizar=true;
                            this.nave=null;
                            terminar_bucle=true;

                        }else{
                            nave.vidas--;
                            basuras.get(i).finalizar=true;
                            basuras.remove(i);
                            terminar_bucle=true;
                        }




                    }

                    if (terminar_bucle){
                        break;
                    }


                }

            }

            for (int i = 0; i < meteoritos.size(); i++) {


                for (int j = 0; j < basuras.size(); j++) {

                    boolean terminar_bucle=false;

                    if (meteoritos.get(i).intersects(basuras.get(j))){
                        meteoritos.get(i).finalizar=true;
                        Sprite_Explosion e = new Sprite_Explosion(imagen_Explosion, 100, meteoritos.get(i).x, meteoritos.get(i).y,50);
                        this.explosiones.add(e);
                        this.explosiones.get(explosiones.size()-1).start();
                        this.reproducir_explosion();
                        basuras.get(j).finalizar=true;
                        basuras.remove(j);
                        meteoritos.remove(i);
                        terminar_bucle=true;
                    }

                    if (terminar_bucle){
                        break;
                    }


                }
            }


            for (int i = 0; i < meteoritos.size(); i++) {

                for (int j = 0; j < laseres.size() ; j++) {

                    boolean terminar_bucle=false;

                    if (meteoritos.get(i).intersects(laseres.get(j))){
                        meteoritos.get(i).finalizar=true;
                        Score+=1000;
                        laseres.get(j).finalizar=true;
                        laseres.remove(j);

                        Sprite_Explosion e = new Sprite_Explosion(imagen_Explosion, 100, meteoritos.get(i).x, meteoritos.get(i).y,50);
                        this.explosiones.add(e);
                        this.explosiones.get(explosiones.size()-1).start();
                        this.reproducir_explosion();
                        terminar_bucle=true;
                        meteoritos.remove(i);
                    }

                    if (terminar_bucle){
                        break;
                    }



                }

            }

            for (int i = 0; i < basuras.size(); i++) {

                for (int j = 0; j < laseres.size(); j++) {

                    boolean terminar_bucle=false;

                    if (basuras.get(i).intersects(laseres.get(j))){
                        laseres.get(j).finalizar=true;
                        laseres.remove(j);
                        terminar_bucle=true;
                    }

                    if (terminar_bucle){
                        break;
                    }

                }





            }


            m.desbloquear();

            p.actualizarPantalla();

            try {
                Thread.sleep(16);
            } catch (InterruptedException ex) {
                System.out.println("Error en el controlador");
            }

        }

        p.getGg().drawImage(new ImageIcon(".//res//fin.png").getImage(), 0, 0, tam_x, tam_y, null);
        p.getGg().setColor(Color.WHITE);
        p.getGg().setFont(new Font("Retro Gaming", Font.PLAIN, 50));
        p.getGg().drawString("Score: "+Score, tam_x/2-200, tam_y/2+50);
        p.repaint();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        p.ponerMenu();
    }

    public void mover_nave_derecha(){

        if (nave==null){
            return;
        }

        if (nave.x>tam_x-nave.ancho){


        }else{
            nave.moverDerecha();
        }

    }

    public void mover_nave_izquierda() {

        if (nave==null){
            return;
        }

        if (nave.x<0){

        }else{
            nave.moverIzquierda();
        }

    }

    public void mover_nave_arriba() {

        if (nave==null){
            return;
        }

        if (nave.y<0){

        }else{
            nave.moverArriba();
        }

    }

    public void mover_nave_abajo() {
        if (nave==null){
            return;
        }

        if (nave.y>tam_y-(nave.alto+30)){

        }else{
            nave.moverAbajo();
        }


    }

    public void disparar(){

        if (nave==null){
            return;
        }

        pintando_laser=true;
        Laser l = new Laser(nave.x+52, nave.y, imagen_laser,m);
        laseres.add(l);
        new Thread(l).start();

        Reproductor reproductor_disparo = new Reproductor(1);
        reproductor_disparo.start();

       /* final Player reproductor_disparo;
        try {
            reproductor_disparo = new Player(audioStream);
        } catch (JavaLayerException e) {
             throw new RuntimeException(e);
        }



        new Thread(() -> {
            try {
                 reproductor_disparo.play();
            } catch (JavaLayerException e) {
                throw new RuntimeException(e);
            }
        }).start();*/

        pintando_laser=false;
    }

    public void reproducir_explosion(){
        Reproductor reproductor_explosion = new Reproductor(2);
        reproductor_explosion.start();
    }


}
