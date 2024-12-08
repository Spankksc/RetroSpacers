package GUI;

import data.Controlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Pantalla extends JPanel {

    Ventana v;
    BufferedImage imagen;
    Graphics gg;
    //Controlador c;
    int x=0;
    int y=0;

    public Pantalla(Ventana v) {
        this.v=v;
        imagen  = new BufferedImage(v.getWidth(), v.getHeight(), BufferedImage.TYPE_INT_RGB);
        gg=imagen.createGraphics();
        limpiarPantalla();
        x=v.getWidth();
        y=v.getHeight();
        //System.out.println("Ancho: "+x+" Alto: "+y);
        /*Controlador c = new Controlador(this);
        c.start();*/



    }

    public int tam_x() {
        return x;
    }

    public int tam_y() {
        return y;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        /*g.setColor(Color.BLACK);
        g.fillRect(0, 0, 100, 100);*/
        g.drawImage(imagen, 0, 0,x,y, this);

        limpiarPantalla();
    }



    public Graphics getGg() {
        return gg;
    }

    public void limpiarPantalla() {

        gg.setColor(Color.BLACK);
        gg.fillRect(0, 0, x, y);



    }

    public void actualizarPantalla() {

        repaint();
    }
    public void setImagen(BufferedImage imagen) {
        this.imagen = imagen;
    }



    public BufferedImage getImagen() {
        return imagen;
    }

    public void ponerMenu(){
        v.poner_Panel_Menu();
    }


}
