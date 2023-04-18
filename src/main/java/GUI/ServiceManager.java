package GUI;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import sw.Monitoring.service3.MonitoringGrpc;
import sw.Monitoring.service3.MonitoringGrpc.MonitoringBlockingStub;
import sw.Monitoring.service3.MonitoringGrpc.MonitoringStub;
import sw.Reminder.service2.ReminderGrpc;
import sw.Reminder.service2.ReminderGrpc.ReminderBlockingStub;
import sw.Reminder.service2.ReminderGrpc.ReminderStub;
import sw.stepCounter.service1.StepCounterGrpc;
import sw.stepCounter.service1.StepCounterGrpc.StepCounterBlockingStub;
import sw.stepCounter.service1.StepCounterGrpc.StepCounterStub;

public class ServiceManager {
	
	static StepCounterBlockingStub blockingStubService1;
	static StepCounterStub asyncStubService1;
	static ReminderBlockingStub blockingStubService2;
	static ReminderStub asyncStubService2;
	static MonitoringBlockingStub blockingStubService3;
	static MonitoringStub asyncStubService3;

	private static int port1;
	private static String resolvedIP1;
	private static int port2;
	private static String resolvedIP2;
	private static int port3;
	private static String resolvedIP3;

	public static void discoverAll() throws InterruptedException {
		
		//Creating the instances of the listener for each service
		GeneralListener step = new GeneralListener("_steps._tcp.local.");
		GeneralListener reminder = new GeneralListener("_reminder._tcp.local.");
		GeneralListener monitor = new GeneralListener("_monitoring._tcp.local.");

		//Starting the treats at the same time to discover the services
		step.start();
		reminder.start();
		monitor.start();
		
		//Wait until discover
		step.join();
		port1 = step.getPort();
		resolvedIP1 = step.getResolvedIP();
		initializeStepsServiceChannel();
		reminder.join();
		port2 = reminder.getPort();
		resolvedIP2 = reminder.getResolvedIP();
		initializeReminderingServiceChannel();
		monitor.join();
		port3 = monitor.getPort();
		resolvedIP3 =monitor.getResolvedIP();
		initializeMonitoringServiceChannel();
	}
	
	public static void main(String[] args) throws InterruptedException {
		discoverAll();
		System.out.println("----------------------------------");
		System.out.println("After multithread: " + port1
				+ ", resolvedIP: " + resolvedIP1
				);
		System.out.println("After multithread: " + port2
				+ ", resolvedIP: " + resolvedIP2
				);
		System.out.println("After multithread: " + port3
				+ ", resolvedIP: " + resolvedIP3
				);
	}
	

	
	static void initializeStepsServiceChannel() {
		System.out.println("Initializing the step service in gui");
	    ManagedChannel stepChannel = ManagedChannelBuilder
	    		.forAddress(resolvedIP1, port1)
	    		.usePlaintext()
	    		.build();
	    
	    blockingStubService1 = StepCounterGrpc.newBlockingStub(stepChannel);
	    asyncStubService1 = StepCounterGrpc.newStub(stepChannel);
		
	}

	static void initializeReminderingServiceChannel() {
	    ManagedChannel reminderChannel = ManagedChannelBuilder
	            .forAddress(resolvedIP2, port2)
	            .usePlaintext()
	            .build();
	    blockingStubService2 = ReminderGrpc.newBlockingStub(reminderChannel);
	    asyncStubService2 = ReminderGrpc.newStub(reminderChannel);
	}

	static void initializeMonitoringServiceChannel() {
	    ManagedChannel monitoringChannel = ManagedChannelBuilder
	            .forAddress(resolvedIP3, port3)
	            .usePlaintext()
	            .build();
	    blockingStubService3 = MonitoringGrpc.newBlockingStub(monitoringChannel);
	    asyncStubService3 = MonitoringGrpc.newStub(monitoringChannel);
	}
	
	static void shutdownStepChannel() {
	    if (blockingStubService1.getChannel() != null || asyncStubService1.getChannel() != null) {
	        if (blockingStubService1.getChannel() != null) {
	            ((ManagedChannel) blockingStubService1.getChannel()).shutdown();
	        }
	        if (asyncStubService1.getChannel() != null) {
	            ((ManagedChannel) asyncStubService1.getChannel()).shutdown();
	        }
	    }
	}
	
	static void shutdownReminderChannel() {
	    if (blockingStubService2.getChannel() != null || asyncStubService2.getChannel() != null) {
	        if (blockingStubService2.getChannel() != null) {
	            ((ManagedChannel) blockingStubService2.getChannel()).shutdown();
	        }
	        if (asyncStubService2.getChannel() != null) {
	            ((ManagedChannel) asyncStubService2.getChannel()).shutdown();
	        }
	    }
	}
	
	static void shutdownMonitoringChannel() {
	    if (blockingStubService3.getChannel() != null || asyncStubService3.getChannel() != null) {
	        if (blockingStubService3.getChannel() != null) {
	            ((ManagedChannel) blockingStubService3.getChannel()).shutdown();
	        }
	        if (asyncStubService3.getChannel() != null) {
	            ((ManagedChannel) asyncStubService3.getChannel()).shutdown();
	        }
	    }
	}


}
