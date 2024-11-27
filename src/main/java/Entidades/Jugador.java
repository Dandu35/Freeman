package Entidades;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.Juego;
import mundo.Animacion;
import mundo.Bandera;
import mundo.Item;
import mundo.Mapa;

import java.util.HashMap;

/**
 * La clase Jugador representa al personaje jugable en el juego.
 * Extiende la clase abstracta Entidad y proporciona métodos y atributos específicos del jugador.
 * @author Mario Puente, David Andueza
 */
public class Jugador extends Entidad  {
    private int vidas;
    private HashMap<String, Animacion> animaciones;
    private int xImagen;
    private int yImagen;
    private int anchoImagen;
    private int altoImagen;
    private String animacionActual;
    private int direccion = 1;
    private int contarItems=0;
    private float velocidadAire = 0f;
    private float gravedad = 0.20f;
    private float velocidadSalto = -3.25f;
    private float velocidadCaida = 9.5f;
    public boolean noHaySuelo = true;
    private Mapa mapa;
    public String nombre;
    public int mundo;

    /**
     * Constructor de la clase Jugador.
     *
     * @param x                La coordenada x inicial del jugador.
     * @param y                La coordenada y inicial del jugador.
     * @param vidas            El número de vidas del jugador.
     * @param velocidad        La velocidad del jugador.
     * @param nombreImagen     El nombre de la imagen asociada al jugador.
     * @param animacionActual  La animación actual del jugador.
     * @param nombre           El nombre del jugador.
     * @param mundo            El número de mundo en el que se encuentra el jugador.
     */
    public Jugador(int x, int y, int vidas,int velocidad, String nombreImagen,String animacionActual, String nombre, int mundo) {
        super(x, y, nombreImagen, velocidad );
        this.vidas = vidas;
        this.nombre = nombre;
        this.mundo = mundo;
        this.animacionActual = animacionActual;
        animaciones = new HashMap<String, Animacion>();
        inicializarAnimaciones();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMundo() {
        return mundo;
    }

    public void setMundo(int mundo) {
        this.mundo = mundo;
    }

    public float getVelocidadSalto() {
        return velocidadSalto;
    }

    public void setVelocidadSalto(float velocidadSalto) {
        this.velocidadSalto = velocidadSalto;
    }

    public float getVelocidadCaida() {
        return velocidadCaida;
    }

    public void setVelocidadCaida(float velocidadCaida) {
        this.velocidadCaida = velocidadCaida;
    }

    public boolean isEnSuelo() {
        return noHaySuelo;
    }

    public void setNoHaySuelo(boolean noHaySuelo) {
        this.noHaySuelo = noHaySuelo;
    }

    public int getDireccion() {
        return direccion;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    public String getAnimacionActual() {
        return animacionActual;
    }

    public void setAnimacionActual(String animacionActual) {
        this.animacionActual = animacionActual;
    }
    private boolean puedoMoverme = true;

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    /**
     * Inicializa las animaciones del jugador.
     */
    public void inicializarAnimaciones(){
        Rectangle cordenadasCorrer[] = {
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
                new Rectangle(352, 0, 32, 32),

        };

        Animacion animacionCorrer = new Animacion(0.065, cordenadasCorrer);
        animaciones.put("correr", animacionCorrer);

        Rectangle coordenadasAndar[] ={
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
        };

        Animacion animacionAndar = new Animacion(0.065, coordenadasAndar);
        animaciones.put("andar", animacionAndar);

    }

    /**
     * Calcula la animación actual del jugador en base al tiempo.
     *
     * @param t El tiempo transcurrido.
     */
    public void calcularAnimacion(double t){
        Rectangle coordenadas = animaciones.get(animacionActual).calcularAnimacionActual(t);
        this.xImagen = (int)coordenadas.getX();
        this.yImagen = (int)coordenadas.getY();
        this.anchoImagen = (int)coordenadas.getWidth();
        this.altoImagen = (int)coordenadas.getHeight();
    }

    /**
     * Obtiene el rectángulo que representa al jugador.
     *
     * @return El rectángulo que representa al jugador.
     */
    public  Rectangle obtenerRectangulo(){
        return new Rectangle(x,y, direccion* anchoImagen-5,altoImagen-2);
    }

    @Override
    public void pintar(GraphicsContext graficos){

        graficos.drawImage( Juego.imagenes.get(nombreImagen),xImagen,yImagen,anchoImagen,altoImagen,x + (direccion==-1?anchoImagen:0),y,  direccion* anchoImagen -5,altoImagen-2);
        graficos.setFill(Color.RED);
        graficos.strokeRect(x,y,anchoImagen-5,altoImagen-2);
    }
    @Override
    public void mover(){


        if( x<200){
            x=200;

        }
        if( x>1000){
            x=1000;
        }

        if (Juego.derecha){
            x+= velocidad;
        }
        if (Juego.izquierda){
            x-=velocidad;
        }

        if(Juego.correr){
            //setVelocidad(5);
            velocidad = 5;
        }


        if (Mapa.estaEnAire) {
            velocidadAire += gravedad;
            setY(getY() + (int)velocidadAire*3);
        }

        if (Juego.saltar && !Mapa.estaEnAire) {
            velocidadAire = velocidadSalto;
            Mapa.estaEnAire = true;
        }
        if(y>= 700){
            setVidas(getVidas()-1);
            if(getVidas()<=0){
                Platform.exit();
            }
            setX(400);
            setY(640);
        }
    }

    /**
     * Verifica la colisión del jugador con un item.
     * Si el item no ha sido cogido y hay colisión, se incrementa el número de vidas del jugador.
     *
     * @param item El item con el que se verifica la colisión.
     */
    public void verificarColisionItem(Item item){
        if(!item.isCoger() && this.obtenerRectangulo().getBoundsInLocal().intersects(item.obtenerRectangulo().getBoundsInLocal())) {
            this.vidas += 1;
            item.setCoger(true);
            contarItems+=1;
            if(contarItems==5){
                Platform.exit();
            }
        }
    }

    /**
     * Verifica la colisión del jugador con una bandera.
     * Si hay colisión, se finaliza el juego.
     *
     * @param bandera La bandera con la que se verifica la colisión.
     */
    public void verificarColisionBandera(Bandera bandera){
        if(this.obtenerRectangulo().getBoundsInLocal().intersects(bandera.obtenerRectangulo().getBoundsInLocal())) {
            Platform.exit();
        }
    }
}
