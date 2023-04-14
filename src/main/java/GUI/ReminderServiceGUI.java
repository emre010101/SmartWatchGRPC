package GUI;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import sw.Reminder.service2.Type;
import sw.Reminder.service2.TaskReminder;

public class ReminderServiceGUI {

    public static VBox createReminderServiceLayout(Button backButton, Button setTaskReminderButton, Button markTaskCompleteButton, TextArea serverMessageArea) {
        VBox layoutReminder = new VBox(10);
        layoutReminder.setPadding(new Insets(20, 20, 20, 20));

        // Set up date and time picker
        Label dateTimeLabel = new Label("Date and Time:");
        DateTimePicker dateTimePicker = new DateTimePicker();

        // Set up task name input
        Label taskNameLabel = new Label("Task Name:");
        TextField taskNameField = new TextField();

        // Set up type dropdown
        Label typeLabel = new Label("Type:");
        ComboBox<Type> typeComboBox = new ComboBox<>(FXCollections.observableArrayList(Type.values()));

        // Set up task list display
        Label taskListLabel = new Label("Task List:");
        ListView<TaskReminder> taskListView = new ListView<>();

        // Add elements to layout
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(dateTimeLabel, 0, 0);
        grid.add(dateTimePicker, 1, 0);
        grid.add(taskNameLabel, 0, 1);
        grid.add(taskNameField, 1, 1);
        grid.add(typeLabel, 0, 2);
        grid.add(typeComboBox, 1, 2);

        layoutReminder.getChildren().addAll(backButton, grid, setTaskReminderButton, taskListLabel, taskListView, markTaskCompleteButton, serverMessageArea);
        return layoutReminder;
    }
}
