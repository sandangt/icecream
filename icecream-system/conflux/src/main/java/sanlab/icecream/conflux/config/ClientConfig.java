package sanlab.icecream.conflux.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;

@Configuration
public class ClientConfig {

    @Lazy
    @Bean
    public RSocketRequester consulRSocketClient(
        RSocketStrategies rSocketStrategies,
        @Value("${app.consul.rsocket.port}") String consulRSocketPort,
        @Value("${app.consul.rsocket.host}") String consulRSocketHost
    ) {
        return RSocketRequester.builder()
            .rsocketStrategies(rSocketStrategies)
            .dataMimeType(MediaType.APPLICATION_CBOR)
            .tcp(consulRSocketHost, Integer.parseInt(consulRSocketPort));
    }

}
