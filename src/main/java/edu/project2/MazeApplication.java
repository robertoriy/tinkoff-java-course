package edu.project2;

import edu.project2.generator.Generator;
import edu.project2.generator.PrimGenerator;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import edu.project2.renderer.Renderer;
import edu.project2.renderer.StringRenderer;
import edu.project2.solver.DepthFirstSearchSolver;
import edu.project2.solver.Solver;
import edu.project2.view.ConsolePrinter;
import edu.project2.view.Printer;
import java.util.List;
import java.util.Scanner;

public final class MazeApplication implements Runnable {
    private final Generator generator = new PrimGenerator();
    private final Solver solver = new DepthFirstSearchSolver();
    private final Renderer renderer = new StringRenderer();
    private final Printer printer = new ConsolePrinter();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        Maze maze = generateMaze();

        printer.print("Your maze!");
        printer.print(renderer.render(maze));

        List<Coordinate> path = findPath(maze);

        printer.print("Your path!");
        printer.print(renderer.render(maze, path));

        scanner.close();
    }

    private Maze generateMaze() {
        Maze maze = null;
        boolean notScanned = true;
        while (notScanned) {
            Dimensions dimensions = scanDimensions();
            try {
                maze = generator.generate(dimensions.height, dimensions.width);
                notScanned = false;
            } catch (IllegalArgumentException e) {
                printer.print("Invalid height/width. Try again!");
            }
        }
        return maze;
    }

    private List<Coordinate> findPath(Maze maze) {
        List<Coordinate> path = null;
        boolean notScanned = true;
        while (notScanned) {
            try {
                Coordinate start = scanCoordinate("start");
                Coordinate end = scanCoordinate("end");

                path = solver.solve(maze, start, end);
                notScanned = false;
            } catch (IllegalArgumentException e) {
                printer.print("Invalid coordinates. Try again!");
            }
        }
        return path;
    }

    private Dimensions scanDimensions() {
        printer.print("Enter height and width <height width>");
        int height = scanner.nextInt();
        int width = scanner.nextInt();
        printer.print(getMessageWithScannedValues(height, width));

        return new Dimensions(height, width);
    }

    private Coordinate scanCoordinate(String coordinateType) {
        printer.print("Enter " + coordinateType + " coordinate <row column>");
        int row = scanner.nextInt();
        int column = scanner.nextInt();
        printer.print(getMessageWithScannedValues(row, column));

        return new Coordinate(row, column);
    }

    private String getMessageWithScannedValues(int... scanned) {
        StringBuilder message = new StringBuilder("You entered:");
        for (int scannedValue : scanned) {
            message.append(" ").append(scannedValue);
        }
        return message.toString();
    }

    private record Dimensions(int height, int width) {
    }
}
