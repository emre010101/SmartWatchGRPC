package GUI;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import com.google.protobuf.Empty;

import ClientSides.StepStreamObserver;

import java.io.IOException;
import java.util.*;


import io.grpc.stub.StreamObserver;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import sw.Reminder.service2.ServerResponse;
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

@SuppressWarnings("restriction")
public class Main extends Application {

	//static ServiceManager serviceManager;
	
	Stage window;
	Scene SceneMain, SceneStep, SceneReminder, SceneMonitoring;
	
	Button button1, button2, button3, button4;
	Button buttonBackStep, buttonBackReminder, buttonBackMonitoring;
	
	private static AtomicBoolean isStreaming = new AtomicBoolean(false);
	private static ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
	
	static TextArea serverMessageArea = new TextArea();
	
	public static void main(String[] args) throws IOException {
		//ServiceManager serviceManager = new ServiceManager();
		//serviceManager.discoverServices();
		
		launch(args); //it sets up the javafx in application class
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//Main Page
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
		
		buttonBackStep.setOnAction(e -> {
			ServiceManager.shutdownStepServiceChannel();
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
			window.setScene(SceneReminder);});
		button3.setOnAction(e -> window.setScene(SceneMonitoring));
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
		Button setTaskReminderButton = new Button("setTaskReminder");
		Button markTaskCompleteButton = new Button("Mark Complete");
		setTaskReminderButton.setOnAction(e -> {
			try {
				setTaskReminder();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		VBox layoutReminder = ReminderServiceGUI.createReminderServiceLayout(buttonBackReminder, setTaskReminderButton, markTaskCompleteButton, serverMessageArea);
	    SceneReminder = new Scene(layoutReminder, 400, 400);
		
		
		
		//Monitoring Service Layout
		StackPane layoutMonitoring = new StackPane();
		layoutMonitoring.getChildren().add(buttonBackMonitoring);
		SceneMonitoring = new Scene(layoutMonitoring, 400, 400);
		
		window.setScene(SceneMain);
		window.setTitle("WELCOME");
		window.show();
		
	}

	private void closeProgram() {
		Boolean answer = ConfirmBox.display("Closing", "Are you sure, want to close?");
		if(answer) {
			window.close();
		}
		
	}
	
	
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
	    ServiceManager.shutdownStepServiceChannel();
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
	
	public static void setTaskReminder() throws InterruptedException {
		String name = "PAin relief";
		String time = "2023-04-15T10:50:23.1977575+01:00";
		TaskReminder req = TaskReminder.newBuilder().setDateTime(time).setTaskName(name).setType(Type.PILL).build();
		
		ServerResponse response = ServiceManager.blockingStubService2.setTaskReminder(req);
		System.out.println(
				"Service message: " + response.getConfirmed()
				);
		Thread.sleep(100);
		//managedChannel.shutdown();
	}
	
	public static void markTask() throws InterruptedException {
		String name = "Booster for the foot";
		TaskComplete req = TaskComplete.newBuilder().setTaskName(name).build();
		ServerResponse response = ServiceManager.blockingStubService2.markTaskComplete(req);
		System.out.println(
				"Service message: " + response.getConfirmed()
				);
		Thread.sleep(100);
	}
}


	
	
