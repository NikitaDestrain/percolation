package com.percolation.detection;

import com.percolation.domain.matrix.Cell;
import com.percolation.domain.matrix.Cluster;
import com.percolation.domain.matrix.Matrix;

/**
 * @author Nikita Govokhin
 */
public class ClusterDetection {

    private static ClusterDetection instance;
    private int k;

    private ClusterDetection() {
        this.k = 0;
    }

    public static ClusterDetection getInstance() {
        if (instance == null) instance = new ClusterDetection();
        return instance;
    }

    private void mergeClusters(Matrix matrix, Cluster sharedCluster, Cluster absorbedCluster) {
        int size = matrix.getN();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                Cell curValue = matrix.getCellValue(x, y);
                if (curValue != null) {
                    if (curValue.getCluster() == absorbedCluster) {
                        curValue.setCluster(sharedCluster);
                    }
                }
            }
        }

        // compute new cluster size
        sharedCluster.setSize(sharedCluster.getSize() + absorbedCluster.getSize());
        matrix.removeCluster(absorbedCluster);
    }

    public Cluster calculateCluster(Matrix matrix, int x, int y) {
        Cluster result = null;

        Cell experimental = matrix.getCellValue(x, y);
        if (experimental.isValue()) {
            Cell neighbourLeft = null;
            Cell neighbourUpper = null;
            if (y != 0) {
                neighbourUpper = matrix.getCellValue(x, y - 1);
            }
            if (x != 0) {
                neighbourLeft = matrix.getCellValue(x - 1, y);
            }

            Cluster neighbourUpperCluster = null;
            Cluster neighbourLeftCluster = null;

            if (neighbourUpper != null) {
                neighbourUpperCluster = neighbourUpper.getCluster();
            }

            if (neighbourLeft != null) {
                neighbourLeftCluster = neighbourLeft.getCluster();
            }

            // case: neighbours do not have cluster
            if ((neighbourLeftCluster == null) && (neighbourUpperCluster == null)) {
                result = new Cluster();
                matrix.addCluster(result);
                k++;
                result.setId(k);
                result.setSize(1);
            }

            // case: upper neighbour has cluster
            if ((neighbourLeftCluster == null) && (neighbourUpperCluster != null)) {
                result = neighbourUpperCluster;
                neighbourUpperCluster.setSize(neighbourUpperCluster.getSize() + 1);
            }

            // case: left neighbour has cluster
            if ((neighbourLeftCluster != null) && (neighbourUpperCluster == null)) {
                result = neighbourLeftCluster;
                neighbourLeftCluster.setSize(neighbourLeftCluster.getSize() + 1);
            }

            // case: both have cluster
            if ((neighbourLeftCluster != null) && (neighbourUpperCluster != null)) {
                if (neighbourLeftCluster != neighbourUpperCluster) {
                    mergeClusters(matrix, neighbourUpperCluster, neighbourLeftCluster);
                }
                result = neighbourUpperCluster;
                neighbourUpperCluster.setSize(neighbourUpperCluster.getSize() + 1);
            }
        }

        return result;
    }

    public void refreshClusterCounter() {
        this.k = 0;
    }
}
