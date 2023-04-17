package GUI;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class StepServiceGUI {

    public static VBox createStepServiceLayout(Button buttonBackStep, Button startStep, Button stopStep, Button getLastHourStepsButton, Button getAverageHourlyStepsButton, Button setStepGoalButton, TextArea serverMessageArea, TextField averageStepsPerMinuteField, TextField stepGoalField, ComboBox<String> averageHourlyStepsComboBox) {
        VBox layoutStep = new VBox(20);

        Label title = new Label("Step Service");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        GridPane buttonGrid = new GridPane();
        buttonGrid.setAlignment(Pos.CENTER);
        buttonGrid.setHgap(20);
        buttonGrid.setVgap(10);

        // Group start, stop buttons and average steps per minute text field together
        HBox startStopGroup = new HBox(10);
        startStopGroup.getChildren().addAll(startStep, stopStep, new Label("Average steps per minute:"), averageStepsPerMinuteField);

        // Group set step goal button and text field together
        HBox setStepGoalGroup = new HBox(10);
        setStepGoalGroup.getChildren().addAll(setStepGoalButton, new Label("Set step goal:"), stepGoalField);

        // Group get average hourly steps button and the drop-down list together
        HBox getAverageHourlyStepsGroup = new HBox(10);
        getAverageHourlyStepsGroup.getChildren().addAll(getAverageHourlyStepsButton, new Label("Average daily steps:"), averageHourlyStepsComboBox);

        // Add the groups to the grid
        buttonGrid.add(startStopGroup, 0, 0, 2, 1);
        buttonGrid.add(getLastHourStepsButton, 0, 1);
        buttonGrid.add(setStepGoalGroup, 0, 2, 2, 1);
        buttonGrid.add(getAverageHourlyStepsGroup, 0, 3, 2, 1);

        // Add the TextArea for server messages below the buttons
        serverMessageArea.setPrefSize(300, 200);
        serverMessageArea.setEditable(false);

        layoutStep.getChildren().addAll(buttonBackStep, title, buttonGrid, serverMessageArea);

        return layoutStep;
    }
}
