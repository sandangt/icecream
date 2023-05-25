package sanlab.icecream.gateway.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sanlab.icecream.sharedlib.constant.GrpcChannel;


@Configuration
public class GrpcChannelConfig {
    @Value("${module.product.grpcUrl}")
    private String grpcProductUrl;

    @Bean(name = GrpcChannel.PRODUCT)
    ManagedChannel managedProductChannel() {
        return ManagedChannelBuilder.forTarget(grpcProductUrl).usePlaintext().build();
    }
}
