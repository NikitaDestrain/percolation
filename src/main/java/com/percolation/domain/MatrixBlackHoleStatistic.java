package com.percolation.domain;

import lombok.Data;

@Data
public class MatrixBlackHoleStatistic {
    double p;
    int blackCellCount;
    int[] blackCellCountInRows;
    int[] blackCellCountInColumns;
    double averageBlackCellCountInRow;
    double averageBlackCellCountInColumn;
    int zeroRowCount;
    int zeroColumnCount;
}
