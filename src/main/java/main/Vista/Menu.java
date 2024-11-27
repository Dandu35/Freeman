package main.Vista;

import Modelo.DAO;
import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;

import static javafx.application.Application.launch;

/**
 * La clase Menu es la interfaz gráfica principal del juego.
 * Proporciona un menú con botones para jugar y salir del juego.
 * @author Mario Puente, David Andueza
 */
public class Menu extends Application {
    private Canvas canvas;
    private GraphicsContext gc;
    private VBox vbox;
    private Button botonJugar;
    private Button botonSalir;
    private BorderPane pane;
    private Scene escena;
    private Group menuRoot;
    private Label titulo;
    private Image fondo;
    private DAO bd;

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
     * Configura la interfaz gráfica del menú principal y muestra la ventana del menú.
     *
     * @param stage La ventana de la aplicación.
     */
    public void start(Stage stage) {
        iniciarDB();

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
     * Inicializa la base de datos y crea el archivo de base de datos si no existe.
     */
    public void iniciarDB() {
        bd = new DAO();
        File archivoBD = new File(bd.getRuta());
        if (!archivoBD.exists()) {
            bd.crearDB();
        }
    }

    /**
     * Crea los botones del menú.
     */
    public void crearBotones() {
        botonJugar = new Button("JUGAR");
        botonSalir = new Button("SALIR");
        botonJugar.setPrefWidth(500);
        botonJugar.setPrefHeight(100);
        botonSalir.setPrefWidth(200);
        botonSalir.setPrefHeight(50);
        botonJugar.setTextFill(Color.BLACK);
        botonSalir.setTextFill(Color.BLACK);

        botonJugar.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 40));
        botonSalir.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 30));

        botonJugar.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(20), Insets.EMPTY)));
        botonJugar.setOpacity(0.8);

        botonSalir.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(20), Insets.EMPTY)));

        botonJugar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gc.setFill(Color.BLACK);
                Stage stageActual = (Stage) botonJugar.getScene().getWindow();
                stageActual.close();
                Stage stageNuevo = new Stage();
                PantallaPartidas pantalla = new PantallaPartidas();
                pantalla.start(stageNuevo);
            }
        });

        botonSalir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gc.setFill(Color.BLACK);
                Platform.exit();
            }

        });

        titulo = new Label("FREEMAN");
        titulo.setTextFill(Color.WHITE);
        titulo.setTextFill(Color.WHITE);
        titulo.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 150));


        titulo.setAlignment(Pos.CENTER);
        botonJugar.setAlignment(Pos.CENTER);
        botonSalir.setAlignment(Pos.CENTER);
    }

    /**
     * Crea el contenedor principal de la interfaz gráfica.
     */
    public void crearPane() {
        vbox = new VBox();
        vbox.getChildren().addAll(titulo, botonJugar, botonSalir);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(70);

        pane = new BorderPane();
        pane.setTop(canvas);
        pane.setCenter(vbox);
        escena = new Scene(pane);
    }

    /**
     * Crea el lienzo de dibujo (canvas) de la interfaz gráfica.
     */
    public void crearCanvas() {
        canvas = new Canvas(1280 , 720);
        gc = canvas.getGraphicsContext2D();
    }

    /**
     * Configura el contexto gráfico del lienzo de dibujo.
     */
    public void setGraphicsContext() {
        Font fuente = new Font("arial", 50);
        gc.setFont(fuente);

        menuRoot = new Group();
        menuRoot.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();
    }

}
