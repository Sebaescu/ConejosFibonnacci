/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sebaescu.losconejosdefibonacci;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ModoEducativo extends Application {

    private ImageView imageView;
    private Label explanationLabel;
    private int currentIndex = 0;
    private HBox fila;
    private VBox vbox;
    private Button nextButton, salirButton;
    private String[] explanations = {
            "En la secuencia de Fibonacci, comienza con un conejo recien nacido.",
            "En el segundo mes, se incorpora otro conejo recién nacido que aún no puede reproducirse, "
                    + "por lo que la secuencia comienza con los dos conejos originales.",
            "A partir del tercer mes, los conejos comienzan a reproducirse. "
                    + "La pareja de conejos da a luz a un nuevo conejo.",
            "En cada nuevo mes, la cantidad de conejos actuales dará a luz a la cantidad de conejos que había el mes anterior.",
            "La secuencia continúa creciendo exponencialmente con cada mes que pasa."
                    + " Sumando la actual cantidad de conejos con la anterior para obtener la siguiente secuencia."
    };
    private List<String> rabbitImages = new ArrayList<>(Arrays.asList(
            "com/sebaescu/losconejosdefibonacci/conejo1.png",
            "com/sebaescu/losconejosdefibonacci/conejo2.png",
            "com/sebaescu/losconejosdefibonacci/conejo3.png",
            "com/sebaescu/losconejosdefibonacci/conejo4.png",
            "com/sebaescu/losconejosdefibonacci/conejo5.png",
            "com/sebaescu/losconejosdefibonacci/conejo6.png",
            "com/sebaescu/losconejosdefibonacci/conejo7.png"));

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Modo Educativo");

        // Configuración de la interfaz de usuario
        StackPane root = new StackPane();
        root.setStyle("-fx-background-image: url('com/sebaescu/losconejosdefibonacci/Granja.png'); "
                + "-fx-background-size: cover;");

        vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-color: transparent;");

        fila = new HBox();
        fila.setAlignment(Pos.CENTER);

        ImageView imageView = new ImageView(new Image("com/sebaescu/losconejosdefibonacci/conejo1.png"));
        imageView.setFitWidth(180);
        imageView.setFitHeight(180);
        fila.getChildren().add(imageView);

        explanationLabel = new Label(explanations[currentIndex]);
        explanationLabel.setWrapText(true);
        explanationLabel.setStyle("-fx-background-color: #acc59d; -fx-font-size: 36; -fx-text-fill: #2d6073;");

        HBox botones = new HBox(10);
        botones.setAlignment(Pos.CENTER);

        nextButton = new Button("Siguiente");
        nextButton.setOnAction(e -> mostrarSiguientePaso());

        salirButton = new Button("Salir");
        salirButton.setOnAction(e -> volverAlMenu());

        Button jugarButton = new Button("Jugar");
        jugarButton.setOnAction(event -> {
            primaryStage.hide();
            JuegoEducativo game = new JuegoEducativo();
            game.start(primaryStage);
        });

        nextButton.setStyle("-fx-min-height: 70; -fx-background-color: #dad061; -fx-font-size: 18; -fx-text-fill: #2d6073; -fx-background-radius: 30;");
        jugarButton.setStyle("-fx-min-height: 70; -fx-background-color: #f5ebb0; -fx-font-size: 18; -fx-text-fill: #2d6073; -fx-background-radius: 30;");
        salirButton.setStyle("-fx-min-height: 70; -fx-background-color: #edab8b; -fx-font-size: 18; -fx-text-fill: #2d6073; -fx-background-radius: 30;");

        botones.getChildren().addAll(nextButton, jugarButton, salirButton);
        vbox.getChildren().addAll(explanationLabel, fila, botones);

        root.getChildren().addAll(vbox);

        // Configuración de la escena y visualización
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        currentIndex = 1;
    }

    private void mostrarSiguientePaso() {
        currentIndex++;
        System.out.println(currentIndex);
        switch (currentIndex) {
            case 1:
                fila.getChildren().clear();
                crearConejo();
                explanationLabel.setText(explanations[0]);
                break;
            case 2:
                crearConejo();
                explanationLabel.setText(explanations[1]);
                break;
            case 3:
                crearConejo();
                explanationLabel.setText(explanations[2]);
                break;
            case 4:
                for (int i = 1; i <= 2; i++) {
                    crearConejo();
                }
                explanationLabel.setText(explanations[3]);
                break;
            case 5:
                for (int i = 1; i <= 3; i++) {
                    crearConejo();
                }
                explanationLabel.setText(explanations[4]);
                break;

        }

        if (currentIndex >= 5)
            currentIndex = 0;
    }

    private void crearConejo() {
        Random random = new Random();
        String rutaAleatoria = rabbitImages.get(random.nextInt(rabbitImages.size()));
        ImageView nuevoConejo = new ImageView(new Image(rutaAleatoria));
        nuevoConejo.setFitWidth(180);
        nuevoConejo.setFitHeight(180);
        fila.getChildren().add(nuevoConejo);
    }

    private void volverAlMenu() {
        Platform.runLater(() -> {
            // Crea una nueva instancia del MainMenu y muestra la ventana maximizada
            MenuPrincipal menuPrincipal = new MenuPrincipal();
            Stage primaryStageMenu = new Stage();
            menuPrincipal.start(primaryStageMenu);
            primaryStageMenu.setMaximized(true);
            Stage stageActual = (Stage) nextButton.getScene().getWindow();
            stageActual.close();
        });
    }
}


