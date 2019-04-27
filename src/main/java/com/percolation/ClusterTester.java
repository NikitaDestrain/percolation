package com.percolation;

import com.percolation.domain.matrix.Matrix;
import com.percolation.domain.matrix.MatrixGeneratorType;

public class ClusterTester {

    private static int[][] TEST_MATRIX = {
            {1, 1, 1, 1},
            {0, 0, 1, 0},
            {1, 1, 1, 1},
            {0, 0, 0, 0}
    };

    private static int[][] TEST_MATRIX2 = {
            {0, 0, 1, 1, 1, 1, 1, 0, 0},
            {0, 0, 0, 1, 0, 1, 0, 0, 0},
            {1, 0, 0, 1, 0, 1, 0, 0, 1},
            {1, 1, 1, 0, 0, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 0, 0, 1, 1, 1},
            {1, 0, 0, 1, 0, 1, 0, 0, 1},
            {0, 0, 0, 1, 0, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 0, 0}
    };

    public static void main(String[] args) {
        int size = TEST_MATRIX2.length;
        Matrix matrix = new Matrix(0, size, 0.0, MatrixGeneratorType.TEST);
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                System.out.print(TEST_MATRIX2[y][x]);
                matrix.setCellValue(x, y, TEST_MATRIX2[y][x] == 1);
            }
            System.out.println();
        }

        System.out.println(matrix.getHumanReadableMatrixWithClusters());
    }
}
