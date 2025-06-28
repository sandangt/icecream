package sanlab.icecream.consul.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.util.MimeType;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientConfig {

    @Value("${app.minio.url}")
    private String minioUrl;
    @Value("${app.minio.access.key}")
    private String accessKey;
    @Value("${app.minio.access.secret}")
    private String accessSecret;

    @Value("${app.echo.rsocket.port}")
    private String echoRsockPort;
    @Value("${app.echo.rsocket.host}")
    private String echoRsockHost;

    @Value("${app.memoir.rsocket.port}")
    private String memoirRsockPort;
    @Value("${app.memoir.rsocket.host}")
    private String memoirRsockHost;

    @Value("${app.chronos.rsocket.port}")
    private String chronosRsockPort;
    @Value("${app.chronos.rsocket.host}")
    private String chronosRsockHost;

    private static final String APPLICATION_CBOR = "application/cbor";

    @Bean
    public RestClient restClient() {
        return RestClient.create();
    }

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
            .endpoint(minioUrl)
            .credentials(accessKey, accessSecret)
            .build();
    }

    @Bean("echoRsockClient")
    public RSocketRequester echoRsockClient(RSocketRequester.Builder builder) {
        return builder
            .dataMimeType(MimeType.valueOf(APPLICATION_CBOR))
            .tcp(echoRsockHost, Integer.parseInt(echoRsockPort));
    }

    @Bean("memoirRsockClient")
    public RSocketRequester memoirRsockClient(RSocketRequester.Builder builder) {
        return builder
            .dataMimeType(MimeType.valueOf(APPLICATION_CBOR))
            .tcp(memoirRsockHost, Integer.parseInt(memoirRsockPort));
    }

    @Bean("chronosRsockClient")
    public RSocketRequester chronosRsockClient(RSocketRequester.Builder builder) {
        return builder
            .dataMimeType(MimeType.valueOf(APPLICATION_CBOR))
            .tcp(chronosRsockHost, Integer.parseInt(chronosRsockPort));
    }

}
