package ru.nsu.pronin.gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ru.nsu.pronin.data.SoundPlayer;

/**
 * The GameMenu class represents the main menu of the Snake Game application.
 * It provides options to start the game,
 * navigate to the settings, or exit the application.
 */
public class GameMenu {

    /**
     * Creates and initializes the main menu scene for the Snake Game application.
     * The menu contains options to start the game, navigate to settings, or exit the application.
     *
     * @param stage the primary stage for displaying the menu
     * @param gameField the game field that represents the main game logic
     * @return the Scene object representing the main menu
     */
    public Scene createMenu(final Stage stage, final GameField gameField) {
        SoundPlayer.playBackgroundMusic();
        VBox menuBox = new VBox(20);
        menuBox.setAlignment(Pos.CENTER);

        Text title = new Text("Snake Game");
        title.setFont(Font.font(36));
        stage.setTitle("Menu");

        Button playBtn = new Button("Play");
        playBtn.setOnAction(e -> {
            try {
                gameField.startGame(stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        Button settingBtn = new Button("Setting");
        settingBtn.setOnAction(e -> {
            try {
                Setting setting = new Setting();
                Scene settingScene = setting.createSettingWindow(stage);
                stage.setScene(settingScene);
                stage.show();
            } catch (Exception ex) {
                throw  new RuntimeException(ex);
            }
        });

        Button exitBtn = new Button("Exit");
        exitBtn.setOnAction(e -> stage.close());

        menuBox.getChildren().addAll(title, playBtn, settingBtn, exitBtn);
        return new Scene(menuBox, 600, 600);
    }
}
