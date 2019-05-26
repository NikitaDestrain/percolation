package com.percolation.detection;

import com.percolation.domain.Way;
import com.percolation.domain.matrix.Cell;
import com.percolation.domain.matrix.Matrix;
import com.percolation.domain.statistic.WayLighningStatistic;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author Dmitryi Vasilev
 */
public class DejkstraDetection {
    private static DejkstraDetection instance;

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
        // WA with for loops, cuz we have additional lines and columns for correct statistic
        for (int y = 1; y < matrix.getN(); y++) {
            Cell[] cell = matrix.getLine(y);
            for (int i = 1; i < cell.length - 1; i++) {
                Cell cel = cell[i];
                if (cel.getCluster() != null)
                    cel.setDejkstraValue(1);
                else
                    cel.setDejkstraValue(matrix.getN() * matrix.getN() + 1);
                cel.setDist(Integer.MAX_VALUE);
                cel.setVisited(false);
            }
        }
    }

//    public void processDejkstra(Cell cell, Cell prev) {
//        Cell left = this.matrix.getCellValue(cell.getX() - 1, cell.getY());
//        Cell right = this.matrix.getCellValue(cell.getX() + 1, cell.getY());
//        Cell bottom = this.matrix.getCellValue(cell.getX(), cell.getY() + 1);
////        System.out.println(cell.getX() + "  " +cell.getY());
////        try {
////            Thread.sleep(500);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
//        if (right != null && right != prev) {
//
//            if (right.getCluster() != null) {
//                if (cell.getCluster() == null && cell.getDejkstraValue() + 1 < right.getDejkstraValue()) {
//                    right.setDejkstraValue(cell.getDejkstraValue() + 1);
//                    processDejkstra(right, cell);
//
//                } else {
//                    right.setDejkstraValue(cell.getDejkstraValue() + 1);
//                    processDejkstra(right, cell);
//                }
//            } else {
//                if (right.getDejkstraValue() > cell.getDejkstraValue() + matrix.getN()) {
//                    right.setDejkstraValue(cell.getDejkstraValue() + matrix.getN());
//                    processDejkstra(right, cell);
//                }
//            }
//        }
//
//        if (left != null && left != prev) {
//            if (left.getCluster() != null) {
//                if (cell.getCluster() == null && cell.getDejkstraValue() + 1 < left.getDejkstraValue()) {
//                    left.setDejkstraValue(cell.getDejkstraValue() + 1);
//                    processDejkstra(left, cell);
//                } else {
//                    left.setDejkstraValue(cell.getDejkstraValue() + 1);
//                    processDejkstra(left, cell);
//
//                }
//            } else {
//                if (left.getDejkstraValue() > cell.getDejkstraValue() + matrix.getN()) {
//                    left.setDejkstraValue(cell.getDejkstraValue() + matrix.getN());
//                    processDejkstra(left, cell);
//                }
//            }
//
//        }
//
//        if (bottom != null && bottom != prev) {
//            if (bottom.getCluster() != null) {
//                if (cell.getCluster() == null && cell.getDejkstraValue() + 1 < bottom.getDejkstraValue()) {
//                    bottom.setDejkstraValue(cell.getDejkstraValue() + 1);
//                    processDejkstra(bottom, cell);
//                } else {
//                    bottom.setDejkstraValue(cell.getDejkstraValue() + 1);
//                    processDejkstra(bottom, cell);
//
//                }
//            } else {
//                if (bottom.getDejkstraValue() > cell.getDejkstraValue() + matrix.getN()) {
//                    bottom.setDejkstraValue(cell.getDejkstraValue() + matrix.getN());
//                    processDejkstra(bottom, cell);
//                }
//            }
//        }
//    }

    public void processDejkstra(Cell currentCell) {
        PriorityQueue<Cell> queue = new PriorityQueue<>();
        queue.add(currentCell);
        currentCell.setVisited(true);
        while (!queue.isEmpty()) {
            Cell actualCell = queue.poll();
            Cell left = this.matrix.getCellValue(actualCell.getX() - 1, actualCell.getY());
            Cell right = this.matrix.getCellValue(actualCell.getX() + 1, actualCell.getY());
            Cell bottom = this.matrix.getCellValue(actualCell.getX(), actualCell.getY() + 1);
            ArrayList<Cell> list = new ArrayList<>();
            if (left != null)
                list.add(left);
            if (right != null)
                list.add(right);
            if (bottom != null)
                list.add(bottom);
            for (Cell cell : list) {
                if (!cell.isVisited()) {
                    double newDist = actualCell.getDist() + cell.getDejkstraValue();
                    if (newDist < cell.getDist()) {
                        queue.remove(actualCell);
                        //  cell.setDejkstraValue((int) newDist);
                        cell.setDist((int) newDist);
                        queue.add(cell);
                    }
                } else {
                    if (actualCell.getDist() + cell.getDejkstraValue() < cell.getDist()) {
                        queue.remove(actualCell);
                        //  cell.setDejkstraValue((int) newDist);
                        cell.setDist((int) actualCell.getDist() + cell.getDejkstraValue());
                        queue.add(cell);
                    }
                }
            }
            actualCell.setVisited(true);
        }

    }

    public void clear() {
        waysShortest.clear();
    }

    public void setupDejkstra() throws CloneNotSupportedException {
        if (ways != null)
            ways.clear();
        for (int i = 0; i < matrix.getN(); i++) {
            setDejkstraValueToCell();
            matrix.getCellValue(i, 0).setDejkstraValue(0);
            matrix.getCellValue(i, 0).setDist(0);
            processDejkstra(matrix.getCellValue(i, 0));
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
        for (Cell cell : way.getWayArray()) {
            if (cell.getCluster() == null)
                way.setRedCell(way.getRedCell() + 1);
        }
        way.setSizeWay(way.getWayArray().size());
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
            if (min > matrix.getCellValue(i, matrix.getN() - 1).getDist()) {
                min = matrix.getCellValue(i, matrix.getN() - 1).getDist();
                cell = matrix.getCellValue(i, matrix.getN() - 1);
            }
        }
        way.setLengthWay(min);
        way.addCell(cell);
        while (cell.getY() != 0) {
            Cell left = this.matrix.getCellValue(cell.getX() - 1, cell.getY());
            Cell right = this.matrix.getCellValue(cell.getX() + 1, cell.getY());
            Cell top = this.matrix.getCellValue(cell.getX(), cell.getY() - 1);
            if (left != null && left.getDist() < min) {
                min = left.getDist();
                cell = left;
            }
            if (right != null && right.getDist() < min) {
                min = right.getDist();
                cell = right;
            }
            if (top != null && top.getDist() < min) {
                min = top.getDist();
                cell = top;
            }
            way.addCell(cell);

        }

        if (way.getWayArray().get(way.getWayArray().size() - 1).getCluster() == null) {
            way.setLengthWay(way.getLengthWay() + way.getMatrix().getN() * way.getMatrix().getN() + 1);
        }
        ways.add(way);

        sizeHole(way);
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

