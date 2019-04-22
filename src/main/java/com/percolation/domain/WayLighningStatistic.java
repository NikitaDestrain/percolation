package com.percolation.domain;

import lombok.Data;

@Data
public class WayLighningStatistic {
    private int n;
    private double p;
    private Way[] way;
    private double averageWay;
}
