package main.Vista;


import Modelo.DAO;
import Entidades.Jugador;
import Log.Log;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import main.Juego;

import java.util.Optional;

/**
 * La clase PantallaPartidas es la interfaz gráfica que muestra las opciones de partida existentes y permite crear nuevas partidas.
 * @author Mario Puente, David Andueza
 */
public class PantallaPartidas extends Application {
    private Canvas canvas;
    private GraphicsContext gc;
    private VBox vbox;
    private Button botonPartida1;
    private Button botonPartida2;
    private Button botonPartida3;
    private BorderPane pane;
    private Scene escena;
    private Group menuRoot;
    private Image fondo;
    private Label titulo;
    private static DAO bd = new DAO();
    private static Log log = new Log();

    /**
     * El método principal que se ejecuta al invocar la clase desde la línea de comandos.
     *
     * @param args Los argumentos de la línea de comandos.
     */
    public static void main(String[] args){
        launch(args);
    }

    /**
     * El método start se ejecuta al iniciar la aplicación JavaFX.
     * Configura la interfaz gráfica de las partidas y muestra la ventana de las partidas.
     *
     * @param stage La ventana de la aplicación.
     */
    public void start(Stage stage) {
        crearCanvas();
        fondo = new Image(getClass().getResource("/Animaciones/Free/Background/FondoMenu.png").toString());
        ImageView imageView = new ImageView(fondo);
        imageView.setFitWidth(canvas.getWidth());
        imageView.setFitHeight(canvas.getHeight());
        imageView.setX(0);
        imageView.setY(0);
        crearBotones();
        crearPane();
        pane.getChildren().add(0, imageView);

        stage.setTitle("Menu");
        stage.setX(400);
        stage.setY(100);
        stage.setWidth(1080);
        stage.setHeight(720);
        stage.setResizable(false);
        stage.setScene(escena);

        setGraphicsContext();

        stage.setScene(escena);
        stage.setTitle("Menu");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Crea los botones de las partidas existentes y el botón para crear una nueva partida.
     */
    public void crearBotones() {
        botonPartida1 = new Button("Nueva partida");
        botonPartida1.setPrefHeight(200);
        botonPartida1.setPrefWidth(900);
        botonPartida1.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(20), Insets.EMPTY)));
        botonPartida1.setOpacity(0.8);
        botonPartida1.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 30));
        if (bd.consultarJugador(1) != null) {
            Jugador jugador1 = bd.consultarJugador(1);
            botonPartida1.setText(jugador1.getNombre());
        }



        botonPartida2 = new Button("Nueva partida");
        botonPartida2.setPrefHeight(200);
        botonPartida2.setPrefWidth(900);
        botonPartida2.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(20), Insets.EMPTY)));
        botonPartida2.setOpacity(0.8);
        botonPartida2.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 30));
        if (bd.consultarJugador(2) != null) { // Utiliza el método next() para avanzar al siguiente registro en el ResultSet
            Jugador jugador2 = bd.consultarJugador(2);
            botonPartida2.setText(jugador2.getNombre());
        }


        botonPartida3 = new Button("Nueva partida");
        botonPartida3.setPrefHeight(200);
        botonPartida3.setPrefWidth(900);
        botonPartida3.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(20), Insets.EMPTY)));
        botonPartida3.setOpacity(0.8);
        botonPartida3.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 30));
        if (bd.consultarJugador(3) != null) {
            Jugador jugador3 = bd.consultarJugador(3);
            botonPartida3.setText(jugador3.getNombre());
        }


        botonPartida1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gc.setFill(Color.BLACK);
                if (!botonPartida1.getText().equals("Nueva partida")) {
                    Jugador jugador1 = bd.consultarJugador(1);
                    cargarPartidaExistente(jugador1);
                }
                else {
                    TextInputDialog dialogo = new TextInputDialog();

                    dialogo.setTitle("Introducir texto");
                    dialogo.setHeaderText(null);
                    Optional<String> resultado = dialogo.showAndWait();
                    resultado.ifPresent(texto -> botonPartida1.setText(texto));
                    bd.insertarDatos(1, dialogo.getResult(), 3, 1);

                    try {
                        crearPartida();
                    } catch (Exception e) {
                        //TODO: especificar tipo excepcion
                        log.logError(e.getMessage());
                        System.out.println("Error al pulsar el boton: " + e);
                    }
                }

            }
        });

        botonPartida2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gc.setFill(Color.BLACK);
                if (!botonPartida2.getText().equals("Nueva partida")) {
                    Jugador jugador2 = bd.consultarJugador(2);
                    cargarPartidaExistente(jugador2);
                }
                else {
                    TextInputDialog dialogo = new TextInputDialog();

                    dialogo.setTitle("Introducir texto");
                    dialogo.setHeaderText(null);
                    Optional<String> resultado = dialogo.showAndWait();
                    resultado.ifPresent(texto -> botonPartida2.setText(texto));
                    bd.insertarDatos(2, dialogo.getResult(), 3, 1);

                    try {
                        crearPartida();
                    } catch (Exception e) {
                        //TODO: especificar tipo excepcion
                        log.logError(e.getMessage());
                        System.out.println("Error al pulsar el boton: " + e);
                    }
                }

            }
        });

        botonPartida3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gc.setFill(Color.BLACK);
                if (!botonPartida3.getText().equals("Nueva partida")) {
                    Jugador jugador3 = bd.consultarJugador(3);
                    cargarPartidaExistente(jugador3);
                }
                else {
                    TextInputDialog dialogo = new TextInputDialog();

                    dialogo.setTitle("Introducir texto");
                    dialogo.setHeaderText(null);
                    Optional<String> resultado = dialogo.showAndWait();
                    resultado.ifPresent(texto -> botonPartida3.setText(texto));
                    bd.insertarDatos(3, dialogo.getResult(), 3, 1);

                    try {
                        crearPartida();
                    } catch (Exception e) {
                        //TODO: especificar tipo excepcion
                        log.logError(e.getMessage());
                        System.out.println("Error al pulsar el boton: " + e);
                    }
                }

            }
        });


        titulo = new Label("FREEMAN");

        titulo.setAlignment(Pos.CENTER);
        botonPartida1.setAlignment(Pos.CENTER);
        botonPartida2.setAlignment(Pos.CENTER);
        botonPartida3.setAlignment(Pos.CENTER);
    }

    /**
     * Crea el contenedor principal de la interfaz.
     */
    public void crearPane() {
        vbox = new VBox();
        vbox.getChildren().addAll(titulo, botonPartida1, botonPartida2, botonPartida3);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);

        pane = new BorderPane();
        pane.setTop(canvas);
        pane.setCenter(vbox);
        escena = new Scene(pane);
    }

    /**
     * Crea el objeto Canvas para dibujar en la interfaz.
     */
    public void crearCanvas() {
        canvas = new Canvas(1280 , 720);
        gc = canvas.getGraphicsContext2D();
    }

    /**
     * Configura el contexto gráfico para el dibujo.
     */
    public void setGraphicsContext() {
        Font fuente = new Font("arial", 50);
        gc.setFont(fuente);

        menuRoot = new Group();
        menuRoot.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();
    }

    /**
     * Crea una nueva partida del juego.
     *
     * @throws Exception si ocurre un error al crear la partida
     */
    public void crearPartida() throws Exception {
        Juego juego = new Juego(null);
        Stage stage = new Stage();
        try {
            juego.start(stage);
        } catch (Exception e) {
            //TODO: especificar tipo excepcion
            log.logError(e.getMessage());
            System.out.println("Error al crear la partida: " + e.toString());
        }
    }

    /**
     * Carga una partida existente del juego.
     *
     * @param jugador el jugador de la partida a cargar
     */
    public void cargarPartidaExistente(Jugador jugador) {
        Juego juego = new Juego(jugador);
        Stage stage = new Stage();
        try {
            juego.start(stage);
        } catch (Exception e) {
            //TODO: especificar tipo excepcion
            log.logError(e.getMessage());
            System.out.println("Error al cargar la partida existente: " + e.toString());
        }
    }

    /**
     * Obtiene el texto del botón de partida especificado.
     *
     * @param partida el número de partida (1, 2 o 3)
     * @return el texto del botón de partida
     */
    public String getBotonPartida(int partida) {
        if (partida == 1) {
            return botonPartida1.getText();
        } else if (partida ==2) {
            return botonPartida2.getText();
        } else if (partida ==3) {
            return botonPartida3.getText();
        } else {
            System.out.println("Boton invalido");
            return null;
        }
    }

}
