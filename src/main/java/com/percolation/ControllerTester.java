package com.percolation;

import com.percolation.domain.matrix.Matrix;
import com.percolation.domain.statistic.ClusterStatistic;
import com.percolation.domain.statistic.MatrixBlackHoleStatistic;
import com.percolation.service.MatrixService;

import java.util.List;

public class ControllerTester {
    public static void main(String[] args) {
        System.out.println("***********************************DEMO*************************************");

        MatrixService ms = MatrixService.getInstance();
        // generate demo
        List<Matrix> test = ms.createTestMatrices();
        MatrixBlackHoleStatistic testStatistic = ms.calculateMatrixBlackHoleStatistic(test.get(0));
        for (Matrix m : test) {
            System.out.println(m.getHumanReadableMatrixWithClusters());
        }

        System.out.println("****************************************************************************");
        System.out.println("Cluster statistic:");
        ClusterStatistic cs =  ms.getClusterStatistic(ms.getCurrentMatrices());
        System.out.println("AverageAmountClusters: " + cs.getAverageAmountClusters());
        System.out.println("AverageSizeCluster: " + cs.getAverageSizeCluster());
    }
}