/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sebaescu.losconejosdefibonacci;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MenuPrincipal extends Application {

    private static final double BUTTON_FONT_SIZE = 20;
    private static final double TITLE_FONT_SIZE = 50;

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();

        // Agregar una imagen de fondo
        Image backgroundImage = new Image("com/sebaescu/losconejosdefibonacci/fondo.png");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        root.getChildren().add(backgroundImageView);

        Label title = new Label("Los Conejos de Fibonacci");
        title.setStyle("-fx-font-size: " + TITLE_FONT_SIZE + "; -fx-font-weight: bold; -fx-text-fill: white;");
        // Inicializar el reproductor de sonido
        //reproductorDeSonido = new ReproductorDeSonido();
        //reproductorDeSonido.reproducirCancion(1);

        Button facilButton = createStyledButton("Que es la sucesion de Fibonacci?", "#8FDB8F");
        Button dificilButton = createStyledButton("Como funciona la sucesion de Fibonacci?", "#FF6961");
        Button ilimitadoBtn = createStyledButton("Juego Fibonacci", "#f2c94e");
        Button closeButton = createStyledButton("Cerrar", "#696969");

        VBox buttonsBox = new VBox(20, facilButton, dificilButton,ilimitadoBtn, closeButton);
        buttonsBox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(20, title, buttonsBox);
        vbox.setAlignment(Pos.CENTER);

        // Ajustar el tamaño del VBox para que ocupe todo el espacio disponible
        VBox.setVgrow(buttonsBox, Priority.ALWAYS);

        StackPane.setAlignment(vbox, Pos.CENTER);
        StackPane.setMargin(vbox, new javafx.geometry.Insets(50, 0, 0, 0));

        root.getChildren().addAll(vbox);

        // Escuchar cambios en el tamaño de la ventana
        primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            backgroundImageView.setFitWidth(newVal.doubleValue());
            updateFontSize(primaryStage, title, facilButton, dificilButton,ilimitadoBtn,closeButton);
        });

        primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> {
            backgroundImageView.setFitHeight(newVal.doubleValue());
            updateFontSize(primaryStage, title, facilButton, dificilButton,ilimitadoBtn ,closeButton);
        });

        primaryStage.maximizedProperty().addListener((obs, oldVal, newVal) -> {
            updateFontSize(primaryStage, title, facilButton, dificilButton, ilimitadoBtn,closeButton);
        });

        facilButton.setOnAction(event -> {
            primaryStage.hide();
            ModoCurioso game = new ModoCurioso();
            game.start(primaryStage);
        });
        ilimitadoBtn.setOnAction(event -> {
            primaryStage.hide();
            App game = new App();
            game.startJuego(primaryStage);
        });
        dificilButton.setOnAction(event -> {
            primaryStage.hide();
            App game = new App();
        });
        closeButton.setOnAction(event -> primaryStage.close());

        // Establecer el tamaño máximo de la ventana al maximizado
        primaryStage.setMaximized(true);

        primaryStage.setTitle("ExploCaves");
        // Usar dimensiones iniciales de la ventana
        Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createStyledButton(String text, String backgroundColor) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: " + backgroundColor + "; -fx-text-fill: white;");
        button.setMinSize(200, 50);
        setButtonFontSize(button, BUTTON_FONT_SIZE);
        return button;
    }

    private void setButtonFontSize(Button button, double fontSize) {
        button.setFont(new Font("Arial", fontSize));
    }

    private void updateFontSize(Stage stage, Label title, Button facilButton, Button dificilButton,Button ensenarButton, Button closeButton) {
        double scaleFactor = stage.isMaximized() ? 1.5 : 1.0;

        title.setStyle("-fx-font-size: " + (TITLE_FONT_SIZE * scaleFactor) +
                "; -fx-font-weight: bold; -fx-text-fill: white;");

        setButtonFontSize(facilButton, BUTTON_FONT_SIZE * scaleFactor);
        setButtonFontSize(dificilButton, BUTTON_FONT_SIZE * scaleFactor);
        setButtonFontSize(closeButton, BUTTON_FONT_SIZE * scaleFactor);
        setButtonFontSize(ensenarButton, BUTTON_FONT_SIZE * scaleFactor);
    }

    public static void main(String[] args) {
        launch(args);
    }
}


