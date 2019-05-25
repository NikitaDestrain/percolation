package com.percolation.domain.matrix;

import com.percolation.detection.ClusterDetection;
import lombok.Data;

import java.util.*;

/**
 * @author Nikita Govokhin
 */
@Data
public class Matrix implements Cloneable {
    // NxN = size
    private int id;
    private boolean containPercolation;
    private int N;
    private int blackCellCount;
    private double p;
    private Cell[][] values;
    private MatrixGeneratorType generatorType;
    private List<Cluster> clusters;

    public Matrix(int id, int N, double p, MatrixGeneratorType generatorType) {
        this.id = id;
        this.N = N;
        this.p = p;
        this.generatorType = generatorType;
        this.blackCellCount = 0;
        this.containPercolation = false;
        this.values = new Cell[N + 2][N + 2];
        this.clusters = new ArrayList<>();
    }

    public void setCellValue(int x, int y, boolean value) {
        // process statistic values
        if (value) {
            blackCellCount++;
        }
        // write value
        Cell cell = new Cell();
        cell.setX(x);
        cell.setY(y);
        values[y + 1][x + 1] = cell;
        values[y + 1][x + 1].setValue(value);
        // calculate cluster
        values[y + 1][x + 1].setCluster(ClusterDetection.getInstance().calculateCluster(this, x, y));
    }

    public Cell getCellValue(int x, int y) {
        if (x == -1 || y == -1)
            return null;
        if (x == this.N || y == this.N)
            return null;
        return values[y + 1][x + 1];
    }

    public Cell[] getLine(int y) {
        return values[y + 1];
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
        for (int y = 1; y < N + 1; y++) {
            for (int x = 1; x < N + 1; x++) {
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
        for (int y = 1; y < N + 1; y++) {
            for (int x = 1; x < N + 1; x++) {
                sb.append(values[y][x].getClusterId());
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public int getCoordX(Cell cell) {
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values.length; j++) {
                if (values[i][j] == cell)
                    return j;
            }
        }
        return -1;
    }

    public int getCoordY(Cell cell) {
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values.length; j++) {
                if (values[i][j] == cell)
                    return i;
            }
        }
        return -1;
    }

    public void addCluster(Cluster cluster) {
        clusters.add(cluster);
    }

    public List<Cluster> getAllClusters() {
        return Collections.unmodifiableList(clusters);
    }

    public void removeCluster(Cluster cluster) {
        clusters.remove(cluster);
    }

    public Matrix clone() throws CloneNotSupportedException {
        return (Matrix) super.clone();
    }
}
