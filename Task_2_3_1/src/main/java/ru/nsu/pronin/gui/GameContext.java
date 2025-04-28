package ru.nsu.pronin.gui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.animation.PauseTransition;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Represents the context required for running the game,
 * containing references to various graphical components and other
 * objects used throughout the game logic.
 * <p>
 * This class bundles the critical game
 * objects into a single entity, providing
 * centralized access for managing game state,
 * rendering, and interaction.
 */
public class GameContext {
    /** Graphics context for rendering. */
    private GraphicsContext gc;

    /** Canvas for drawing. */
    private Canvas canvas;

    /** Pause transition for controlling game ticks. */
    private PauseTransition pause;

    /** Root node of the scene graph. */
    private Group root;

    /** Graphics context for drawing the snake. */
    private GraphicsContext snake;

    /** Graphics context for drawing the apple. */
    private GraphicsContext apple;

    /** Primary stage of the application. */
    private Stage stage;

    /** Text element for displaying the score. */
    private Text scoreText;

    /**
     * Constructs a new GameContext object.
     * This class serves as a container for various components and objects
     * required to manage the game state, graphical rendering and interactions.
     *
     * @param paramGc        The GraphicsContext for rendering game graphics.
     * @param paramCanvas    The Canvas on which the game is drawn.
     * @param paramPause     The PauseTransition used to control game timing.
     * @param paramRoot      The root Group containing
     *                      all visual elements of the game.
     * @param paramSnake     The GraphicsContext for rendering the snake.
     * @param paramApple     The GraphicsContext for rendering the apple.
     * @param paramStage     The primary Stage where the game is displayed.
     * @param paramScoreText The Text node displaying the player's score.
     */
    public GameContext(final GraphicsContext paramGc, final Canvas paramCanvas,
                       final PauseTransition paramPause,
                       final Group paramRoot, final GraphicsContext paramSnake,
                       final GraphicsContext paramApple, final Stage paramStage,
                       final Text paramScoreText) {
        this.gc = paramGc;
        this.canvas = paramCanvas;
        this.pause = paramPause;
        this.root = paramRoot;
        this.snake = paramSnake;
        this.apple = paramApple;
        this.stage = paramStage;
        this.scoreText = paramScoreText;
    }

    /**
     * Retrieves the GraphicsContext used for rendering the game graphics.
     *
     * @return the current GraphicsContext
     * instance associated with this GameContext.
     */
    public GraphicsContext getGc() {
        return gc;
    }

    /**
     * Sets the GraphicsContext used for rendering game graphics.
     *
     * @param graphicsContext The GraphicsContext
     *                       to be associated with this GameContext.
     */
    public void setGc(final GraphicsContext graphicsContext) {
        this.gc = graphicsContext;
    }

    /**
     * Retrieves the Canvas on which the game is drawn.
     *
     * @return the current Canvas instance associated with this GameContext.
     */
    public Canvas getCanvas() {
        return canvas;
    }

    /**
     * Sets the Canvas on which the game is drawn.
     *
     * @param gameCanvas The Canvas instance
     *                  to be associated with this GameContext.
     */
    public void setCanvas(final Canvas gameCanvas) {
        this.canvas = gameCanvas;
    }

    /**
     * Retrieves the PauseTransition associated with this GameContext.
     *
     * @return the current PauseTransition instance used to control game timing.
     */
    public PauseTransition getPause() {
        return pause;
    }

    /**
     * Sets the PauseTransition instance
     * used to control game timing in this GameContext.
     *
     * @param gamePause The PauseTransition
     *             instance to be associated with this GameContext.
     */
    public void setPause(final PauseTransition gamePause) {
        this.pause = gamePause;
    }

    /**
     * Retrieves the root Group containing all visual elements of the game.
     *
     * @return the root Group instance associated with this GameContext,
     *         which serves as the container for
     *         all graphical nodes in the game.
     */
    public Group getRoot() {
        return root;
    }

    /**
     * Sets the root Group containing all visual elements of the game.
     *
     * @param gameRoot The root Group to be associated with this GameContext.
     *            This Group
     *             serves as the container for all graphical nodes in the game.
     */
    public void setRoot(final Group gameRoot) {
        this.root = gameRoot;
    }

    /**
     * Retrieves the GraphicsContext used for rendering the snake.
     *
     * @return the current GraphicsContext instance
     * associated with rendering the snake.
     */
    public GraphicsContext getSnake() {
        return snake;
    }

    /**
     * Sets the GraphicsContext used for rendering the snake in the game.
     *
     * @param snakeGraphicsContext The GraphicsContext to be
     *             associated with rendering the snake.
     */
    public void setSnake(final GraphicsContext snakeGraphicsContext) {
        this.snake = snakeGraphicsContext;
    }

    /**
     * Retrieves the GraphicsContext used for rendering the apple.
     *
     * @return the current GraphicsContext
     * instance associated with rendering the apple.
     */
    public GraphicsContext getApple() {
        return apple;
    }

    /**
     * Sets the GraphicsContext used for rendering the apple in the game.
     *
     * @param appleGraphicsContext The GraphicsContext
     *             to be associated with rendering the apple.
     */
    public void setApple(final GraphicsContext appleGraphicsContext) {
        this.apple = appleGraphicsContext;
    }

    /**
     * Retrieves the primary Stage where the game is displayed.
     *
     * @return the current Stage instance associated with this GameContext.
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Sets the primary Stage where the game is displayed.
     *
     * @param currentStage The Stage to be associated with the game,
     *             serving as the
     *              primary window for visual rendering and user interaction.
     */
    public void setStage(final Stage currentStage) {
        this.stage = currentStage;
    }

    /**
     * Retrieves the Text node displaying the player's score in the game.
     *
     * @return the Text instance used for displaying the player's score.
     */
    public Text getScoreText() {
        return scoreText;
    }

    /**
     * Sets the Text node to display the player's score in the game.
     *
     * @param scoretext The Text instance
     *                 to be associated with displaying the player's score.
     */
    public void setScoreText(final Text scoretext) {
        this.scoreText = scoretext;
    }
}
