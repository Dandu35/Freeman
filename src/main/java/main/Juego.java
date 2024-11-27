package main;

import Modelo.DAO;
import Entidades.Jugador;
import Log.Log;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import main.Vista.PantallaPartidas;
import mundo.*;

import java.util.HashMap;

/**
 * Clase principal que representa el juego.
 * @author Mario Puente, David Andueza
 */
public class Juego extends Application {
    private GraphicsContext graficos;
    private Group root;
    private Scene escena;
    private Fondo fondo;
    private PantallaPartidas partidas = new PantallaPartidas();
    private Mapa mapa;
    private Canvas lienzo;
    private int x, y;
    private Jugador jugador;
    public static boolean arriba, abajo, izquierda, derecha, correr, saltar;
    public static HashMap<String, Image> imagenes;
    private Item item;
    private Bandera bandera;
    private int partida;
    DAO bd = new DAO();
    private static Log log = new Log();

    //private Bloques bloque;

    public Juego (Jugador jugador) {
        this.jugador = jugador;
    }

    /**
     * Método principal para ejecutar la aplicación.
     *
     * @param args los argumentos de línea de comandos
     */
    public static void main(String[] args){
        launch(args);
    }

    /**
     * Inicia la interfaz gráfica del juego.
     *
     * @param ventana el escenario principal de la aplicación
     * @throws Exception si ocurre un error al iniciar el juego
     */
    @Override
    public void start(Stage ventana) throws Exception {
        inicializarComponentes();
        eventos();
        ventana.setScene(escena);
        ventana.setTitle("Juego");
        ventana.setResizable(true);
        ventana.show();
        refreshJuego();
    }

    /**
     * Actualiza el estado del juego en cada ciclo de animación y realiza el dibujado en el lienzo.
     */
    public void refreshJuego(){
        long tiempoInicial = System.nanoTime();
        AnimationTimer animationTimer = new AnimationTimer() {
            //Este metodo se ejecuta en bucle
            @Override
            public void handle(long tiempoActual) {
                double t = (tiempoActual - tiempoInicial) / 1000000000.0;
                System.out.println(t);
                actualizarEstado(t);
                pintar();
            }
        };

        animationTimer.start();

    }

    /**
     * Actualiza el estado del juego en cada ciclo de animación.
     *
     * @param t el tiempo transcurrido desde el último ciclo de animación
     */
    public void actualizarEstado(double t){
        item.calcularAnimacion(t);
        bandera.calcularAnimacion(t);
        try {
            jugador.verificarColisionItem(item);
            jugador.verificarColisionBandera(bandera);
        } catch (NullPointerException e) {
            //TODO: revisar
            log.logError(e.getMessage());
            System.out.println("Error al verificar la colision de un item: " + e);
        }
        mapa.hitBoxBloque(jugador);
        jugador.calcularAnimacion(t);
        jugador.mover();
        mapa.mover(jugador);
        item.mover(jugador);
        bandera.mover(jugador);
    }

    /**
     * Inicializa los componentes del juego.
     */
    public void inicializarComponentes(){
        imagenes = new HashMap<String, Image>();
        cargarImagenes();

        if (bd.consultarJugador(partida) != null) {
            String nombreJugador = bd.consultarJugador(partida).getNombre();
            int mundo = bd.consultarJugador(partida).getMundo();
            if (jugador == null) {
                jugador = new Jugador(400, 640,3,3,"MaskDudeAndar","andar", nombreJugador, mundo);
            }
        } else {
            jugador = new Jugador(400, 640, 3, 3, "MaskDudeAndar", "andar", partidas.getBotonPartida(partida), 1);
        }


        fondo = new Fondo(0,0,"BackgroundAzul");
        // bloque = new Bloques(48,48,96,0,"Terreno",0,0);
        mapa  = new Mapa(0,0,"Terreno",3);
        mapa.inicializarBloques(1);
        item = new Item(490,500,"Moneda","platano");
        bandera= new Bandera(1500,300 ,"bandera","bandera");
        root = new Group();
        escena = new Scene(root,1280 ,720);
        lienzo = new Canvas(1280 , 720);
        root.getChildren().add(lienzo);

        graficos = lienzo.getGraphicsContext2D();
    }

    /**
     * Carga las imágenes utilizadas en el juego.
     */
    public void cargarImagenes(){
        imagenes.put("MaskDudeAndar",new Image((getClass().getResource("/Animaciones/Free/Main Characters/Mask Dude/Idle (32x32).png").toString())));
        imagenes.put("BackgroundAzul", new Image(getClass().getResource("/Animaciones/Free/Background/Blue.png").toString()));
        imagenes.put("Terreno", new Image(getClass().getResource("/Animaciones/Free/Terrain/Terrain (16x16).png").toString()));
        imagenes.put("MaskDudeCorrer",new Image((getClass().getResource("/Animaciones/Free/Main Characters/Mask Dude/Run (32x32).png").toString())));
        imagenes.put("Moneda",new Image((getClass().getResource("/Animaciones/Free/Items/Fruits/Bananas.png").toString())));
        imagenes.put("bandera",new Image((getClass().getResource("/Animaciones/Free/Items/Checkpoints/End/End (Pressed) (64x64).png").toString())));
    }

    /**
     * Dibuja los elementos del juego en el lienzo.
     */
    public void pintar() {

        fondo.pintar(graficos);
        //bloque.pintar(graficos);

        mapa.pintar(graficos);
        jugador.pintar(graficos);
        item.pintar(graficos);
        bandera.pintar(graficos);
        graficos.fillText("Vidas: "+jugador.getVidas(), 20,20);

    }

    /**
     * Maneja los eventos de teclado del juego.
     */
    public void eventos(){
        // escena.setOnKeyPressed(new Evento());
        escena.setOnKeyPressed(new EventHandler<KeyEvent>() {
            //Cada vez que se presiona una tecla.
            @Override
            public void handle(KeyEvent evento) {
                switch (evento.getCode().toString()){
                    case "w":
                        arriba = true;
                        break;
                    case "S":
                        abajo = true;
                        break;
                    case "A":
                        izquierda = true;
                        jugador.setDireccion(-1);
                        break;
                    case "D":
                        derecha = true;
                        jugador.setDireccion(1);
                        break;
                    case  "SHIFT":
                        correr = true;
                        //jugador.setVelocidad(5);
                        jugador.setNombreImagen("MaskDudeCorrer");
                        break;
                    case "SPACE":
                        saltar = true;
                        break;
                    case "ESCAPE":
                        jugador.setVidas(jugador.getVidas() - 1);
                        bd.insertarDatos(partida, jugador.getNombre(), jugador.getVidas(), jugador.getMundo());
                        Platform.exit();
//                        Stage stageNuevo = new Stage();
//                        PantallaPartidas pantalla = new PantallaPartidas();
//                        pantalla.start(stageNuevo);
//                        break;
                }
            }
        });

        escena.setOnKeyReleased(new EventHandler<KeyEvent>() {
            //Cada vez que se suelta una tecla.
            @Override
            public void handle(KeyEvent evento) {
                switch (evento.getCode().toString()){
                    case "W":
                        arriba = false;
                        break;
                    case "S":
                        abajo = false;
                        break;
                    case "A":
                        izquierda = false;
                        break;
                    case "D":
                        derecha = false;
                        break;
                    case  "SHIFT":
                        correr= false;
                        jugador.setVelocidad(3);
                        mapa.setVelocidad(3);
                        jugador.setNombreImagen("MaskDudeAndar");
                        break;
                    case "SPACE":
                        saltar = false;
                        break;
                }
            }
        });
    }

}