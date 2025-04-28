package ru.nsu.pronin.service;

import ru.nsu.pronin.data.FieldData;
import ru.nsu.pronin.data.SnakeData;
import ru.nsu.pronin.gui.GameField;
import java.awt.Point;

/**
 * The SnakeService class provides functionality
 * to control the movement of the snake
 * and handle interactions such as consuming
 * food during gameplay. It uses the SnakeData
 * and FieldData classes to manage the game
 * state and snake behavior.
 */
public class SnakeService {

    /**
     * Moves the snake in the direction specified by its current state.
     * The direction is determined by the state value:
     * 0 - moves from left to right.
     * 1 - moves from top to bottom.
     * 2 - moves from right to left.
     * 3 - moves from bottom to top.
     * <p>
     * During movement, the method updates the position of the snake's head
     * by adding a new point at the appropriate position. It removes the last
     * point from the snake's body to maintain its length unless food is eaten.
     * <p>
     * If the snake's head overlaps with any of the food items on the field,
     * the method triggers the snake's growth
     * process by invoking the `eatingFood` method.
     * <p>
     * The snake's position and size are updated dynamically based on its state
     * and interactions with food.
     */
    public void move() {
        if (SnakeData.getState() == 0) {
            Point head = SnakeData.getSnakeList().getFirst();
            head = new Point(head.x + 10, head.y);
            logicOfBodyIncrease(head);

            //System.out.println( SnakeData.getSnakeList().size());
        } else if (SnakeData.getState() == 1) { // движение сверху вниз
            Point head = SnakeData.getSnakeList().getFirst();
            head = new Point(head.x, head.y - 10);
            logicOfBodyIncrease(head);
        } else if (SnakeData.getState() == 2) {
            Point head = SnakeData.getSnakeList().getFirst();
            head = new Point(head.x - 10, head.y);
            logicOfBodyIncrease(head);
        } else if (SnakeData.getState() == 3) {
            Point head = SnakeData.getSnakeList().getFirst();
            head = new Point(head.x, head.y + 10);
            logicOfBodyIncrease(head);
        }
    }

    /**
     * The logic of the body of the snake.
     * @param head
     */
    private void logicOfBodyIncrease(final Point head) {
        SnakeData.getSnakeList().addFirst(head);
        SnakeData.deletePointFromList();
        for (int i = 0; i < FieldData.getApple().size(); i++) {
            if (SnakeData.getSnakeList().getFirst().
                    equals(FieldData.getApple().get(i))) {
                eatingFood();
            }
        }
    }

    /**
     * Handles the process of the snake eating food in the game.
     * <p>
     * This method is triggered when the snake's head
     * collides with a food item on the game field.
     * Upon collision:
     * - A new point is added to the snake's body
     * at the last tail position using the
     *   {@code addPointToList} method from the {@code SnakeData} class.
     * - The consumed apple is removed from the game field.
     * - A new apple is generated and added to the game field.
     */
    public void eatingFood() {
        SnakeData snakeData = new SnakeData();
        snakeData.addPointToList((int) GameField.getLastTailPosition().getX(),
                (int) GameField.getLastTailPosition().getY());
        FieldData.removeApple();
        FieldData.addNewAppleToField();
    }
}
