package ru.nsu.pronin.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import ru.nsu.pronin.data.FieldData;
import ru.nsu.pronin.data.SnakeData;

public class Setting {

    public Scene createSettingWindow(Stage stage) {
        VBox settingBox = new VBox(20);
        settingBox.setAlignment(Pos.TOP_CENTER);

        Text title = new Text("Game Settings");
        title.setFont(Font.font(20));
        stage.setTitle("Settings");

        // Кнопка "Назад" для возврата в меню
        HBox topBar = new HBox();
        topBar.setAlignment(Pos.TOP_LEFT);
        Button back = new Button("⬅");
        back.setOnAction(e -> {
            try {
                GameMenu menu = new GameMenu();
                Scene menuScene = menu.createMenu(stage, new GameField());
                stage.setScene(menuScene);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        // Поле для изменения скорости игры
        TextField speedTextField = new TextField();
        speedTextField.setMaxWidth(80);
        Button setSpeed = new Button("Set Speed");
        setSpeed.setOnAction(e -> {
            try {
                GameField.getPause().setDuration(Duration.millis(Integer.parseInt(speedTextField.getText())));
            } catch (Exception ex) {
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

        Label levelLabel = new Label("Select Level:");
        ToggleGroup levelGroup = new ToggleGroup();

        RadioButton level1 = new RadioButton("Level 1");
        level1.setToggleGroup(levelGroup);

        RadioButton level2 = new RadioButton("Level 2");
        level2.setToggleGroup(levelGroup);

        RadioButton level3 = new RadioButton("Level 3");
        level3.setToggleGroup(levelGroup);

        RadioButton level4 = new RadioButton("Level 4");
        level4.setToggleGroup(levelGroup);

        RadioButton level5 = new RadioButton("Level 5");
        level5.setToggleGroup(levelGroup);

        RadioButton classicLevel = new RadioButton("Classic");
        classicLevel.setToggleGroup(levelGroup);

        CheckBox abilityPassThroughWalls = new CheckBox("abilityPassThroughTheWalls");
        abilityPassThroughWalls.setOnAction(event -> {
            FieldData.setAbilityPassThroughWalls(abilityPassThroughWalls.isSelected());
        });
        abilityPassThroughWalls.setSelected(FieldData.isAbilityPassThroughWalls());

        level1.setUserData(1);
        level2.setUserData(2);
        level3.setUserData(3);
        level4.setUserData(4);
        level5.setUserData(5);
        classicLevel.setUserData(6);

        levelGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                int selectedLevel = (int) ((RadioButton) newValue).getUserData();
                FieldData.setLevel(selectedLevel);
            }
        });


        switch (FieldData.numberOfLevel) {
            case 1:
                level1.setSelected(true);
                break;
            case 2:
                level2.setSelected(true);
                break;
            case 3:
                level3.setSelected(true);
                break;
            case 4:
                level4.setSelected(true);
                break;
            case 5:
                level5.setSelected(true);
                break;
            case 6:
                classicLevel.setSelected(true);
                break;
        }

        VBox levelBox = new VBox(10, level1, level2, level3, level4, level5, classicLevel, abilityPassThroughWalls);
        levelBox.setAlignment(Pos.CENTER);
        topBar.getChildren().add(back);
        settingBox.getChildren().addAll(title, topBar, speedTextField,
                setSpeed, numberOfPointForVictory, setPointForVictory, levelLabel, levelBox);
        return new Scene(settingBox, 600, 600);
    }
}