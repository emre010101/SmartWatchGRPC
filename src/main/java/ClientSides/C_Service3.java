package ClientSides;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import sw.Monitoring.service3.EmergencyContact;
import sw.Monitoring.service3.GetHealthRecordsRequest;
import sw.Monitoring.service3.GetHealthRecordsResponse;
import sw.Monitoring.service3.HeartRateRequest;
import sw.Monitoring.service3.MonitoringGrpc;
import sw.Monitoring.service3.MonitoringGrpc.MonitoringBlockingStub;
import sw.Monitoring.service3.MonitoringGrpc.MonitoringStub;
import sw.Monitoring.service3.ServerResponse;
import sw.Monitoring.service3.UserRecords;
import sw.Monitoring.service3.PatientID;

public class C_Service3 {

	private static MonitoringBlockingStub blockingStub;
	private static MonitoringStub asyncStub;
	private static ManagedChannel managedChannel;

	public static void main(String[] args) throws InterruptedException {
		managedChannel = ManagedChannelBuilder.forAddress("localhost", 1081).usePlaintext().build();

		// stubs -- generate from proto
		blockingStub = MonitoringGrpc.newBlockingStub(managedChannel);

		asyncStub = MonitoringGrpc.newStub(managedChannel);

		// Call the setTaskReminder method
		try {
			saveUserCredientials();
			lookForUser();
			sendHearRate();
		} finally {
			Thread.sleep(500);
			managedChannel.shutdown();
		}
	}

	public static void saveUserCredientials() {
		int p_id = 15;
		int p_age = 40;
		String nam = "Lenoarda Diacaprio";
		double we = 45.65;
		double he = 1.85;
		String add = "27B Mounjoy Street, Dublin 1";
		String conName = "Ruben";
		String conPhone = "98956565";
		String conName2 = "LEo";
		String conPhone2 = "088746513165";
		EmergencyContact emergContact1 = EmergencyContact.newBuilder().setName(conName).setPhone(conPhone).build();
		EmergencyContact emergContact2 = EmergencyContact.newBuilder().setName(conName2).setPhone(conPhone2).build();
		List<EmergencyContact> emergencies = new ArrayList<EmergencyContact>();
		emergencies.add(emergContact1);
		emergencies.add(emergContact2);

		UserRecords reply = UserRecords.newBuilder().setPatientId(p_id).setAge(p_age).setName(nam).setWeight(we)
				.setHeight(he).setAddress(add).addAllContacts(emergencies).build();

		ServerResponse response = blockingStub.setUserRecords(reply);

		System.out.println("Server message : " + response.getConfirmed());

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void lookForUser() {
		PatientID patient = PatientID.newBuilder().setPatientId(13).build();
		GetHealthRecordsRequest reply = GetHealthRecordsRequest.newBuilder().setPatientId(patient).build();
		GetHealthRecordsResponse response = blockingStub.getHealthRecords(reply);
		UserRecords user = response.getUserRecords();
		System.out.println(
				"ClientSide: receiving the User Details: " + user.getName() +", " +  user.getPatientId() + ", " + user.getAge()
				+", " + user.getAddress() + ", " + user.getWeight() + ", " + user.getHeight()
				);
		List<EmergencyContact> contactsList = user.getContactsList();
		System.out.println("Emergency Contacts:");
		for (EmergencyContact contact : contactsList) {
			System.out.println("Name: " + contact.getName() + ", Phone: " + contact.getPhone());
			}
	}
	
	public static void sendHearRate() {
		HeartRateStreamingResponse response = new HeartRateStreamingResponse();
		StreamObserver<HeartRateRequest> requestStreamObserver = asyncStub.monitorHeartRate(response);
		
		for(int i=0; i<20; i++) {
			try {
				PatientID patient_id = PatientID.newBuilder().setPatientId(2).build();
				HeartRateRequest request = HeartRateRequest.newBuilder()
								.setPatientId(patient_id)
								.setHeartRate(ThreadLocalRandom.current().nextDouble(50, 180))
								.build();
				requestStreamObserver.onNext(request);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}catch(RuntimeException e) {
				e.printStackTrace();
			}
				
		}requestStreamObserver.onCompleted();
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
