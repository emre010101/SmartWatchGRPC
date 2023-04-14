package GUI;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class StepServiceGUI {

    public static VBox createStepServiceLayout(Button buttonBackStep, Button startStep, Button stopStep, Button getLastHourStepsButton, Button getAverageHourlyStepsButton, Button setStepGoalButton, TextArea serverMessageArea) {
        VBox layoutStep = new VBox(20);

        Label title = new Label("Step Service");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        GridPane buttonGrid = new GridPane();
        buttonGrid.setAlignment(Pos.CENTER);
        buttonGrid.setHgap(20);
        buttonGrid.setVgap(10);

        // Add the buttons to the grid
        buttonGrid.add(startStep, 0, 0);
        buttonGrid.add(stopStep, 1, 0);
        buttonGrid.add(getLastHourStepsButton, 0, 1);
        buttonGrid.add(getAverageHourlyStepsButton, 1, 1);
        buttonGrid.add(setStepGoalButton, 0, 2);

        // Add the TextArea for server messages below the buttons
        serverMessageArea.setPrefSize(300, 200);
        serverMessageArea.setEditable(false);

        layoutStep.getChildren().addAll(buttonBackStep, title, buttonGrid, serverMessageArea);

        return layoutStep;
    }
}
