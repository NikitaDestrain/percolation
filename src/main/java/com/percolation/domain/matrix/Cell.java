package com.percolation.domain.matrix;

import lombok.Data;

@Data
public class Cell {
    private Cluster cluster;
    private boolean value;

    public int getHumanReadableValue() {
        return value ? 1 : 0;
    }

    public int getClusterId() {
        return cluster == null ? 0 : cluster.getId();
    }
}
