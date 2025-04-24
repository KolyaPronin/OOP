package ru.nsu.pronin.gui;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import ru.nsu.pronin.controller.SnakeController;
import ru.nsu.pronin.data.FieldData;
import ru.nsu.pronin.data.SnakeData;
import ru.nsu.pronin.service.FieldService;
import ru.nsu.pronin.service.SnakeService;

import java.awt.Point;
import java.awt.geom.Point2D;


/**
 * The GameField class represents the main game
 * application for a Snake game built using the JavaFX framework.
 * It is responsible for initializing the application,
 * showing the game menu, and managing the gameplay loop.
 */
public class GameField extends Application {
    /** Speed. */
    private static final int SPEED = 50;

    /** Position of the last segment of the snake's tail. */
    private static Point2D lastTailPosition;

    /** Pause transition for controlling the game's update speed. */
    private static PauseTransition pause =
            new PauseTransition(Duration.millis(SPEED));

    /**
     * Handles the launch process of the game.
     * This method initializes or transitions the game to the necessary state
     * by invoking internal logic and
     * functionalities associated with the game launch.
     */
    public void field() {
        launch();

    }

    /**
     * Starts the JavaFX application and initializes the game menu.
     * This method is called internally during the application launch
     * to display the initial menu.
     *
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     */
    @Override
    public void start(final Stage stage) {
        showMenu(stage);
    }

    /**
     * Displays the main game menu on the specified stage.
     * This method initializes and transitions the application
     * to the game menu view, enabling user interaction to start
     * the game, configure settings, or exit the application.
     *
     * @param stage the primary stage for the application on which
     *              the menu is displayed
     */
    public void showMenu(final Stage stage) {
        GameMenu menu = new GameMenu();
        Scene menuScene = menu.createMenu(stage, this);
        stage.setScene(menuScene);
        stage.show();
    }

