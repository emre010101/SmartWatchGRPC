package ClientSides;

import com.google.protobuf.Empty;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import sw.stepCounter.service1.StepCount;
import sw.stepCounter.service1.StepCounterGrpc;
import sw.stepCounter.service1.StepCounterGrpc.StepCounterBlockingStub;
import sw.stepCounter.service1.StepCounterGrpc.StepCounterStub;
import sw.stepCounter.service1.StepsRequest;

public class C_Service1 {
	
	
	private static StepCounterBlockingStub blockingStub;
	private static StepCounterStub asyncStub;
	private static ManagedChannel managedChannel;
	private static final Logger logger = LogManager.getLogger(C_Service1.class);
	
	public static void main(String[] args) {
		System.setProperty("java.util.logging.config.file", "src/main/resources/logging.properties");
		
		managedChannel = ManagedChannelBuilder
				.forAddress("localhost", 1026)
				.usePlaintext()
				.build();

		//stubs -- generate from proto
		blockingStub = StepCounterGrpc.newBlockingStub(managedChannel);

		asyncStub = StepCounterGrpc.newStub(managedChannel);
		
		try {
			//lastHour();
			stepStreamingRequest();
			Thread.sleep(100);
			lastHour();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void stepStreamingRequest() throws InterruptedException {
		System.out.println("-- Client side stepStreamingRequest Invoked");
		CountDownLatch latch = new CountDownLatch(2);
		StreamObserver<StepsRequest> streamObserver = asyncStub.sendSteps(new StepStreamObserver(latch));
		boolean run = true;
		try {
			for (int i = 0; i < 50; i++) {
				StepsRequest stepsRequest = StepsRequest.newBuilder().setSteps(25).build();
				streamObserver.onNext(stepsRequest);
				Thread.sleep(500);
				System.out.printf("Test %s : ", i);
			}
			
		streamObserver.onCompleted();
		//Thread.sleep(1000);
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
		latch.await();
	}
	
	public static void lastHour() {
		System.out.println("--ClientSide : lastHour() invoked");
		try {
			StepCount stepCount = blockingStub.getLastHourSteps(Empty.getDefaultInstance());
			logger.info("The steps taken in the last hour : " + stepCount.getCount());
			System.out.println("Test in lashour: " + stepCount.getCount());
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}/*finally {
			try {
				managedChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
			} catch (InterruptedException e2) {
				// TODO: handle exception
			}
		}*/
	}

}
