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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Создает окно ввода данных для генерации, описывает методы для передачи информации в основное меню
 * для дальнейшего использования в методах генерации матриц, описывает методы для сброса даныыъ и ввода
 * дополнительной информации по разным типам генерации матриц
 *
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
    Label exep = new Label();
    boolean exit;

    @FXML
    public void Del() {
        Param.getChildren().remove(exep);
    }


    @FXML
    public void AddVar() {
        if (Zapol.getValue() == "Детерминированное распределение") {
            MatP.clear();
            MatT.clear();
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
            MatP.clear();
            MatT.clear();
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
            MatP.clear();
            MatT.clear();
            Param.getChildren().remove(MatP);
            Param.getChildren().remove(P);
            Param.getChildren().remove(exp);
            Param.getChildren().remove(MatT);

        }
        if (Zapol.getValue() == "Равномерное распределение") {
            MatP.clear();
            MatT.clear();
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
        MatN.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Del();
            }
        });
        MatT.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Del();
            }
        });
        MatP.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Del();
            }
        });
        Zapol.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Del();
            }
        });
        OK.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    exit = true;
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
                    if (Nint <= 0 || Tint <= 0 || Pint < 0 || Pint > 1) {
                        throw new Exception();
                    }
                } catch (NumberFormatException ex) {
                    Param.getChildren().remove(exep);
                    exep.setText("Неверный формат чисел, введите числа формата для int N, для float х.х");
                    exep.setMaxWidth(370);
                    exep.setWrapText(true);
                    exep.setTextFill(Color.RED);
                    exep.setLayoutX(40.0);
                    exep.setLayoutY(225.0);
                    Param.getChildren().add(exep);
                    exit = false;
                } catch (Exception ex) {
                    Param.getChildren().remove(exep);
                    exep.setText("Неверные размерности: для размеров и количества экспериментов > 0, вероятности > 0 и < 1");
                    exep.setMaxWidth(370);
                    exep.setWrapText(true);
                    exep.setTextFill(Color.RED);
                    exep.setLayoutX(40.0);
                    exep.setLayoutY(225.0);
                    Param.getChildren().add(exep);
                    exit = false;
                }
                if (exit == true) {
                    Stage stage = (Stage) OK.getScene().getWindow();
                    stage.close();
                }
            }
        });
        sbros.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Param.getChildren().remove(MatP);
                Param.getChildren().remove(P);
                Param.getChildren().remove(exp);
                Param.getChildren().remove(MatT);
                Param.getChildren().remove(exep);
                MatN.clear();
                Zapol.setValue(clr);
            }
        });
    }

}