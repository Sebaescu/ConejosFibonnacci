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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ModoCurioso extends Application {

    private String[] datosCuriosos = {
            "La secuencia de Fibonacci se encuentra en la naturaleza, desde la disposición de las hojas hasta el crecimiento de frutas, "
                    + "creando patrones matemáticamente encantadores.",
            "Algunas flores muestran espirales de Fibonacci en la forma en que crecen.",
            "Los huracanes exhiben patrones de espirales de Fibonacci en su desarrollo, proporcionando no solo belleza matemática,"
                    + " sino también información valiosa para comprender su dinámica y estructura.",
            "En el cuerpo humano, la disposición de las hojas en el tallo, ramas en los árboles bronquiales de los pulmones y "
                    + "la forma de las orejas pueden seguir patrones de Fibonacci.",
            "Si cuentas las estrellas en el cielo de noche, algunas veces verás patrones que siguen la secuencia de Fibonacci. "
                    + "¡Es como si las estrellas estuvieran jugando un juego matemático con nosotros!"
    };
    private String[] imagenesFibo = {
            "com/sebaescu/losconejosdefibonacci/Fibonacci1.png",
            "com/sebaescu/losconejosdefibonacci/Fibonacci2.png",
            "com/sebaescu/losconejosdefibonacci/Fibonacci3.png",
            "com/sebaescu/losconejosdefibonacci/Fibonacci4.png",
            "com/sebaescu/losconejosdefibonacci/Fibonacci5.png"
    };
    private int currentIndex = 0;
    private VBox vbox;
    private Label labelDatoCurioso;
    private ImageView imageView;
    private Button buttonAvanzar;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Modo Curioso");

        // Configuración de la interfaz de usuario
        StackPane root = new StackPane();
        root.setStyle("-fx-background-image: url('com/sebaescu/losconejosdefibonacci/fondo4.png'); "
                + "-fx-background-size: cover;");

        vbox = new VBox(10);
        vbox.setAlignment(Pos.BOTTOM_CENTER);

        // Label para mostrar dato curioso
        labelDatoCurioso = new Label(datosCuriosos[currentIndex]);
        labelDatoCurioso.setMaxWidth(1300);
        labelDatoCurioso.setWrapText(true);
        labelDatoCurioso.setStyle("-fx-font-size: 36; -fx-text-fill: #f5ebb0;");

        // HBox para la imagen y botones de navegación
        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER);

        // Botón retroceder
        Button buttonRetroceder = new Button("Retroceder");
        buttonRetroceder.setOnAction(e -> retroceder());
        buttonRetroceder.setStyle("-fx-min-height: 60; -fx-background-color: #dad061; -fx-font-size: 20; -fx-text-fill: #2d6073; -fx-background-radius: 20;");

        // Imagen
        imageView = new ImageView(new Image(imagenesFibo[currentIndex]));
        imageView.setFitHeight(350);
        imageView.setFitWidth(350); // Ajusta el ancho de la imagen según tus necesidades

        // Botón avanzar
        buttonAvanzar = new Button("Avanzar");
        buttonAvanzar.setOnAction(e -> avanzar());
        buttonAvanzar.setStyle("-fx-min-height: 60; -fx-background-color: #dad061; -fx-font-size: 20; -fx-text-fill: #2d6073; -fx-background-radius: 20;");
        
        HBox botones = new HBox(10);
        botones.setAlignment(Pos.CENTER);

        // Botón jugar
        Button buttonJugar = new Button("Jugar");
        buttonJugar.setOnAction(event -> {
            primaryStage.hide();
            JuegoCurioso game = new JuegoCurioso();
            game.start(primaryStage);
        });
        buttonJugar.setStyle("-fx-min-height: 60; -fx-background-color: #acc59d; -fx-font-size: 20; -fx-text-fill: #09456c; -fx-background-radius: 25;");

        Button salirButton = new Button("Salir");
        salirButton.setOnAction(event -> {
            volverAlMenu();
        });
        salirButton.setStyle("-fx-min-height: 60; -fx-background-color: #edab8b; -fx-font-size: 20; -fx-text-fill: #09456c; -fx-background-radius: 25;");

        // Agregar elementos al VBox y HBox
        hbox.getChildren().addAll(buttonRetroceder, imageView, buttonAvanzar);
        botones.getChildren().addAll(buttonJugar, salirButton);
        vbox.getChildren().addAll(labelDatoCurioso, hbox, botones);

        root.getChildren().addAll(vbox);

        // Configuración de la escena y visualización
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void avanzar() {
        if (currentIndex == 4) {
            currentIndex = 0;
            setImage();
            return;
        }
        if (currentIndex < datosCuriosos.length - 1) {
            currentIndex++;
            setImage();
        }
    }

    private void retroceder() {
        if (currentIndex == 0) {
            currentIndex = 4;
            setImage();
            return;
        }
        if (currentIndex > 0) {
            currentIndex--;
            setImage();
        }
    }

    private void setImage() {
        labelDatoCurioso.setText(datosCuriosos[currentIndex]);
        imageView.setImage(new Image(imagenesFibo[currentIndex]));
        imageView.setFitHeight(350);
        imageView.setFitWidth(350);
    }

    private void volverAlMenu() {
        Platform.runLater(() -> {
            // Crea una nueva instancia del MainMenu y muestra la ventana maximizada
            MenuPrincipal menuPrincipal = new MenuPrincipal();
            Stage primaryStageMenu = new Stage();
            menuPrincipal.start(primaryStageMenu);
            primaryStageMenu.setMaximized(true);
            Stage stageActual = (Stage) buttonAvanzar.getScene().getWindow();
            stageActual.close();
        });
    }
}


