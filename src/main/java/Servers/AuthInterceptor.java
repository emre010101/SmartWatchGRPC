package Servers;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.grpc.Status;

public class AuthInterceptor implements ServerInterceptor {

    private static final String API_KEY = "API key <9546287>";

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
        // Get the authentication token from the metadata.
        String apiKey = headers.get(Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER));

        // Check if the authentication token is valid.
        if (isValidApiKey(apiKey)) {
            // If the token is valid, proceed with the request.
        	System.out.println("Testing passed !!>................");
            return next.startCall(call, headers);
        } else {
            // If the token is invalid, terminate the call with an error status.
        	System.out.println("Testing NOT passed !!>................");

            call.close(Status.UNAUTHENTICATED.withDescription("Invalid API key"), new Metadata());
            return new ServerCall.Listener<ReqT>() {};
        }
    }

    private boolean isValidApiKey(String apiKey) {
        // Check if the API key matches the expected value.
        return API_KEY.equals(apiKey);
    }
}
