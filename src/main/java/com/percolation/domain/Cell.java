package com.percolation.domain;

import lombok.Data;

@Data
public class Cell {
    private Cluster cluster;
    private boolean value;

    public int getHumanReadableValue() {
        return value ? 1 : 0;
    }
}
