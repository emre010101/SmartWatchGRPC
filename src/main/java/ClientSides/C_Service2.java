package ClientSides;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import sw.Reminder.service2.ReminderGrpc;
import sw.Reminder.service2.TaskReminder;
import sw.Reminder.service2.Type;
import sw.Reminder.service2.ReminderGrpc.ReminderBlockingStub;
//import sw.Reminder.service2.ReminderGrpc.ReminderStub;
import sw.Reminder.service2.ServerResponse;
import sw.Reminder.service2.TaskComplete;

public class C_Service2 {

	private static ReminderBlockingStub blockingStub;
	//private static ReminderStub asyncStub;
	private static ManagedChannel managedChannel;
	private static ServiceInfo serviceinfo;
	
	public static void main(String[] args) {
		
		String reminder_service_type = "reminder._tcp.local.";
		discoverReminderService(reminder_service_type);
		String host = serviceinfo.getHostAddresses()[0];
		int port = serviceinfo.getPort();
		managedChannel = ManagedChannelBuilder
				.forAddress(host, port)
				.usePlaintext()
				.build();
		/*managedChannel = ManagedChannelBuilder
				.forAddress("localhost", 1048)
				.usePlaintext()
				.build();

		//stubs -- generate from proto*/
		blockingStub = ReminderGrpc.newBlockingStub(managedChannel);

		//asyncStub = ReminderGrpc.newStub(managedChannel);
		
		   //Call the setTaskReminder method
	    try {
	        setTaskReminder();
	        markTask();
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    } finally {
	        // Properly shut down the channel when you're done using it
	        managedChannel.shutdown();
	    }
	}
	
private static void discoverReminderService(String service_type) {
		
		
		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

				
			jmdns.addServiceListener(service_type, new ServiceListener() {
				
				@Override
				public void serviceResolved(ServiceEvent event) {
					System.out.println("Math Service resolved: " + event.getInfo());

					serviceinfo = event.getInfo();

					int port = serviceinfo.getPort();
					
					System.out.println("resolving " + service_type + " with properties ...");
					System.out.println("\t port: " + port);
					System.out.println("\t type:"+ event.getType());
					System.out.println("\t name: " + event.getName());
					System.out.println("\t description/properties: " + serviceinfo.getNiceTextString());
					System.out.println("\t host: " + serviceinfo.getHostAddresses()[0]);
				
					
				}
				
				@Override
				public void serviceRemoved(ServiceEvent event) {
					System.out.println("Reminder Service removed: " + event.getInfo());

					
				}
				
				@Override
				public void serviceAdded(ServiceEvent event) {
					System.out.println("Reminder Service added: " + event.getInfo());

					
				}
			});
			
			// Wait a bit
			Thread.sleep(2000);
			
			jmdns.close();

		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void setTaskReminder() throws InterruptedException {
		String name = "PAin relief";
		String time = "2023-04-15T10:50:23.1977575+01:00";
		TaskReminder req = TaskReminder.newBuilder().setDateTime(time).setTaskName(name).setType(Type.PILL).build();
		
		ServerResponse response = blockingStub.setTaskReminder(req);
		System.out.println(
				"Service message: " + response.getConfirmed()
				);
		Thread.sleep(100);
		//managedChannel.shutdown();
	}
	
	public static void markTask() throws InterruptedException {
		String name = "Booster for the foot";
		TaskComplete req = TaskComplete.newBuilder().setTaskName(name).build();
		ServerResponse response = blockingStub.markTaskComplete(req);
		System.out.println(
				"Service message: " + response.getConfirmed()
				);
		Thread.sleep(100);
	}
}
