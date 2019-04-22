package com.percolation.io;

import com.percolation.domain.BlackHoleLightningStatistic;
import com.percolation.domain.ClusterStatistic;
import com.percolation.domain.MatrixBlackHoleStatistic;

import java.io.FileWriter;
import java.util.List;

public class IOUtils {

    private static final String CSV_MATRIX_STATISTICS_HEADER = "p,avg bc cnt in row,avg bc cnt in column,zero row cnt,zero column cnt";
    private static final String COMMA_DELIMITER = ",";
    private static final String LINE_SEPARATOR = "\n";
    private static final String OUT_FOLDER = "data/";
    private static final String CLUSTER_STATISTIC = "cluster_statistic";
    private static final String BLACK_HOLE_LIGHTNING_STATISTIC = "black_hole_lightning_statistic";
    private static final String WAY_LIGHTNING_STATISTIC = "way_lightning_statistic";
    private static final String CSV_CLUSTER_STATISTIC_HEADER = "averageAmountClusters,averageSizeCluster,averageDistanceBetweenClusters";
    private static final String CSV_BLACK_HOLE_LIGHTNING_STATISTIC_HEADER ="averageAmountBlackHole,concentrationBlackHole";
    private static final String CSV_WAY_LIGHTNING_STATISTIC_HEADER = ",";

    private static IOUtils instance;

    private IOUtils() {
    }

    public static IOUtils getInstance() {
        if (instance == null) instance = new IOUtils();
        return instance;
    }

    public void writeMatrixStatisticsToCSV(List<MatrixBlackHoleStatistic> list, int matrixSize) {
        String fileName = list.size() + "size" + matrixSize + "x" + matrixSize + ".csv";
        try (FileWriter fileWriter = new FileWriter(OUT_FOLDER + fileName)) {
            fileWriter.append(CSV_MATRIX_STATISTICS_HEADER);
            fileWriter.append(LINE_SEPARATOR);
            for (MatrixBlackHoleStatistic mBHS : list) {
                fileWriter.append(String.valueOf(mBHS.getP()));
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
            System.out.println("Write to CSV file Succeeded! See result \'data/300size10x10.csv\'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
