package ru.nsu.pronin.service;

import ru.nsu.pronin.data.FieldData;
import ru.nsu.pronin.data.SnakeData;
import ru.nsu.pronin.gui.GameField;

import java.awt.*;

public class SnakeService {

    /**
    * function for movement snake
    */
    public void move(){

        if (SnakeData.getState() == 0){ // движение слева направо
            Point head = SnakeData.getSnakeList().get(0);
            head = new Point(head.x + 10, head.y);
            SnakeData.getSnakeList().add(0,head);

            SnakeData.deletePointFromList();
            for (int i = 0; i < FieldData.getApple().size(); i++) {
                if (SnakeData.getSnakeList().get(0).equals(FieldData.getApple().get(i))) {
                    eatingFood();
                }
            }

            //System.out.println( SnakeData.getSnakeList().size());
        } else if (SnakeData.getState() == 1) { // движение сверху вниз
            Point head = SnakeData.getSnakeList().get(0);
            head = new Point(head.x, head.y - 10);
            SnakeData.getSnakeList().add(0,head);
            SnakeData.deletePointFromList();
            for (int i = 0; i < FieldData.getApple().size(); i++) {
                if (SnakeData.getSnakeList().get(0).equals(FieldData.getApple().get(i))) {
                    eatingFood();
                }
            }
        } else if(SnakeData.getState() == 2) {
            Point head = SnakeData.getSnakeList().get(0);
            head = new Point(head.x - 10, head.y);
            SnakeData.getSnakeList().add(0,head);
            SnakeData.deletePointFromList();
            for (int i = 0; i < FieldData.getApple().size(); i++) {
                if (SnakeData.getSnakeList().get(0).equals(FieldData.getApple().get(i))) {
                    eatingFood();
                }
            }
        } else if(SnakeData.getState() == 3) {
            Point head = SnakeData.getSnakeList().get(0);
            head = new Point(head.x , head.y + 10);
            SnakeData.getSnakeList().add(0,head);
            SnakeData.deletePointFromList();
            for (int i = 0; i < FieldData.getApple().size(); i++) {
                if (SnakeData.getSnakeList().get(0).equals(FieldData.getApple().get(i))) {
                    eatingFood();
                }
            }
        }
    }

    /**
     * In a collision with the food of the snake, it eats and increases
     */
    public void eatingFood(){
        // при столкновении запрос в SnakeData чтобы список увеличился
        SnakeData snakeData = new SnakeData();
        snakeData.addPointToList((int) GameField.getLastTailPosition().getX(), (int) GameField.getLastTailPosition().getY());
        FieldData.removeApple();
        FieldData.addNewAppleToField();
    }
}
