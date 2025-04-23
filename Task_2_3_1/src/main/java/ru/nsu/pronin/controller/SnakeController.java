package ru.nsu.pronin.controller;

import javafx.animation.Animation;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import ru.nsu.pronin.data.SnakeData;
import ru.nsu.pronin.gui.GameContext;
import ru.nsu.pronin.gui.GameField;
import ru.nsu.pronin.gui.GameMenu;

/**
 * The SnakeController class is responsible
 * for handling user input during the game,
 * supporting actions such as restarting,
 * pausing, resuming, navigating menus, and
 * changing the snake's direction while ensuring game logic consistency.
 */
public class SnakeController {
    /**
     * The state for moving upwards.
     */
    private static final int STATE_UP = 1;

    /**
     * The state for moving downwards.
     */
    private static final int STATE_DOWN = 3;

    /**
     * The state for moving to the left.
     */
    private static final int STATE_LEFT = 2;

    /**
     * The state for moving to the right.
     */
    private static final int STATE_RIGHT = 0;

    /**
     * Handles the key input during the game,
     * performing actions such as restarting the game,
     * pausing/resuming, navigating menus,
     * or changing the direction of the snake.
     *
     * @param code    the {@code KeyCode}
     *               representing the key pressed by the user
     * @param context the {@code GameContext}
     *                holding the state and graphical elements of the game
     */
    public void handleKey(final KeyCode code, final GameContext context) {
        if (code == KeyCode.ENTER) {
            GameField.restart(context);
            return;
        }

        if (code == KeyCode.ESCAPE) {
            // ошибка что после паузы проходит сквозь препятствия.
            context.getPause().stop();
            GameMenu menu = new GameMenu();
            Scene menuScene = menu.createMenu(context.getStage(),
                    new GameField());
            context.getStage().setScene(menuScene);
        }

        if (code == KeyCode.SPACE) {
            if (context.getPause().getStatus() == Animation.Status.PAUSED) {
                context.getPause().play();
            } else {
                context.getPause().pause();
            }
        }
        if (SnakeData.isDirectionChangedThisTick()) {
            return;
        }
        int currentState = SnakeData.getState();
        switch (code) {
            case UP:
                if (currentState != STATE_DOWN) {
                    SnakeData.setState(STATE_UP);
                    SnakeData.setDirectionChangedThisTick(true);
                }
                break;
            case DOWN:
                if (currentState != STATE_UP) {
                    SnakeData.setState(STATE_DOWN);
                    SnakeData.setDirectionChangedThisTick(true);
                }
                break;
            case LEFT:
                if (currentState != STATE_RIGHT) {
                    SnakeData.setState(2);
                    SnakeData.setDirectionChangedThisTick(true);
                }
                break;
            case RIGHT:
                if (currentState != STATE_LEFT) {
                    SnakeData.setState(STATE_RIGHT);
                    SnakeData.setDirectionChangedThisTick(true);
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + code);
        }

        switch (code) {
            case W:
                if (currentState != STATE_DOWN) {
                    SnakeData.setState(STATE_UP);
                    SnakeData.setDirectionChangedThisTick(true);
                }
                break;
            case S:
                if (currentState != STATE_UP) {
                    SnakeData.setState(STATE_DOWN);
                    SnakeData.setDirectionChangedThisTick(true);
                }
                break;
            case A:
                if (currentState != STATE_RIGHT) {
                    SnakeData.setState(STATE_LEFT);
                    SnakeData.setDirectionChangedThisTick(true);
                }
                break;
            case D:
                if (currentState != STATE_LEFT) {
                    SnakeData.setState(STATE_RIGHT);
                    SnakeData.setDirectionChangedThisTick(true);
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + code);
        }
    }

}
