package edu.hw3.task6;

import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SpecialStockMarket implements StockMarket {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Queue<Stock> queue = new PriorityQueue<>();

    @Override
    public void add(Stock stock) {
        validateNotNull(stock);

        LOGGER.info("Add - {}", stock);

        queue.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        validateNotNull(stock);

        LOGGER.info("Removing - {}", stock);

        if (queue.remove(stock)) {
            LOGGER.info("Removed successfully");
        }
    }

    @Override
    public Stock mostValuableStock() {
        if (queue.isEmpty()) {
            throw new NoSuchElementException();
        }
        Stock stock = queue.peek();
        LOGGER.info("Most valuable stock - {}", stock);
        return stock;
    }

    private void validateNotNull(Stock stock) {
        if (stock == null) {
            throw new IllegalArgumentException("Must be not null");
        }
    }
}
