package ClientSides;

import java.util.ArrayList;
import java.util.List;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import sw.Monitoring.service3.EmergencyContact;
import sw.Monitoring.service3.MonitoringGrpc;
import sw.Monitoring.service3.MonitoringGrpc.MonitoringBlockingStub;
import sw.Monitoring.service3.MonitoringGrpc.MonitoringStub;
import sw.Monitoring.service3.ServerResponse;
import sw.Monitoring.service3.UserRecords;


public class C_Service3 {

	private static MonitoringBlockingStub blockingStub;
	private static MonitoringStub asyncStub;
	private static ManagedChannel managedChannel;
	
	public static void main(String[] args) throws InterruptedException {
		managedChannel = ManagedChannelBuilder
				.forAddress("localhost", 1045)
				.usePlaintext()
				.build();

		//stubs -- generate from proto
		blockingStub = MonitoringGrpc.newBlockingStub(managedChannel);

		asyncStub = MonitoringGrpc.newStub(managedChannel);
		
		   //Call the setTaskReminder method
	    try {
	    	saveUserCredientials();
	    } finally {
	        // Properly shut down the channel when you're done using it
	        managedChannel.shutdown();
	    }
	}
	
	public static void saveUserCredientials() {
		int p_id = 10;
		int p_age = 30;
		String nam = "Lenoarda Diacaprio";
		double we = 45.65;
		double he = 1.85;
		String add = "27B Mounjoy Street, Dublin 1";
		String conName = "William";
		String conPhone = "98956565";
		String conName2 = "Rohid";
		String conPhone2 = "088746513165";
		EmergencyContact emergContact1 = EmergencyContact.newBuilder().setName(conName).setPhone(conPhone).build();
		EmergencyContact emergContact2 = EmergencyContact.newBuilder().setName(conName2).setPhone(conPhone2).build();
		List<EmergencyContact> emergencies = new ArrayList<EmergencyContact>();
		emergencies.add(emergContact1);
		emergencies.add(emergContact2);
			
		UserRecords reply = UserRecords.newBuilder().setPatientId(p_id).setAge(p_age)
								.setName(nam).setWeight(we).setHeight(he)
								.setAddress(add).addAllContacts(emergencies).build();
		
		ServerResponse response = blockingStub.setUserRecords(reply);
		
		System.out.println(
				"Server message : " + response.getConfirmed()
				);
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		}
	
	
	
	
	
	}
	
	
	
	

