package mundo;

import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

/**
 * Clase que representa una animación compuesta por una secuencia de coordenadas de rectángulos.
 * @author Mario Puente, David Andueza
 */
public class Animacion {
    private double duracion;
    private Rectangle coordenadas[];

    /**
     * Constructor de la clase Animacion.
     *
     * @param duracion    Duración total de la animación.
     * @param coordenadas Arreglo de rectángulos que representan las coordenadas de la animación.
     */
    public Animacion(double duracion, Rectangle coordenadas[]) {
        this.duracion = duracion;
        this.coordenadas = coordenadas;
    }

    /**
     * Obtiene la duración total de la animación.
     *
     * @return Duración total de la animación.
     */
    public double getDuracion() {
        return duracion;
    }

    /**
     * Establece la duración total de la animación.
     *
     * @param duracion Duración total de la animación.
     */
    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    /**
     * Obtiene las coordenadas de los rectángulos que componen la animación.
     *
     * @return Arreglo de rectángulos que representan las coordenadas de la animación.
     */
    public Rectangle[] getCoordenadas() {
        return coordenadas;
    }

    /**
     * Establece las coordenadas de los rectángulos que componen la animación.
     *
     * @param coordenadas Arreglo de rectángulos que representan las coordenadas de la animación.
     */
    public void setCoordenadas(Rectangle[] coordenadas) {
        this.coordenadas = coordenadas;
    }

    /**
     * Calcula el rectángulo de coordenadas correspondiente a un instante de tiempo dado.
     *
     * @param t Instante de tiempo.
     * @return Rectángulo de coordenadas correspondiente al instante de tiempo dado.
     */
    public Rectangle calcularAnimacionActual (double t) {
        int cantidadFrames = this.coordenadas.length;
        int frameActual= (int)(t%(cantidadFrames*duracion)/duracion);
        return coordenadas[frameActual];
    }
}
