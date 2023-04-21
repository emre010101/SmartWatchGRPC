package ServerSides;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.grpc.stub.StreamObserver;
import sw.stepCounter.service1.StepCount;
import sw.stepCounter.service1.StepsRequest;

public class StepsStreamingRequest implements StreamObserver<StepsRequest>{

	private StreamObserver<StepCount> stepsStreamObserver; 
	private int steps;
	private int totalSteps; //to be written to database in every 10 minutes
	private static int runTimeSteps; //to be sent to client when streaming is done
    private static File directory = new File("C:\\Code\\SmartWatchGRPC\\database");
    private static final String FILE_NAME = directory.getAbsolutePath() + "//stepsDatabase.txt";
	
	public StepsStreamingRequest(StreamObserver<StepCount> stepSStreamObserver) {
		System.out.println("ServerSide: receiving the steps... ");
		this.stepsStreamObserver = stepSStreamObserver;
		
		 // Create a ScheduledExecutorService to save total steps in every 5 minutes
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(this::saveTotalStepsToFile, 0, 5, TimeUnit.MINUTES);
	}

	/*It observes the client messages*/
	@Override
	public void onNext(StepsRequest stepRequest) {
		{
			System.out.println("ServerSide received " + stepRequest.getSteps() + " steps...");
			steps = stepRequest.getSteps();
			totalSteps += steps;
			runTimeSteps += steps;
		}
	}

	@Override
	public void onError(Throwable t) {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/*When client finishes sending server will return*/
	@Override
	public void onCompleted() {
		
		StepCount stepCount = StepCount.newBuilder().setCount(runTimeSteps).build();
		saveTotalStepsToFile();
		stepsStreamObserver.onNext(stepCount);
		System.out.println("ServerSide: server completes ... ");
		stepsStreamObserver.onCompleted();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void saveTotalStepsToFile() {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
	        writer.write(ZonedDateTime.now(ZoneId.systemDefault()).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME) + "->" + totalSteps + "\n");
	        totalSteps = 0; // Reset the total steps counter
	    } catch (IOException e) {
	        System.err.println("Failed to save total steps to file: " + e.getMessage());
	    }
	}

	public static int getRunTimeSteps() {
		return runTimeSteps;
	}

	public static void setRunTimeSteps(int zero) {
		runTimeSteps = zero;
	}

	


}
