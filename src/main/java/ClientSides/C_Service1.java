package ClientSides;

import com.google.protobuf.Empty;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

/*import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;*/

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import sw.stepCounter.service1.HourlyStepCount;
import sw.stepCounter.service1.HourlyStepRequest;
import sw.stepCounter.service1.StepCount;
import sw.stepCounter.service1.StepCounterGrpc;
import sw.stepCounter.service1.StepCounterGrpc.StepCounterBlockingStub;
import sw.stepCounter.service1.StepCounterGrpc.StepCounterStub;
import sw.stepCounter.service1.StepsRequest;
import sw.stepCounter.service1.WeekDays;

public class C_Service1 {
	
	
	private static StepCounterBlockingStub blockingStub;
	private static StepCounterStub asyncStub;
	private static ManagedChannel managedChannel;
	//private static final Logger logger = LogManager.getLogger(C_Service1.class);
	private static ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();;
	private static AtomicBoolean isStreaming = new AtomicBoolean(false);
	private static ServiceInfo stepInfo;
	
	/*private static void discoverStepService(String service_type) {
		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
				
			jmdns.addServiceListener(service_type, new ServiceListener() {
				
				@Override
				public void serviceResolved(ServiceEvent event) {
					System.out.println("Step Service resolved: " + event.getInfo());

					stepInfo = event.getInfo();

					int port = stepInfo.getPort();
					
					System.out.println("resolving " + service_type + " with properties ...");
					System.out.println("\t port: " + port);
					System.out.println("\t type:"+ event.getType());
					System.out.println("\t name: " + event.getName());
					System.out.println("\t description/properties: " + stepInfo.getNiceTextString());
					System.out.println("\t host: " + stepInfo.getHostAddresses()[0]);
				
				}
				
				@Override
				public void serviceRemoved(ServiceEvent event) {
					System.out.println("Step Service removed: " + event.getInfo());

					
				}
				
				@Override
				public void serviceAdded(ServiceEvent event) {
					System.out.println("Step Service added: " + event.getInfo());	
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}*/
	
	public static void main(String[] args) {
		//System.setProperty("java.util.logging.config.file", "src/main/resources/logging.properties");
		
		/*String step_service_type = "steps._tcp.local.";
		discoverStepService(step_service_type);
		
		String host = stepInfo.getHostAddresses()[0];
		int port = stepInfo.getPort();
		
		ManagedChannel channel = ManagedChannelBuilder
				.forAddress(host, port)
				.usePlaintext()
				.build();*/
		
		managedChannel = ManagedChannelBuilder
				.forAddress("localhost", 1031)
				.usePlaintext()
				.build();
		//stubs -- generate from proto
		blockingStub = StepCounterGrpc.newBlockingStub(managedChannel);

		asyncStub = StepCounterGrpc.newStub(managedChannel);
		

		
		Scanner scanner = new Scanner(System.in);
	    String input;
	    boolean isRunning = false;

	    while (true) {
	        System.out.println("Enter 'start' to start sending steps, 'stop' to stop sending steps, or 'exit' to quit:");
	        input = scanner.nextLine();

	        if (input.equalsIgnoreCase("start") && !isRunning) {
	            try {
	                lastHour();
	                getAverage();
	                startStepStreamingRequest();
	                //stepStreamingRequest();
	                
	                isRunning = true;
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        } else if (input.equalsIgnoreCase("stop") && isRunning) {
	            System.out.println("System paused!!");
	            isStreaming.set(false);
	            isRunning = false;

	        } else if (input.equalsIgnoreCase("exit")) {
	            if (isRunning) {
	                executor.shutdown();
	            }
	            managedChannel.shutdown();
	            scanner.close();
	            break;
	        } else {
	            System.out.println("Invalid command. Please enter 'start', 'stop', or 'exit'.");
	        }
	    }
	}

	
	
	public static void stepStreamingRequest() throws InterruptedException {
	    //System.out.println("-- Client side stepStreamingRequest Invoked");
	    CountDownLatch latch = new CountDownLatch(2);
	    StreamObserver<StepsRequest> streamObserver = asyncStub.sendSteps(new StepStreamObserver(latch));

	    // Create a ScheduledExecutorService to send 50 steps every 1 minute
	    //executor = Executors.newSingleThreadScheduledExecutor();
	    executor.scheduleAtFixedRate(() -> {
	        if (isStreaming.get()) {
	            try {
	                StepsRequest stepsRequest = StepsRequest.newBuilder().setSteps(25).build();
	                streamObserver.onNext(stepsRequest);
	                System.out.println("ClientSide: sent 25 steps");
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }, 0, 1, TimeUnit.MINUTES);

	    //latch.await();
	    //Thread.sleep(100);
	}
	
	public static void startStepStreamingRequest() {
	    isStreaming.set(true);
	    new Thread(() -> {
	        try {
	            stepStreamingRequest();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }).start();
	}
	
	public static void lastHour() {
		System.out.println("--ClientSide : lastHour() invoked");
		try {
			StepCount stepCount = blockingStub.getLastHourSteps(Empty.getDefaultInstance());
			//logger.info("The steps taken in the last hour : " + stepCount.getCount());
			System.out.println("Test in lasthour: " + stepCount.getCount());
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}/*finally {
			try {
				managedChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
			} catch (InterruptedException e2) {
				// TODO: handle exception
			}
		}*/
	}
	
	public static void getAverage() {
		System.out.println("getAverage invoked!!");
		WeekDays week = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("Please type from which day to now want to see the daily average"
				+ "/n last day, last 5 day, last 10 day, last 30 Day");
		
		while (true) {
			String period = sc.nextLine();
			if(period.equalsIgnoreCase("last day")) {
				week = WeekDays.LAST_DAY;
				break;
			}else if(period.equalsIgnoreCase("last 5 day")){
				week = WeekDays.LAST_5_DAYS;
				break;
			}else if(period.equalsIgnoreCase("last 10 day")){
				week = WeekDays.LAST_10_DAYS;
				break;
			}else if(period.equalsIgnoreCase("last 30 day")){
				week = WeekDays.LAST_30_DAYS;
				break;
			}else {
				System.out.println("Please type valid period!!");
			}
		}
		
		//WeekDays.LAST_DAY
		
		HourlyStepRequest req = HourlyStepRequest.newBuilder().setWeekDays(week).build();
		HourlyStepCount response = blockingStub.getAverageHourlySteps(req);
		System.out.println(
				"For the period: " + response.getWeekDays() + "\n"+
				"Average steps: " + response.getAverageSteps()+ "\n"+
				"Message: " + response.getMessage()
				);
	}

}
