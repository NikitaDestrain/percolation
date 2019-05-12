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
public class UniformDistributionMatrixGenerator implements MatrixGenerator {

    private Randomizer randomizer;
    private MatrixGeneratorType generatorType;
    private ClusterDetection clusterDetection;
    private PercolationDetection percolationDetection;

    public UniformDistributionMatrixGenerator() {
        this.randomizer = Randomizer.getInstance();
        this.generatorType = MatrixGeneratorType.UNIFORM_DISTRIBUTION;
        this.clusterDetection = ClusterDetection.getInstance();
        this.percolationDetection = PercolationDetection.getInstance();
    }

    public void refreshRandomizer() {
        randomizer.refreshSeed();
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
    public List<Matrix> generateRandomMatrices(int count, int N, double P) {
        List<Matrix> matrices = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            matrices.add(generateRandomMatrix(i, N, P));
        }
        return Collections.unmodifiableList(matrices);
    }
}
