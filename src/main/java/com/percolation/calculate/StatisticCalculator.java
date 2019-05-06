package com.percolation.calculate;

import com.percolation.domain.matrix.Cell;
import com.percolation.domain.matrix.Cluster;
import com.percolation.domain.matrix.Matrix;
import com.percolation.domain.statistic.ClusterStatistic;
import com.percolation.domain.statistic.MatrixBlackHoleStatistic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class StatisticCalculator {

    private static StatisticCalculator instance;

    private StatisticCalculator() {
    }

    public static StatisticCalculator getInstance() {
        if (instance == null) instance = new StatisticCalculator();
        return instance;
    }

    public double calculateConcentrationRate(int blackCount, int whiteCount) {
        return (double) blackCount / (double) (blackCount + whiteCount);
    }

    public MatrixBlackHoleStatistic calculateMatrixBlackHoleStatistic(Matrix matrix) {
        MatrixBlackHoleStatistic mBHS = new MatrixBlackHoleStatistic();

        int N = matrix.getN();
        double p = matrix.getP();
        int blackCellCount = matrix.getBlackCellCount();
        int whiteCellCount = N * N - blackCellCount;
        double concentrationRate = calculateConcentrationRate(blackCellCount, whiteCellCount);
        int[] blackCellCountInRows = new int[N];
        int[] blackCellCountInColumns = new int[N];
        double averageBlackCellCountInRow;
        double averageBlackCellCountInColumn;
        int zeroRowCount = 0;
        int zeroColumnCount = 0;

        // column runner
        for (int x = 0; x < N; x++) {
            boolean isZeroColumn = true;
            for (int y = 0; y < N; y++) {
                Cell cell = matrix.getCellValue(x, y);
                if (cell.isValue()) {
                    blackCellCountInColumns[x] = blackCellCountInColumns[x] + 1;
                    blackCellCountInRows[y] = blackCellCountInRows[y] + 1;
                    isZeroColumn = false;
                }
            }
            if (isZeroColumn) {
                zeroColumnCount++;
            }
        }

        // row runner
        for (int y = 0; y < N; y++) {
            boolean isZeroRow = true;
            for (int x = 0; x < N; x++) {
                Cell cell = matrix.getCellValue(x, y);
                if (cell.isValue()) {
                    blackCellCountInColumns[x] = blackCellCountInColumns[x]++;
                    blackCellCountInRows[y] = blackCellCountInRows[y]++;
                    isZeroRow = false;
                }
            }
            if (isZeroRow) {
                zeroRowCount++;
            }
        }

        int acc1 = 0;
        int acc2 = 0;
        for (int i = 0; i < N; i++) {
            acc1 += blackCellCountInRows[i];
            acc2 += blackCellCountInColumns[i];
        }
        averageBlackCellCountInRow = (double) acc1 / (double) N;
        averageBlackCellCountInColumn = (double) acc2 / (double) N;

        mBHS.setMatrixId(matrix.getId());
        mBHS.setMatrixContainsPercolation(matrix.isContainPercolation());
        mBHS.setP(p);
        mBHS.setBlackCellCount(blackCellCount);
        mBHS.setBlackCellCountInRows(blackCellCountInRows);
        mBHS.setBlackCellCountInColumns(blackCellCountInColumns);
        mBHS.setAverageBlackCellCountInRow(averageBlackCellCountInRow);
        mBHS.setAverageBlackCellCountInColumn(averageBlackCellCountInColumn);
        mBHS.setZeroRowCount(zeroRowCount);
        mBHS.setZeroColumnCount(zeroColumnCount);
        mBHS.setConcentrationRate(concentrationRate);

        return mBHS;
    }

    public List<MatrixBlackHoleStatistic> calculateMatrixBlackHoleStatistic(List<Matrix> matrices) {
        List<MatrixBlackHoleStatistic> statistics = new ArrayList<>();
        for (Matrix m : matrices) {
            statistics.add(calculateMatrixBlackHoleStatistic(m));
        }
        return Collections.unmodifiableList(statistics);
    }

    public ClusterStatistic calculateClusterStatistic(List<Matrix> matrices) {
        int overallClusterCnt = 0;
        int overallClusterSize = 0;

        int matricesCount = matrices.size();
        for (Matrix matrix : matrices) {
            List<Cluster> clusters = matrix.getClusters();

            overallClusterCnt += clusters.size();
            for (Cluster cluster : clusters) {
                overallClusterSize += cluster.getSize();
            }
        }

        double avgAmountClusters = (double) overallClusterCnt / (double) matricesCount;
        double avgSizeCluster = (double) overallClusterSize / ((double) matricesCount * (double) overallClusterCnt);

        ClusterStatistic result = new ClusterStatistic();
        result.setAverageAmountClusters(avgAmountClusters);
        result.setAverageSizeCluster(avgSizeCluster);
        
        return result;
    }
}
