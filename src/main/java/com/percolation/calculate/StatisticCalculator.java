package com.percolation.calculate;

import com.percolation.domain.Cell;
import com.percolation.domain.Matrix;
import com.percolation.domain.MatrixBlackHoleStatistic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        int blackCellCount = 0;
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
                    blackCellCount++;
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
                    blackCellCount++;
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

        mBHS.setP(p);
        mBHS.setBlackCellCount(blackCellCount);
        mBHS.setBlackCellCountInRows(blackCellCountInRows);
        mBHS.setBlackCellCountInColumns(blackCellCountInColumns);
        mBHS.setAverageBlackCellCountInRow(averageBlackCellCountInRow);
        mBHS.setAverageBlackCellCountInColumn(averageBlackCellCountInColumn);
        mBHS.setZeroRowCount(zeroRowCount);
        mBHS.setZeroColumnCount(zeroColumnCount);

        return mBHS;
    }

    public List<MatrixBlackHoleStatistic> calculateMatrixBlackHoleStatistic(List<Matrix> matrices) {
        List<MatrixBlackHoleStatistic> statistics = new ArrayList<>();
        for (Matrix m : matrices) {
            statistics.add(calculateMatrixBlackHoleStatistic(m));
        }
        return Collections.unmodifiableList(statistics);
    }
}
