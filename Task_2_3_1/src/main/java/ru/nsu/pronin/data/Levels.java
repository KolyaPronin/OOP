package ru.nsu.pronin.data;

import javafx.util.Duration;
import ru.nsu.pronin.gui.GameField;

import java.awt.*;
import java.awt.geom.Point2D;

//игра из 7 уровней,
// чтобы пройти ее надо пройти каждый уровень
public class Levels {
    public static void levelOne(){
        FieldData.getListWalls().clear();
        GameField.getPause().setDuration(Duration.millis(150));
        SnakeData.setNumberOfPointForVictory(5);

        SnakeData.getSnakeList().clear();
        SnakeData.getSnakeList().add(new Point(100,100));
        SnakeData.setState(0);

        for (int i = 1; i < 5; i++) {

            FieldData.getListWalls().add(new Point2D.Double(10 * i, 20));
            FieldData.getListWalls().add(new Point2D.Double(50,60 + i * 10));
            FieldData.getListWalls().add(new Point2D.Double(220,150 + i * 10));
            FieldData.getListWalls().add(new Point2D.Double(450,450 + i * 10));
            FieldData.getListWalls().add(new Point2D.Double(450 + ((i / 2) * 10),450));
            FieldData.getListWalls().add(new Point2D.Double(520 + ((i / 2) * 10),120));
            FieldData.getListWalls().add(new Point2D.Double(520,120 - i * 10));
            FieldData.getListWalls().add(new Point2D.Double(10 * i ,450));
            FieldData.getListWalls().add(new Point2D.Double(300 + i * 10 ,550));
        }
    }

    // рельсы
    public static void levelTwo(){
        FieldData.getListWalls().clear();
        GameField.getPause().setDuration(Duration.millis(100));
        SnakeData.setNumberOfPointForVictory(10);

        SnakeData.getSnakeList().clear();
        SnakeData.getSnakeList().add(new Point(100,100));
        SnakeData.setState(0);

        for (int i = 1; i < 48; i++) {
            FieldData.getListWalls().add(new Point2D.Double(60 + i * 10, 40));
            FieldData.getListWalls().add(new Point2D.Double(60 + i * 10, 530));
        }

        for (int i = 1; i < 35; i++) {
            FieldData.getListWalls().add(new Point2D.Double(120 + i * 10, 220));
            FieldData.getListWalls().add(new Point2D.Double(120 + i * 10, 340));
        }
        FieldData.getListWalls().add(new Point2D.Double(130, 230));
        FieldData.getListWalls().add(new Point2D.Double(460, 230));

        FieldData.getListWalls().add(new Point2D.Double(130, 330));
        FieldData.getListWalls().add(new Point2D.Double(460, 330));

        for (int i = 1; i < 5; i++) {
            FieldData.getListWalls().add(new Point2D.Double(60, 30 + i * 10));
            FieldData.getListWalls().add(new Point2D.Double(540, 30 + i * 10));

            FieldData.getListWalls().add(new Point2D.Double(60, 540 - i * 10));
            FieldData.getListWalls().add(new Point2D.Double(540, 540 - i * 10));
        }
    }

    // крест
    public static void levelThree(){
        FieldData.getListWalls().clear();
        GameField.getPause().setDuration(Duration.millis(70));
        SnakeData.setNumberOfPointForVictory(15);

        SnakeData.getSnakeList().clear();
        SnakeData.getSnakeList().add(new Point(100,100));
        SnakeData.setState(0);


        for (int i = 1; i < 30; i++) {
            FieldData.getListWalls().add(new Point2D.Double(290, 120 + i * 10));
            FieldData.getListWalls().add(new Point2D.Double(140 + i * 10, 280));
        }
    }

    public static void levelFour() {
        FieldData.getListWalls().clear();
        GameField.getPause().setDuration(Duration.millis(50));
        SnakeData.setNumberOfPointForVictory(20);
        SnakeData.getSnakeList().clear();
        SnakeData.getSnakeList().add(new Point(100,100));
        SnakeData.setState(0);


        for (int i = 1; i < 40; i++) {
            FieldData.getListWalls().add(new Point2D.Double(70, 120 + i * 10));
            FieldData.getListWalls().add(new Point2D.Double(70 + i * 10, 130));
        }
        for (int i = 0; i < 30; i++) {
            FieldData.getListWalls().add(new Point2D.Double(470, 130 + i * 10));
            FieldData.getListWalls().add(new Point2D.Double(470 - i * 10, 420));

        }

        for(int i = 0; i < 20; i++){
            FieldData.getListWalls().add(new Point2D.Double(170, 230 + i * 10));
            FieldData.getListWalls().add(new Point2D.Double(360 - i * 10, 230));
        }
        for (int i = 0; i < 10; i++){
            FieldData.getListWalls().add(new Point2D.Double(360, 320 - i * 10));
        }
        for (int i = 0; i < 10; i++){
            FieldData.getListWalls().add(new Point2D.Double(360 - i * 10, 320 ));
        }
        for (int i = 0; i < 5; i++) {
            FieldData.getListWalls().add(new Point2D.Double(260, 320 - i * 10));
        }
    }
    // Большой крест. Змей должен проходить сквозь стены
    public static void levelFive(){
        FieldData.getListWalls().clear();
        SnakeData.getSnakeList().clear();
        SnakeData.getSnakeList().add(new Point(100,100));
        SnakeData.setState(0);
        FieldData.setAbilityPassThroughWalls(true);
        GameField.getPause().setDuration(Duration.millis(40));
        SnakeData.setNumberOfPointForVictory(5);

        for (int i = 0; i < 60; i++) {
            FieldData.getListWalls().add(new Point2D.Double(290, i * 10));
            FieldData.getListWalls().add(new Point2D.Double(i * 10, 280));
        }
    }

    public static void classicLevel(){
        FieldData.getListWalls().clear();
        FieldData.setAbilityPassThroughWalls(true);
        SnakeData.setNumberOfPointForVictory(100);

        SnakeData.getSnakeList().clear();
        SnakeData.getSnakeList().add(new Point(100,100));
        SnakeData.setState(0);
    }
}
