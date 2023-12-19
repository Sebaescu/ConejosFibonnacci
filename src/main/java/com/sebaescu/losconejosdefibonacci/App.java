package com.sebaescu.losconejosdefibonacci;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class App extends Application {
    private List<String> conejosImagenes = new ArrayList<>(Arrays.asList(
            "com/sebaescu/losconejosdefibonacci/conejo1.png",
            "com/sebaescu/losconejosdefibonacci/conejo2.png",
            "com/sebaescu/losconejosdefibonacci/conejo3.png",
            "com/sebaescu/losconejosdefibonacci/conejo4.png",
            "com/sebaescu/losconejosdefibonacci/conejo5.png"));
    private List<ImageView> conejoImageViews = new ArrayList<>();
    private int index = 2;
    private HBox imagenesHBox;
    private VBox root;
    private HBox botonesBox;
    private Button siguienteButton, reiniciarButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Los Conejos de Fibonacci");

        // Botones Siguiente y Reiniciar
        siguienteButton = new Button("Siguiente");
        siguienteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                siguiente();
            }
        });

        reiniciarButton = new Button("Reiniciar");
        reiniciarButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                reiniciar();
            }
        });

        // HBox para las imágenes
        inicializarConejos();  // Mover la inicialización aquí

        // HBox para contener los HBox
        root = new VBox(10);
        root.setAlignment(Pos.CENTER); // Centrar el VBox
        root.getChildren().add(imagenesHBox);

        // HBox para los botones
        botonesBox = new HBox(10); // Espaciado de 10 entre los elementos
        botonesBox.setAlignment(Pos.CENTER); // Centrar el HBox
        botonesBox.getChildren().addAll(siguienteButton, reiniciarButton);

        // VBox principal
        root.getChildren().addAll(botonesBox);

        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.setMaximized(true); // Inicializar en ventana maximizada
        primaryStage.show();
    }

    private void inicializarConejos() {
        // Inicializar HBox para las imágenes de los conejos
        imagenesHBox = new HBox(10); // Espaciado de 10 entre los elementos
        imagenesHBox.setAlignment(Pos.CENTER); // Centrar el HBox

        // Agregar la primera imagen de conejo al HBox
        Random random = new Random();
        String rutaAleatoria = conejosImagenes.get(random.nextInt(conejosImagenes.size()));
        conejoImageViews.add(new ImageView(new Image(rutaAleatoria)));
        conejoImageViews.get(0).setFitWidth(150); // Establecer el ancho
        conejoImageViews.get(0).setFitHeight(150); // Establecer la altura
        imagenesHBox.getChildren().add(conejoImageViews.get(0));
    }

    private void siguiente() {
        index++;
        // Obtener el número de conejos a generar según la secuencia de Fibonacci
        int numeroConejosAGenerar = fibonacci(index) - conejoImageViews.size();

        // Generar y agregar nuevos conejos
        for (int i = 0; i < numeroConejosAGenerar; i++) {
            Random random = new Random();
            String rutaAleatoria = conejosImagenes.get(random.nextInt(conejosImagenes.size()));
            ImageView nuevoConejo = new ImageView(new Image(rutaAleatoria));
            nuevoConejo.setFitWidth(150); // Establecer el ancho
            nuevoConejo.setFitHeight(150); // Establecer la altura

            conejoImageViews.add(nuevoConejo);
            imagenesHBox.getChildren().add(nuevoConejo);
        }

        // Verificar si es necesario crear un nuevo HBox
        if (imagenesHBox.getChildren().size() >= 10) {
            // Crear un nuevo HBox y agregarlo al contenedor
            root.getChildren().add(imagenesHBox);
            imagenesHBox = new HBox(10);
            imagenesHBox.setAlignment(Pos.CENTER);
        }

        // Actualizar la interfaz
        botonesBox.getChildren().clear();
        botonesBox.getChildren().addAll(siguienteButton, reiniciarButton);
    }

    private void reiniciar() {
        // Mantener la primera imagen y eliminar las demás
        conejoImageViews.subList(1, conejoImageViews.size()).clear();
        index = 2;

        // Actualizar la interfaz
        imagenesHBox.getChildren().clear();
        imagenesHBox.getChildren().addAll(conejoImageViews);

        // Agregar los botones al HBox
        botonesBox.getChildren().clear();
        botonesBox.getChildren().addAll(siguienteButton, reiniciarButton);
    }
    public int fibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}

