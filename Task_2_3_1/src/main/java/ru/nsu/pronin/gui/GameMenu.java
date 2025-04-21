package ru.nsu.pronin.gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameMenu {
    public Scene createMenu(Stage stage, GameField gameField) {
        VBox menuBox = new VBox(20);
        menuBox.setAlignment(Pos.CENTER);

        Text title = new Text("Snake Game");
        title.setFont(Font.font(36));

        Button playBtn = new Button("Play");
        playBtn.setOnAction(e -> {
            try {
                gameField.startGame(stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        Button exitBtn = new Button("Exit");
        exitBtn.setOnAction(e -> stage.close());

        menuBox.getChildren().addAll(title, playBtn, exitBtn);
        return new Scene(menuBox, 600, 600);
    }
}
