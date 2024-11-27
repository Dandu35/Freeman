package mundo;

import Entidades.Jugador;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import main.Juego;

import java.util.HashMap;

/**
 * Clase que representa una bandera en el juego.
 * La bandera puede tener animaciones y se puede interactuar con ella.
 * @author Mario Puente, David Andueza
 */
public class Bandera extends Fondo{
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
     * Constructor de la clase Bandera.
     *
     * @param x              Coordenada x de la posición de la bandera.
     * @param y              Coordenada y de la posición de la bandera.
     * @param nombreImagen   Nombre de la imagen de la bandera.
     * @param animacionActual Nombre de la animación actual de la bandera.
     */
    public Bandera(int x, int y, String nombreImagen, String animacionActual) {
        super(x, y, nombreImagen);
        this.animacionActual = animacionActual;
        this.ancho = 64;
        this.alto = 64;
        animaciones = new HashMap<String, Animacion>();
        inicializarAnimaciones();
    }

    /**
     * Verifica si la bandera ha sido cogida.
     *
     * @return true si la bandera ha sido cogida, false de lo contrario.
     */
    public boolean isCoger() {
        return coger;
    }

    /**
     * Establece si la bandera ha sido cogida.
     *
     * @param coger true si la bandera ha sido cogida, false de lo contrario.
     */
    public void setCoger(boolean coger) {
        this.coger = coger;
    }

    /**
     * Obtiene el nombre de la animación actual de la bandera.
     *
     * @return Nombre de la animación actual de la bandera.
     */
    public String getAnimacionActual() {
        return animacionActual;
    }

    /**
     * Establece el nombre de la animación actual de la bandera.
     *
     * @param animacionActual Nombre de la animación actual de la bandera.
     */
    public void setAnimacionActual(String animacionActual) {
        this.animacionActual = animacionActual;
    }

    /**
     * Inicializa las animaciones disponibles para la bandera.
     */
    public void inicializarAnimaciones(){
        Rectangle cordenadasBandera[] = {
                new Rectangle(0, 0, 64, 64),
                new Rectangle(64, 0, 64, 64),
                new Rectangle(128, 0, 64, 64),
                new Rectangle(192, 0, 64, 64),
                new Rectangle(256, 0, 64, 64),
                new Rectangle(320, 0, 64, 64),
                new Rectangle(384, 0, 64, 64),
                new Rectangle(448, 0, 64, 64),
        };

        Animacion animacionBandera = new Animacion(0.065, cordenadasBandera);
        animaciones.put("bandera", animacionBandera);

    }

    /**
     * Calcula la animación actual de la bandera en función del tiempo.
     *
     * @param t Instante de tiempo.
     */
    public void calcularAnimacion(double t){
        Rectangle coordenadas = animaciones.get(animacionActual).calcularAnimacionActual(t);
        this.xImagen = (int)coordenadas.getX();
        this.yImagen = (int)coordenadas.getY();
        this.anchoImagen = (int)coordenadas.getWidth();
        this.altoImagen = (int)coordenadas.getHeight();
    }

    /**
     * Obtiene el rectángulo que representa la posición y dimensiones de la bandera.
     *
     * @return Rectángulo que representa la posición y dimensiones de la bandera.
     */
    public Rectangle obtenerRectangulo(){
        return new Rectangle(x,y, ancho,alto);
    }

    @Override
    public void pintar(GraphicsContext graficos) {
            graficos.drawImage( Juego.imagenes.get(nombreImagen),xImagen,yImagen,anchoImagen,altoImagen,this.x ,this.y,anchoImagen ,altoImagen);
    }

    /**
     * Mueve la bandera en función de la posición del jugador.
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

