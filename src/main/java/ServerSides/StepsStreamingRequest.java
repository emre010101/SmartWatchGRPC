package ServerSides;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.grpc.stub.StreamObserver;
import sw.stepCounter.service1.StepCount;
import sw.stepCounter.service1.StepsRequest;

public class StepsStreamingRequest implements StreamObserver<StepsRequest>{

	private StreamObserver<StepCount> stepsStreamObserver; 
	private int totalSteps;
    private final Object lock = new Object();
    private static File directory = new File("C:\\Code\\SmartWatchGRPC\\database");
    private static final String FILE_NAME = directory.getAbsolutePath() + "//stepsDatabase.txt";
	
	
	
	public StepsStreamingRequest(StreamObserver<StepCount> stepsStreamObserver) {
		System.out.println("Receiving the steps... ");
		this.stepsStreamObserver = stepsStreamObserver;
		
		 // Create a ScheduledExecutorService to save total steps at the beginning of every hour
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(this::saveTotalStepsToFile, calculateInitialDelay(), 60, TimeUnit.MINUTES);
	}

	/*It observes the client messages*/
	@Override
	public void onNext(StepsRequest stepRequest) {
		synchronized (lock) {
			totalSteps += stepRequest.getSteps();
		}
	}

	@Override
	public void onError(Throwable t) {
		// TODO Auto-generated method stub
		
	}

	/*When client finishes sending server will return*/
	@Override
	public void onCompleted() {
		saveTotalStepsToFile();
		
		StepCount stepCount = StepCount.newBuilder().setCount(totalSteps).build();
		this.stepsStreamObserver.onNext(stepCount);
		System.out.println("Server Completes ... ");
		this.stepsStreamObserver.onCompleted();
	}

    private void saveTotalStepsToFile() {
        synchronized (lock) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
                writer.write("Steps taken during the hour ending at " + LocalDateTime.now() + ": " + totalSteps + "\n");
                totalSteps = 0; // Reset the total steps counter
            } catch (IOException e) {
                System.err.println("Failed to save total steps to file: " + e.getMessage());
            }
        }
    }

    private long calculateInitialDelay() {
        LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());
        LocalDateTime nextExecution = now.withMinute(59).withSecond(59).withNano(0).plusHours((now.getMinute() == 59 && now.getSecond() >= 59) ? 1 : 0);
        return now.until(nextExecution, ChronoUnit.SECONDS);
    }

}
