package com.percolation.domain.statistic;

import lombok.Data;

@Data
public class ClusterStatistic {
    private int averageAmountClusters;
    private double averageSizeCluster;
    private double averageDistanceBetweenClusters;
}
