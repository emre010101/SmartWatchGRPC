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
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;

public class ServiceManager {
	
	//Creating stubs for each service
	static StepCounterBlockingStub blockingStubService1withMeta;
	static StepCounterStub asyncStubService1withMeta;
	static ReminderBlockingStub blockingStubService2withMeta;
	static ReminderStub asyncStubService2withMeta;
	static MonitoringBlockingStub blockingStubService3withMeta;
	static MonitoringStub asyncStubService3withMeta;

	//Each service port number and IP address
	private static int port1;
	private static String resolvedIP1;
	private static int port2;
	private static String resolvedIP2;
	private static int port3;
	private static String resolvedIP3;
	
	static // Create the metadata.
	Metadata metadata = new Metadata();

	public static void discoverAll() throws InterruptedException {
		
		//Adding the authentication API key to metedata
		metadata.put(Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER), "API key <9546287>");

		
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
		System.out.println("Teeeeeeeeeeeeees: " + resolvedIP1);
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
	    
	    // Initialise  the stubs for each service
	    blockingStubService1withMeta = StepCounterGrpc.newBlockingStub(stepChannel);
	    asyncStubService1withMeta = StepCounterGrpc.newStub(stepChannel);
	    
	    // Attach the metadata to the stubs
	    blockingStubService1withMeta = MetadataUtils.attachHeaders(blockingStubService1withMeta, metadata);
	    asyncStubService1withMeta = MetadataUtils.attachHeaders(asyncStubService1withMeta, metadata);
	}

	static void initializeReminderingServiceChannel() {
		System.out.println("Initializing the reminder service in gui");
	    ManagedChannel reminderChannel = ManagedChannelBuilder
	            .forAddress(resolvedIP2, port2)
	            .usePlaintext()
	            .build();
	  //Initialize  the stubs for each service
	    blockingStubService2withMeta = ReminderGrpc.newBlockingStub(reminderChannel);
	    asyncStubService2withMeta = ReminderGrpc.newStub(reminderChannel);
	    
	    blockingStubService2withMeta = MetadataUtils.attachHeaders(blockingStubService2withMeta, metadata);
	    asyncStubService2withMeta = MetadataUtils.attachHeaders(asyncStubService2withMeta, metadata);
	}

	static void initializeMonitoringServiceChannel() {
		System.out.println("Initializing the monitoring service in gui");
	    ManagedChannel monitoringChannel = ManagedChannelBuilder
	            .forAddress(resolvedIP3, port3)
	            .usePlaintext()
	            .build();
	  //Initialize  the stubs for each service
	    blockingStubService3withMeta = MonitoringGrpc.newBlockingStub(monitoringChannel);
	    asyncStubService3withMeta = MonitoringGrpc.newStub(monitoringChannel);
	    
	    blockingStubService3withMeta = MetadataUtils.attachHeaders(blockingStubService3withMeta, metadata);
	    asyncStubService3withMeta = MetadataUtils.attachHeaders(asyncStubService3withMeta, metadata);
	}
	
	static void shutdownStepChannel() {
	    if (blockingStubService1withMeta.getChannel() != null || asyncStubService1withMeta.getChannel() != null) {
	        if (blockingStubService1withMeta.getChannel() != null) {
	            ((ManagedChannel) blockingStubService1withMeta.getChannel()).shutdown();
	        }
	        if (asyncStubService1withMeta.getChannel() != null) {
	            ((ManagedChannel) asyncStubService1withMeta.getChannel()).shutdown();
	        }
	    }
	}
	
	static void shutdownReminderChannel() {
	    if (blockingStubService2withMeta.getChannel() != null || asyncStubService2withMeta.getChannel() != null) {
	        if (blockingStubService2withMeta.getChannel() != null) {
	            ((ManagedChannel) blockingStubService2withMeta.getChannel()).shutdown();
	        }
	        if (asyncStubService2withMeta.getChannel() != null) {
	            ((ManagedChannel) asyncStubService2withMeta.getChannel()).shutdown();
	        }
	    }
	}
	
	static void shutdownMonitoringChannel() {
	    if (blockingStubService3withMeta.getChannel() != null || asyncStubService3withMeta.getChannel() != null) {
	        if (blockingStubService3withMeta.getChannel() != null) {
	            ((ManagedChannel) blockingStubService3withMeta.getChannel()).shutdown();
	        }
	        if (asyncStubService3withMeta.getChannel() != null) {
	            ((ManagedChannel) asyncStubService3withMeta.getChannel()).shutdown();
	        }
	    }
	}


}
