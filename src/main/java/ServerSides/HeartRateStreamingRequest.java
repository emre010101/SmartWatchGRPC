package ServerSides;

import io.grpc.stub.StreamObserver;
import sw.Monitoring.service3.HeartRateRequest;
import sw.Monitoring.service3.HeartRateWarning;
import sw.Monitoring.service3.UserRecords;

public class HeartRateStreamingRequest implements StreamObserver<HeartRateRequest>{
	
	private StreamObserver<HeartRateWarning> heartRateObserver;
	
	public HeartRateStreamingRequest(StreamObserver<HeartRateWarning> heartRateObserver) {
		this.heartRateObserver = heartRateObserver;
	}

	@Override
	public void onNext(HeartRateRequest heartRateRequest) {
	    double heartRate = heartRateRequest.getHeartRate();
	    int patientID = heartRateRequest.getPatientId().getPatientId();
	    UserRecords currentPatient = DataBaseConsulter.lookForUser(patientID);
	    int currentPatientAge = 35; // Default age
	    int maxHeartRate = 220 - currentPatientAge; // Default maxHeartRate
	    
	    if (!currentPatient.getName().equals("User not found")) { // If the user exists
	        currentPatientAge = currentPatient.getAge();
	        maxHeartRate = 220 - currentPatientAge;
	    }
	    
	    double lowerTargetHR = 0.5 * maxHeartRate;
	    double upperTargetHR = 0.85 * maxHeartRate;
	    String resp;

	    if (heartRate < 60) {
	        // Heart rate is below the normal RHR
	        resp = "Heart rate is too low.";
	    } else if (heartRate > maxHeartRate) {
	        // Heart rate exceeds the maximum heart rate
	    	resp = "Heart rate is too high! Danger.";
	    } else if (heartRate >= lowerTargetHR && heartRate <= upperTargetHR) {
	        // Heart rate is within the target heart rate zone
	    	resp = "Heart rate is within the target range. Good job!";
	    } else {
	        // Heart rate is above the normal RHR but outside the target heart rate zone
	    	resp = "Heart rate is outside the target range.";
	    }
	    
	    HeartRateWarning reply = HeartRateWarning.newBuilder().setMessage(resp).build();
	    this.heartRateObserver.onNext(reply);
	}


	@Override
	public void onError(Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCompleted() {
		this.heartRateObserver.onCompleted();
		
	}


	

}
