package sanlab.icecream.product.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sanlab.icecream.sharedlib.constant.GrpcChannel;


@Configuration
public class GrpcChannelConfig {
    @Value("${module.lookup.grpcUrl}")
    private String grpcLookupUrl;

    @Bean(name = GrpcChannel.LOOKUP)
    ManagedChannel managedLookupChannel() {
        return ManagedChannelBuilder.forTarget(grpcLookupUrl).usePlaintext().build();
    }
}
