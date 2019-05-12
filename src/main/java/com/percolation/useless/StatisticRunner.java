package com.percolation.useless;

import com.percolation.calculate.StatisticCalculator;
import com.percolation.domain.matrix.Matrix;
import com.percolation.domain.statistic.MatrixBlackHoleStatistic;
import com.percolation.generator.*;
import com.percolation.io.IOUtils;

import java.util.ArrayList;
import java.util.List;

public class StatisticRunner {

    private static final int MATRIX_SIZE = 50;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        StatisticCalculator sc = StatisticCalculator.getInstance();
        IOUtils iOU = IOUtils.getInstance();

        System.out.println("UNIFORM DISTRIBUTION processing...");
        MatrixGenerator mg = new UniformDistributionMatrixGenerator();
        List<Matrix> matrices = mg.generateRandomMatrices(500, MATRIX_SIZE, 0.2);
        //System.out.println(matrices.get(5).getHumanReadableMatrix());
        List<MatrixBlackHoleStatistic> statistics = sc.calculateMatrixBlackHoleStatistic(matrices);
        iOU.writeMatrixStatisticsToCSV("uniform_distribution", statistics, MATRIX_SIZE);
        System.out.println("SUCCESS\n");

        System.out.println("UNIFORM processing...");
        mg = new UniformMatrixGenerator();
        matrices = mg.generateRandomMatrices(5, MATRIX_SIZE, 0.95);
        //System.out.println(matrices.get(5).getHumanReadableMatrix());
        statistics = sc.calculateMatrixBlackHoleStatistic(matrices);
        iOU.writeMatrixStatisticsToCSV("uniform_5_0.9", statistics, MATRIX_SIZE);
        System.out.println("SUCCESS\n");

        System.out.println("GRADIENT processing...");
        mg = new GradientMatrixGenerator();
        matrices = mg.generateRandomMatrices(500, MATRIX_SIZE, 0.9);
        //System.out.println(matrices.get(5).getHumanReadableMatrix());
        statistics = sc.calculateMatrixBlackHoleStatistic(matrices);
        iOU.writeMatrixStatisticsToCSV("gradient_0.9", statistics, MATRIX_SIZE);
        System.out.println("SUCCESS\n");

        System.out.println("MOSTOVOY GRADIENT processing...");
        MostovoyGradientMatrixGenerator mGMG = new MostovoyGradientMatrixGenerator();
        matrices = new ArrayList<>();
        Matrix matrixHorizontal = mGMG.generateMatrix(0, MATRIX_SIZE % 2 == 0 ? MATRIX_SIZE + 1 : MATRIX_SIZE, true);
        Matrix matrixVertical = mGMG.generateMatrix(1, MATRIX_SIZE % 2 == 0 ? MATRIX_SIZE + 1 : MATRIX_SIZE, false);
        matrices.add(matrixHorizontal);
        //System.out.println(matrixHorizontal.getHumanReadableMatrixWithClusters());
        matrices.add(matrixVertical);
        //System.out.println(matrixVertical.getHumanReadableMatrixWithClusters());
        statistics = sc.calculateMatrixBlackHoleStatistic(matrices);
        iOU.writeMatrixStatisticsToCSV("mostovoy_gradient_", statistics, MATRIX_SIZE);
        System.out.println("SUCCESS\n");

        long finish = System.currentTimeMillis();
        System.out.println((finish - start) + " ms");
    }
}
