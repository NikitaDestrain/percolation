package com.percolation.io;

import com.percolation.detection.ClusterDetection;
import com.percolation.domain.matrix.Matrix;
import com.percolation.domain.matrix.MatrixGeneratorType;
import com.percolation.domain.statistic.BlackHoleLightningStatistic;
import com.percolation.domain.statistic.ClusterStatistic;
import com.percolation.domain.statistic.MatrixBlackHoleStatistic;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IOUtils {

    // statistic constants
    private static final String CSV_MATRIX_STATISTICS_HEADER = "id,p,percolation,blackCount,concentrationRate,averageBlackCellInRow,averageBlackCellInColumn,zeroRowCount,zeroColumnCount";
    private static final String COMMA_DELIMITER = ",";
    private static final String LINE_SEPARATOR = "\n";
    private static final String OUT_FOLDER = "data/";
    private static final String CLUSTER_STATISTIC = "cluster_statistic";
    private static final String BLACK_HOLE_LIGHTNING_STATISTIC = "black_hole_lightning_statistic";
    private static final String WAY_LIGHTNING_STATISTIC = "way_lightning_statistic";
    private static final String CSV_CLUSTER_STATISTIC_HEADER = "averageAmountClusters,averageSizeCluster,averageDistanceBetweenClusters";
    private static final String CSV_BLACK_HOLE_LIGHTNING_STATISTIC_HEADER = "averageAmountBlackHole,concentrationBlackHole";
    private static final String CSV_WAY_LIGHTNING_STATISTIC_HEADER = ",";

    // test constants
    public static final String TEST_RESOURCES_FOLDER = "test-resources/";
    public static final String TEST_CHESS_FILE = "Chess.txt";
    public static final String TEST_CIRCLES_FILE = "Circles.txt";
    public static final String TEST_HARD_FILE = "HardTest.txt";
    public static final String TEST_HORIZONTAL_FILE = "Horizontal.txt";
    public static final String TEST_VERTICAL_FILE = "Vertical.txt";
    public static final String TEST_RAIN_FILE = "Rain.txt";
    public static final String TEST_H_0_FILE = "HTest0.txt";

    private List<String> testPool;

    private static IOUtils instance;

    private IOUtils() {
        this.testPool = new ArrayList<>();
        this.testPool.add(TEST_CHESS_FILE);
        this.testPool.add(TEST_CIRCLES_FILE);
        this.testPool.add(TEST_HARD_FILE);
        this.testPool.add(TEST_HORIZONTAL_FILE);
        this.testPool.add(TEST_VERTICAL_FILE);
        this.testPool.add(TEST_RAIN_FILE);
        this.testPool.add(TEST_H_0_FILE);
    }

    public static IOUtils getInstance() {
        if (instance == null) instance = new IOUtils();
        return instance;
    }

    public Matrix readMatrixFromFile(int matrixId, String path, String fileName, MatrixGeneratorType generatorType) {
        ClusterDetection.getInstance().refreshClusterCounter();
        List<Character> chars = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(path + fileName)))) {
            int c;
            while ((c = reader.read()) != -1) {
                char tmp = (char) c;
                if (tmp != '\r') {
                    chars.add(tmp);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // calculate matrix size
        int size = 0;
        for (char c : chars) {
            if (c != '\n') {
                size++;
            } else {
                break;
            }
        }
        // create matrix
        Matrix matrix = new Matrix(matrixId, size, 1.0, generatorType);
        int counter = 0;
        int y = 0;
        int x = 0;
        boolean isContinue = true;
        while (isContinue) {
            char cont = chars.get(counter);
            if (cont == '1' || cont == '0') {
                matrix.setCellValue(x, y, cont == '1');
                x++;
            }
            if (cont == '\n') {
                y++;
                x = 0;
            }
            if (y == size) {
                isContinue = false;
            }
            counter++;
        }

        return matrix;
    }

    public List<Matrix> readTestMatrices() {
        List<Matrix> result = new ArrayList<>();
        int idGen = 0;
        for (String name : testPool) {
            System.out.println("Process " + name + "...");
            result.add(readMatrixFromFile(idGen++, TEST_RESOURCES_FOLDER, name, MatrixGeneratorType.TEST));
        }
        return Collections.unmodifiableList(result);
    }

    @SuppressWarnings("Duplicates")
    public void writeMatrixStatisticsToCSV(String generatorName, List<MatrixBlackHoleStatistic> list, int matrixSize) {
        String fileName = generatorName + "_" + list.size() + "size" + matrixSize + "x" + matrixSize + ".csv";
        try (FileWriter fileWriter = new FileWriter(OUT_FOLDER + fileName)) {
            fileWriter.append(CSV_MATRIX_STATISTICS_HEADER);
            fileWriter.append(LINE_SEPARATOR);
            for (MatrixBlackHoleStatistic mBHS : list) {
                fileWriter.append(String.valueOf(mBHS.getMatrixId()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(mBHS.getP()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(mBHS.isMatrixContainsPercolation()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(mBHS.getBlackCellCount()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(mBHS.getConcentrationRate()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(mBHS.getAverageBlackCellCountInRow()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(mBHS.getAverageBlackCellCountInColumn()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(mBHS.getZeroRowCount()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(mBHS.getZeroColumnCount()));
                fileWriter.append(LINE_SEPARATOR);
            }
            System.out.println("Write to CSV file succeeded! See result: " + OUT_FOLDER + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("Duplicates")
    public void writeClusterStatistic(List<ClusterStatistic> clusterStatistics) {
        String fileName = CLUSTER_STATISTIC + ".csv";
        try (FileWriter fileWriter = new FileWriter(OUT_FOLDER + fileName)) {
            fileWriter.append(CSV_CLUSTER_STATISTIC_HEADER);
            fileWriter.append(LINE_SEPARATOR);
            for (ClusterStatistic clusterStatistic : clusterStatistics) {
                fileWriter.append(String.valueOf(clusterStatistic.getAverageAmountClusters()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(clusterStatistic.getAverageSizeCluster()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(clusterStatistic.getAverageDistanceBetweenClusters()));
                fileWriter.append(LINE_SEPARATOR);
            }
            System.out.println("Write to CSV file Succeeded! See result " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("Duplicates")
    public void writeBlackHoleLightningStatistic(List<BlackHoleLightningStatistic> blackHoleLightningStatistics) {
        String fileName = BLACK_HOLE_LIGHTNING_STATISTIC + ".csv";
        try (FileWriter fileWriter = new FileWriter(OUT_FOLDER + fileName)) {
            fileWriter.append(CSV_BLACK_HOLE_LIGHTNING_STATISTIC_HEADER);
            fileWriter.append(LINE_SEPARATOR);
            for (BlackHoleLightningStatistic blackHoleLightningStatistic : blackHoleLightningStatistics) {
                fileWriter.append(String.valueOf(blackHoleLightningStatistic.getAverageAmountBlackHole()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(blackHoleLightningStatistic.getConcentrationBlackHole()));
                fileWriter.append(LINE_SEPARATOR);
            }
            System.out.println("Write to CSV file Succeeded! See result " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("Duplicates")
    public void writeWayLightningStatistic(List<BlackHoleLightningStatistic> blackHoleLightningStatistics) {
        String fileName = BLACK_HOLE_LIGHTNING_STATISTIC + ".csv";
        try (FileWriter fileWriter = new FileWriter(OUT_FOLDER + fileName)) {
            fileWriter.append(CSV_BLACK_HOLE_LIGHTNING_STATISTIC_HEADER);
            fileWriter.append(LINE_SEPARATOR);
            for (BlackHoleLightningStatistic blackHoleLightningStatistic : blackHoleLightningStatistics) {
                fileWriter.append(String.valueOf(blackHoleLightningStatistic.getAverageAmountBlackHole()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(blackHoleLightningStatistic.getConcentrationBlackHole()));
                fileWriter.append(LINE_SEPARATOR);
            }
            System.out.println("Write to CSV file Succeeded! See result " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
