package ru.nsu.pronin.data;

import java.awt.*;
import java.util.*;
import java.util.List;

public class SnakeData {
    private static final List<Point> head = Arrays.asList(new Point(100, 100));
    private static final List<Point> snakeList = new ArrayList<Point>(head);
    private static int state = 0; // состояние 0 слева направо, 1 сверху  вниз, 2 справа налево, 3 снизу вверх
    private static boolean directionChangedThisTick = false;
    public static int numberOfPointForVictory = 0;

    /**
     * Метод добавления
     */
    public void addPointToList(int x, int y){
        snakeList.add(new Point(x, y));

    }

    /**
    * Метод удаления хвоста
    */
    public static void deletePointFromList(){
        snakeList.remove(snakeList.size() - 1);
    }

    public static boolean isDirectionChangedThisTick() {
        return directionChangedThisTick;
    }

    public static void setDirectionChangedThisTick(boolean value) {
        directionChangedThisTick = value;
    }

    public void saveCoordinatesTail(){
        Point tail = snakeList.get(snakeList.size() - 1);
    }

    /**
     *  Getter to obtain a movement state
     */
    public static int getState() {
        return state;
    }

    public static void setState(int state){
        SnakeData.state = state;
    }

    public static List<Point> getSnakeList(){
        return snakeList;
    }

    public static void setNumberOfPointForVictory(int number){
        numberOfPointForVictory = number;
    }

    public static int getNumberOfPointForVictory(){
        return numberOfPointForVictory;
    }


}
