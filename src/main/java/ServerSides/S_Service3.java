package ServerSides;

import java.util.List;

import io.grpc.stub.StreamObserver;
import sw.Monitoring.service3.EmergencyContact;
import sw.Monitoring.service3.GetHealthRecordsRequest;
import sw.Monitoring.service3.GetHealthRecordsResponse;
import sw.Monitoring.service3.HeartRateRequest;
import sw.Monitoring.service3.HeartRateWarning;
import sw.Monitoring.service3.MonitoringGrpc.MonitoringImplBase;
import sw.Monitoring.service3.ServerResponse;
import sw.Monitoring.service3.UserRecords;
//import sw.Monitoring.service3.PatientID;*/

public class S_Service3 extends MonitoringImplBase{

	@Override
	public void setUserRecords(UserRecords request, StreamObserver<ServerResponse> responseObserver) {
		System.out.println(
				"Receiving the User Details: " + request.getName() +", " +  request.getPatientId() + ", " + request.getAge()
				+", " + request.getAddress() + ", " + request.getWeight() + ", " + request.getHeight()
				);
	    List<EmergencyContact> contactsList = request.getContactsList();
	    System.out.println("Emergency Contacts:");
	    for (EmergencyContact contact : contactsList) {
	        System.out.println("Name: " + contact.getName() + ", Phone: " + contact.getPhone());
	    }
	    String reply = DataBaseConsulter.saveUser(request);
	    ServerResponse response = ServerResponse.newBuilder().setConfirmed(reply).build();
	    responseObserver.onNext(response);
	    responseObserver.onCompleted();
	}

	

	@Override
	public void getHealthRecords(GetHealthRecordsRequest request,
			StreamObserver<GetHealthRecordsResponse> responseObserver) {
		int patientId = request.getPatientId().getPatientId();
		System.out.println("Receiving the patiend id to be retrivied: " + patientId);
		UserRecords reply = DataBaseConsulter.lookForUser(patientId);
		
		// Create a GetHealthRecordsResponse and set the user_records field
	    GetHealthRecordsResponse response = GetHealthRecordsResponse.newBuilder()
	            .setUserRecords(reply)
	            .build();
	    
	    // Send the response
	    responseObserver.onNext(response);
	    responseObserver.onCompleted();
		
	}



	@Override
	public StreamObserver<HeartRateRequest> monitorHeartRate(StreamObserver<HeartRateWarning> responseObserver) {
		System.out.println(
				"Heart Rate Request received"
				);
		return new HeartRateStreamingRequest(responseObserver);
	}

	
}
