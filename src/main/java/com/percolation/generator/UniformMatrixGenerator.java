package com.percolation.generator;

import com.percolation.detection.ClusterDetection;
import com.percolation.detection.PercolationDetection;
import com.percolation.domain.matrix.Matrix;
import com.percolation.domain.matrix.MatrixGeneratorType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Nikita Govokhin
 */
public class UniformMatrixGenerator implements MatrixGenerator {

    private Randomizer randomizer;
    private MatrixGeneratorType generatorType;
    private ClusterDetection clusterDetection;
    private PercolationDetection percolationDetection;

    private static final double START_P = 0.05;
    private static final double STEP_P = 0.05;

    public UniformMatrixGenerator() {
        this.randomizer = Randomizer.getInstance();
        this.generatorType = MatrixGeneratorType.UNIFORM;
        this.clusterDetection = ClusterDetection.getInstance();
        this.percolationDetection = PercolationDetection.getInstance();
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Matrix generateRandomMatrix(int id, int N, double P) {
        clusterDetection.refreshClusterCounter();
        Matrix matrix = new Matrix(id, N, P, generatorType);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix.setCellValue(i, j, randomizer.getRandomValue() <= P);
            }
        }
        percolationDetection.detectPercolation(matrix);
        return matrix;
    }

    @Override
    public List<Matrix> generateRandomMatrices(int countPerP, int N, double maxP) {
        List<Matrix> matrices = new ArrayList<>();
        boolean isContinue = true;
        double p = START_P;
        int idGen = 0;
        while (isContinue) {
            for (int i = 0; i < countPerP; i++) {
                matrices.add(generateRandomMatrix(idGen++, N, p));
            }
            p += STEP_P;
            isContinue = maxP >= p;
        }
        return Collections.unmodifiableList(matrices);
    }
}
