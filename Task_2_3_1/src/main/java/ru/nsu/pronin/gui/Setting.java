package ru.nsu.pronin.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Setting {
    public Scene createSettingWindow(Stage stage){
        VBox  settingBox = new VBox(20);
        settingBox.setAlignment(Pos.TOP_CENTER);

        Text title  = new Text("Game Setting");
        title.setFont(Font.font(20));

        HBox topBar = new HBox();
        topBar.setAlignment(Pos.TOP_LEFT);
        Button Back  = new Button("<-");
        Back.setOnAction(e -> {
            try {
                GameMenu menu = new GameMenu();
                Scene menuScene = menu.createMenu(stage, new GameField());
                // вернуть menuScene из GameField
                stage.setScene(menuScene);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        topBar.getChildren().add(Back);

        settingBox.getChildren().addAll(title, topBar);
        return new Scene(settingBox, 600,600);
    }

}
