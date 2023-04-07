package ServerSides;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
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
	private int runTimeSteps; //to be sent to client when streaming is done
    //private final Object lock = new Object();
    private static File directory = new File("C:\\Code\\SmartWatchGRPC\\database");
    private static final String FILE_NAME = directory.getAbsolutePath() + "//stepsDatabase.txt";
	
	
	
	public StepsStreamingRequest(StreamObserver<StepCount> stepsStreamObserver) {
		System.out.println("ServerSide: receiving the steps... ");
		this.stepsStreamObserver = stepsStreamObserver;
		
		 // Create a ScheduledExecutorService to save total steps in every 10 minutes
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(this::saveTotalStepsToFile, 0, 1, TimeUnit.MINUTES);
	}

	/*It observes the client messages*/
	@Override
	public void onNext(StepsRequest stepRequest) {
		{
			steps = stepRequest.getSteps();
			//System.out.println("ServerSide received: " + steps);
			totalSteps += steps;
			runTimeSteps += steps;
		}
	}

	@Override
	public void onError(Throwable t) {
		// TODO Auto-generated method stub
		
	}

	/*When client finishes sending server will return*/
	@Override
	public void onCompleted() {
		
		StepCount stepCount = StepCount.newBuilder().setCount(runTimeSteps).build();
		saveTotalStepsToFile();
		this.stepsStreamObserver.onNext(stepCount);
		System.out.println("ServeSide: server completes ... ");
		this.stepsStreamObserver.onCompleted();
	}


	private void saveTotalStepsToFile() {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
	        writer.write(LocalDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME) + "->" + totalSteps + "\n");
	        totalSteps = 0; // Reset the total steps counter
	    } catch (IOException e) {
	        System.err.println("Failed to save total steps to file: " + e.getMessage());
	    }
	}


}
