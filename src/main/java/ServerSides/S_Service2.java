package ServerSides;

import com.google.protobuf.Empty;

import io.grpc.stub.StreamObserver;
import sw.Reminder.service2.ReminderGrpc.ReminderImplBase;
import sw.Reminder.service2.ServerResponse;
import sw.Reminder.service2.TaskComplete;
import sw.Reminder.service2.TaskReminder;

public class S_Service2 extends ReminderImplBase{

	@Override
	public void setTaskReminder(TaskReminder request, StreamObserver<ServerResponse> responseObserver) {
		System.out.println("Receiving the reminder" + request.getTaskName() + request.getDateTime() + request.getTypeValue());
		String taskName = request.getTaskName();
		String date_time = request.getDateTime();
		int type = request.getTypeValue();
		String reply = DataBaseConsulter.saveReminder(taskName, date_time, type);
		ServerResponse response = ServerResponse.newBuilder().setConfirmed(reply).build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}


	@Override
	public void markTaskComplete(TaskComplete request, StreamObserver<ServerResponse> responseObserver) {
		// TODO Auto-generated method stub
		super.markTaskComplete(request, responseObserver);
	}

	@Override
	public void getTaskList(Empty request, StreamObserver<TaskReminder> responseObserver) {
		// TODO Auto-generated method stub
		super.getTaskList(request, responseObserver);
	}

	
} 
