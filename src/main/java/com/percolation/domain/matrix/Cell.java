package com.percolation.domain.matrix;

import lombok.Data;

@Data
public class Cell {
    private Cluster cluster;
    private boolean value;
    private int dejkstraValue;
    private int x;
    private int y;

    public int getDejkstraValue() {
        return dejkstraValue;
    }

    public void setDejkstraValue(int dejkstraValue) {
        this.dejkstraValue = dejkstraValue;
    }

    public int getHumanReadableValue() {
        return value ? 1 : 0;
    }

    public int getClusterId() {
        return cluster == null ? 0 : cluster.getId();
    }
}
