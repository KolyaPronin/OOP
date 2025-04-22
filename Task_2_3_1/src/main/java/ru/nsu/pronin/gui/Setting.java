package ru.nsu.pronin.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import ru.nsu.pronin.data.SnakeData;

public class Setting {
    public Scene createSettingWindow(Stage stage){
        VBox settingBox = new VBox(20);
        settingBox.setAlignment(Pos.TOP_CENTER);

        Text title  = new Text("Game Setting");
        title.setFont(Font.font(20));
        stage.setTitle("Settings");

        HBox topBar = new HBox();
        topBar.setAlignment(Pos.TOP_LEFT);
        Button back  = new Button("⬅");
        back.setOnAction(e -> {
            try {
                GameMenu menu = new GameMenu();
                Scene menuScene = menu.createMenu(stage, new GameField());
                stage.setScene(menuScene);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        TextField speedTextField = new TextField();
        speedTextField.setMaxWidth(80);
        Button setSpeed = new Button("Set Speed");
        setSpeed.setOnAction(e -> {
            try{
                GameField.getPause().setDuration(Duration.millis(Integer.parseInt(speedTextField.getText())));
            } catch (Exception ex){
                throw new RuntimeException(ex);
            }
        });

        TextField numberOfPointForVictory = new TextField();
        numberOfPointForVictory.setMaxWidth(80);
        Button setPointForVictory = new Button("Set Number");
        setPointForVictory.setOnAction((e -> {
            try {
                SnakeData.setNumberOfPointForVictory(Integer.parseInt(numberOfPointForVictory.getText()));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }));


        topBar.getChildren().add(back);

        settingBox.getChildren().addAll(title, topBar, speedTextField,
                setSpeed,numberOfPointForVictory, setPointForVictory );
        return new Scene(settingBox, 600,600);
    }

}
