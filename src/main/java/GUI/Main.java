package GUI;

import com.google.protobuf.Empty;
import sw.Monitoring.service3.HeartRateWarning;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import javafx.collections.FXCollections;

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
import sw.Monitoring.service3.UserRecords;
import sw.Monitoring.service3.ServerResponse;
import sw.Reminder.service2.TaskComplete;
import sw.Reminder.service2.TaskReminder;
import sw.Reminder.service2.Type;
import sw.stepCounter.service1.HourlyStepCount;
import sw.stepCounter.service1.HourlyStepRequest;
import sw.stepCounter.service1.StepCount;
import sw.stepCounter.service1.StepGoal;
import sw.stepCounter.service1.StepsRequest;
import sw.stepCounter.service1.WeekDays;
import sw.stepCounter.service1.StepGoalResponse;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import javafx.application.Platform;

@SuppressWarnings("restriction")
public class Main extends Application {

	// Stage and Scenes
	Stage window;
	Scene SceneMain, SceneStep, SceneReminder, SceneMonitoring;
    Map<Scene, String> sceneIds = new HashMap<>(); //For identifying the scene is being shut down

	// Main Buttons
	Button button1, button2, button3, button4;

	// StepService Buttons
	Button buttonBackStep, startStep, stopStep, getLastHourStepsButton, getAverageHourlyStepsButton, setStepGoalButton;

	// ReminderService Buttons
	Button buttonBackReminder, setTaskReminderButton, markTaskCompleteButton, getUnmarkedTasksButton;

	// Monitoring Buttons
	Button buttonBackMonitoring, saveUserCredentialsBtn, lookForUser, sendHeartRate, stopHeart;

	private ScheduledExecutorService scheduledExecutorService;
	private static ListView<TaskReminder> taskListView;
	private StreamObserver<HeartRateRequest> requestStreamObserverHeart;
	private StreamObserver<StepsRequest> requestStreamObserverStep;

	static TextArea StepServerResponseArea = new TextArea();
	static TextArea ReminderServerResponseArea = new TextArea();
	static TextArea MonitoringServerResponseArea = new TextArea();

