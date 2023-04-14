package ClientSides;

import java.util.concurrent.CountDownLatch;

import io.grpc.stub.StreamObserver;
import sw.stepCounter.service1.StepCount;

public class StepStreamObserver implements StreamObserver<StepCount>{

	private CountDownLatch latch;
	private int stepCount;
	
	
	public StepStreamObserver(CountDownLatch latch) {
		this.latch = latch;
	}
	
	@Override
	public void onNext(StepCount step) {
		System.out.println(
				"The steps have been made : " + step.getCount()
				);
		this.stepCount = step.getCount();
		
	}

	@Override
	public void onError(Throwable t) {
		this.latch.countDown();
		
	}

	@Override
	public void onCompleted() {
		System.out.println(
				"Server is completed!"
				);
		this.latch.countDown();
		
	}

	public int getStepCount() {
		return stepCount;
	}

	public void setStepCount(int stepCount) {
		this.stepCount = stepCount;
	}
	
	
	
}
