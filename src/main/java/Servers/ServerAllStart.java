package Servers;
/**
 * @author emrek
 * @date 19/04/2023
 * */

public class ServerAllStart {

	public static void startServers() {
		GrpcServer1 server1 = new GrpcServer1();
		GrpcServer2 server2 = new GrpcServer2();
		GrpcServer3 server3 = new GrpcServer3();
		server1.start();
		server2.start();
		server3.start();
	}
	
}
