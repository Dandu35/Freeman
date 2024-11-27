package mundo;

import Entidades.Jugador;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import main.Juego;

import java.util.ArrayList;

/**
 * La clase Mapa representa un mapa del juego que contiene bloques y se utiliza para el movimiento del jugador.
 * @author Mario Puente, David Andueza
 */
public class Mapa extends Fondo {

    private int velocidad;
    private int nivelMundo;
    private int ancho;
    private boolean choca = false;
    private boolean chocaFin = false;
    private int alto;
    private boolean chocaLateral;
    private ArrayList<Bloques> bloques;
    private ArrayList<Bloques> bloquesAire;
    public static  boolean estaEnAire = false;
    private int bloquesmap[][] = {
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    };
    private int bloquesmapMundo1[][] = {
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,4,4,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,4,4,0},
            {0,0,0,0,0,0,0,0,0,4,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,4,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,4,0,0,0,0,0,0,0,1,0,0},
            {0,0,1,1,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,1,0,0,0},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1},
    };

    /**
     * Crea un nuevo objeto Mapa con la posición inicial, el nombre de la imagen y la velocidad especificados.
     *
     * @param x             La coordenada x inicial del mapa.
     * @param y             La coordenada y inicial del mapa.
     * @param nombreImagen  El nombre de la imagen del mapa.
     * @param velocidad     La velocidad del mapa.
     */
    public Mapa(int x, int y, String nombreImagen, int velocidad) {
        super(x, y, nombreImagen);
        this.velocidad=velocidad;
    }

    /**
     * Comprueba si el jugador está chocando con algún bloque.
     *
     * @return true si el jugador está chocando con algún bloque, false de lo contrario.
     */
    public boolean isChoca() {
        return choca;
    }

    /**
     * Establece si el jugador está chocando con algún bloque.
     *
     * @param choca true si el jugador está chocando con algún bloque, false de lo contrario.
     */
    public void setChoca(boolean choca) {
        this.choca = choca;
    }

    /**
     * Obtiene la velocidad del mapa.
     *
     * @return La velocidad del mapa.
     */
    public int getVelocidad() {
        return velocidad;
    }

    /**
     * Establece la velocidad del mapa.
     *
     * @param velocidad La velocidad del mapa.
     */
    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    /**
     * Comprueba si el jugador está en el aire.
     *
     * @return true si el jugador está en el aire, false de lo contrario.
     */
    public static boolean isEstaEnAire() {
        return estaEnAire;
    }

    /**
     * Establece si el jugador está en el aire.
     *
     * @param estaEnAire true si el jugador está en el aire, false de lo contrario.
     */
    public static void setEstaEnAire(boolean estaEnAire) {
        Mapa.estaEnAire = estaEnAire;
    }

    /**
     * Inicializa los bloques del mapa según el nivel del mundo especificado.
     *
     * @param nivelMundo El nivel del mundo.
     */
    public void inicializarBloques(int nivelMundo){
        switch (nivelMundo){
            case 1:
                bloquesmap = bloquesmapMundo1;
                break;
        }
        bloques = new ArrayList<Bloques>();
        for (int i = 0; i < bloquesmap.length ; i++) {
            for (int j = 0; j < bloquesmap[i].length ; j++) {
                if (bloquesmap[i][j] != 0){
                    this.bloques.add(new Bloques(bloquesmap[i][j],48,48,"Terreno",j*48,i*48));
                }else{
                    setEstaEnAire(true);
                }
            }
        }
    }



    public void hitBoxBloque(Jugador jugador) {
        Rectangle obtenerRectangulo = new Rectangle();
        for (int i = 0; i < bloques.size(); i++) {
           Bloques bloqueActual = bloques.get(i);
           obtenerRectangulo = new Rectangle(bloqueActual.getX(), bloqueActual.getY(), bloqueActual.getAncho(), bloqueActual.getAlto());
           int posJugXDer = jugador.getX() + jugador.getVelocidad();
           int posJugXIzq = jugador.getX();
           int posJugY = jugador.getY();
           boolean chocaIzq = false;
           boolean chocaDer = false;

           if (posJugXDer+jugador.obtenerRectangulo().getWidth() >= bloqueActual.getX() && posJugXDer +jugador.obtenerRectangulo().getWidth() <=(bloqueActual.getX()+bloqueActual.getAncho()) && posJugY>=bloqueActual.getY() && posJugY<=(bloqueActual.getY()+bloqueActual.getAlto())) {
               Juego.derecha = false;
                chocaDer = true;

           }
           if (posJugXIzq <= (bloqueActual.getX()+ bloqueActual.getAncho()) && posJugXIzq - jugador.obtenerRectangulo().getWidth() >=(bloqueActual.getX()+bloqueActual.getAncho()) && posJugY>=bloqueActual.getY() && posJugY<=(bloqueActual.getY()+bloqueActual.getAlto())) {
                    Juego.izquierda = false;
              chocaIzq = true;
           }
           if(posJugY+jugador.obtenerRectangulo().getHeight() >= bloqueActual.getY()&&(posJugY<=bloqueActual.getY()+bloqueActual.getAlto())&& (chocaDer || chocaIzq)){
                    setEstaEnAire(false);
                    jugador.setY(bloqueActual.getY() - (int) jugador.obtenerRectangulo().getHeight());
           }
        }
    }



    public void pintar(GraphicsContext graficos) {
        for (int i = 0; i < bloques.size(); i++) {
            bloques.get(i).pintar(graficos);
        }
    }


    public boolean isChocaJugadorFin() {
        return chocaFin;
    }

    public static void setChocaJugadorFin(boolean chocaFin) {
        chocaFin = chocaFin;
    }

    public void getXJugador(Jugador jugador){
        x = jugador.getX();
        if (x==200 || x==1000){
            setChocaJugadorFin(true);
        }else {setChocaJugadorFin(false);}

    }



    public void mover(Jugador jugador) {
        if(Juego.derecha &&jugador.getX()>=1000){
            for (int i = 0; i < bloques.size(); i++) {
                x= bloques.get(i).getX();
                velocidad= jugador.getVelocidad();
                bloques.get(i).setX(x-velocidad);
            }
        }
        if (Juego.izquierda&&jugador.getX()<=200){
            for (int i = 0; i < bloques.size(); i++) {
                x=  bloques.get(i).getX();
                velocidad= jugador.getVelocidad();
                bloques.get(i).setX(x+velocidad);
            }
        }
    }
}
