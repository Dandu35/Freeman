package mundo;

import Entidades.Jugador;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.Juego;

import java.util.HashMap;

/**
 * Clase que representa un item en el juego.
 * Los items son objetos que el jugador puede recoger durante el juego.
 * @author Mario Puente, David Andueza
 */
public class Item extends Fondo{
    private int ancho;
    private HashMap<String, Animacion> animaciones;
    private int xImagen;
    private int yImagen;
    private int anchoImagen;
    private int altoImagen;
    private String animacionActual;
    private int alto;
    private boolean coger = false;

    /**
     * Constructor de la clase Item.
     *
     * @param x               Coordenada x del item.
     * @param y               Coordenada y del item.
     * @param nombreImagen    Nombre de la imagen del item.
     * @param animacionActual Nombre de la animación actual del item.
     */
    public Item(int x, int y, String nombreImagen, String animacionActual) {
        super(x, y, nombreImagen);
        this.animacionActual = animacionActual;
        this.ancho = 32;
        this.alto = 32;
        animaciones = new HashMap<String, Animacion>();
        inicializarAnimaciones();
    }

    /**
     * Verifica si el item ha sido recogido por el jugador.
     *
     * @return `true` si el item ha sido recogido, `false` de lo contrario.
     */
    public boolean isCoger() {
        return coger;
    }

    /**
     * Establece si el item ha sido recogido por el jugador.
     *
     * @param coger Valor que indica si el item ha sido recogido o no.
     */
    public void setCoger(boolean coger) {
        this.coger = coger;
    }

    /**
     * Obtiene el nombre de la animación actual del item.
     *
     * @return Nombre de la animación actual del item.
     */
    public String getAnimacionActual() {
        return animacionActual;
    }

    /**
     * Establece el nombre de la animación actual del item.
     *
     * @param animacionActual Nombre de la animación actual del item.
     */
    public void setAnimacionActual(String animacionActual) {
        this.animacionActual = animacionActual;
    }

    /**
     * Inicializa las animaciones del item.
     */
    public void inicializarAnimaciones(){
        Rectangle cordenadasPlatano[] = {
                new Rectangle(0, 0, 32, 32),
                new Rectangle(32, 0, 32, 32),
                new Rectangle(64, 0, 32, 32),
                new Rectangle(96, 0, 32, 32),
                new Rectangle(128, 0, 32, 32),
                new Rectangle(160, 0, 32, 32),
                new Rectangle(192, 0, 32, 32),
                new Rectangle(224, 0, 32, 32),
                new Rectangle(256, 0, 32, 32),
                new Rectangle(288, 0, 32, 32),
                new Rectangle(320, 0, 32, 32),
                new Rectangle(384, 0, 32, 32),
                new Rectangle(416, 0, 32, 32),
                new Rectangle(448, 0, 32, 32),
                new Rectangle(480, 0, 32, 32),
                new Rectangle(512, 0, 32, 32),
                new Rectangle(544, 0, 32, 32),

        };

        Animacion animacionPlatano = new Animacion(0.065, cordenadasPlatano);
        animaciones.put("platano", animacionPlatano);

    }

    /**
     * Calcula la animación actual del item en función del tiempo.
     *
     * @param t Tiempo transcurrido.
     */
    public void calcularAnimacion(double t){

        Rectangle coordenadas = animaciones.get(animacionActual).calcularAnimacionActual(t);
        this.xImagen = (int)coordenadas.getX();
        this.yImagen = (int)coordenadas.getY();
        this.anchoImagen = (int)coordenadas.getWidth();
        this.altoImagen = (int)coordenadas.getHeight();
    }

    /**
     * Obtiene el rectángulo del item.
     *
     * @return Rectángulo que representa el item.
     */
    public Rectangle obtenerRectangulo(){
        return new Rectangle(x,y, ancho,alto);
    }

    @Override
    public void pintar(GraphicsContext graficos) {
        if (this.coger) {
            return;
        }
        else {
            graficos.drawImage( Juego.imagenes.get(nombreImagen),xImagen,yImagen,anchoImagen,altoImagen,this.x ,this.y,anchoImagen ,altoImagen);
        }
    }

    /**
     * Mueve el item en función del movimiento del jugador.
     *
     * @param jugador Jugador del juego.
     */
    public void mover(Jugador jugador) {
        if(Juego.derecha &&jugador.getX()>=1000){
                setX(x-jugador.getVelocidad());
            }
        if (Juego.izquierda&&jugador.getX()<=200){
            setX(x+jugador.getVelocidad());
        }
    }
}
