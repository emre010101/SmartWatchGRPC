package GUI;

import com.google.protobuf.Empty;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import com.google.protobuf.Empty;

import ClientSides.HeartRateStreamingResponse;
import ClientSides.StepStreamObserver;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.*;


import io.grpc.stub.StreamObserver;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import sw.Monitoring.service3.EmergencyContact;
import sw.Monitoring.service3.GetHealthRecordsRequest;
import sw.Monitoring.service3.GetHealthRecordsResponse;
import sw.Monitoring.service3.HeartRateRequest;
import sw.Monitoring.service3.PatientID;
import sw.Monitoring.service3.UserRecords;
import sw.Monitoring.service3.ServerResponse;

//import sw.Reminder.service2.ServerResponse;
import sw.Reminder.service2.TaskComplete;
import sw.Reminder.service2.TaskReminder;
import sw.Reminder.service2.Type;
import sw.stepCounter.service1.HourlyStepCount;
import sw.stepCounter.service1.HourlyStepRequest;
import sw.stepCounter.service1.StepCount;
import sw.stepCounter.service1.StepsRequest;
import sw.stepCounter.service1.WeekDays;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sw.Reminder.service2.Type;
import sw.Reminder.service2.TaskReminder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javafx.application.Platform;


@SuppressWarnings("restriction")
public class Main extends Application {

	//static ServiceManager serviceManager;
	
	//Stage and Scenes
	Stage window;
	Scene SceneMain, SceneStep, SceneReminder, SceneMonitoring;
	
	//Main Buttons
	Button button1, button2, button3, button4;
	
	//StepService Buttons
	Button buttonBackStep;
	
	//ReminderService Buttons
	Button buttonBackReminder, setTaskReminderButton, markTaskCompleteButton, getUnmarkedTasksButton;
	
	//Monitoring Buttons
	Button buttonBackMonitoring;
	
	private static AtomicBoolean isStreaming = new AtomicBoolean(false);
	private static AtomicBoolean sendHeartRateFlag = new AtomicBoolean(true);
	private static ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
	private static ListView<TaskReminder> taskListView;
	
	static TextArea serverMessageArea = new TextArea();
	static TextArea serverResponseArea = new TextArea();
	static TextArea MonitoringServerResponseArea = new TextArea();
	
