package edu.project2.view;

public class ConsolePrinter implements Printer {
    @SuppressWarnings("checkstyle:RegexpSinglelineJava")
    @Override
    public void print(String input) {
        System.out.println(input);
    }
}
