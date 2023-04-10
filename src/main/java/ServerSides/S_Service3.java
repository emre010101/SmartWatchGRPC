package ServerSides;

import java.util.List;

import io.grpc.stub.StreamObserver;
import sw.Monitoring.service3.EmergencyContact;
import sw.Monitoring.service3.GetHealthRecordsRequest;
import sw.Monitoring.service3.HeartRateRequest;
import sw.Monitoring.service3.HeartRateWarning;
import sw.Monitoring.service3.MonitoringGrpc.MonitoringImplBase;
import sw.Monitoring.service3.ServerResponse;
import sw.Monitoring.service3.UserRecords;

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
	}

	@Override
	public void getHealthRecords(GetHealthRecordsRequest request, StreamObserver<UserRecords> responseObserver) {
		// TODO Auto-generated method stub
		super.getHealthRecords(request, responseObserver);
	}

	@Override
	public StreamObserver<HeartRateRequest> monitorHeartRate(StreamObserver<HeartRateWarning> responseObserver) {
		// TODO Auto-generated method stub
		return super.monitorHeartRate(responseObserver);
	}

	
}
