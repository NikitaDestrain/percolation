package com.percolation.domain.statistic;

import lombok.Data;

/**
 * @author Nikita Govokhin
 */
@Data
public class MatrixBlackHoleStatistic {
    int matrixId;
    boolean matrixContainsPercolation;
    double p;
    int blackCellCount;
    int[] blackCellCountInRows;
    int[] blackCellCountInColumns;
    double averageBlackCellCountInRow;
    double averageBlackCellCountInColumn;
    int zeroRowCount;
    int zeroColumnCount;
    double concentrationRate;
}
