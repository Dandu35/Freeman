package mundo;

import Entidades.Entidad;
import javafx.scene.canvas.GraphicsContext;
import main.Juego;

/**
 * Clase que representa el fondo en el juego.
 * El fondo puede ser pintado en el lienzo del juego y moverse.
 * @author Mario Puente, David Andueza
 */
public class Fondo  {
    private final int ALTURA_TAMANO_FONDO = 1280 ;
    private final int ANCHURA_TAMANO_FONDO = 720;
    protected int x,y;
    protected String nombreImagen;

    /**
     * Constructor de la clase Fondo.
     *
     * @param x            Coordenada x del fondo.
     * @param y            Coordenada y del fondo.
     * @param nombreImagen Nombre de la imagen del fondo.
     */
    public Fondo(int x, int y, String nombreImagen) {
        this.x = x;
        this.y = y;
        this.nombreImagen = nombreImagen;
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

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    /**
     * Pinta el fondo en el lienzo del juego.
     *
     * @param graficos Contexto gráfico del juego.
     */
    public void pintar(GraphicsContext graficos) {
        graficos.drawImage(Juego.imagenes.get(this.nombreImagen), this.x, this.y,ALTURA_TAMANO_FONDO,ANCHURA_TAMANO_FONDO);
    }

}
