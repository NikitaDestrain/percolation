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
 * @author Kirill Galanov
 */
public class ControllerFile {

    List<Matrix> matr;

    @FXML
    public Button OK;
    public CheckBox CB;

    @FXML
    public void initialize() {
        OK.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (CB.isSelected() == true) {
                    matr = Controller.getInstance().getMatr();
                    MatrixService.getInstance().calculateMatrixBlackHoleStatistic(matr, true);
                }
                Stage stage = (Stage) OK.getScene().getWindow();
                stage.close();
            }
        });
    }
}
