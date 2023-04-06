package ClientSides;

import com.emrek.models.BankServiceGrpc;

import ds.examples.maths.MathServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import sw.stepCounter.service1.StepCounterGrpc;
import sw.stepCounter.service1.StepCounterGrpc.StepCounterBlockingStub;
import sw.stepCounter.service1.StepCounterGrpc.StepCounterStub;

public class Service1 {
	
	private static StepCounterBlockingStub blockingStub;
	private static StepCounterStub asyncStub;
	private static ManagedChannel managedChannel;

	public static void main(String[] args) {
		managedChannel = ManagedChannelBuilder
				.forAddress("localhost", 50069)
				.usePlaintext()
				.build();

		//stubs -- generate from proto
		blockingStub = StepCounterGrpc.newBlockingStub(managedChannel);

		asyncStub = StepCounterGrpc.newStub(managedChannel);

	}

}
