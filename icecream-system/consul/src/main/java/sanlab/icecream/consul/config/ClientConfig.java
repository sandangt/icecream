package sanlab.icecream.consul.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientConfig {

    @Lazy
    @Bean
    public RestClient restClient() {
        return RestClient.create();
    }

    @Bean
    public RestClient identityClient(@Value("${app.identity.url}") String identityUrl) {
        return RestClient.create(identityUrl);
    }

    @Lazy
    @Bean
    public MinioClient minioClient(
        @Value("${app.minio.url}") String url,
        @Value("${app.minio.access.key}") String accessKey,
        @Value("${app.minio.access.secret}") String accessSecret
    ) {
        return MinioClient.builder()
            .endpoint(url)
            .credentials(accessKey, accessSecret)
            .build();
    }

    @Lazy
    @Bean
    public RSocketRequester echoRSocketClient(
        RSocketStrategies rSocketStrategies,
        @Value("${app.echo.rsocket.port}") String port,
        @Value("${app.echo.rsocket.host}") String host
    ) {
        return RSocketRequester.builder()
            .rsocketStrategies(rSocketStrategies)
            .dataMimeType(MediaType.APPLICATION_CBOR)
            .tcp(host, Integer.parseInt(port));
    }

    @Lazy
    @Bean
    public RSocketRequester memoirRSocketClient(
        RSocketStrategies rSocketStrategies,
        @Value("${app.memoir.rsocket.port}") String port,
        @Value("${app.memoir.rsocket.host}") String host
    ) {
        return RSocketRequester.builder()
            .rsocketStrategies(rSocketStrategies)
            .dataMimeType(MediaType.APPLICATION_CBOR)
            .tcp(host, Integer.parseInt(port));
    }

    @Lazy
    @Bean
    public RSocketRequester chronosRSocketClient(
        RSocketStrategies rSocketStrategies,
        @Value("${app.chronos.rsocket.port}") String port,
        @Value("${app.chronos.rsocket.host}") String host
    ) {
        return RSocketRequester.builder()
            .rsocketStrategies(rSocketStrategies)
            .dataMimeType(MediaType.APPLICATION_CBOR)
            .tcp(host, Integer.parseInt(port));
    }

}
