package sw.Reminder.service2;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * Reminder service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: service2.proto")
public final class ReminderGrpc {

  private ReminderGrpc() {}

  public static final String SERVICE_NAME = "service2.Reminder";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sw.Reminder.service2.TaskReminder,
      sw.Reminder.service2.ServerResponse> getSetTaskReminderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetTaskReminder",
      requestType = sw.Reminder.service2.TaskReminder.class,
      responseType = sw.Reminder.service2.ServerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sw.Reminder.service2.TaskReminder,
      sw.Reminder.service2.ServerResponse> getSetTaskReminderMethod() {
    io.grpc.MethodDescriptor<sw.Reminder.service2.TaskReminder, sw.Reminder.service2.ServerResponse> getSetTaskReminderMethod;
    if ((getSetTaskReminderMethod = ReminderGrpc.getSetTaskReminderMethod) == null) {
      synchronized (ReminderGrpc.class) {
        if ((getSetTaskReminderMethod = ReminderGrpc.getSetTaskReminderMethod) == null) {
          ReminderGrpc.getSetTaskReminderMethod = getSetTaskReminderMethod = 
              io.grpc.MethodDescriptor.<sw.Reminder.service2.TaskReminder, sw.Reminder.service2.ServerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "service2.Reminder", "SetTaskReminder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sw.Reminder.service2.TaskReminder.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sw.Reminder.service2.ServerResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ReminderMethodDescriptorSupplier("SetTaskReminder"))
                  .build();
          }
        }
     }
     return getSetTaskReminderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      sw.Reminder.service2.ServerResponse> getCheckReminderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "checkReminder",
      requestType = com.google.protobuf.Empty.class,
      responseType = sw.Reminder.service2.ServerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      sw.Reminder.service2.ServerResponse> getCheckReminderMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, sw.Reminder.service2.ServerResponse> getCheckReminderMethod;
    if ((getCheckReminderMethod = ReminderGrpc.getCheckReminderMethod) == null) {
      synchronized (ReminderGrpc.class) {
        if ((getCheckReminderMethod = ReminderGrpc.getCheckReminderMethod) == null) {
          ReminderGrpc.getCheckReminderMethod = getCheckReminderMethod = 
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, sw.Reminder.service2.ServerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "service2.Reminder", "checkReminder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sw.Reminder.service2.ServerResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ReminderMethodDescriptorSupplier("checkReminder"))
                  .build();
          }
        }
     }
     return getCheckReminderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sw.Reminder.service2.TaskComplete,
      sw.Reminder.service2.ServerResponse> getMarkTaskCompleteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MarkTaskComplete",
      requestType = sw.Reminder.service2.TaskComplete.class,
      responseType = sw.Reminder.service2.ServerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sw.Reminder.service2.TaskComplete,
      sw.Reminder.service2.ServerResponse> getMarkTaskCompleteMethod() {
    io.grpc.MethodDescriptor<sw.Reminder.service2.TaskComplete, sw.Reminder.service2.ServerResponse> getMarkTaskCompleteMethod;
    if ((getMarkTaskCompleteMethod = ReminderGrpc.getMarkTaskCompleteMethod) == null) {
      synchronized (ReminderGrpc.class) {
        if ((getMarkTaskCompleteMethod = ReminderGrpc.getMarkTaskCompleteMethod) == null) {
          ReminderGrpc.getMarkTaskCompleteMethod = getMarkTaskCompleteMethod = 
              io.grpc.MethodDescriptor.<sw.Reminder.service2.TaskComplete, sw.Reminder.service2.ServerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "service2.Reminder", "MarkTaskComplete"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sw.Reminder.service2.TaskComplete.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sw.Reminder.service2.ServerResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ReminderMethodDescriptorSupplier("MarkTaskComplete"))
                  .build();
          }
        }
     }
     return getMarkTaskCompleteMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      sw.Reminder.service2.TaskReminder> getGetTaskListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetTaskList",
      requestType = com.google.protobuf.Empty.class,
      responseType = sw.Reminder.service2.TaskReminder.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      sw.Reminder.service2.TaskReminder> getGetTaskListMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, sw.Reminder.service2.TaskReminder> getGetTaskListMethod;
    if ((getGetTaskListMethod = ReminderGrpc.getGetTaskListMethod) == null) {
      synchronized (ReminderGrpc.class) {
        if ((getGetTaskListMethod = ReminderGrpc.getGetTaskListMethod) == null) {
          ReminderGrpc.getGetTaskListMethod = getGetTaskListMethod = 
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, sw.Reminder.service2.TaskReminder>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "service2.Reminder", "GetTaskList"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sw.Reminder.service2.TaskReminder.getDefaultInstance()))
                  .setSchemaDescriptor(new ReminderMethodDescriptorSupplier("GetTaskList"))
                  .build();
          }
        }
     }
     return getGetTaskListMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ReminderStub newStub(io.grpc.Channel channel) {
    return new ReminderStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ReminderBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ReminderBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ReminderFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ReminderFutureStub(channel);
  }

  /**
   * <pre>
   * Reminder service
   * </pre>
   */
  public static abstract class ReminderImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Saves when pills need to be taken and others tasks and reminds the user (Unary)
     * </pre>
     */
    public void setTaskReminder(sw.Reminder.service2.TaskReminder request,
        io.grpc.stub.StreamObserver<sw.Reminder.service2.ServerResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSetTaskReminderMethod(), responseObserver);
    }

    /**
     * <pre>
     * Check the reminders whether the time is up or not(Server Streaming)
     * </pre>
     */
    public void checkReminder(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<sw.Reminder.service2.ServerResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCheckReminderMethod(), responseObserver);
    }

    /**
     * <pre>
     * Tracks completion of a task/event (Unary) //It could be doctor appointment reminder or whatever
     * </pre>
     */
    public void markTaskComplete(sw.Reminder.service2.TaskComplete request,
        io.grpc.stub.StreamObserver<sw.Reminder.service2.ServerResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMarkTaskCompleteMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves the list of tasks that have been set on the server (Server Streaming)
     * </pre>
     */
    public void getTaskList(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<sw.Reminder.service2.TaskReminder> responseObserver) {
      asyncUnimplementedUnaryCall(getGetTaskListMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSetTaskReminderMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sw.Reminder.service2.TaskReminder,
                sw.Reminder.service2.ServerResponse>(
                  this, METHODID_SET_TASK_REMINDER)))
          .addMethod(
            getCheckReminderMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                sw.Reminder.service2.ServerResponse>(
                  this, METHODID_CHECK_REMINDER)))
          .addMethod(
            getMarkTaskCompleteMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sw.Reminder.service2.TaskComplete,
                sw.Reminder.service2.ServerResponse>(
                  this, METHODID_MARK_TASK_COMPLETE)))
          .addMethod(
            getGetTaskListMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                sw.Reminder.service2.TaskReminder>(
                  this, METHODID_GET_TASK_LIST)))
          .build();
    }
  }

  /**
   * <pre>
   * Reminder service
   * </pre>
   */
  public static final class ReminderStub extends io.grpc.stub.AbstractStub<ReminderStub> {
    private ReminderStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ReminderStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ReminderStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ReminderStub(channel, callOptions);
    }

    /**
     * <pre>
     * Saves when pills need to be taken and others tasks and reminds the user (Unary)
     * </pre>
     */
    public void setTaskReminder(sw.Reminder.service2.TaskReminder request,
        io.grpc.stub.StreamObserver<sw.Reminder.service2.ServerResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetTaskReminderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Check the reminders whether the time is up or not(Server Streaming)
     * </pre>
     */
    public void checkReminder(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<sw.Reminder.service2.ServerResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getCheckReminderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Tracks completion of a task/event (Unary) //It could be doctor appointment reminder or whatever
     * </pre>
     */
    public void markTaskComplete(sw.Reminder.service2.TaskComplete request,
        io.grpc.stub.StreamObserver<sw.Reminder.service2.ServerResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMarkTaskCompleteMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves the list of tasks that have been set on the server (Server Streaming)
     * </pre>
     */
    public void getTaskList(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<sw.Reminder.service2.TaskReminder> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGetTaskListMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Reminder service
   * </pre>
   */
  public static final class ReminderBlockingStub extends io.grpc.stub.AbstractStub<ReminderBlockingStub> {
    private ReminderBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ReminderBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ReminderBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ReminderBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Saves when pills need to be taken and others tasks and reminds the user (Unary)
     * </pre>
     */
    public sw.Reminder.service2.ServerResponse setTaskReminder(sw.Reminder.service2.TaskReminder request) {
      return blockingUnaryCall(
          getChannel(), getSetTaskReminderMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Check the reminders whether the time is up or not(Server Streaming)
     * </pre>
     */
    public java.util.Iterator<sw.Reminder.service2.ServerResponse> checkReminder(
        com.google.protobuf.Empty request) {
      return blockingServerStreamingCall(
          getChannel(), getCheckReminderMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Tracks completion of a task/event (Unary) //It could be doctor appointment reminder or whatever
     * </pre>
     */
    public sw.Reminder.service2.ServerResponse markTaskComplete(sw.Reminder.service2.TaskComplete request) {
      return blockingUnaryCall(
          getChannel(), getMarkTaskCompleteMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves the list of tasks that have been set on the server (Server Streaming)
     * </pre>
     */
    public java.util.Iterator<sw.Reminder.service2.TaskReminder> getTaskList(
        com.google.protobuf.Empty request) {
      return blockingServerStreamingCall(
          getChannel(), getGetTaskListMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Reminder service
   * </pre>
   */
  public static final class ReminderFutureStub extends io.grpc.stub.AbstractStub<ReminderFutureStub> {
    private ReminderFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ReminderFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ReminderFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ReminderFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Saves when pills need to be taken and others tasks and reminds the user (Unary)
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<sw.Reminder.service2.ServerResponse> setTaskReminder(
        sw.Reminder.service2.TaskReminder request) {
      return futureUnaryCall(
          getChannel().newCall(getSetTaskReminderMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Tracks completion of a task/event (Unary) //It could be doctor appointment reminder or whatever
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<sw.Reminder.service2.ServerResponse> markTaskComplete(
        sw.Reminder.service2.TaskComplete request) {
      return futureUnaryCall(
          getChannel().newCall(getMarkTaskCompleteMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SET_TASK_REMINDER = 0;
  private static final int METHODID_CHECK_REMINDER = 1;
  private static final int METHODID_MARK_TASK_COMPLETE = 2;
  private static final int METHODID_GET_TASK_LIST = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ReminderImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ReminderImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SET_TASK_REMINDER:
          serviceImpl.setTaskReminder((sw.Reminder.service2.TaskReminder) request,
              (io.grpc.stub.StreamObserver<sw.Reminder.service2.ServerResponse>) responseObserver);
          break;
        case METHODID_CHECK_REMINDER:
          serviceImpl.checkReminder((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<sw.Reminder.service2.ServerResponse>) responseObserver);
          break;
        case METHODID_MARK_TASK_COMPLETE:
          serviceImpl.markTaskComplete((sw.Reminder.service2.TaskComplete) request,
              (io.grpc.stub.StreamObserver<sw.Reminder.service2.ServerResponse>) responseObserver);
          break;
        case METHODID_GET_TASK_LIST:
          serviceImpl.getTaskList((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<sw.Reminder.service2.TaskReminder>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ReminderBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ReminderBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return sw.Reminder.service2.ReminderImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Reminder");
    }
  }

  private static final class ReminderFileDescriptorSupplier
      extends ReminderBaseDescriptorSupplier {
    ReminderFileDescriptorSupplier() {}
  }

  private static final class ReminderMethodDescriptorSupplier
      extends ReminderBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ReminderMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ReminderGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ReminderFileDescriptorSupplier())
              .addMethod(getSetTaskReminderMethod())
              .addMethod(getCheckReminderMethod())
              .addMethod(getMarkTaskCompleteMethod())
              .addMethod(getGetTaskListMethod())
              .build();
        }
      }
    }
    return result;
  }
}
