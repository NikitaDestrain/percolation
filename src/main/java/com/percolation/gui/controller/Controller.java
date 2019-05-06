package com.percolation.gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class Controller {

    private static Controller instance;

    public Controller() {
        instance = this;
    }

    public static Controller getInstance() {
        if (instance == null) instance = new Controller();
        return instance;
    }

    int x = 15;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    int y = 15;

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @FXML
    public Button play;
    public Button pause;
    public Button ishod;
    public Button print;
    public Button data;
    public AnchorPane primaryStage;


    @FXML
    public void openNewStage() throws IOException {

        Stage scenetwo = new Stage();
        Parent two = FXMLLoader.load(getClass().getResource("two.fxml"));
        scenetwo.setTitle("Matrix Parametrs");
        scenetwo.setScene(new Scene(two, scenetwo.getWidth(), scenetwo.getHeight()));
        scenetwo.initModality(Modality.APPLICATION_MODAL);
        scenetwo.show();
    }

    @FXML
    public void printMatrix() throws IOException {

        int length = getX();
        int width = getY();

        GridPane root = new GridPane();

        for (int y = 0; y < length; y++) {
            for (int x = 0; x < width; x++) {

                Random rand = new Random();
                int rand1 = rand.nextInt(2);

                // Create a new TextField in each Iteration
                TextField tf = new TextField();
                tf.setPrefHeight(40);
                tf.setPrefWidth(40);
                tf.setAlignment(Pos.CENTER);
                tf.setEditable(false);
                tf.setText("(" + rand1 + ")");

                // Iterate the Index using the loops
                root.setRowIndex(tf, y);
                root.setColumnIndex(tf, x);
                root.getChildren().add(tf);
            }
        }

        Stage stage = new Stage();
        Scene scene = new Scene(root, 900, 900);
        stage.setTitle("Matrix");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void initialize() {
        Image Play = new Image(getClass().getResource("../images/play.png").toExternalForm());
        Image Pause = new Image(getClass().getResource("../images/pause.png").toExternalForm());
        Image Ishod = new Image(getClass().getResource("../images/ishod.png").toExternalForm());
        Image Print = new Image(getClass().getResource("../images/print.png").toExternalForm());
        Image Data = new Image(getClass().getResource("../images/data.png").toExternalForm());
        play.graphicProperty().setValue(new ImageView(Play));
        play.setContentDisplay(ContentDisplay.TOP);
        pause.graphicProperty().setValue(new ImageView(Pause));
        pause.setContentDisplay(ContentDisplay.TOP);
        ishod.graphicProperty().setValue(new ImageView(Ishod));
        ishod.setContentDisplay(ContentDisplay.TOP);
        print.graphicProperty().setValue(new ImageView(Print));
        print.setContentDisplay(ContentDisplay.TOP);
        data.graphicProperty().setValue(new ImageView(Data));
        data.setContentDisplay(ContentDisplay.TOP);
    }

}