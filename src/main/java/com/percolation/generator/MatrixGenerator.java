package com.percolation.generator;

import com.percolation.domain.Matrix;

import java.util.List;

public interface MatrixGenerator {
    Matrix generateRandomMatrix(int N, double p);

    List<Matrix> generateRandomMatrices(int count, int N, double minP, double maxP);
}
