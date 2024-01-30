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

        // VBox
        vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);

        // Label para mostrar dato curioso
        labelDatoCurioso = new Label(datosCuriosos[currentIndex]);
        
        // HBox para la imagen y botones de navegación
        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER);

        // Botón retroceder
        Button buttonRetroceder = new Button("Retroceder");
        buttonRetroceder.setOnAction(e -> retroceder());

        // Imagen
        imageView = new ImageView(new Image(imagenesFibo[currentIndex]));
        imageView.setFitWidth(200); // Ajusta el ancho de la imagen según tus necesidades

        // Botón avanzar
        buttonAvanzar = new Button("Avanzar");
        buttonAvanzar.setOnAction(e -> avanzar());
        
        HBox botones = new HBox(10);
        botones.setAlignment(Pos.CENTER);
        // Botón jugar
        Button buttonJugar = new Button("Jugar");
        buttonJugar.setOnAction(event -> {
            primaryStage.hide();
            JuegoCurioso game = new JuegoCurioso();
            game.start(primaryStage);
        });
        Button salirButton = new Button("Salir");
        salirButton.setOnAction(event -> {
            volverAlMenu();
        });

        // Agregar elementos al VBox y HBox
        hbox.getChildren().addAll(buttonRetroceder, imageView, buttonAvanzar);
        botones.getChildren().addAll(buttonJugar,salirButton);
        vbox.getChildren().addAll(labelDatoCurioso, hbox, botones);

        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void avanzar() {
        if(currentIndex == 4){
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
        if(currentIndex == 0){
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
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
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