    /**
     * Initializes and starts the game by setting
     * up the game field, visuals, and game logic interactions.
     * This method renders the game components
     * (snake, apples, walls, etc.), sets the score display,
     * and handles game events such as player movements,
     * game over conditions, and level progressions.
     *
     * @param stage the primary stage of the application,
     *             used to display the game scene and handle user interactions.
     * @throws Exception if an exception occurs during
     * the initialization or execution of the game.
     */
    public void startGame(final Stage stage) throws Exception {
        FieldData.field();

        Canvas canvas = new Canvas(600, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);

        for (Point2D wall : FieldData.getListWalls()) {
            gc.fillRect(wall.getX(), wall.getY(), 10, 10);
        }
        Group root = new Group();
        root.getChildren().add(canvas);
        Text scoreText = new Text();
        scoreText.setFont(Font.font(15));
        scoreText.setFill(Color.WHITE);
        scoreText.setX(10); // положение по X
        scoreText.setY(20); // положение по Y
        root.getChildren().add(scoreText);


        //PauseTransition pause = new PauseTransition(Duration.millis(50));

        GraphicsContext snake = canvas.getGraphicsContext2D();
        GraphicsContext apple = canvas.getGraphicsContext2D();



        Scene sceneGame = new Scene(
                root, FieldData.getSizeX(), FieldData.getSizeY(), Color.GRAY);
        stage.setScene(sceneGame);
        stage.setTitle("Snake");


        SnakeService service = new SnakeService();
        SnakeController controller = new SnakeController();
        FieldService fieldService = new FieldService();
        GameContext context = new GameContext(
                gc, canvas, pause, root, snake, apple, stage, scoreText);


        pause.setOnFinished(e -> {
            double oldX = SnakeData.getSnakeList().
                    get(SnakeData.getSnakeList().size() - 1).getX();
            double oldY = SnakeData.getSnakeList().
                    get(SnakeData.getSnakeList().size() - 1).getY();
            lastTailPosition = SnakeData.getSnakeList().
                    get(SnakeData.getSnakeList().size() - 1);

            SnakeData.setDirectionChangedThisTick(false);
            service.move();
            boolean gameOver = fieldService.borderChecker(pause);

            scoreText.setText("Score: " + (SnakeData.getSnakeList().size() - 1)
                    + " / " + SnakeData.getNumberOfPointForVictory());

            if (gameOver) {
                Rectangle dim =
                        new Rectangle(
                                FieldData.getSizeX(), FieldData.getSizeY());
                dim.setFill(Color.rgb(0, 0, 0, 0.5));
                root.getChildren().add(dim);

                Text gameOverText = new Text("GAME OVER");
                gameOverText.setFont(Font.font(70));
                gameOverText.setFill(Color.RED);

                gameOverText.setX(FieldData.getSizeX() / 2
                       - gameOverText.getLayoutBounds().getWidth() / 2);
                gameOverText.setY(FieldData.getSizeY() / 2);


                root.getChildren().add(gameOverText);

                return;
            }

            if (SnakeData.getSnakeList().size()
                    == SnakeData.getNumberOfPointForVictory() + 1) {
                Rectangle dim = new Rectangle(
                        FieldData.getSizeX(), FieldData.getSizeY());
                dim.setFill(Color.rgb(0, 0, 0, 0.5));
                root.getChildren().add(dim);

                Button nextLevelBtn = new Button("Next Level");

                double btnWidth = 100;
                double btnHeight = 30;

                nextLevelBtn.setLayoutX(
                        FieldData.getSizeX() / 2 - btnWidth / 2);
                nextLevelBtn.setLayoutY(FieldData.getSizeY() / 2 + 50);
                nextLevelBtn.setOnAction(event -> {
                    FieldData.setLevel(FieldData.getNumberOfLevel() + 1);
                    try {
                        startGame(stage);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });

                Text youWinText = new Text("You Win");
                youWinText.setFont(Font.font(70));
                youWinText.setFill(Color.GREENYELLOW);
                youWinText.setX(FieldData.getSizeX() / 2
                        - youWinText.getLayoutBounds().getWidth() / 2);
                youWinText.setY(FieldData.getSizeY() / 2);

                root.getChildren().add(youWinText);
                root.getChildren().add(nextLevelBtn);
                pause.stop();

                return;
            }


            gc.setFill(Color.GRAY);
            gc.fillRect(oldX, oldY, 10, 10);


            apple.setFill(Color.DARKRED);
            for (int i = 0; i < FieldData.getApple().size(); i++) {
                apple.fillRect(FieldData.getApple().get(i).getX(),
                        FieldData.getApple().get(i).getY(), 10, 10);
            }

            snake.setFill(Color.DARKGREEN);
            for (int i = 0; i < SnakeData.getSnakeList().size(); i++) {
                snake.fillRect(SnakeData.getSnakeList().get(i).getX(),
                        SnakeData.getSnakeList().get(i).getY(), 10, 10);
            }


            scoreText.setText("Score: " + (SnakeData.getSnakeList().size() - 1)
                    + " / " + SnakeData.getNumberOfPointForVictory());

            pause.playFromStart();
        });
        pause.play();

        sceneGame.setOnKeyPressed(event -> {
            try {
                controller.handleKey(event.getCode(), context);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        stage.show();
    }

    /**
     * Retrieves the last recorded position of the snake's tail.
     *
     * @return the last position of the snake's tail as a Point2D object
     */
    public static Point2D getLastTailPosition() {
        return lastTailPosition;
    }


    /**
     * Restarts the game by resetting the
     * game state and rendering the initial elements.
     * This method clears the current game field,
     * reinitializes the snake, apple, and wall positions,
     * and resumes the game loop.
     *
     * @param context the {@code GameContext}
     *               object containing the current game state,
     *                including game components and graphical rendering context.
     */
    public static void restart(final GameContext context) {
        context.getPause().stop();

        context.getGc().setFill(Color.GRAY);
        context.getGc().fillRect(0, 0, context.getCanvas().getWidth(),
                context.getCanvas().getHeight());
        context.getRoot().getChildren().removeIf(node ->
                (node instanceof Text && node != context.getScoreText())
                        || node instanceof Rectangle);

        context.getGc().setFill(Color.BLACK);

        for (Point2D wall : FieldData.getListWalls()) {
            context.getGc().fillRect(wall.getX(), wall.getY(), 10, 10);
        }

        context.getApple().setFill(Color.DARKRED);
        FieldData.getApple().clear();
        FieldData.addNewAppleToField();

        SnakeData.getSnakeList().clear();
        SnakeData.getSnakeList().add(new Point(100, 100));
        SnakeData.setState(0);

        context.getPause().play();
    }

    /**
     * Retrieves the shared {@code PauseTransition}
     * instance used within the game.
     * The {@code PauseTransition} controls timing-related functionality,
     * such as delays and durations for animations or actions in the game.
     *
     * @return the {@code PauseTransition} instance used
     * for controlling pauses and timing in the game
     */
    public static PauseTransition getPause() {
        return pause;
    }
}
