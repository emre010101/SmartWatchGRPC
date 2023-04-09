package ClientSides;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import sw.Reminder.service2.ReminderGrpc;
import sw.Reminder.service2.TaskReminder;
import sw.Reminder.service2.Type;
import sw.Reminder.service2.ReminderGrpc.ReminderBlockingStub;
import sw.Reminder.service2.ReminderGrpc.ReminderStub;
import sw.Reminder.service2.ServerResponse;
import sw.Reminder.service2.TaskComplete;

public class C_Service2 {

	private static ReminderBlockingStub blockingStub;
	private static ReminderStub asyncStub;
	private static ManagedChannel managedChannel;
	
	public static void main(String[] args) {
		managedChannel = ManagedChannelBuilder
				.forAddress("localhost", 1048)
				.usePlaintext()
				.build();

		//stubs -- generate from proto
		blockingStub = ReminderGrpc.newBlockingStub(managedChannel);

		asyncStub = ReminderGrpc.newStub(managedChannel);
		
		   //Call the setTaskReminder method
	    try {
	        setTaskReminder();
	        markTask();
	    } catch (InterruptedException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } finally {
	        // Properly shut down the channel when you're done using it
	        managedChannel.shutdown();
	    }
	}
	
	public static void setTaskReminder() throws InterruptedException {
		String name = "Booster for the foot";
		String time = "2023-04-09T10:50:23.1977575+01:00";
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
