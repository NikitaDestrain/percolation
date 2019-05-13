package com.percolation.detection;

import com.percolation.domain.Way;
import com.percolation.domain.matrix.Cell;
import com.percolation.domain.matrix.Matrix;
import com.percolation.domain.statistic.WayLighningStatistic;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Dmitryi Vasilev
 */
public class DejkstraDetection {
    private static DejkstraDetection instance;
    private String LEFT_SIDE = "LEFTSIDE";
    private String RIGHT_SIDE = "RIGHTSIDE";
    private String TOP_SIDE = "TOPSIDE";
    private String BOTTOM_SIDE = "BOTTOMSIDE";

    private Matrix matrix;
    int counter = 0;
    private boolean flag = false;
    private ArrayList<Way> ways = new ArrayList<>();
    private Way shortestWay;
    private ArrayList<Way> waysShortest = new ArrayList<>();

    private DejkstraDetection() {
    }

    public static DejkstraDetection getInstance() {
        if (instance == null)
            instance = new DejkstraDetection();
        return instance;
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }

    public void setDejkstraValueToCell() {
        for (Cell[] cell : this.matrix.getValues()
        ) {
            for (Cell cel : cell
            ) {
                if (cel.getCluster() != null)
                    cel.setDejkstraValue(1);
                else
                    cel.setDejkstraValue(Integer.MAX_VALUE / 2);
            }
        }
    }

    public void processDejkstra(Cell cell, Cell prev) {
        Cell left = this.matrix.getCellValue(cell.getX() - 1, cell.getY());
        Cell right = this.matrix.getCellValue(cell.getX() + 1, cell.getY());
        Cell bottom = this.matrix.getCellValue(cell.getX(), cell.getY() + 1);

        if (right != null && right != prev) {

            if (right.getCluster() != null) {
                if (cell.getCluster() == null && cell.getDejkstraValue() + 1 < right.getDejkstraValue()) {
                    right.setDejkstraValue(cell.getDejkstraValue() + 1);
                    processDejkstra(right, cell);

                } else {
                    right.setDejkstraValue(cell.getDejkstraValue() + 1);
                    processDejkstra(right, cell);
                }
            } else {
                if (right.getDejkstraValue() > cell.getDejkstraValue() + matrix.getN()) {
                    right.setDejkstraValue(cell.getDejkstraValue() + matrix.getN());
                    processDejkstra(right, cell);
                }
            }
        }

        if (left != null && left != prev) {
            if (left.getCluster() != null) {
                if (cell.getCluster() == null && cell.getDejkstraValue() + 1 < left.getDejkstraValue()) {
                    left.setDejkstraValue(cell.getDejkstraValue() + 1);
                    processDejkstra(left, cell);
                } else {
                    left.setDejkstraValue(cell.getDejkstraValue() + 1);
                    processDejkstra(left, cell);

                }
            } else {
                if (left.getDejkstraValue() > cell.getDejkstraValue() + matrix.getN()) {
                    left.setDejkstraValue(cell.getDejkstraValue() + matrix.getN());
                    processDejkstra(left, cell);
                }
            }

        }

        if (bottom != null && bottom != prev) {
            if (bottom.getCluster() != null) {
                if (cell.getCluster() == null && cell.getDejkstraValue() + 1 < bottom.getDejkstraValue()) {
                    bottom.setDejkstraValue(cell.getDejkstraValue() + 1);
                    processDejkstra(bottom, cell);
                } else {
                    bottom.setDejkstraValue(cell.getDejkstraValue() + 1);
                    processDejkstra(bottom, cell);

                }
            } else {
                if (bottom.getDejkstraValue() > cell.getDejkstraValue() + matrix.getN()) {
                    bottom.setDejkstraValue(cell.getDejkstraValue() + matrix.getN());
                    processDejkstra(bottom, cell);
                }
            }
        }
    }

    public void setupDejkstra() throws CloneNotSupportedException {
        if (ways != null)
            ways.clear();
        for (int i = 0; i < matrix.getN(); i++) {
            setDejkstraValueToCell();
            matrix.getCellValue(i, 0).setDejkstraValue(0);
            processDejkstra(matrix.getCellValue(i, 0), null);
            findWay();
        }
        findShortestWay();
        calculateWidthWay();
    }

