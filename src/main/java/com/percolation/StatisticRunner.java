package com.percolation;

import com.percolation.calculate.StatisticCalculator;
import com.percolation.domain.Matrix;
import com.percolation.domain.MatrixBlackHoleStatistic;
import com.percolation.generator.UniformDistributionGenerator;
import com.percolation.io.IOUtils;

import java.util.List;

public class StatisticRunner {

    public static void main(String[] args) {
        StatisticCalculator sc = StatisticCalculator.getInstance();
        IOUtils iOU = IOUtils.getInstance();
        UniformDistributionGenerator udg = UniformDistributionGenerator.getInstance();
        List<Matrix> matrices = udg.generateRandomMatrices(300, 10, 0.05, 0.9);
        List<MatrixBlackHoleStatistic> statistics = sc.calculateMatrixBlackHoleStatistic(matrices);
        //System.out.println(statistics.get(8));
        iOU.writeMatrixStatisticsToCSV(statistics, 10);
    }
}
