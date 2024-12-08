package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Pantalla_Menu extends JPanel {
    Ventana v;

    ImageIcon fondo;
    ImageIcon jugar;
    ImageIcon salir;

    public Pantalla_Menu(Ventana v) {
        this.v=v;
        fondo = new ImageIcon(".//res/menu.png");
        jugar = new ImageIcon(".//res/Jugar.png");
        salir = new ImageIcon(".//res/salir.png");
        this.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.weightx=GridBagConstraints.CENTER;
        c.weighty=GridBagConstraints.CENTER;

        c.gridx=0;
        c.gridy=2;

        JButton b = new JButton();
        b.setIcon(jugar);
        b.setPreferredSize(new Dimension(170, 40));
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                v.ponerPanel();
            }
        });
        this.add(b, c);


        c.gridx=1;
        JButton b2 = new JButton();
        b2.setIcon(salir);
        b2.setPreferredSize(new Dimension(170, 40));
        b2.setBorderPainted(false);
        b2.setContentAreaFilled(false);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                v.dispose();
            }
        });
        this.add(b2, c);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
    }




}
