package com.percolation.generator;

import com.percolation.domain.matrix.Matrix;

import java.util.List;

public interface MatrixGenerator {
    Matrix generateRandomMatrix(int id, int N, double p);

    List<Matrix> generateRandomMatrices(int count, int N, double p);
}
