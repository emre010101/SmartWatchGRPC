package Servers;
/**
 * @author emrek
 * @date 10/04/2023
 * Server to add service and register using jmdns
 * */

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Properties;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import Services.S_Service1;
import io.grpc.Server;
import io.grpc.ServerBuilder;


public class GrpcServer1 extends Thread{

	//private JmDNS jmdns;

	public void run(){
		//Creating the instance of the Server
		GrpcServer1 stepserver = new GrpcServer1();
		
		//Properties for required registering the server
		Properties prop = stepserver.getProperties();
		
		//Registering the properties
		stepserver.registerService(prop);
		
		//Port number is also in the properties file
		int port = Integer.valueOf(prop.getProperty("service_port"));
		
		
		//Using try and catch on starting a server
		try {
			//Server starting on the port number given
			Server server1 = ServerBuilder.forPort(port)
					.addService(new S_Service1())
					.intercept(new AuthInterceptor())
					.build()
					.start();
			
			System.out.println("Step server started listening on " + port);
			//To avoid instance start and termination
			server1.awaitTermination();
			
		}catch(IOException | InterruptedException e) {
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
	            
	            String service_type = prop.getProperty("service_type") ;
	            String service_name = prop.getProperty("service_name")  ;
	            int service_port = Integer.valueOf( prop.getProperty("service_port") );

	            
	            String service_description_properties = prop.getProperty("service_description")  ;
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


}
