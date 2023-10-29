package edu.hw3.task6;

public record Stock(String name, double price) implements Comparable<Stock> {
    @Override
    public int compareTo(Stock other) {
        return Double.compare(other.price(), this.price);
    }
}
