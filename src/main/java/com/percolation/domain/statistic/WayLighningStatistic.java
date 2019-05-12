package com.percolation.domain.statistic;

import com.percolation.domain.Way;
import lombok.Data;

import java.util.ArrayList;

@Data
public class WayLighningStatistic {
    private int n;
    private double p;
    private ArrayList<Way> way;
    private double averageWay;
}