	public static void main(String[] args) throws IOException {
		//ServiceManager serviceManager = new ServiceManager();
		//serviceManager.discoverServices();
		
		launch(args); //it sets up the javafx in application class
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//-----------------------------------Main Page Layout---------------------------------------------------
		window = primaryStage;
		window.setTitle("Smart Watch");
		
		window.setOnCloseRequest(e -> {
			e.consume();
			closeProgram();
		});
		
		Label labelMain = new Label("Welcome to Smart Watch!");
		
		button1 = new Button("Step Service");
		button2 = new Button("Reminder Service");
		button3 = new Button("Monitoring Service");
		button4 = new Button("Back to main");

		buttonBackStep = new Button("Back to main");
		buttonBackReminder = new Button("Back to main");
		buttonBackMonitoring = new Button("Back to main");
		
		//-------------------Initialising----------------------------Reminder PAge Layout---------------------------
		setTaskReminderButton = new Button("setTaskReminder");
		markTaskCompleteButton = new Button("Mark Complete");
		getUnmarkedTasksButton = new Button("Get Remaning Tasks");
		HashMap<String, Object> reminderControls = ReminderServiceGUI.createReminderServiceLayout(buttonBackReminder, setTaskReminderButton, markTaskCompleteButton, getUnmarkedTasksButton, serverMessageArea);
		
		//---------------------------------------------------------Event Listeners-----------------------------------
		
		//Main Page and main Buttons
		
		buttonBackStep.setOnAction(e -> {
			ServiceManager.shutdownChannel(1);
			window.setScene(SceneMain);
		});
		buttonBackReminder.setOnAction(e -> window.setScene(SceneMain));
		buttonBackMonitoring.setOnAction(e -> window.setScene(SceneMain));
		
		/*use of lambda to use event listener
		 *Since Even Listener interface only have one method "handle"
		 *lambda can be used. */
		button1.setOnAction(e -> {
			ServiceManager.initializeStepServiceChannel(); 
			window.setScene(SceneStep);
			});

		button2.setOnAction(e -> {
		    ServiceManager.initializeReminderServiceChannel();
		    window.setScene(SceneReminder);
		});
		button3.setOnAction(e -> {
			ServiceManager.initializeMonitoringChannel();
			window.setScene(SceneMonitoring);});
		button4.setOnAction(e -> window.setScene(SceneMain));
		

		
		//Main page layout
		VBox layoutMain = new VBox(50);
		layoutMain.getChildren().addAll(labelMain, button1, button2, button3);
		SceneMain = new Scene(layoutMain, 400, 400);
		
		// Add TextArea for displaying server messages
		serverMessageArea.setPrefSize(300, 200);
		serverMessageArea.setEditable(false);
		
		//------------------------------------------------Step Service Layout------------------------------------------------------------------------------------------------------
		Button startStep = new Button("Start");
		Button getLastHourStepsButton = new Button("Get Last Hour Steps");
		Button getAverageHourlyStepsButton = new Button("Get Average Hourly Steps");
		Button setStepGoalButton = new Button("Set Step Goal");
		startStep.setOnAction(e -> {
			try {
				stepStreamingRequest();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			isStreaming.set(true);
		});
		Button stopStep = new Button("Stop");
		stopStep.setOnAction(e -> {
			isStreaming.set(false);
		});
		getLastHourStepsButton.setOnAction(e -> {
		    lastHour();
		});

		getAverageHourlyStepsButton.setOnAction(e -> {
		    getAverage();
		});

		setStepGoalButton.setOnAction(e -> {
		    //setStepGoal();
		});
		
		VBox layoutStep = StepServiceGUI.createStepServiceLayout(buttonBackStep, startStep, stopStep, getLastHourStepsButton, getAverageHourlyStepsButton, setStepGoalButton, serverMessageArea);
		SceneStep = new Scene(layoutStep, 400, 400);
		
		//-----------------------------------------------Reminder Service Layout------------------------------------------------------------------------------------
		//StackPane layoutReminder = new StackPane();

		setTaskReminderButton.setOnAction(e -> {
		    try {
		        DatePicker datePicker = (DatePicker) reminderControls.get("datePicker");
		        TextField taskNameField = (TextField) reminderControls.get("taskNameField");
		        ComboBox<Type> typeComboBox = (ComboBox<Type>) reminderControls.get("typeComboBox");
		        Spinner<Integer> hourSpinner = (Spinner<Integer>) reminderControls.get("hourSpinner");
		        Spinner<Integer> minuteSpinner = (Spinner<Integer>) reminderControls.get("minuteSpinner");
		        setTaskReminder(datePicker, taskNameField, typeComboBox, hourSpinner, minuteSpinner);
		    } catch (InterruptedException e1) {
		        e1.printStackTrace();
		    }
		});
		markTaskCompleteButton.setOnAction(e -> {
		    taskListView = (ListView<TaskReminder>) reminderControls.get("taskListView");
		    TaskReminder selectedTask = taskListView.getSelectionModel().getSelectedItem();
		    if (selectedTask != null) {
		        try {
		            markTask(selectedTask);
		            taskListView.getItems().remove(selectedTask);
		        } catch (InterruptedException e1) {
		            e1.printStackTrace();
		        }
		    } else {
		        // Display a message to the user to select a task
		        serverResponseArea.appendText("Please select a task to mark as complete.\n");
		    }
		});
		
		getUnmarkedTasksButton.setOnAction(e ->{
		    taskListView = (ListView<TaskReminder>) reminderControls.get("taskListView");
		    getUndoneTasks(taskListView);
		});


		/**/
		//---Reminder Layout
		/*VBox layoutReminder = (VBox) reminderControls.get("layoutReminder");
	    SceneReminder = new Scene(layoutReminder, 400, 400);*/
	      VBox layoutReminder = (VBox) reminderControls.get("layoutReminder");
	      serverResponseArea = (TextArea) reminderControls.get("serverResponseArea");
	        
	        // Create a new HBox to wrap layoutReminder and serverResponseArea
	        HBox mainLayout = new HBox(10, layoutReminder, serverResponseArea);
	        
	        SceneReminder = new Scene(mainLayout, 800, 600);
		
	//----------------------------------------------------------------------------------------------------
	        HashMap<String, Object> controls = MonitoringServiceGUI.createMonitoringServiceLayout(buttonBackMonitoring);

	        VBox layoutMonitoring = (VBox) controls.get("layoutMonitoring");
	        MonitoringServerResponseArea = (TextArea) controls.get("serverResponseArea");
	        
	        
	        SceneMonitoring = new Scene(layoutMonitoring, 400, 400);

	        // You can access other controls in the HashMap like this:
	        Button saveUserCredentialsBtn = (Button) controls.get("saveUserCredentialsBtn");
	        // Add event listeners for buttons and other controls here.

	        saveUserCredentialsBtn.setOnAction(event -> {
	            TextField patientIdField = (TextField) controls.get("patientIdField");
	            int patientId = Integer.parseInt(patientIdField.getText());

	            TextField ageField = (TextField) controls.get("ageField");
	            int age = Integer.parseInt(ageField.getText());

	            TextField nameField = (TextField) controls.get("nameField");
	            String name = nameField.getText();

	            TextField weightField = (TextField) controls.get("weightField");
	            double weight = Double.parseDouble(weightField.getText());

	            TextField heightField = (TextField) controls.get("heightField");
	            double height = Double.parseDouble(heightField.getText());

	            TextField addressField = (TextField) controls.get("addressField");
	            String address = addressField.getText();

	            TextField contact1NameField = (TextField) controls.get("contact1NameField");
	            String contact1Name = contact1NameField.getText();

	            TextField contact1PhoneField = (TextField) controls.get("contact1PhoneField");
	            String contact1Phone = contact1PhoneField.getText();

	            TextField contact2NameField = (TextField) controls.get("contact2NameField");
	            String contact2Name = contact2NameField.getText();

	            TextField contact2PhoneField = (TextField) controls.get("contact2PhoneField");
	            String contact2Phone = contact2PhoneField.getText();

	            EmergencyContact emergContact1 = EmergencyContact.newBuilder().setName(contact1Name).setPhone(contact1Phone).build();
	            EmergencyContact emergContact2 = EmergencyContact.newBuilder().setName(contact2Name).setPhone(contact2Phone).build();

	            List<EmergencyContact> emergencies = new ArrayList<EmergencyContact>();
	            emergencies.add(emergContact1);
	            emergencies.add(emergContact2);

	            saveUserCredentials(patientId, age, name, weight, height, address, emergencies);
	        });

		
		
		window.setScene(SceneMain);
		window.setTitle("WELCOME");
		window.show();
		
	}

	private void closeProgram() {
		//Platform.runLater(() -> {

		Boolean answer = ConfirmBox.display("Closing", "Are you sure, want to close?");
		if(answer) {
			//ServiceManager.shutdownChannel(0);
			window.close();
		}
		//});
		
	}
	
	/*------------------------------------------------------------------------------------------------------------------------------*/
	public static void stepStreamingRequest() throws InterruptedException {
	    //System.out.println("-- Client side stepStreamingRequest Invoked");
	    CountDownLatch latch = new CountDownLatch(2);
	    StreamObserver<StepsRequest> streamObserver = ServiceManager.asyncStubService1.sendSteps(new StepStreamObserver(latch));

	    // Create a ScheduledExecutorService to send 25 steps every 1 minute
	    //executor = Executors.newSingleThreadScheduledExecutor();
	    executor.scheduleAtFixedRate(() -> {
	        if (isStreaming.get()) {
	            try {
	                StepsRequest stepsRequest = StepsRequest.newBuilder().setSteps(25).build();
	                streamObserver.onNext(stepsRequest);
	                System.out.println("Sent 25 steps");
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }, 0, 1, TimeUnit.MINUTES);

	}
	
	public static void lastHour() {
	    System.out.println("--ClientSide : lastHour() invoked");
	    try {
	        StepCount stepCount = ServiceManager.blockingStubService1.getLastHourSteps(Empty.getDefaultInstance());
	        System.out.println("Test in lasthour: " + stepCount.getCount());
	        serverMessageArea.appendText("The steps taken in the last hour: " + stepCount.getCount() + "\n");
	        Thread.sleep(1000);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    //ServiceManager.shutdownStepServiceChannel();
	}
	
	public static void getAverage() {
		System.out.println("getAverage invoked!!");
		WeekDays week = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("Please type from which day to now want to see the daily average"
				+ "/n last day, last 5 day, last 10 day, last 30 Day");
		
		while (true) {
			String period = sc.nextLine();
			if(period.equalsIgnoreCase("last day")) {
				week = WeekDays.LAST_DAY;
				break;
			}else if(period.equalsIgnoreCase("last 5 day")){
				week = WeekDays.LAST_5_DAYS;
				break;
			}else if(period.equalsIgnoreCase("last 10 day")){
				week = WeekDays.LAST_10_DAYS;
				break;
			}else if(period.equalsIgnoreCase("last 30 day")){
				week = WeekDays.LAST_30_DAYS;
				break;
			}else {
				System.out.println("Please type valid period!!");
			}
		}
		sc.close();
		
		//WeekDays.LAST_DAY
		
		HourlyStepRequest req = HourlyStepRequest.newBuilder().setWeekDays(week).build();
		HourlyStepCount response = ServiceManager.blockingStubService1.getAverageHourlySteps(req);
		System.out.println(
				"For the period: " + response.getWeekDays() + "\n"+
				"Average steps: " + response.getAverageSteps()+ "\n"+
				"Message: " + response.getMessage()
				);
	}
	/*------------------------------------------------------------------------------------------------------------------------------*/

	public static void setTaskReminder(DatePicker datePicker, TextField taskNameField, ComboBox<Type> typeComboBox, Spinner<Integer> hourSpinner, Spinner<Integer> minuteSpinner) throws InterruptedException {
	    LocalDate date = datePicker.getValue();
	    LocalTime time = LocalTime.of(hourSpinner.getValue(), minuteSpinner.getValue());
	    LocalDateTime dateTime = LocalDateTime.of(date, time);

	    String taskName = taskNameField.getText();
	    Type type = typeComboBox.getValue();

	    TaskReminder req = TaskReminder.newBuilder().setDateTime(dateTime.toString()).setTaskName(taskName).setType(type).build();
	    
	    sw.Reminder.service2.ServerResponse response = ServiceManager.blockingStubService2.setTaskReminder(req);
	    System.out.println("Service message: " + response.getConfirmed());
	    Platform.runLater(()->{
	    	
			taskListView.getItems().add(req);
		    serverResponseArea.appendText("Server message: " + response.getConfirmed() + "\n");

	    });
	    Thread.sleep(150);
	}

	public static void markTask(TaskReminder task) throws InterruptedException {
	    String name = task.getTaskName();
	    TaskComplete req = TaskComplete.newBuilder().setTaskName(name).build();
	    sw.Reminder.service2.ServerResponse response = ServiceManager.blockingStubService2.markTaskComplete(req);
	    System.out.println(
	            "Service message: " + response.getConfirmed()
	    );
	    Platform.runLater(()->{
		    serverResponseArea.appendText("Server message: " + response.getConfirmed());

	    });
	    Thread.sleep(100);
	}
	
	public static void getUndoneTasks(ListView<TaskReminder> taskListView) {
		StreamObserver<TaskReminder> responseObserver = new StreamObserver<TaskReminder>() {

			@Override
			public void onNext(TaskReminder reminder) {
				System.out.println(reminder.getTaskName());
				System.out.println(reminder.getDateTime());
				Platform.runLater(()->{
					taskListView.getItems().add(reminder);
				});
			}
			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub	
			}
		};
		ServiceManager.asyncStubService2.getTaskList(null, responseObserver);
	}
	
	
	/*------------------------------------------Monitoring Service Client Side Methods-----------------------------------------------------------------------*/
	public static void saveUserCredentials(int p_id, int p_age, String nam, double we, double he, String add, List<EmergencyContact> emergencies) {
		
		UserRecords reply = UserRecords.newBuilder().setPatientId(p_id).setAge(p_age).setName(nam).setWeight(we)
				.setHeight(he).setAddress(add).addAllContacts(emergencies).build();

		ServerResponse response = ServiceManager.blockingStubService3.setUserRecords(reply);

		System.out.println("Server message : " + response.getConfirmed());
		Platform.runLater(() ->{
			MonitoringServerResponseArea.appendText(response.getConfirmed());
		});

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void lookForUser(int id) {
		PatientID patient = PatientID.newBuilder().setPatientId(id).build();
		GetHealthRecordsRequest reply = GetHealthRecordsRequest.newBuilder().setPatientId(patient).build();
		GetHealthRecordsResponse response = ServiceManager.blockingStubService3.getHealthRecords(reply);
		UserRecords user = response.getUserRecords();
		System.out.println(
				"ClientSide: receiving the User Details: " + user.getName() +", " +  user.getPatientId() + ", " + user.getAge()
				+", " + user.getAddress() + ", " + user.getWeight() + ", " + user.getHeight()
				);
		List<EmergencyContact> contactsList = user.getContactsList();
		System.out.println("Emergency Contacts:");
		for (EmergencyContact contact : contactsList) {
			System.out.println("Name: " + contact.getName() + ", Phone: " + contact.getPhone());
			}
	}
	
	//to change the global variable to false to stop send heart rate
	/*public static void stopSendHeartRate() {
	    sendHeartRateFlag.set(false);
	}*/
	
	public static void sendHearRate() {
		HeartRateStreamingResponse response = new HeartRateStreamingResponse();
		StreamObserver<HeartRateRequest> requestStreamObserver = ServiceManager.asyncStubService3.monitorHeartRate(response);
		
		while (sendHeartRateFlag.get()) { 
			try {
				PatientID patient_id = PatientID.newBuilder().setPatientId(2).build();
				HeartRateRequest request = HeartRateRequest.newBuilder()
								.setPatientId(patient_id)
								.setHeartRate(ThreadLocalRandom.current().nextDouble(50, 180))
								.build();
				requestStreamObserver.onNext(request);
				try {
					Thread.sleep(750);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}catch(RuntimeException e) {
				e.printStackTrace();
			}
				
		}requestStreamObserver.onCompleted();
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}


	
	
