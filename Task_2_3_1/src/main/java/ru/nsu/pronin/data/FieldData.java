package ru.nsu.pronin.data;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FieldData {
    private static final int sizeX = 600;
    private static final int sizeY = 600;
    private static List<Point2D> walls = new ArrayList<Point2D>();
    private static final List<Point> apple = new ArrayList<>(List.of(new Point(200, 100)));
    // Метод для инициализации стен
    public static void field() {
        for (int i = 1; i < 5; i++) {
            walls.add(new Point2D.Double(10 * i, 20));
            walls.add(new Point2D.Double(50,60 + i * 10));
            walls.add(new Point2D.Double(220,150 + i * 10));
            walls.add(new Point2D.Double(450,450 + i * 10));
            walls.add(new Point2D.Double(450 + ((i / 2) * 10),450));
            walls.add(new Point2D.Double(520 + ((i / 2) * 10),120));
            walls.add(new Point2D.Double(520,120 - i * 10));
            walls.add(new Point2D.Double(10 * i ,450));
            walls.add(new Point2D.Double(300 + i * 10 ,550));
            // Потом добавить еще стен
            // начало координат левый верхний угол.
            // Чтобы сдвинуть по x (влево) в пределах экрана 600*600
            // надо ввести (x < 600) + (1 < i < 5) * 10(квадрат клетки).
            // Чтобы сдвинуть по y( вверх или вниз) в пределах экрана 600*600
            // надо ввести (y < 600) + (1 < i < 5) * 10(квадрат клетки)
            // 600*600 == правому нижнему углу экрана.
            // Чтобы укоротить длину препятствия, следует применить к i целочисленное деление, без дробей.
        }

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
}
