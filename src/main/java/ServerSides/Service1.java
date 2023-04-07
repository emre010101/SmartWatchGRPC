package ServerSides;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.google.protobuf.Empty;

import io.grpc.stub.StreamObserver;
import sw.stepCounter.service1.HourlyStepCount;
import sw.stepCounter.service1.HourlyStepRequest;
import sw.stepCounter.service1.StepCount;
import sw.stepCounter.service1.StepCounterGrpc.StepCounterImplBase;
import sw.stepCounter.service1.StepGoal;
import sw.stepCounter.service1.StepGoalResponse;
import sw.stepCounter.service1.StepsRequest;
import sw.stepCounter.service1.WeekDays;


public class Service1 extends StepCounterImplBase{
	
	/*This method will send the steps to database which will write to text file */
	@Override
	public StreamObserver<StepsRequest> sendSteps(StreamObserver<StepCount> responseObserver) {
		 return new StepsStreamingRequest(responseObserver);
	}

	/*Database will be consulted for the last hour steps*/
	@Override
	public void getLastHourSteps(Empty request, StreamObserver<StepCount> responseObserver) {
	    ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
        ZonedDateTime oneHourBefore = now.minusHours(1);
		StepCount lastHourSteps = StepCount.newBuilder().setCount(DataBaseConsulter.checkStepsFromStartTime(oneHourBefore)).build();
		responseObserver.onNext(lastHourSteps);
		responseObserver.onCompleted();
	}


	@Override
	public void getAverageHourlySteps(HourlyStepRequest request, StreamObserver<HourlyStepCount> responseObserver) {
		System.out.println("Receiving getAverageHourlySteps: " + request.getWeekDays());
		WeekDays week = request.getWeekDays();
		//int weekday = request.getWeekDaysValue();
		String msg = "No walk!";
		
		int result = DataBaseConsulter.checkAverageSteps(request.getWeekDays());
		
		if(result<100) {
			msg = "You should walk more";
		}else if(result<500) {
			msg = "You can walk more";
		}else if(result<1000) {
			msg = "Get you ass up";
		}else if(result<1500) {
			msg = "Not too bad";
		}else if(result<2000){
			msg = "This is good";
		}else if(result<2500) {
			msg = "WELL DONE!";
		}else {
			msg = "You are AMAZING";
		}
		
		HourlyStepCount reply = HourlyStepCount.newBuilder().setAverageSteps(result).setWeekDays(week).setMessage(msg).build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	@Override
	public void setStepGoal(StepGoal request, StreamObserver<StepGoalResponse> responseObserver) {
		System.out.println("Server received the StepGoal: " + request.getGoal());
		int goal = request.getGoal();
		StepsStreamingRequest.setRunTimeSteps(0);
		int left = goal - StepsStreamingRequest.getRunTimeSteps();
		boolean achive = false;
		String msg = "Default";
		while(achive==false) {
			left = goal - StepsStreamingRequest.getRunTimeSteps();
			if(left < goal-(goal/3)) {
				msg = "You've done the first quarter";
			}else if(left < goal-(goal/2)) {
				msg = "You've done the first half";
			}else if(left < (int)goal-(goal/1.5)) {
				msg = "You've done some progress";
			}else if(left < 0) {
				msg = "You've made it!!!!";
				achive = true;
			}
			StepGoalResponse reply = StepGoalResponse.newBuilder().setLeft(left).setMessage(msg).setSuccess(achive).build();
			responseObserver.onNext(reply);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(achive == true) {
			responseObserver.onCompleted();
		}
	}


	
	
}
