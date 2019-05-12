package com.percolation.gui.controller;

import com.percolation.domain.Way;
import com.percolation.domain.matrix.Cell;
import com.percolation.domain.matrix.Matrix;
import com.percolation.domain.matrix.MatrixGeneratorType;
import com.percolation.generator.UniformDistributionMatrixGenerator;
import com.percolation.service.MatrixService;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Kirill Galanov
 */
public class Controller {

    private static Controller instance;

    public Controller() {
        instance = this;
    }

    public static Controller getInstance() {
        if (instance == null) instance = new Controller();
        return instance;
    }

    public List getMatr() {
        return Matr;
    }

    public void setMatr(List matr) {
        Matr = matr;
    }

    List<Matrix> Matr = null;
    List<Matrix> UniM = null;
    List<Matrix> GradM = null;
    List<Matrix> MGradM = null;
    List<Matrix> UniDM = null;
    List<Matrix> TestM = null;
    double LY = 408.0;
    Button UniB = new Button();
    Button GradB = new Button();
    Button MGradB = new Button();
    Button UniDB = new Button();
    ImageView imageView = new ImageView();
    WritableImage image;
    PixelWriter pixelWriter;
    int x1, y1;
    Way put = null;


    @FXML
    public Button play;
    public Button stop;
    public Button data;
    public Button chess;
    public Button circles;
    public Button krest;
    public Button horpol;
    public Button verpol;
    public Button rain;
    public Button htest;
    public ScrollPane scroll;
    public AnchorPane primaryStage;
    public StackPane imgcon;
    public ScrollPane scrimg;
    public Label perc;
    public Label size;
    public Label blkpnt;
    public Label Pvalue;
    public Button way;