	public static void main(String[] args) throws IOException {
		// ServiceManager serviceManager = new ServiceManager();
		// serviceManager.discoverServices();

		launch(args); // it sets up the javafx in application class
	}

	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage primaryStage) throws Exception {

        // Set IDs for your scenes
        sceneIds.put(SceneMain, "SceneMain");
        sceneIds.put(SceneStep, "SceneStep");
        sceneIds.put(SceneReminder, "SceneReminder");
        sceneIds.put(SceneMonitoring, "SceneMonitoring");
		// -----------------------------------Main Page
		// Layout---------------------------------------------------
		window = primaryStage;
		window.setTitle("Smart Watch");
		/*
		 * window.setWidth(500); window.setHeight(500);
		 */

		Label labelMain = new Label("Welcome to Smart Watch!");

		button1 = new Button("Step Service");
		button2 = new Button("Reminder Service");
		button3 = new Button("Monitoring Service");
		button4 = new Button("Back to main");

		buttonBackStep = new Button("Back to main");
		buttonBackReminder = new Button("Back to main");
		buttonBackMonitoring = new Button("Back to main");

		// ---------------------------------------------------------Event
		// Listeners-----------------------------------

		// Main Page and main Buttons

		buttonBackStep.setOnAction(e -> {
			System.out.println(sceneIds.get(window.getScene()));
			ServiceManager.shutdownStepChannel();
			window.setScene(SceneMain);
		});
		buttonBackReminder.setOnAction(e -> {
			ServiceManager.shutdownReminderChannel();
			window.setScene(SceneMain);
		});
		buttonBackMonitoring.setOnAction(e -> {
			ServiceManager.shutdownMonitoringChannel();
			window.setScene(SceneMain);
		});

		/*
		 * use of lambda to use event listener Since Even Listener interface only have
		 * one method "handle" lambda can be used.
		 */
		button1.setOnAction(e -> {
			ServiceManager.discover("stepService");
			window.setScene(SceneStep);
		});

		button2.setOnAction(e -> {
			ServiceManager.discover("reminderingService");
			window.setScene(SceneReminder);
		});
		button3.setOnAction(e -> {
			ServiceManager.discover("monitoringService");
			window.setScene(SceneMonitoring);
		});
		button4.setOnAction(e -> window.setScene(SceneMain));

		// Main page layout
		VBox layoutMain = new VBox(50);
		layoutMain.getChildren().addAll(labelMain, button1, button2, button3);
		SceneMain = new Scene(layoutMain, 500, 500);


		// ------------------------------------------------Step Service
		// Layout------------------------------------------------------------------------------------------------------
		startStep = new Button("Start");
		stopStep = new Button("Stop");
		getLastHourStepsButton = new Button("Get Last Hour Steps");
		getAverageHourlyStepsButton = new Button("Get Average Daily Steps");
		setStepGoalButton = new Button("Set Step Goal");
		TextField averageStepsPerMinuteField = new TextField();
		TextField stepGoalField = new TextField();
		ComboBox<String> averageDailyStepsComboBox = new ComboBox<>(
				FXCollections.observableArrayList("Last day", "Last 5 days", "Last 10 days", "Last 30 days"));

		VBox stepServiceLayout = StepServiceGUI.createStepServiceLayout(buttonBackStep, startStep, stopStep,
				getLastHourStepsButton, getAverageHourlyStepsButton, setStepGoalButton, StepServerResponseArea,
				averageStepsPerMinuteField, stepGoalField, averageDailyStepsComboBox);

		startStep.setOnAction(event -> {
			try {
				int typedStepPerMinute = Integer.parseInt(averageStepsPerMinuteField.getText());
				startSendingSteps(typedStepPerMinute);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		});

		stopStep.setOnAction(e -> stopSendingSteps());

		getLastHourStepsButton.setOnAction(e -> lastHour());

		getAverageHourlyStepsButton.setOnAction(e -> {
			// System.out.println(averageHourlyStepsComboBox);
			String period = averageDailyStepsComboBox.getValue();
			// System.out.println("TEST: " + period);
			getAverage(period);
		});

		setStepGoalButton.setOnAction(e -> {
			int stepGoal = Integer.parseInt(stepGoalField.getText());
			setStepGoal(stepGoal);
		});

		SceneStep = new Scene(stepServiceLayout, 500, 500);

		// -----------------------------------------------Reminder Service
		// Layout------------------------------------------------------------------------------------

		setTaskReminderButton = new Button("setTaskReminder");
		markTaskCompleteButton = new Button("Mark Complete");
		getUnmarkedTasksButton = new Button("Get Remaning Tasks");
		HashMap<String, Object> reminderControls = ReminderServiceGUI.createReminderServiceLayout(buttonBackReminder,
				setTaskReminderButton, markTaskCompleteButton, getUnmarkedTasksButton, ReminderServerResponseArea);

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
				ReminderServerResponseArea.appendText("Please select a task to mark as complete.\n");
			}
		});

		getUnmarkedTasksButton.setOnAction(e -> {
			taskListView = (ListView<TaskReminder>) reminderControls.get("taskListView");
			getUndoneTasks(taskListView);
		});

		/**/
		// ---Reminder Layout
		/*
		 * VBox layoutReminder = (VBox) reminderControls.get("layoutReminder");
		 * SceneReminder = new Scene(layoutReminder, 400, 400);
		 */
		VBox layoutReminder = (VBox) reminderControls.get("layoutReminder");
		ReminderServerResponseArea = (TextArea) reminderControls.get("serverResponseArea");

		// Create a new HBox to wrap layoutReminder and serverResponseArea
		HBox mainLayout = new HBox(10, layoutReminder, ReminderServerResponseArea);

		SceneReminder = new Scene(mainLayout, 800, 600);

		// ----------------------------------------------------------------------------------------------------
		HashMap<String, Object> controls = MonitoringServiceGUI.createMonitoringServiceLayout(buttonBackMonitoring);

		VBox layoutMonitoring = (VBox) controls.get("layoutMonitoring");
		MonitoringServerResponseArea = (TextArea) controls.get("serverResponseArea");

		SceneMonitoring = new Scene(layoutMonitoring, 900, 700);

		// can access other controls in the HashMap like this:
		saveUserCredentialsBtn = (Button) controls.get("saveUserCredentialsBtn");
		lookForUser = (Button) controls.get("lookForUserBtn");
		sendHeartRate = (Button) controls.get("sendHeartRateBtn");
		stopHeart = (Button) controls.get("stopheart");

		// Add event listeners for buttons and other controls here.

		saveUserCredentialsBtn.setOnAction(event -> {

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

			EmergencyContact emergContact1 = EmergencyContact.newBuilder().setName(contact1Name).setPhone(contact1Phone)
					.build();
			EmergencyContact emergContact2 = EmergencyContact.newBuilder().setName(contact2Name).setPhone(contact2Phone)
					.build();

			List<EmergencyContact> emergencies = new ArrayList<EmergencyContact>();
			emergencies.add(emergContact1);
			emergencies.add(emergContact2);

			saveUserCredentials(age, name, weight, height, address, emergencies);
		});

		lookForUser.setOnAction(event -> {
			TextField lookforNameID = (TextField) controls.get("lookForUserNameField");
			String lokforUser = lookforNameID.getText();
			if (isDigit(lokforUser)) {
				lookForUser(Integer.parseInt(lokforUser));
			} else {
				lookForUser(lokforUser);
			}
		});

		sendHeartRate.setOnAction(event -> {
			TextField heart1 = (TextField) controls.get("heartRateField1");
			String heart1St = heart1.getText();
			TextField heart2 = (TextField) controls.get("heartRateField2");
			String heart2St = heart2.getText();
			TextField lookforNameID = (TextField) controls.get("lookForUserNameField");
			String lokforUser = lookforNameID.getText();
			if (isDigit(lokforUser)) {
				Platform.runLater(() -> {
					AlertBox.display("No Digit", "Plase change the user id with name!!");
				});
			} else {
				startSendHeartRate(Integer.parseInt(heart1St), Integer.parseInt(heart2St), lokforUser);
			}

		});

		stopHeart.setOnAction(e -> {
			try {
				stopSendingHeartRate();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		});// sendHeartRateFlag.set(false));

		// ----------------------------------------------------------------------------------------
		window.setOnCloseRequest(e -> {
			e.consume();
			closeProgram();
		});

		window.setScene(SceneMain);
		window.setTitle("WELCOME");
		window.show();

	}

	// ----------------------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------------------

	private boolean isDigit(String lokforUser) {
		try {
			Integer.parseInt(lokforUser);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private void closeProgram() {
	    Boolean answer = ConfirmBox.display("Closing", "Are you sure, want to close?");
	    if (answer) {
	        Platform.runLater(() -> {
	            window.close();
	        });
	    }
	}

	public void startExecutorService() {
		if (scheduledExecutorService == null || scheduledExecutorService.isShutdown()) {
			scheduledExecutorService = Executors.newScheduledThreadPool(1);
		}
	}

	public void shutdownExecutorService() {
		if (scheduledExecutorService != null) {
			scheduledExecutorService.shutdown();
		}
	}

	/*---------------------------------------------------Step Service Client Side Methods------------------------------------------------------------*/
	// ----------------------------------------------------------------------------------------------------------------

	private void startSendingSteps(int averageStep) {
		shutdownExecutorService(); // Just in case shutting down every time before starting
		startExecutorService();
		StreamObserver<StepCount> responseObserver = createResponseObserverforStep();
		requestStreamObserverStep = ServiceManager.asyncStubService1.sendSteps(responseObserver);
		scheduledExecutorService.scheduleAtFixedRate(() -> {
			stepStreamingRequest(averageStep, requestStreamObserverStep);
		}, 0, 60, TimeUnit.SECONDS);
	}

	private void stopSendingSteps() {
		shutdownExecutorService();
		requestStreamObserverStep.onCompleted();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Stream Observed is separated from the main method to avoid creating each time
	 * method is invoked instead it will be invoked one and with using the same
	 * instance multiple requests will be sent
	 */
	private StreamObserver<StepCount> createResponseObserverforStep() {
		return new StreamObserver<StepCount>() {
			@Override
			public void onNext(StepCount value) {
				Platform.runLater(() -> {
					int step = value.getCount();
					StepServerResponseArea.appendText("\n" + Integer.toString(step));
				});
			}

			@Override
			public void onError(Throwable t) {
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onCompleted() {
				Platform.runLater(() -> {
					StepServerResponseArea.appendText("CS: Server Completes...");
				});
			}

		};
	}

	/*
	 * Using scheduleded scheduler thos ,ethod will be invoked the sent steps
	 * periodically with the amount of steps typed by the user
	 */
	public static void stepStreamingRequest(int stepAverageinMinute, StreamObserver<StepsRequest> streamObserver) {
		try {
			StepsRequest stepsRequest = StepsRequest.newBuilder().setSteps(stepAverageinMinute).build();
			streamObserver.onNext(stepsRequest);
			Platform.runLater(() -> {
				StepServerResponseArea.appendText(stepAverageinMinute + ": steps sent" + "\n");
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* The steps have been made in the last hour will be collected */
	public static void lastHour() {
		System.out.println("CS: lastHour() invoked");
		try {
			StepCount stepCount = ServiceManager.blockingStubService1.getLastHourSteps(Empty.getDefaultInstance());
			System.out.println("Test in lasthour: " + stepCount.getCount());
			Platform.runLater(() -> {
				StepServerResponseArea.appendText("\n"+"The steps taken in the last hour: " + stepCount.getCount() + "\n");
			});
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ServiceManager.shutdownStepServiceChannel();
	}

	/* User can choose the period want to see for steps he/she has done */
	public static void getAverage(String period) {
		System.out.println("CS: getAverage() invoked!");
		WeekDays week = null;

		if (period.equalsIgnoreCase("last day")) {
			week = WeekDays.LAST_DAY;
		} else if (period.equalsIgnoreCase("last 5 days")) {
			week = WeekDays.LAST_5_DAYS;
		} else if (period.equalsIgnoreCase("last 10 days")) {
			week = WeekDays.LAST_10_DAYS;
		} else if (period.equalsIgnoreCase("last 30 days")) {
			week = WeekDays.LAST_30_DAYS;
		} else {
			week = WeekDays.LAST_5_DAYS;
		}

		HourlyStepRequest req = HourlyStepRequest.newBuilder().setWeekDays(week).build();
		HourlyStepCount response = ServiceManager.blockingStubService1.getAverageHourlySteps(req);
		String serverMessage = "\n" + "For the period: " + response.getWeekDays() + "\n" + "Average steps: "
				+ response.getAverageSteps() + "\n" + "Message: " + response.getMessage();
		Platform.runLater(() -> {
			StepServerResponseArea.appendText(serverMessage);
		});
	}

	public static void setStepGoal(int goal) {
		System.out.println("CS: setStepGoal is invoked!!");
		StepGoal req = StepGoal.newBuilder().setGoal(goal).build();
		StreamObserver<StepGoalResponse> responseObserver = new StreamObserver<StepGoalResponse>() {

			@Override
			public void onNext(StepGoalResponse value) {
				System.out.println(value.getLeft());
				System.out.println(value.getMessage());
				System.out.println(value.getSuccess());
				Platform.runLater(() -> {
					StepServerResponseArea.appendText("\n" + "Completed: " + value.getSuccess() + " || " + "Left: "
							+ value.getLeft() + "\n" + value.getMessage());
				});
			}

			@Override
			public void onError(Throwable t) {
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onCompleted() {
				Platform.runLater(() -> {
					StepServerResponseArea.appendText("CS: setStepGaol Completed..");
				});
			}
		};
		ServiceManager.asyncStubService1.setStepGoal(req, responseObserver);
	}
	/*--------------------------------------------------Reminder Service Client Side Methods----------------------------------------------------------------------------*/
	// ----------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------

	public static void setTaskReminder(DatePicker datePicker, TextField taskNameField, ComboBox<Type> typeComboBox,
		Spinner<Integer> hourSpinner, Spinner<Integer> minuteSpinner) throws InterruptedException {
		LocalDate date = datePicker.getValue();
		LocalTime time = LocalTime.of(hourSpinner.getValue(), minuteSpinner.getValue());
		LocalDateTime dateTime = LocalDateTime.of(date, time);

		String taskName = taskNameField.getText();
		Type type = typeComboBox.getValue();

		TaskReminder req = TaskReminder.newBuilder().setDateTime(dateTime.toString()).setTaskName(taskName)
				.setType(type).build();

		sw.Reminder.service2.ServerResponse response = ServiceManager.blockingStubService2.setTaskReminder(req);
		System.out.println("Service message: " + response.getConfirmed());
		Platform.runLater(() -> {

			taskListView.getItems().add(req);
			ReminderServerResponseArea.appendText("Server message: " + response.getConfirmed() + "\n");

		});
	}

	public static void markTask(TaskReminder task) throws InterruptedException {
		String name = task.getTaskName();
		TaskComplete req = TaskComplete.newBuilder().setTaskName(name).build();
		sw.Reminder.service2.ServerResponse response = ServiceManager.blockingStubService2.markTaskComplete(req);
		System.out.println("Service message: " + response.getConfirmed());
		Platform.runLater(() -> {
			ReminderServerResponseArea.appendText("Server message: " + response.getConfirmed());

		});
	}

	public static void getUndoneTasks(ListView<TaskReminder> taskListView) {
		StreamObserver<TaskReminder> responseObserver = new StreamObserver<TaskReminder>() {

			@Override
			public void onNext(TaskReminder reminder) {
				System.out.println(reminder.getTaskName());
				System.out.println(reminder.getDateTime());
				Platform.runLater(() -> {
					taskListView.getItems().add(reminder);
				});
			}

			@Override
			public void onError(Throwable t) {
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

			@Override
			public void onCompleted() {
				System.out.println("Server completes..");
			}
		};
		ServiceManager.asyncStubService2.getTaskList(null, responseObserver);
	}

	/*------------------------------------------Monitoring Service Client Side Methods--------------------------------------------------*/
	// ----------------------------------------------------------------------------------------------------

	public static void saveUserCredentials(int p_age, String nam, double we, double he, String add,
			List<EmergencyContact> emergencies) {

		UserRecords reply = UserRecords.newBuilder().setAge(p_age).setName(nam).setWeight(we).setHeight(he)
				.setAddress(add).addAllContacts(emergencies).build();

		ServerResponse response = ServiceManager.blockingStubService3.setUserRecords(reply);

		System.out.println("Server message : " + response.getConfirmed());
		Platform.runLater(() -> {
			MonitoringServerResponseArea.appendText("\n"+response.getConfirmed());
		});

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void lookForUser(int id) {
		// PatientID patient = PatientID.newBuilder().setPatientId(id).build();
		GetHealthRecordsRequest reply = GetHealthRecordsRequest.newBuilder().setPatientId(id).build();
		GetHealthRecordsResponse response = ServiceManager.blockingStubService3.getHealthRecords(reply);
		UserRecords user = response.getUserRecords();

		List<EmergencyContact> contactsList = user.getContactsList();
		Platform.runLater(() -> {
			MonitoringServerResponseArea.appendText("ClientSide: receiving the User Details: " + user.getName() + ", "
					+ user.getAge() + ", " + user.getAddress() + ", " + user.getWeight() + ", " + user.getHeight());
		});
		System.out.println("Emergency Contacts:");
		for (EmergencyContact contact : contactsList) {
			Platform.runLater(() -> {
				MonitoringServerResponseArea
						.appendText("\n"+"Name: " + contact.getName() + ", Phone: " + contact.getPhone() + "\n");
			});
		}
	}

	public static void lookForUser(String name) {
		// PatientID patient = PatientID.newBuilder().setPatientId(id).build();
		GetHealthRecordsRequest reply = GetHealthRecordsRequest.newBuilder().setName(name).build();
		GetHealthRecordsResponse response = ServiceManager.blockingStubService3.getHealthRecords(reply);
		UserRecords user = response.getUserRecords();
		List<EmergencyContact> contactsList = user.getContactsList();

		Platform.runLater(() -> {
			MonitoringServerResponseArea.appendText("ClientSide: receiving the User Details: " + user.getName() + ", "
					+ user.getAge() + ", " + user.getAddress() + ", " + user.getWeight() + ", " + user.getHeight());
		});
		for (EmergencyContact contact : contactsList) {
			Platform.runLater(() -> {
				MonitoringServerResponseArea
						.appendText("Name: " + contact.getName() + ", Phone: " + contact.getPhone() + "\n");
			});
		}
	}

	public void startSendHeartRate(int heart1, int heart2, String patientName) {
		shutdownExecutorService();
		startExecutorService();

		StreamObserver<HeartRateWarning> responseObserver = createResponseObserver();
		requestStreamObserverHeart = ServiceManager.asyncStubService3.monitorHeartRate(responseObserver);

		scheduledExecutorService.scheduleAtFixedRate(() -> 
					sendHeartRate(heart1, heart2, patientName, requestStreamObserverHeart), 0, 5, TimeUnit.SECONDS);
	}

	private void stopSendingHeartRate() throws InterruptedException {
		shutdownExecutorService();
		requestStreamObserverHeart.onCompleted();
	}

	public static void sendHeartRate(int heart1, int heart2, String patientName,
			StreamObserver<HeartRateRequest> requestStreamObserver) {
		try {
			HeartRateRequest request = HeartRateRequest.newBuilder().setName(patientName)
					.setHeartRate(ThreadLocalRandom.current().nextDouble(heart1, heart2)).build();
			requestStreamObserver.onNext(request);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	private StreamObserver<HeartRateWarning> createResponseObserver() {
		return new StreamObserver<HeartRateWarning>() {
			@Override
			public void onNext(HeartRateWarning heartRateWarning) {
				Platform.runLater(() -> {
					MonitoringServerResponseArea.appendText("\n"+"ServerResponse: " + heartRateWarning.getMessage());
				});
			}

			@Override
			public void onError(Throwable t) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onCompleted() {
				Platform.runLater(() -> {
					MonitoringServerResponseArea.appendText("\n"+"Server Completes...");
				});
			}
		};

	}

}
