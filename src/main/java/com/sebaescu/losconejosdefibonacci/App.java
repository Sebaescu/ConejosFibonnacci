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
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

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
    private Stage stage;
    private HBox botonesBox;
    private Button siguienteButton, reiniciarButton;
    private ScrollPane scrollPane;
    private TextField userInput;
    private Label numeroConejosLabel,nSucesion;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
        public void start(Stage primaryStage) {
            MenuPrincipal menuPrincipal = new MenuPrincipal();
            menuPrincipal.start(primaryStage);
        }
        public void startJuego(Stage primaryStage){
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
        // Botón para generar conejos según la entrada del usuario
        Button generarButton = new Button("Generar Conejos");
        generarButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                generarConejosSegunInput();
                userInput.clear();
                userInput.setPromptText("Ingrese un número (1-10)");
            }
        });
        Button salirButton = new Button("Salir");
        salirButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                volverAlMenu();
                
            }
        });
        // Crear campo de entrada para el usuario
        userInput = new TextField();
        userInput.setPromptText("Ingrese un número (1-10)");
        userInput.setMaxWidth(150);
        inicializarConejos();
        botonesBox = new HBox(30);
        botonesBox.setAlignment(Pos.CENTER);
        numeroConejosLabel = new Label("Número de Conejos: " + conejoImageViews.size());
        nSucesion = new Label("Sucesión #1");
        botonesBox.getChildren().addAll(siguienteButton, reiniciarButton, numeroConejosLabel,nSucesion,userInput,generarButton,salirButton);

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
        nSucesion.setText("Sucesión #"+(index-1));
        numeroConejosLabel.setText("Número de Conejos: " + conejoImageViews.size());
    }

    private void reiniciar() {
        conejoImageViews.clear();
        imagenesVBox.getChildren().clear();
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
        index = 2;
        nSucesion.setText("Sucesión #"+(index-1));
        numeroConejosLabel.setText("Número de Conejos: " + conejoImageViews.size());
    }

    public int fibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
    public void generarConejosSegunInput(){
        try {
            int inputNumero = Integer.parseInt(userInput.getText());

            if (inputNumero >= 1 && inputNumero <= 10) {
                reiniciar();
                if(inputNumero>1){
                    index = inputNumero;
                    siguiente(); // Esto reiniciará y generará conejos según el nuevo índice ingresado.
                }
            } else {
                mostrarAlerta("Por favor, ingrese un número entre 1 y 10.");
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Por favor, ingrese un número válido.");
        }
        
    }
    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    private void volverAlMenu() {
        Platform.runLater(() -> {
            // Crea una nueva instancia del MainMenu y muestra la ventana maximizada
            MenuPrincipal menuPrincipal = new MenuPrincipal();
            Stage primaryStageMenu = new Stage();
            menuPrincipal.start(primaryStageMenu);
            primaryStageMenu.setMaximized(true);
            Stage stageActual = (Stage) siguienteButton.getScene().getWindow();
            stageActual.close();
        });
    }
}


