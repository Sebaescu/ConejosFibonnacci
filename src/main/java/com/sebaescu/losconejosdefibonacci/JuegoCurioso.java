/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sebaescu.losconejosdefibonacci;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JuegoCurioso extends Application {
    private List<String> imagenesJuegoList;
    private int currentIndex = 1, nCorrectas = 0;
    private ImageView imageView;
    private ToggleGroup toggleGroup;
    private Label labelPregunta;
    private Button buttonSiguiente;
    private VBox vbox;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Juego Curioso");

        // Configuración de la interfaz de usuario
        StackPane root = new StackPane();
        root.setStyle("-fx-background-image: url('com/sebaescu/losconejosdefibonacci/fondo3.png'); "
                + "-fx-background-size: cover;");

        vbox = new VBox(20);
        vbox.setAlignment(Pos.BOTTOM_CENTER);

        // Inicializar lista de imágenes de juego
        imagenesJuegoList = new ArrayList<>();
        Collections.addAll(imagenesJuegoList,
                "com/sebaescu/losconejosdefibonacci/Juego1.png",
                "com/sebaescu/losconejosdefibonacci/Juego1B.png",
                "com/sebaescu/losconejosdefibonacci/Juego2.png",
                "com/sebaescu/losconejosdefibonacci/Juego2B.png",
                "com/sebaescu/losconejosdefibonacci/Juego3.png",
                "com/sebaescu/losconejosdefibonacci/Juego3B.png",
                "com/sebaescu/losconejosdefibonacci/Juego4.png",
                "com/sebaescu/losconejosdefibonacci/Juego4B.png",
                "com/sebaescu/losconejosdefibonacci/Juego5.png",
                "com/sebaescu/losconejosdefibonacci/Juego6.png",
                "com/sebaescu/losconejosdefibonacci/Juego7.png",
                "com/sebaescu/losconejosdefibonacci/Juego8.png",
                "com/sebaescu/losconejosdefibonacci/Juego9.png",
                "com/sebaescu/losconejosdefibonacci/Juego10.png");
        Collections.shuffle(imagenesJuegoList);

        // Pregunta
        labelPregunta = new Label("¿Contiene esta imagen la secuencia de Fibonacci?");
        labelPregunta.setStyle("-fx-font-size: 36; -fx-text-fill: #f5ebb0;");
        vbox.getChildren().add(labelPregunta);

        // Imagen
        currentIndex = 1;
        imageView = new ImageView(new Image(getImagePath()));
        imageView.setFitWidth(350);
        imageView.setFitHeight(350);
        vbox.getChildren().add(imageView);

        // ToggleGroup para los radio buttons
        toggleGroup = new ToggleGroup();

        // RadioButtons
        RadioButton radioButtonSi = new RadioButton("Sí");
        radioButtonSi.setStyle("-fx-font-size: 24; -fx-text-fill: #f5ebb0; -fx-padding: 10;");
        radioButtonSi.setToggleGroup(toggleGroup);

        RadioButton radioButtonNo = new RadioButton("No");
        radioButtonNo.setStyle("-fx-font-size: 24; -fx-text-fill: #f5ebb0; -fx-padding: 10;");
        radioButtonNo.setToggleGroup(toggleGroup);

        // HBox para los radio buttons
        HBox hboxRadioButtons = new HBox(10, radioButtonSi, radioButtonNo);
        hboxRadioButtons.setAlignment(Pos.CENTER);
        vbox.getChildren().add(hboxRadioButtons);

        HBox botones = new HBox(10);
        botones.setAlignment(Pos.CENTER);
        buttonSiguiente = new Button("Siguiente");
        buttonSiguiente.setOnAction(e -> siguientePregunta());
        buttonSiguiente.setStyle("-fx-min-height: 60; -fx-background-color: #dad061; -fx-font-size: 24; -fx-text-fill: #2d6073; -fx-background-radius: 30;");
        Button salirButton = new Button("Salir");
        salirButton.setOnAction(event -> {
            volverAlMenu();
        });
        salirButton.setStyle("-fx-min-height: 70; -fx-background-color: #edab8b; -fx-font-size: 24; -fx-text-fill: #2d6073; -fx-background-radius: 30;");
        botones.getChildren().addAll(buttonSiguiente, salirButton);
        vbox.getChildren().add(botones);

        // Agregar VBox al StackPane
        root.getChildren().add(vbox);

        // Configuración de la escena y visualización
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void mostrarPregunta() {
        labelPregunta.setText("¿Contiene esta imagen la secuencia de Fibonacci?");
        imageView.setImage(new Image(getImagePath()));
        toggleGroup.selectToggle(null); // Desseleccionar los radio buttons
    }

    private String getImagePath() {
        return imagenesJuegoList.get(currentIndex - 1);
    }

    private void siguientePregunta() {
        if (toggleGroup.getSelectedToggle() == null)
            return;
        else {
            // Verificar la respuesta y avanzar a la siguiente pregunta
            String respuestaCorrecta = getRespuestaCorrecta();
            RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
            String respuestaSeleccionada = selectedRadioButton.getText();

            if (respuestaCorrecta.equals(respuestaSeleccionada)) {
                nCorrectas++;
            }
            if (currentIndex == 10) {
                labelPregunta.setText("Tuviste " + nCorrectas + " preguntas correctas!");
                buttonSiguiente.setDisable(true);
                return;
            }
            currentIndex++;
            if (currentIndex <= imagenesJuegoList.size()) {
                mostrarPregunta();
            }
        }
    }

    private String getRespuestaCorrecta() {
        String imagePath = imageView.getImage().getUrl();
        System.out.println(imagePath);
        switch (imagePath) {
            case "com/sebaescu/losconejosdefibonacci/Juego1.png":
            case "com/sebaescu/losconejosdefibonacci/Juego4.png":
            case "com/sebaescu/losconejosdefibonacci/Juego8.png":
            case "com/sebaescu/losconejosdefibonacci/Juego10.png":
                return "Sí";
            default:
                return "No";
        }
    }

    private void volverAlMenu() {
        Platform.runLater(() -> {
            // Crea una nueva instancia del MainMenu y muestra la ventana maximizada
            MenuPrincipal menuPrincipal = new MenuPrincipal();
            Stage primaryStageMenu = new Stage();
            menuPrincipal.start(primaryStageMenu);
            primaryStageMenu.setMaximized(true);
            Stage stageActual = (Stage) buttonSiguiente.getScene().getWindow();
            stageActual.close();
        });
    }
}

