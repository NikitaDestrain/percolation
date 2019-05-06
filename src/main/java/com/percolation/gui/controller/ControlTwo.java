package com.percolation.gui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControlTwo {

    int Mint = 0;
    int Nint = 0;

    @FXML
    private Button OK;
    public TextField MatM;
    public TextField MatN;
    public ComboBox Zapol;


    @FXML
    public void initialize(){
        ObservableList< String> var =
                FXCollections.observableArrayList(
                        "Вертикальный", "Горзонтальный", "Дождь", "Кольца", "Шахматный", "Случайный"
                );
        Zapol.setItems(var);
                OK.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Mint = Integer.parseInt(MatM.getText());
                Nint = Integer.parseInt(MatN.getText());
                Controller.getInstance().setX(Mint);
                Controller.getInstance().setY(Nint);
                Stage stage = (Stage) OK.getScene().getWindow();
                stage.close();
            }
        });
    }

}