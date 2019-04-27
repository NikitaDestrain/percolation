package com.percolation.domain;

import com.percolation.domain.matrix.Matrix;
import lombok.Data;

@Data
public class Way {
    private static final int AMOUNT_COLUMN = 2;

    private Matrix matrix;

    private int[][] wayArray;

    public void init(int n) {
        wayArray = new int[n][AMOUNT_COLUMN];
    }

    public void setValue(int n, int x, int y) {
        this.wayArray[n][AMOUNT_COLUMN - 2] = x;
        this.wayArray[n][AMOUNT_COLUMN - 1] = y;
    }
}
