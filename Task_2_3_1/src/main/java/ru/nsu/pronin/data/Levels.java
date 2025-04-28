package ru.nsu.pronin.data;

import javafx.util.Duration;
import ru.nsu.pronin.gui.GameField;
import java.awt.Point;
import java.awt.geom.Point2D;


/**
 * The Levels class represents a collection of predefined levels for the game.
 * Each level configures wall positions, snake's initial state, game speed,
 * and other properties to create unique challenges or scenarios.
 */
public final class Levels {

    /**
     * Private constructor to prevent instantiation.
     */
    private Levels() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Initializes the first level of the game
     * by setting up the necessary walls, snake, and game parameters.
     */
    public static void levelOne() {
        FieldData.getListWalls().clear();
        GameField.getPause().setDuration(Duration.millis(150));
        SnakeData.setNumberOfPointForVictory(5);

        SnakeData.getSnakeList().clear();
        SnakeData.getSnakeList().add(new Point(100, 100));
        SnakeData.setState(0);

        for (int i = 1; i < 5; i++) {
            FieldData.getListWalls().add(new Point2D.Double(10 * i, 20));
            FieldData.getListWalls().add(new Point2D.Double(50, 60 + i * 10));
            FieldData.getListWalls().add(new Point2D.Double(220, 150 + i * 10));
            FieldData.getListWalls().add(new Point2D.Double(450, 450 + i * 10));
            FieldData.getListWalls().add(new Point2D.Double(
                    450 + ((i / 2) * 10), 450));
            FieldData.getListWalls().add(new Point2D.Double(
                    520 + ((i / 2) * 10), 120));
            FieldData.getListWalls().add(new Point2D.Double(520, 120 - i * 10));
            FieldData.getListWalls().add(new Point2D.Double(10 * i, 450));
            FieldData.getListWalls().add(new Point2D.Double(300 + i * 10, 550));
        }
    }

    /**
     * Configures the second level of the game by setting up walls,
     * snake position,
     * and other relevant game configurations.
     * <p>
     * This method clears all existing wall and snake data,
     * sets the snake's initial state,
     * establishes the victory condition by
     * defining the required number of points, and
     * generates wall structures specific
     * to the second level layout. The wall configuration
     * includes horizontal and vertical segments
     * as well as additional corner points.
     * <p>
     * Key behaviors:
     * - Clears existing walls using {@link FieldData#getListWalls}.
     * - Adjusts pause duration for gameplay using {@link GameField#getPause}.
     * - Sets the number of points required
     * to win using {@link SnakeData#setNumberOfPointForVictory}.
     * - Initializes the snake's starting
     * position using {@link SnakeData#getSnakeList}.
     * - Sets the initial state of the snake using {@link SnakeData#setState}.
     * - Populates the walls of the level
     * with specific placements using {@link FieldData#getListWalls}.
     * <p>
     * This method is typically invoked when the game
     * progresses to the second level,
     * ensuring a fresh and distinct gameplay experience.
     */
    public static void levelTwo() {
        FieldData.getListWalls().clear();
        GameField.getPause().setDuration(Duration.millis(100));
        SnakeData.setNumberOfPointForVictory(10);

        SnakeData.getSnakeList().clear();
        SnakeData.getSnakeList().add(new Point(100,100));
        SnakeData.setState(0);

        for (int i = 1; i < 48; i++) {
            FieldData.getListWalls().add(new Point2D.Double(60 + i * 10, 40));
            FieldData.getListWalls().add(new Point2D.Double(60 + i * 10, 530));
        }

        for (int i = 1; i < 35; i++) {
            FieldData.getListWalls().add(new Point2D.Double(120 + i * 10, 220));
            FieldData.getListWalls().add(new Point2D.Double(120 + i * 10, 340));
        }
        FieldData.getListWalls().add(new Point2D.Double(130, 230));
        FieldData.getListWalls().add(new Point2D.Double(460, 230));

        FieldData.getListWalls().add(new Point2D.Double(130, 330));
        FieldData.getListWalls().add(new Point2D.Double(460, 330));

        for (int i = 1; i < 5; i++) {
            FieldData.getListWalls().add(new Point2D.Double(60, 30 + i * 10));
            FieldData.getListWalls().add(new Point2D.Double(540, 30 + i * 10));

            FieldData.getListWalls().add(new Point2D.Double(60, 540 - i * 10));
            FieldData.getListWalls().add(new Point2D.Double(540, 540 - i * 10));
        }
    }

    /**
     * Configures the third level
     * of the game by setting up walls, snake position,
     * and other relevant game configurations.
     * <p>
     * This method prepares the game field
     * for the third level by clearing all
     * existing wall and snake data, setting the snake's initial state, defining
     * the victory condition as reaching a specified number of points, and
     * generating wall structures specific
     * to the third level layout in the shape
     * of a cross.
     * <p>
     * Key behaviors:
     * - Clears existing walls using {@link FieldData#getListWalls}.
     * - Adjusts pause duration for gameplay using {@link GameField#getPause}.
     * - Sets the number of points required to win using
     * {@link SnakeData#setNumberOfPointForVictory}.
     * - Initializes the snake's starting position using
     * {@link SnakeData#getSnakeList}.
     * - Sets up walls in a cross-like pattern using
     * {@link FieldData#getListWalls}.
     */
    public static void levelThree() {
        FieldData.getListWalls().clear();
        GameField.getPause().setDuration(Duration.millis(70));
        SnakeData.setNumberOfPointForVictory(15);

        SnakeData.getSnakeList().clear();
        SnakeData.getSnakeList().add(new Point(100, 100));
        SnakeData.setState(0);


        for (int i = 1; i < 30; i++) {
            FieldData.getListWalls().add(new Point2D.Double(290, 120 + i * 10));
            FieldData.getListWalls().add(new Point2D.Double(140 + i * 10, 280));
        }
    }

