package com.percolation.generator;

import com.percolation.detection.ClusterDetection;
import com.percolation.detection.PercolationDetection;
import com.percolation.domain.matrix.Matrix;
import com.percolation.domain.matrix.MatrixGeneratorType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GradientMatrixGenerator implements MatrixGenerator {

    private static final double MIN_P = 0.01;

    private Randomizer randomizer;
    private MatrixGeneratorType generatorType;
    private double step;
    private ClusterDetection clusterDetection;
    private PercolationDetection percolationDetection;

    public GradientMatrixGenerator() {
        this.randomizer = Randomizer.getInstance();
        this.step = 0.0;
        this.generatorType = MatrixGeneratorType.GRADIENT;
        this.clusterDetection = ClusterDetection.getInstance();
        this.percolationDetection = PercolationDetection.getInstance();
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Matrix generateRandomMatrix(int id, int N, double P) {
        clusterDetection.refreshClusterCounter();
        Matrix matrix = new Matrix(id, N, P, generatorType);
        double acc = MIN_P;

        for (int j = 0; j < N; j++) {
            for (int i = 0; i < N; i++) {
                matrix.setCellValue(i, j, randomizer.getRandomValue() <= acc);
            }
            acc += step;
        }
        percolationDetection.detectPercolation(matrix);
        return matrix;
    }

    @Override
    public List<Matrix> generateRandomMatrices(int count, int N, double maxP) {
        List<Matrix> matrices = new ArrayList<>();
        // calculate step
        this.step = (maxP - MIN_P) / N;
        for (int i = 0; i < count; i++) {
            matrices.add(generateRandomMatrix(i, N, maxP));
        }
        return Collections.unmodifiableList(matrices);
    }
}
