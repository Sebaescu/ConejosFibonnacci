/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sebaescu.losconejosdefibonacci;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.application.Platform;

public class JuegoCurioso extends Application {
    private List<String> imagenesJuegoList;
    private int currentIndex = 1, nCorrectas =0;
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

        // VBox principal
        vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);

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

         // VBox principal
        vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);

        // Pregunta
        labelPregunta = new Label("¿Contiene esta imagen la secuencia de Fibonacci?");
        vbox.getChildren().add(labelPregunta);

        // Imagen
        currentIndex = 1;
        imageView = new ImageView(new Image(getImagePath()));
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        vbox.getChildren().add(imageView);

        // ToggleGroup para los radio buttons
        toggleGroup = new ToggleGroup();

        // RadioButtons
        RadioButton radioButtonSi = new RadioButton("Sí");
        radioButtonSi.setToggleGroup(toggleGroup);

        RadioButton radioButtonNo = new RadioButton("No");
        radioButtonNo.setToggleGroup(toggleGroup);

        // HBox para los radio buttons
        HBox hboxRadioButtons = new HBox(10, radioButtonSi, radioButtonNo);
        hboxRadioButtons.setAlignment(Pos.CENTER);
        vbox.getChildren().add(hboxRadioButtons);

        HBox botones = new HBox(10);
        botones.setAlignment(Pos.CENTER);
        buttonSiguiente = new Button("Siguiente");
        buttonSiguiente.setOnAction(e -> siguientePregunta());
        Button salirButton = new Button("Salir");
        salirButton.setOnAction(event -> {
            volverAlMenu();
        });
        botones.getChildren().addAll(buttonSiguiente,salirButton);
        vbox.getChildren().add(botones);

        // Mostrar la primera pregunta
        mostrarPregunta();

        Scene scene = new Scene(vbox, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void mostrarPregunta() {
        labelPregunta.setText("¿Contiene esta imagen la secuencia de Fibonacci?");
        imageView.setImage(new Image(getImagePath()));
        toggleGroup.selectToggle(null); // Desseleccionar los radio buttons
    }

    private String getImagePath() {
        return "com/sebaescu/losconejosdefibonacci/Juego" + currentIndex + ".png";
    }

    private void siguientePregunta() {
        if (toggleGroup.getSelectedToggle() == null)
            return;
        else{
            // Verificar la respuesta y avanzar a la siguiente pregunta
            String respuestaCorrecta = getRespuestaCorrecta();
            System.out.println(respuestaCorrecta);
            RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
            String respuestaSeleccionada = selectedRadioButton.getText();

            if (respuestaCorrecta.equals(respuestaSeleccionada)) {
                System.out.println("¡Respuesta Correcta!");
                nCorrectas++;
            } else {
                System.out.println("Respuesta Incorrecta");
                
            }
            if(currentIndex == 10){
                labelPregunta.setText("Tuviste "+nCorrectas+" preguntas correctas!");
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
            case "file:/C:/Users/Sebastian/Documents/NetBeansProjects/LosConejosDeFibonacci/target/classes/com/sebaescu/losconejosdefibonacci/Juego1.png":
            case "file:/C:/Users/Sebastian/Documents/NetBeansProjects/LosConejosDeFibonacci/target/classes/com/sebaescu/losconejosdefibonacci/Juego2.png":
            case "file:/C:/Users/Sebastian/Documents/NetBeansProjects/LosConejosDeFibonacci/target/classes/com/sebaescu/losconejosdefibonacci/Juego3.png":
            case "file:/C:/Users/Sebastian/Documents/NetBeansProjects/LosConejosDeFibonacci/target/classes/com/sebaescu/losconejosdefibonacci/Juego4.png":
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
