package sanlab.icecream.consul.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.codec.cbor.Jackson2CborDecoder;
import org.springframework.http.codec.cbor.Jackson2CborEncoder;
import org.springframework.messaging.rsocket.RSocketStrategies;

@Configuration
public class RSocketConfig {

    @Lazy
    @Bean
    public RSocketStrategies rSocketStrategies() {
        return RSocketStrategies.builder()
            .decoder(new Jackson2CborDecoder())
            .encoder(new Jackson2CborEncoder())
            .build();
    }
}
