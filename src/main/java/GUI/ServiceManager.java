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

	private static String service_type1 = "_steps._tcp.local.";
	private static String service_type2 = "reminder._tcp.local.";
	private static String service_type3 = "monitoring._tcp.local.";
	
	private static int port;
	private static String resolvedIP;
	
	public static void discover(String serviceName) {
		if(serviceName.equals("stepService")) {
			discoverService(service_type1);
			initializeStepsServiceChannel();
		}else if(serviceName.equals("reminderingService")) {
			discoverService(service_type2);
			initializeReminderingServiceChannel();
		}else if(serviceName.equals("monitoringService")) {
			discoverService(service_type3);
			initializeMonitoringServiceChannel();
		}else {
			AlertBox.display("error", "Service name does not match!!!");
		}
	}
	private static void discoverService(String serviceType) {
		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
				
			jmdns.addServiceListener(serviceType, new SampleListener());
			
			// Wait a bit
			Thread.sleep(20000);
			
			//jmdns.close();

		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	private static class SampleListener implements ServiceListener {
		public void serviceAdded(ServiceEvent event) {
			System.out.println("Service added: " + event.getInfo());
		}
		public void serviceRemoved(ServiceEvent event) {
			System.out.println("Service removed: " + event.getInfo());
		}
		@SuppressWarnings("deprecation")
		public void serviceResolved(ServiceEvent event) {
					System.out.println("Service resolved: " + event.getInfo());
	
                    ServiceInfo info = event.getInfo();
                    port = info.getPort();
                    resolvedIP = info.getHostAddress();                    
                    System.out.println("IP Resolved - " + resolvedIP + ":" + port);
		}
	}
	
	static void initializeStepsServiceChannel() {
		System.out.println("Initializing the step service in gui");
	    ManagedChannel stepChannel = ManagedChannelBuilder
	    		.forAddress(resolvedIP, port)
	    		.usePlaintext()
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

	static void initializeReminderingServiceChannel() {
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

	static void initializeMonitoringServiceChannel() {
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
