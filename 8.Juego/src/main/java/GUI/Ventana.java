package GUI;

import data.Controlador;
import main.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Ventana extends JFrame {

    Pantalla panel;
    App ap;
    Pantalla_Menu pm;
    Controlador c;
    public Ventana(App ap) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int ancho = screenSize.width;
        int alto = screenSize.height;

        this.ap=ap;
        this.setSize(ancho,alto);
        this.setUndecorated(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        poner_Panel_Menu();

        //this.repaint();

        //ponerPanel();


        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_LEFT && c!=null){
                    System.out.println("Tecla izquierda");
                    c.mover_nave_izquierda();
                }
                if(e.getKeyCode()==KeyEvent.VK_RIGHT && c!=null){
                    System.out.println("Tecla derecha");
                    c.mover_nave_derecha();
                }
                if(e.getKeyCode()==KeyEvent.VK_UP && c!=null){
                    System.out.println("Tecla arriba");
                    c.mover_nave_arriba();
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN && c!=null){
                    System.out.println("Tecla abajo");
                    c.mover_nave_abajo();
                }

                if (e.getKeyCode()==KeyEvent.VK_SPACE && c!=null){
                    System.out.println("Disparar");
                    c.disparar();
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });



    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

    }

    public Pantalla getPanel() {
        return panel;
    }

    public void ponerPanel() {
        pm.setVisible(false);



        panel=new Pantalla(this);
        panel.setVisible(true);
        this.setContentPane(panel);
        c = new Controlador(panel);
        c.start();

        //this.revalidate();
        this.requestFocus();
    }

    public void poner_Panel_Menu(){
        c=null;
        if (panel!=null){
            panel.setVisible(false);
        }

        pm=new Pantalla_Menu(this);
        this.setContentPane(pm);
        pm.setVisible(true);
        this.revalidate();


    }



}
