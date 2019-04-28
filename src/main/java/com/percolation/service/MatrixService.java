package com.percolation.service;

import com.percolation.calculate.StatisticCalculator;
import com.percolation.domain.matrix.Matrix;
import com.percolation.domain.matrix.MatrixGeneratorType;
import com.percolation.domain.statistic.MatrixBlackHoleStatistic;
import com.percolation.generator.GradientMatrixGenerator;
import com.percolation.generator.MostovoyGradientMatrixGenerator;
import com.percolation.generator.UniformDistributionMatrixGenerator;
import com.percolation.generator.UniformMatrixGenerator;
import com.percolation.io.IOUtils;

import java.util.ArrayList;
import java.util.List;

public class MatrixService {

    private IOUtils ioUtils;
    private UniformMatrixGenerator uniformMatrixGenerator;
    private GradientMatrixGenerator gradientMatrixGenerator;
    private MostovoyGradientMatrixGenerator mostovoyGradientMatrixGenerator;
    private UniformDistributionMatrixGenerator uniformDistributionMatrixGenerator;
    private StatisticCalculator statisticCalculator;

    private static MatrixService instance;

    private MatrixService() {
        this.ioUtils = IOUtils.getInstance();
        this.statisticCalculator = StatisticCalculator.getInstance();
        this.uniformMatrixGenerator = new UniformMatrixGenerator();
        this.gradientMatrixGenerator = new GradientMatrixGenerator();
        this.mostovoyGradientMatrixGenerator = new MostovoyGradientMatrixGenerator();
        this.uniformDistributionMatrixGenerator = new UniformDistributionMatrixGenerator();
    }

    public static MatrixService getInstance() {
        if (instance == null) instance = new MatrixService();
        return instance;
    }

    public List<Matrix> createTestMatrices() {
        return ioUtils.readTestMatrices();
    }

    public Matrix createMatrix(int id, int N, int p, MatrixGeneratorType generatorType) {
        Matrix matrix = null;
        switch (generatorType) {
            case UNIFORM:
                // in this case p = max p
                matrix = uniformMatrixGenerator.generateRandomMatrix(id, N, p);
                break;
            case GRADIENT:
                // in this case p = max p
                matrix = gradientMatrixGenerator.generateRandomMatrix(id, N, p);
                break;
            case MOSTOVOY_GRADIENT:
                // if p equals 1.0 -> horizontal
                matrix = mostovoyGradientMatrixGenerator.generateMatrix(id, N, p == 1.0);
                break;
            case UNIFORM_DISTRIBUTION:
                matrix = uniformDistributionMatrixGenerator.generateRandomMatrix(id, N, p);
                break;
            default:
                matrix = uniformDistributionMatrixGenerator.generateRandomMatrix(id, N, p);
                break;
        }
        return matrix;
    }

    public List<Matrix> createMatrices(int count, int N, int p, MatrixGeneratorType generatorType) {
        List<Matrix> result = null;
        switch (generatorType) {
            case UNIFORM:
                // in this case p = max p
                result = uniformMatrixGenerator.generateRandomMatrices(count, N, p);
                break;
            case GRADIENT:
                // in this case p = max p
                result = gradientMatrixGenerator.generateRandomMatrices(count, N, p);
                break;
            case MOSTOVOY_GRADIENT:
                // only two matrix
                result = new ArrayList<>();
                result.add(mostovoyGradientMatrixGenerator.generateMatrix(0, N, true));
                result.add(mostovoyGradientMatrixGenerator.generateMatrix(1, N, false));
                break;
            case UNIFORM_DISTRIBUTION:
                result = uniformDistributionMatrixGenerator.generateRandomMatrices(count, N, p);
                break;
            default:
                result = uniformDistributionMatrixGenerator.generateRandomMatrices(count, N, p);
                break;
        }
        return result;
    }

    public MatrixBlackHoleStatistic calculateMatrixBlackHoleStatistic(Matrix matrix) {
        return statisticCalculator.calculateMatrixBlackHoleStatistic(matrix);
    }

    public List<MatrixBlackHoleStatistic> calculateMatrixBlackHoleStatistic(List<Matrix> matrices, boolean exportToFile) {
        List<MatrixBlackHoleStatistic> matrixBlackHoleStatistics = statisticCalculator.calculateMatrixBlackHoleStatistic(matrices);

        if (exportToFile) {
            Matrix tmp = matrices.get(0);
            String generatorName = tmp.getGeneratorType().toString().toLowerCase();
            int size = tmp.getN();
            ioUtils.writeMatrixStatisticsToCSV(generatorName, matrixBlackHoleStatistics, size);
        }

        return matrixBlackHoleStatistics;
    }
}
