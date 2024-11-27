package Entidades;

import javafx.scene.canvas.GraphicsContext;

/**
 * La clase abstracta Entidad representa una entidad en el sistema (por ejemplo un jugador).
 * Proporciona métodos comunes para manipular y acceder a los atributos de una entidad.
 * @author Mario Puente, David Andueza
 */
public abstract class Entidad {
    protected int x;
    protected int y;
    protected String nombreImagen;
    protected int velocidad = 3;

    /**
     * Constructor de la clase Entidad.
     *
     * @param x             La coordenada x inicial de la entidad.
     * @param y             La coordenada y inicial de la entidad.
     * @param nombreImagen  El nombre de la imagen asociada a la entidad.
     * @param velocidad     La velocidad de la entidad.
     */
    public Entidad(int x, int y, String nombreImagen, int velocidad) {
        this.x = x;
        this.y = y;
        this.nombreImagen = nombreImagen;
        this.velocidad = velocidad;
    }

    /**
     * Método abstracto que define cómo pintar la entidad en un contexto gráfico.
     *
     * @param graficos  El contexto gráfico en el que se va a pintar la entidad.
     */
    public abstract void pintar(GraphicsContext graficos);

    /**
     * Método abstracto que define cómo mover la entidad.
     */
    public abstract void mover();

    /**
     * Obtiene la coordenada x de la entidad.
     *
     * @return La coordenada x de la entidad.
     */
    public int getX() {
        return x;
    }

    /**
     * Establece la coordenada x de la entidad.
     *
     * @param x La nueva coordenada x de la entidad.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Obtiene la coordenada y de la entidad.
     *
     * @return La coordenada y de la entidad.
     */
    public int getY() {
        return y;
    }

    /**
     * Establece la coordenada y de la entidad.
     *
     * @param y La nueva coordenada y de la entidad.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Obtiene el nombre de la imagen asociada a la entidad.
     *
     * @return El nombre de la imagen asociada a la entidad.
     */
    public String getNombreImagen() {
        return nombreImagen;
    }

    /**
     * Establece el nombre de la imagen asociada a la entidad.
     *
     * @param nombreImagen El nuevo nombre de la imagen asociada a la entidad.
     */
    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    /**
     * Obtiene la velocidad de la entidad.
     *
     * @return La velocidad de la entidad.
     */
    public int getVelocidad() {
        return velocidad;
    }

    /**
     * Establece la velocidad de la entidad.
     *
     * @param velocidad La nueva velocidad de la entidad.
     */
    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }
}
