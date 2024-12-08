package data;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Reproductor extends Thread{

    FileInputStream fis;
    int i;
    Player player;
    boolean finalizar=false;

    public Reproductor(int i) {


        this.i=i;


    }

    @Override
    public void run() {
        switch (i){
            case 1:
                try {
                    fis=new FileInputStream(".//res/sonido_disparo.mp3");
                     player=new Player(fis);
                    player.play();


                } catch (FileNotFoundException | JavaLayerException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 2:
                try {
                    fis=new FileInputStream(".//res/sonido_explosion.mp3");
                    player=new Player(fis);

                    player.play();


                } catch (FileNotFoundException | JavaLayerException e) {
                    throw new RuntimeException(e);
                }
                break;

                case 3:
                    reproducirMusica();
                    break;


        }
    }

    private void reproducirMusica() {


        try {
            fis=new FileInputStream(".//res/musica.mp3");
             player=new Player(fis);
            player.play();
        } catch (FileNotFoundException | JavaLayerException e) {
            throw new RuntimeException(e);
        }


        while(!finalizar){
            System.out.println("Reproduciendo musica");
            if (player.isComplete()){
                try {
                    fis=new FileInputStream(".//res/musica.mp3");
                    player=new Player(fis);
                    player.play();
                } catch (FileNotFoundException | JavaLayerException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }

    public void finalizar_sonido(){
        player.close();
    }

}
