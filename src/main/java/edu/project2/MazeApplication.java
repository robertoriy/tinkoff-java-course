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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class MazeApplication implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Generator generator = new PrimGenerator();
    private final Solver solver = new DepthFirstSearchSolver();
    private final Renderer renderer = new StringRenderer();
    private final Printer printer = new ConsolePrinter();
    private final Scanner scanner = new Scanner(System.in);
    private Maze maze;
    private List<Coordinate> path;

    @Override
    public void run() {
        scanHeightWidthAndFillMaze();

        LOGGER.info("Your maze!");
        printer.print(renderer.render(maze));

        scanCoordinatesAndFindPath();

        LOGGER.info("Your path!");
        printer.print(renderer.render(maze, path));

        scanner.close();
    }

    private void scanHeightWidthAndFillMaze() {
        while (true) {
            LOGGER.info("Enter height and width <height width>");
            int height = scanner.nextInt();
            int width = scanner.nextInt();
            LOGGER.info("You entered: {} {}", height, width);
            try {
                maze = generator.generate(height, width);
            } catch (IllegalArgumentException e) {
                LOGGER.info("Invalid height/width. Try again!");
                continue;
            }
            break;
        }
    }

    private void scanCoordinatesAndFindPath() {
        while (true) {
            LOGGER.info("Enter start and end coordinates <x1 y1 x2 y2>");
            int x1 = scanner.nextInt();
            int y1 = scanner.nextInt();
            int x2 = scanner.nextInt();
            int y2 = scanner.nextInt();
            LOGGER.info("You entered: {} {} {} {}", x1, y1, x2, y2);

            try {
                path = solver.solve(maze, new Coordinate(x1, y1), new Coordinate(x2, y2));
            } catch (IllegalArgumentException e) {
                LOGGER.info("Invalid coordinates. Try again!");
                continue;
            }
            break;
        }
    }
}
