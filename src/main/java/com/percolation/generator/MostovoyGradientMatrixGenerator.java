package com.percolation.generator;

import com.percolation.detection.ClusterDetection;
import com.percolation.detection.PercolationDetection;
import com.percolation.domain.matrix.Matrix;
import com.percolation.domain.matrix.MatrixGeneratorType;

public class MostovoyGradientMatrixGenerator {

    private MatrixGeneratorType generatorType;
    private ClusterDetection clusterDetection;
    private PercolationDetection percolationDetection;

    public MostovoyGradientMatrixGenerator() {
        this.generatorType = MatrixGeneratorType.MOSTOVOY_GRADIENT;
        this.clusterDetection = ClusterDetection.getInstance();
        this.percolationDetection = PercolationDetection.getInstance();
    }

    // horizontal = 1.0 / vertical = other
    // alpha = 45
    // should be (N - 1)% 6 = 0
    @SuppressWarnings("Duplicates")
    public Matrix generateMatrix(int id, int N, boolean isHorizontal) {
        clusterDetection.refreshClusterCounter();
        Matrix matrix = new Matrix(id, N, isHorizontal ? 1.0 : -1.0, generatorType);

        int center = N / 2;
        int thirdPart = N / 3;
        int halfOfThirdPart = thirdPart / 2;
        int startY = center - halfOfThirdPart;

        if (isHorizontal) {
            boolean isStartY = false;
            int oneCount = -1;

            for (int y = 0; y < N; y++) {
                if (y >= startY) {
                    isStartY = true;
                    oneCount++;
                }
                for (int x = 0; x < N; x++) {
                    if (isStartY && (x >= (center - oneCount) && x <= (center + oneCount))) {
                        matrix.setCellValue(x, y, true);
                    } else {
                        matrix.setCellValue(x, y, false);
                    }
                }
            }
        } else {
            boolean isStartX = false;
            int oneCount = -1;

            for (int x = 0; x < N; x++) {
                if (x >= startY) {
                    isStartX = true;
                    oneCount++;
                }
                for (int y = 0; y < N; y++) {
                    if (isStartX && (y >= (center - oneCount) && y <= (center + oneCount))) {
                        matrix.setCellValue(x, y, true);
                    } else {
                        matrix.setCellValue(x, y, false);
                    }
                }
            }
        }
        percolationDetection.detectPercolation(matrix);
        return matrix;
    }
}
