package ServerSides;

import com.google.protobuf.Empty;

import io.grpc.stub.StreamObserver;
import sw.stepCounter.service1.HourlyStepCount;
import sw.stepCounter.service1.HourlyStepRequest;
import sw.stepCounter.service1.StepCount;
import sw.stepCounter.service1.StepCounterGrpc.StepCounterImplBase;
import sw.stepCounter.service1.StepGoal;
import sw.stepCounter.service1.StepGoalResponse;
import sw.stepCounter.service1.StepsRequest;


public class Service1 extends StepCounterImplBase{
	
	/*This method will send the steps to database which will write to text file */
	@Override
	public StreamObserver<StepsRequest> sendSteps(StreamObserver<StepCount> responseObserver) {
		 return new StepsStreamingRequest(responseObserver);
	}

	/*Database will be consulted for the last hour steps*/
	@Override
	public void getLastHourSteps(Empty request, StreamObserver<StepCount> responseObserver) {
		StepCount lastHourSteps = StepCount.newBuilder().setCount(DataBaseConsulter.checkLastHour()).build();
		responseObserver.onNext(lastHourSteps);
		responseObserver.onCompleted();
	}


	@Override
	public void getAverageHourlySteps(HourlyStepRequest request, StreamObserver<HourlyStepCount> responseObserver) {
		// TODO Auto-generated method stub
		super.getAverageHourlySteps(request, responseObserver);
	}

	@Override
	public void setStepGoal(StepGoal request, StreamObserver<StepGoalResponse> responseObserver) {
		// TODO Auto-generated method stub
		super.setStepGoal(request, responseObserver);
	}
	
	
}
