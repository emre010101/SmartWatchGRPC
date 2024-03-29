package Services;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.google.protobuf.Empty;

import io.grpc.stub.StreamObserver;
import sw.stepCounter.service1.AverageStepCount;
import sw.stepCounter.service1.AverageStepRequest;
import sw.stepCounter.service1.Periods;
import sw.stepCounter.service1.StepCount;
import sw.stepCounter.service1.StepCounterGrpc.StepCounterImplBase;
import sw.stepCounter.service1.StepGoal;
import sw.stepCounter.service1.StepGoalResponse;
import sw.stepCounter.service1.StepsRequest;

public class S_Service1 extends StepCounterImplBase{
	
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



	/*Receives the period to be calculated the average steps have been made */
	@Override
	public void getAverage(AverageStepRequest request, StreamObserver<AverageStepCount> responseObserver) {
			System.out.println("Receiving getAverageHourlySteps: " + request.getPeriod());
			Periods period = request.getPeriod();
			//int weekday = request.getWeekDaysValue();
			String msg = "No walk!";
			
			int result = DataBaseConsulter.checkAverageSteps(request.getPeriod());
			
			if(result<100) {
				msg = "My grandmother walks more than you";
			}else if(result<500) {
				msg = "You can walk more";
			}else if(result<1000) {
				msg = "Have you thought running instead?";
			}else if(result<1500) {
				msg = "Not too bad";
			}else if(result<2000){
				msg = "This is good";
			}else if(result<2500) {
				msg = "WELL DONE!";
			}else {
				msg = "You are AMAZING";
			}
			
			AverageStepCount reply = AverageStepCount.newBuilder().setAverageSteps(result).setPeriod(period).setMessage(msg).build();
			responseObserver.onNext(reply);
			responseObserver.onCompleted();
		
	}
	
	/*Server streaming it will send custom messages until goal is reached*/
	@Override
	public void setStepGoal(StepGoal request, StreamObserver<StepGoalResponse> responseObserver) {
		System.out.println("Server received the StepGoal: " + request.getGoal());
		int goal = request.getGoal();
		StepsStreamingRequest.setRunTimeSteps(0);
		int left = goal - StepsStreamingRequest.getRunTimeSteps();
		boolean achive = false;
		String msg = "You haven't started yet!";
		while (achive == false) {
		    left = goal - StepsStreamingRequest.getRunTimeSteps();
		    if (left <= 0) {
		        msg = "You've made it!!!!";
		        achive = true;
		    } else if(goal == left) {
		    	msg = "You haven't started yet!!";
		    } else if (left >= (int) goal - (goal * 0.1)) {
		        msg = "You've done 10% of the goal";
		    } else if (left >= (int) goal - (goal * 0.2)) {
		        msg = "You've done 20% of the goal";
		    } else if (left >= (int) goal - (goal * 0.3)) {
		        msg = "You've done 30% of the goal";
		    } else if (left >= (int) goal - (goal * 0.4)) {
		        msg = "You've done 40% of the goal";
		    } else if (left >= (int) goal - (goal * 0.5)) {
		        msg = "You've done the first half";
		    } else if (left >= (int) goal - (goal * 0.6)) {
		        msg = "You've done 60% of the goal";
		    } else if (left >= (int) goal - (goal * 0.7)) {
		        msg = "You've done 70% of the goal";
		    } else if (left >= (int) goal - (goal * 0.8)) {
		        msg = "You've done 80% of the goal";
		    } else if (left >= (int) goal - (goal * 0.9)) {
		        msg = "You've done 90% of the goal";
		    }

			StepGoalResponse reply = StepGoalResponse.newBuilder().setLeft(left).setMessage(msg).setSuccess(achive).build();
			responseObserver.onNext(reply);
			try {
				Thread.sleep(8500);
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
