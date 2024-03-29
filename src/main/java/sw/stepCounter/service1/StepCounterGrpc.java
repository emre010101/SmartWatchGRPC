package sw.stepCounter.service1;

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
 * StepCounter service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: service1.proto")
public final class StepCounterGrpc {

  private StepCounterGrpc() {}

  public static final String SERVICE_NAME = "service1.StepCounter";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sw.stepCounter.service1.StepsRequest,
      sw.stepCounter.service1.StepCount> getSendStepsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendSteps",
      requestType = sw.stepCounter.service1.StepsRequest.class,
      responseType = sw.stepCounter.service1.StepCount.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<sw.stepCounter.service1.StepsRequest,
      sw.stepCounter.service1.StepCount> getSendStepsMethod() {
    io.grpc.MethodDescriptor<sw.stepCounter.service1.StepsRequest, sw.stepCounter.service1.StepCount> getSendStepsMethod;
    if ((getSendStepsMethod = StepCounterGrpc.getSendStepsMethod) == null) {
      synchronized (StepCounterGrpc.class) {
        if ((getSendStepsMethod = StepCounterGrpc.getSendStepsMethod) == null) {
          StepCounterGrpc.getSendStepsMethod = getSendStepsMethod = 
              io.grpc.MethodDescriptor.<sw.stepCounter.service1.StepsRequest, sw.stepCounter.service1.StepCount>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "service1.StepCounter", "SendSteps"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sw.stepCounter.service1.StepsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sw.stepCounter.service1.StepCount.getDefaultInstance()))
                  .setSchemaDescriptor(new StepCounterMethodDescriptorSupplier("SendSteps"))
                  .build();
          }
        }
     }
     return getSendStepsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      sw.stepCounter.service1.StepCount> getGetLastHourStepsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetLastHourSteps",
      requestType = com.google.protobuf.Empty.class,
      responseType = sw.stepCounter.service1.StepCount.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      sw.stepCounter.service1.StepCount> getGetLastHourStepsMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, sw.stepCounter.service1.StepCount> getGetLastHourStepsMethod;
    if ((getGetLastHourStepsMethod = StepCounterGrpc.getGetLastHourStepsMethod) == null) {
      synchronized (StepCounterGrpc.class) {
        if ((getGetLastHourStepsMethod = StepCounterGrpc.getGetLastHourStepsMethod) == null) {
          StepCounterGrpc.getGetLastHourStepsMethod = getGetLastHourStepsMethod = 
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, sw.stepCounter.service1.StepCount>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "service1.StepCounter", "GetLastHourSteps"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sw.stepCounter.service1.StepCount.getDefaultInstance()))
                  .setSchemaDescriptor(new StepCounterMethodDescriptorSupplier("GetLastHourSteps"))
                  .build();
          }
        }
     }
     return getGetLastHourStepsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sw.stepCounter.service1.AverageStepRequest,
      sw.stepCounter.service1.AverageStepCount> getGetAverageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAverage",
      requestType = sw.stepCounter.service1.AverageStepRequest.class,
      responseType = sw.stepCounter.service1.AverageStepCount.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sw.stepCounter.service1.AverageStepRequest,
      sw.stepCounter.service1.AverageStepCount> getGetAverageMethod() {
    io.grpc.MethodDescriptor<sw.stepCounter.service1.AverageStepRequest, sw.stepCounter.service1.AverageStepCount> getGetAverageMethod;
    if ((getGetAverageMethod = StepCounterGrpc.getGetAverageMethod) == null) {
      synchronized (StepCounterGrpc.class) {
        if ((getGetAverageMethod = StepCounterGrpc.getGetAverageMethod) == null) {
          StepCounterGrpc.getGetAverageMethod = getGetAverageMethod = 
              io.grpc.MethodDescriptor.<sw.stepCounter.service1.AverageStepRequest, sw.stepCounter.service1.AverageStepCount>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "service1.StepCounter", "GetAverage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sw.stepCounter.service1.AverageStepRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sw.stepCounter.service1.AverageStepCount.getDefaultInstance()))
                  .setSchemaDescriptor(new StepCounterMethodDescriptorSupplier("GetAverage"))
                  .build();
          }
        }
     }
     return getGetAverageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sw.stepCounter.service1.StepGoal,
      sw.stepCounter.service1.StepGoalResponse> getSetStepGoalMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetStepGoal",
      requestType = sw.stepCounter.service1.StepGoal.class,
      responseType = sw.stepCounter.service1.StepGoalResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<sw.stepCounter.service1.StepGoal,
      sw.stepCounter.service1.StepGoalResponse> getSetStepGoalMethod() {
    io.grpc.MethodDescriptor<sw.stepCounter.service1.StepGoal, sw.stepCounter.service1.StepGoalResponse> getSetStepGoalMethod;
    if ((getSetStepGoalMethod = StepCounterGrpc.getSetStepGoalMethod) == null) {
      synchronized (StepCounterGrpc.class) {
        if ((getSetStepGoalMethod = StepCounterGrpc.getSetStepGoalMethod) == null) {
          StepCounterGrpc.getSetStepGoalMethod = getSetStepGoalMethod = 
              io.grpc.MethodDescriptor.<sw.stepCounter.service1.StepGoal, sw.stepCounter.service1.StepGoalResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "service1.StepCounter", "SetStepGoal"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sw.stepCounter.service1.StepGoal.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sw.stepCounter.service1.StepGoalResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new StepCounterMethodDescriptorSupplier("SetStepGoal"))
                  .build();
          }
        }
     }
     return getSetStepGoalMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static StepCounterStub newStub(io.grpc.Channel channel) {
    return new StepCounterStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static StepCounterBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new StepCounterBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static StepCounterFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new StepCounterFutureStub(channel);
  }

  /**
   * <pre>
   * StepCounter service
   * </pre>
   */
  public static abstract class StepCounterImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Sends multiple steps every minute
     * </pre>
     */
    public io.grpc.stub.StreamObserver<sw.stepCounter.service1.StepsRequest> sendSteps(
        io.grpc.stub.StreamObserver<sw.stepCounter.service1.StepCount> responseObserver) {
      return asyncUnimplementedStreamingCall(getSendStepsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Requests how many steps have been done in the last hour (Unary)
     * </pre>
     */
    public void getLastHourSteps(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<sw.stepCounter.service1.StepCount> responseObserver) {
      asyncUnimplementedUnaryCall(getGetLastHourStepsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Requests average steps for the period given //changed
     * </pre>
     */
    public void getAverage(sw.stepCounter.service1.AverageStepRequest request,
        io.grpc.stub.StreamObserver<sw.stepCounter.service1.AverageStepCount> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAverageMethod(), responseObserver);
    }

    /**
     * <pre>
     * Sets a daily step goal and tracks progress towards that goal 
     * </pre>
     */
    public void setStepGoal(sw.stepCounter.service1.StepGoal request,
        io.grpc.stub.StreamObserver<sw.stepCounter.service1.StepGoalResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSetStepGoalMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSendStepsMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                sw.stepCounter.service1.StepsRequest,
                sw.stepCounter.service1.StepCount>(
                  this, METHODID_SEND_STEPS)))
          .addMethod(
            getGetLastHourStepsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                sw.stepCounter.service1.StepCount>(
                  this, METHODID_GET_LAST_HOUR_STEPS)))
          .addMethod(
            getGetAverageMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sw.stepCounter.service1.AverageStepRequest,
                sw.stepCounter.service1.AverageStepCount>(
                  this, METHODID_GET_AVERAGE)))
          .addMethod(
            getSetStepGoalMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                sw.stepCounter.service1.StepGoal,
                sw.stepCounter.service1.StepGoalResponse>(
                  this, METHODID_SET_STEP_GOAL)))
          .build();
    }
  }

  /**
   * <pre>
   * StepCounter service
   * </pre>
   */
  public static final class StepCounterStub extends io.grpc.stub.AbstractStub<StepCounterStub> {
    private StepCounterStub(io.grpc.Channel channel) {
      super(channel);
    }

    private StepCounterStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StepCounterStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new StepCounterStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends multiple steps every minute
     * </pre>
     */
    public io.grpc.stub.StreamObserver<sw.stepCounter.service1.StepsRequest> sendSteps(
        io.grpc.stub.StreamObserver<sw.stepCounter.service1.StepCount> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getSendStepsMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     * Requests how many steps have been done in the last hour (Unary)
     * </pre>
     */
    public void getLastHourSteps(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<sw.stepCounter.service1.StepCount> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetLastHourStepsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Requests average steps for the period given //changed
     * </pre>
     */
    public void getAverage(sw.stepCounter.service1.AverageStepRequest request,
        io.grpc.stub.StreamObserver<sw.stepCounter.service1.AverageStepCount> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAverageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Sets a daily step goal and tracks progress towards that goal 
     * </pre>
     */
    public void setStepGoal(sw.stepCounter.service1.StepGoal request,
        io.grpc.stub.StreamObserver<sw.stepCounter.service1.StepGoalResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getSetStepGoalMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * StepCounter service
   * </pre>
   */
  public static final class StepCounterBlockingStub extends io.grpc.stub.AbstractStub<StepCounterBlockingStub> {
    private StepCounterBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private StepCounterBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StepCounterBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new StepCounterBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Requests how many steps have been done in the last hour (Unary)
     * </pre>
     */
    public sw.stepCounter.service1.StepCount getLastHourSteps(com.google.protobuf.Empty request) {
      return blockingUnaryCall(
          getChannel(), getGetLastHourStepsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Requests average steps for the period given //changed
     * </pre>
     */
    public sw.stepCounter.service1.AverageStepCount getAverage(sw.stepCounter.service1.AverageStepRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetAverageMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Sets a daily step goal and tracks progress towards that goal 
     * </pre>
     */
    public java.util.Iterator<sw.stepCounter.service1.StepGoalResponse> setStepGoal(
        sw.stepCounter.service1.StepGoal request) {
      return blockingServerStreamingCall(
          getChannel(), getSetStepGoalMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * StepCounter service
   * </pre>
   */
  public static final class StepCounterFutureStub extends io.grpc.stub.AbstractStub<StepCounterFutureStub> {
    private StepCounterFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private StepCounterFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StepCounterFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new StepCounterFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Requests how many steps have been done in the last hour (Unary)
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<sw.stepCounter.service1.StepCount> getLastHourSteps(
        com.google.protobuf.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(getGetLastHourStepsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Requests average steps for the period given //changed
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<sw.stepCounter.service1.AverageStepCount> getAverage(
        sw.stepCounter.service1.AverageStepRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAverageMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_LAST_HOUR_STEPS = 0;
  private static final int METHODID_GET_AVERAGE = 1;
  private static final int METHODID_SET_STEP_GOAL = 2;
  private static final int METHODID_SEND_STEPS = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final StepCounterImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(StepCounterImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_LAST_HOUR_STEPS:
          serviceImpl.getLastHourSteps((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<sw.stepCounter.service1.StepCount>) responseObserver);
          break;
        case METHODID_GET_AVERAGE:
          serviceImpl.getAverage((sw.stepCounter.service1.AverageStepRequest) request,
              (io.grpc.stub.StreamObserver<sw.stepCounter.service1.AverageStepCount>) responseObserver);
          break;
        case METHODID_SET_STEP_GOAL:
          serviceImpl.setStepGoal((sw.stepCounter.service1.StepGoal) request,
              (io.grpc.stub.StreamObserver<sw.stepCounter.service1.StepGoalResponse>) responseObserver);
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
        case METHODID_SEND_STEPS:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.sendSteps(
              (io.grpc.stub.StreamObserver<sw.stepCounter.service1.StepCount>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class StepCounterBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    StepCounterBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return sw.stepCounter.service1.stepCounterImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("StepCounter");
    }
  }

  private static final class StepCounterFileDescriptorSupplier
      extends StepCounterBaseDescriptorSupplier {
    StepCounterFileDescriptorSupplier() {}
  }

  private static final class StepCounterMethodDescriptorSupplier
      extends StepCounterBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    StepCounterMethodDescriptorSupplier(String methodName) {
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
      synchronized (StepCounterGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new StepCounterFileDescriptorSupplier())
              .addMethod(getSendStepsMethod())
              .addMethod(getGetLastHourStepsMethod())
              .addMethod(getGetAverageMethod())
              .addMethod(getSetStepGoalMethod())
              .build();
        }
      }
    }
    return result;
  }
}
