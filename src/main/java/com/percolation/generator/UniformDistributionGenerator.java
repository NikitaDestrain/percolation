package com.percolation.generator;

import com.percolation.calculate.StatisticCalculator;
import com.percolation.domain.Matrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class UniformDistributionGenerator implements MatrixGenerator {

    private static UniformDistributionGenerator instance;
    private StatisticCalculator statisticCalculator;
    private Random randomizer;

    private static final double RANGE_MIN = 0.0;
    private static final double RANGE_MAX = 1.0;

    private UniformDistributionGenerator() {
        this.statisticCalculator = StatisticCalculator.getInstance();
        this.randomizer = new Random(System.currentTimeMillis());
    }

    public static UniformDistributionGenerator getInstance() {
        if (instance == null) instance = new UniformDistributionGenerator();
        return instance;
    }

    public static void setInstance(UniformDistributionGenerator instance) {
        UniformDistributionGenerator.instance = instance;
    }

    public void refreshSeed() {
        randomizer = new Random(System.currentTimeMillis());
    }

    private double getRandomValue() {
        return RANGE_MIN + (RANGE_MAX - RANGE_MIN) * randomizer.nextDouble();
    }

    private double getRandomValue(double minP, double maxP) {
        return minP + (maxP - minP) * randomizer.nextDouble();
    }

    @Override
    public Matrix generateRandomMatrix(int N, double P) {
        Matrix matrix = new Matrix(N, P);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix.setCellValue(i, j, getRandomValue() <= P);
            }
            // process statistic and save to domain
            matrix.setConcentrationRate(
                    statisticCalculator.calculateConcentrationRate(
                            matrix.getBlackCellCount(),
                            matrix.getWhiteCellCount()
                    )
            );
        }
        return matrix;
    }

    @Override
    public List<Matrix> generateRandomMatrices(int count, int N, double minP, double maxP) {
        List<Matrix> matrices = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            double P = getRandomValue(minP, maxP);
            matrices.add(generateRandomMatrix(N, P));
        }
        return Collections.unmodifiableList(matrices);
    }
}
