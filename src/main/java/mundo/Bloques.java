package mundo;

import javafx.scene.canvas.GraphicsContext;
import main.Juego;

/**
 * Clase que representa los bloques en el juego.
 * Los bloques tienen diferentes tipos y pueden ser pintados en el lienzo del juego.
 * @author Mario Puente, David Andueza
 */
public class Bloques {
    private int ancho;
    private int alto;
    private int xBloque;
    private int yBloque;
    private String nombreImagen;
    private int x;
    private int y;
    private int tipoBloque;

    /**
     * Constructor de la clase Bloques.
     *
     * @param tipoBloque    Tipo del bloque.
     * @param ancho         Ancho del bloque.
     * @param alto          Alto del bloque.
     * @param nombreImagen  Nombre de la imagen del bloque.
     * @param x             Coordenada x del bloque.
     * @param y             Coordenada y del bloque.
     */
    public Bloques(int tipoBloque, int ancho, int alto, String nombreImagen, int x, int y) {
        this.ancho = ancho;
        this.alto = alto;
        this.nombreImagen = nombreImagen;
        this.x = x;
        this.y = y;

        switch (tipoBloque){
            case 1:
                this.xBloque= 96;
                this.yBloque= 0;
                break;
            case 2:
                this.xBloque= 96;
                this.yBloque= 64;
                break;
            case 3:
                this.xBloque= 96;
                this.yBloque= 128;
                break;
            case 4:
                this.xBloque= 192;
                this.yBloque= 0;
                this.alto = 16;
                break;
            case 5:
                this.xBloque= 192;
                this.yBloque= 64;
                break;
            case 6:
                this.xBloque= 192;
                this.yBloque= 128;
                break;

        }

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

    public int getxBloque() {
        return xBloque;
    }

    public void setxBloque(int xBloque) {
        this.xBloque = xBloque;
    }

    public int getyBloque() {
        return yBloque;
    }

    public void setyBloque(int yBloque) {
        this.yBloque = yBloque;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    public int getTipoBloque() {
        return tipoBloque;
    }

    public void setTipoBloque(int tipoBloque) {
        this.tipoBloque = tipoBloque;
    }

    /**
     * Pinta el bloque en el lienzo del juego.
     *
     * @param graficos Contexto gráfico del juego.
     */
    public void pintar(GraphicsContext graficos){

        graficos.drawImage(Juego.imagenes.get(nombreImagen),xBloque,yBloque,ancho,alto,x,y,ancho,alto);

    }

}
