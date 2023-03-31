package ServerSides;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Properties;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import com.google.protobuf.Empty;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import sw.stepCounter.service1.HourlyStepCount;
import sw.stepCounter.service1.HourlyStepRequest;
import sw.stepCounter.service1.StepCount;
import sw.stepCounter.service1.StepCounterGrpc.StepCounterImplBase;
import sw.stepCounter.service1.StepGoal;
import sw.stepCounter.service1.StepGoalResponse;
import sw.stepCounter.service1.StepsRequest;


public class Service1 extends StepCounterImplBase{
	
	public static void main(String[] args) {
		//Creating the instance of the Service1
		Service1 stepCounter = new Service1();

		//Properties for required registering the server saved in different file
		Properties prop = stepCounter.getProperties();
		
		//Method implemented below
		stepCounter.registerService(prop);
		
		//Port number is also in the properties file
		int port = Integer.valueOf( prop.getProperty("service_port") );// #.50051;

		//Very important use try and catch when starting the server
		try {
			//Server starting on the port number given
			Server server = ServerBuilder.forPort(port)
					.addService(stepCounter)
					.build()
					.start();
			
			System.out.println("StepCounter Server started, listening on " + port);

			server.awaitTermination();


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	/*Method implemented to read and save the properties in the new instance of properties "prop"*/
	private Properties getProperties() {
		
		Properties prop = null;		
		
		 try (InputStream input = new FileInputStream("src/main/resources/service1.properties")) {

	            prop = new Properties();

	            // load a properties file
	            prop.load(input);

	            // get the property value and print it out
	            System.out.println("StepCounter Service properties ...");
	            System.out.println("\t service_type: " + prop.getProperty("service_type"));
	            System.out.println("\t service_name: " +prop.getProperty("service_name"));
	            System.out.println("\t service_description: " +prop.getProperty("service_description"));
		        System.out.println("\t service_port: " +prop.getProperty("service_port"));

	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	
		 return prop;
	}
	
	//Passing the properties to register and share using JmDNS
	private  void registerService(Properties prop) {
		
		 try {
	            // Create a JmDNS instance
	            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
	            
	            String service_type = prop.getProperty("service_type") ;//"_http._tcp.local.";
	            String service_name = prop.getProperty("service_name")  ;// "example";
	           // int service_port = 1234;
	            int service_port = Integer.valueOf( prop.getProperty("service_port") );// #.50051;
	            String service_description_properties = prop.getProperty("service_description")  ;//"path=index.html";
	            
	            // Register a service
	            ServiceInfo serviceInfo = ServiceInfo.create(service_type, service_name, service_port, service_description_properties);
	            jmdns.registerService(serviceInfo);
	            
	            System.out.printf("registrering service with type %s and name %s \n", service_type, service_name);
	            
	            // Wait a bit
	            Thread.sleep(1000);

	            // Unregister all services
	            //jmdns.unregisterAllServices();

	        } catch (IOException e) {
	            System.out.println(e.getMessage());
	        } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    
	}

	@Override
	public StreamObserver<StepsRequest> sendSteps(StreamObserver<Empty> responseObserver) {
		// TODO Auto-generated method stub
		return super.sendSteps(responseObserver);
	}

	@Override
	public void getLastHourSteps(Empty request, StreamObserver<StepCount> responseObserver) {
		// TODO Auto-generated method stub
		super.getLastHourSteps(request, responseObserver);
	}

	@Override
	public StreamObserver<HourlyStepRequest> getAverageHourlySteps(StreamObserver<HourlyStepCount> responseObserver) {
		// TODO Auto-generated method stub
		return super.getAverageHourlySteps(responseObserver);
	}

	@Override
	public void setStepGoal(StepGoal request, StreamObserver<StepGoalResponse> responseObserver) {
		// TODO Auto-generated method stub
		super.setStepGoal(request, responseObserver);
	}

	

	
}
