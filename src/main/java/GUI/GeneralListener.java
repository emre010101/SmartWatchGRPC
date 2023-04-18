package GUI;
/*
 * GeneralListener.java
 * @author Emre Kavak
 * @17/04/2023
 * 
 * */
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

/*A class to resolve dns address and find services information*/
public class GeneralListener extends Thread{
	
	public int port;
	public String resolvedIP;
	public String serviceType;
	
	public GeneralListener(String hst) {
		this.serviceType = hst;
		
	}
	
	@Override
	public void run() {
		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
				
			jmdns.addServiceListener(serviceType, new SampleListener());
			
			// Wait a bit
			Thread.sleep(20000);
			
			jmdns.close();
			
			System.out.println("Discovering the service: " + serviceType);

		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	private class SampleListener implements ServiceListener {
		
		@Override
		public void serviceAdded(ServiceEvent event) {
			System.out.println("Service added: " + event.getInfo());
		}
		@Override
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
	public int getPort() {
		return port;
	}


	public String getResolvedIP() {
		return resolvedIP;
	}


}
