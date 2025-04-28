package ru.nsu.pronin.data;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The SnakeData class manages the data and state of the snake in the game.
 * It includes functionality for adding
 * and removing points from the snake's body,
 * tracking the snake's state, and managing game-specific parameters like the
 * number of points required for victory.
 */
public class SnakeData {
    /**
     * The starting point of the snake's head.
     */
    private static final List<Point> HEAD_DEFAULT_POSITION
            = Arrays.asList(new Point(100, 100));

    /**
     * List of points representing the snake's body (starts from the head).
     */
    private static final List<Point> snakeList
            = new ArrayList<Point>(HEAD_DEFAULT_POSITION);

    /**
     * Current state of the snake's direction:
     * 0 - left to right,
     * 1 - top to bottom,
     * 2 - right to left,
     * 3 - bottom to top.
     */
    private static int state = 0;

    /**
     * Indicates whether the snake's direction has changed in the current tick.
     */
    private static boolean directionChangedThisTick = false;

    /**
     * The number of points required to win the game.
     */
    private static int numberOfPointForVictory = 10;

    /**
     * Adds a new point to the list representing
     * the snake's body at the specified coordinates.
     *
     * @param x the x-coordinate of the new point to be added
     * @param y the y-coordinate of the new point to be added
     */
    public void addPointToList(final int x, final int y) {
        snakeList.add(new Point(x, y));
    }


    /**
     * Removes the last point from the snake's body list,
     * effectively reducing its size by one.
     * This is used during movement to simulate the snake progressing forward
     * by removing the tail after adding a new head.
     */
    public static void deletePointFromList() {
        snakeList.remove(snakeList.size() - 1);
    }

    /**
     * Checks whether the snake's direction
     * has changed during the current game tick.
     *
     * @return {@code true} if the direction
     * was changed this tick, {@code false} otherwise
     */
    public static boolean isDirectionChangedThisTick() {
        return directionChangedThisTick;
    }

    /**
     * Sets the flag indicating whether the snake's direction
     * has changed during the current game tick.
     * This method is typically used to prevent multiple
     * direction changes within a single tick.
     *
     * @param value {@code true} to indicate that
     *                         the direction has been changed this tick,
     *              {@code false} otherwise
     */
    public static void setDirectionChangedThisTick(final boolean value) {
        directionChangedThisTick = value;
    }


    /**
     * Retrieves the current state of the snake,
     * indicating its direction of movement.
     * The state is represented as an integer:
     * - 0: Moving left to right
     * - 1: Moving top to bottom
     * - 2: Moving right to left
     * - 3: Moving bottom to top
     *
     * @return the current state of the snake's movement
     */
    public static int getState() {
        return state;
    }

    /**
     * Sets the current state of the snake,
     * indicating its direction of movement.
     * The state is represented as an integer:
     * - 0: Moving left to right
     * - 1: Moving top to bottom
     * - 2: Moving right to left
     * - 3: Moving bottom to top
     *
     * @param stateDirection the direction of the snake's movement,
     *             represented as an integer
     */
    public static void setState(final int stateDirection) {
        SnakeData.state = stateDirection;
    }

    /**
     * Retrieves the list of points that represent the snake's body.
     *
     * @return a list of {@link Point} objects representing the snake's body,
     *         where each point corresponds to a segment of the snake.
     */
    public static List<Point> getSnakeList() {
        return snakeList;
    }

    /**
     * Sets the number of points required for victory in the game.
     *
     * @param number the number of points needed to achieve victory
     */
    public static void setNumberOfPointForVictory(final int number) {
        numberOfPointForVictory = number;
    }

    /**
     * Retrieves the number of points required to achieve victory in the game.
     *
     * @return the number of points needed to win the game
     */
    public static int getNumberOfPointForVictory() {
        return numberOfPointForVictory;
    }
}
