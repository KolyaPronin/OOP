package ru.nsu.pronin.service;

import javafx.animation.PauseTransition;
import ru.nsu.pronin.data.FieldData;
import ru.nsu.pronin.data.SnakeData;



public class FieldService {

    /**
     * A function that checks that the snake did not go beyond
     */
    public boolean borderChecker(PauseTransition pause) {
        double headX = SnakeData.getSnakeList().get(0).x;
        double headY = SnakeData.getSnakeList().get(0).y;

        if (headX < 0 || headX >= 600 || headY < 0 || headY >= 600) {
            pause.stop();
            return true;
        }
        for (int i = 1; i < SnakeData.getSnakeList().size(); i++) {
            if(headX == SnakeData.getSnakeList().get(i).x && headY == SnakeData.getSnakeList().get(i).y){
                pause.stop();
                return true;
            }
        }
        for (int i = 0; i < FieldData.getListWalls().size(); i++) {
            if (headX == FieldData.getListWalls().get(i).getX() && headY == FieldData.getListWalls().get(i).getY()){
               pause.stop();
               return true;
            }
        }

        return false;

    }



}
