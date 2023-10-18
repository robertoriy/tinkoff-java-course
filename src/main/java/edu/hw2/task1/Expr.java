package edu.hw2.task1;

public sealed interface Expr {
    double evaluate();

    record Constant(double number) implements Expr {
        @Override
        public double evaluate() {
            return number;
        }
    }

    record Negate(Expr expression) implements Expr {
        @Override
        public double evaluate() {
            return -expression.evaluate();
        }
    }

    record Addition(Expr first, Expr second) implements Expr {
        @Override
        public double evaluate() {
            return first.evaluate() + second.evaluate();
        }
    }

    record Multiplication(Expr first, Expr second) implements Expr {
        @Override
        public double evaluate() {
            return first.evaluate() * second.evaluate();
        }
    }

    record Exponent(Expr base, int power) implements Expr {
        @Override
        public double evaluate() {
            return Math.pow(base.evaluate(), power);
        }
    }
}
