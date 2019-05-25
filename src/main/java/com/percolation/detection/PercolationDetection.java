package com.percolation.detection;

import com.percolation.domain.matrix.Cell;
import com.percolation.domain.matrix.Cluster;
import com.percolation.domain.matrix.Matrix;

import java.util.*;

/**
 * @author Nikita Govokhin
 */
public class PercolationDetection {

    private static PercolationDetection instance;

    private PercolationDetection() {
    }

    public static PercolationDetection getInstance() {
        if (instance == null) instance = new PercolationDetection();
        return instance;
    }

    // return clusters with percolation
    public Set<Cluster> detectPercolation(Matrix matrix) {
        Set<Cluster> res = new HashSet<>();
        int size = matrix.getN();
        Cell[] firstLine = matrix.getLine(0);
        Cell[] lastLine = matrix.getLine(size - 1);

        for (int i = 1; i < size + 1; i++) {
            Cluster cont = firstLine[i].getCluster();
            if (cont != null) {
                for (int j = 1; j < size + 1; j++) {
                    if (lastLine[j].getCluster() == cont) {
                        res.add(cont);
                        //System.out.println("[PERCOLATION DETECTED]: Matrix id = " + matrix.getId() + "; Cluster id = " + cont.getId());
                    }
                }
            }
        }
        if (res.size() != 0) {
            matrix.setContainPercolation(true);
        }
        return Collections.unmodifiableSet(res);
    }
}
