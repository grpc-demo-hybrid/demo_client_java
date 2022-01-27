package dojo.config;

import io.grpc.Channel;
import io.grpc.examples.helloworld.GreeterGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcSvcConfig {
    @GrpcClient("simple-svc")
    private Channel simpleSvcChannel;

    @Bean
    public GreeterGrpc.GreeterBlockingStub greeterBlockingStub() {
        return GreeterGrpc.newBlockingStub(simpleSvcChannel);
    }
}
