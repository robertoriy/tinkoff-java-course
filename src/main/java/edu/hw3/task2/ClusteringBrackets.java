package edu.hw3.task2;

import java.util.ArrayList;
import java.util.List;

public final class ClusteringBrackets {
    private static final char DEFAULT_OPEN_SYMBOL = '(';
    private static final char DEFAULT_CLOSE_SYMBOL = ')';
    private final char openSymbol;
    private final char closeSymbol;

    public ClusteringBrackets() {
        this(DEFAULT_OPEN_SYMBOL, DEFAULT_CLOSE_SYMBOL);
    }

    public ClusteringBrackets(char openSymbol, char closeSymbol) {
        this.openSymbol = openSymbol;
        this.closeSymbol = closeSymbol;
    }

    public List<String> clusterize(String brackets) {
        validateNotEmpty(brackets);

        List<String> clusters = new ArrayList<>();

        StringBuilder currentCluster = new StringBuilder();
        int count = 0;

        for (char symbol : brackets.toCharArray()) {
            validateSymbol(symbol);
            if (symbol == openSymbol) {
                count++;
            } else if (symbol == closeSymbol) {
                count--;
            }
            validateOrder(count);

            currentCluster.append(symbol);

            if (count == 0) {
                clusters.add(currentCluster.toString());
                currentCluster.setLength(0);
            }
        }
        validateNumberOfSymbols(count);

        return clusters;
    }

    private void validateNotEmpty(String brackets) {
        if (brackets == null || brackets.isBlank()) {
            throw new IllegalArgumentException("Input must be not null and not empty");
        }
    }

    private void validateSymbol(char symbol) {
        if (symbol != openSymbol && symbol != closeSymbol) {
            throw new IllegalArgumentException("Input must contain only open/close symbols");
        }
    }

    private void validateOrder(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Wrong order of open/close symbols");
        }
    }

    private void validateNumberOfSymbols(int count) {
        if (count != 0) {
            throw new IllegalArgumentException("Number of open/close symbols must be the same");
        }
    }
}
