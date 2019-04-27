package com.percolation.domain.statistic;

import com.percolation.domain.Way;
import lombok.Data;

@Data
public class WayLighningStatistic {
    private int n;
    private double p;
    private Way[] way;
    private double averageWay;
}
