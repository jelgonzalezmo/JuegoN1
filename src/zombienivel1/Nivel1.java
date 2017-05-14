/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombienivel1;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.paint.Color;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Estudiante
 */
public class Nivel1 extends JPanel implements ActionListener, KeyListener {

    int x;
    int z;
    int m;
    private ArrayList<Circulos> circulos;
    private Zombie roberto = new Zombie(100, 350);
    private Timer timer;
    private int Delay = 20;
    private int secuencia = 0;
    private Color color;
    private ArrayList<Rectangle> nivel1 = new ArrayList<>();
    private ArrayList<Rectangle> muertos = new ArrayList<>();
    private boolean direccion;

    public Nivel1() {
        this.addKeyListener(this);
        setFocusable(true);
        direccion = true;
        timer = new Timer(Delay, this);
        timer.start();
        this.color = Color.BLUE;
        this.circulos = new ArrayList<>();
        llenarcirculos();
    }

    public void llenarcirculos() {
        int iniX = 100;
        int iniY = 2;
        Random k = new Random();
        for (int i = 0; i < 200; i++) {
            iniX += -z + Math.abs(k.nextInt() % 150);
            iniY = Math.abs(k.nextInt() % 15);
            this.circulos.add(new Circulos(iniX, iniY));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image fondo = CargarImagen("fondo1.jpg");
        if (roberto.getColisiones() <= 10) {
            for (int i = 0; i < x; i++) {
                g.drawImage(fondo, -x + (i * 800), 0, 800, 500, this);
            }

            Image zombie = CargarImagen("ZRunn.png");

            if (direccion) {
                g.drawImage(zombie, m, 350, m + 100, 464,
                        (this.secuencia * 322), 0, (this.secuencia * 322) + 322, 388, this);
            } else {
                g.drawImage(zombie, m + 100, 350, m, 464,
                        (this.secuencia * 322), 0, (this.secuencia * 322) + 322, 388, this);

            }
            //g.drawRect(m + 17, 355, 75, 105);
            Image meteorito = CargarImagen("meteorito.png");
            g.drawString("Colisiones", 600, 30);
            g.drawString(": " + roberto.getColisiones(), 670, 30);
            int xr = 0;
            int yr = 0;
            for (int i = 0; i < 200; i++) {
                Random l = new Random();
                int in = Math.abs(l.nextInt() % 1000);
                xr = -z + this.circulos.get(i).getX();
                yr = z + this.circulos.get(i).getY();

                if (this.circulos.get(i).getY() > 0) {
                    this.circulos.get(i).setY(-in);
                }

               g.drawImage(meteorito, xr, yr, 60, 110, this);
               g.drawRect(xr, yr, 50, 100);
                Rectangle r = new Rectangle(m + 17, 355, 75, 105);
                Rectangle mu = new Rectangle(xr, yr, 50, 100);
                if (r.intersects(mu)) {
                    roberto.setColisiones(roberto.getColisiones() + 1);
                    muertos.add(mu);
                }
            }
            for (Rectangle p : muertos) {
                nivel1.remove(p);
            }
            muertos = new ArrayList<>();
        } else {
            Image a = CargarImagen("images.png");
            g.drawImage(a, 0, 0, 800, 500, null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        x += 2;
        z += 3;
        repaint();
    }

    private Image CargarImagen(String imageName) {
        ImageIcon ii = new ImageIcon(imageName);
        Image image = ii.getImage();
        return image;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int key = ke.getKeyCode();

        if (key == KeyEvent.VK_RIGHT && m + 10 < 700) {
            m = m + 10;
            if (this.secuencia == 9) {
                this.secuencia = 0;
            } else {
                this.secuencia++;
            }
            direccion = true;
        }
        if (key == KeyEvent.VK_LEFT && m - 10 > 0) {
            m = m - 10;
            if (this.secuencia == 0) {
                this.secuencia = 9;
            } else {
                this.secuencia--;
            }
            direccion = false;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
