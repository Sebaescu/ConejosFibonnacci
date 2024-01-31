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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JuegoEducativo extends Application {

    private VBox vbox;
    private Label preguntaLabel,respuestaLabel;
    private HBox imagenesHBox;
    private HBox respuestaHBox;
    private TextField respuestaTextField;
    private Button verificarButton;
    private int currentIndex =1,pCorrectas = 0;

    private String[] preguntas = {
            "¿Cuántos conejos hay en la imagen?",
            "¿En que mes se encuentra la secuencia de Fibonacci?",
            "¿Cuántos conejos se reproducirán el próximo mes?",
            "¿Cuántos conejos habrá en total después de dos meses?"
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
        primaryStage.setTitle("Juego Educativo");

        // Configuración de la interfaz de usuario
        vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);

        // Label para la pregunta
        preguntaLabel = new Label(preguntas[0]);
        respuestaLabel = new Label("Ingrese su respuesta: ");
        // HBox para las imágenes de los conejos
        imagenesHBox = new HBox(20);
        imagenesHBox.setAlignment(Pos.CENTER);
        cargarImagenesConejos(5);

        // HBox para la respuesta
        respuestaHBox = new HBox(10);
        respuestaHBox.setAlignment(Pos.CENTER);

        // TextField para la respuesta
        respuestaTextField = new TextField();
        respuestaTextField.setPromptText("Ingrese su respuesta");

        // Botón para verificar la respuesta
        verificarButton = new Button("Verificar");
        verificarButton.setOnAction(e -> verificarRespuesta());
        Button salirButton = new Button("Salir");
        salirButton.setOnAction(e -> volverAlMenu());
        // Agregar elementos al VBox
        respuestaHBox.getChildren().addAll(respuestaLabel,respuestaTextField, verificarButton,salirButton);
        vbox.getChildren().addAll(preguntaLabel, imagenesHBox, respuestaHBox);

        // Configuración de la escena y visualización
        Scene scene = new Scene(vbox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void cargarImagenesConejos(int n) {
        imagenesHBox.getChildren().clear(); // Limpiar imágenes existentes

        for (int i = 1; i <= n; i++) {
            Random random = new Random();
            String rutaAleatoria = rabbitImages.get(random.nextInt(rabbitImages.size()));
            ImageView nuevoConejo = new ImageView(new Image(rutaAleatoria));
            nuevoConejo.setFitWidth(150);
            nuevoConejo.setFitHeight(150);
            imagenesHBox.getChildren().add(nuevoConejo);
        }
                  
    }

    private void verificarRespuesta() {
        int respuestaIngresada = Integer.parseInt(respuestaTextField.getText());
        switch(currentIndex){
            case 1:
                if(respuestaIngresada == 5)
                    pCorrectas++;
                break;
            case 2:
                if(respuestaIngresada == 3)
                    pCorrectas++;
                break;
            case 3:
                if(respuestaIngresada == 8)
                    pCorrectas++;
                break;
            case 4:
                if(respuestaIngresada == 21)
                    pCorrectas++;
                break;
        }
        if(currentIndex == 4){
            preguntaLabel.setText("Tuviste "+pCorrectas+" respuestas correctas!");
            verificarButton.setDisable(true);
            respuestaTextField.setDisable(true);
        }else{
            currentIndex++;
            switch(currentIndex){
                case 2:
                    preguntaLabel.setText(preguntas[currentIndex-1]);
                    cargarImagenesConejos(3);
                    break;
                case 3:
                    preguntaLabel.setText(preguntas[currentIndex-1]);
                    cargarImagenesConejos(5);
                    break;
                case 4:
                    preguntaLabel.setText(preguntas[currentIndex-1]);
                    cargarImagenesConejos(8);
                    break;
            }
            
            respuestaTextField.clear();
        }
    }
    private void volverAlMenu() {
        Platform.runLater(() -> {
            // Crea una nueva instancia del MainMenu y muestra la ventana maximizada
            MenuPrincipal menuPrincipal = new MenuPrincipal();
            Stage primaryStageMenu = new Stage();
            menuPrincipal.start(primaryStageMenu);
            primaryStageMenu.setMaximized(true);
            Stage stageActual = (Stage) verificarButton.getScene().getWindow();
            stageActual.close();
        });
    }
}
