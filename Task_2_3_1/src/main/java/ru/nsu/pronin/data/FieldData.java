package ru.nsu.pronin.data;

import java.awt.*;
import javafx.scene.paint.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FieldData {
    private static final int sizeX = 600;
    private static final int sizeY = 600;
    private static final List<Point2D> walls = new ArrayList<Point2D>();
    private static final List<Point> apple = new ArrayList<>(List.of(new Point(200, 100)));
    public static int numberOfLevel = 1;
    private static boolean abilityPassThroughWalls = false;
    private static Color color = Color.GRAY;

    // Метод для инициализации стен
    public static void field() {
        if(numberOfLevel == 1) {
             Levels.levelOne();
         } else if(numberOfLevel == 2) {
             Levels.levelTwo();
         } else if(numberOfLevel == 3) {
             Levels.levelThree();
         } else if(numberOfLevel == 4) {
             Levels.levelFour();
         } else if(numberOfLevel == 5) {
             Levels.levelFive();
         } else if (numberOfLevel == 6) {
            Levels.classicLevel();
        }
    }

    public static void setLevel(int num){
        numberOfLevel = num;
    }

    public static int getNumberOfLevel() {
        return numberOfLevel;
    }

    public static int getSizeX() {
        return sizeX;
    }

    public static int getSizeY() {
        return sizeY;
    }

    public static List<Point2D> getListWalls() {
        return walls;
    }

    public static List<Point> getApple(){
        return apple;
    }

    public static void addNewAppleToField() {
        Point newApple;
        boolean isOnWall;

        do {
            newApple = generateNewApple();
            isOnWall = false;

            for (Point2D wall : getListWalls()) {
                if (newApple.x == wall.getX() && newApple.y == wall.getY()) {
                    isOnWall = true;
                    break;
                }
            }
        } while (isOnWall);

        apple.add(newApple);
    }

    public static Point generateNewApple(){
        return new Point(new Random().nextInt(60) * 10, new Random().nextInt(60) * 10);

    }

    public static void removeApple(){
        apple.remove(0);
    }

    public static boolean isAbilityPassThroughWalls() {
        return abilityPassThroughWalls;
    }

    public static void setAbilityPassThroughWalls(boolean abilityPassThroughWalls) {
        FieldData.abilityPassThroughWalls = abilityPassThroughWalls;
    }

//    public static void setColor(Color color){
//        FieldData.color = color;
//    }
//
//    public static Color getColor(){
//        return getColor();
//    }
}
