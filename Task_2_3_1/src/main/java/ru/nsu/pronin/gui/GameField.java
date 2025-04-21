package ru.nsu.pronin.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import ru.nsu.pronin.controller.SnakeController;
import ru.nsu.pronin.data.FieldData;
import ru.nsu.pronin.data.SnakeData;
import ru.nsu.pronin.service.FieldService;
import ru.nsu.pronin.service.SnakeService;
import javafx.animation.PauseTransition;
import javafx.util.Duration;


import java.awt.*;
import java.awt.geom.Point2D;

public class GameField extends Application {
    private static Point2D lastTailPosition;

    public void field(){
        launch();

    }

    @Override
    public void start(Stage stage) {
        showMenu(stage);
    }

    public void showMenu(Stage stage) {
        GameMenu menu = new GameMenu();
        Scene menuScene = menu.createMenu(stage, this);
        stage.setScene(menuScene);
        stage.show();
    }

    public void startGame(Stage stage) throws Exception {
        FieldData.field();

        Canvas canvas = new Canvas(600, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.BLACK);

        for (Point2D wall : FieldData.getListWalls()) {
            gc.fillRect(wall.getX(), wall.getY(), 10, 10);
        }

        Group root = new Group();
        root.getChildren().add(canvas);


        PauseTransition pause = new PauseTransition(Duration.millis(50));

        GraphicsContext snake = canvas.getGraphicsContext2D();
        GraphicsContext apple = canvas.getGraphicsContext2D();



        Scene sceneGame = new Scene(root, FieldData.getSizeX(), FieldData.getSizeY(), Color.GRAY);
        stage.setScene(sceneGame);
        stage.setTitle("Snake");


        SnakeService service = new SnakeService();
        SnakeController controller = new SnakeController();
        FieldService fieldService = new FieldService();
        GameContext context = new GameContext(gc, canvas, pause, root, snake, apple,stage);


        pause.setOnFinished(e -> {
            double oldX = SnakeData.getSnakeList().get(SnakeData.getSnakeList().size() - 1).getX();
            double oldY = SnakeData.getSnakeList().get(SnakeData.getSnakeList().size() - 1).getY();
            lastTailPosition = SnakeData.getSnakeList().get(SnakeData.getSnakeList().size() - 1);

            SnakeData.setDirectionChangedThisTick(false);
            service.move();
            boolean gameOver = fieldService.borderChecker(pause);

            if (gameOver) {
                Text gameOverText = new Text("GAME OVER");
                gameOverText.setFont(Font.font(70));
                gameOverText.setFill(Color.RED);

                gameOverText.setX(FieldData.getSizeX()/2 - gameOverText.getLayoutBounds().getWidth()/2);
                gameOverText.setY(FieldData.getSizeY()/2);


                root.getChildren().add(gameOverText);

                return;
            }


            gc.setFill(Color.GRAY);
            gc.fillRect(oldX, oldY, 10, 10);


            apple.setFill(Color.RED);
            for (int i = 0; i < FieldData.getApple().size(); i++) {
                apple.fillRect(FieldData.getApple().get(i).getX(),FieldData.getApple().get(i).getY(),10,10);
            }

            snake.setFill(Color.DARKGREEN);
            for(int i = 0; i < SnakeData.getSnakeList().size(); i++) {
                snake.fillRect(SnakeData.getSnakeList().get(i).getX(), SnakeData.getSnakeList().get(i).getY(), 10, 10);
            }

            pause.playFromStart();
        });
        pause.play(); // старт

        sceneGame.setOnKeyPressed(event -> {
            try {
                controller.handleKey(event.getCode(), context);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        stage.show();
    }

    public static Point2D getLastTailPosition() {
        return lastTailPosition;
    }


    public static void restart(GameContext context){
        context.pause.stop();

        context.gc.setFill(Color.GRAY);
        context.gc.fillRect(0,0,context.canvas.getWidth(), context.canvas.getHeight());
        context.root.getChildren().removeIf(node -> node instanceof Text);

        context.gc.setFill(Color.BLACK);

        for (Point2D wall : FieldData.getListWalls()) {
            context.gc.fillRect(wall.getX(), wall.getY(), 10, 10);
        }

        context.apple.setFill(Color.RED);
        FieldData.getApple().clear();
        FieldData.addNewAppleToField();

        SnakeData.getSnakeList().clear();
        SnakeData.getSnakeList().add(new Point(100,100));
        SnakeData.setState(0);

        context.pause.play();
    }
}
