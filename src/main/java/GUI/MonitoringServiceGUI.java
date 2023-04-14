package GUI;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public class MonitoringServiceGUI {

    public static HashMap<String, Object> createMonitoringServiceLayout(Button backButton) {
        HashMap<String, Object> controls = new HashMap<>();

        VBox layoutMonitoring = new VBox(10);
        layoutMonitoring.setPadding(new Insets(20, 20, 20, 20));

        // Patient ID
        Label patientIdLabel = new Label("Patient ID:");
        TextField patientIdField = new TextField();

        // Age
        Label ageLabel = new Label("Age:");
        TextField ageField = new TextField();

        // Name
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        // Weight
        Label weightLabel = new Label("Weight:");
        TextField weightField = new TextField();

        // Height
        Label heightLabel = new Label("Height:");
        TextField heightField = new TextField();

        // Address
        Label addressLabel = new Label("Address:");
        TextField addressField = new TextField();

        // Emergency Contacts
        Label emergencyContactsLabel = new Label("Emergency Contacts:");

        Label contact1NameLabel = new Label("Name 1:");
        TextField contact1NameField = new TextField();

        Label contact1PhoneLabel = new Label("Phone 1:");
        TextField contact1PhoneField = new TextField();

        Label contact2NameLabel = new Label("Name 2:");
        TextField contact2NameField = new TextField();

        Label contact2PhoneLabel = new Label("Phone 2:");
        TextField contact2PhoneField = new TextField();

        // Server response area
        Label serverResponseLabel = new Label("Server Response:");
        TextArea serverResponseArea = new TextArea();
        serverResponseArea.setPrefWidth(250);
        serverResponseArea.setEditable(false);
        serverResponseArea.setWrapText(true);

        // Buttons
        Button saveUserCredentialsBtn = new Button("Save User Credentials");
        Button lookForUserBtn = new Button("Look for User");
        Button sendHeartRateBtn = new Button("Send Heart Rate");

        // Add elements to layout
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.addRow(0, patientIdLabel, patientIdField);
        grid.addRow(1, ageLabel, ageField);
        grid.addRow(2, nameLabel, nameField);
        grid.addRow(3, weightLabel, weightField);
        grid.addRow(4, heightLabel, heightField);
        grid.addRow(5, addressLabel, addressField);
        grid.addRow(6, contact1NameLabel, contact1NameField);
        grid.addRow(7, contact1PhoneLabel, contact1PhoneField);
        grid.addRow(8, contact2NameLabel, contact2NameField);
        grid.addRow(9, contact2PhoneLabel, contact2PhoneField);

        HBox buttons = new HBox(10, saveUserCredentialsBtn, lookForUserBtn, sendHeartRateBtn);
        layoutMonitoring.getChildren().addAll(backButton, grid, buttons, serverResponseLabel, serverResponseArea);

        controls.put("patientIdField", patientIdField);
        controls.put("ageField", ageField);
        controls.put("nameField", nameField);
        controls.put("weightField", weightField);
        controls.put("heightField", heightField);
        controls.put("addressField", addressField);
        controls.put("contact1NameField", contact1NameField);
        controls.put("contact1PhoneField", contact1PhoneField);
        controls.put("contact2NameField", contact2NameField);
        controls.put("contact2PhoneField", contact2PhoneField);
        controls.put("saveUserCredentialsBtn", saveUserCredentialsBtn);
        controls.put("lookForUserBtn", lookForUserBtn);
        controls.put("sendHeartRateBtn", sendHeartRateBtn);
        controls.put("serverResponseArea", serverResponseArea);
        controls.put("layoutMonitoring", layoutMonitoring);

        return controls;
        }

}
