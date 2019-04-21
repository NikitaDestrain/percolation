package com.percolation.io;

import com.percolation.domain.MatrixBlackHoleStatistic;

import java.io.FileWriter;
import java.util.List;

public class IOUtils {

    private static final String CSV_MATRIX_STATISTICS_HEADER = "p,avg bc cnt in row,avg bc cnt in column,zero row cnt,zero column cnt";
    private static final String COMMA_DELIMITER = ",";
    private static final String LINE_SEPARATOR = "\n";
    private static final String OUT_FOLDER = "data/";

    private static IOUtils instance;

    private IOUtils() {
    }

    public static IOUtils getInstance() {
        if (instance == null) instance = new IOUtils();
        return instance;
    }

    public void writeMatrixStatisticsToCSV(List<MatrixBlackHoleStatistic> list, int matrixSize) {
        String fileName = list.size() + "size" + matrixSize + "x" + matrixSize + ".csv";
        try (FileWriter fileWriter = new FileWriter(OUT_FOLDER + fileName)) {
            fileWriter.append(CSV_MATRIX_STATISTICS_HEADER);
            fileWriter.append(LINE_SEPARATOR);
            for (MatrixBlackHoleStatistic mBHS : list) {
                fileWriter.append(String.valueOf(mBHS.getP()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(mBHS.getAverageBlackCellCountInRow()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(mBHS.getAverageBlackCellCountInColumn()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(mBHS.getZeroRowCount()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(mBHS.getZeroColumnCount()));
                fileWriter.append(LINE_SEPARATOR);
            }
            System.out.println("Write to CSV file Succeeded! See result \'data/300size10x10.csv\'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
