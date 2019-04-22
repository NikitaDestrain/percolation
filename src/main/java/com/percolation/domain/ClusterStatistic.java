package com.percolation.domain;

import lombok.Data;

@Data
public class ClusterStatistic {
    private int averageAmountClusters;
    private double averageSizeCluster;
    private double averageDistanceBetweenClusters;
}
