package ru.nsu.pronin.service;

import javafx.animation.PauseTransition;
import ru.nsu.pronin.data.FieldData;
import ru.nsu.pronin.data.SnakeData;



public class FieldService {

    /**
     * A function that checks that the snake did not go beyond
     */
    public boolean borderChecker(PauseTransition pause) {
        // Получаем координаты головы
        int  headX = SnakeData.getSnakeList().get(0).x;
        int  headY = SnakeData.getSnakeList().get(0).y;

        // Проверка способности проходить сквозь стены
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

            // Обновляем координаты головы в списке SnakeData.getSnakeList()
            SnakeData.getSnakeList().get(0).x = headX;
            SnakeData.getSnakeList().get(0).y = headY;
        } else {
            // Если стена не проходима
            if (headX < 0 || headX >= 600 || headY < 0 || headY >= 600) {
                pause.stop();
                return true;
            }
        }

        // Проверка столкновения с телом змеи
        for (int i = 1; i < SnakeData.getSnakeList().size(); i++) {
            if (headX == SnakeData.getSnakeList().get(i).x && headY == SnakeData.getSnakeList().get(i).y) {
                pause.stop();
                return true;
            }
        }

        // Проверка столкновения со стенами
        for (int i = 0; i < FieldData.getListWalls().size(); i++) {
            if (headX == FieldData.getListWalls().get(i).getX() && headY == FieldData.getListWalls().get(i).getY()) {
                pause.stop();
                return true;
            }
        }

        return false;
    }



}
