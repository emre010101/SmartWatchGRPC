package ClientSides;

import io.grpc.stub.StreamObserver;
import sw.Monitoring.service3.HeartRateWarning;

public class HeartRateStreamingResponse implements StreamObserver<HeartRateWarning>{

	@Override
	public void onNext(HeartRateWarning heartRateWarning) {
		System.out.println(
				heartRateWarning.getMessage()
				);
		
	}

	@Override
	public void onError(Throwable t) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onCompleted() {
		System.out.println("System shut down...");
	}
	
	

}
