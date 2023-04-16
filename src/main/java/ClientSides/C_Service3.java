package ClientSides;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

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

public class C_Service3 {

	private static MonitoringBlockingStub blockingStub;
	private static MonitoringStub asyncStub;
	private static ManagedChannel managedChannel;
	//to make it constantly running the send heart rate
	private static AtomicBoolean sendHeartRateFlag = new AtomicBoolean(true);

	public static void main(String[] args) throws InterruptedException, IOException {
		managedChannel = ManagedChannelBuilder.forAddress("localhost", 1082).usePlaintext().build();

		// stubs -- generate from proto
		blockingStub = MonitoringGrpc.newBlockingStub(managedChannel);

		asyncStub = MonitoringGrpc.newStub(managedChannel);

		try {
            saveUserCredientials();
            lookForUser();

            // Run sendHearRate in a separate thread in order to be able to invoke other methods when ever we want.
            Thread sendHeartRateThread = new Thread(() -> {
                sendHearRate();
            });
            sendHeartRateThread.start();

            // CLI implementation
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String command;

            System.out.println("Commands: \n1. saveUserCredientials\n2. lookForUser\n3. stopSendHeartRate\n4. exit");
            while ((command = reader.readLine()) != null) {
                switch (command.toLowerCase()) {
                    case "saveusercreidential":
                        saveUserCredientials();
                        break;
                    case "lookforuser":
                        lookForUser();
                        break;
                    case "stopsendheartrate":
                        stopSendHeartRate();
                        break;
                    case "exit":
                        sendHeartRateFlag.set(false);
                        managedChannel.shutdown();
                        System.exit(0);
                    default:
                        System.out.println("Invalid command. Try again.");
                }
            }
        } finally {
            Thread.sleep(500);
            managedChannel.shutdown();
        }
	}

	public static void saveUserCredientials() {
		int p_age = 21;
		String nam = "Joa Bolbas";
		double we = 82.65;
		double he = 1.86;
		String add = "27B Washington Street, Dublin 4";
		String conName = "Carlos";
		String conPhone = "9895695565";
		String conName2 = "Koksddf";
		String conPhone2 = "0887464513165";
		EmergencyContact emergContact1 = EmergencyContact.newBuilder().setName(conName).setPhone(conPhone).build();
		EmergencyContact emergContact2 = EmergencyContact.newBuilder().setName(conName2).setPhone(conPhone2).build();
		List<EmergencyContact> emergencies = new ArrayList<EmergencyContact>();
		emergencies.add(emergContact1);
		emergencies.add(emergContact2);

		UserRecords reply = UserRecords.newBuilder().setAge(p_age).setName(nam).setWeight(we)
				.setHeight(he).setAddress(add).addAllContacts(emergencies).build();

		ServerResponse response = blockingStub.setUserRecords(reply);

		System.out.println("Server message : " + response.getConfirmed());

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void lookForUser() {
		/*As we use onofmode, one of the field can be set not both of them or none of them*/
		GetHealthRecordsRequest reply = GetHealthRecordsRequest
				.newBuilder()
				.setPatientId(15)
				//.setName("Emre")
				.build();

		GetHealthRecordsResponse response = blockingStub.getHealthRecords(reply);
		UserRecords user = response.getUserRecords();
		System.out.println(
				"ClientSide: receiving the User Details: " + user.getName() +", " + user.getAge()
				+", " + user.getAddress() + ", " + user.getWeight() + ", " + user.getHeight()
				);
		List<EmergencyContact> contactsList = user.getContactsList();
		System.out.println("Emergency Contacts:");
		for (EmergencyContact contact : contactsList) {
			System.out.println("Name: " + contact.getName() + ", Phone: " + contact.getPhone());
			}
	}
	
	//to change the global variable to false to stop send heart rate
	public static void stopSendHeartRate() {
	    sendHeartRateFlag.set(false);
	}
	
	public static void sendHearRate() {
		HeartRateStreamingResponse response = new HeartRateStreamingResponse();
		StreamObserver<HeartRateRequest> requestStreamObserver = asyncStub.monitorHeartRate(response);
		
		while (sendHeartRateFlag.get()) { 
			try {
				//PatientID patient_id = PatientID.newBuilder().setPatientId(2).build();
				HeartRateRequest request = HeartRateRequest.newBuilder()
								//.setPatientId(15) oneOfMode exist on name and patient_id
								.setName("emre")
								.setHeartRate(ThreadLocalRandom.current().nextDouble(50, 180))
								.build();
				requestStreamObserver.onNext(request);
				try {
					Thread.sleep(750);
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
