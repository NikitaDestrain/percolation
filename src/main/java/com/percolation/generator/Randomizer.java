package com.percolation.generator;

import java.util.Random;

/**
 * @author Nikita Govokhin
 */
class Randomizer {

    private Random worker;
    private static Randomizer instance;

    private Randomizer() {
        this.worker = new Random(System.currentTimeMillis());
    }

    static Randomizer getInstance() {
        if (instance == null) instance = new Randomizer();
        return instance;
    }

    void refreshSeed() {
        worker = new Random(System.currentTimeMillis());
    }

    double getRandomValue() {
        return GeneratorConstants.RANGE_MIN + (GeneratorConstants.RANGE_MAX - GeneratorConstants.RANGE_MIN) * worker.nextDouble();
    }
}
