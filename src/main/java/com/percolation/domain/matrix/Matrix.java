package com.percolation.domain.matrix;

import com.percolation.detection.ClusterDetection;
import lombok.Data;

@Data
public class Matrix {
    // NxN = size
    private int id;
    private boolean containPercolation;
    private int N;
    private int blackCellCount;
    private double p;
    private Cell[][] values;
    private MatrixGeneratorType generatorType;

    public Matrix(int id, int N, double p, MatrixGeneratorType generatorType) {
        this.id = id;
        this.N = N;
        this.p = p;
        this.generatorType = generatorType;
        this.blackCellCount = 0;
        this.containPercolation = false;
        this.values = new Cell[N][N];
    }

    public void setCellValue(int x, int y, boolean value) {
        // process statistic values
        if (value) {
            blackCellCount++;
        }
        // write value
        values[y][x] = new Cell();
        values[y][x].setValue(value);
        // calculate cluster
        values[y][x].setCluster(ClusterDetection.getInstance().calculateCluster(this, x, y));
    }

    public Cell getCellValue(int x, int y) {
        return values[y][x];
    }

    public Cell[] getLine(int y) {
        return values[y];
    }

    @SuppressWarnings("Duplicates")
    public String getHumanReadableMatrix() {
        StringBuilder sb = new StringBuilder();
        sb.append("id: " + id +
                "\nSize: " + N + "x" + N +
                "\np: " + p +
                "\nGenerator type: " + generatorType.toString() +
                "\nBlack cell count: " + blackCellCount +
                "\nPercolation: " + containPercolation
        );
        sb.append("\n");
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                sb.append(values[y][x].getHumanReadableValue());
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @SuppressWarnings("Duplicates")
    public String getHumanReadableMatrixWithClusters() {
        StringBuilder sb = new StringBuilder();
        sb.append("id: " + id +
                "\nSize: " + N + "x" + N +
                "\np: " + p +
                "\nGenerator type: " + generatorType.toString() +
                "\nBlack cell count: " + blackCellCount +
                "\nPercolation: " + containPercolation
        );
        sb.append("\n");
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                sb.append(values[y][x].getClusterId());
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
