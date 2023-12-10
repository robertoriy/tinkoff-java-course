package edu.project4.model;

public final class Pixel {
    private int red;
    private int green;
    private int blue;
    private int hitCount;
    private double normal;

    private Pixel(int red, int green, int blue, int hitCount, double normal) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.hitCount = hitCount;
        this.normal = normal;
    }

    public static Pixel create(int red, int green, int blue, int hitCount, double normal) {
        return new Pixel(red, green, blue, hitCount, normal);
    }

    public int red() {
        return red;
    }

    public void red(int value) {
        red = value;
    }

    public int green() {
        return green;
    }

    public void green(int value) {
        green = value;
    }

    public int blue() {
        return blue;
    }

    public void blue(int value) {
        blue = value;
    }

    public int hitCount() {
        return hitCount;
    }

    public void hitCount(int value) {
        hitCount = value;
    }

    public double normal() {
        return normal;
    }

    public void normal(double value) {
        normal = value;
    }
}
