package ru.nsu.pronin.controller;

import javafx.scene.input.KeyCode;
import ru.nsu.pronin.data.SnakeData;
import ru.nsu.pronin.gui.GameContext;
import ru.nsu.pronin.gui.GameField;

public class SnakeController {
    public void handleKey(KeyCode code, GameContext context) {
        if (code == KeyCode.ENTER) {
            GameField.restart(context);
            return;
        }
        if (SnakeData.isDirectionChangedThisTick()) return;

        int currentState = SnakeData.getState();

        switch (code) {
            case UP:
                if (currentState != 3) {
                    SnakeData.setState(1);
                    SnakeData.setDirectionChangedThisTick(true);
                }
                break;
            case DOWN:
                if (currentState != 1) {
                    SnakeData.setState(3);
                    SnakeData.setDirectionChangedThisTick(true);
                }
                break;
            case LEFT:
                if (currentState != 0) {
                    SnakeData.setState(2);
                    SnakeData.setDirectionChangedThisTick(true);
                }
                break;
            case RIGHT:
                if (currentState != 2) {
                    SnakeData.setState(0);
                    SnakeData.setDirectionChangedThisTick(true);
                }
                break;
        }
    }

}
