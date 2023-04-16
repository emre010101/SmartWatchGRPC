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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.geometry.Pos;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import java.util.HashMap;

public class MonitoringServiceGUI {

	public static HashMap<String, Object> createMonitoringServiceLayout(Button backButton) {
	    HashMap<String, Object> controls = new HashMap<>();

	    VBox layoutMonitoring = new VBox(10);
	    layoutMonitoring.setPadding(new Insets(20, 20, 20, 20));

	    // Title and back button
	    Label title = new Label("Monitoring Service");
	    title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
	    HBox titleHBox = new HBox(10);
	    titleHBox.getChildren().addAll(backButton, title);
	    
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
        
        Label emergency = new Label("Emergency Contacts:");

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
        Button stopBtn = new Button("Stop");


        // Add elements to layout
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.addRow(0, ageLabel, ageField, nameLabel, nameField);
        grid.addRow(1, weightLabel, weightField, heightLabel, heightField);
        grid.addRow(3, addressLabel, addressField);
        grid.addRow(4, emergency);
        grid.addRow(5, contact1NameLabel, contact1NameField, contact1PhoneLabel, contact1PhoneField);
        grid.addRow(6, contact2NameLabel, contact2NameField, contact2PhoneLabel, contact2PhoneField);

        // Look for user elements
        TextField lookForUserNameField = new TextField();
        lookForUserNameField.setPromptText("Name or Patient-ID");

        VBox lookForUserVBox = new VBox(10);
        lookForUserVBox.getChildren().addAll(lookForUserNameField, lookForUserBtn);


       /* HBox lookForUserHBox = new HBox(10);
        lookForUserHBox.setAlignment(Pos.BASELINE_RIGHT);
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        lookForUserHBox.getChildren().addAll(spacer, lookForUserLabel, lookForUserNameField, lookForUserBtn);*/

        // Heart rate elements
        Label heartRateLabel1 = new Label("Min Heart Rate:");
        TextField heartRateField1 = new TextField();

        Label heartRateLabel2 = new Label("Max Heart Rate:");
        TextField heartRateField2 = new TextField();

        HBox heartRateHBox = new HBox(10);
        heartRateHBox.getChildren().addAll(heartRateLabel1, heartRateField1, heartRateLabel2, heartRateField2, sendHeartRateBtn, stopBtn);

        // Add elements to layout
        layoutMonitoring.getChildren().addAll(titleHBox, grid, saveUserCredentialsBtn, lookForUserVBox, heartRateHBox, serverResponseLabel, serverResponseArea);

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
        controls.put("lookForUserNameField", lookForUserNameField);
        controls.put("heartRateField1", heartRateField1);
        controls.put("heartRateField2", heartRateField2);
        controls.put("stopheart", stopBtn);
        
        return controls;
    }


}
