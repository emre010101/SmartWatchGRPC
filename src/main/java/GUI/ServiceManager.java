package GUI;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.function.Consumer;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

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

	/*private static String service_type1 = "steps._tcp.local.";
	private static String service_type2 = "reminder._tcp.local.";
	private static String service_type3 = "monitoring._tcp.local.";
	
	private static ServiceInfo StepServiceInfo;
	private static ServiceInfo ReminderServiceInfo;
	private static ServiceInfo MonitoringServiceInfo;
	
	private JmDNS jmdns;
	*/
	void discoverServices() throws IOException {
		/*System.out.println("discoverServices invoked");
		try {
			discoverService(service_type1, info -> StepServiceInfo = info);
			discoverService(service_type2, info -> ReminderServiceInfo = info);
			discoverService(service_type3, info -> MonitoringServiceInfo = info);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

	   /* try {
	        Thread.sleep(3000); // Use a longer sleep duration to give enough time for the services to be discovered
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	    
	    jmdns.close(); // Close the JmDNS instance after the services are discovered
	    
	    System.out.println("Test: " + StepServiceInfo.getPort());*/
	}

	private static void discoverService(String serviceType, Consumer<ServiceInfo> serviceInfoUpdater) throws InterruptedException {
	    try {
	    	System.out.println("discover service is invoked");
	        JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

	        jmdns.addServiceListener(serviceType, new ServiceListener() {
	            @Override
	            public void serviceResolved(ServiceEvent event) {
	                System.out.println("Service resolved: " + event.getInfo());
	                serviceInfoUpdater.accept(event.getInfo());
	            }

	            @Override
	            public void serviceRemoved(ServiceEvent event) {
	                System.out.println("Service removed: " + event.getInfo());
	            }

	            @Override
	            public void serviceAdded(ServiceEvent event) {
	                System.out.println("Service added: " + event.getInfo());
	                jmdns.requestServiceInfo(event.getType(), event.getName(), 1); // Add this line to request service info
	            }
	        });

	        Thread.sleep(500);

	        jmdns.close();
	    } catch (UnknownHostException e) {
	        System.out.println(e.getMessage());
	    } catch (IOException e) {
	        System.out.println(e.getMessage());
	    }
	    System.out.println("Begin: ");
	}
	
	static void initializeStepServiceChannel() {
		//service_type1 = "steps._tcp.local.";
		String reminderHost = "localhost"; 
		int reminderPort = 1031;
		System.out.println("Initializing the step service in gui");
	    ManagedChannel stepChannel = ManagedChannelBuilder
	    		.forAddress(reminderHost, reminderPort)
	    		.build();
	    
	    blockingStubService1 = StepCounterGrpc.newBlockingStub(stepChannel);
	    asyncStubService1 = StepCounterGrpc.newStub(stepChannel);
		/*try {
			if(StepServiceInfo!=null) {
			    String stepHost = StepServiceInfo.getHostAddresses()[0];
			    int stepPort = StepServiceInfo.getPort();
			    System.out.println("Step Service Host in initiliazed: " + stepHost);
			    System.out.println("Step Service Port in initiliazed: " + stepPort);
			    ManagedChannel stepChannel = ManagedChannelBuilder
			            .forAddress(stepHost, stepPort)
			            .usePlaintext()
			            .build();
			    blockingStubService1 = StepCounterGrpc.newBlockingStub(stepChannel);
			    asyncStubService1 = StepCounterGrpc.newStub(stepChannel);
			}else {
				AlertBox.display("Null exception", "Step Service is not intiliazed");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}*/
	}

	static void initializeReminderServiceChannel() {
		String reminderHost = "localhost"; 
		int reminderPort = 1050;
	   /* String reminderHost = ReminderServiceInfo.getHostAddresses()[0];
	    int reminderPort = ReminderServiceInfo.getPort();*/
	    ManagedChannel reminderChannel = ManagedChannelBuilder
	            .forAddress(reminderHost, reminderPort)
	            .usePlaintext()
	            .build();
	    blockingStubService2 = ReminderGrpc.newBlockingStub(reminderChannel);
	    asyncStubService2 = ReminderGrpc.newStub(reminderChannel);
	}

	static void initializeMonitoringChannel() {
	    /*String monitoringHost = MonitoringServiceInfo.getHostAddresses()[0];
	    int monitoringPort = MonitoringServiceInfo.getPort();*/
		String monitoringHost = "localhost";
		int monitoringPort = 1082;
	    ManagedChannel monitoringChannel = ManagedChannelBuilder
	            .forAddress(monitoringHost, monitoringPort)
	            .usePlaintext()
	            .build();
	    blockingStubService3 = MonitoringGrpc.newBlockingStub(monitoringChannel);
	    asyncStubService3 = MonitoringGrpc.newStub(monitoringChannel);
	}
	
	static void shutdownChannel(int channelnumber) {
		if(channelnumber == 1 || channelnumber == 0 ) {
		    if (blockingStubService1.getChannel() != null) {
		    	((ManagedChannel) blockingStubService1.getChannel()).shutdown();
		    }
		    if (asyncStubService1.getChannel() != null) {
		    	((ManagedChannel) asyncStubService1.getChannel()).shutdown();
		    }
		}
		if(channelnumber == 2 || channelnumber == 0)  {
		    if (blockingStubService2.getChannel() != null) {
		    	((ManagedChannel) blockingStubService2.getChannel()).shutdown();
		    }
		    if (asyncStubService2.getChannel() != null) {
		    	((ManagedChannel) asyncStubService2.getChannel()).shutdown();
		    }
		}
		if(channelnumber == 3 || channelnumber == 0)  {
		    if (blockingStubService3.getChannel() != null) {
		    	((ManagedChannel) blockingStubService3.getChannel()).shutdown();
		    }
		    if (asyncStubService3.getChannel() != null) {
		    	((ManagedChannel) asyncStubService3.getChannel()).shutdown();
		    }
		}
	}

}
