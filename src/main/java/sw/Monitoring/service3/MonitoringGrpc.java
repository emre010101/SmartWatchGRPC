package sw.Monitoring.service3;

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
 *&#47; Monitoring service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: service3.proto")
public final class MonitoringGrpc {

  private MonitoringGrpc() {}

  public static final String SERVICE_NAME = "service3.Monitoring";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sw.Monitoring.service3.UserRecords,
      sw.Monitoring.service3.ServerResponse> getSetUserRecordsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetUserRecords",
      requestType = sw.Monitoring.service3.UserRecords.class,
      responseType = sw.Monitoring.service3.ServerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sw.Monitoring.service3.UserRecords,
      sw.Monitoring.service3.ServerResponse> getSetUserRecordsMethod() {
    io.grpc.MethodDescriptor<sw.Monitoring.service3.UserRecords, sw.Monitoring.service3.ServerResponse> getSetUserRecordsMethod;
    if ((getSetUserRecordsMethod = MonitoringGrpc.getSetUserRecordsMethod) == null) {
      synchronized (MonitoringGrpc.class) {
        if ((getSetUserRecordsMethod = MonitoringGrpc.getSetUserRecordsMethod) == null) {
          MonitoringGrpc.getSetUserRecordsMethod = getSetUserRecordsMethod = 
              io.grpc.MethodDescriptor.<sw.Monitoring.service3.UserRecords, sw.Monitoring.service3.ServerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "service3.Monitoring", "SetUserRecords"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sw.Monitoring.service3.UserRecords.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sw.Monitoring.service3.ServerResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new MonitoringMethodDescriptorSupplier("SetUserRecords"))
                  .build();
          }
        }
     }
     return getSetUserRecordsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sw.Monitoring.service3.GetHealthRecordsRequest,
      sw.Monitoring.service3.GetHealthRecordsResponse> getGetHealthRecordsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetHealthRecords",
      requestType = sw.Monitoring.service3.GetHealthRecordsRequest.class,
      responseType = sw.Monitoring.service3.GetHealthRecordsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sw.Monitoring.service3.GetHealthRecordsRequest,
      sw.Monitoring.service3.GetHealthRecordsResponse> getGetHealthRecordsMethod() {
    io.grpc.MethodDescriptor<sw.Monitoring.service3.GetHealthRecordsRequest, sw.Monitoring.service3.GetHealthRecordsResponse> getGetHealthRecordsMethod;
    if ((getGetHealthRecordsMethod = MonitoringGrpc.getGetHealthRecordsMethod) == null) {
      synchronized (MonitoringGrpc.class) {
        if ((getGetHealthRecordsMethod = MonitoringGrpc.getGetHealthRecordsMethod) == null) {
          MonitoringGrpc.getGetHealthRecordsMethod = getGetHealthRecordsMethod = 
              io.grpc.MethodDescriptor.<sw.Monitoring.service3.GetHealthRecordsRequest, sw.Monitoring.service3.GetHealthRecordsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "service3.Monitoring", "GetHealthRecords"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sw.Monitoring.service3.GetHealthRecordsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sw.Monitoring.service3.GetHealthRecordsResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new MonitoringMethodDescriptorSupplier("GetHealthRecords"))
                  .build();
          }
        }
     }
     return getGetHealthRecordsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sw.Monitoring.service3.HeartRateRequest,
      sw.Monitoring.service3.HeartRateWarning> getMonitorHeartRateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MonitorHeartRate",
      requestType = sw.Monitoring.service3.HeartRateRequest.class,
      responseType = sw.Monitoring.service3.HeartRateWarning.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<sw.Monitoring.service3.HeartRateRequest,
      sw.Monitoring.service3.HeartRateWarning> getMonitorHeartRateMethod() {
    io.grpc.MethodDescriptor<sw.Monitoring.service3.HeartRateRequest, sw.Monitoring.service3.HeartRateWarning> getMonitorHeartRateMethod;
    if ((getMonitorHeartRateMethod = MonitoringGrpc.getMonitorHeartRateMethod) == null) {
      synchronized (MonitoringGrpc.class) {
        if ((getMonitorHeartRateMethod = MonitoringGrpc.getMonitorHeartRateMethod) == null) {
          MonitoringGrpc.getMonitorHeartRateMethod = getMonitorHeartRateMethod = 
              io.grpc.MethodDescriptor.<sw.Monitoring.service3.HeartRateRequest, sw.Monitoring.service3.HeartRateWarning>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "service3.Monitoring", "MonitorHeartRate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sw.Monitoring.service3.HeartRateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sw.Monitoring.service3.HeartRateWarning.getDefaultInstance()))
                  .setSchemaDescriptor(new MonitoringMethodDescriptorSupplier("MonitorHeartRate"))
                  .build();
          }
        }
     }
     return getMonitorHeartRateMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MonitoringStub newStub(io.grpc.Channel channel) {
    return new MonitoringStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MonitoringBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new MonitoringBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MonitoringFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new MonitoringFutureStub(channel);
  }

  /**
   * <pre>
   *&#47; Monitoring service
   * </pre>
   */
  public static abstract class MonitoringImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Saves user credentials for accessing health records (Unary)
     * </pre>
     */
    public void setUserRecords(sw.Monitoring.service3.UserRecords request,
        io.grpc.stub.StreamObserver<sw.Monitoring.service3.ServerResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSetUserRecordsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Requests health records (Unary)
     * </pre>
     */
    public void getHealthRecords(sw.Monitoring.service3.GetHealthRecordsRequest request,
        io.grpc.stub.StreamObserver<sw.Monitoring.service3.GetHealthRecordsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetHealthRecordsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Sends heart rate data from client and receives warnings from server (Bidirectional Streaming)
     * </pre>
     */
    public io.grpc.stub.StreamObserver<sw.Monitoring.service3.HeartRateRequest> monitorHeartRate(
        io.grpc.stub.StreamObserver<sw.Monitoring.service3.HeartRateWarning> responseObserver) {
      return asyncUnimplementedStreamingCall(getMonitorHeartRateMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSetUserRecordsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sw.Monitoring.service3.UserRecords,
                sw.Monitoring.service3.ServerResponse>(
                  this, METHODID_SET_USER_RECORDS)))
          .addMethod(
            getGetHealthRecordsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sw.Monitoring.service3.GetHealthRecordsRequest,
                sw.Monitoring.service3.GetHealthRecordsResponse>(
                  this, METHODID_GET_HEALTH_RECORDS)))
          .addMethod(
            getMonitorHeartRateMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                sw.Monitoring.service3.HeartRateRequest,
                sw.Monitoring.service3.HeartRateWarning>(
                  this, METHODID_MONITOR_HEART_RATE)))
          .build();
    }
  }

  /**
   * <pre>
   *&#47; Monitoring service
   * </pre>
   */
  public static final class MonitoringStub extends io.grpc.stub.AbstractStub<MonitoringStub> {
    private MonitoringStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MonitoringStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MonitoringStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MonitoringStub(channel, callOptions);
    }

    /**
     * <pre>
     * Saves user credentials for accessing health records (Unary)
     * </pre>
     */
    public void setUserRecords(sw.Monitoring.service3.UserRecords request,
        io.grpc.stub.StreamObserver<sw.Monitoring.service3.ServerResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetUserRecordsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Requests health records (Unary)
     * </pre>
     */
    public void getHealthRecords(sw.Monitoring.service3.GetHealthRecordsRequest request,
        io.grpc.stub.StreamObserver<sw.Monitoring.service3.GetHealthRecordsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetHealthRecordsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Sends heart rate data from client and receives warnings from server (Bidirectional Streaming)
     * </pre>
     */
    public io.grpc.stub.StreamObserver<sw.Monitoring.service3.HeartRateRequest> monitorHeartRate(
        io.grpc.stub.StreamObserver<sw.Monitoring.service3.HeartRateWarning> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getMonitorHeartRateMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * <pre>
   *&#47; Monitoring service
   * </pre>
   */
  public static final class MonitoringBlockingStub extends io.grpc.stub.AbstractStub<MonitoringBlockingStub> {
    private MonitoringBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MonitoringBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MonitoringBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MonitoringBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Saves user credentials for accessing health records (Unary)
     * </pre>
     */
    public sw.Monitoring.service3.ServerResponse setUserRecords(sw.Monitoring.service3.UserRecords request) {
      return blockingUnaryCall(
          getChannel(), getSetUserRecordsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Requests health records (Unary)
     * </pre>
     */
    public sw.Monitoring.service3.GetHealthRecordsResponse getHealthRecords(sw.Monitoring.service3.GetHealthRecordsRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetHealthRecordsMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *&#47; Monitoring service
   * </pre>
   */
  public static final class MonitoringFutureStub extends io.grpc.stub.AbstractStub<MonitoringFutureStub> {
    private MonitoringFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MonitoringFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MonitoringFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MonitoringFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Saves user credentials for accessing health records (Unary)
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<sw.Monitoring.service3.ServerResponse> setUserRecords(
        sw.Monitoring.service3.UserRecords request) {
      return futureUnaryCall(
          getChannel().newCall(getSetUserRecordsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Requests health records (Unary)
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<sw.Monitoring.service3.GetHealthRecordsResponse> getHealthRecords(
        sw.Monitoring.service3.GetHealthRecordsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetHealthRecordsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SET_USER_RECORDS = 0;
  private static final int METHODID_GET_HEALTH_RECORDS = 1;
  private static final int METHODID_MONITOR_HEART_RATE = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MonitoringImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MonitoringImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SET_USER_RECORDS:
          serviceImpl.setUserRecords((sw.Monitoring.service3.UserRecords) request,
              (io.grpc.stub.StreamObserver<sw.Monitoring.service3.ServerResponse>) responseObserver);
          break;
        case METHODID_GET_HEALTH_RECORDS:
          serviceImpl.getHealthRecords((sw.Monitoring.service3.GetHealthRecordsRequest) request,
              (io.grpc.stub.StreamObserver<sw.Monitoring.service3.GetHealthRecordsResponse>) responseObserver);
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
        case METHODID_MONITOR_HEART_RATE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.monitorHeartRate(
              (io.grpc.stub.StreamObserver<sw.Monitoring.service3.HeartRateWarning>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class MonitoringBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MonitoringBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return sw.Monitoring.service3.MonitorImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Monitoring");
    }
  }

  private static final class MonitoringFileDescriptorSupplier
      extends MonitoringBaseDescriptorSupplier {
    MonitoringFileDescriptorSupplier() {}
  }

  private static final class MonitoringMethodDescriptorSupplier
      extends MonitoringBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MonitoringMethodDescriptorSupplier(String methodName) {
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
      synchronized (MonitoringGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MonitoringFileDescriptorSupplier())
              .addMethod(getSetUserRecordsMethod())
              .addMethod(getGetHealthRecordsMethod())
              .addMethod(getMonitorHeartRateMethod())
              .build();
        }
      }
    }
    return result;
  }
}
