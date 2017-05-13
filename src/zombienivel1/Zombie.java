/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombienivel1;

/**
 *
 * @author jeisson
 */
public class Zombie {
    private int colisiones = 0;
    private int x;
    private int y;
    private int ancho=108;
    private int alto=90;

    public Zombie(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getColisiones() {
        return colisiones;
    }

    public void setColisiones(int colisiones) {
        this.colisiones = colisiones;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }
    

}
