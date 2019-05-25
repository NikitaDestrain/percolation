package com.percolation.gui.controller;

import com.percolation.domain.matrix.Matrix;
import com.percolation.domain.matrix.MatrixGeneratorType;
import com.percolation.service.MatrixService;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

import java.awt.*;
import java.util.List;

/**
 * Создает окно выбора экспорта различных видов статистики, описывает методы вызова основных методов
 * экспорта статистики в файл, описывает методы для выбора экспорта статистики
 *
 * @author Kirill Galanov
 */
public class ControllerFile {

    List<Matrix> matr;

    @FXML
    public Button OK;
    public CheckBox BCM;
    public CheckBox SWM;
    public CheckBox CM;

    @FXML
    public void initialize() {
        OK.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                matr = Controller.getInstance().getMatr();
                if (BCM.isSelected() == true) {
                    MatrixService.getInstance().calculateMatrixBlackHoleStatistic(matr, true);
                }
                if (SWM.isSelected() == true) {
                    try {
                        MatrixService.getInstance().writeMatrixWayStatisticToFile(matr.get(0).getN(), matr);
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                }
                if (CM.isSelected() == true)
                    MatrixService.getInstance().getClusterStatistic(matr);
                Stage stage = (Stage) OK.getScene().getWindow();
                stage.close();
            }
        });
    }
}
