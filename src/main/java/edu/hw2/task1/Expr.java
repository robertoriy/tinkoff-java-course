package edu.hw2.task1;

public sealed interface Expr {
    double evaluate();

    public record Constant(int number) implements Expr {
        @Override
        public double evaluate() {
            return 0;
        }
    }
    public record Negate(Expr expression) implements Expr {
        @Override
        public double evaluate() {
            return 0;
        }
    }
    public record Exponent(Expr base, int exponent) implements Expr {
        @Override
        public double evaluate() {
            return 0;
        }
    }
    public record Addition(Expr first, Expr second) implements Expr {
        @Override
        public double evaluate() {
            return 0;
        }
    }
    public record Multiplication(Expr first, Expr second) implements Expr {
        @Override
        public double evaluate() {
            return 0;
        }
    }
}
