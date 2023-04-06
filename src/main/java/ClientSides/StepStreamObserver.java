package ClientSides;

import io.grpc.stub.StreamObserver;
import sw.stepCounter.service1.StepCount;

public class StepStreamObserver implements StreamObserver<StepCount>{

	@Override
	public void onNext(StepCount step) {
		System.out.println(
				"The steps have been made : " + step.getCount()
				);
		
	}

	@Override
	public void onError(Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCompleted() {
		System.out.println(
				"Server is completed!"
				);
		
	}
	
}
