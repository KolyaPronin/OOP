package ru.nsu.pronin.service;

import javafx.animation.PauseTransition;
import ru.nsu.pronin.data.FieldData;
import ru.nsu.pronin.data.SnakeData;

/**
 * The FieldService class contains methods
 * responsible for managing game field-related logic
 * and interactions, particularly focused
 * on the snake's movement and collision detection.
 */
public class FieldService {

    /**
     * Checks for collisions or boundary
     * conditions involving the snake in the game.
     * This includes collision with walls,
     * the snake's own body, or game-defined boundaries.
     * If collisions are detected, the game is paused.
     *
     * @param pause the game pause transition object,
     *             used to pause the game in case of collisions
     * @return {@code true} if a collision or boundary
     * condition is detected, {@code false} otherwise
     */
    public boolean borderChecker(final PauseTransition pause) {
        int  headX = SnakeData.getSnakeList().getFirst().x;
        int  headY = SnakeData.getSnakeList().getFirst().y;

        if (FieldData.isAbilityPassThroughWalls()) {
            if (headX < 0) {
                headX = 600; // Переносим голову справа
            } else if (headX >= 600) {
                headX = 0; // Переносим голову слева
            } else if (headY < 0) {
                headY = 600; // Переносим голову снизу
            } else if (headY >= 600) {
                headY = 0; // Переносим голову сверху
            }
            SnakeData.getSnakeList().getFirst().x = headX;
            SnakeData.getSnakeList().getFirst().y = headY;
        } else {
            // Если стена не проходима
            if (headX < 0 || headX >= 600 || headY < 0 || headY >= 600) {
                pause.stop();
                return true;
            }
        }
        for (int i = 1; i < SnakeData.getSnakeList().size(); i++) {
            if (headX == SnakeData.getSnakeList().get(i).x
                    && headY == SnakeData.getSnakeList().get(i).y) {
                pause.stop();
                return true;
            }
        }
        for (int i = 0; i < FieldData.getListWalls().size(); i++) {
            if (headX == FieldData.getListWalls().get(i).getX()
                    && headY == FieldData.getListWalls().get(i).getY()) {
                pause.stop();
                return true;
            }
        }

        return false;
    }
}