    private void calculateWidthWay() {
        int min = Integer.MAX_VALUE / 2;
        int max = 0;
        for (int i = 0; i < waysShortest.size(); i++) {
            for (Cell cell : waysShortest.get(i).getWayArray()
            ) {

                if (cell.getX() > max)
                    max = cell.getX();
                if (cell.getX() < min)
                    min = cell.getX();

            }
            waysShortest.get(i).setWidthWay(max - min);
        }
    }

    private void findShortestWay() {
        int min = Integer.MAX_VALUE / 2;
        Way way = null;
        for (Way way1 : ways) {
            if (way1.getLengthWay() < min) {
                min = way1.getLengthWay();
                way = way1;
            }
        }
        this.shortestWay = way;
        waysShortest.add(way);
    }

    public Way getShortestWay() {
        return shortestWay;
    }

    public void findWay() throws CloneNotSupportedException {
        flag = false;
        Way way = new Way();
        way.init();
        Cell cell = null;
        way.setMatrix(matrix);
        int min = Integer.MAX_VALUE / 2;
        for (int i = 0; i < matrix.getN(); i++) {
            if (min > matrix.getCellValue(i, matrix.getN() - 1).getDejkstraValue()) {
                min = matrix.getCellValue(i, matrix.getN() - 1).getDejkstraValue();
                cell = matrix.getCellValue(i, matrix.getN() - 1);
            }
        }
        way.setLengthWay(min);
        way.addCell(cell);
        rec(cell, way);

        sizeHole(way);
    }

    private void rec(Cell cell, Way way) throws CloneNotSupportedException {
        int min = Integer.MAX_VALUE / 2;
        if (flag)
            return;
        while (cell.getDejkstraValue() != 0 && !flag) {
            Cell left = this.matrix.getCellValue(cell.getX() - 1, cell.getY());
            Cell right = this.matrix.getCellValue(cell.getX() + 1, cell.getY());
            Cell top = this.matrix.getCellValue(cell.getX(), cell.getY() - 1);


            if (left != null && left.getDejkstraValue() < min) {
                min = left.getDejkstraValue();
                cell = left;
            }
            if (right != null && right.getDejkstraValue() < min) {
                min = right.getDejkstraValue();
                cell = right;
            }
            if (top != null && top.getDejkstraValue() < min) {
                min = top.getDejkstraValue();
                cell = top;
            }
            if (min == Integer.MAX_VALUE / 2) {
                break;
            }
            if (left != null && right != null && left.getDejkstraValue() == right.getDejkstraValue() && left.getDejkstraValue() == min) {
                way.addCell(cell);
                rec(left, way.clone());
                rec(right, way.clone());
            }
            if (left != null && top != null && left.getDejkstraValue() == top.getDejkstraValue() && top.getDejkstraValue() == min) {
                way.addCell(cell);
                rec(left, way.clone());
                rec(top, way.clone());
            }
            if (top != null && right != null && top.getDejkstraValue() == right.getDejkstraValue() && right.getDejkstraValue() == min) {
                way.addCell(cell);
                rec(top, way.clone());
                rec(right, way.clone());
            }

            if (way.getWayArray().contains(cell))
                break;

            if (cell.getCluster() == null)
                way.setRedCell(way.getRedCell() + 1);
             if (!flag)
                way.addCell(cell);

        }
        if (!flag)
            ways.add(way);
        flag = true;
    }

    private void sizeHole(Way way) {
        int max = 0;
        for (int i = 0; i < way.getWayArray().size(); i++) {
            int count = 0;
            while (i < way.getWayArray().size() && matrix.getCellValue(way.getWayArray().get(i).getX(), way.getWayArray().get(i).getY()).getCluster() == null) {
                count++;
                i++;
            }
            if (count > max)
                max = count;
        }
        way.setSizeHole(max - 1);
    }

    public WayLighningStatistic getWayStatistics() {
        WayLighningStatistic wayLighningStatistic = new WayLighningStatistic();
        wayLighningStatistic.setN(matrix.getN());
        wayLighningStatistic.setP(matrix.getP());
        wayLighningStatistic.setWay(waysShortest);
        wayLighningStatistic.setAverageWay(getAverageWay());
        return wayLighningStatistic;
    }

    private double getAverageWay() {
        double average = 0;
        for (Way way : waysShortest) {
            average += way.getLengthWay();
        }
        return (average / waysShortest.size());
    }
}

