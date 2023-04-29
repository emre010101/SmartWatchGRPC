package Services;

import java.util.List;

import com.google.protobuf.Empty;

import io.grpc.stub.StreamObserver;
import sw.Reminder.service2.ReminderGrpc.ReminderImplBase;
import sw.Reminder.service2.ServerResponse;
import sw.Reminder.service2.TaskComplete;
import sw.Reminder.service2.TaskReminder;


public class S_Service2 extends ReminderImplBase{

	/*Receives the taks to be saved and sends them to database and return a message indicating everything went well*/
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
		System.out.println("Receiving the task completed: " + request.getTaskName());
		String taskName = request.getTaskName();
		String reply = DataBaseConsulter.markCompleted(taskName);
		ServerResponse response = ServerResponse.newBuilder().setConfirmed(reply).build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	@Override
	public void getTaskList(Empty request, StreamObserver<TaskReminder> responseObserver) {
		System.out.println("Client consulted the getTaskList in serverSide");
		List<String> unmarkeds = DataBaseConsulter.checkUnmarkedReminders();
		if(unmarkeds.size()==0) {
			TaskReminder empty = TaskReminder.newBuilder().setDateTime(null).setTaskName("There is no unmarked reminder in that name").setType(null).build();
			responseObserver.onNext(empty);
			responseObserver.onCompleted();
		}else {
			for(String line : unmarkeds) {
				String[] parts = line.split("<->");
				TaskReminder reply = TaskReminder.newBuilder().setTaskName(parts[0]).setDateTime(parts[1]).setTypeValue(Integer.parseInt(parts[2])).build();
				responseObserver.onNext(reply);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Server: getTaskList is completed...");
			responseObserver.onCompleted();
		}
	}

	
} 
