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
public class Nivel1 extends JPanel implements ActionListener ,KeyListener{
    int x;
    int z;
    int m;
    private ArrayList<Circulos> circulos;
    private Zombie roberto = new Zombie(100, 350);
    private Timer timer;
    private int Delay = 20;
    private int secuencia = 0;
    private Color color;
    private ArrayList<Rectangle> nivel1;
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
        Random r = new Random();
        for (int i = 0; i < 40; i++) {
            iniX += -z + Math.abs(r.nextInt() % 200);
            iniY = Math.abs(r.nextInt()%5);
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
        }
        Image zombie=CargarImagen("ZRunn.png");
         
        if (direccion) {
            g.drawImage(zombie, m, 350, m + 100,  464,
                    (this.secuencia * 322), 0, (this.secuencia * 322) + 322, 388, this);
        } else {
            g.drawImage(zombie, m + 100, 350, m, 464,
                    (this.secuencia * 322), 0, (this.secuencia * 322) + 322, 388, this);
           
        }
        g.drawRect(m + 10,  350, 90, 114);
        Image meteorito = CargarImagen("meteorito.png");
               g.drawString("Colisiones", 600, 30);
        g.drawString(": " + roberto.getColisiones(), 670, 30);
        for (int i = 0; i < 20; i++) {
            int xr = -z + this.circulos.get(i).getX();
            int yr = z + this.circulos.get(i).getY();
            g.drawImage(meteorito, xr, yr, 60, 110, this);
            g.drawRect( xr, yr, 60, 110);
            Rectangle r = new Rectangle(m + 10,  350, 90, 114);
            Rectangle m = new Rectangle(xr, yr, 60, 110);
            if (r.intersects(m)) {
                roberto.setColisiones(roberto.getColisiones() + 1);
                nivel1.add(m);
            }
        }
    for (Rectangle m : nivel1) {
          circulos.remove(m);
        }
 
        /*
        
              for (rec1 m : ni){
                    {re.re( m )
                    }
            }*/
       
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        x += 2;
        z += 3;
        repaint();
    }

    public Rectangle bordeZombie() {
        Rectangle bz = new Rectangle(m + 10,  350, 90, 114);
        return bz;
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
