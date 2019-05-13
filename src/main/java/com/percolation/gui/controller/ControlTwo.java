package com.percolation.gui.controller;

import com.percolation.domain.matrix.Matrix;
import com.percolation.domain.matrix.MatrixGeneratorType;
import com.percolation.service.MatrixService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author Kirill Galanov
 */
public class ControlTwo {

    float Pint = 0;
    int Nint = 50;
    int Tint = 0;


    @FXML
    public Button OK;
    public Button sbros;
    public TextField MatN;
    public ComboBox Zapol;
    public AnchorPane Param;
    TextField MatT = new TextField();
    TextField MatP = new TextField();
    Label exp = new Label();
    Label P = new Label();

    @FXML
    public void AddVar() {
        if (Zapol.getValue() == "Детерминированное распределение") {
            Param.getChildren().remove(MatP);
            Param.getChildren().remove(P);
            Param.getChildren().remove(exp);
            Param.getChildren().remove(MatT);
            exp.setText("Количество экспериментов:");
            exp.setLayoutX(25.0);
            exp.setLayoutY(137.0);
            P.setText("Максимальная вероятность P:");
            P.setLayoutX(25.0);
            P.setLayoutY(197.0);
            MatT.setLayoutX(245.0);
            MatT.setLayoutY(137.0);
            MatP.setLayoutX(245.0);
            MatP.setLayoutY(192.0);
            Param.getChildren().addAll(MatP, P, exp, MatT);
        }
        if (Zapol.getValue() == "Градиент") {
            Param.getChildren().remove(exp);
            Param.getChildren().remove(MatT);
            Param.getChildren().remove(MatP);
            Param.getChildren().remove(P);
            P.setText("Максимальная вероятность P:");
            P.setLayoutX(25.0);
            P.setLayoutY(197.0);
            exp.setText("Количество экспериментов:");
            exp.setLayoutX(25.0);
            exp.setLayoutY(137.0);
            MatP.setLayoutX(245.0);
            MatP.setLayoutY(192.0);
            MatT.setLayoutX(245.0);
            MatT.setLayoutY(137.0);
            Param.getChildren().addAll(MatP, P, MatT, exp);
        }
        if (Zapol.getValue() == "Градиент Мостового") {
            Param.getChildren().remove(MatP);
            Param.getChildren().remove(P);
            Param.getChildren().remove(exp);
            Param.getChildren().remove(MatT);

        }
        if (Zapol.getValue() == "Равномерное распределение") {
            Param.getChildren().remove(exp);
            Param.getChildren().remove(MatT);
            Param.getChildren().remove(MatP);
            Param.getChildren().remove(P);
            P.setText("Вероятность P:");
            P.setLayoutX(25.0);
            P.setLayoutY(197.0);
            MatT.setLayoutX(245.0);
            MatT.setLayoutY(137.0);
            exp.setText("Количество экспериментов:");
            exp.setLayoutX(25.0);
            exp.setLayoutY(137.0);
            MatP.setLayoutX(245.0);
            MatP.setLayoutY(192.0);
            Param.getChildren().addAll(MatP, P, exp, MatT);
        }
    }

    @FXML
    public void initialize() {
        ObservableList<String> var =
                FXCollections.observableArrayList(
                        "Детерминированное распределение", "Градиент", "Градиент Мостового", "Равномерное распределение"
                );
        String clr = "";
        Zapol.setItems(var);
        OK.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Nint = Integer.parseInt(MatN.getText());
                if (Zapol.getValue() == "Градиент Мостового") {
                    Pint = 1;
                    Tint = 1;
                } else {
                    Tint = Integer.parseInt(MatT.getText());
                    Pint = Float.parseFloat(MatP.getText());
                }
                if (Zapol.getValue() == "Детерминированное распределение")
                    Controller.getInstance().setMatr(MatrixService.getInstance().createMatrices(Tint, Nint, Pint, MatrixGeneratorType.UNIFORM));
                if (Zapol.getValue() == "Градиент")
                    Controller.getInstance().setMatr(MatrixService.getInstance().createMatrices(Tint, Nint, Pint, MatrixGeneratorType.GRADIENT));
                if (Zapol.getValue() == "Градиент Мостового")
                    Controller.getInstance().setMatr(MatrixService.getInstance().createMatrices(Tint, Nint, Pint, MatrixGeneratorType.MOSTOVOY_GRADIENT));
                if (Zapol.getValue() == "Равномерное распределение")
                    Controller.getInstance().setMatr(MatrixService.getInstance().createMatrices(Tint, Nint, Pint, MatrixGeneratorType.UNIFORM_DISTRIBUTION));
                Stage stage = (Stage) OK.getScene().getWindow();
                stage.close();
            }
        });
        sbros.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Param.getChildren().remove(MatP);
                Param.getChildren().remove(P);
                Param.getChildren().remove(exp);
                Param.getChildren().remove(MatT);
                MatN.clear();
                Zapol.setValue(clr);
            }
        });
    }

}