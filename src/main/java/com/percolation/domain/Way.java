package com.percolation.domain;

import com.percolation.domain.matrix.Cell;
import com.percolation.domain.matrix.Matrix;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Way implements Cloneable {
    private static final int AMOUNT_COLUMN = 2;

    private Matrix matrix;

    private ArrayList<Cell> wayArray;

    private int lengthWay;

    private int redCell;

    private double sizeHole;

    private double widthWay;

    public void init() {
        wayArray = new ArrayList<>();
        redCell = 0;
    }

    public void addCell(Cell cell) {
        this.wayArray.add(cell);

    }

    @Override
    public Way clone() throws CloneNotSupportedException {
        return (Way)super.clone();
    }
}
