package GUI;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import sw.Reminder.service2.Type;
import sw.Reminder.service2.TaskReminder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class ReminderServiceGUI {
	@SuppressWarnings("restriction")
	public static HashMap<String, Object> createReminderServiceLayout(Button backButton, Button setTaskReminderButton, Button markTaskCompleteButton, Button getUnmarkedTasks, TextArea serverResponse) {
	    HashMap<String, Object> controls = new HashMap<>();
	    VBox layoutReminder = new VBox(10);
	    layoutReminder.setPadding(new Insets(20, 20, 20, 20));

	    // Add title
	    Label titleLabel = new Label("Reminder Service");
	    titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

	    // Set up date picker
	    Label dateLabel = new Label("Date:");
	    DatePicker datePicker = new DatePicker();

	    // Set up time picker using Spinners for hours and minutes
	    Label timeLabel = new Label("Time:");
	    Spinner<Integer> hourSpinner = new Spinner<>(0, 23, 0);
	    Spinner<Integer> minuteSpinner = new Spinner<>(0, 59, 0);
	    HBox timePickerBox = new HBox(5, hourSpinner, new Label(":"), minuteSpinner);

	    // Set up task name input
	    Label taskNameLabel = new Label("Task Name:");
	    TextField taskNameField = new TextField();

	    // Set up type dropdown
	    Label typeLabel = new Label("Type:");
	    ComboBox<Type> typeComboBox = new ComboBox<>(FXCollections.observableArrayList(Type.values()));

	    // Set up task list display
	    Label taskListLabel = new Label("Task List:");
	    ListView<TaskReminder> taskListView = new ListView<>();

	    // Set up server response area
	    Label serverResponseLabel = new Label("Server Response:");
	    TextArea serverResponseArea = new TextArea();
	    serverResponseArea.setPrefWidth(250);
	    serverResponseArea.setPrefHeight(100);
	    serverResponseArea.setEditable(false);
	    serverResponseArea.setWrapText(true);

	    // Add elements to layout
	    GridPane grid = new GridPane();
	    grid.setHgap(10);
	    grid.setVgap(10);
	    grid.add(dateLabel, 0, 0);
	    grid.add(datePicker, 1, 0);
	    grid.add(timeLabel, 0, 1);
	    grid.add(timePickerBox, 1, 1);
	    grid.add(taskNameLabel, 0, 2);
	    grid.add(taskNameField, 1, 2);
	    grid.add(typeLabel, 0, 3);
	    grid.add(typeComboBox, 1, 3);

	    HBox buttons = new HBox(10, setTaskReminderButton);
	    HBox lowerButtons = new HBox(10, getUnmarkedTasks, markTaskCompleteButton);
	    HBox taskArea = new HBox(10, taskListView);
	    VBox lowerSection = new VBox(10, lowerButtons, serverResponseLabel, serverResponseArea);
	    layoutReminder.getChildren().addAll(titleLabel, backButton, grid, buttons, taskListLabel, taskArea, lowerSection);

	    controls.put("taskNameField", taskNameField);
	    controls.put("typeComboBox", typeComboBox);
	    controls.put("datePicker", datePicker);
	    controls.put("hourSpinner", hourSpinner);
	    controls.put("minuteSpinner", minuteSpinner);
	    controls.put("layoutReminder", layoutReminder);
	    controls.put("taskListView", taskListView);
	    controls.put("serverResponseArea", serverResponseArea);

	    return controls;
	}

}
