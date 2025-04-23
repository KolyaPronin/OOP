package ru.nsu.pronin.data;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The FieldData class represents the field,
 * apples, walls, and other game settings
 * for a simple game. This class provides methods to manage the game field,
 * generate and handle apples, and configure gameplay attributes like levels
 * and the ability to pass through walls.
 */
public final class FieldData {
    /** Width of the game field. */
    private static final int SIZE_X = 600;

    /** Height of the game field. */
    private static final int SIZE_Y = 600;

    /** List of wall positions on the game field. */
    private static final List<Point2D> WALLS = new ArrayList<>();

    /** List of apple positions on the game field. */
    private static final List<Point> APPLE =
            new ArrayList<>(List.of(new Point(200, 100)));

    /** Current level number in the game. */
    private static int numberOfLevel = 1;

    /** Indicates if passing through walls is allowed. */
    private static boolean abilityPassThroughWalls = false;

    /** Represents the first game level. */
    private static final int LEVEL_ONE = 1;

    /** Represents the second game level. */
    private static final int LEVEL_TWO = 2;

    /** Represents the third game level. */
    private static final int LEVEL_THREE = 3;

    /** Represents the fourth game level. */
    private static final int LEVEL_FOUR = 4;

    /** Represents the fifth game level. */
    private static final int LEVEL_FIVE = 5;

    /** Represents the classic game level. */
    private static final int LEVEL_CLASSIC = 6;

    /** Maximum number of cells along the x-axis and y-axis. */
    private static final int GRID_SIZE = 60;

    /** Size of each cell in the grid, in pixels. */
    private static final int CELL_SIZE = 10;
    /**
     * Private constructor to prevent instantiation of the utility class.
     */
    private FieldData() {
        throw new UnsupportedOperationException(
                "Utility class cannot be instantiated");
    }

    /**
     * Initializes the game field by setting up walls
     * and configuration based on the current level.
     * <p>
     * The method checks the current `numberOfLevel`
     * to determine which level configuration
     * to activate, corresponding to predefined
     * setups in the `Levels` class. Each level
     * configuration initializes the walls and
     * other gameplay elements specific to that level.
     * <p>
     * Levels handled:
     * - Level 1: Triggers `Levels.levelOne()`.
     * - Level 2: Triggers `Levels.levelTwo()`.
     * - Level 3: Triggers `Levels.levelThree()`.
     * - Level 4: Triggers `Levels.levelFour()`.
     * - Level 5: Triggers `Levels.levelFive()`.
     * - Level 6: Triggers `Levels.classicLevel()`.
     */
    public static void field() {
        if (numberOfLevel == LEVEL_ONE) {
            Levels.levelOne();
        } else if (numberOfLevel == LEVEL_TWO) {
            Levels.levelTwo();
        } else if (numberOfLevel == LEVEL_THREE) {
            Levels.levelThree();
        } else if (numberOfLevel == LEVEL_FOUR) {
            Levels.levelFour();
        } else if (numberOfLevel == LEVEL_FIVE) {
            Levels.levelFive();
        } else if (numberOfLevel == LEVEL_CLASSIC) {
            Levels.classicLevel();
        }
    }

    /**
     * Updates the current level of the game.
     *
     * @param num the level number to set for the game,
     *           where each number corresponds
     *            to a specific game configuration or layout.
     */
    public static void setLevel(final int num) {
        numberOfLevel = num;
    }

    /**
     * Retrieves the current game level.
     *
     * @return the current level number of the game
     */
    public static int getNumberOfLevel() {
        return numberOfLevel;
    }

    /**
     * Retrieves the width of the game field.
     *
     * @return the width of the game field as an integer
     */
    public static int getSizeX() {
        return SIZE_X;
    }

    /**
     * Retrieves the height of the game field.
     *
     * @return the height of the game field as an integer
     */
    public static int getSizeY() {
        return SIZE_Y;
    }

    /**
     * Retrieves the list of wall coordinates on the current game field.
     *
     * @return a List of Point2D objects representing
     * the positions of the walls on the game field
     */
    public static List<Point2D> getListWalls() {
        return WALLS;
    }

    /**
     * Retrieves the current list of apple positions on the game field.
     *
     * @return a List of Point objects representing the
     * positions of apples on the game field
     */
    public static List<Point> getApple() {
        return APPLE; }

    /**
     * Adds a new apple to the game field while ensuring that its position
     * does not overlap with any existing walls.
     * <p>
     * The method generates a new random position for the apple and checks
     * whether the generated position overlaps with any wall coordinates.
     * If the position overlaps, a new position is generated repeatedly until
     * a valid position is found. The valid apple position is then added
     * to the list of apple positions.
     * <p>
     * The apple's position is generated using {@link #generateNewApple()}, and
     * the list of wall positions is retrieved using {@link #getListWalls()}.
     */
    public static void addNewAppleToField() {
        Point newApple;
        boolean isOnWall;

        do {
            newApple = generateNewApple();
            isOnWall = false;

            for (Point2D wall : getListWalls()) {
                if (newApple.x == wall.getX() && newApple.y == wall.getY()) {
                    isOnWall = true;
                    break;
                }
            }
        } while (isOnWall);

        APPLE.add(newApple);
    }

    /**
     * Generates a new random position
     * for an apple within the game field boundaries.
     * The X and Y coordinates of the
     * generated position are multiples of 10, with
     * values ranging between 0 and 590.
     *
     * @return a Point object representing
     * the randomly generated position for the apple
     */
    public static Point generateNewApple() {
        return new Point(new Random().nextInt(GRID_SIZE) * CELL_SIZE,
                new Random().nextInt(GRID_SIZE) * CELL_SIZE);

    }

    /**
     * Removes the first apple from the list of apples on the game field.
     * <p>
     * This method is typically invoked
     * when an apple has been consumed by the snake
     * or needs to be replaced. It removes
     * the apple at index 0 from the list of apple
     * positions maintained by the game.
     * <p>
     * It is important to ensure that the
     * list of apples is not empty when this method is
     * called, as attempting to remove an
     * element from an empty list will result in an error.
     */
    public static void removeApple() {
        APPLE.removeFirst();
    }

    /**
     * Checks if the ability to pass through walls is enabled in the game field.
     *
     * @return {@code true} if the snake
     * can pass through walls, otherwise {@code false}.
     */
    public static boolean isAbilityPassThroughWalls() {
        return abilityPassThroughWalls;
    }

    /**
     * Sets the ability for the snake to pass through walls during the game.
     *
     * @param canPassThroughWalls
     * a boolean value indicating whether the snake
     *                                can pass through walls.
     *                                If {@code true}, the
     *                                snake is allowed to move across the field
     *                                boundaries without being obstructed;
     *                                otherwise, it cannot.
     */
    public static void setAbilityPassThroughWalls(
            final boolean canPassThroughWalls) {
        FieldData.abilityPassThroughWalls = canPassThroughWalls;
    }
}
