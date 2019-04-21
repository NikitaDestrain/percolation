package com.percolation.domain;

import lombok.Data;

@Data

// todo remove statistic info
public class Matrix {
    // NxN = size
    private int N;
    private int blackCellCount;
    private int whiteCellCount;
    private double concentrationRate;
    private double p;
    private Cell[][] values;

    public Matrix(int N, double p) {
        this.N = N;
        this.p = p;
        this.blackCellCount = 0;
        this.whiteCellCount = 0;
        this.values = new Cell[N][N];
    }

    public void setCellValue(int x, int y, boolean value) {
        // process statistic values
        if (value) {
            blackCellCount++;
        } else {
            whiteCellCount++;
        }
        // write value
        values[x][y] = new Cell();
        values[x][y].setValue(value);
    }

    public Cell getCellValue(int x, int y) {
        return values[x][y];
    }

    public String getHumanReadableMatrix() {
        StringBuilder sb = new StringBuilder();
        sb.append("Size: " + N + "x" + N + "\np: " + p + "\nConcentration rate: " + concentrationRate);
        sb.append("\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(values[i][j].getHumanReadableValue());
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