    /**
     * Configures the fourth level
     * of the game by setting up walls, the snake's position,
     * and other relevant game configurations.
     * <p>
     * This method initializes the game field for the fourth level by:
     * - Clearing all existing wall and snake data.
     * - Setting the snake's initial state.
     * - Defining the victory condition
     * as reaching a specified number of points.
     * - Establishing wall structures specific
     * to the fourth level layout using nested loops,
     *   creating complex wall patterns including vertical,
     *   horizontal, and corner segments.
     * <p>
     * Key behaviors:
     */
    public static void levelFour() {
        FieldData.getListWalls().clear();
        GameField.getPause().setDuration(Duration.millis(50));
        SnakeData.setNumberOfPointForVictory(20);
        SnakeData.getSnakeList().clear();
        SnakeData.getSnakeList().add(new Point(100, 100));
        SnakeData.setState(0);


        for (int i = 1; i < 40; i++) {
            FieldData.getListWalls().add(new Point2D.Double(70, 120 + i * 10));
            FieldData.getListWalls().add(new Point2D.Double(70 + i * 10, 130));
        }
        for (int i = 0; i < 30; i++) {
            FieldData.getListWalls().add(new Point2D.Double(470, 130 + i * 10));
            FieldData.getListWalls().add(new Point2D.Double(470 - i * 10, 420));

        }

        for (int i = 0; i < 20; i++) {
            FieldData.getListWalls().add(new Point2D.Double(170, 230 + i * 10));
            FieldData.getListWalls().add(new Point2D.Double(360 - i * 10, 230));
        }
        for (int i = 0; i < 10; i++) {
            FieldData.getListWalls().add(new Point2D.Double(360, 320 - i * 10));
        }
        for (int i = 0; i < 10; i++) {
            FieldData.getListWalls().add(
                    new Point2D.Double(360 - i * 10, 320));
        }
        for (int i = 0; i < 5; i++) {
            FieldData.getListWalls().add(new Point2D.Double(260, 320 - i * 10));
        }
    }

    /**
     * Configures the fifth level of the game
     * by setting up walls, the snake's position,
     * and other relevant game configurations.
     * <p>
     * This method prepares the game field for
     * the fifth level by clearing all existing
     * wall and snake data, setting the snake's
     * initial position and state, defining the
     * victory condition as reaching a specified
     * number of points, and enabling the snake's
     * ability to pass through walls.
     * It also adjusts the gameplay speed by modifying the
     * pause duration and constructs walls
     * in a defined layout with vertical and horizontal lines.
     *
     * Key behaviors:
     * - Clears existing wall data using {@link FieldData#getListWalls}.
     * - Clears the snake's data and initializes the starting position using
     * {@link SnakeData#getSnakeList}.
     * - Sets the snake's state using {@link SnakeData#setState}.
     * - Enables the snake to pass through walls using
     * {@link FieldData#setAbilityPassThroughWalls}.
     * - Adjusts the game's speed by setting pause duration using
     * {@link GameField#getPause}.
     * - Sets the number of points required to win the level using
     * {@link SnakeData#setNumberOfPointForVictory}.
     * - Constructs walls in a vertical and horizontal pattern using
     * {@link FieldData#getListWalls}.
     */
    public static void levelFive() {
        FieldData.getListWalls().clear();
        SnakeData.getSnakeList().clear();
        SnakeData.getSnakeList().add(new Point(100, 100));
        SnakeData.setState(0);
        FieldData.setAbilityPassThroughWalls(true);
        GameField.getPause().setDuration(Duration.millis(40));
        SnakeData.setNumberOfPointForVictory(25);

        for (int i = 0; i < 60; i++) {
            FieldData.getListWalls().add(new Point2D.Double(290, i * 10));
            FieldData.getListWalls().add(new Point2D.Double(i * 10, 280));
        }
    }

    /**
     * Configures the classic level of the game
     * by setting up the initial game state,
     * including walls, snake position, victory conditions, and game rules.
     *
     * This method performs the following operations:
     * - Clears all existing wall data using {@link FieldData#getListWalls}.
     * - Enables the ability for the snake to pass through walls using
     * {@link FieldData#setAbilityPassThroughWalls}.
     * - Sets the number of points required to win the classic level using
     * {@link SnakeData#setNumberOfPointForVictory}.
     * - Clears all existing snake data from {@link SnakeData#getSnakeList}.
     * - Initializes the snake's starting position by adding a single point to
     * {@link SnakeData#getSnakeList}.
     * - Sets the initial state of the snake using {@link SnakeData#setState}.
     *
     * This method is intended to be invoked when
     * the classic level of the game is initiated,
     * establishing a straightforward game configuration.
     */
    public static void classicLevel() {
        FieldData.getListWalls().clear();
        FieldData.setAbilityPassThroughWalls(true);
        SnakeData.setNumberOfPointForVictory(100);

        SnakeData.getSnakeList().clear();
        SnakeData.getSnakeList().add(new Point(100, 100));
        SnakeData.setState(0);
    }
}
