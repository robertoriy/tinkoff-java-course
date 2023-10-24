package edu.hw2.task3.probability;

import java.util.Random;

public class RandomProbabilityChecker implements ProbabilityChecker {
    private final double probabilityOfEvent;
    private final Random random = new Random();

    public RandomProbabilityChecker(double probabilityOfEvent) {
        this.probabilityOfEvent = probabilityOfEvent;
    }

    @Override
    public boolean hasEventOccurred() {
        return random.nextDouble() < probabilityOfEvent;
    }
}
