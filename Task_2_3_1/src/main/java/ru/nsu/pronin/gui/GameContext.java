package ru.nsu.pronin.gui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.animation.PauseTransition;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameContext {
    public GraphicsContext gc;
    public Canvas canvas;
    public PauseTransition pause;
    public Group root;
    public GraphicsContext snake;
    public GraphicsContext apple;
    public Stage stage;
    public Text scoreText;


    public GameContext(GraphicsContext gc, Canvas canvas, PauseTransition pause,
                       Group root, GraphicsContext snake, GraphicsContext apple, Stage stage, Text scoreText) {
        this.gc = gc;
        this.canvas = canvas;
        this.pause = pause;
        this.root = root;
        this.snake = snake;
        this.apple = apple;
        this.stage = stage;
        this.scoreText = scoreText;
    }
}
