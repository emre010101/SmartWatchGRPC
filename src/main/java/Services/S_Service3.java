package Services;

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

public class S_Service3 extends MonitoringImplBase{

	@Override
	public void setUserRecords(UserRecords request, StreamObserver<ServerResponse> responseObserver) {
		System.out.println(
				"Receiving the User Details: " + request.getName() +", " +  /*request.getPatientId() +*/ request.getAge()
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


	@SuppressWarnings("incomplete-switch")
	@Override
	public void getHealthRecords(GetHealthRecordsRequest request,
			StreamObserver<GetHealthRecordsResponse> responseObserver) {
		UserRecords reply = null;
		try {
			//Check which credential has been sent by the client.
			switch(request.getModeCase()) {
			case PATIENT_ID:
				System.out.println("ServerSide: receiving the patientID to consult the database -> "
						+ request.getPatientId());
				int patientId = request.getPatientId();
				reply = DataBaseConsulter.lookForUser(patientId);
				break;
			
			case NAME:
				System.out.println("ServerSide: receiving the patientName to consult the database -> "
						+ request.getName());
				String patientName = request.getName();
				reply = DataBaseConsulter.lookForUser(patientName);
				break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