    @FXML
    public void GenerateRun() throws IOException {
        Stage scenetwo = new Stage();
        Parent two = FXMLLoader.load(getClass().getResource("/fxml/two.fxml"));
        scenetwo.setTitle("Параметры генератора");
        scenetwo.setScene(new Scene(two, scenetwo.getWidth(), scenetwo.getHeight()));
        scenetwo.initModality(Modality.APPLICATION_MODAL);
        scenetwo.show();
        scenetwo.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent we) {
                if (Matr.get(0).getGeneratorType() == MatrixGeneratorType.UNIFORM) {
                    if (UniM == null) {
                        UniB.setText("Детерминированное распределение");
                        UniB.setLayoutY(LY);
                        LY = LY + 52.0;
                        UniB.setPrefWidth(190.0);
                        UniB.setWrapText(true);
                        primaryStage.getChildren().addAll(UniB);
                    }
                    UniM = Matr;
                    System.out.println(UniM.size());
                }
                if (Matr.get(0).getGeneratorType() == MatrixGeneratorType.GRADIENT) {
                    if (GradM == null) {
                        GradB.setText("Градиент");
                        GradB.setLayoutY(LY);
                        LY = LY + 31.0;
                        GradB.setPrefWidth(190.0);
                        GradB.setWrapText(true);
                        primaryStage.getChildren().addAll(GradB);
                    }
                    GradM = Matr;
                }
                if (Matr.get(0).getGeneratorType() == MatrixGeneratorType.MOSTOVOY_GRADIENT) {
                    if (MGradM == null) {
                        MGradB.setText("Градиент Мостового");
                        MGradB.setLayoutY(LY);
                        LY = LY + 31.0;
                        MGradB.setPrefWidth(190.0);
                        MGradB.setWrapText(true);
                        primaryStage.getChildren().addAll(MGradB);
                    }
                    MGradM = Matr;
                }
                if (Matr.get(0).getGeneratorType() == MatrixGeneratorType.UNIFORM_DISTRIBUTION) {
                    if (UniDM == null) {
                        UniDB.setText("Равномерное распределение");
                        UniDB.setLayoutY(LY);
                        LY = LY + 52.0;
                        UniDB.setPrefWidth(190.0);
                        UniDB.setWrapText(true);
                        primaryStage.getChildren().addAll(UniDB);
                    }
                    UniDM = Matr;
                }
                if (UniM != null || GradM != null || MGradM != null || UniDM != null)
                    data.setDisable(false);
            }
        });
    }

    private Image createColorScaleImage(Matrix matr, int width, int height, int mnozh) {
        image = new WritableImage(width * mnozh, height * mnozh);
        pixelWriter = image.getPixelWriter();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                x1 = x * mnozh;
                y1 = y * mnozh;
                for (int i = 0; i < mnozh; i++) {
                    for (int j = 0; j < mnozh; j++) {
                        if (matr.getCellValue(x, y).getHumanReadableValue() == 1) {
                            double value = matr.getCellValue(x, y).getClusterId();
                            pixelWriter.setColor(x1 + i, y1 + j, Color.hsb(value * 30, 1, 1));
                        } else pixelWriter.setColor(x1 + i, y1 + j, Color.WHITE);
                        if (i == 0 || j == 0 || i == mnozh - 1 || j == mnozh - 1)
                            pixelWriter.setColor(x1 + i, y1 + j, Color.BLACK);
                    }
                }
            }
        }
        return image;
    }


    @FXML
    public void ImportFile() throws IOException {
        Matr = new ArrayList<Matrix>();
        if (UniM != null)
            Matr.addAll(UniM);
        if (GradM != null)
            Matr.addAll(GradM);
        if (MGradM != null)
            Matr.addAll(MGradM);
        if (UniDM != null)
            Matr.addAll(UniDM);
        Stage scenefile = new Stage();
        Parent file = FXMLLoader.load(getClass().getResource("/fxml/file.fxml"));
        scenefile.setTitle("Экспорт в файл");
        scenefile.setScene(new Scene(file, scenefile.getWidth(), scenefile.getHeight()));
        scenefile.initModality(Modality.APPLICATION_MODAL);
        scenefile.show();
    }

    ;

    @FXML
    public void printMatrix(Matrix matr) {
        perc.setVisible(true);
        size.setVisible(true);
        blkpnt.setVisible(true);
        Pvalue.setVisible(true);
        scrimg.setVisible(true);
        imgcon.setVisible(true);
        if (matr.isContainPercolation() == true)
            perc.setText("Перколяция есть");
        else perc.setText("Перколяции нет");
        size.setText("Размер: " + Integer.toString(matr.getN()) + "х" + Integer.toString(matr.getN()));
        blkpnt.setText("Количество 'черных' точек: " + Integer.toString(matr.getBlackCellCount()));
        Pvalue.setText("Вероятность: " + Double.toString(matr.getP()));
        primaryStage.getChildren().remove(imgcon);
        imgcon.getChildren().remove(imageView);
        Image colorScale = createColorScaleImage(matr, matr.getN(), matr.getN(), 11);
        imageView.setImage(colorScale);
        imgcon.getChildren().addAll(imageView);
    }

    @FXML
    public void printWay(Matrix matr, int mnozh) throws CloneNotSupportedException {
        put = MatrixService.getInstance().getShortestWayMatrix(matr);
        System.out.println(put.getLengthWay());
        int x, y;
        for (Cell cell : put.getWayArray()) {
            x = cell.getX();
            y = cell.getY();
            x1 = x * mnozh;
            y1 = y * mnozh;
            pixelWriter = image.getPixelWriter();
            for (int i = 0; i < mnozh; i++) {
                for (int j = 0; j < mnozh; j++) {
                    if (matr.getCellValue(x, y).getHumanReadableValue() == 1) {
                        double value = matr.getCellValue(x, y).getClusterId();
                        pixelWriter.setColor(x1 + i, y1 + j, Color.PINK);
                    } else pixelWriter.setColor(x1 + i, y1 + j, Color.RED);
                    if (i == 0 || j == 0 || i == mnozh - 1 || j == mnozh - 1)
                        pixelWriter.setColor(x1 + i, y1 + j, Color.BLACK);
                }
            }
        }
    }

    @FXML
    public void initialize() {
        Image Play = new Image(getClass().getResource("/images/play.png").toExternalForm());
        Image Stop = new Image(getClass().getResource("/images/stop.png").toExternalForm());
        Image Data = new Image(getClass().getResource("/images/data.png").toExternalForm());
        play.graphicProperty().setValue(new ImageView(Play));
        stop.graphicProperty().setValue(new ImageView(Stop));
        data.graphicProperty().setValue(new ImageView(Data));
        TestM = MatrixService.getInstance().createTestMatrices();
        scrimg.setVisible(false);
        scroll.setVisible(false);
        imgcon.setVisible(false);
        perc.setVisible(false);
        size.setVisible(false);
        blkpnt.setVisible(false);
        Pvalue.setVisible(false);
        way.setVisible(false);
        data.setDisable(true);
        chess.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                way.setVisible(true);
                scroll.setVisible(false);
                printMatrix(TestM.get(0));
                way.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        try {
                            printWay(TestM.get(0), 11);
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        circles.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                way.setVisible(true);
                scroll.setVisible(false);
                printMatrix(TestM.get(1));
                way.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        try {
                            printWay(TestM.get(1), 11);
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        krest.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                way.setVisible(true);
                scroll.setVisible(false);
                printMatrix(TestM.get(2));
                way.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        try {
                            printWay(TestM.get(2), 11);
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        horpol.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                way.setVisible(true);
                scroll.setVisible(false);
                printMatrix(TestM.get(3));
                way.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        try {
                            printWay(TestM.get(3), 11);
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        verpol.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                way.setVisible(true);
                scroll.setVisible(false);
                printMatrix(TestM.get(4));
                way.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        try {
                            printWay(TestM.get(4), 11);
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        rain.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                way.setVisible(true);
                scroll.setVisible(false);
                printMatrix(TestM.get(5));
                way.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        try {
                            printWay(TestM.get(5), 11);
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        htest.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                way.setVisible(true);
                scroll.setVisible(false);
                printMatrix(TestM.get(6));
                way.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        try {
                            printWay(TestM.get(6), 11);
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        UniB.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                scroll.setVisible(true);
                GridPane root1 = new GridPane();
                Button[] matrix = new Button[UniM.size()];
                for (int i = 0; i < UniM.size(); i++) {
                    matrix[i] = new Button();
                    matrix[i].setText("Матрица " + (i + 1));
                    matrix[i].setPrefWidth(185);
                    root1.setRowIndex(matrix[i], i);
                    root1.setColumnIndex(matrix[i], 0);
                    root1.getChildren().add(matrix[i]);
                    int finalI = i;
                    matrix[i].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            way.setVisible(true);
                            printMatrix(UniM.get(finalI));
                            way.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    try {
                                        printWay(UniM.get(finalI), 11);
                                    } catch (CloneNotSupportedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    });
                }
                scroll.setContent(root1);
            }
        });
        GradB.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                scroll.setVisible(true);
                GridPane root1 = new GridPane();
                Button[] matrix = new Button[GradM.size()];
                for (int i = 0; i < GradM.size(); i++) {
                    matrix[i] = new Button(/*"Matrix" +  i*/);
                    matrix[i].setText("Матрица " + (i + 1));
                    matrix[i].setPrefWidth(185);
                    root1.setRowIndex(matrix[i], i);
                    root1.setColumnIndex(matrix[i], 0);
                    root1.getChildren().add(matrix[i]);
                    int finalI = i;
                    matrix[i].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            way.setVisible(true);
                            printMatrix(GradM.get(finalI));
                            way.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    try {
                                        printWay(GradM.get(finalI), 11);
                                    } catch (CloneNotSupportedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    });
                }
                scroll.setContent(root1);
            }
        });
        MGradB.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                scroll.setVisible(true);
                GridPane root1 = new GridPane();
                Button[] matrix = new Button[MGradM.size()];
                for (int i = 0; i < MGradM.size(); i++) {
                    matrix[i] = new Button(/*"Matrix" +  i*/);
                    matrix[i].setText("Матрица " + (i + 1));
                    matrix[i].setPrefWidth(185);
                    root1.setRowIndex(matrix[i], i);
                    root1.setColumnIndex(matrix[i], 0);
                    root1.getChildren().add(matrix[i]);
                    int finalI = i;
                    matrix[i].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            way.setVisible(true);
                            printMatrix(MGradM.get(finalI));
                            way.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    try {
                                        printWay(MGradM.get(finalI), 11);
                                    } catch (CloneNotSupportedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    });
                }
                scroll.setContent(root1);
            }
        });
        UniDB.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                scroll.setVisible(true);
                GridPane root1 = new GridPane();
                Button[] matrix = new Button[UniDM.size()];
                for (int i = 0; i < UniDM.size(); i++) {
                    matrix[i] = new Button(/*"Matrix" +  i*/);
                    matrix[i].setText("Матрица " + (i + 1));
                    matrix[i].setPrefWidth(185);
                    root1.setRowIndex(matrix[i], i);
                    root1.setColumnIndex(matrix[i], 0);
                    root1.getChildren().add(matrix[i]);
                    int finalI = i;
                    matrix[i].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            printMatrix(UniDM.get(finalI));
                            way.setVisible(true);
                            way.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    try {
                                        printWay(UniDM.get(finalI), 11);
                                    } catch (CloneNotSupportedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    });
                }
                scroll.setContent(root1);
            }
        });
        stop.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Matr = null;
                UniM = null;
                GradM = null;
                MGradM = null;
                UniDM = null;
                scrimg.setVisible(false);
                scroll.setVisible(false);
                imgcon.setVisible(false);
                perc.setVisible(false);
                size.setVisible(false);
                blkpnt.setVisible(false);
                Pvalue.setVisible(false);
                LY = 408.0;
                primaryStage.getChildren().remove(UniB);
                primaryStage.getChildren().remove(GradB);
                primaryStage.getChildren().remove(MGradB);
                primaryStage.getChildren().remove(UniDB);
                data.setDisable(true);
                way.setVisible(false);
            }
        });
    }

}