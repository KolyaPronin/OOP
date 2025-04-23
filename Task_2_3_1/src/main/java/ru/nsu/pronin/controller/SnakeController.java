package ru.nsu.pronin.controller;

import javafx.animation.Animation;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import ru.nsu.pronin.data.SnakeData;
import ru.nsu.pronin.gui.GameContext;
import ru.nsu.pronin.gui.GameField;
import ru.nsu.pronin.gui.GameMenu;
import ru.nsu.pronin.gui.Setting;

public class SnakeController {
    public void handleKey(KeyCode code, GameContext context) {
        if (code == KeyCode.ENTER) {
            GameField.restart(context);
            return;
        }

        if (code == KeyCode.ESCAPE){
            context.pause.stop();   // ошибка что после паузы проходит сквозь препятствия.
            GameMenu menu = new GameMenu();
            Scene menuScene = menu.createMenu(context.stage, new GameField());
            context.stage.setScene(menuScene);

            //context.stage.close();
        }

        if (code == KeyCode.SPACE) {
            if (context.pause.getStatus() == Animation.Status.PAUSED) {
                context.pause.play();
            } else {
                context.pause.pause();
            }
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

        switch (code) {
            case W:
                if (currentState != 3) {
                    SnakeData.setState(1);
                    SnakeData.setDirectionChangedThisTick(true);
                }
                break;
            case S:
                if (currentState != 1) {
                    SnakeData.setState(3);
                    SnakeData.setDirectionChangedThisTick(true);
                }
                break;
            case A:
                if (currentState != 0) {
                    SnakeData.setState(2);
                    SnakeData.setDirectionChangedThisTick(true);
                }
                break;
            case D:
                if (currentState != 2) {
                    SnakeData.setState(0);
                    SnakeData.setDirectionChangedThisTick(true);
                }
                break;
        }
    }

}
