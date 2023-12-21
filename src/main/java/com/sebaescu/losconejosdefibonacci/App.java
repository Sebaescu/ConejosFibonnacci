package com.sebaescu.losconejosdefibonacci;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javafx.application.Platform;
import javafx.geometry.Insets;

public class App extends Application {
    private List<String> conejosImagenes = new ArrayList<>(Arrays.asList(
            "com/sebaescu/losconejosdefibonacci/conejo1.png",
            "com/sebaescu/losconejosdefibonacci/conejo2.png",
            "com/sebaescu/losconejosdefibonacci/conejo3.png",
            "com/sebaescu/losconejosdefibonacci/conejo4.png",
            "com/sebaescu/losconejosdefibonacci/conejo5.png",
            "com/sebaescu/losconejosdefibonacci/conejo6.png",
            "com/sebaescu/losconejosdefibonacci/conejo7.png"));
    private List<ImageView> conejoImageViews = new ArrayList<>();
    private int index = 2;
    private VBox imagenesVBox;
    private HBox filaActual;
    private VBox root;
    private HBox botonesBox;
    private Button siguienteButton, reiniciarButton;
    private ScrollPane scrollPane;
    private Label numeroConejosLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Los Conejos de Fibonacci");
        primaryStage.setMaximized(true);  // Abrir la ventana maximizada
        
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

        inicializarConejos();
        botonesBox = new HBox(30);
        botonesBox.setAlignment(Pos.CENTER);
        numeroConejosLabel = new Label("Número de Conejos: " + conejoImageViews.size());
        botonesBox.getChildren().addAll(siguienteButton, reiniciarButton, numeroConejosLabel);

        scrollPane = new ScrollPane();
        imagenesVBox.setStyle("-fx-background-color: transparent; -fx-control-inner-background: transparent;");
        filaActual.setStyle("-fx-background-color: transparent; -fx-control-inner-background: transparent;");

        scrollPane.setContent(imagenesVBox);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-control-inner-background: transparent;");
        VBox contenidoVBox = new VBox();
        contenidoVBox.setAlignment(Pos.CENTER);
        contenidoVBox.getChildren().addAll(scrollPane, botonesBox);
        StackPane stackPane = new StackPane();
        Image fondo = new Image("com/sebaescu/losconejosdefibonacci/Granja.png");
        ImageView fondoImageView = new ImageView(fondo);
        fondoImageView.setPreserveRatio(true);
        fondoImageView.setFitWidth(primaryStage.getWidth());
        fondoImageView.setFitHeight(primaryStage.getHeight());
        stackPane.getChildren().addAll(fondoImageView, contenidoVBox);

        // Establecer el fondo de StackPane como transparente
        stackPane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(stackPane);
        root.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.show();

        // Actualizar el tamaño de la imagen de fondo cuando cambia el tamaño de la ventana
        primaryStage.widthProperty().addListener((obs, oldVal, newVal) ->
                fondoImageView.setFitWidth((double) newVal));
        primaryStage.heightProperty().addListener((obs, oldVal, newVal) ->
                fondoImageView.setFitHeight((double) newVal));
    }

    private void inicializarConejos() {
        imagenesVBox = new VBox(10);
        imagenesVBox.setAlignment(Pos.CENTER);

        // Establecer el fondo de VBox como transparente
        imagenesVBox.setStyle("-fx-background-color: transparent; -fx-control-inner-background: transparent;");

        filaActual = new HBox(10);
        filaActual.setAlignment(Pos.CENTER);

        // Establecer el fondo de HBox como transparente
        filaActual.setStyle("-fx-background-color: transparent; -fx-control-inner-background: transparent;");

        Random random = new Random();
        String rutaAleatoria = conejosImagenes.get(random.nextInt(conejosImagenes.size()));
        conejoImageViews.add(new ImageView(new Image(rutaAleatoria)));
        conejoImageViews.get(0).setFitWidth(150);
        conejoImageViews.get(0).setFitHeight(150);
        filaActual.getChildren().add(conejoImageViews.get(0));
        imagenesVBox.getChildren().add(filaActual);
    }

    private void siguiente() {
        index++;
        int numeroConejosAGenerar = fibonacci(index) - conejoImageViews.size();

        for (int i = 0; i < numeroConejosAGenerar; i++) {
            Random random = new Random();
            String rutaAleatoria = conejosImagenes.get(random.nextInt(conejosImagenes.size()));
            ImageView nuevoConejo = new ImageView(new Image(rutaAleatoria));
            nuevoConejo.setFitWidth(150);
            nuevoConejo.setFitHeight(150);

            conejoImageViews.add(nuevoConejo);
            // Establecer el fondo de HBox como transparente
            HBox nuevoHBox = new HBox(10);
            nuevoHBox.setAlignment(Pos.CENTER);
            nuevoHBox.setStyle("-fx-background-color: transparent; -fx-control-inner-background: transparent;");
            nuevoHBox.getChildren().add(nuevoConejo);

            filaActual.getChildren().add(nuevoHBox);

            if (filaActual.getChildren().size() >= 10) {
                filaActual = new HBox(10);
                filaActual.setAlignment(Pos.CENTER);
                // Establecer el fondo de HBox como transparente
                filaActual.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
                imagenesVBox.getChildren().add(filaActual);
            }
        }

        numeroConejosLabel.setText("Número de Conejos: " + conejoImageViews.size());
    }

    private void reiniciar() {
        // Cerrar la ventana actual
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();

        // Crear una nueva instancia de App y lanzarla en un nuevo Stage
        Platform.runLater(() -> {
            try {
                new App().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public int fibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}


