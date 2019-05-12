package com.percolation.useless;

import com.percolation.domain.matrix.Matrix;
import com.percolation.domain.matrix.MatrixGeneratorType;
import com.percolation.domain.statistic.ClusterStatistic;
import com.percolation.domain.statistic.MatrixBlackHoleStatistic;
import com.percolation.service.MatrixService;

import java.util.ArrayList;
import java.util.List;

public class ControllerTester {
    public static void main(String[] args) {
        System.out.println("***********************************DEMO*************************************");

        MatrixService ms = MatrixService.getInstance();
        // generate demo
        List<Matrix> test = ms.createTestMatrices();
        MatrixBlackHoleStatistic testStatistic = ms.calculateMatrixBlackHoleStatistic(test.get(0));
        /*for (Matrix m : test) {
            System.out.println(m.getHumanReadableMatrixWithClusters());
        }*/

        System.out.println("****************************************************************************");
        System.out.println("Cluster statistic:");
        ClusterStatistic cs =  ms.getClusterStatistic(ms.getCurrentMatrices());
        System.out.println("AverageAmountClusters: " + cs.getAverageAmountClusters());
        System.out.println("AverageSizeCluster: " + cs.getAverageSizeCluster());

        List<Matrix> list = new ArrayList<>();
        list.add(test.get(2));
        ClusterStatistic cs1 = ms.getClusterStatistic(list);
        //ms.writeMatrixStatisticToFile("test", ms.calculateMatrixBlackHoleStatistic(list), 50);
        System.out.println(cs1);
        System.out.println();
        Matrix m = list.get(0);
        System.out.println(m.getClusters().size());
        System.out.println();

        List<Matrix> testers = ms.createMatrices(100,50,0.25,MatrixGeneratorType.UNIFORM_DISTRIBUTION);
        ClusterStatistic cs2 = ms.getClusterStatistic(testers);
        System.out.println(cs2);
    }
}
