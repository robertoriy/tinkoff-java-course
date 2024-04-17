package edu.hw8.task2;

import java.util.ArrayList;
import java.util.List;

public final class FibonacciApplication {
    private FibonacciApplication() {
    }

    public static List<Integer> compute(int poolSize, List<Integer> values) {
        List<Integer> results = new ArrayList<>(values);

        try (ThreadPool threadPool = FixedThreadPool.of(poolSize)) {
            threadPool.start();
            for (int i = 0; i < values.size(); i++) {
                int current = i;
                threadPool.execute(() -> results.set(current, compute(values.get(current))));
            }
        }
        return results;
    }

    public static int compute(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return compute(n - 1) + compute(n - 2);
    }
}
